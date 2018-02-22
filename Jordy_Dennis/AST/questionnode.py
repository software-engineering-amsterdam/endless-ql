
class QuestionNode:
    def __init__(self, question, varName, varType):
        self.question = question
        self.varName = varName
        self.varType = varType

    def __repr__(self):
        return "Question: {}, {}:{}".format(self.question,self.varName, self.varType)