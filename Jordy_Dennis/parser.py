import sys
from antlr4 import *
from LexParser.QLGrammarLexer import QLGrammarLexer
from LexParser.QLGrammarParser import QLGrammarParser

def main(argv):
    input = FileStream(argv[1])
    lexer = QLGrammarLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLGrammarParser(stream)
    tree = parser.form()
    print(tree.toStringTree(recog=parser))

if __name__ == '__main__':
    main(sys.argv)