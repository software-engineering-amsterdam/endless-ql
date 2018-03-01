import unittest
import os
import sys
from test_methods import *

Path = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, Path + '/../')

from parser import *
from antlr4.InputStream import InputStream
from LexParser.QLGrammarLexer import QLGrammarLexer
from LexParser.QLGrammarParser import QLGrammarParser

class LexerTest(unittest.TestCase):
    def testGoodFiles(self):
        path = 'Testing/lexer_test_files/correct_test'
        for filename in os.listdir(path):
            inputText, outputText = getInputOutput(path, filename)
            lexer_str = getLexerFromString(inputText)
            self.assertEqual(lexer_str, outputText, filename)


    def testErrorFiles(self):
        path = 'Testing/lexer_test_files/fail_test'
        for filename in os.listdir(path):
            file_object = open(path + "/" + filename, "r")
            inputText = file_object.read()

            self.assertRaises(Exception, getLexerFromString, inputText, filename)
            file_object.close()

if __name__ == '__main__':
    unittest.main()
