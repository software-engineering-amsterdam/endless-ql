
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
            return ["Assign", float]
        elif(expType == varNodeType):
            return ["Assign", expType]
        else:
            errorstring = "Incomparible types: " + str(varNodeType) + " and " + \
             str(expType) + "; of assignment at line " + str(self.line)
            throwError(errorstring)

    # Add the newly created variable if necessary, and call linkVars for the expression children.
    # We also add the assignment node, so we can set the varNode of this assignment later, to be equal
    # to a varNode which could be used elsewhere
    def linkVars(self, varDict):
        # call for children
        self.expression.linkVars(varDict)
        # Adding new entry
        varname = self.varNode.getVarname()
        line = self.varNode.getLine()
        if varname in varDict:
            errorstring = "Error, double declaration of variable '" + varname + "' at line " + str(line)
            throwError(errorstring)
        else:
            new_entry = {   "type": self.varNode.checkTypes(),
                            "node": self.varNode,
                            "assign": self
                        }
            varDict[varname] = new_entry


    def __repr__(self):
        return "Assigment: \"{}\" {}:{} = {}".format(self.question, self.varNode.getVarname(), self.varNode.checkTypes(), self.expression)
