from QLSast import *
import sys

class QLSTypeChecker(object):
	
    def __init__(self, ql_ast, qls_ast):
    	self.ql_ast = ql_ast
    	self.qls_ast = qls_ast


    def startQLSTypeCheck(self):
        pass
        

    # Checks if every question in QL is referenced in QLS.
    def checkReferencesToQL(self):
    	pass


    # Checks whether the types of the questions are compatible with the assigned widgets.
    def checkWidgetQuestionCompatibility(self):
    	pass


    # Check if every question is only placed once.
    def checkQuestionUniqueness(self):
    	pass
