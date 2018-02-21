from pyql.ast.form import statement


class QuestionStatement(statement.Statement):

    def __init__(self, location, identifier, text, question_type):
        super().__init__(location)
        self._identifier = identifier
        self._text = text
        self._question_type = question_type

    @property
    def identifier(self):
        return self._identifier

    @property
    def text(self):
        return self._text

    @property
    def question_type(self):
        return self._question_type

    def __repr__(self):
        output = {
            "identifier":       str(self.identifier),
            "text":             str(self.text),
            "question_type":    str(self.question_type),
        }
        return str({"AST QuestionStatement " + str(self.location): str(output)})
