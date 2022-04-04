 
import json
from os import listdir
from os.path import isfile, join
import matplotlib.pyplot as plt
import numpy as np
import re


class ResultReader:
    def __init__(self):
        print("Reader ready")


    @staticmethod
    def parse(path , regex = '.*'):
        folders = [ folder for folder in listdir(path) if re.match(regex  , folder )]
        #folders = listdir(path)
        print(folders)
        runs = []
        
        for foldername in folders:
            run = None
            with open(f'{path}/{foldername}/static.json') as json_file:
                data = json.load(json_file)
                data['density'] = data['total_agents'] / (data['space_width']**2) 
                run = data
                #print(run)
                
            with open(f'{path}/{foldername}/snapshots.json') as json_file:

                data = json.load(json_file)

            # print("len: " , len(data['snapshots']))
                orderArray = []
                t = 0
                for snapshot in data['snapshots']: 
                    lastSnapshot = snapshot['snapshot']
                    totalVel = []

                    for axis in lastSnapshot[0]['vel']:
                        totalVel.append(0)

                    for bird in lastSnapshot:
                        for (idx , axis ) in enumerate(bird['vel']):
                            totalVel[idx] += bird['vel'][axis]

                    order = 0
                    for direction in totalVel:
                        order+= direction**2
                    order = np.sqrt(order) / (len(lastSnapshot) * run['velocity'])
                    orderArray.append(( t , order))
                    t+=1

                run['orders'] = orderArray
                runs.append(run)       
        return runs
    
