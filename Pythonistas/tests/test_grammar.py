import sys
import os
import pytest

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)) + '/../')

from grammar.debug_grammar import GrammarDebugger


def test_all_ql_pass_forms():
    """ Iterates through all the passing ql forms directory and finds grammar errors"""
    path = 'tests/forms/ql/pass/'

    for form in os.listdir(path):
        if form.endswith(".ql"):
            print(form)
            g_debug = GrammarDebugger(path + form)
            g_debug.debug_grammar()
        else:
            assert True


def test_all_ql_fail_forms():
    """ Iterates through all the failing ql forms directory and finds grammar errors"""
    path = 'tests/forms/ql/fail/'

    for form in os.listdir(path):
        if form.endswith(".ql"):
            print(form)
            g_debug = GrammarDebugger(path + form)
            with pytest.raises(Exception) as e:
                g_debug.debug_grammar()
        else:
            assert True


def test_all_qls_pass_forms():
    """ Iterates through all the passing ql+qls forms directory and finds grammar errors"""
    path = 'tests/forms/qls/pass/'

    for form in os.listdir(path):
        if form.endswith(".qls"):
            print(form)
            g_debug = GrammarDebugger(path + form)
            g_debug.debug_grammar()
        else:
            assert True


def test_all_qls_fail_forms():
    """ Iterates through all the passing ql+qls forms directory and finds grammar errors"""
    path = 'tests/forms/qls/fail/'

    for form in os.listdir(path):
        if form.endswith(".qls"):
            print(form)
            g_debug = GrammarDebugger(path + form)
            with pytest.raises(Exception) as e:
                g_debug.debug_grammar()
        else:
            assert True
