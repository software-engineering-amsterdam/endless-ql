# -*- coding: utf-8 -*-
"""
Documentation goes here:

to run give file as arg, example:
$ python run_app.py forms/simple.ql
"""
import os
import argparse
import sys

from commons.config import config
from commons.utility import open_file
from commons.logging import *
from parse.ql_parser import *


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

    logger.debug('Started {} {}'.format(config['program']['name'], config['program']['version']))

    # openfile
    file = open_file(args.file_name)

    # lexer
    tokens = ql_lex(file)
    logger.debug('Tokens found: {}'.format(tokens))

    # parse & ast
    result = ql_parser(tokens)
    logger.debug('Result AST: {}'.format(result))

    # todo: maybe to a json inbetween to have a good look at ast instead of cli
    # view(source_code=result)
    # static checker
    #   - https://github.com/titusjan/astviewer
    # expression eval

    # gui
    # gui.buildWidget(result)
    #
    # sys.exit()


if __name__ == '__main__':
    main()
