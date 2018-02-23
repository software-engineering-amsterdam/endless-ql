from PyQt5.QtWidgets import *
import sys
import parse
import ast
from gui import gui
import pytest
import time

def testfunc():
    app = QApplication(sys.argv)
    screen = gui.InputWindow()
    screen.show()

    file = open("testoutput.txt","w")
    file.write("hi")
    file.close()

    screen.qlInput.setText("form Box1HouseOwning \n {hasSoldHouse: \"Did you sell a house in 2010?\" boolean\n hasBoughtHouse: \"Did you by a house in 2010?\" boolean\n hasMaintLoan: \"Did you enter a loan for maintenance/reconstruction?\" boolean\n}")
    print(screen.qlInput.toPlainText())
    screen.parse()

    print(screen.output.answers)                    wa
    print(screen.output.questions)


    file = open("testoutput.txt")
    testtext = file.read()
    file.close()

    print(testtext)

    assert testtext == "hi"
    sys.exit(app.exec_())


testfunc()
