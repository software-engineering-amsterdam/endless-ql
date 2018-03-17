"""
	Usefull methods and imports here
"""

import pprint
import collections


import sys
# Throw an exception without printing the python stacktrace
def throwError(text):
    print("QLS Interpreter error:")
    print(text)
    sys.exit(1)

def printDict(dic):
	pp = pprint.PrettyPrinter(indent=4)
	pp.pprint(dic)

# check the difference between the first and the second list.
def listDif(list1, list2):
	for item in list1:
		list2.remove(item)
	return list2
