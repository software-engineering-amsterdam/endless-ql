from AST.base_node import BaseNode


class QuestionNode(BaseNode):
    def __init__(self, line_number, question, label, value_type, expression):
        super(QuestionNode, self).__init__(line_number)
        self._question = question
        self._label = label
        self._value_type = value_type
        self._expression = expression

    @property
    def question(self):
        return self._question

    @property
    def label(self):
        return self._label

    @property
    def val_type(self):
        return self._value_type

    @property
    def expression(self):
        return self._expression
