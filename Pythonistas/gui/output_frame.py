'''
This file contains the OutputFrame class, for use with the MainWindow class from gui.py. After Questionnaire Language
(QL) is parsed by another widget, OutputFrame will come to contain the encoded questionnaire.
'''
from PyQt5 import QtWidgets


class OutputFrame(QtWidgets.QFrame):
    def __init__(self, questionIDs=[], questions={}):
        super(OutputFrame, self).__init__()
        self.frame_layout = QtWidgets.QVBoxLayout()
        self.setLayout(self.frame_layout)
        # self.row = 0

        self.questionIDs = questionIDs  # Ordered list of question IDs.
        self.questions = questions  # Dictionary with question objects as values, and question IDs as keys
        self.output_path = 'QL_output.txt'
        self.add_questions()

    def get_question_object(self, questionID):
        index = self.questionIDs.index(questionID)
        return self.questions[index]

    def get_output_path(self):
        return self.output_path

    def set_output_path(self, output_path):
        self.output_path = output_path

    def add_submit_button(self):
        self.submit_button = QtWidgets.QPushButton('Submit', self)
        self.submit_button.clicked.connect(self.submit)
        self.frame_layout.addWidget(self.submit_button)

    def submit(self):
        # Writes answers to txt file
        file = open(self.output_path, 'w')
        for ID in self.questionIDs:
            file.write(self.questions[ID].question+str(self.questions[ID].answer)+'\n')
        file.close()

    def add_question(self, question_frame):
        # Adds a frame containing a question string and the answering method to the OutputFrame
        self.frame_layout.addWidget(question_frame)

    def add_questions(self):
        # Gets frames containing question string and answering method for each question, and adds them to OutputFrame
        for ID in self.questionIDs:
            question = self.questions[ID]
            question_frame = question.create_frame()
            self.add_question(question_frame)

    def check_duplicate_question_strings(self):
        question_list = []
        # Compiles a list of all question strings
        for ID in self.questionIDs:
            question = self.questions[ID]
            question_list.append(question.question)

        duplicates = set([duplicate for duplicate in question_list if question_list.count(duplicate) > 1])
        if len(duplicates) > 0:
            warning_string = "Warning: duplicate questions:{}".format(str(duplicates)[1:-1])
            self.frame_layout.addWidget(QtWidgets.QLabel(warning_string))
            # return "Warning: duplicate questions:{}".format(str(duplicates)[1:-1])

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





