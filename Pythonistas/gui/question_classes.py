from PyQt5 import QtWidgets


class Question:
    def __init__(self, question_id, question, data_type, answer='undefined'):
        super(Question, self).__init__()
        self.question_id = question_id
        self.question = question
        self.data_type = data_type
        self.answer = answer
        self.default_answer = answer  # Used to reset
        self.hidden_answer = answer  # Saves if hidden
        self.visibility = True
        self.question_frame = None

    def get_data_type(self):
        return self.data_type

    def if_true(self):
        if self.question_frame:
            self.question_frame.setVisible(True)
        else:
            self.visibility = True

        # Restores the original answer
        self.answer = self.hidden_answer

    def if_false(self):
        if self.question_frame:
            self.question_frame.setVisible(False)
        else:
            self.visibility = False

        # Saves the original answer
        if self.answer == self.default_answer:
            pass
        else:
            self.hidden_answer = self.answer
        self.answer = self.default_answer

    # def set_answer(self, answer):
    #     self.answer = answer

    # def get_answer(self):
    #     return self.answer


class BooleanQuestion(Question):
    def __init__(self, question_id, question, data_type='boolean', answer='undefined'):
        Question.__init__(self, question_id, question, data_type, answer)
        self.button_group = QtWidgets.QButtonGroup()
        self.true_button = None
        self.false_button = None
        self.button_group.setExclusive(True)
        self.if_questions = []
        self.question_layout = None

    def set_true_button(self, button):
        # try:
        #     self.button_group.removeButton(self.truebutton)
        # except:
        #     pass
        self.true_button = button
        self.button_group.addButton(self.true_button)

    def set_answer_true(self):
        self.answer = True
        self.true_button.toggle()
        for question in self.if_questions:
            question.if_true()

    def set_false_button(self, button):
        # try:
        #     self.button_group.removeButton(self.falsebutton)
        # except:
        #     pass
        self.false_button = button
        self.button_group.addButton(button)

    def set_answer_false(self):
        self.answer = False
        self.false_button.toggle()
        for question in self.if_questions:
            question.if_false()

    def create_frame(self):
        """ Creates output frame """
        self.question_frame = QtWidgets.QFrame()
        self.question_layout = QtWidgets.QGridLayout()
        self.question_frame.setLayout(self.question_layout)
        self.question_frame.setVisible(self.visibility)

        self.question_layout.addWidget(QtWidgets.QLabel(self.question), 0, 0)
        self.question_layout.addWidget(self.true_button, 0, 1)
        self.question_layout.addWidget(self.false_button, 0, 2)
        return self.question_frame

    def add_if_question(self, question):
        self.if_questions.append(question)
        question.visibility = False

    def if_true(self):
        if self.question_frame:
            self.question_frame.setVisible(True)
        else:
            self.visibility = True

        # Restores the original answer when the question is revealed again.
        self.answer = self.hidden_answer

        # Reveals the underlying questions too.
        if self.answer:
            for question in self.if_questions:
                question.if_true()

    def if_false(self):
        if self.question_frame:
            self.question_frame.setVisible(False)
        else:
            self.visibility = False

        # Saves the original answer even when the question is hidden,
        # and even when the False button is pressed multiple times.
        if self.answer == self.default_answer:
            pass
        else:
            self.hidden_answer = self.answer
        self.answer = self.default_answer

        # Hides the underlying questions too
        for question in self.if_questions:
            question.if_false()


class MoneyQuestion(Question):
    def __init__(self, question_id, question, data_type='money', answer='undefined'):
        Question.__init__(self, question_id, question, data_type, answer)
        self.text_input_box = QtWidgets.QLineEdit()
        self.text_input_box.textEdited.connect(self.update_answer)
        self.question_layout = None

    def set_answer_box(self, text_box):
        self.text_input_box = text_box

    def update_answer(self):
        self.answer = self.text_input_box.text()

    def create_frame(self):
        """ Creates output frame """
        self.question_frame = QtWidgets.QFrame()
        self.question_layout = QtWidgets.QGridLayout()
        self.question_frame.setLayout(self.question_layout)
        self.question_frame.setVisible(self.visibility)

        self.question_layout.addWidget(QtWidgets.QLabel(self.question), 0, 0)
        self.question_layout.addWidget(self.text_input_box, 0, 1)

        return self.question_frame
