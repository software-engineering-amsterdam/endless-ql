import sys
import os

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)) + '/../')

from grammar.debug_grammar import GrammarDebugger


def test_all_pass_forms():
    """ Iterates through all the passing forms directory and finds grammar errors"""
    path = 'tests/forms/ql/pass/'

    for form in os.listdir(path):
        if form.endswith(".ql"):
            g_debug = GrammarDebugger(path + form)
            report, err = g_debug.debug_grammar()

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
            g_debug = GrammarDebugger(path + form)
            report, err = g_debug.debug_grammar()

            if not err:
                print("Form should fail but didn't: {}".format(form))
                print(err, report)
                assert False
        else:
            assert True
