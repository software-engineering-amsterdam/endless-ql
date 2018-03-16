from PyQt5 import QtWidgets, QtCore
import os, sys, os.path
sys.path.insert(1, os.path.join(sys.path[0], '..'))
from gui import gui, input_frame, output_frame
from pytestqt import qtbot


def test_input_frame(qtbot):
    app = QtWidgets.QApplication(sys.argv)
    screen = input_frame.InputFrame()
    screen.show()

    qtbot.mouseClick(screen.parsebutton, QtCore.Qt.LeftButton)
    qtbot.mouseClick(screen.quitbutton, QtCore.Qt.LeftButton)
    # print(screen.isVisible())
    assert screen.isVisible() == False

def test_output_frame(qtbot):
    app = QtWidgets.QApplication(sys.argv)
    screen = output_frame.OutputFrame()
    screen.show()
    screen.add_submit_button()
    screen.set_output_path("tests/QL_output.txt")

    qtbot.mouseClick(screen.submit_button, QtCore.Qt.LeftButton)

    exists = os.path.exists("tests/QL_output.txt")
    os.remove("tests/QL_output.txt")
    assert exists

def test_main_window(qtbot):
    # todo: verify usefulness of this test
    app = QtWidgets.QApplication(sys.argv)
    screen = gui.MainWindow()
    screen.show()

    screen.initiate_outputFrame()
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
#     screen.qlInput.setText('form Box1HouseOwning {\
#     "Did you sell a house in 2010?" hasSoldHouse: boolean\
#     "Did you by a house in 2010?" hasBoughtHouse: boolean\
#     "Did you enter a loan for maintenance/reconstruction?" hasMaintLoan: boolean\
# }')
#     qtbot.mouseClick(screen.parsebutton, QtCore.Qt.LeftButton)
#     qtbot.mouseClick(screen.submit_button, QtCore.Qt.LeftButton)
#     qtbot.mouseClick(screen.quitbutton, QtCore.Qt.LeftButton)
#
#     file = open("QL_output.txt")
#     testtext = file.read()
#     file.close()
#     # os.remove("QL_output.txt")
#
#     assert testtext == 'Did you sell a house in 2010?undefined\nDid you by a house in 2010?undefined\nDid you enter a loan for maintenance/reconstruction?undefined\n'
#

# # -*- coding: utf-8 -*-
# """
# Documentation goes here:
#
# to run:
# $ python run_app.py
# """
# import argparse
# import os
# import sys
#
# from grammar.run_antlr import run_antlr_parse_gen
# from commons.config import config
# from grammar.debug_grammar import GrammarDebugger
# from gui.gui import *
#
#
# def test():
#     """
#     Main program
#     """
#     # CLI
#     parser = argparse.ArgumentParser(description='Python Questionnaire Language')
#     parser.add_argument('-v', '--version', action='store_true',
#                         help="Prints the program version.")
#     parser.add_argument('-t', '--test', action='store_true',
#                         help="Runs the testsuite.")
#     parser.add_argument('-g', '--grammar', action='store_true',
#                         help='Debug grammar.')
#     parser.add_argument('-p', '--parser', action='store', type=str, choices=['ql', 'QL', 'qls', 'QLS'],
#                         help='Generate parser.')
#
#     args = parser.parse_args()
#
#     # Run version
#     if args.version:
#         print('{} {}'.format(config['program']['name'], config['program']['version']))
#         sys.exit(0)
#
#     # Run testsuite
#     if args.test:
#         os.system("pytest -vv")
#         sys.exit(0)
#
#     # Debug grammar
#     if args.grammar:
#         # todo: make it so that you give path in CLI call + cleanup GrammerDebugger
#         g_debug = GrammarDebugger("tests/forms/ql/pass/arithmetic.ql")
#         report, errors = g_debug.debug_grammar()
#         sys.exit(0)
#
#     # Generate antlr parser
#     if args.parser:
#         run_antlr_parse_gen(args.parser)
#         sys.exit(0)
#
#     # GUI
#     app = QtWidgets.QApplication(sys.argv)
#     screen = MainWindow()
#     screen.show()
#
#     sys.exit(app.exec_())
#
#
# if __name__ == '__main__':
#     main()
