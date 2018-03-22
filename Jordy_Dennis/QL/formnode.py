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

    def addStatements(self, statements):
        for statement in statements:
            self.block.append(statement)

    def getQLName(self):
        return self.name


    def __repr__(self):
        return "Form: {}, block: {}".format(self.name, self.block)
