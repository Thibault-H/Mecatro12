import numpy as np
from scipy.optimize import minimize, root

n = 1.0
g = 9.81

""" Programme d'optimisation des position des accoches des câbles sur la structure fixe et sur la structure mobile """


## Basis change from the (X,Y,Z) system to the (G,x,y,z) system

def basisChange(X,Y,Z,G): # return coordinates in (G,x,y,z) system, G beeing written in the (X,Y,Z) system
#détails des calculs sur feuille (photo mise en ligne sur 3dexperience)
    L = np.sqrt(G[0]**2 + G[1]**2 + G[2]**2)
    l = np.sqrt(G[0] ** 2 + G[1] ** 2)
    M = np.array([X,Y,Z])

    R = np.array([[-G[0] / L, -G[1] / L, -G[2] / L],
                  [G[1] / l, -G[0] / l, 0],
                  [-G[2] * G[0] / l / L, -G[2] * G[1] / l / L, (G[0] ** 2 + G[1] ** 2) / l / L]])

    return R.dot(M - G)
    
    
## Inertia Balance

def inertiaBalance(t,r,P,G): #t is a 8-dim vector containing the 8 Tensions (scalar) while r is a 8*3 array containing the 8 vectors GMi in the coordinate (x,y,z), P is a 8*3 array containing the 8 vectors GPi in the coordonates (X,Y,Z). G (centre d'inertie) is written in the coordonates (X,Y,Z).
    massCenter = basisChange(G[0],G[1],G[2]-g,G) #gravity force in (G,x,y,z) / on calcule à un facteur de masse près 
#remarque : on peut tout calculer à un facteur m près, constant et positif, qui ne changera pas les positions qui minimisent les tensions dans les câbles 
    for i in range(len(P)):
        P[i] = basisChange(P[i][0],P[i][1],P[i][2],G) # after that, we are working in the (G,x,y,z) system

#for i in range(len(P)): #normalisation des vecteurs directeurs 
#    P[i] /= np.linalg.norm((P[i])) #print(p[i]) WARNING causes troubles when p is int array
#    r[i] /= np.linalg.norm((r[i]))

    angularMomentum = np.array([0,0,0])
        
    for i in range(len(t)) :
        massCenter += t[i]*(np.linalg.norm(P[i]-r[i]))
        
#sum of the Ti added to the mass center : équation de la mécanique qui doit être nulle à l'équilibre

    for i in range(len(t)):
        angularMomentum += np.cross(r[i],t[i]*P[i])

    #return np.maximum(massCenter,angularMomentum)
    #return max(massCenter)
    return np.concatenate((massCenter,angularMomentum), axis=0)


#def physics(t):


## optimisation 

def maxTensions(t):
    print(sum((t**2)))
    return (sum(t**2))

