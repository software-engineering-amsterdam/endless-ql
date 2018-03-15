import sys
from antlr4 import *
from pyql.antlr.QLLexer import QLLexer
from pyql.antlr.QLParser import QLParser
from pyql.ast.parse_tree_visitor import ParseTreeVisitor
from pyql.static_analysis.static_checker import StaticChecker

from pyql.static_analysis.symbol_table import SymbolTable

from pyql.gui.gui_visitor import GUIVisitor


def main(argv):
    input = FileStream(argv[1])
    lexer = QLLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLParser(stream)
    parse_tree = parser.form()

    visitor = ParseTreeVisitor()
    ast = parse_tree.accept(visitor)

    sc = StaticChecker()
    sc.run(ast)

    stb = SymbolTable()

    GUIVisitor(ast, stb)


if __name__ == '__main__':
    main(sys.argv)
