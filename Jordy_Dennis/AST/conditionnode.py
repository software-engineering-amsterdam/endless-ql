"""
This Class of used for the If and Elif conditional.
Both types need a condition that has to be evaluated
and a block which can contain statements.
"""

class ConditionNode:
    def __init__(self, condition):
        self.condition = condition
        self.block = []

    def addQuestions(self, block):
        for i in block:
            self.block.append(i)

    def __repr__(self):
        return "({}) {}".format(self.condition, self.block)

