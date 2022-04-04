
import statistics
from resultReader import ResultReader
import matplotlib.pyplot as plt
import numpy as np

def getPromedioOrden( runs ):
    values = []
    promedioOrdenes = []
    for i in range(0 , runs[0]['total_steps'] + 1 ):
        promedioLocal = 0
        for j in range(0 , len(runs)):
            promedioLocal += runs[j]['orders'][i][1]
            values.append(runs[j]['orders'][i][1])

        promedioLocal = promedioLocal / len(runs)
        desvio = statistics.pstdev(values)
        promedioOrdenes.append( ( promedioLocal , desvio ) )
    return promedioOrdenes

def main():
    individuals = [ 40 , 100 , 400 , 4000 , 10000
    steps = [ x for x in range( 0 , 5 , 0.2 ) ]
    for i in individuals:
        for j in steps:S
            runs = ResultReader.parse("../results" , f"^TestVA_{i}_{j}" )
    print(runs)
   
    #for run in runs:
      #  print(run)
   
    print(getPromedioOrden(runs))
if __name__ == "__main__":
    main()