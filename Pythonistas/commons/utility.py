# -*- coding: utf-8 -*-
import os


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
