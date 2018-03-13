# -*- coding: utf-8 -*-
import os


def open_file(filename):
    """Open file and return string"""
    file = open(filename)
    characters = file.read()
    file.close()
    return characters
