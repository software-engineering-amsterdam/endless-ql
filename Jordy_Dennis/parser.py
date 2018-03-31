# Jordy Bottelier
# Dennis Kruidenberg

import logging

from antlr4 import *
from antlr4.InputStream import InputStream
from antlr4.error.ErrorListener import ErrorListener

from GUI import *
from Grammar.QLGrammarLexer import QLGrammarLexer
from Grammar.QLGrammarParser import QLGrammarParser
from Grammar.QLSGrammarLexer import QLSGrammarLexer
from Grammar.QLSGrammarParser import QLSGrammarParser
from Visitors.qlVisitor import QLVisitor
from Visitors.qlsVisitor import QLSVisitor


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
    logging.basicConfig(level=logging.ERROR)
    logger = logging.getLogger(__name__)

    # QL
    if len(argv) > 1:
        input_file = argv[1]
    else:
        print("Please specify a file to parse")
        exit(1)

    # parse input file
    input = FileStream(input_file)
    lexer = QLGrammarLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLGrammarParser(stream)
    parser._listeners = [MyErrorListener()]

    # visit the parse tree
    qlTree = parser.form()
    qlVisitor = QLVisitor()
    qlVisitor.visit(qlTree)

    # Generate and validate ast
    ast = qlVisitor.getAst()
    ast.linkVars()
    ast.checkTypes()

    # QLS
    if len(argv) > 2:
        input_file = argv[2]

        # parse input file
        input = FileStream(input_file)
        lexer = QLSGrammarLexer(input)
        stream = CommonTokenStream(lexer)
        parser = QLSGrammarParser(stream)
        parser._listeners = [MyErrorListener()]

        # visit parse tree
        qlsTree = parser.stylesheet()
        qlsVisitor = QLSVisitor()
        qlsVisitor.visit(qlsTree)

        qlsAST = qlsVisitor.stylesheet
        qlsAST.prepareAndCheckAst(ast.getVarDict())

        # setup GUI
        Gui(ast, qlsVisitor.stylesheet)
    else:
        # setup GUI
        Gui(ast, None)


if __name__ == '__main__':
    main(sys.argv)
