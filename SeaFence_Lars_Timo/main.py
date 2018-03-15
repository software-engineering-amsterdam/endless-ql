import sys
from antlr4 import *
from QL.QLLexer import QLLexer
from QL.QLParser import QLParser
from QL.QLVisitor import QLVisitor
from QLVisitorHelper import QLVisitorHelper
from QLS.QLSLexer import QLSLexer
from QLS.QLSParser import QLSParser
from QLS.QLSVisitor import QLSVisitor
from QLSVisitorHelper import QLSVisitorHelper
from GuiBuilder import GuiBuilder
from TypeChecker import TypeChecker

def main(argv):
    input = FileStream(argv[1])
    ql_lexer = QLLexer(input)
    ql_stream = CommonTokenStream(ql_lexer)
    ql_parser = QLParser(ql_stream)

    ql_tree = ql_parser.form()

    ql_visitor = QLVisitorHelper()
    ql_ast = ql_visitor.visit(ql_tree)


    input = FileStream(argv[2])
    qls_lexer = QLSLexer(input)
    qls_stream = CommonTokenStream(qls_lexer)
    qls_parser = QLSParser(qls_stream)

    qls_tree = qls_parser.stylesheet()

    qls_visitor = QLSVisitorHelper()
    qls_ast = qls_visitor.visit(qls_tree)

    # print ast

    # checker = TypeChecker(ql_ast)

    # builder = GuiBuilder(ql_ast)
    # gui = Gui()
    # gui.addLabel("hey", "jaja")
    # gui.removeLabel("hey")
    # gui.addCheckBox("yes")
    # gui.addSlider("slider", 0, 100, 'horizontal')
    # gui.addSpinBox("spinbox", 0, 100)
    # gui.addTextBox("text", 3, 30)
    # gui.addRadioButton("radio", "ben je dik:", 2)
    # gui.addDropDown("drop", ["1", "2", "3"])
    # builder.gui.window.mainloop()

if __name__ == '__main__':
    main(sys.argv)