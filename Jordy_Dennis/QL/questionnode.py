"""
    Node for question, it contains a varnode where the value will be stored
"""
from .ast_methods import *
import collections

class QuestionNode:
    def __init__(self, question, varNode, line):
        self.question = question
        self.varNode = varNode
        self.line = line
        self.nodeType = "Question"

    """
        Check if a variable has not been used yet, if so, add it to the dictionary, otherwise throw an error
        The node list is a collection of all the occurences of the varNodes, this is collected so we can easily
        modify all the values once they have been changed in the gui
    """
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
            new_entry["node_list"] = [self.varNode]
            varDict[varname] = new_entry

    def checkTypes(self):
        return ["Question", self.varNode.checkTypes()]

    def getNodeType(self):
        return self.nodeType

    def getVarName(self):
        return self.varNode.getVarname()

    def getQuestion(self):
        return self.question

    def setVar(self, value ,varDict):
        self.varNode.setVar(value)
        var = self.varNode.getVarname()
        varDict[var]['node'] = self.varNode

    def __repr__(self):
        return "Question: {}, {}:{}".format(self.question, self.varNode.getVarname(), self.varNode.checkTypes())
