import unittest
import os
import sys


Path = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, Path + '/../')

from parser import *
from antlr4.InputStream import InputStream
from LexParser.QLGrammarLexer import QLGrammarLexer
from LexParser.QLGrammarParser import QLGrammarParser


def getLexerFromString(input):
    input_stream = InputStream(input)
    lexer = QLGrammarLexer(input_stream)
    token_stream = CommonTokenStream(lexer)
    parser = QLGrammarParser(token_stream)
    parser._listeners = [MyErrorListener()]

    tree = parser.form()
    tree_str = tree.toStringTree(recog=parser)
    return str(tree_str)


class LexerTest(unittest.TestCase):
    def testGoodFiles(self):
        path = 'Testing/lexer_test_files/correct_test'
        for filename in os.listdir(path):
            file_object = open(path + "/" + filename, "r")
            data = file_object.read().split("---\n")
            input = data[0].strip("\n")
            output = data[1].strip("\n")
            lexer_str = getLexerFromString(input)
            
            self.assertEqual(lexer_str, output, filename)
            file_object.close()


    def testErrorFiles(self):
        path = 'Testing/lexer_test_files/fail_test'
        for filename in os.listdir(path):
            file_object = open(path + "/" + filename, "r")
            input = file_object.read()

            self.assertRaises(Exception, getLexerFromString, input, filename)
            file_object.close()

if __name__ == '__main__':
    unittest.main()
