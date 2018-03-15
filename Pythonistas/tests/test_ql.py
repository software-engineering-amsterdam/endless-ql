import sys
import os
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)) + '/../')

from grammar.run_antlr import run_antlr
from commons.utility import open_file


# # Helpers
# def helper_qltests(str_input, expected_result):
#     tree = run_antrl(str_input)
#     assert tree.toStringTree() == expected_result
#
#
# # Single statements
# def test_form():
#     str_input = 'form Box1HouseOwning {}'
#     expected_result = '([] form Box1HouseOwning ([18] { }) <EOF>)'
#     helper_qltests(str_input, expected_result)
#
#
# def test_question():
#     str_input = 'hasSoldHouse: "Did you sell a house in 2010?" boolean'
#     expected_result = '''([] <missing 'form'> hasSoldHouse ([18] : "Did you sell a house in 2010?" boolean) <EOF>)'''
#     helper_qltests(str_input, expected_result)
#
#
# # Form composition
# def test_if():
#     form = open_file('tests/forms/if.ql')
#     expected_result = '([] form Box1HouseOwning ([18] { ([28 18] ([42 28 18] "Did you sell a house in 2010?" hasSoldHouse : ([49 42 28 18] boolean))) ([28 18] ([43 28 18] if ( ([76 43 28 18] ([68 76 43 28 18] hasSoldHouse)) ) ([82 43 28 18] { ([28 82 43 28 18] ([42 28 82 43 28 18] "Price the house was sold for:" sellingPrice : ([49 42 28 82 43 28 18] money) ([50 42 28 82 43 28 18] = ( ([62 50 42 28 82 43 28 18] ([68 62 50 42 28 82 43 28 18] 6)) )))) }))) }) <EOF>)'
#     helper_qltests(form, expected_result)
#
#
# def test_money_declare():
#     str_input = open_file('tests/forms/money_declare.ql')
#     expected_result = '([] form Box1HouseOwning ([18] { ([28 18] ([42 28 18] "Did you sell a house in 2010?" hasSoldHouse : ([49 42 28 18] boolean))) ([28 18] ([42 28 18] "Price the house was sold for:" sellingPrice : ([49 42 28 18] money) ([50 42 28 18] = ( ([62 50 42 28 18] ([68 62 50 42 28 18] 7)) )))) }) <EOF>)'
#     helper_qltests(str_input, expected_result)
#
#
# def test_pp_example():
#     str_input = open_file('tests/forms/pp_example.ql')
#     expected_result = '([] form Box1HouseOwning ([18] { ([28 18] ([42 28 18] "Did you sell a house in 2010?" hasSoldHouse : ([49 42 28 18] boolean))) ([28 18] ([42 28 18] "Did you by a house in 2010?" hasBoughtHouse : ([49 42 28 18] boolean))) ([28 18] ([42 28 18] "Did you enter a loan for maintenance/reconstruction?" hasMaintLoan : ([49 42 28 18] boolean))) ([28 18] ([43 28 18] if ( ([76 43 28 18] ([68 76 43 28 18] hasSoldHouse)) ) ([82 43 28 18] { ([28 82 43 28 18] ([42 28 82 43 28 18] "Price the house was sold for:" sellingPrice : ([49 42 28 82 43 28 18] money))) ([28 82 43 28 18] ([42 28 82 43 28 18] "Private debts for the sold house:" privateDebt : ([49 42 28 82 43 28 18] money))) }))) }) <EOF>)'
#     helper_qltests(str_input, expected_result)
