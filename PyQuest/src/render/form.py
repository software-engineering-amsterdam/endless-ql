from visitors.expression_evaluator import ExpressionEvaluator
from AST.position import Position
from AST.expressions.literals.boolean_node import BooleanNode
from AST.types.type_boolean import TypeBoolean


class Form:
    def __init__(self, identifier):
        self.__identifier = identifier
        self.__block = []

    @property
    def identifier(self):
        return self.__identifier

    @property
    def block(self):
        return self.__block

    def find_question_of_widget(self, widget):
        for question in self.block:
            if question.widget == widget:
                return question

        return

    def update_show_condition_on_change(self, changed_widget):
        changed_question = self.find_question_of_widget(changed_widget)
        changed_question.answer = BooleanNode(Position(0, 0), TypeBoolean, changed_question.widget.value())

        for question in self.block:
            visitor = ExpressionEvaluator(self)
            visitor.visit(question.show_condition)

            if question.show_condition.value:
                question.widget.show()
                question.widget_label.show()
            else:
                question.widget.hide()
                question.widget_label.hide()
