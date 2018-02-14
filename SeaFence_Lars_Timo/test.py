import sys
from antlr4 import *
from QLLexer import QLLexer
from QLParser import QLParser

def main(argv):
    input = FileStream(argv[1])
    lexer = QLLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLParser(stream)

    tree = parser.form()
    print(tree.toStringTree(recog=parser))

if __name__ == '__main__':
    main(sys.argv)