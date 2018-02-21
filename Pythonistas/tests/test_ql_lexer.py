"""
pytest: https://docs.pytest.org/en/latest/
"""
import sys
import os

myPath = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, myPath + '/../')


from lexer.ql_lexer import ql_lex


def add_one(x):
    return x + 1


def test_structure():
    assert add_one(3) == 4


# todo: write tests
def test_form():
    form = "form Box1HouseOwning {}"
    assert ql_lex(form) == "[('form', 'form'), ('Box1HouseOwning', 'id'), ('{', 'reserved'), ('}', 'reserved')]"


def test_declaration():
    # "Did you sell a house in 2010?" \n hasSoldHouse: boolean
    pass


def test_if():
    # if
    pass
