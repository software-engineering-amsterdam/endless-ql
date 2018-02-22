
class AssignmentNode:
    def __init__(self, question, varName, varType, expression, line):
        self.question = question
        self.varName = varName
        self.varType = varType
        self.expression = expression
        self.line = line

    def changeValue(self, value):
        pass

    def __repr__(self):
        return "Assigment: \"{}\" {}:{} = {}".format(self.question,self.varName, self.varType, self.expression)