import matplotlib.pyplot as plt
#from mpl_toolkits.basemap import Basemap
import numpy as np
from pysolar.solar import * ## pysolar c'est trop bien c'est le feu c'est le sang de la veine !!
import datetime
from pylab import *
from mpl_toolkits.mplot3d import Axes3D
from math import sqrt
'''This python Script allows the user to choose its input parameters and get the sun trajectory for the experience'''

## draft for the use of the library

"""
date = datetime.datetime(2007, 2, 18, 15, 13, 1, 130320, tzinfo=datetime.timezone.utc)
print(get_altitude(42.206, -71.382, date))
print(get_azimuth(42.206, -71.382, date))


#winterAzi = datafomPySolarAzi
#winterAlt = datafromPySolarAlt

# create instance of basemap, note we want a south polar projection to 90 = E
#myMap = Basemap(projection='spstere',boundinglat=0,lon_0=180,resolution='l',round=True,suppress_ticks=True)
# set the grid up
gridX,gridY = 10.0,15.0
parallelGrid = np.arange(-90.0,90.0,gridX)
meridianGrid = np.arange(-180.0,180.0,gridY)

# draw parallel and meridian grid, not labels are off. We have to manually create these.
myMap.drawparallels(parallelGrid,labels=[False,False,False,False])
myMap.drawmeridians(meridianGrid,labels=[False,False,False,False],labelstyle='+/-',fmt='%i')

# we have to send our values through basemap to convert coordinates, note -winterAlt
winterX,winterY = myMap(winterAzi,-winterAlt)

# plot azimuth labels, with a North label.
ax = plt.gca()
ax.text(0.5,1.025,'N',transform=ax.transAxes,horizontalalignment='center',verticalalignment='bottom',size=25)
for para in np.arange(gridY,360,gridY):
    x= (1.1*0.5*np.sin(np.deg2rad(para)))+0.5
    y= (1.1*0.5*np.cos(np.deg2rad(para)))+0.5
    ax.text(x,y,u'%i\N{DEGREE SIGN}'%para,transform=ax.transAxes,horizontalalignment='center',verticalalignment='center')


# plot the winter values
myMap.plot(winterX,winterY ,'bo')
"""
## Trajectoire



"""pour repérer quelle plage d'heure est pertinente car éclairée dans la journée souhaitée. Renvoie un tableau de "date" avec des pas de 10 minutes entre chaque position"""
def datesPlage (annee, mois, jour, latitude, longitude): 
    Dates = []
    i=0
    for j in range(143):
        i += 600
        heure = i//3600
        reste = i-heure*3600
        minutes = reste//60
        reste -=60*minutes
        date = datetime.datetime(annee, mois, jour, heure, minutes, reste, 0, tzinfo=datetime.timezone.utc)
        alt = get_altitude(latitude,longitude,date)
        if alt>0 and alt<180 :
            Dates.append(date)
    return Dates

"""orientation es l'angle que forme la face de la maisonnette avec le nord (sens trigo) ; renvoi les angles théta en phi des coordonnées sphériques du soleil UNIQUEMENT si ils sont dans le quart de cercle qui nous interesse"""
def anglesRefMaisonnette (date, latitude, longitude, orientation):
    az = get_azimuth(latitude,longitude,date)
    phi = az-orientation
    theta = get_altitude(latitude,longitude,date)
    if phi<0:
        phi+=360
    if phi<=90 or phi>=270:
        if phi>180:
            phi -=360
        return phi,theta


"""renvoie un tableau : essemble de coordonnées sphériques (trajectoire discrétisée toutes les 10 minutes)"""
def trajectoireSoleil (annee, mois, jour, latitude, longitude, orientation):
    Coordonnees = []
    dates = []
    dates = datesPlage(annee, mois,jour,latitude,longitude)
    for date in dates:
        Coordonnees.append(anglesRefMaisonnette(date,latitude,longitude,orientation))
    return Coordonnees
    
"""def affichageTrajectoire (Coordonnees):
    X = []
    Y = []
    Z = []
    Xs = []
    Ys = []
    Zs = []
    for i in range (100) :
        xs = i/100
        ys = sqrt(1-xs**2)
        nj = int(ys*50)
        for j in range (nj):
            ys = j/50
            zs = sqrt(1-ys**2)
            Xs.append(xs)
            Ys.append(ys)
            Zs.append(zs)
    fig = figure()
    ax = Axes3D(fig)
    bx = Axes3D(fig)
    for co in Coordonnees : 
        if co != None :
            x = math.cos(co[0]/180*math.pi) * math.cos(co[1]/180*math.pi)
            y = math.sin(co[0]/180*math.pi) * math.cos(co[1]/180*math.pi)
            d = sqrt(x**2+y**2)
            z = sqrt(1-d**2)
            X.append(x)
            Y.append(y)
            Z.append(z)
    ax.plot(X,Y,Z)
    bx.plot_surface(Xs, Ys, Zs)"""
    
def affichageTrajectoire (Coordonnees):
    X = []
    Y = []
    Z = []
    fig = figure()
    ax = Axes3D(fig)
    for co in Coordonnees : 
        if co != None :
            y = -math.cos(co[0]/180*math.pi) * math.cos(co[1]/180*math.pi)
            x = math.sin(co[0]/180*math.pi) * math.cos(co[1]/180*math.pi)
            d = sqrt(x**2+y**2)
            z = sqrt(1-d**2)
            X.append(x)
            Y.append(y)
            Z.append(z)
    axes = plt.gca() 
    axes.set_xlabel('axe des x')
    axes.set_ylabel('axe des y')
    ax.plot(X,Y,Z)

