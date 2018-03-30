"""
This file contains the OutputFrame class, for use with the MainWindow class from gui.py. After Questionnaire Language
(QL) is parsed by another widget, OutputFrame will come to contain the encoded questionnaire.
"""
from PyQt5 import QtWidgets, QtCore


class OutputFrame(QtWidgets.QFrame):
    def __init__(self, question_ids=list(), questions=None, warning=None, errors=None):
        super(OutputFrame, self).__init__()
        self.frame_layout = QtWidgets.QVBoxLayout()
        self.frame_layout.setAlignment(QtCore.Qt.AlignTop)
        self.setLayout(self.frame_layout)

        self.question_ids = question_ids  # Ordered list of question id's
        self.questions = questions  # {question_id: question object}
        self.output_path = 'QL_output.txt'

        if errors:
            for error in errors:
                self.frame_layout.addWidget(QtWidgets.QLabel(error))
        elif questions:
            self.add_questions()

            submit_button = QtWidgets.QPushButton('Submit', self)
            submit_button.clicked.connect(self.write_to_txt)
            self.frame_layout.addWidget(submit_button, alignment=QtCore.Qt.AlignRight)

            if warning:
                self.frame_layout.addWidget(QtWidgets.QLabel(warning))
        else:
            pass

    def write_to_txt(self):
        """ Writes answers to txt file """
        file = open(self.output_path, 'w')
        for question_id in self.question_ids:
            file.write(self.questions[question_id].question_string+str(self.questions[question_id].answer)+'\n')
        file.close()

    def add_question(self, question_frame):
        """ Adds frame with question and answer string """
        self.frame_layout.addWidget(question_frame, alignment=QtCore.Qt.AlignLeft)

    def add_questions(self):
        """ Aggregate individual question frames """
        for question_id in self.question_ids:
            question = self.questions[question_id]
            question_frame = question.create_frame()
            self.add_question(question_frame)