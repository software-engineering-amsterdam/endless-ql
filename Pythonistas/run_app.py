# -*- coding: utf-8 -*-
"""
Documentation goes here
"""
import logging
import ast
import sys
from lexer.ql_lexer import ql_lex


logger = logging.getLogger(__name__)


def main(filename):
    # openfile
    file = open_file(filename)

    # lexer
    tokens = ql_lex(file)
    print(tokens)

    # parse
    # create ast
    # static checker
    # expression eval
    # gui
    sys.exit()


def open_file(filename):
    file = open(filename)
    characters = file.read()
    file.close()
    return characters


def tests():
    # write
    pass


if __name__ == '__main__':
    # to run give file as arg
    # python run_app.py util/hello.imp
    filename = sys.argv[1]
    main(filename)
