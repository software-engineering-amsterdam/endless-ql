import sys
import os

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)) + '/../')

from grammar.run_antlr import run_antrl


def helper_qltests(str_input, expected_result):
    tree = run_antrl(str_input)
    assert tree.toStringTree() == expected_result


def test_form():
    str_input = 'form Box1HouseOwning {}'
    expected_result = '([] form Box1HouseOwning ([22] { }) <EOF>)'
    helper_qltests(str_input, expected_result)


def test_question():
    str_input = 'hasSoldHouse: "Did you sell a house in 2010?" boolean'
    expected_result = '''([] <missing 'form'> hasSoldHouse ([22] : "Did you sell a house in 2010?" boolean) <EOF>)'''
    helper_qltests(str_input, expected_result)


# def test_if():
#     str_input = ''
#     expected_result = '([] form Box1HouseOwning ([22] { }) <EOF>)'
#     helper_qltests(str_input, expected_result)

