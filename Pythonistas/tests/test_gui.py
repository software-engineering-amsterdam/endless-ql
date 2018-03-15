from PyQt5.QtWidgets import *
from PyQt5 import QtCore
import os,sys
sys.path.insert(1, os.path.join(sys.path[0], '..'))
from gui import gui
from pytestqt import qtbot

# def test_gui(qtbot):
#     app = QApplication(sys.argv)
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