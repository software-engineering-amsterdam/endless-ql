import sys
from antlr4 import *
from QLLexer import QLLexer
from QLParser import QLParser
# from HtmlQLListener import HtmlQLListener


def main(argv):
    input = FileStream(argv[1])
    lexer = QLLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLParser(stream)
    tree = parser.form()
    print(tree)

    # output = open("output.html", "w")

    # htmlQL = HtmlQLListener(output)
    # walker = ParseTreeWalker()
    # walker.walk(htmlQL, tree)

    # output.close()

if __name__ == '__main__':
    main(sys.argv)
