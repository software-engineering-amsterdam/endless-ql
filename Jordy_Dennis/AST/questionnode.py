"""

"""
from .ast_methods import *


class QuestionNode:
    def __init__(self, question, varNode, line):
        self.question = question
        self.varNode = varNode
        self.line = line
        self.nodeType = "Question"

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
            new_entry = collections.OrderedDict()
            new_entry["type"] = self.varNode.checkTypes()
            new_entry["node"] = self.varNode
            new_entry["assign"] = self
            varDict[varname] = new_entry

    # Traversal called after linkVars is called, this one is used to return a dictionary that holds the questions
    # of the program along with their scope (if they are in a conditional or not). The output will be used to
    # generate questions
    def getQLOrder(self):
        return self.question

    def getNodeType(self):
        return self.nodeType

    def getQLName(self):
        return self.varNode.getVarname()

    def getQuestion(self):
        return self.question

    def __repr__(self):
        return "Question: {}, {}:{}".format(self.question, self.varNode.getVarname(), self.varNode.checkTypes())
