import antlr4
# from antlr4 import *
from processed.antlr.QLLexer import QLLexer
from processed.antlr.QLParser import QLParser


# todo: delete processed/antlr after computation
def run_antrl(input, isFile = True):
    if isFile:
        QLinput = antlr4.FileStream(input)
    else:
        QLinput = antlr4.InputStream(input)
    lexer = QLLexer(QLinput)
    stream = antlr4.CommonTokenStream(lexer)
    parser = QLParser(stream)
    return parser.form()


# todo: create import only if exsist
def module_exists(module_name):
    try:
        __import__(module_name)
    except ImportError:
        return False
    else:
        return True
