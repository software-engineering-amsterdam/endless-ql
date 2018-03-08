import pprint
from .ast_methods import *


""" 
AST tree used for the QL visitor
Important notes: 

-   We do not support variable scopes, THIS IS VERY IMPORTANT, that is why all variables can only be declared or assigned once! 

-   Typechecking is done by traversing the tree, and then passing and comparing types bottum-up.
    You can compare integers with floats, but otherwise types should always be the same.
"""
class QLAst:
    def __init__(self):
        self.form = None
        self.types = []
        self.varDict = collections.OrderedDict()

    # Check all types of child forms
    def checkTypes(self):
        types = []
        types.append(self.form.checkTypes())
        self.types = types
        return types

    # Link all variables from the assignments to the variable nodes.
    # We do not support variable scopes, THIS IS VERY IMPORTANT, that is why all variables can only be declared or assigned once!
    # The varDict contains all used variables along with their types and the node which will hold the variable
    def linkVars(self):
        self.form.linkVars(self.varDict)
        return self.varDict

    def getVarDict(self):
        return self.varDict

    def getName(self):
        return self.form.name

    def __repr__(self):
        return "FORMS: {}".format(self.forms)
