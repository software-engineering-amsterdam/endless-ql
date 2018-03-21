"""
This file contains the main window for a Questionnaire Language (QL) parser GUI. The MainWindow contains an input_frame,
which in turn contains a textbox for entering QL text, and a "Parse" button. When this button is pressed, the text is
parsed and the encoded questionnaire is opened in an output_frame in the MainWindow. This questionnaire is interactive,
and the entered answers may be saved to a .txt file by pressing the "Submit" button.
"""
# import visitor.visitor as visitor_script
from visitor.listener import listen
from PyQt5 import QtWidgets
from PyQt5 import QtCore
from grammar.run_antlr import run_antlr
from grammar.data_structure import ParserCarrier
import sys
from gui.input_frame import InputFrame
from gui.output_frame import OutputFrame


class MainWindow(QtWidgets.QWidget):
    def __init__(self):
        super(MainWindow, self).__init__()
        # Parses QL input
        self.main_layout = QtWidgets.QHBoxLayout()
        self.main_layout.setSpacing(10)
        self.setLayout(self.main_layout)
        self.setWindowTitle('QL parser')
        self.setGeometry(600, 600, 1100, 600)
        self.tree = None
        self.parser = None

        # Initiates frames for within the window, and adds them.
        self.input_frame = InputFrame()
        self.output_frame = OutputFrame()
        self.main_layout.addWidget(self.input_frame)
        self.main_layout.addWidget(self.output_frame)

        # When the signal parse_is_pressed is given by input_frame, MainWindow takes necessary actions to parse
        self.input_frame.parse_is_pressed.connect(self.parse)

    def initiate_output_frame(self,questionIDs=[], questions={}):
        # Removes the old output_frame from the window
        self.output_frame.setParent(None)
        self.output_frame.destroy()

        # Reinitializes output_frame and adds it to the main window
        self.output_frame = OutputFrame(questionIDs, questions)
        self.main_layout.addWidget(self.output_frame)

    def parse(self, ql_text, qls_text):
        ql_data = ParserCarrier()
        if ql_text:
            ql_data.set_ql_grammar_text(ql_text)
            ql_data.run_antlr_ql()
            # if error in tree:
            #   addwidget errormessage

            # The tree is traversed, the questions it contains are collected, as well as the first error encountered.
            [questionIDs, questions, error_message] = listen(ql_data.ql_tree)
            # The output_frame is initialized and appropriately filled with questions and their answering tools.
            self.initiate_output_frame(questionIDs, questions)
            self.output_frame.add_submit_button()

            if error_message:
                self.initiate_output_frame()
                self.output_frame.frame_layout.addWidget(QtWidgets.QLabel(error_message))
            else:
                self.output_frame.check_duplicate_question_strings()

        else:  # todo: if garbage in, this error message out.
            self.output_frame.frame_layout.addWidget(QtWidgets.QLabel("QL input missing"))
            # pass

        if qls_text:
            ql_data.set_qls_grammar_text(ql_text)
            ql_data.run_antlr_qls()
            # todo: create listener/visiter for QLS
            # listen(ql_data.qls_tree, self.output_frame)
            # self.output_frame.add_submit_button()
        else:
            # self.output_frame.no_tree_message()
            pass


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    ex = MainWindow()
    ex.show()
    sys.exit(app.exec_())