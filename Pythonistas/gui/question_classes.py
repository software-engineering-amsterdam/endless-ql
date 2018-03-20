from PyQt5 import QtWidgets

class Question:
    def __init__(self, questionID, question, datatype, answer = 'undefined'):
        super(Question, self).__init__()
        self.questionID = questionID
        self.question = question
        self.datatype = datatype
        self.answer = answer
        self.defaultanswer = answer  # Used to reset an answer when a question is hidden
        self.hiddenanswer = answer  # Saves the user's answer if a question is hidden

    def set_answer(self, answer):
        self.answer = answer

    def get_answer(self):
        return self.answer

    def get_datatype(self):
        return self.datatype

    def if_is_set_true(self):
        self.questionframe.setVisible(True)
        # Restores the original answer when the question is revealed again.
        self.answer = self.hiddenanswer

    def if_is_set_false(self):
        self.questionframe.setVisible(False)

        # Saves the original answer even when the question is hidden,
        # and even when the False button is pressed multiple times.
        if self.answer == self.defaultanswer:
            pass
        else:
            self.hiddenanswer = self.answer
        self.answer = self.defaultanswer


class BooleanQuestion(Question):
    def __init__(self, questionID, question, datatype='boolean', answer='undefined'):
        Question.__init__(self, questionID, question, datatype, answer)
        self.buttongroup = QtWidgets.QButtonGroup()
        self.truebutton = None
        self.falsebutton = None
        self.buttongroup.setExclusive(True)
        self.ifquestions = []

    def set_truebutton(self, button):
        # try:
        #     self.buttongroup.removeButton(self.truebutton)
        # except:
        #     pass
        self.truebutton = button
        self.buttongroup.addButton(self.truebutton)

    def set_answer_true(self):
        self.answer = True
        self.truebutton.toggle()
        for question in self.ifquestions:
            question.if_is_set_true()

    def set_falsebutton(self, button):
        # try:
        #     self.buttongroup.removeButton(self.falsebutton)
        # except:
        #     pass
        self.falsebutton = button
        self.buttongroup.addButton(self.falsebutton)

    def set_answer_false(self):
        self.answer = False
        self.falsebutton.toggle()
        for question in self.ifquestions:
            question.if_is_set_false()

    def create_frame(self):
        # Creates a frame to be given to the questionnaire output frame.
        self.questionframe = QtWidgets.QFrame()
        self.questionlayout = QtWidgets.QGridLayout()
        self.questionframe.setLayout(self.questionlayout)
        self.questionlayout.addWidget(QtWidgets.QLabel(self.question), 0, 0)
        self.questionlayout.addWidget(self.truebutton, 0, 1)
        self.questionlayout.addWidget(self.falsebutton, 0, 2)
        return self.questionframe

    def add_if_question(self,question):
        self.ifquestions.append(question)
        question.questionframe.setVisible(False)


class MoneyQuestion(Question):
    def __init__(self, questionID, question, datatype='money', answer='undefined'):
        Question.__init__(self, questionID, question, datatype, answer)
        self.textInputBox = QtWidgets.QLineEdit()
        self.textInputBox.textEdited.connect(self.update_answer)

    # def set_text_input_box(self,textInputBox):
    #     self.textInputBox = textInputBox

    # def get_text(self):
    #     self.textInputBox.text()

    def update_answer(self):
        self.answer = self.textInputBox.text()

    def create_frame(self):
        # Creates a frame to be given to the questionnaire output frame.
        self.questionframe = QtWidgets.QFrame()
        self.questionlayout = QtWidgets.QGridLayout()
        self.questionframe.setLayout(self.questionlayout)

        self.questionlayout.addWidget(QtWidgets.QLabel(self.question),0,0)
        self.questionlayout.addWidget(self.textInputBox,0,1)

        return self.questionframe