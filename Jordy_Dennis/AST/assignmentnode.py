
from .ast_methods import *

class AssignmentNode:

    def __init__(self, question, varNode, expression, line):
        self.question = question
        self.varNode = varNode
        self.expression = expression
        self.line = line

    def changeValue(self, value):
        pass

    # This only works if both types are the same, OR if the expression is of type integer and the varNode is of type boolean.
    # Since an integer can always fit in a boolean
    def checkTypes(self):
        expType = self.expression.checkTypes()
        varNodeType = self.varNode.checkTypes()

        if(expType == int and varNodeType == float):
            return float
        elif(expType == varNodeType):
            return expType
        else:
            errorstring = "Incomparible types: " + str(varNodeType) + " and " + \
             str(expType) + "; of assignment at line " + str(self.line)
            throwError(errorstring)


    def __repr__(self):
        return "Assigment: \"{}\" {}:{} = {}".format(self.question, self.varName, self.varType, self.expression)
