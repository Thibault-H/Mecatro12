import numpy as np
from scipy.optimize import minimize, root
from matplotlib import pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

def constraintFunction(G, P, r):
    u = unitVectorsTensions(G, P, r)
    U, V, X = tensionPlane(G, u, r)

    def fun(a, b):
        return a * U + b * V + X

    return fun


import numpy as np
import numpy.linalg

g = 1# TODO


## Basis change from the (X,Y,Z) system to the (G,x,y,z) system

def basisChange(X, Y, Z, G):  # return coordinates in (G,x,y,z) system, G beeing written in the (X,Y,Z) system

    L = np.sqrt(G[0] ** 2 + G[1] ** 2 + G[2] ** 2)
    l = np.sqrt(G[0] ** 2 + G[1] ** 2)
    M = np.array([X, Y, Z])

    R = np.array([[-G[0] / L, -G[1] / L, -G[2] / L],
                  [G[1] / l, -G[0] / l, 0],
                  [-G[2] * G[0] / l / L, -G[2] * G[1] / l / L, (G[0] ** 2 + G[1] ** 2) / l / L]])

    return R.dot(M - G)

def basisChangeInv(m, G):  # return coordinates in (G,x,y,z) system, G beeing written in the (X,Y,Z) system

    L = np.sqrt(G[0] ** 2 + G[1] ** 2 + G[2] ** 2)
    l = np.sqrt(G[0] ** 2 + G[1] ** 2)

    R = np.array([[-G[0] / L, -G[1] / L, -G[2] / L],
                  [G[1] / l, -G[0] / l, 0],
                  [-G[2] * G[0] / l / L, -G[2] * G[1] / l / L, (G[0] ** 2 + G[1] ** 2) / l / L]])
    R_inv = np.linalg.inv(R)
    return R_inv.dot(m) + G


### UnitVector Tensions

def unitVectorsTensions(G, P, r, m=8):  # P est un tableau 8X3, contenant les 8 points
    # Pi dans le système de coordonnées (O,X,Y,Z)

    u = np.zeros((m, 3))  # unitVectors

    for i in range(m):
        pi = basisChange(P[i][0], P[i][1], P[i][2], G)
        u[i] = (pi - r[i]) / np.linalg.norm(pi - r[i])

    return u


def basisChangeLinear(X, Y, Z, G):  # return coordinates in (G,x,y,z) system, G beeing written in the (X,Y,Z) system

    L = np.sqrt(G[0] ** 2 + G[1] ** 2 + G[2] ** 2)
    l = np.sqrt(G[0] ** 2 + G[1] ** 2)
    M = np.array([X, Y, Z])

    R = np.array([[-G[0] / L, -G[1] / L, -G[2] / L],
                  [G[1] / l, -G[0] / l, 0],
                  [-G[2] * G[0] / l / L, -G[2] * G[1] / l / L, (G[0] ** 2 + G[1] ** 2) / l / L]])

    return R.dot(M)


### Fonction principale

def tensionPlane(G, u,
                 r, m=8):  # u 8x3 sont les vecteurs normés directeur des tensions, r = 8x3 sont les point d'attache des cables sur le robot

    vecteurGravite = basisChangeLinear(0, 0, - g, G)  # ?????!!!
    print("vect gravité", vecteurGravite)

    A = np.zeros((6, m))
    B = np.array([vecteurGravite[0], vecteurGravite[1], vecteurGravite[2], 0, 0, 0])

    for i in range(m):
        A[0][i] = u[i][0]
        A[1][i] = u[i][1]
        A[2][i] = u[i][2]

        A[3][i] = r[i][1] * u[i][2] - r[i][2] * u[i][1]
        A[4][i] = r[i][2] * u[i][0] - r[i][0] * u[i][2]
        A[5][i] = r[i][0] * u[i][1] - r[i][1] * u[i][0]

    print('u', u)
    print('A', A)
    print('B', B)

    P = A[:6, :6]  # submatrix of A
    print('P', P)
    det = np.linalg.det(P)

    if (abs(det) < 0.000000001):
        print("Matrice non inversible")
    print(det)
    Q = np.linalg.inv(P)

    if m == 8:
        C = A[:, 6]  # column 7 of A
        D = A[:, 7]  # column 8 of A
        U = np.concatenate((Q.dot(C), [-1, 0]), axis=0)  # first vector of the basis of the ker
        V = np.concatenate((Q.dot(D), [0, -1]), axis=0)  # second vector of the basis of the ker
        print('U \n', U)
        print('V \n', V)
        X = np.concatenate((Q.dot(B), [0, 0]), axis=0)  # particular solution of AX = B
        print('X \n', X)

        print('AU \n', A.dot(U))
        print('AV \n', A.dot(V))
        print('AX \n', A.dot(X))
        return (U, V, X)
    elif m == 6:
        X = Q.dot(B)
        print('X6 = \n', X)
        print('AX \n', A.dot(X))
        return X  # particular solution of AX = B





# print(inertiaBalance(t,r,p))
def physicLaw(t):
    # print('t =',t)
    # print('phyLaw = ',min(t))
    return min(t)


'''
def testPossible(t):
    print('t =',t)
    print('eq = ',inertiaBalance(t,r,p))
    return np.concatenate((inertiaBalance(t,r,p),np.array([0,0])),axis=0)
'''


def optimizeF(G, P, r):
    # return minimize(maxTensions,[4.5, 4.5, 4, 4, 0.1,0.1,0.1,0.1],constraints={'type': 'eq', 'fun': cons}).x
    fun = constraintFunction(G, P, r)

    def maxTensions(a):
        t = fun(a[0], a[1])
        # print(t)
        return max(t)

    return minimize(maxTensions, [0, 1])


def maximiseF(G, P, r):
    # return minimize(maxTensions,[4.5, 4.5, 4, 4, 0.1,0.1,0.1,0.1],constraints={'type': 'eq', 'fun': cons}).x
    fun = constraintFunction(G, P, r)

    def maxTensions(a):
        t = fun(a[0], a[1])
        # print(min(t))
        return -min(t)

    return minimize(maxTensions, [1.5, 10])


def optimizeC(G, P, r):
    # return minimize(maxTensions,[4.5, 4.5, 4, 4, 0.1,0.1,0.1,0.1],constraints={'type': 'eq', 'fun': cons}).x
    fun = constraintFunction(G, P, r)

    def cons(a):
        t = fun(a[0], a[1])
        # print('min ',min(t))
        return min(t)

    def maxTensions(a):
        t = fun(a[0], a[1])
        # print(max(t))
        return max(t)

    return minimize(maxTensions, [-1.5, -3.9], constraints={'type': 'ineq', 'fun': cons})


#def possible():
    # return minimize(testPossible, [1, 3, 0, 2, 1, 1, 1, 4.],constraints={'type': 'ineq', 'fun': physicLaw}).x
#    return root(rtestPossible, [4.5, 4.5, 4, 4, 0.1, 0.1, 0.1, 0.1]).x



##test
P = np.array([[0, 1, -1], [-2, 1, -1], [0, -1, -1.], [-2, -1, -1],
              [0, 1, 1], [-2, 1., 1], [0, -1, 1], [-2, -1, 1]], dtype=np.float32)
r = 0.3 * np.array([[1., 1, -1], [-1, 1, -1], [1, -1, -1], [-1, -1, -1],
                    [1, 1, 1], [-1, 1, 1], [1, -1, 1], [-1, -1, 1]], dtype=np.float32)
# r = 0.3*np.array([[0, 1, -1], [-1, 0, -1], [1, 0, -1], [0, -1, -1],
#                   [1, 0, 1], [0, 1, 1], [0, -1, 1], [-1, 0, 1]])
G = np.array([-1, 0.1, 0.2], dtype=np.float32)
P6 = P[2:]
r6 = r[2:]
def print_3d(u, ax):
    n = len(u)
    x = np.zeros(n)
    y = np.zeros(n)
    z = np.zeros(n)
    for i in range(n):
        x[i] = u[i][0]
        y[i] = u[i][1]
        z[i] = u[i][2]
        ax.text(x[i], y[i], z[i], str(i))
    ax.scatter(x, y, z)


def v_basis_change_inv(array):
    res = np.zeros((len(array),3))
    for i in range(len(array)):
        res[i] = basisChangeInv(array[i], G)
    return res

def v_basis_change(array):
    res = np.zeros((len(array),3))
    for i in range(len(array)):
        res[i] = basisChange(array[i][0], array[i][1], array[i][2], G)
    return res


fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')
print_3d(P, ax)
print_3d(v_basis_change_inv(r), ax)
print_3d([np.array([0,0,-g])], ax)
print_3d([G], ax)
plt.show()



#res = maximiseF(G, P, r).x

def line_abc(G, P, r, am=100, m=8):
    u = unitVectorsTensions(G, P, r, m)
    U, V, X = tensionPlane(G, u, r, m)
    min = 10 ** (-10)
    max = 10 ** (10)

    def v_zero(i):
        if abs(U[i]) < min:
            print('coord ' + str(i) + ' : ' + str(X[i] > 0))
        elif U[i] > 0:
            c = -X[i] / U[i]
            plt.plot([c, c], [-20, 20], 'y') # (a,b) black line
        else:
            c = -X[i] / U[i]
            plt.plot([c, c], [-20, 20], 'k') # (a,b) yellow line

    for i in range(0, m):
        if abs(V[i]) < min:
            v_zero(i)
        else:
            slope = - U[i] / V[i]
            ori_coord = - X[i] / V[i]

            def f(a):
                return slope * a + ori_coord

            if V[i] > 0:
                plt.plot([-am, am], [f(-am), f(am)], 'b') # (a,b) has to be above the blue line
            else:
                plt.plot([-am, am], [f(-am), f(am)], 'r') # (a,b) has to be under the red line
    plt.show()

# line_abc(G, P, r) TODO

fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')
print_3d(v_basis_change(P6), ax)
print_3d(r6, ax)
print_3d([basisChangeLinear(0, 0, - g, G)], ax)
print_3d([basisChangeLinear(0, 0, 0, G)], ax)
plt.show()
tensionPlane(G, unitVectorsTensions(G, P, r, m=6), r, m=6)



def tensionPlane2D(T, G):  # u 4x2 sont les vecteurs normés directeur des tensions, r = 8x3 sont les point d'attache des cables sur le robot

    u = np.zeros((4,2))
    for i in range(4):
        u[i] = T[i] / np.linalg.norm(T[i])

    A = np.zeros((2, 4))
    for i in range(4):
        A[0][i] = u[i][0]
        A[1][i] = u[i][1]

    B = G

    print('u', u)
    print('A', A)

    P = A[:2, :2]  # submatrix of A
    C = A[:, 2]  # column 3 of A
    D = A[:, 3]  # column 4 of A
    print('P', P)
    det = np.linalg.det(P)

    if (abs(det) < 0.000000001):
        print("Matrice non inversible")
    print(det)

    Q = np.linalg.inv(P)

    U = np.concatenate((Q.dot(C), [-1, 0]), axis=0)  # first vector of the basis of the ker
    V = np.concatenate((Q.dot(D), [0, -1]), axis=0)  # second vector of the basis of the ker
    X = np.concatenate((Q.dot(B), [0, 0]), axis=0)  # particular solution of AX = B

    print('U \n', U)
    print('V \n', V)
    print('X \n', X)
    print('AU \n', A.dot(U))
    print('AV \n', A.dot(V))
    print('AX \n', A.dot(X))

    return U, V, X

def line_2d(T, G, am=100, m=8):
    U, V, X = tensionPlane2D(T, G)
    min = 10 ** (-10)
    max = 10 ** (10)

    print(-0.1*U - 6 * V + X)
    def v_zero(i):
        if abs(U[i]) < min:
            print('coord ' + str(i) + ' : ' + str(X[i] > 0))
        elif U[i] > 0:
            c = -X[i] / U[i]
            plt.plot([c, c], [-20, 20], 'y') # (a,b) black line
        else:
            c = -X[i] / U[i]
            plt.plot([c, c], [-20, 20], 'k') # (a,b) yellow line

    for i in range(0, m):
        if abs(V[i]) < min:
            v_zero(i)
        else:
            slope = - U[i] / V[i]
            ori_coord = - X[i] / V[i]

            def f(a):
                return slope * a + ori_coord

            if V[i] > 0:
                plt.plot([-am, am], [f(-am), f(am)], 'b') # (a,b) has to be above the blue line
            else:
                plt.plot([-am, am], [f(-am), f(am)], 'r') # (a,b) has to be under the red line
    plt.show()

'''
T2d = np.array([[2, -1],
                [-1, -1],
                [-1, 1],
                [-2, -1]])
G = np.array([0,-g])
line_2d(T2d, G, m=4)
'''
