# Jordy Bottelier
# Dennis Kruidenberg



import sys
import pprint
from antlr4 import *
from GUI import *
from parse_grammar import generateParsers
import logging


# Generate the lexer and parser for the grammar
generateParsers()

# Import the generated files
from qlVisitor import QLVisitor
from qlsVisitor import QLSVisitor
from LexParser.QLGrammarLexer import QLGrammarLexer
from LexParser.QLGrammarParser import QLGrammarParser
from LexParser.QLSGrammarLexer import QLSGrammarLexer
from LexParser.QLSGrammarParser import QLSGrammarParser
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

    qlVisitor = QLVisitor()
    qlVisitor.visit(tree)

    ast = qlVisitor.getAst()
    return ast


def main(argv):
    # used to log debug self.logger.debugs
    # set to logging.DEBUG to show debug messages, logging.ERROR to not show
    logging.basicConfig(level=logging.DEBUG)
    logger = logging.getLogger(__name__)
    # QL
    if len(argv)>1:
        input_file = argv[1]
        print(input_file)
    else:
        input_file = 'test_ql'
    input = FileStream(input_file)
    lexer = QLGrammarLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLGrammarParser(stream)
    parser._listeners = [MyErrorListener()]
    qlTree = parser.form()


    # pass tree to visitor
    qlVisitor = QLVisitor()
    qlVisitor.visit(qlTree)


    # Get and validate AST -------------------
    ast = qlVisitor.getAst()
    ast.linkVars()
    ast.checkTypes()

    # start up Gui
    Gui(ast, False)


    # QLS
    if len(argv)>2:
        input_file = argv[2]
    else:
        input_file = 'test_qls'
    input = FileStream(input_file)
    lexer = QLSGrammarLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLSGrammarParser(stream)
    parser._listeners = [MyErrorListener()]
    qlsTree = parser.stylesheet()

    # pass tree to visitor
    qlsVisitor = QLSVisitor()
    qlsVisitor.visit(qlsTree)
    # print(qlsTree.toStringTree())


if __name__ == '__main__':
    main(sys.argv)
