"""
    Assignment Node, used when a variable without question is assigned. It contains an expression and a varnode.
    The question here is actually the variable name.
"""

from .ast_methods import *
import collections
class AssignmentNode:

    def __init__(self, question, varNode, expression, line):
        self.question = question
        self.varNode = varNode
        self.expression = expression
        self.line = line
        self.nodeType = "Assignment"

    # TODO, check if this can be removed?
    def changeValue(self, value):
        pass

    """
        Check the types of the expression, and check if the expression has the same type as the assignment.
        This only works if both types are the same, OR if the expression is of type integer and the varNode is of type float.
        Since an integer can always fit in a float

        Return statements that can be used for debugging
    """
    def checkTypes(self):
        expType = self.expression.checkTypes()
        varNodeType = self.varNode.checkTypes()

        if(expType == int and varNodeType == float):
            return ["Assign: " + self.varNode.varname, float]
        elif(expType == varNodeType):
            return ["Assign: " + self.varNode.varname, expType]
        else:
            errorstring = "Incomparible types: " + str(varNodeType) + " and " + \
             str(expType) + "; of assignment at line " + str(self.line)
            throwError(errorstring)

    """
        Add the newly created variable if necessary, and call linkVars for the expression children.
        The node list is a collection of all the occurences of the varNodes, this is collected so we can easily
        modify all the values once they have been changed in the gui
    """
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
            new_entry = collections.OrderedDict()
            new_entry["type"] = self.varNode.checkTypes()
            new_entry["node"] = self.varNode
            new_entry["assign"] = self
            new_entry["node_list"] = [self.varNode]
            varDict[varname] = new_entry

    """
        Evaluate the expression for the GUI
    """
    def evaluate(self, varDict):
        var = self.getVarName()
        outcome = self.expression.evaluate()
        self.varNode.setVar(outcome)
        varDict[var]['node'] = self.varNode

    """
        Some getters and setters --------------------
    """
    def getNodeType(self):
        return self.nodeType

    def getName(self):
        return self.question

    def getVarName(self):
        return self.varNode.getVarname()

    def getExpression(self):
        return self.expression

    def getQuestion(self):
        return self.question

    def __repr__(self):
        return "Assigment: \"{}\" {}:{} = {}".format(self.question, self.varNode.getVarname(), self.varNode.checkTypes(), self.expression)
