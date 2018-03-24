"""
This file contains the OutputFrame class, for use with the MainWindow class from gui.py. After Questionnaire Language
(QL) is parsed by another widget, OutputFrame will come to contain the encoded questionnaire.
"""
from PyQt5 import QtWidgets, QtCore


class OutputFrame(QtWidgets.QFrame):
    def __init__(self, question_ids=list(), questions=None):
        super(OutputFrame, self).__init__()
        self.frame_layout = QtWidgets.QVBoxLayout()
        self.setLayout(self.frame_layout)
        self.submit_button = None

        self.question_ids = question_ids  # Ordered list of question id's
        self.questions = questions  # {question_id: question object}
        self.output_path = 'QL_output.txt'
        self.add_questions()

    def add_submit_button(self):
        """ Possible after parsing """
        self.submit_button = QtWidgets.QPushButton('Submit', self)
        self.submit_button.clicked.connect(self.write_to_txt)
        self.frame_layout.addWidget(self.submit_button, alignment=QtCore.Qt.AlignRight)

    def write_to_txt(self):
        """ Writes answers to txt file """
        file = open(self.output_path, 'w')
        for question_id in self.question_ids:
            file.write(self.questions[question_id].question+str(self.questions[question_id].answer)+'\n')
        file.close()

    def add_question(self, question_frame):
        """ Adds frame with question and answer string """
        self.frame_layout.addWidget(question_frame, alignment=QtCore.Qt.AlignRight)

    def add_questions(self):
        """ Aggregate individual question frames """
        for question_id in self.question_ids:
            question = self.questions[question_id]
            question_frame = question.create_frame()
            self.add_question(question_frame)

    # def check_duplicate_question_strings(self):
    #     question_list = []
    #     warning_string = None
    #     # Compiles a list of all question strings
    #     for ID in self.questionIDs:
    #         question = self.questions[ID]
    #         question_list.append(question.question)
    #
    #     duplicates = set([duplicate for duplicate in question_list if question_list.count(duplicate) > 1])
    #     if len(duplicates) > 0:
    #         warning_string = "Warning: duplicate questions:{}".format(str(duplicates)[1:-1])
    #         self.frame_layout.addWidget(QtWidgets.QLabel(warning_string))
    #         # return "Warning: duplicate questions:{}".format(str(duplicates)[1:-1])
    #     return warning_string

    def check_cyclic_ifs(self):
        pass  # todo: check cyclic ifs
        # Note: there can be no cyclic ifs due to the linear (one-way) nature of the listener

        # for question in self.questions:
        #     oldlists = [question.questionID]
        #     newlists = []
        #     for list in oldlists:
        #         if_question = self.get_question_object(list[-1])
        #         if_questions = if_question.getifquestions
        #         for if_question in if_questions:
        #             list.append(if_question)
        #             newlists.append(list)
        #             duplicates = set([duplicate for duplicate in list if list.count(duplicate) > 1])
        #             print(duplicates)
        #             if len(duplicates) > 0:
        #                 return "Error: cyclic dependency in if statements"





