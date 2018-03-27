import sys
import os
import pytest

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)) + '/../')

from grammar.parser import Parser
from commons.utility import open_file


def test_all_ql_pass_forms():
    """ Iterates through all the passing ql forms directory and finds grammar errors"""
    path = 'tests/forms/ql/pass/'

    for form in os.listdir(path):
        if form.endswith(".ql"):
            print(form)
            file = open_file(path + form)
            parser = Parser()
            parser.set_ql_grammar_text(file)
            parser.run_antlr_ql()
            if parser.ql_errors:
                print(parser.ql_errors)
                assert False
        else:
            assert True


def test_all_ql_fail_forms():
    """ Iterates through all the failing ql forms directory and finds grammar errors"""
    path = 'tests/forms/ql/fail/'

    for form in os.listdir(path):
        if form.endswith(".ql"):
            print(form)
            file = open_file(path + form)
            parser = Parser()
            parser.set_ql_grammar_text(file)
            parser.run_antlr_ql()
            if not parser.ql_errors:
                print(parser.ql_errors)
                assert False
        else:
            assert True


def test_all_qls_pass_forms():
    """ Iterates through all the passing ql+qls forms directory and finds grammar errors"""
    path = 'tests/forms/qls/pass/'

    for form in os.listdir(path):
        if form.endswith(".qls"):
            print(form)
            file = open_file(path + form)
            parser = Parser()
            parser.set_qls_grammar_text(file)
            parser.run_antlr_qls()
            if parser.qls_errors:
                print(parser.qls_errors)
                assert False
        else:
            assert True


def test_all_qls_fail_forms():
    """ Iterates through all the passing ql+qls forms directory and finds grammar errors"""
    path = 'tests/forms/qls/fail/'

    for form in os.listdir(path):
        if form.endswith(".qls"):
            print(form)
            file = open_file(path + form)
            parser = Parser()
            parser.set_qls_grammar_text(file)
            parser.run_antlr_qls()
            if not parser.qls_errors:
                print(parser.qls_errors)
                assert False
        else:
            assert True
