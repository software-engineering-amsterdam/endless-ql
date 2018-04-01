from gui.widgets.label import Label
from ql.ast.visitors.expression_evaluator import ExpressionEvaluator


class QuestionModel:
    def __init__(self, label, identifier, answer_type, answer, computed, visibility_condition):
        self.__label = label
        self.__identifier = identifier
        self.__answer_type = answer_type
        self.__answer = answer
        self.__computed = computed
        self.__visibility_condition = visibility_condition
        self.__widget = None
        self.__widget_label = None

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
    def visibility_condition(self):
        return self.__visibility_condition

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

    def evaluate_visibility_condition(self, form):
        visitor = ExpressionEvaluator(form)
        visitor.visit(self.visibility_condition)
        return visitor.result

    def evaluate_answer(self, form):
        visitor = ExpressionEvaluator(form)
        visitor.visit(self.answer)
        return visitor.result

    def pyqt5_render(self, layout, form, visible=True):
        self.widget_label = Label(self.label)

        if self.computed:
            answer_result = str(self.evaluate_answer(form))
            self.widget = Label(answer_result)
        else:
            self.widget = self.answer_type.pyqt5_default_widget()

        if not visible:
            self.widget.hide()
            self.widget_label.hide()

        layout.addRow(self.widget_label, self.widget)
