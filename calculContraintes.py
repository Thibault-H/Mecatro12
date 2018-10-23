import numpy as np


n = 1.0

def basisChange(X,Y,Z,G):
    L = np.sqrt(G[0]**2 + G[1]**2 + G[2]**2)
    l = np.sqrt(G[0] ** 2 + G[1] ** 2)
    '''R = np.array([[-G[0]/L,-G[1]/L,-G[2]/L],
                  [-G[1]/l,G[0]/l,0],
                  [-(0-G[2])*G[0]/l/L,-(0-G[2])*G[1]/l/L,-(G[0]**2+G[1]**2)/l/L]])'''
    R = np.array([[-G[0] / L, -G[1] / L, -G[2] / L],
                  [G[1] / l, -G[0] / l, 0],
                  [ -G[2] * G[0] / l / L, -G[2] * G[1] / l / L, (G[0] ** 2 + G[1] ** 2) / l / L]])
    print(R)
    #R = np.linalg.inv(R)
    M = np.array([[X,Y,Z]])
    G = np.array([[G[0],G[1],G[2]]])

    return R.dot(M.T - G.T)

print(basisChange(0,0,0,[-1,1,1]))