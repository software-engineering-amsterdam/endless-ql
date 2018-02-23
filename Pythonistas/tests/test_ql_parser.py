import sys
import os

myPath = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, myPath + '/../')


from lexer.ql_lexer import *
from parse.ql_parser import *


# Helper
def parser_test_helper(code, parser, expected):
    tokens = ql_lex(code)
    result = parser(tokens, 0)
    assert None != result
    assert expected == result.value


# Statements
def test_boolean_assign_stmt():
    parser_test_helper('hasSoldHouse: "Did you sell a house in 2010?" boolean',
                       stmt_list(),
                       AssignStatement('hasSoldHouse', '"Did you sell a house in 2010?"', 'boolean'))


def test_money_assign_stmt():
    parser_test_helper('sellingPrice: "Price the house was sold for:" money',
                       stmt_list(),
                       AssignStatement('sellingPrice', '"Price the house was sold for:"', 'money'))


def test_form_stmt():
    code = 'form Box1HouseOwning { \n' \
           'hasSoldHouse: "Did you sell a house in 2010?" boolean \n' \
           '}'
    expected = FormStatement('Box1HouseOwning',
                             AssignStatement('hasSoldHouse', '"Did you sell a house in 2010?"', 'boolean'))
    parser_test_helper(code, stmt_list(), expected)


def test_if_stmt():
    code = 'if (hasSoldHouse) { \n' \
           'hasBoughtHouse: "Did you by a house in 2010?" boolean \n' \
           '}'
    expected = IfStatement('hasSoldHouse', AssignStatement('hasBoughtHouse', '"Did you by a house in 2010?"', 'boolean'))
    parser_test_helper(code, stmt_list(), expected)


def test_compound_stmt():
    code = 'hasSoldHouse: "Did you sell a house in 2010?" boolean \n' \
           'hasBoughtHouse: "Did you by a house in 2010?" boolean'
    expected = CompoundStatement(AssignStatement('hasSoldHouse', '"Did you sell a house in 2010?"', 'boolean'),
                                 AssignStatement('hasBoughtHouse', '"Did you by a house in 2010?"', 'boolean'))
    parser_test_helper(code, stmt_list(), expected)
