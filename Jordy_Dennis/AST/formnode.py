"""
This Class wraps the form into a node. It has to have a name 
and can contain statements in its block
"""


class FormNode:
    def __init__(self, name, line):
        self.name = name
        self.children = []
        self.line = line
        self.block = []

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


    def __repr__(self):
        return "Form: {}, block: {}".format(self.name, self.block)
