import sys
from antlr4 import *
from antlr_files_2.QLLexer import QLLexer
from antlr_files_2.QLParser import QLParser
from antlr_files_2.QLVisitor import QLVisitor
from QLVisitorHelper import QLVisitorHelper
from GuiBuilder import GuiBuilder
from TypeChecker import TypeChecker

def main(argv):
    input = FileStream(argv[1])
    lexer = QLLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLParser(stream)

    tree = parser.form()

    visitor = QLVisitorHelper()

    ast = visitor.visit(tree)
    # print ast

    # checker = TypeChecker(ast)

    builder = GuiBuilder(ast)
    # gui = Gui()
    # gui.addLabel("hey", "jaja")
    # gui.removeLabel("hey")
    # gui.addCheckBox("yes")
    # gui.addSlider("slider", 0, 100, 'horizontal')
    # gui.addSpinBox("spinbox", 0, 100)
    # gui.addTextBox("text", 3, 30)
    # gui.addRadioButton("radio", "ben je dik:", 2)
    # gui.addDropDown("drop", ["1", "2", "3"])
    builder.gui.window.mainloop()

if __name__ == '__main__':
    main(sys.argv)