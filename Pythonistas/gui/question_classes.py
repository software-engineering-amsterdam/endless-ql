"""
This file contains classes that reflect questions as taken from a Questionnaire Language (QL) AST, and corresponding GUI
elements. Instances of these classes are created in the visitor of this AST. If also a corresponding QLS AST is visited,
these objects are assigned attributes as defined in the QLS text.
"""
from PyQt5 import QtWidgets, QtCore, QtGui


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
        self.attributes = {'color': None, 'font_size': None, 'font': None, 'widget': None}

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

    def set_answer_label(self, label):
        self.attributes['widget'] = QtWidgets.QLabel(label)
        self.answer = label

    def create_label(self):
        # Creates a label as specified by the question's attributes
        question_label = QtWidgets.QLabel(self.question_string)
        color = self.attributes['color']
        font_size = self.attributes['font_size']
        font = self.attributes['font']
        question_label.setStyleSheet("color: {0}; font-size: {1}px; font-family: {2}".format(color, font_size, font))
        return question_label

    def set_attributes(self, attributes):
        # Sets class attributes that have not been set before
        if self.data_type in attributes.keys():
            attributes = attributes[self.data_type]
            for key in self.attributes.keys() & attributes.keys():
                if not self.attributes[key]:
                    self.attributes[key] = attributes[key]


class BooleanQuestion(Question):
    def __init__(self, question_id, question_string, data_type='boolean', answer='undefined'):
        Question.__init__(self, question_id, question_string, data_type, answer)
        self.buttons = []
        self.if_questions = []
        self.attributes['choices'] = None

    def set_radiobuttons(self, choices=('True', 'False')):
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

    def set_answer_label(self, label):
        if label == 'True' or label == 'False':
            self.attributes['widget'] = QtWidgets.QLabel(label)
            self.answer = label
            return None
        else:
            return ["Error: bad value for boolean question type: {}".format(label)]

    def create_frame(self):
        """ Creates output frame """
        self.question_frame = QtWidgets.QFrame()
        question_layout = QtWidgets.QGridLayout()
        self.question_frame.setLayout(question_layout)
        self.question_frame.setVisible(self.visibility)

        question_layout.addWidget(self.create_label(), 0, 0)

        if type(self.attributes['widget']) == QtWidgets.QLabel:
            self.buttons = [self.attributes['widget']]
            if self.answer != 'True' and self.answer != 'False':
                return QtWidgets.QLabel("Error: bad value for boolean question type")
        elif self.attributes['widget'] == 'radio':
            if self.attributes['choices']:
                self.set_radiobuttons(self.attributes['choices'])
        elif self.attributes['widget'] == 'checkbox':
            self.set_checkbox()
        elif not self.attributes['widget']:
            self.set_radiobuttons()
        else:
            return QtWidgets.QLabel("Error: undefined widget for boolean question type")

        column = 1
        for button in self.buttons:
            question_layout.addWidget(button, 0, column)
            column += 1

        return self.question_frame


class MoneyQuestion(Question):
    def __init__(self, question_id, question_string, data_type='money', answer='undefined'):
        Question.__init__(self, question_id, question_string, data_type, answer)
        self.text_input_box = None
        self.attributes['width'] = None

    def set_line_edit(self):
        self.text_input_box = QtWidgets.QLineEdit()
        # Allows only numerical input
        reg_ex = QtCore.QRegExp("[0-9]*")
        input_validator = QtGui.QRegExpValidator(reg_ex, self.text_input_box)
        self.text_input_box.setValidator(input_validator)

        self.text_input_box.textEdited.connect(self.update_answer)

    def set_spinbox(self):
        self.text_input_box = QtWidgets.QDoubleSpinBox()
        self.text_input_box.setMaximum(9 * 10**15)  # If set much higher, gives unexpected behaviour
        self.text_input_box.setDecimals(0)
        self.text_input_box.valueChanged.connect(self.update_answer)

    def update_answer(self):
        self.answer = self.text_input_box.text()

    def set_answer_label(self, label):
        if label.isdigit():
            self.attributes['widget'] = QtWidgets.QLabel(label)
            self.answer = label
            return None
        else:
            return ["Error: bad value for money question type: {}".format(label)]

    def create_frame(self):
        """ Creates output frame """
        self.question_frame = QtWidgets.QFrame()
        question_layout = QtWidgets.QGridLayout()
        self.question_frame.setLayout(question_layout)
        self.question_frame.setVisible(self.visibility)

        question_layout.addWidget(self.create_label(), 0, 0)

        if type(self.attributes['widget']) == QtWidgets.QLabel:
            self.text_input_box = self.attributes['widget']
        elif self.attributes['widget'] == 'spinbox':
            self.set_spinbox()
        elif not self.attributes['widget']:
            self.set_line_edit()
        elif self.attributes['widget'] == 'textbox':
            self.set_line_edit()
        else:
            return QtWidgets.QLabel("Error: undefined widget for money question type")

        if self.attributes['width']:
            self.text_input_box.setFixedWidth(self.attributes['width'])

        question_layout.addWidget(self.text_input_box, 0, 1)
        return self.question_frame
