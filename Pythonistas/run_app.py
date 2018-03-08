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

    args = parser.parse_args()

    # Run version
    if args.version:
        print('{} {}'.format(config['program']['name'], config['program']['version']))
        sys.exit(0)

    # Run testsuite
    if args.test:
        os.system("pytest -vv")
        sys.exit(0)

    # Generate antlr parser
    run_antlr_parse_gen()

    # GUI
    app = QApplication(sys.argv)
    screen = MainWindow()
    screen.show()

    sys.exit(app.exec_())


if __name__ == '__main__':
    main()
