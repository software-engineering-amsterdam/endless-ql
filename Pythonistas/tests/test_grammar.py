import sys
import os
import pytest
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)) + '/../')

from tests.debug_grammar import debug_grammar


def test_all_forms():
    """ Iterates through all the forms directory and finds grammar errors"""
    for forms in os.listdir('tests/forms/'):
        if forms.endswith(".ql"):
            report = debug_grammar("tests/forms/arithmetic.ql")
            if "line" in report:
                print(report)
                assert False
        # todo: Make a folder with forms that should fail
        # elif forms.endswith('fails.ql'):

        else:
            assert True
