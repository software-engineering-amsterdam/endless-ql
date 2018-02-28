# -*- coding: utf-8 -*-
import sys
import os


def set_testfile_path():
    my_path = os.path.dirname(os.path.abspath(__file__))
    sys.path.insert(0, my_path + '/../')
