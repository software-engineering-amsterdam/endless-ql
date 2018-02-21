# -*- coding: utf-8 -*-
"""
Documentation goes here:

to run give file as arg, example:
$ python run_app.py forms/simple.ql
"""
import os
import logging
import argparse
import sys
import ast

from astviewer.main import view
from commons.config import config
from commons.logging import logging_basic_config
from commons.utility import open_file
from parse.ql_parser import *
from gui import gui

logger = logging.getLogger(__name__)


def main():
    """
    Main program
    """
    # CLI
    # todo: link pytest tests with cli arg parser
    parser = argparse.ArgumentParser(description='Python Questionnaire Language')
    parser.add_argument(dest='file_name', help='Python input file', nargs='?')
    parser.add_argument('-l', '--log-level', dest='log_level', default='warn',
                        choices=('debug', 'info', 'warn', 'error', 'critical'),
                        help="Log level. Only log messages with a level higher or equal than this "
                        "will be printed. Default: 'warn'")
    parser.add_argument('-v', '--version', action='store_true',
                        help="Prints the program version.")
    parser.add_argument('-t', '--test', action='store_true',
                        help="Runs the testsuite.")

    args = parser.parse_args()

    # logging
    logging_basic_config(args.log_level.upper())

    # Run version
    if args.version:
        print('{} {}'.format(config['program']['name'], config['program']['version']))
        sys.exit(0)

    # Run testsuite
    if args.test:
        os.system("pytest")
        sys.exit(0)

    logger.info('Started {} {}'.format(config['program']['name'], config['program']['version']))

    # openfile
    file = open_file(args.file_name)
    # file = open("C:/Users/svdh/PycharmProjects/sql/endless-ql/Pythonistas/forms/simple.ql","r")

    # lexer
    tokens = ql_lex(file)
    print(tokens)

    # parse & ast
    result = ql_parser(tokens)
    # return result
    print(result)

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
