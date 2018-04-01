from util.ast import ASTNode


class Statement(ASTNode):

    def __init__(self, location):
        super().__init__(location)

    def __repr__(self):
        return "Statement at {0}".format(self.location)

    __str__ = __repr__


class Question(Statement):

    def __init__(self, location, identifier):
        super().__init__(location)
        self._identifier = identifier

    @property
    def identifier(self):
        return self._identifier


class QuestionStyle(Statement):

    def __init__(self, location, identifier, widget):
        super().__init__(location)
        self._identifier = identifier
        self._widget = widget

    @property
    def identifier(self):
        return self._identifier

    @property
    def widget(self):
        return self._widget


class Default(Statement):

    def __init__(self, location, question_type, widget):
        super().__init__(location)
        self._question_type = question_type
        self._widget = widget

    @property
    def question_type(self):
        return self._question_type

    @property
    def widget(self):
        return self._widget


class DefaultWithBody(Statement):

    def __init__(self, location, question_type, widget, rules):
        super().__init__(location)
        self._question_type = question_type
        self._widget = widget
        self._rules = rules

    @property
    def question_type(self):
        return self._question_type

    @property
    def widget(self):
        return self._widget

    @property
    def rules(self):
        return self._rules
