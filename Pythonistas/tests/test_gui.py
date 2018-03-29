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

    screen.output_path = "tests/QL_output.txt"
    screen.write_to_txt()

    exists = os.path.exists("tests/QL_output.txt")
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
    ql_text = utility.open_file(r"tests/forms/ql/pass/three_questions.ql")
    screen.parse(ql_text)
    assert True

# def test_manual_debug():
#     app = QtWidgets.QApplication(sys.argv)
#     screen = gui.MainWindow()
#     screen.show()
#
#     sys.exit(app.exec_())