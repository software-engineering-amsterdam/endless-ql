from gui.helper import gui_to_string


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

    def update_show_condition_on_change(self, changed_widget):
        changed_question = self.find_question_of_widget(changed_widget)

        if changed_question:
            changed_question.answer = changed_question.answer_type.get_literal_node(changed_question.widget.value())

            # number_of_rows = window.form_group_box.layout().rowCount()
            #
            # for row_index in range(number_of_rows):
            #     label = window.form_group_box.layout().itemAt(row_index * 2).widget()
            #     widget = window.form_group_box.layout().itemAt(row_index * 2 + 1).widget()
            #     print(type(label), widget.value())

            for question in self.block:
                result = question.evaluate_show_condition(self)

                if result:
                    question.widget.show()
                    question.widget_label.show()
                else:
                    question.widget.hide()
                    question.widget_label.hide()

                if question.computed:
                    answer_result = gui_to_string(question.evaluate_answer(self))
                    question.widget.setText(answer_result)
