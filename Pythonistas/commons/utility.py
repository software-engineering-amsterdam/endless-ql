# -*- coding: utf-8 -*-


def open_file(filename):
    file = open(filename)
    characters = file.read()
    file.close()
    return characters
