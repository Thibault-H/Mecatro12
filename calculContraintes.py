import numpy as np
from scipy.optimize import minimize, fsolve

n = 1.0
g = 9.81

def basisChange(X,Y,Z,G): # return coordinates in (G,x,y,z) system

    L = np.sqrt(G[0]**2 + G[1]**2 + G[2]**2)
    l = np.sqrt(G[0] ** 2 + G[1] ** 2)
    M = np.array([X,Y,Z])

    R = np.array([[-G[0] / L, -G[1] / L, -G[2] / L],
                  [G[1] / l, -G[0] / l, 0],
                  [-G[2] * G[0] / l / L, -G[2] * G[1] / l / L, (G[0] ** 2 + G[1] ** 2) / l / L]])

    return R.dot(M - G)



def inertiaBalance(t,r,p): #t is a 8-dim vector containing the 8 Tensions while r is a 8*3 array containing the 8 vectors GMi in the coordinate (x,y,z), p r is a 8*3 array containing the 8

    massCenter = np.array([0,0,-g])
    for i in range(len(p)):
        p[i] /= np.linalg.norm(p[i]) #print(p[i]) WARNING causes troubles when p is int array
    for i in range(len(t)):
        massCenter = massCenter + t[i]*p[i] #sum of the Ti

    angularMomentum = np.array([0,0,0])
    for i in range(len(t)):
        angularMomentum = angularMomentum + np.cross(r[i],t[i]*p[i])

    #return np.maximum(massCenter,angularMomentum)
    #return max(massCenter)
    return np.concatenate((massCenter,angularMomentum,[0,0]), axis=0)

#def physics(t):


def maxTensions(t):
    return max(t[0],t[1],t[2],t[3],t[4],t[5],t[6],t[7])


r = np.array([[1,1,1],[1,1,-1],[1,-1,1],[1,-1,-1],[-1,1,1],[-1,1,-1],[-1,-1,1],[-1,-1,-1]])
t = np.array([0.2,0.1,0.5,2,1,0.6,0.8,2])
#p = np.array([[1.1,1,1],[1,1,-1],[1,-1,1],[1,-1,-1],[-1,1,1],[-1,1,-1],[-1,-1,1],[-1,-1,-1]])
p = np.array([[1.,1,1],[1,1,-1],[1,-1,1],[1,-1,-1],[-1,1,1],[-1,1,-1],[-1,-1,1],[-1,-1,-1]])


#print(inertiaBalance(t,r,p))

def cons(t):
    return inertiaBalance(t,r,p)
def testCons(t):
    return t

def optimizeF():
    return minimize(maxTensions,[0.5,6,1,2,1,1,1,0],constraints={'type': 'eq', 'fun': cons}).x

def possible():
    return fsolve(cons,[1,1,1,1,1,1,1,1])

print(possible())

print(cons([9.49570921,-7.421,1,1,1,1,1,1]))