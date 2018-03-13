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
from tests.debug_grammar import debug_grammar
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
                        help="Debug grammar.")
    parser.add_argument('-p', '--parser', action='store_true',
                        help="Generate parser.")

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
        # todo: make it so that you give path in CLI call
        # debug_grammar('C:/Users/svdh/PycharmProjects/sql/endless-ql/Pythonistas/tests/forms/if.ql')
        # debug_grammar("tests/forms/if.ql")
        sys.stdout.write(debug_grammar("tests/forms/if.ql"))
        sys.exit(0)

    # Generate antlr parser
    if args.parser:
        run_antlr_parse_gen()
        sys.exit(0)

    # GUI
    app = QApplication(sys.argv)
    screen = MainWindow()
    screen.show()

    sys.exit(app.exec_())


if __name__ == '__main__':
    main()
