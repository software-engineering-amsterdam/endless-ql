"""

"""
from .ast_methods import *


class QuestionNode:
    def __init__(self, question, varNode, line):
        self.question = question
        self.varNode = varNode
        self.line = line

    def checkTypes(self):
        return ["Question", self.varNode.checkTypes()]

    # Check if a variable has not been used yet, if so, add it to the dictionary, otherwise throw an error
    def linkVars(self, varDict):
        varname = self.varNode.getVarname()
        line = self.varNode.getLine()
        if varname in varDict:
            errorstring = "Error, double declaration of variable '" + varname + "' at line " + str(line)
            throwError(errorstring)
        else:
            new_entry = {"type": self.varNode.checkTypes(),
                         "node": self.varNode,
                         "assign": self
                         }
            varDict[varname] = new_entry

    def __repr__(self):
        return "Question: {}, {}:{}".format(self.question, self.varNode.getVarname(), self.varNode.checkTypes())
