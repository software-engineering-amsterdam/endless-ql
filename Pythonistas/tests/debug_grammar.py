import sys
from commons.utility import open_file
import subprocess


def debug_grammar(path):
    """ Prints tokens and tree """
    string_input = open_file(path)
    output = subprocess.check_output("""
    cd tests/antlr4
    ./antlr4-tester.sh QL.g4 {} form
    """.format(repr(string_input)), shell=True, executable='/bin/bash', universal_newlines=True)
    sys.stdout.write(output)
