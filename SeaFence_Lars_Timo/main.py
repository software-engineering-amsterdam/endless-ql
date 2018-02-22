import sys
from antlr4 import *
from antlr_files_2.QLLexer import QLLexer
from antlr_files_2.QLParser import QLParser
from antlr_files_2.QLVisitor import QLVisitor
from Gui import Gui

def main(argv):
    # input = FileStream(argv[1])
    # lexer = QLLexer(input)
    # stream = CommonTokenStream(lexer)
    # parser = QLParser(stream)

    # tree = parser.form()

    # visitor = QLVisitor()
    # visitor.visit(tree)
    
    # print(tree.toStringTree(recog=parser))
    gui = Gui()
    gui.addLabel("hey", "jaja")
    gui.addCheckBox("yes")
    gui.addSlider("slider", 0, 100, 'horizontal')
    gui.addSpinBox("spinbox", 0, 100)
    gui.addTextBox("text", 3, 30)
    gui.addRadioButton("radio", "ben je dik:", 2)
    gui.showWindow()

if __name__ == '__main__':
    main(sys.argv)