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
        self.varDict = {}

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
        varDict = {}
        for form in self.forms:
            form.linkVars(varDict)
        self.varDict = varDict
        return self.varDict

    def __repr__(self):
        return "FORMS: {}".format(self.forms)
