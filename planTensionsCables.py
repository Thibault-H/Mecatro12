import numpy as np
import numpy.linalg


g = 9.81

## Basis change from the (X,Y,Z) system to the (G,x,y,z) system

def basisChange(X,Y,Z,G): # return coordinates in (G,x,y,z) system, G beeing written in the (X,Y,Z) system

    L = np.sqrt(G[0]**2 + G[1]**2 + G[2]**2)
    l = np.sqrt(G[0] ** 2 + G[1] ** 2)
    M = np.array([X,Y,Z])

    R = np.array([[-G[0] / L, -G[1] / L, -G[2] / L],
                  [G[1] / l, -G[0] / l, 0],
                  [-G[2] * G[0] / l / L, -G[2] * G[1] / l / L, (G[0] ** 2 + G[1] ** 2) / l / L]])

    return R.dot(M - G)

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
    
    vecteurGravite = basisChange(0,0,-g,G)) #?????!!!
    
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
    
    if (abs(det)<0.000000001):
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
