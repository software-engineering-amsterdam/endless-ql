from PyQt5 import QtWidgets

class Question:
    def __init__(self, questionID, question, datatype, answer = 'undefined'):
        super(Question, self).__init__()
        self.questionID = questionID
        self.question = question
        self.datatype = datatype
        self.answer = answer

    def set_answer(self, answer):
        self.answer = answer

    def get_answer(self):
        return self.answer


class BooleanQuestion(Question):
    def __init__(self, questionID, question, datatype, answer = 'undefined'):
        Question.__init__(self, questionID, question, datatype, answer)
        self.buttongroup = QtWidgets.QButtonGroup()
        self.truebutton = None
        self.falsebutton = None
        self.buttongroup.setExclusive(True)

    def set_truebutton(self, button):
        # try:
        #     self.buttongroup.removeButton(self.truebutton)
        # except:
        #     pass
        self.truebutton = button
        self.buttongroup.addButton(self.truebutton)

    def set_answer_true(self):
        self.answer = True

    def set_falsebutton(self, button):
        # try:
        #     self.buttongroup.removeButton(self.falsebutton)
        # except:
        #     pass
        self.falsebutton = button
        self.buttongroup.addButton(self.falsebutton)

    def set_answer_false(self):
        self.answer = False

    def create_frame(self):
        questionframe = QtWidgets.QFrame()
        self.questionlayout = QtWidgets.QGridLayout()
        questionframe.setLayout(self.questionlayout)
        self.questionlayout.addWidget(QtWidgets.QLabel(self.question), 0, 0)
        self.questionlayout.addWidget(self.truebutton, 0, 1)
        self.questionlayout.addWidget(self.falsebutton, 0, 2)
        return questionframe

class MoneyQuestion(Question):
    def __init__(self, questionID, question, datatype, answer = 'undefined'):
        Question.__init__(self, questionID, question, datatype, answer)
        self.textInput = None

    def set_text_input(self,textInput):
        self.textInput = textInput

    def get_text(self):
        self.textInput.text()

    def set_answer_text(self):
        self.answer = self.textInput.text()
        print(self.answer)

    def create_frame(self):
        # Creates a frame to be given to the questionnaire output frame.
        questionframe = QtWidgets.QFrame()
        self.questionlayout = QtWidgets.QGridLayout()
        questionframe.setLayout(self.questionlayout)

        self.questionlayout.addWidget(QtWidgets.QLabel(self.question),0,0)
        self.questionlayout.addWidget(self.textInput,0,1)

        return questionframe