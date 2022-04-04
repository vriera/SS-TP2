
import statistics
from resultReader import ResultReader
import matplotlib.pyplot as plt
import numpy as np
import json

def saveIntoJson(filename , data):
    
    with open(filename, 'w') as fp:
        json.dump(data, fp)

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

def getPromedioLast(runs):
    values = []
    promedioLocal = 0
    for j in range(0 , len(runs)):
        promedioLocal += runs[j]['orders'][-1][1]
        values.append(runs[j]['orders'][-1][1])

    promedioLocal = promedioLocal / len(runs)
    desvio = statistics.pstdev(values)
    
    return (promedioLocal , desvio)

def visitAllFiles():
    individuals = [ 4000 ]
    steps = [ x/10 for x in range(0 , 50 , 2 ) ]
    promedios = []
    desvios = []

    dataPorVariacion = dict()

    with open(f'results2.json') as json_file:
                dataPorVariacion = json.load(json_file)
        
    
    for i in individuals:
        for j in steps:
            runs = ResultReader.parse("../results" , f"^TestVA_{i}_{j}" )
            results = getPromedioLast(runs)
            promedios.append(results[0])
            desvios.append(results[1])
            if j not in dataPorVariacion:
                dataPorVariacion[j] = []
            dataPorVariacion[j].append((results[0] , results[1] , i))
    saveIntoJson('results.json' , dataPorVariacion)
    return dataPorVariacion

def main():
    visitAllFiles()
    return
    with open(f'results2.json') as json_file:
                dataPorVariacion = json.load(json_file)
    
    print([ x[0][0] for x in dataPorVariacion.values()])
    plt.plot( [ float(x) for x in dataPorVariacion.keys() ], [ x[0][0] for x in dataPorVariacion.values()] , 'bt' )            
    plt.show()
    #for run in runs:
      #  print(run)
   
if __name__ == "__main__":
    main()