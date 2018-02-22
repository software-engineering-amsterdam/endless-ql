"""
This Class wraps the form into a node. It has to have a name 
and can contain statements in its block

"""
class FormNode:
    def __init__(self, name):
        self.name = name
        self.block = []

    def addStatements(self, statements):
        for statement in statements:
            self.block.append(statement)

    def  __repr__(self):
        return "Form: {}, block: {}".format(self.name, self.block)