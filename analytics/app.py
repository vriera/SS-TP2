
 
import json
from os import listdir
from os.path import isfile, join
from resultReader import ResultReader
import matplotlib.pyplot as plt
import numpy as np


def main():
    runs = ResultReader.parse("../results" )
    for run in runs:
        print(run)
if __name__ == "__main__":
    main()