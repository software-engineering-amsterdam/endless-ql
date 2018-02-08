import sys
from antlr4 import *
from LexParser.QLGrammarLexer import QLGrammarLexer
from LexParser.QLGrammarParser import QLGrammarParser

def main(argv):
    input = FileStream(argv[1])
    lexer = HelloLexer(input)
    stream = CommonTokenStream(lexer)
    parser = HelloParser(stream)
    tree = parser.r()
    print(tree.toStringTree(recog=parser))

if __name__ == '__main__':
    main(sys.argv)