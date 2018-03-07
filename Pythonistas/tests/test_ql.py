import sys
import os
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)) + '/../')

from grammar.run_antlr import run_antrl
from commons.utility import open_file


# Helpers
def helper_qltests(str_input, expected_result):
    tree = run_antrl(str_input)
    assert tree.toStringTree() == expected_result


# Single statements
def test_form():
    str_input = 'form Box1HouseOwning {}'
    expected_result = '([] form Box1HouseOwning ([22] { }) <EOF>)'
    helper_qltests(str_input, expected_result)


def test_question():
    str_input = 'hasSoldHouse: "Did you sell a house in 2010?" boolean'
    expected_result = '''([] <missing 'form'> hasSoldHouse ([22] : "Did you sell a house in 2010?" boolean) <EOF>)'''
    helper_qltests(str_input, expected_result)


def test_if():
    str_input = 'hasSoldHouse: "Did you sell a house in 2010?" boolean \n' \
                'if (hasSoldHouse) {sellingPrice: "Price the house was sold for:" money}'
    expected_result = '''([] <missing 'form'> hasSoldHouse ([22] : "Did you sell a house in 2010?" boolean ''' + \
                      '''if ( hasSoldHouse ) { sellingPrice : "Price the house was sold for:" money }) <EOF>)'''
    helper_qltests(str_input, expected_result)


def test_if_else():
    str_input = 'hasSoldHouse: "Did you sell a house in 2010?" boolean \n' \
                'if (hasSoldHouse) {sellingPrice: "Price the house was sold for:" money} \n' \
                'else {sellingPrice: "Price the house was sold for:" boolean}'
    expected_result = '''([] <missing 'form'> hasSoldHouse ([22] : "Did you sell a house in 2010?" boolean ''' + \
                      '''if ( hasSoldHouse ) { sellingPrice : "Price the house was sold for:" money } ''' + \
                      '''else { sellingPrice : "Price the house was sold for:" boolean }) <EOF>)'''
    helper_qltests(str_input, expected_result)


# Composition
def test_form_if():
    form = open_file('tests/forms/if.ql')
    expected_result = '([] form Box1HouseOwning ([22] { ([32 22] ([46 32 22] hasSoldHouse : "Did you sell a house in 2010?" [54 46 32 22])) ([32 22] ([46 32 22] boolean)) ([32 22] [48 32 22]) ([32 22] ([48 32 22] if ()) ([32 22] ([46 32 22] hasSoldHouse ) {)) ([32 22] ([46 32 22] sellingPrice : "Price the house was sold for:" [54 46 32 22])) ([32 22] ([46 32 22] money)) }) } <EOF>)'
    helper_qltests(form, expected_result)
