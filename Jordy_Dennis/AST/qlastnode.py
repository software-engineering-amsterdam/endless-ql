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
        self.forms = []
        self.types = []
        self.varDict = collections.OrderedDict()
        self.qlOrder = collections.OrderedDict()

    def addForm(self, form):
        self.forms.append(form)

    # Check all types of child forms
    def checkTypes(self):
        types = []
        for form in self.forms:
            types.append(form.checkTypes())
        self.types = types
        return types

    # Link all variables from the assignments to the variable nodes.
    # We do not support variable scopes, THIS IS VERY IMPORTANT, that is why all variables can only be declared or assigned once!
    # The varDict contains all used variables along with their types and the node which will hold the variable
    def linkVars(self):
        for form in self.forms:
            form.linkVars(self.varDict)
        return self.varDict

    def getVarDict(self):
        return self.varDict

    # Traversal called after linkVars is called, this one is used to return a dictionary that holds the questions
    # of the program along with their scope (if they are in a conditional or not). The output will be used to
    # generate questions
    def getQLOrder(self):
        for form in self.forms:
            self.qlOrder[form.getQLName()] = form.getQLOrder()
        return self.qlOrder

    def __repr__(self):
        return "FORMS: {}".format(self.forms)
