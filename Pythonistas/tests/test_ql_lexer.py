"""
pytest: https://docs.pytest.org/en/latest/
"""
import sys
import os

myPath = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, myPath + '/../')


from lexer.ql_lexer import ql_lex


# todo: put \n into tests
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
