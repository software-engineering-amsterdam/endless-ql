"""
This file contains the main window for a Questionnaire Language (QL) parser GUI. The MainWindow contains an input_frame,
which in turn contains a textbox for entering QL text, and a "Parse" button. When this button is pressed, the text is
parsed and the encoded questionnaire is opened in an output_frame in the MainWindow. This questionnaire is interactive,
and the entered answers may be saved to a .txt file by pressing the "Submit" button.
"""
from visitor.ql_visitor import visit_ql
from visitor.qls_visitor import visit_qls
from PyQt5 import QtWidgets, QtCore
from antlr.parser import ParserInterface
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

        # Initiates inner frames
        self.input_frame = InputFrame()
        self.output_frame = OutputFrame()
        self.main_layout.addWidget(self.input_frame, alignment=QtCore.Qt.AlignLeft)
        self.main_layout.addWidget(self.output_frame, alignment=QtCore.Qt.AlignRight)

        # Connect btn with parsing
        self.input_frame.parse_is_pressed.connect(self.parse)

    def initiate_output_frame(self, question_ids=list(), questions=None, warning=None, errors=None):
        """ Reinitialize output frame """
        self.output_frame.setParent(None)
        self.output_frame.destroy()

        self.output_frame = OutputFrame(question_ids, questions, warning, errors)
        self.main_layout.addWidget(self.output_frame, alignment=QtCore.Qt.AlignRight)

    def parse(self, ql_text, qls_text):
        """ Parse the GUI user input """
        if ql_text:
            # Init & traverse QL AST
            ql_data = ParserInterface(ql_text, 'QL')

            if ql_data.errors:
                self.initiate_output_frame(errors=ql_data.errors)
                return
            [question_ids, questions, error_message, warning_message] = visit_ql(ql_data.ast)

            if qls_text:
                # Init & traverses QLS AST
                qls_data = ParserInterface(qls_text, 'QLS')

                if qls_data.errors:
                    self.initiate_output_frame(errors=qls_data.errors)
                    return
                error_message = visit_qls(qls_data.ast, question_ids, questions)

            # The output_frame is initialized and appropriately filled with questions and their answering tools.
            self.initiate_output_frame(question_ids, questions, warning_message, error_message)

        else:
            self.initiate_output_frame(errors="Error: QL input missing")
