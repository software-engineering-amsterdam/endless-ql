import pprint


import sys
# Throw an exception without printing the python stacktrace
def throwError(text):
    print("QL Interpreter error:")
    print(text)
    sys.exit(1)

def printDict(dic):
	pp = pprint.PrettyPrinter(indent=4)
	pp.pprint(dic)
