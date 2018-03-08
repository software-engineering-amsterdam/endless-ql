from PyQt5.QtWidgets import QLabel
from visitors.expression_evaluator import ExpressionEvaluator


class Question:
    def __init__(self, label, identifier, answer_type, answer, show_condition):
        self.__label = label
        self.__identifier = identifier
        self.__answer_type = answer_type
        self.__answer = answer
        self.__show_condition = show_condition
        self.__widget = None
        self.__widget_label = None

    @property
    def label(self):
        return self.__label

    @label.setter
    def label(self, value):
        self.__label = value

    @property
    def identifier(self):
        return self.__identifier

    @property
    def answer_type(self):
        return self.__answer_type

    @property
    def answer(self):
        return self.__answer

    @property
    def show_condition(self):
        return self.__show_condition

    @show_condition.setter
    def show_condition(self, value):
        self.__show_condition = value

    @property
    def widget(self):
        return self.__widget

    @widget.setter
    def widget(self, value):
        self.__widget = value

    @property
    def widget_label(self):
        return self.__widget_label

    @widget_label.setter
    def widget_label(self, value):
        self.__widget_label = value

    def evaluate_show_condition(self, form):
        visitor = ExpressionEvaluator(form)
        visitor.visit(self.show_condition)

        return visitor.result

    def pyqt5_render(self, layout, show=True):
        self.widget = self.answer_type.pyqt5_default_widget()
        self.__widget_label = QLabel(self.label)

        if not show:
            self.widget.hide()
            self.widget_label.hide()

        layout.addRow(self.__widget_label, self.widget)
