"""
This file contains the main window for a Questionnaire Language (QL) parser GUI. The MainWindow contains an input_frame,
which in turn contains a textbox for entering QL text, and a "Parse" button. When this button is pressed, the text is
parsed and the encoded questionnaire is opened in an output_frame in the MainWindow. This questionnaire is interactive,
and the entered answers may be saved to a .txt file by pressing the "Submit" button.
"""
from visitor.visitor import visit
from visitor.QLSVisitor import visit_qls
from PyQt5 import QtWidgets, QtCore
from grammar.parser import Parser
import sys
from gui.input_frame import InputFrame
from gui.output_frame import OutputFrame


class MainWindow(QtWidgets.QWidget):
    def __init__(self):
        super(MainWindow, self).__init__()
        self.main_layout = QtWidgets.QHBoxLayout()
        self.main_layout.setSpacing(10)
        self.setLayout(self.main_layout)
        self.setWindowTitle('QL parser')
        self.setGeometry(200, 200, 1000, 500)
        self.tree = None
        self.parser = None

        # Initiates inner frames
        self.input_frame = InputFrame()
        self.output_frame = OutputFrame()
        self.main_layout.addWidget(self.input_frame)
        self.main_layout.addWidget(self.output_frame)

        # Connect btn with parsing
        self.input_frame.parse_is_pressed.connect(self.parse)

    def initiate_output_frame(self, question_ids=list(), questions=None):
        """ Reinitialize output frame """
        self.output_frame.setParent(None)
        self.output_frame.destroy()

        self.output_frame = OutputFrame(question_ids, questions)
        self.main_layout.addWidget(self.output_frame, alignment=QtCore.Qt.AlignRight)

    def parse(self, ql_text, qls_text):
        """ Parse the GUI user input """
        ql_data = Parser()
        qls_data = Parser()

        if ql_text:
            ql_data.set_ql_grammar_text(ql_text)
            ql_data.run_antlr_ql()
            # Traverses QL AST
            [question_ids, questions, error_message, warning_message] = visit(ql_data.ql_tree)

            if qls_text:
                ql_data.set_qls_grammar_text(qls_text)
                ql_data.run_antlr_qls()
                # todo: create listener/visiter for QLS
                # Traverses QLS AST
                x = visit_qls(ql_data.qls_tree, question_ids, questions)

            if error_message:
                self.initiate_output_frame()
                self.output_frame.frame_layout.addWidget(QtWidgets.QLabel(error_message))
                return
            # The output_frame is initialized and appropriately filled with questions and their answering tools.
            self.initiate_output_frame(question_ids, questions)
            self.output_frame.add_submit_button()

            if warning_message:
                self.output_frame.frame_layout.addWidget(QtWidgets.QLabel(warning_message))

        if qls_text:
            qls_data.set_qls_grammar_text(qls_text)
            qls_data.run_antlr_qls()
            # todo: create listener/visiter for QLS
            # listen(ql_data.qls_tree, self.output_frame)
            # self.output_frame.add_submit_button()
        else:
            self.initiate_output_frame()
            self.output_frame.frame_layout.addWidget(QtWidgets.QLabel("QL input missing"))