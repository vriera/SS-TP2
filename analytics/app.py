
from cProfile import label
from orden import Orden
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


def getVaVsDensity():
    individuals = [ 40, 100 , 400 , 4000 ]

    densities = [x/10 for x in range(5 , 80 , 5) ]
    individuals = [int( x/10 * (20**2)) for x in range(5,80,5)]
    print(densities)
    print(individuals)
    promedios = []
    desvios = []
 
    dataPorVariacion = dict()
    for i in individuals:
        for j in densities:
            runs = ResultReader.parse("../results" , f"^TestDensity_{i}" )
            results = getPromedioLast(runs)
            promedios.append(results[0])
            desvios.append(results[1])
            dataPorVariacion[str(j)] = (results[0] , results[1] , i)
          
           
    saveIntoJson('resultsVaDensity.json' , dataPorVariacion)
    return dataPorVariacion


def main():
    data =  getVaVsDensity()
    Orden.plotVaVsDensity(data)

    #with open(f'results.json') as json_file:
     #           dataPorVariacion = json.load(json_file)
    
   # Orden.plotOrden(dataPorVariacion)
   # print(dataPorVariacion)
    
   
if __name__ == "__main__":
    main()