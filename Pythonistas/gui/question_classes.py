from PyQt5 import QtWidgets


class Question:
    def __init__(self, question_id, question_string, data_type, answer='undefined'):
        self.question_id = question_id
        self.question_string = question_string
        self.data_type = data_type
        self.answer = answer
        self.default_answer = answer  # Used to reset
        self.hidden_answer = answer  # Saves if hidden
        self.visibility = True
        self.question_frame = None
        self.attributes = {'color':None,'font_size':None,'font':None}

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

    def create_label(self):
        question_label = QtWidgets.QLabel(self.question_string)
        color = self.attributes['color']
        font_size = self.attributes['font_size']
        font = self.attributes['font']
        question_label.setStyleSheet("color: {0}; font-size: {1}px; font-family: {2}".format(color, font_size, font))
        # question_label.setStyleSheet("color: {0}; font-size: {1}".format('red', 'peop'))
        return question_label

    def set_attributes(self, attributes):
        # thanks to
        # stackoverflow.com/questions/15277307/update-a-dictionary-with-another-dictionary-but-only-non-none-values
        self.attributes.update((k, attributes[k]) for k in self.attributes.keys() & attributes.keys())


class BooleanQuestion(Question):
    def __init__(self, question_id, question_string, data_type='boolean', answer='undefined'):
        Question.__init__(self, question_id, question_string, data_type, answer)
        self.buttons = []
        self.if_questions = []
        self.question_layout = None

    def set_radiobuttons(self, choices=('Yes', 'No')):
        # todo: make flexible
        button_group = QtWidgets.QButtonGroup()
        button_group.setExclusive(True)

        true_button = QtWidgets.QRadioButton(choices[0])
        true_button.pressed.connect(self.set_answer_true)
        true_button.pressed.connect(true_button.toggle)
        self.buttons.append(true_button)
        button_group.addButton(true_button)

        false_button = QtWidgets.QRadioButton(choices[1])
        false_button.pressed.connect(self.set_answer_false)
        false_button.pressed.connect(false_button.toggle)
        self.buttons.append(false_button)
        button_group.addButton(false_button)

    def set_checkbox(self):
        check_box = QtWidgets.QCheckBox()
        check_box.stateChanged.connect(self.state_changed)
        self.buttons.append(check_box)

    def state_changed(self, state):
        print(state)
        if state:
            self.set_answer_true()
        else:
            self.set_answer_false()

    def set_answer_false(self):
        self.answer = False
        for question in self.if_questions:
            question.if_false()

    def set_answer_true(self):
        self.answer = True
        for question in self.if_questions:
            question.if_true()

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
        if self.answer == True:  # Must be explicitly equal to True, as answer may be "undefined".
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

    def create_frame(self):
        """ Creates output frame """
        self.question_frame = QtWidgets.QFrame()
        self.question_layout = QtWidgets.QGridLayout()
        self.question_frame.setLayout(self.question_layout)
        self.question_frame.setVisible(self.visibility)

        self.question_layout.addWidget(self.create_label(), 0, 0)

        if not self.buttons:
            # self.set_checkbox()
            self.set_radiobuttons()
        column = 1
        for button in self.buttons:
            self.question_layout.addWidget(button, 0, column)
            column += 1

        return self.question_frame


class MoneyQuestion(Question):
    def __init__(self, question_id, question_string, data_type='money', answer='undefined'):
        Question.__init__(self, question_id, question_string, data_type, answer)
        self.text_input_box = QtWidgets.QLineEdit()
        self.text_input_box.textEdited.connect(self.update_answer)
        self.question_layout = None

        self.attributes['width'] = None

    def set_answer_box(self, text_box):
        self.text_input_box = text_box
        self.text_input_box.textEdited.connect(self.update_answer)

    def update_answer(self):
        self.answer = self.text_input_box.text()

    def create_frame(self):
        """ Creates output frame """
        self.question_frame = QtWidgets.QFrame()
        self.question_layout = QtWidgets.QGridLayout()
        self.question_frame.setLayout(self.question_layout)
        self.question_frame.setVisible(self.visibility)

        self.question_layout.addWidget(self.create_label(), 0, 0)

        self.question_layout.addWidget(self.text_input_box, 0, 1)
        return self.question_frame