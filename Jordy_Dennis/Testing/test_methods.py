import unittest
import os
import sys

Path = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, Path + '/../')

from parser import *
from antlr4.InputStream import InputStream
from LexParser.QLGrammarLexer import QLGrammarLexer
from LexParser.QLGrammarParser import QLGrammarParser


# Get the input and output text needed for a test on which the output is controlled
def getInputOutput(path, filename):
    file_object = open(path + "/" + filename, "r")
    data = file_object.read().split("---\n")
    file_object.close()
    inputText = data[0].strip("\n")
    outputText = data[1].strip("\n")
    return inputText, outputText

# apply the lexer to a string, in order to test the lexer
def getLexerFromString(inputText):
    input_stream = InputStream(inputText)
    lexer = QLGrammarLexer(input_stream)
    token_stream = CommonTokenStream(lexer)
    parser = QLGrammarParser(token_stream)
    parser._listeners = [MyErrorListener()]

    tree = parser.form()
    tree_str = tree.toStringTree(recog=parser)
    return str(tree_str)

# get the AST of a program-string (after lexing and parsing)
def getAstFromString(inputText):
    input_stream = InputStream(inputText)
    lexer = QLGrammarLexer(input_stream)
    stream = CommonTokenStream(lexer)
    parser = QLGrammarParser(stream)
    tree = parser.form()

    visitor = Visitor()
    visitor.visit(tree)

    ast = visitor.getAst()
    return ast
