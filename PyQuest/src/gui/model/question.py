from gui.model.widgets import Label
from ql.ast.visitors.expression_evaluator import ExpressionEvaluator


class Question:
    def __init__(self, label, identifier, answer_type, answer, computed, show_condition):
        self.__label = label
        self.__identifier = identifier
        self.__answer_type = answer_type
        self.__answer = answer
        self.__computed = computed
        self.__show_condition = show_condition
        self.__widget = None
        self.__widget_label = Label(label)

    @property
    def label(self):
        return self.__label

    @property
    def identifier(self):
        return self.__identifier

    @property
    def answer_type(self):
        return self.__answer_type

    @property
    def answer(self):
        return self.__answer

    @answer.setter
    def answer(self, value):
        self.__answer = value

    @property
    def computed(self):
        return self.__computed

    @property
    def show_condition(self):
        return self.__show_condition

    @property
    def widget(self):
        return self.__widget

    @widget.setter
    def widget(self, value):
        self.__widget = value

    @property
    def widget_label(self):
        return self.__widget_label

    def evaluate_show_condition(self, form):
        visitor = ExpressionEvaluator(form)
        visitor.visit(self.show_condition)

        return visitor.result

    def evaluate_answer(self, form):
        visitor = ExpressionEvaluator(form)
        visitor.visit(self.answer)

        return visitor.result

    def pyqt5_render(self, layout, form, show=True):
        if self.computed:
            answer_result = str(self.evaluate_answer(form))
            self.widget = Label(answer_result)
        else:
            self.widget = self.answer_type.pyqt5_default_widget()

        if not show:
            self.widget.hide()
            self.widget_label.hide()

        layout.addRow(self.widget_label, self.widget)
