# -*- coding: utf-8 -*-
"""
Documentation goes here:

to run:
$ python run_app.py
"""
import argparse
import os
import sys

from grammar.run_antlr import run_antlr_parse_gen
from commons.config import config
from grammar.debug_grammar import GrammarDebugger
from gui.gui import *


def main():
    """
    Main program
    """
    # CLI
    parser = argparse.ArgumentParser(description='Python Questionnaire Language')
    parser.add_argument('-v', '--version', action='store_true',
                        help="Prints the program version.")
    parser.add_argument('-t', '--test', action='store_true',
                        help="Runs the testsuite.")
    parser.add_argument('-g', '--grammar', action='store_true',
                        help='Debug grammar.')
    parser.add_argument('-p', '--parser', action='store', type=str, choices=['ql', 'QL', 'qls', 'QLS'],
                        help='Generate parser.')

    args = parser.parse_args()

    # Run version
    if args.version:
        print('{} {}'.format(config['program']['name'], config['program']['version']))
        sys.exit(0)

    # Run testsuite
    if args.test:
        os.system("pytest -vv")
        sys.exit(0)

    # Debug grammar
    if args.grammar:
        # todo: make it so that you give path in CLI call + cleanup GrammerDebugger
        g_debug = GrammarDebugger("tests/forms/ql/fail/arithmetic.ql")
        report, errors = g_debug.debug_grammar()
        sys.exit(0)

    # Generate antlr parser
    if args.parser:
        run_antlr_parse_gen(args.parser)
        sys.exit(0)

    # GUI
    app = QtWidgets.QApplication(sys.argv)
    screen = MainWindow()
    screen.show()

    sys.exit(app.exec_())


if __name__ == '__main__':
    main()
