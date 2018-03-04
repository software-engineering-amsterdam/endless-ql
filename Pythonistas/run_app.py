# -*- coding: utf-8 -*-
"""
Documentation goes here:

to run give file as arg, example:
$ python run_app.py forms/simple.ql
"""
import argparse
import sys
import os

from antlr.run_antlr import run_antrl
from visitor.visitor import *
from commons.config import config
from gui.gui import *


def main():
    """
    Main program
    """
    # CLI
    parser = argparse.ArgumentParser(description='Python Questionnaire Language')
    parser.add_argument(dest='file_name', help='Python input file', nargs='?')
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
        os.system("pytest")
        sys.exit(0)

    # Antlr4
    os.system('java -jar {} -Dlanguage=Python3 antlr/QL.g4 -o {} -visitor'.format(
        '/usr/local/lib/antlr-4.7.1-complete.jar', config['antlr']['directory']))

    tree = run_antrl(args.file_name)
    # print(tree.toStringTree(recog=parser))

    # Visit
    # visit(tree)

    # Gui
    app = QApplication(sys.argv)
    screen = InputWindow(tree)
    screen.show()

    sys.exit(app.exec_())


if __name__ == '__main__':
    main()
