from PyQt5.QtWidgets import *
import sys
from pytest import *
from gui import gui

def testfunc():
    app = QApplication(sys.argv)
    screen = gui.InputWindow()
    screen.show()

    file = open("output.txt","w")
    file.write("hi")
    file.close()

    screen.qlInput.setText("form Box1HouseOwning {\n hasSoldHouse: \"Did you sell a house in 2010?\" boolean\n hasBoughtHouse: \"Did you by a house in 2010?\" boolean\n hasMaintLoan: \"Did you enter a loan for maintenance/reconstruction?\" boolean\n}")
    screen.parse()
    screen.output.submit()

    file = open("output.txt")
    testtext = file.read()
    file.close()

    assert testtext == '"Did you sell a house in 2010?"undefined\n"Did you by a house in 2010?"undefined\n"Did you enter a loan for maintenance/reconstruction?"undefined\n'

    sys.exit()


testfunc()
