"""
pytest: https://docs.pytest.org/en/latest/
"""
import os
import sys
sys.path.insert(1, os.path.join(sys.path[0], '..'))
from lexer.ql_lexer import *


def lexer_test_helper(code, expected):
    actual = ql_lex(code)
    assert expected == actual


# Single tests
def test_empty():
    lexer_test_helper('', [])


def test_id():
    lexer_test_helper('abc', [('abc', 'id')])


def test_keyword_first():
    lexer_test_helper('if', [('if', 'reserved')])


def test_space():
    lexer_test_helper(' ', [])


def test_id_space():
    lexer_test_helper('abc def', [('abc', 'id'), ('def', 'id')])


# Statement tests
def test_form():
    form_statement = "form Box1HouseOwning {}"
    assert ql_lex(form_statement) == [('form', 'form'), ('Box1HouseOwning', 'id'), ('{', 'reserved'), ('}', 'reserved')]


def test_declaration():
    declaration_statement = 'hasSoldHouse: "Did you sell a house in 2010?" boolean'
    assert ql_lex(declaration_statement) == [('hasSoldHouse', 'id'), (':', 'reserved'),
                                             ('"Did you sell a house in 2010?"', 'value'), ('boolean', 'reserved')]


def test_if():
    if_statement = 'if (hasSoldHouse) {sellingPrice: "Price the house was sold for:" money}'
    assert ql_lex(if_statement) == [('if', 'reserved'), ('(', 'reserved'), ('hasSoldHouse', 'id'), (')', 'reserved'),
                                    ('{', 'reserved'), ('sellingPrice', 'id'), (':', 'reserved'),
                                    ('"Price the house was sold for:"', 'value'), ('money', 'reserved'),
                                    ('}', 'reserved')]
