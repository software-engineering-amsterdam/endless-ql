"""
This class wraps the conditional expressions. 
Every conditionalNode has one if, optional multiple elif and one optional else
The If and elif are conditionals, else is just a block
"""

class ConditionalNode:
    def __init__(self, ifCondition, line):
        self.ifCondition= ifCondition
        self.elifCondition = []
        self.elseBlock = None
        self.line = line

    def addElifCondition(self, condition):
        self.elifCondition.append(condition)

    def addElseChild(self, block):
        self.elseBlock = block

    def checkTypes(self):
        print("checkingCond")


    def __repr__(self):
        return "Conditional: if: {} elif: {} else: {}".format(self.ifCondition, self.elifCondition, self.elseBlock)