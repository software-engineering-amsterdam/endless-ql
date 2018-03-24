from PyQt5 import QtWidgets, QtCore
import os, sys, os.path
sys.path.insert(1, os.path.join(sys.path[0], '..'))
from gui import gui, input_frame, output_frame
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
    screen.add_submit_button()
    screen.set_output_path("tests/QL_output.txt")

    qtbot.mouseClick(screen.submit_button, QtCore.Qt.LeftButton)

    exists = os.path.exists("tests/QL_output.txt")
    assert exists


def test_main_window(qtbot):
    # todo: verify usefulness of this test
    app = QtWidgets.QApplication(sys.argv)
    screen = gui.MainWindow()
    screen.show()

    screen.initiate_output_frame()
    screen.close()

    assert True



# def test_gui(qtbot):
#     app = QtWidgets.QApplication(sys.argv)
#     screen = gui.MainWindow()
#     screen.show()
#
#     file = open("QL_output.txt","w")
#     file.write("hi")
#     file.close()
#
#     screen.ql_input.setText('form Box1HouseOwning {\
#     "Did you sell a house in 2010?" hasSoldHouse: boolean\
#     "Did you by a house in 2010?" hasBoughtHouse: boolean\
#     "Did you enter a loan for maintenance/reconstruction?" hasMaintLoan: boolean\
# }')
#     qtbot.mouseClick(screen.parse_button, QtCore.Qt.LeftButton)
#     qtbot.mouseClick(screen.submit_button, QtCore.Qt.LeftButton)
#     qtbot.mouseClick(screen.quit_button, QtCore.Qt.LeftButton)
#
#     file = open("QL_output.txt")
#     testtext = file.read()
#     file.close()
#     # os.remove("QL_output.txt")
#
#     assert testtext == 'Did you sell a house in 2010?undefined\nDid you by a house in 2010?undefined\nDid you enter a loan for maintenance/reconstruction?undefined\n'
#
