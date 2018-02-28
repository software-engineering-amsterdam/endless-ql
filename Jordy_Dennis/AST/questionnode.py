"""

"""


class QuestionNode:
    def __init__(self, question, varName, varType, line):
        self.question = question
        self.varName = varName
        self.varType = varType
        self.line = line

    def checkTypes(self):
        return ["Question", self.varType]

    def __repr__(self):
        return "Question: {}, {}:{}".format(self.question, self.varName, self.varType)
