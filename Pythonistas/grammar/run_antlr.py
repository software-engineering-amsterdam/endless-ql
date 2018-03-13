import antlr4
import os
import subprocess
import sys

from commons.config import config
from parser_generator.grammar.QLLexer import QLLexer
from parser_generator.grammar.QLParser import QLParser


def run_antlr_parse_gen():
    if sys.platform == 'win32':
        pass
        # todo: make sure classpath always works
        # os.system('SET CLASSPATH=.;C:\\Javalib\\antlr-4.7.1-complete.jar;')
        # os.system('java org.antlr.v4.Tool C:\\Javalib\\antlr-4.7.1-complete.jar -Dlanguage=Python3 grammar/QL.g4 -o "parser_generator" -visitor')
        # os.system('java -jar C:\\Javalib\\antlr-4.7.1-complete.jar -Dlanguage=Python3 grammar/QL.g4 -o "parser_generator" -visitor')

    else:
        # subprocess.run(["java", "-jar", "/usr/local/lib/antlr-4.7.1-complete.jar", "-Dlanguage=Python3",
        #                 "grammar/QL.g4", "-o", "{}".format(config['antlr']['directory']), "-visitor"], shell=True)
        # subprocess.run(["ls", "-l"])
        # subprocess.check_output(['pwd'])
        # subprocess.run('java -jar {} -Dlanguage=Python3 grammar/QL.g4 -o {} -visitor'.format(
        #     '/usr/local/lib/antlr-4.7.1-complete.jar', config['antlr']['directory']), shell=True)
        os.system('java -jar {} -Dlanguage=Python3 grammar/QL.g4 -o {} -visitor'.format(
            '/usr/local/lib/antlr-4.7.1-complete.jar', config['antlr']['directory']))


# todo: delete processed/antlr after computation?
def run_antrl(txt_input):
    ql_input = antlr4.InputStream(txt_input)
    lexer = QLLexer(ql_input)
    stream = antlr4.CommonTokenStream(lexer)
    parser = QLParser(stream)
    return parser.form()
