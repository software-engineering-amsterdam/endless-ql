import sys
import os
import pytest
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)) + '/../')

from pathlib import Path
from commons.utility import open_file
from tests.debug_grammar import compile_run_debug


def test_all_pass_forms():
    """ Iterates through all the passing forms directory and finds grammar errors"""
    path = 'tests/forms/ql/pass/'

    for form in os.listdir(path):
        if form.endswith(".ql"):
            string_input = open_file(path + form)
            report, err = compile_run_debug(string_input)

            if err:
                print("Form that failed: {}".format(form))
                print(err, report)
                assert False
        else:
            assert True


def test_all_fail_forms():
    """ Iterates through all the failing forms directory and finds grammar errors"""
    path = 'tests/forms/ql/fail/'

    for form in os.listdir(path):
        if form.endswith(".ql"):
            string_input = open_file(path + form)
            report, err = compile_run_debug(string_input)

            if not err:
                print("Form should fail but didn't: {}".format(form))
                print(err, report)
                assert False
        else:
            assert True
