class ConditionNode:
    def __init__(self, expression):
        self.expression = expression
        self.questions = []

    def addQuestions(self, questions):
        for i in questions:
            self.questions.append(i)

    def __repr__(self):
        return "({}) {}".format(self.expression, self.questions)

