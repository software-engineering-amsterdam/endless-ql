from PyQt5 import QtWidgets


class OutputFrame(QtWidgets.QFrame):
    def __init__(self):
        super(OutputFrame, self).__init__()
        self.outputlayout = QtWidgets.QGridLayout()
        self.setLayout(self.outputlayout)
        self.row = 0
        self.btn_grp = []
        self.questions = []  # Ordered list of questions
        self.answers = []  # Ordered list of corresponding answers

    # def add_question(self, completequestion):  # todo: split question and its datatype in ast rather than here
    #     # Adds questions and answer option
    #
    #     splitquestion = completequestion.split('"')  # Filters the actual question from the input string
    #     question = splitquestion[1]
    #     datatype = splitquestion[2].split(":")[1]  # Filters datatype from the question string
    #
    #     choices = ['Yes', 'No']  # Default choices; todo: move to appropriate location.
    #
    #     self.outputlayout.addWidget(QtWidgets.QLabel(question))
    #     self.questions.append(question)
    #     self.answers.append('undefined')  # Default answer
    #
    #     if datatype == 'boolean':
    #         self.btn_grp.append(QtWidgets.QButtonGroup())  # Makes sure only one radiobutton can be true per question
    #         for choicenumber in range(len(choices)):
    #             radiobutton = QtWidgets.QRadioButton(choices[choicenumber])
    #             radiobutton.answer = choices[choicenumber]
    #             radiobutton.question = question
    #
    #             self.outputlayout.addWidget(radiobutton, self.row, choicenumber+1)
    #             radiobutton.toggled.connect(self.write_answer)
    #
    #             self.btn_grp[-1].setExclusive(True)
    #             self.btn_grp[-1].addButton(radiobutton)
    #
    #     elif datatype == 'money':
    #         # todo:testing
    #         textbox = QtWidgets.QLineEdit(self)
    #         textbox.answer = textbox.text()
    #         textbox.question = question
    #         textbox.textEdited.connect(self.write_answer)
    #         self.outputlayout.addWidget(textbox, self.row, 1)
    #
    #     self.row += 1

    def write_answer(self):
        # Saves the user's answer to the corresponding question
        sender = self.sender()
        try:
            sender.answer = sender.text()
        except:
            pass
        self.answers[self.questions.index(sender.question)] = sender.answer

    def add_submit_button(self):
        self.submit_button = QtWidgets.QPushButton('Submit', self)
        self.submit_button.clicked.connect(self.submit)
        self.submit_button.resize(self.submit_button.sizeHint())
        self.outputlayout.addWidget(self.submit_button,self.row,1)

    def submit(self):
        # Writes answers to txt file
        file = open('QL_output.txt', 'w')

        for i in range(len(self.questions)):
            file.write(self.questions[i]+str(self.answers[i])+"\n")
        file.close()

    def no_tree_message(self):
        self.outputlayout.addWidget(QtWidgets.QLabel('Invalid input'))