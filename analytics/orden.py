import matplotlib.pyplot as plt

class Orden:
    @staticmethod
    def plotOrden(data):
        n40 = [ x[0][0] for x in data.values()]
        n100 =[ x[1][0] for x in data.values()]
        n400 =[ x[2][0] for x in data.values()]
    # print(n100)
        #print(n400)
    # n4000 =[ x[0][3] for x in dataPorVariacion.values()]
        steps = [ float(x) for x in data.keys() ]
        plt.plot( steps , n40 , 'bo' , label="N=40")
        plt.plot(steps , n100 , 'rx', label="N=100")
        plt.plot( steps , n400 , 'gs', label="N=400" )
        plt.xlabel("Amplitud del ruido")
        plt.ylabel("Parámetro de orden")
        plt.legend(loc='upper right')
        plt.grid()
        plt.show()
    #for run in runs:
      #  print(run)