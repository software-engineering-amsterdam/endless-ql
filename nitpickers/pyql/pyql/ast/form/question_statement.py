from pyql.ast.form import statement


class QuestionStatement(statement.Statement):

    def __init__(self, location, identifier, text, question_type, answer=None):
        super().__init__(location)
        self._identifier = identifier
        self._text = text
        self._question_type = question_type
        self._answer = answer

    @property
    def identifier(self):
        return self._identifier

    @property
    def text(self):
        return self._text

    @property
    def question_type(self):
        return self._question_type

    @property
    def answer(self):
        return self._answer

    @answer.setter
    def answer(self, answer):
        self._answer = answer

    def __repr__(self):
        return "AST QuestionStatement at: " + str(self.location)
