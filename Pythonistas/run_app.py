# -*- coding: utf-8 -*-
"""
Documentation goes here:

to run give file as arg, example:
$ python run_app.py forms/simple.ql
"""
import logging
import argparse
import sys
from commons.config import config
from commons.logging import logging_basic_config
from commons.utility import open_file
from parse.ql_parser import *

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

    args = parser.parse_args()

    # logging
    logging_basic_config(args.log_level.upper())

    if args.version:
        print('{} {}'.format(config['program']['name'], config['program']['version']))
        sys.exit(0)

    logger.info('Started {} {}'.format(config['program']['name'], config['program']['version']))

    # openfile
    file = open_file(args.file_name)

    # lexer
    tokens = ql_lex(file)
    print(tokens)
    print('\n')

    # parse & ast
    result = ql_parser(tokens)
    print(result)

    # todo: maybe to a json inbetween to have a good look at ast instead of cli

    # static checker
    #   - https://github.com/titusjan/astviewer
    # expression eval
    # gui
    sys.exit()


if __name__ == '__main__':
    main()
