import antlr4
import os
import subprocess
import sys

from commons.config import config
from parser_generator.grammar.QLLexer import QLLexer
from parser_generator.grammar.QLParser import QLParser
from antlr4.error.ErrorListener import ErrorListener


def run_antlr_parse_gen(grammar):
    if sys.platform == 'win32':
        # todo: translate cmd for windows
        pass
    else:
        os.system('java -jar {} -Dlanguage=Python3 grammar/{}.g4 -o {} -visitor'.format(
            '/usr/local/lib/antlr-4.7.1-complete.jar', grammar, config['antlr']['directory']))


# todo: delete processed/antlr after computation?
def run_antlr(txt_input):
    ql_input = antlr4.InputStream(txt_input)
    lexer = QLLexer(ql_input)
    stream = antlr4.CommonTokenStream(lexer)
    parser = QLParser(stream)
    parser._listeners = [MyErrorListener()]
    return parser.form()


class MyErrorListener(ErrorListener):
    """ Uses Errorlistener from antlr4 """

    def __init__(self):
        super(MyErrorListener, self).__init__()

    def syntaxError(self, recognizer, offending_symbol, line, column, msg, e):
        raise Exception(f"SyntaxError: { recognizer } - { line } - { msg } - { e }")

    def reportAmbiguity(self, recognizer, dfa, start_index, stop_index, exact, ambig_alts, configs):
        raise Exception(f"Ambiguity: { recognizer } - { start_index } - { stop_index } - { exact }")

    def reportAttemptingFullContext(self, recognizer, dfa, start_index, stop_index, conflicting_alts, configs):
        raise Exception(f"FullContextAttempt: { recognizer } - { start_index } - { stop_index } - { conflicting_alts }")

    def reportContextSensitivity(self, recognizer, dfa, start_index, stop_index, prediction, configs):
        raise Exception(f"ContextSensitivity: { recognizer } - { start_index } - { stop_index } - { prediction }")
