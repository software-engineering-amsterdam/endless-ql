
class AssignmentNode:
    def __init__(self, question, var, varType, expression):
        self.question = question
        self.varName = varName
        self.varType = varType
        self.value = expression

    def changeValue(self, value):
        pass

    def __repr__(self):
        return "Assigment: \"{}\" {}:{} = {}".format(self.question,self.varName, self.varType, self.expression)