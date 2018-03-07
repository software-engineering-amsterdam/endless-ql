# Jordy Bottelier
# Dennis Kruidenberg

# Needed variable declarations
grammarName = "QLGrammar"
pythonVersion = "Python3"
destinationFolder = "LexParser"

import sys
import pprint
from antlr4 import *
from parse_grammar import main_parser
from question_generator import Question_Generator

# Generate the lexer and parser for the grammar
main_parser(grammarName, pythonVersion, destinationFolder)

# Import the generated files
from visitor import Visitor
from LexParser.QLGrammarLexer import QLGrammarLexer
from LexParser.QLGrammarParser import QLGrammarParser
from antlr4.error.ErrorListener import ErrorListener
from antlr4.InputStream import InputStream

# https://stackoverflow.com/questions/32224980/python-2-7-antlr4-make-antlr-throw-exceptions-on-invalid-input
class MyErrorListener(ErrorListener):

    def __init__(self):
        super(MyErrorListener, self).__init__()

    def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
        raise Exception("SyntaxError: " + msg + " at line: " + str(line))

def printDict(dic):
    pp = pprint.PrettyPrinter(indent=4)
    pp.pprint(dic)


def getAstFromString(input):
    input_stream = InputStream(input)
    lexer = QLGrammarLexer(input_stream)
    stream = CommonTokenStream(lexer)
    parser = QLGrammarParser(stream)
    tree = parser.form()

    visitor = Visitor()
    visitor.visit(tree)

    ast = visitor.getAst()
    return ast


def main(argv):
    input = FileStream(argv[1])
    lexer = QLGrammarLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLGrammarParser(stream)
    parser._listeners = [MyErrorListener()]
    tree = parser.form()

    # pass tree to visitor
    visitor = Visitor()
    visitor.visit(tree)
    # print(visitor.QLAst)
    ast = visitor.getAst()
    varDict = ast.linkVars()
    # printDict(varDict)
    ast.checkTypes()
    qg = Question_Generator(varDict, ast)



if __name__ == '__main__':
    main(sys.argv)
