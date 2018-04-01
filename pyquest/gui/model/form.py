from json import dumps


class FormModel:
    def __init__(self, identifier):
        self.__identifier = identifier
        self.__block = []

    @property
    def identifier(self):
        return self.__identifier

    @property
    def block(self):
        return self.__block

    def to_json(self):
        return dumps({question.identifier: question.answer.value.get_json_value() for question in self.block})

    def find_question_of_widget(self, widget):
        for question in self.block:
            if question.widget == widget:
                return question

    def update_questions_on_change(self, changed_widget):
        changed_question = self.find_question_of_widget(changed_widget)

        if changed_question:
            new_value = changed_question.widget.get_value(changed_question.answer_type)
            changed_question.answer = changed_question.answer_type.get_literal_node(new_value)

            for question in self.block:
                result = question.evaluate_visibility_condition(self)

                if result:
                    question.widget.show()
                    question.widget_label.show()
                else:
                    question.widget.hide()
                    question.widget_label.hide()

                if question.computed:
                    answer_result = str(question.evaluate_answer(self))
                    question.widget.setText(answer_result)
