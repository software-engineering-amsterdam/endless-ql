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

    def get_datatype(self):
        return self.datatype


class BooleanQuestion(Question):
    def __init__(self, questionID, question, datatype, answer = 'undefined'):
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
        for question in self.ifquestions:
            question.questionframe.setVisible(True)
            try:  # todo: clean up
                if question.buttongroup.checkedButton() == question.truebutton:
                    question.set_answer_true()
                elif question.buttongroup.checkedButton() == question.falsebutton:
                    question.set_answer_false()
            except:
                pass

    def set_falsebutton(self, button):
        # try:
        #     self.buttongroup.removeButton(self.falsebutton)
        # except:
        #     pass
        self.falsebutton = button
        self.buttongroup.addButton(self.falsebutton)

    def set_answer_false(self):
        self.answer = False
        for question in self.ifquestions:
            question.questionframe.setVisible(False)
            question.set_answer('undefined')

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
    def __init__(self, questionID, question, datatype, answer = 'undefined'):
        Question.__init__(self, questionID, question, datatype, answer)
        self.textInputBox = None

    def set_text_input_box(self,textInputBox):
        self.textInputBox = textInputBox

    def get_text(self):
        self.textInputBox.text()

    def set_answer_text(self):
        self.answer = self.textInputBox.text()

    def create_frame(self):
        # Creates a frame to be given to the questionnaire output frame.
        self.questionframe = QtWidgets.QFrame()
        self.questionlayout = QtWidgets.QGridLayout()
        self.questionframe.setLayout(self.questionlayout)

        self.questionlayout.addWidget(QtWidgets.QLabel(self.question),0,0)
        self.questionlayout.addWidget(self.textInputBox,0,1)

        return self.questionframe