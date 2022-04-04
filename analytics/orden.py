import matplotlib.pyplot as plt

class Orden:
    @staticmethod
    def plotOrden(data):
        n40 = [ x[0][0] for x in data.values()]
        n40error = [ x[0][1] for x in data.values()]
        n100 =[ x[1][0] for x in data.values()]
        n100error = [ x[1][1] for x in data.values()]
        n400 =[ x[2][0] for x in data.values()]
        n400error = [ x[2][1] for x in data.values()]
    # print(n100)
        #print(n400)
    # n4000 =[ x[0][3] for x in dataPorVariacion.values()]
        steps = [ float(x) for x in data.keys() ]
        markers, caps , bars = plt.errorbar( steps , n40 ,yerr = n40error,  elinewidth= 1.4, fmt = 'bo' , ecolor='cyan', label="N=40")
        [bar.set_alpha(0.8) for bar in bars]
        [cap.set_alpha(0.8) for cap in caps]
        markers, caps , bars =  plt.errorbar(steps , n100 , yerr =n100error ,  elinewidth= 1.6,fmt = 'rx' , ecolor='magenta', label="N=100")
        [bar.set_alpha(0.5) for bar in bars]
        [cap.set_alpha(0.5) for cap in caps]
        markers, caps , bars = plt.errorbar( steps , n400 , yerr = n400error , elinewidth= 1.8, fmt = 'gs',ecolor='limegreen', label="N=400" )
        [bar.set_alpha(0.5) for bar in bars]
        [cap.set_alpha(0.5) for cap in caps]
        plt.xlabel("Amplitud del ruido")
        plt.ylabel("Parámetro de orden")
        plt.legend(loc='upper right')
        plt.grid()
        plt.show()
    #for run in runs:
      #  print(run)

    @staticmethod
    def plotVaVsDensity(data):
        values = [ x[0] for x in data.values()]
        error = [ x[1] for x in data.values()]
        steps = [ float(x) for x in data.keys() ]
        markers, caps , bars = plt.errorbar( steps , values ,yerr = error,  elinewidth= 1.4, fmt = 'bo' , ecolor='cyan')
        [bar.set_alpha(0.8) for bar in bars]
        [cap.set_alpha(0.8) for cap in caps]
        plt.xlabel("Amplitud del ruido")
        plt.ylabel("densidad")
        plt.legend(loc='upper right')
        plt.grid()
        plt.show()
    #for run in runs:
      #  print(run)