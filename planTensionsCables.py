import numpy as np

g = 9.81
### Fonction principale

def planTension(X,Y,Z,u1,u2,u3,u4,u5,u6,u7,u8,r1,r2,r3,r4,r5,r6,r7,r8): #ui sont les vecteurs normés directeur des tensions, ri sont les point d'attache des cables sur le robot
    
    vecteurGravite = basisChange(0,0,-g,np.array([X,Y,Z]))
    
    A = np.zeros((6,8))
    B = np.array([vecteurGravite[0],vecteurGravite[1],vecteurGravite[2],0,0,0])
    
    A[0][0] = u1[0]
    A[0][1] = u2[0]
    A[0][2] = u3[0]
    A[0][3] = u4[0]
    A[0][4] = u5[0]
    A[0][5] = u6[0]
    A[0][6] = u7[0]
    A[0][7] = u8[0]
    
    A[1][0] = u1[1]
    A[1][1] = u2[1]
    A[1][2] = u3[1]
    A[1][3] = u4[1]
    A[1][4] = u5[1]
    A[1][5] = u6[1]
    A[1][6] = u7[1]
    A[1][7] = u8[1]
    
    A[2][0] = u1[2]
    A[2][1] = u2[2]
    A[2][2] = u3[2]
    A[2][3] = u4[2]
    A[2][4] = u5[2]
    A[2][5] = u6[2]
    A[2][6] = u7[2]
    A[2][7] = u8[2]
    
    A[3][0] = r1[1]*u1[2] - r1[2]*u1[1]
    A[3][1] = r2[1]*u2[2] - r2[2]*u2[1]
    A[3][2] = r3[1]*u3[2] - r3[2]*u3[1]
    A[3][3] = r4[1]*u4[2] - r4[2]*u4[1]
    A[3][4] = r5[1]*u5[2] - r5[2]*u5[1]
    A[3][5] = r6[1]*u6[2] - r6[2]*u6[1]
    A[3][6] = r7[1]*u7[2] - r7[2]*u7[1]
    A[3][7] = r8[1]*u8[2] - r8[2]*u8[1]
    
    A[4][0] = r1[2]*u1[0] - r1[0]*u1[2]
    A[4][1] = r2[2]*u2[0] - r2[0]*u2[2]
    A[4][2] = r3[2]*u3[0] - r3[0]*u3[2]
    A[4][3] = r4[2]*u4[0] - r4[0]*u4[2]
    A[4][4] = r5[2]*u5[0] - r5[0]*u5[2]
    A[4][5] = r6[2]*u6[0] - r6[0]*u6[2]
    A[4][6] = r7[2]*u7[0] - r7[0]*u7[2]
    A[4][7] = r8[2]*u8[0] - r8[0]*u8[2]
    
    A[5][0] = r1[0]*u1[1] - r1[1]*u1[0]
    A[5][1] = r2[0]*u2[1] - r2[1]*u2[0]
    A[5][2] = r3[0]*u3[1] - r3[1]*u3[0]
    A[5][3] = r4[0]*u4[1] - r4[1]*u4[0]
    A[5][4] = r5[0]*u5[1] - r5[1]*u5[0]
    A[5][5] = r6[0]*u6[1] - r6[1]*u6[0]
    A[5][6] = r7[0]*u7[1] - r7[1]*u7[0]
    A[5][7] = r8[0]*u8[1] - r8[1]*u8[0]
    
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
