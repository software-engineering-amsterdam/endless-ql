"""
This Class of used for the If and Elif conditional.
Both types need a expression that has to be evaluated
and a block which can contain statements.
"""


class ConditionNodeBlock:
    def __init__(self, expression, line):
        self.expression = expression
        self.block = []
        self.line = line

    def addQuestions(self, block):
        for i in block:
            self.block.append(i)

    # Check types of children, return them for the sake of possible debugging
    def checkTypes(self):
        types = []
        ifType = self.expression.checkTypes()
        types.append(ifType)
        for statement in self.block:
            types.append(statement.checkTypes())
        return types

    def __repr__(self):
        return "({}) {}".format(self.expression, self.block)
