import numpy as np
import matplotlib.pyplot as plt
import numpy.linalg
from copy import deepcopy
from math import sqrt,pi
from pylab import *

scale = 0.25

#quelques constantes

g =  9.81 #gravity
m = 16*scale #mass

DX = 5*scale
DY = 3.5*scale
DZ = 4.0*scale

lx = 0.5*scale
ly = 0.5*scale
lz = 0.5*scale

hauteurFenetre = 1.075*scale

## Basis change from the (X,Y,Z) system to the (G,x,y,z) system

def basisChange(X,Y,Z,G): # return coordinates in (G,x,y,z) system, G beeing written in the (X,Y,Z) system

    L = np.sqrt(G[0]**2 + G[1]**2 + G[2]**2)
    l = np.sqrt(G[0] ** 2 + G[1] ** 2)
    M = np.array([X,Y,Z])

    R = np.array([[-G[0] / L, -G[1] / L, -G[2] / L],
                  [G[1] / l, -G[0] / l, 0],
                  [-G[2] * G[0] / l / L, -G[2] * G[1] / l / L, (G[0] ** 2 + G[1] ** 2) / l / L]])

    inter = R.dot(M - G)
    
    res = np.array([inter[1],-inter[0],inter[2]])
    return res

def basisChangeLinear(X,Y,Z,G): # return coordinates in (G,x,y,z) system, G beeing written in the (X,Y,Z) system

    L = np.sqrt(G[0]**2 + G[1]**2 + G[2]**2)
    l = np.sqrt(G[0] ** 2 + G[1] ** 2)
    M = np.array([X,Y,Z])

    R = np.array([[-G[0] / L, -G[1] / L, -G[2] / L],
                  [G[1] / l, -G[0] / l, 0],
                  [-G[2] * G[0] / l / L, -G[2] * G[1] / l / L, (G[0] ** 2 + G[1] ** 2) / l / L]])
    inter = R.dot(M)
    
    res = np.array([inter[1],-inter[0],inter[2]])
    return res

### UnitVector Tensions

def unitVectorsTensions(G,P,r): # P est un tableau 8X3, contenant les 8 points
                                # Pi dans le système de coordonnées (O,X,Y,Z)
    
    u = np.zeros((8,3)) # unitVectors
    
    for i in range(8):
        pi = basisChange(P[i][0],P[i][1],P[i][2],G)
        u[i] = (pi - r[i])/np.linalg.norm(pi - r[i])
    
    return(u)



### Fonction principale

def tensionPlane(G,u,r): # u 8x3 sont les vecteurs normés directeur des tensions, r = 8x3 sont les point d'attache des cables sur le robot
    
    vecteurGravite = basisChangeLinear(0,0,-m*g,G)
    
    A = np.zeros((6,8))
    B = np.array([vecteurGravite[0],vecteurGravite[1],vecteurGravite[2],0,0,0])
    
    for i in range(8):
        A[0][i] = u[i][0]
        A[1][i] = u[i][1]
        A[2][i] = u[i][2]
        
        A[3][i] = r[i][1]*u[i][2] - r[i][2]*u[i][1]
        A[4][i] = r[i][2]*u[i][0] - r[i][0]*u[i][2]
        A[5][i] = r[i][0]*u[i][1] - r[i][1]*u[i][0]
    
    
    P = A[:6,:6] #submatrix of A
    C = A[:,6] #column 7 of A
    D = A[:,7] #column 8 of A
    
    det = np.linalg.det(P)
    
    if (abs(det)<0.00000000000001):
        print("Matrice non inversible")
        print(det)
    
    Q = np.linalg.inv(P)
    
    U = np.concatenate((Q.dot(C),[-1,0]),axis=0) #first vector of the basis of the ker
    V = np.concatenate((Q.dot(D),[0,-1]),axis=0) #second vector of the basis of the ker
    
    X = np.concatenate((Q.dot(B),[0,0]),axis=0) #particular solution of AX = B
    
    return(U,V,X)

### Tests algébriques

A = np.random.random((6,8)) #random postitions of Pi
B = np.array([-1,0,-1,0,0,0]) #modify and adapt to the mass

P = A[:6,:6] #submatrix of A
C = A[:,6] #column 7 of A
D = A[:,7] #column 8 of A

det = np.linalg.det(P)

if (abs(det)<0.000000001):
    print(det)

Q = np.linalg.inv(P)

U = np.concatenate((Q.dot(C),[-1,0]),axis=0) #first vector of the basis of the ker
V = np.concatenate((Q.dot(D),[0,-1]),axis=0) #second vector of the basis of the ker

X = np.concatenate((Q.dot(B),[0,0]),axis=0) #particular solution of AX = B

print(A.dot(U)) # AU = 0
print(A.dot(V)) # AV = 0
print(A.dot(X)) # AX = B

print(A.dot(5613*U + 1235*V + X))


### Orthogonalisation

def orthogonalisation(U,V):
    u = U/(np.linalg.norm(U))
    vv = V - (np.vdot(u,V))*u
    v = vv/(np.linalg.norm(vv))
    return(u,v)

U = np.array([2,0])
V = np.array([1,1])

print(orthogonalisation(U,V))

### Appartenance au convexe

def appartientOmega(u,v,x,alph,beta):
    t = True
    for i in range(8):
        t = t&(not(alph*u[i]+beta*v[i]+x[i]<0))
    return(t)

u = np.array([1,1,0,0,0,0,0,0])
v = np.array([0,1,0,0,0,0,0,0])
x = np.array([0,0,0,0,0,0,0,0])

print(appartientOmega(u,v,x,1,-1.1))

###

tableauInit = []
for i in range(8):
    tableauInit.append([-1,-1,-1,-1,-1,-1,-1,-1])

# tableauInit est le tableau qui va stocker des couples de float représentant un poin du plan (alpha,beta)



def intersectionsEquations(u,v,x):
    tableauIntersections = deepcopy(tableauInit)
    for i in range(8):
        for j in range(i+1,8):
            det = u[i]*v[j]-u[j]*v[i]
            if abs(det)>1e-10: #à modifier éventuellement
                alph = (v[i]*x[j]-v[j]*x[i])/det
                beta = (u[j]*x[i]-u[i]*x[j])/det
                tableauIntersections[i][j] = [alph,beta] #ça marche ça ?
    return(tableauIntersections)

def pointsExtremauxConfigurations(u,v,x,tableauIntersections):
    tableauOmega = deepcopy(tableauIntersections) #Omega est l'ensemble des points d'intersections des droites qui sont dans tous les demis plans
    for i in range(8):
        for j in range(i+1,8):
            if tableauIntersections[i][j]!=-1:
                possible = True
                alph = tableauIntersections[i][j][0]
                beta = tableauIntersections[i][j][1]
                for k in range(8):
                    if (k!=i)and(k!=j):
                        possible = possible&(alph*u[k]+beta*v[k]+x[k]>0)
                if not(possible):
                    tableauOmega[i][j] = -1
    return(tableauOmega)

def determinerDroitesLimites(tableauOmega):
    nombrePoints = [0 for i in range(8)] #représente le nombre de points de Omega sur chaque droite, compris entre 1 et 2
    droitesExtremales = []
    droitesInter = []
    for i in range(8):
        for j in range(i+1,8):
            if tableauOmega[i][j]!=-1:
                nombrePoints[i]+=1
                nombrePoints[j]+=1
    for i in range(8):
        if nombrePoints[i]==1:
            droitesExtremales.append(i)
        if nombrePoints[i]==2:
            droitesInter.append(i)
    return(droitesExtremales,droitesInter)

def estBorne(droitesExtremales):
    return(droitesExtremales==[])

def determinerVecteursExtreme(u,v,x,tableauOmega,droitesExtremales):
    vecteurs = []
    i1 = droitesExtremales[0]
    i2 = droitesExtremales[1]
    j1 = -1
    j2 = -1
    
    
    for j in range(1,8):
        if (j!=i1):
            if ((tableauOmega[i1][j]!=-1)or(tableauOmega[j][i1]!=-1)):
                j1=j
    for j in range(1,8):
        if (j!=i2):
            if ((tableauOmega[i2][j]!=-1)or(tableauOmega[j][i2]!=-1)):
                j2=j
    
    if (tableauOmega[i1][j1]==-1):
        point1=tableauOmega[j1][i1]
    else:
        point1=tableauOmega[i1][j1]
    
    if (tableauOmega[i2][j2]==-1):
        point2=tableauOmega[j2][i2]
    else:
        point2=tableauOmega[i2][j2]
    
    alph1 = point1[0] + v[i1]
    beta1 = point1[1] - u[i1]
    
    if (alph1*u[j1]+beta1*v[j1]+x[j1]>-1e-7):
        vecteurs.append([v[i1],-u[i1]])
    else:
        vecteurs.append([-v[i1],u[i1]])

    
    alph2 = point2[0] + v[i2]
    beta2 = point2[1] - u[i2]
    
    if (alph2*u[j2]+beta2*v[j2]+x[j2]>-1e-7):
        vecteurs.append([v[i2],-u[i2]])
    else:
        vecteurs.append([-v[i2],u[i2]])
    
    for k in range(2):
        print(vecteurs[k])
        norme = sqrt(vecteurs[k][0]**2+vecteurs[k][1]**2)
        vecteurs[k] = [vecteurs[k][0]/norme,vecteurs[k][1]/norme]
    return(vecteurs)

###


def estPositionPossible(u,v,x):
    tableauOmega = pointsExtremauxConfigurations(u,v,x,intersectionsEquations(u,v,x))
    for i in range(8):
        for j in range(i+1,8):
            if tableauOmega[i][j]!=-1:
                return(True)
    return(False)

def descriptionConfigurationsPossibles(u,v,x):
    tableauOmega = pointsExtremauxConfigurations(u,v,x,intersectionsEquations(u,v,x))
    droitesExtremales,droitesInter = determinerDroitesLimites(tableauOmega)
    
    if estBorne(droitesExtremales):
        vecteurs=[]
    else:
        vecteurs=determinerVecteursExtreme(u,v,x,tableauOmega,droitesExtremales)
    
    points = []
    
    for i in range(8):
        for j in range(i+1,8):
            if tableauOmega[i][j]!=-1:
                points.append([tableauOmega[i][j]])
    return(points,vecteurs)


### Projection sur le convexe

def projectionSurLePlan(u,v,x): #on suppose u et v BON du plan
    P0 = x - (np.vdot(u,x)*u +np.vdot(v,x)*v)
    return(np.array([np.dot(u,P0),np.dot(v,P0)]))

u = np.array([sqrt(0.5),0,sqrt(0.5)])
v = np.array([0,1,0])
x = np.array([0,0,-1])

#print(projectionSurLePlan(u,v,x))

def projectionSurUneDroite(x,y,a,b,c): #On projette P0 sur la droite d'équation ax+by+c=0, ce qui donne le point P1
    u = np.array([b/sqrt(a*a+b*b),-a/sqrt(a*a+b*b)])
    P0 = np.array([x,y])
    if (b==0):
        P1 = np.array([-c/a,P0[1]])
    else:
        x0 = np.array([-c/a,0])
        P1 = x0 + np.dot(u,P0-x0)*u
    return(P1)

#print(projectionSurUneDroite(0.5,0.5,-1,1,-1))


    

def projectionSurLeConvexe(u,v,x,alph0,beta0,tableauOmega,droitesInter,droitesExtremales):# on suppose omega non vide, et on se place dans le plan (alph,beta)
    P0 = projectionSurLePlan(u,v,x)
    if(appartientOmega(u,v,x,P0[0],P0[1])):
        return(np.array([alph0,beta0]))
    
    projetesPotentiels = [] #on stock les projetés orthogonaux qui sont dans le convexe Omega, sous la forme de np.array(alph,beta)
    for i in range(8): # on traite les points d'intersections
        for j in range(i+1,8):
            if tableauOmega[i][j]!=-1:
                projetesPotentiels.append(np.array([tableauOmega[i][j][0],tableauOmega[i][j][1]]))
    
    for i in droitesInter:
        projete = projectionSurUneDroite(alph0,beta0,u[i],v[i],x[i])
        if(appartientOmega(u,v,x,projete[0],projete[1])):
            projetesPotentiels.append(projete)
    
    for i in droitesExtremales:
        projete = projectionSurUneDroite(alph0,beta0,u[i],v[i],x[i])
        if(appartientOmega(u,v,x,projete[0],projete[1])):
            projetesPotentiels.append(projete)
    
    # projetePotentiels contient à présent les projetés de P0 sur les droites, ainsi que les points extrêmaux
    # on cherche à présent le point de cette liste le plus proche de P0 :
    
    def d(A,B):
        return(sqrt((A[0]-B[0])*(A[0]-B[0])+(A[1]-B[1])*(A[1]-B[1])))
    
    P1 = projetesPotentiels[0]
    min = d(P0,P1)

    for point in projetesPotentiels:
        dist = d(point,P0)
        if(dist<min):
            P1 = point
            min = dist
    
    return(P1)



### Description du convexe

def descriptionConfigurations(G,P,r):
    vectorsTensions = unitVectorsTensions(G,P,r)
    U,V,x= tensionPlane(G,vectorsTensions,r)
    u,v = orthogonalisation(U,V)
    return(descriptionConfigurationsPossibles(u,v,x),intersectionsEquations(u,v,x))



P = np.array([[0, 1, -1], [-2, 1, -1], [0, -1, -1.], [-2, -1, -1],
              [0, 1, 1], [-2, 1., 1], [0, -1, 1], [-2, -1, 1]], dtype=np.float32)

r = 0.3*np.array([[0, 1, -1], [-1, 0, -1], [1, 0, -1], [0, -1, -1],
                   [1, 0, 1], [0, 1, 1], [0, -1, 1], [-1, 0, 1]])

G = np.array([-1, 0.1, 0.2], dtype=np.float32)

(listePoints,listeVecteurs),pointsIntersections = descriptionConfigurations(G,P,r)

print(listePoints) #coordonnées dans la base (X,u,v) où (u,v) orthonormée

for ligne in pointsIntersections:
    for point in ligne:
        if (point!=-1):
            plt.plot(point[0],point[1],'.k')

for point in listePoints:
    plt.plot(point[0],point[1],'.r')

### Fonction qui permet de savoir si la configuration est possible

def estConfigPossible(G,P,r):
    vectorsTensions = unitVectorsTensions(G,P,r)
    U,V,x= tensionPlane(G,vectorsTensions,r)    
    u,v = orthogonalisation(U,V)
    return(estPositionPossible(u,v,x))

#test

P = np.array([[0, 1, -1], [-2, 1, -1], [0, -1, -1.], [-2, -1, -1],
              [0, 1, 1], [-2, 1., 1], [0, -1, 1], [-2, -1, 1]], dtype=np.float32)

r = 0.3*np.array([[0, 1, -1], [-1, 0, -1], [1, 0, -1], [0, -1, -1],
                   [1, 0, 1], [0, 1, 1], [0, -1, 1], [-1, 0, 1]])

G = np.array([-1, 0.1, 0.2], dtype=np.float32)

print(estConfigPossible(G,P,r))

### Fonction globale donnant la configuration minimsant les tensions, pourvu que la configuration soit possible :

def configOptimale(G,P,r):
    vectorsTensions = unitVectorsTensions(G,P,r)
    U,V,x= tensionPlane(G,vectorsTensions,r)
    
    u,v = orthogonalisation(U,V)
    P0 = projectionSurLePlan(u,v,x)
    
    tableauIntersections = intersectionsEquations(u,v,x)
    tableauOmega = pointsExtremauxConfigurations(u,v,x,tableauIntersections)
    droitesExtremales,droitesInter = determinerDroitesLimites(tableauOmega)
    P1 = projectionSurLeConvexe(u,v,x,P0[0],P0[1],tableauOmega,droitesInter,droitesExtremales)
    #return(P1) #renvoie alpha et beta
    
    # for i in droitesInter:
    #     extr1=-1
    #     extr2=-1
    #     for j in range(i+1,8):
    #         if(tableauOmega!=-1):
    #             if extr1==-1:
    #                 extr1 = np.array([tableauOmega[i][j][0],tableauOmega[i][j][1]])
    #             else :
    #                 extr2 = np.array([tableauOmega[i][j][0],tableauOmega[i][j][1]])
    #     plt.plot([extr1[0],extr2[0]],[extr1[1],extr2[1]],'-k')
    return(P1[0]*u+P1[1]*v+x)
    
print(configOptimale(G,P,r))


### Tests sur la possibilité d'une configuration


P =  np.array([[DX/2, -DY, -hauteurFenetre], [DX/2, 0, -hauteurFenetre], [-DX/2, 0, -hauteurFenetre], [-DX/2, -DY, -hauteurFenetre],
              [DX/2, -DY, DZ-hauteurFenetre], [DX/2, 0, DZ-hauteurFenetre], [-DX/2,0, DZ-hauteurFenetre], [-DX/2, -DY, DZ-hauteurFenetre]], dtype=np.float32)

r = 0.5*np.array([[-1, -1, 1],[1, -1, 1], [1, 1, 1], [-1, 1, 1],[-1, -1, -1], [1, -1, -1], [1, 1, -1], [-1, 1, -1]],dtype=np.float32)

G = np.array([0,-DY/2,DZ/2-hauteurFenetre], dtype=np.float32)

#plotPosition(G,P,r)

#print(estConfigPossible(G,P,r))
print(descriptionConfigurations(G,P,r))

### tests
plt.figure()
P = np.array([[0, 1, -1], [-2, 1, -1], [0, -1, -1.], [-2, -1, -1],
              [0, 1, 1], [-2, 1., 1], [0, -1, 1], [-2, -1, 1]], dtype=np.float32)

r = 0.3*np.array([[0, 1, -1], [-1, 0, -1], [1, 0, -1], [0, -1, -1],
                   [1, 0, 1], [0, 1, 1], [0, -1, 1], [-1, 0, 1]])

G = np.array([-1, 0.1, 0.2], dtype=np.float32)

def testGraphique(G,P,r):
    
    
    
    (listePoints,listeVecteurs),pointsIntersections = descriptionConfigurations(G,P,r)
    
    
    for ligne in pointsIntersections:
        for point in ligne:
            if (point!=-1):
                plt.plot(point[0],point[1],'.k')
    
    for point in listePoints:
        plt.plot(point[0],point[1],'.r')
    
    if listePoints!=[]:
        P1 = configOptimale(G,P,r)
        
        plt.plot(P1[0],P1[1],'xm')
        
        plt.plot(0,0,'.m')
        
        theta = np.linspace(0, 2*pi, 4000)
        
        r = sqrt(P1[0]*P1[0]+P1[1]*P1[1])
        x = r*cos(theta)
        y = r*sin(theta)
        plt.plot(x, y)
    
testGraphique(G,P,r)


print(estConfigPossible(G,P,r))



### Couverture des positions statiques

#en mètres

DX = 5
DY = 3.5
DZ = 4.0

hauteurFenetre = 1.075

P =  np.array([[DX/2, -DY, -hauteurFenetre], [DX/2, 0, -hauteurFenetre], [-DX/2, 0, -hauteurFenetre], [-DX/2, -DY, -hauteurFenetre],
              [DX/2, -DY, DZ-hauteurFenetre], [DX/2, 0, DZ-hauteurFenetre], [-DX/2,0, DZ-hauteurFenetre], [-DX/2, -DY, DZ-hauteurFenetre]], dtype=np.float32)
#les points d'accroche à la structure sont dans les coins du parallépipède

def ensemblePointsTests(xmin,xmax,ymin,ymax,zmin,zmax,nombrePoints): #on aura en sortie nombrePoints^3 points de test
    l = []
    nombrePoints-=1
    x,y,z=xmin,ymin,zmin
    pasX = (xmax-xmin)/nombrePoints
    pasY = (ymax-ymin)/nombrePoints
    pasZ = (zmax-zmin)/nombrePoints
    for i in range(nombrePoints+1):
        for j in range(nombrePoints+1):
            for k in range(nombrePoints+1):
                l.append(np.array([x,y,z]))
                z+=pasZ
            z=zmin
            y+=pasY
        y=ymin
        x+=pasX
    return(l)


def positionsPossibles(listePointsG,P,r): #renvoie un tableau [nombrePoints^2 [position,tensionOptimale]],tensionMax
    positionsPossibles = [-1 for G in listePointsG]
    i=0
    tensionMax = -1
    for G in listePointsG:
        if(estConfigPossible(G,P,r)):
            X = configOptimale(G,P,r)
            positionsPossibles[i] = [G,max(X)]
            if (max(X)>tensionMax):
                tensionMax = max(X)
        #print(positionsPossibles)
        i+=1
    return(positionsPossibles,tensionMax)

r = 0.5*np.array([[-1, -1, 1],[1, -1, 1], [1, 1, 1], [-1, 1, 1],[-1, -1, -1], [1, -1, -1], [1, 1, -1], [-1, 1, -1]],dtype=np.float32)

#print (ensemblePointsTests(-1.5,1.5,-2,0,-0.5,2,4)[2][0], ensemblePointsTests(-1.5,1.5,-2,0,-0.5,2,3)[2][1], ensemblePointsTests(-1.5,1.5,-2,0,-0.5,2,3)[2][2]) 
print(positionsPossibles (ensemblePointsTests(-1.5,1.5,-2,0,-0.5,2,4), P,r))

def proportionTrue (xmin,xmax,ymin,ymax,zmin,zmax,nombrePoints, P,r):
    A = positionsPossibles(ensemblePointsTests(xmin,xmax,ymin,ymax,zmin,zmax,nombrePoints), P,r)[0]
    N = len(A)
    nombreTrue = 0
    for bool in A:
        if bool:
            nombreTrue+=1
    return(nombreTrue / N)


print(positionsPossibles(ensemblePointsTests(-DX/10,DX/10,-DY/2-DY/10,-DY/2+DY/10,DZ/2-hauteurFenetre/10,DZ/2+hauteurFenetre/10,4), P,r))
print(proportionTrue(-DX/2,DX/2,-DY,0,-hauteurFenetre,DZ-hauteurFenetre,6,P,r))

### Limiter la tension des cables

P =  np.array([[DX/2, -DY, -hauteurFenetre], [DX/2, 0, -hauteurFenetre], [-DX/2, 0, -hauteurFenetre], [-DX/2, -DY, -hauteurFenetre],
              [DX/2, -DY, DZ-hauteurFenetre], [DX/2, 0, DZ-hauteurFenetre], [-DX/2,0, DZ-hauteurFenetre], [-DX/2, -DY, DZ-hauteurFenetre]], dtype=np.float32)

r = 0.5*np.array([[-lx, -ly, -lz], [-lx, ly, -lz], [lx, ly, -lz], [lx, -ly, -lz],[-lx, -ly, lz],[-lx, ly, lz], [lx, ly, lz], [lx, -ly, lz]],dtype=np.float32)

def positionTensionLimitées(posPossibles,tensionLim):
    positions = [] #pour stocker les positions restantes
    for pos in posPossibles[0]:
        if (pos!=-1):
            if pos[1]<tensionLim:
                positions.append(pos[0])
    return(positions)


l = positionsPossibles(ensemblePointsTests(-DX/2,DX/2,-DY,0,-hauteurFenetre,DZ-hauteurFenetre,10), P,r)
positionsLimitees = positionTensionLimitées(l,100)






fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')
ax.set_xlim3d(-4, 4)
ax.set_ylim3d(-4, 4)
ax.set_zlim3d(-4, 4)
print_3d(P, ax)
# for point in l[0]:
#     if point!=-1:
#         ax.scatter3D(point[0][0],point[0][1],point[0][2],c='b')
for point in positionsLimitees:
    ax.scatter3D(point[0],point[1],point[2],c='c')
plt.show()

### Points d'accroches croisés selon x : 0<->3, 2<->1, 4<->7, 6<->5

P =  np.array([[DX/2, -DY, -hauteurFenetre], [DX/2, 0, -hauteurFenetre], [-DX/2, 0, -hauteurFenetre], [-DX/2, -DY, -hauteurFenetre],
              [DX/2, -DY, DZ-hauteurFenetre], [DX/2, 0, DZ-hauteurFenetre], [-DX/2,0, DZ-hauteurFenetre], [-DX/2, -DY, DZ-hauteurFenetre]], dtype=np.float32)

r = 0.5*np.array([[-lx, -ly, -lz], [-lx, ly, -lz], [lx, ly, -lz], [lx, -ly, -lz],[-lx, -ly, lz],[-lx, ly, lz], [lx, ly, lz], [lx, -ly, lz]],dtype=np.float32)

e = 0

printPointsAtteignables(ensemblePointsTests(-DX/2+e,DX/2-e,-DY+e,0-e,-hauteurFenetre+e,DZ-hauteurFenetre-e,10), P,r)
print(positionsPossibles(ensemblePointsTests(-DX/2+e,DX/2-e,-DY+e,0-e,-hauteurFenetre+e,DZ-hauteurFenetre-e,10), P,r)[1])

### Représentation 3D des points atteignables

P =  np.array([[DX/2, -DY, -hauteurFenetre], [DX/2, 0, -hauteurFenetre], [-DX/2, 0, -hauteurFenetre], [-DX/2, -DY, -hauteurFenetre],
              [DX/2, -DY, DZ-hauteurFenetre], [DX/2, 0, DZ-hauteurFenetre], [-DX/2,0, DZ-hauteurFenetre], [-DX/2, -DY, DZ-hauteurFenetre]], dtype=np.float32)

r = 0.5*np.array([[-1, -1, -1], [1, -1, -1], [1, 1, -1], [-1, 1, -1],[-1, -1, 1],[1, -1, 1], [1, 1, 1], [-1, 1, 1]],dtype=np.float32)
r = 0.5*np.array([[-1, 1, 1], [1, -1, -1], [1, 1, -1], [-1, -1, 1],[-1, 1, -1],[1, 1, -1], [1, -1, -1], [-1, -1, -1]],dtype=np.float32)

def printPointsAtteignables(listePointsG,P,r):
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    ax.set_xlim3d(-4, 4)
    ax.set_ylim3d(-4, 4)
    ax.set_zlim3d(-4, 4)
    positionsPossibles = [False for G in listePointsG]
    i=0
    tensionMax = -1
    for G in listePointsG:
        if(estConfigPossible(G,P,r)):
            ax.scatter(G[0],G[1],G[2],c='g')
        # else:
        #     ax.scatter(G[0],G[1],G[2],c='r')
        #print(positionsPossibles)
        i+=1
    print_3d(P, ax)
    plt.show()

printPointsAtteignables(ensemblePointsTests(-DX/2,DX/2,-DY,0,-hauteurFenetre,DZ-hauteurFenetre,10), P,r)
    

### Recherche des points d'accroches optimaux

P =  np.array([[DX/2, -DY, -hauteurFenetre], [DX/2, 0, -hauteurFenetre], [-DX/2, 0, -hauteurFenetre], [-DX/2, -DY, -hauteurFenetre],
              [DX/2, -DY, DZ-hauteurFenetre], [DX/2, 0, DZ-hauteurFenetre], [-DX/2,0, DZ-hauteurFenetre], [-DX/2, -DY, DZ-hauteurFenetre]], dtype=np.float32)

def ensembleConfigTest(xmin,xmax,ymin,ymax,zmin,zmax,nombrePoints): #on veut tester plein de configurations d'accroche
    l = []
    nombrePoints-=1
    x,y,z=xmin,ymin,zmin
    pasX = (xmax-xmin)/nombrePoints
    pasY = (ymax-ymin)/nombrePoints
    pasZ = (zmax-zmin)/nombrePoints
    for i in range(nombrePoints+1):
        for j in range(nombrePoints+1):
            for k in range(nombrePoints+1):
                l.append(np.array([x,y,z]))
                z+=pasZ
            z=zmin
            y+=pasY
        y=ymin
        x+=pasX
    return(l)
