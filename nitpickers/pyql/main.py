import sys
from antlr4 import *
from pyql.antlr.QLLexer import QLLexer
from pyql.antlr.QLParser import QLParser
from pyql.ast.visitor import ParseTreeVisitor


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

    # output = open("output.html", "w")

    # htmlQL = HtmlQLListener(output)
    # walker = ParseTreeWalker()
    # walker.walk(htmlQL, tree)

    # output.close()



if __name__ == '__main__':
    main(sys.argv)
