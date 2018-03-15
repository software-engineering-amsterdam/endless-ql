from QLSast import *
import sys

class QLSTypeChecker(object):
	
    def __init__(self, ast):
    	self.ast = ast