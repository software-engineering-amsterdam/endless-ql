"""
This class wraps the conditional expressions.
Every conditionalNode has one if, optional multiple elif and one optional else

The ifConditionBlock contains a node in which the expression for the if lies, and its statements (that are called after evaluation)

The elifConditionBlock contains multiple ifConditionBlocks

The else condition is a block of statements
"""
from .ast_methods import *

class ConditionalNode:
    def __init__(self, ifConditionBlock, line):
        self.ifConditionBlock = ifConditionBlock
        self.elifConditionBlock = []
        self.elseBlock = None
        self.line = line
        self.qlOrder = collections.OrderedDict()
        self.nodeType = "Conditional"

    def addElifCondition(self, condition):
        self.elifCondition.append(condition)

    def addElseChild(self, block):
        self.elseBlock = block

    # We check the types of the expressions, it does not matter if they are eventually int or bool, since they all have a default value.
    # We also do not have to check anything else, because at this point an error would have been thrown if the types didnt match.
    # Return the types for possible debugging
    def checkTypes(self):
        types = []
        ifType = self.ifConditionBlock.checkTypes()
        types.append(ifType)
        for elifBlock in self.elifConditionBlock:
            types.append(elifBlock.checkTypes())
        if(self.elseBlock):
            for elseblock in self.elseBlock:
                types.append(elseblock.checkTypes())
        return ["Conditional:", types]

    # Link all variables from the assignments/questions to the variable nodes.
    def linkVars(self, varDict):
        self.ifConditionBlock.linkVars(varDict)
        for elifBlock in self.elifConditionBlock:
            elifBlock.linkVars(varDict)
        if(self.elseBlock):
            for elseblock in self.elseBlock:
                elseblock.linkVars(varDict)

    def getNodeType(self):
        return self.nodeType

    def getIf(self):
        return self.ifConditionBlock

    def getElIf(self):
        return self.elifConditionBlock

    def getElse(self):
        return self.elseBlock


    def __repr__(self):
        return "Conditional: if: {} elif: {} else: {}".format(self.ifConditionBlock, self.elifConditionBlock, self.elseBlock)
