import sys
from antlr4 import *
from pyql.antlr.QLLexer import QLLexer
from pyql.antlr.QLParser import QLParser
from pyql.ast import 
from pyql.ast. import TypeChecker


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
    vv = TypeChecker()
    c.accept(vv)
    print(c)


if __name__ == '__main__':
    main(sys.argv)
