import antlr4
import os
import sys

from commons.config import config
from parser_generator.grammar.QLLexer import QLLexer
from parser_generator.grammar.QLParser import QLParser


def run_antlr_parse_gen():
    if sys.platform == 'win32':
        # todo: make sure classpath always works
        os.system('SET CLASSPATH=.;C:\\Javalib\\antlr-4.7.1-complete.jar;')
        os.system(
            'java org.antlr.v4.Tool -Dlanguage=Python3 -visitor')  # .format('/usr/local/lib/antlr-4.7.1-complete.jar',
        #           config['antlr']['directory']))

    else:
        os.system('java -jar {} -Dlanguage=Python3 grammar/QL.g4 -o {} -visitor'.format(
            '/usr/local/lib/antlr-4.7.1-complete.jar', config['antlr']['directory']))


# todo: delete processed/antlr after computation?
def run_antrl(txt_input):
    ql_input = antlr4.InputStream(txt_input)
    lexer = QLLexer(ql_input)
    stream = antlr4.CommonTokenStream(lexer)
    parser = QLParser(stream)
    return parser.form()
