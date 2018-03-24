from PyQt5 import QtWidgets


class Question:
    def __init__(self, questionID, question, data_type, answer = 'undefined'):
        super(Question, self).__init__()
        self.questionID = questionID
        self.question = question
        self.data_type = data_type
        self.answer = answer
        self.default_answer = answer  # Used to reset an answer when a question is hidden
        self.hidden_answer = answer  # Saves the user's answer if a question is hidden
        self.visibility = True
        self.question_frame = None

    # def set_answer(self, answer):
    #     self.answer = answer

    # def get_answer(self):
    #     return self.answer

    def get_data_type(self):
        return self.data_type

    def if_is_set_true(self):  # todo: find proper name for this
        if self.question_frame:
            self.question_frame.setVisible(True)
        else:
            self.visibility = True

        # Restores the original answer when the question is revealed again.
        self.answer = self.hidden_answer

    def if_is_set_false(self):  # todo: find proper name for this
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


class BooleanQuestion(Question):
    def __init__(self, questionID, question, data_type='boolean', answer='undefined'):
        Question.__init__(self, questionID, question, data_type, answer)
        self.button_group = QtWidgets.QButtonGroup()
        self.truebutton = None
        self.falsebutton = None
        self.button_group.setExclusive(True)
        self.if_questions = []

    def set_truebutton(self, button):
        # try:
        #     self.button_group.removeButton(self.truebutton)
        # except:
        #     pass
        self.truebutton = button
        self.button_group.addButton(self.truebutton)

    def set_answer_true(self):
        self.answer = True
        self.truebutton.toggle()
        for question in self.if_questions:
            question.if_is_set_true()

    def set_falsebutton(self, button):
        # try:
        #     self.button_group.removeButton(self.falsebutton)
        # except:
        #     pass
        self.falsebutton = button
        self.button_group.addButton(self.falsebutton)

    def set_answer_false(self):
        self.answer = False
        self.falsebutton.toggle()
        for question in self.if_questions:
            question.if_is_set_false()

    def create_frame(self):
        # Creates a frame to be given to the questionnaire output frame.
        self.question_frame = QtWidgets.QFrame()
        self.question_layout = QtWidgets.QGridLayout()
        self.question_frame.setLayout(self.question_layout)
        self.question_frame.setVisible(self.visibility)

        self.question_layout.addWidget(QtWidgets.QLabel(self.question), 0, 0)
        self.question_layout.addWidget(self.truebutton, 0, 1)
        self.question_layout.addWidget(self.falsebutton, 0, 2)
        return self.question_frame

    def add_if_question(self,question):
        self.if_questions.append(question)
        question.visibility = False

    def if_is_set_true(self):  # todo: find proper name for this
        if self.question_frame:
            self.question_frame.setVisible(True)
        else:
            self.visibility = True

        # Restores the original answer when the question is revealed again.
        self.answer = self.hidden_answer

        # Reveals the underlying questions too.
        if self.answer == True:
            for question in self.if_questions:
                question.if_is_set_true()

    def if_is_set_false(self):  # todo: find proper name for this
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
            question.if_is_set_false()


class MoneyQuestion(Question):
    def __init__(self, questionID, question, data_type='money', answer='undefined'):
        Question.__init__(self, questionID, question, data_type, answer)
        self.text_input_box = QtWidgets.QLineEdit()
        self.text_input_box.textEdited.connect(self.update_answer)

    def set_answer_box(self,textBox):
        self.text_input_box = textBox

    def update_answer(self):
        self.answer = self.text_input_box.text()

    def create_frame(self):
        # Creates a frame to be given to the questionnaire output frame.
        self.question_frame = QtWidgets.QFrame()
        self.question_layout = QtWidgets.QGridLayout()
        self.question_frame.setLayout(self.question_layout)
        self.question_frame.setVisible(self.visibility)

        self.question_layout.addWidget(QtWidgets.QLabel(self.question),0,0)
        self.question_layout.addWidget(self.text_input_box,0,1)

        return self.question_frame