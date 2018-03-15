import antlr4
import os
import subprocess
import sys

from commons.config import config
from parser_generator.grammar.QLLexer import QLLexer
from parser_generator.grammar.QLParser import QLParser


def run_antlr_parse_gen(grammar):
    if sys.platform == 'win32':
        # todo: translate cmd for windows
        pass
    else:
        os.system('java -jar {} -Dlanguage=Python3 grammar/{}.g4 -o {} -visitor'.format(
            '/usr/local/lib/antlr-4.7.1-complete.jar', grammar.upper(), config['antlr']['directory']))


# todo: delete processed/antlr after computation?
def run_antlr(txt_input):
    ql_input = antlr4.InputStream(txt_input)
    lexer = QLLexer(ql_input)
    stream = antlr4.CommonTokenStream(lexer)
    parser = QLParser(stream)
    return parser.form()
