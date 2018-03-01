import unittest
import os
import sys

Path = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, Path + '/../')

from parser import *
from antlr4.InputStream import InputStream
from LexParser.QLGrammarLexer import QLGrammarLexer
from LexParser.QLGrammarParser import QLGrammarParser



def getInputOutput(path, filename):
    file_object = open(path + "/" + filename, "r")
    data = file_object.read().split("---\n")
    file_object.close()
    inputText = data[0].strip("\n")
    outputText = data[1].strip("\n")
    return inputText, outputText

def getLexerFromString(input):
    input_stream = InputStream(input)
    lexer = QLGrammarLexer(input_stream)
    token_stream = CommonTokenStream(lexer)
    parser = QLGrammarParser(token_stream)
    parser._listeners = [MyErrorListener()]

    tree = parser.form()
    tree_str = tree.toStringTree(recog=parser)
    return str(tree_str)