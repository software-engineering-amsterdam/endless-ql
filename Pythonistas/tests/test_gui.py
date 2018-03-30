from PyQt5 import QtWidgets, QtCore
from gui import main_window, input_frame, output_frame
import os
import sys
from commons import utility
# sys.path.insert(1, os.path.join(sys.path[0], '..'))
from pytestqt import qtbot


def test_input_frame(qtbot):
    app = QtWidgets.QApplication(sys.argv)
    screen = input_frame.InputFrame()
    screen.show()

    qtbot.mouseClick(screen.parse_button, QtCore.Qt.LeftButton)
    qtbot.mouseClick(screen.quit_button, QtCore.Qt.LeftButton)
    assert screen.isVisible() is False


def test_output_frame(qtbot):
    app = QtWidgets.QApplication(sys.argv)
    screen = output_frame.OutputFrame()
    screen.show()

    screen.output_path = "tests/output_frame_test.txt"
    screen.write_to_txt()

    exists = os.path.exists("tests/output_frame_test.txt")
    assert exists


def test_main_window(qtbot):
    app = QtWidgets.QApplication(sys.argv)
    screen = main_window.MainWindow()
    screen.show()

    screen.initiate_output_frame()
    screen.close()

    assert True


def test_gui(qtbot):
    app = QtWidgets.QApplication(sys.argv)
    screen = main_window.MainWindow()
    screen.show()
    ql_text = """form Box1HouseOwning {
    "Did you sell a house in 2010?" hasSoldHouse: boolean
    "Did you by a house in 2010?" hasBoughtHouse: boolean
    "Did you enter a loan for maintenance/reconstruction?" hasMaintLoan: boolean
}"""
    screen.parse(ql_text,"")
    question_ids = screen.output_frame.question_ids
    questions = screen.output_frame.questions
    questions[question_ids[0]].set_answer_true()
    questions[question_ids[1]].set_answer_false()
    screen.output_frame.output_path = "tests/gui_test.txt"

    screen.output_frame.write_to_txt()
    expected_answer = """"Did you sell a house in 2010?"True
"Did you by a house in 2010?"False
"Did you enter a loan for maintenance/reconstruction?"undefined
"""
    actual_answer = utility.open_file("tests/gui_test.txt")
    assert expected_answer == actual_answer


# def test_manual_debug():
#     app = QtWidgets.QApplication(sys.argv)
#     screen = gui.MainWindow()
#     screen.show()
#
#     sys.exit(app.exec_())
