# -*- coding: utf-8 -*-
import os
import sys
from commons.config import config


def open_file(filename):
    """Open file and return string"""
    file = open(filename)
    characters = file.read()
    file.close()
    return characters


def remove_char(string, n):
    """ Removes nth char from string"""
    first_part = string[:n]
    last_part = string[n + 1:]
    return first_part + last_part


def run_antlr_parse_gen(grammar):
    if sys.platform == 'win32':
        # todo: translate cmd for windows
        pass
    else:
        os.system('java -jar {} -Dlanguage=Python3 grammar/{}.g4 -o {} -visitor'.format(
            '/usr/local/lib/antlr-4.7.1-complete.jar', grammar, config['antlr']['directory']))
