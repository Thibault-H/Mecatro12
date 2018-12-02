import numpy as np
import numpy.linalg
from copy import deepcopy
from math import sqrt


if(False):
    print("tg")
else:
    print("fbtg")
###

tableauInit = []
for i in range(8):
    tableauInit += [[-1,-1,-1,-1,-1,-1,-1,-1]]

# tableauInit est le tableau qui va stocker des couples de float représentant un poin du plan (alpha,beta)



def intersectionsEquations(u,v,x):
    tableauIntersections = deepcopy(tableauInit)
    for i in range(8):
        for j in range(i+1,8):
            det = u[0]*v[1]-u[1]*v[0]
            if abs(det)>1e-6: #à modifier éventuellement
                alph = (u[1]*x[1]-v[1]*u[0])/det
                beta = (v[0]*x[0]-u[0]*x[1])/det
                tableauIntersections[i][j] = [alph,beta] #ça marche ça ?
    return(tableauIntersections)

def pointsExtremauxConfigurations(u,v,x,tableauIntersections):
    tableauOmega = deepcopy(tableauIntersections) #Omega est l'ensemble des points d'intersections des droites qui sont dans tous les demis plans
    for i in range(8):
        for j in range(i+1,8):
            if tableauIntersections[i][j]!=-1:
                possible = True
                alph = tableauIntersections[i][j][0]
                beta = tableauIntersections[i][j][0]
                for k in range(8):
                    if (k!=i)and(k!=j):
                        t = t&(alph*u[k]+beta*v[k]+x[k]>0)
                if not(t):
                    tableauOmega[i][j] = -1
    return(tableauOmega)


def determinerDroitesLimites(tableauOmega):
    nombrePoints = [0 for i in range(8)] #représente le nombre de points de Omega sur chaque droite, compris entre 1 et 2
    droitesExtremales = []
    droitesInter = []
    for i in range(8):
        for j in range(i+1,8):
            if tableauOmega[i][j]!=1:
                nombrePoints[i]+=1
                nombrePoints[j]+=1
    for i in range(8):
        if nombrePoints[i]==1:
            droitesExtremales+= [i]
        if nombrePoints[i]==2:
            droitesInter+= [i]
    return(droitesExtremales,droitesInter)

def estBorne(droitesExtremales):
    return(droitesExtremales==[])

def determinerVecteursExtreme(u,v,x,tableauOmega,droitesExtremales):
    vecteurs = []
    i1 = droitesExtremales[0]
    i2 = droitesExtremales[1]
    j1 = -1
    j2 = -1
    
    for j in range(i1+1,8):
        if tableauOmega[i1][j]!=-1:
            j1=j
    for j in range(i2+1,8):
        if tableauOmega[i2][j]!=-1:
            j2=j
    
    alph1 = tableauOmega[i1][j1][0] + v[i1]
    beta1 = tableauOmega[i1][j1][1] - u[i1]
    
    if (alph1*u[j1]+beta1*v[j1]+x[j1]>0):
        vecteurs+= [[v[i1],-u[i1]]
    else:
        vecteurs+= [[-v[i1],u[i1]]
    
    alph2 = tableauOmega[i2][j2][0] + v[i2]
    beta2 = tableauOmega[i2][j2][1] - u[i2]
    
    if (alph1*u[j2]+beta1*v[j2]+x[j2]>0):
        vecteurs+= [[v[i2],-u[i2]]
    else:
        vecteurs+= [[-v[i2],u[i2]]
    
    for k in range(2):
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
                points+=[tableauOmega[i][j]]
    return(points,vecteurs)



