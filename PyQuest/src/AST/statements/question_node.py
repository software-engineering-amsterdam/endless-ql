from AST.base_node import BaseNode
from PyQt5.QtWidgets import QLabel, QLineEdit


class QuestionNode(BaseNode):
    def __init__(self, line_number, question, label, value_type, expression):
        super(QuestionNode, self).__init__(line_number)
        self.__question = question
        self.__label = label
        self.__value_type = value_type
        self.__expression = expression

    @property
    def question(self):
        return self.__question

    @property
    def label(self):
        return self.__label

    @property
    def value_type(self):
        return self.__value_type

    @property
    def expression(self):
        return self.__expression

    def pyqt5_render(self, layout):
        layout.addRow(QLabel(self.question), self.value_type.pyqt5_default_widget())

