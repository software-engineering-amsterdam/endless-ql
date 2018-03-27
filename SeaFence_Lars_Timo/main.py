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
from QLTypeChecker import QLTypeChecker
from QLSTypeChecker import QLSTypeChecker
from Tkinter import *
from ttk import *

def main(argv):
    input = FileStream(argv[1])
    ql_lexer = QLLexer(input)
    ql_stream = CommonTokenStream(ql_lexer)
    ql_parser = QLParser(ql_stream)

    ql_tree = ql_parser.form()

    ql_visitor = QLVisitorHelper()
    ql_ast = ql_visitor.visit(ql_tree)

    ql_checker = QLTypeChecker()
    ql_checker.startQLTypeCheck(ql_ast.statements)

    input = FileStream(argv[2])
    qls_lexer = QLSLexer(input)
    qls_stream = CommonTokenStream(qls_lexer)
    qls_parser = QLSParser(qls_stream)

    qls_tree = qls_parser.stylesheet()

    qls_visitor = QLSVisitorHelper()
    qls_ast = qls_visitor.visit(qls_tree)
    print qls_ast
    
    if len(argv) == 3:
        input = FileStream(argv[2])
        qls_lexer = QLSLexer(input)
        qls_stream = CommonTokenStream(qls_lexer)
        qls_parser = QLSParser(qls_stream)

        qls_tree = qls_parser.stylesheet()

        qls_visitor = QLSVisitorHelper()
        qls_ast = qls_visitor.visit(qls_tree)

        qls_checker = QLSTypeChecker()
        qls_checker.startQLSTypeCheck(ql_ast.statements, qls_ast.pages)

    builder = GuiBuilder(ql_ast, qls_ast)
    builder.gui.window.mainloop()

if __name__ == '__main__':
    main(sys.argv)