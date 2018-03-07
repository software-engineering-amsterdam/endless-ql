import sys
from antlr4 import *
from pyql.antlr.QLLexer import QLLexer
from pyql.antlr.QLParser import QLParser
from pyql.ast.parse_tree_visitor import ParseTreeVisitor

from pyql.static_analysis.type_check import TypeChecker

from pyql.static_analysis.symbol_table import *


def main(argv):
    input = FileStream(argv[1])
    lexer = QLLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLParser(stream)
    tree = parser.form()
    print(tree)
    b = type(tree)
    visitor = ParseTreeVisitor()
    c = tree.accept(visitor)
    print(c)

    ss = SymbolTableBuilder().build(c)

    vv = TypeChecker()
    c.accept(vv)
    print(c)


if __name__ == '__main__':
    main(sys.argv)
