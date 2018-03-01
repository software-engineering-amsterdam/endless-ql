import unittest
import os
import sys
from io import StringIO
from antlr4.InputStream import InputStream

Path = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, Path + '/../')

from parser import *

from LexParser.QLGrammarLexer import QLGrammarLexer
from LexParser.QLGrammarParser import QLGrammarParser


def getLexerFromString(input):
    input_stream = InputStream(input)
    lexer = QLGrammarLexer(input_stream)
    token_stream = CommonTokenStream(lexer)
    parser = QLGrammarParser(token_stream)
    tree = parser.form()
    tree_str = tree.toStringTree(recog=parser)
    return str(tree_str)


class LexerTest(unittest.TestCase):
    def testEmptyForm(self):
        input = "form test {}"
        lexer_str = getLexerFromString(input)
        compare = str('(form form test (block { }) <EOF>)')

        #self.assertEqual(lexer_str, compare, "empty form test")

    def testFullExample(self):
        input = """
        form taxOfficeExample
        { 
          "Did you sell a house in 2010?"
            hasSoldHouse: boolean
          "Did you buy a house in 2010?"
            hasBoughtHouse: boolean
          "Did you enter a loan?"
            hasMaintLoan: boolean
        }
        """
        lexer_str = getLexerFromString(input)
        output = str('(form form taxOfficeExample (block { (statement (question '
                     '"Did you sell a house in 2010?" hasSoldHouse : (types boolean)))'
                     ' (statement (question "Did you buy a house in 2010?" hasBoughtHouse : (types boolean)))'
                     ' (statement (question "Did you enter a loan?" hasMaintLoan : (types boolean))) }) <EOF>)')
        #self.assertEqual(lexer_str, output)

    def testErrorFiles(self):
        path = 'Testing/lexer_test_files/fail_test'
        for filename in os.listdir('Testing/lexer_test_files/fail_test'):
            file_object = open(path + "/" + filename, "r")
            input = file_object.read()
            self.assertRaises(TypeError, getLexerFromString(input))
            file_object.close()

if __name__ == '__main__':
    unittest.main()
