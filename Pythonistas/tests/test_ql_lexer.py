"""
pytest: https://docs.pytest.org/en/latest/
"""
import sys
import os

myPath = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, myPath + '/../')


from lexer.ql_lexer import ql_lex


# Helper
def lexer_test_helper(code, expected):
    actual = ql_lex(code)
    assert expected == actual


# Single tests
def test_empty():
    lexer_test_helper('', [])


def test_id():
    lexer_test_helper('abc', [('abc', 'id')])


def test_reserved_first():
    lexer_test_helper('if', [('if', 'reserved')])


def test_space():
    lexer_test_helper(' ', [])


def test_id_space():
    lexer_test_helper('abc def', [('abc', 'id'), ('def', 'id')])


# Constructed tests
def test_form():
    lexer_test_helper("form Box1HouseOwning {}",
                      [('form', 'form'), ('Box1HouseOwning', 'id'), ('{', 'reserved'), ('}', 'reserved')])


def test_declaration():
    lexer_test_helper('hasSoldHouse: "Did you sell a house in 2010?" boolean',
                      [('hasSoldHouse', 'id'), (':', 'reserved'),
                       ('"Did you sell a house in 2010?"', 'value'), ('boolean', 'reserved')]
                      )


def test_if():
    lexer_test_helper('if (hasSoldHouse) {sellingPrice: "Price the house was sold for:" money}',
                        [('if', 'reserved'), ('(', 'reserved'), ('hasSoldHouse', 'id'), (')', 'reserved'),
                        ('{', 'reserved'), ('sellingPrice', 'id'), (':', 'reserved'),
                        ('"Price the house was sold for:"', 'value'), ('money', 'reserved'),
                        ('}', 'reserved')]
                      )
