"""
This Class wraps the form into a node. It has to have a name
and can contain statements in its block

Statements are either conditionals, questions or assignments
"""

from .ast_methods import *
class FormNode:
    def __init__(self, name, line):
        self.name = name
        self.line = line
        self.block = []
        self.qlOrder = collections.OrderedDict()

    def addStatements(self, statements):
        for statement in statements:
            self.block.append(statement)

    # Check all types of child forms
    def checkTypes(self):
        types = []
        for statement in self.block:
            types.append(statement.checkTypes())
        return types

    # Link all variables from the assignments/questions to the variable nodes.
    def linkVars(self, varDict):
        for statement in self.block:
            statement.linkVars(varDict)

    """
    Traversal called after linkVars is called, this one is used to return a dictionary that holds the questions
    of the program along with their scope (if they are in a conditional or not). The output will be used to
    generate questions
    
    """
    def getQLOrder(self):
        for statement in self.block:
            self.qlOrder[statement.getQLName()] = statement.getQLOrder()
        return self.qlOrder

    def getQLName(self):
        return self.name


    def __repr__(self):
        return "Form: {}, block: {}".format(self.name, self.block)
