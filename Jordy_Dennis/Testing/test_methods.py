import os
import sys

Path = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, Path + '/../')

from parser import *
from antlr4.InputStream import InputStream
from Grammar.QLGrammarLexer import QLGrammarLexer
from Grammar.QLGrammarParser import QLGrammarParser

from Grammar.QLSGrammarLexer import QLSGrammarLexer
from Grammar.QLSGrammarParser import QLSGrammarParser
import unittest


# Get the input and output text needed for a test on which the output is controlled
def getInputOutput(path, filename):
    file_object = open(path + "/" + filename, "r")
    data = file_object.read().split("---\n")
    file_object.close()
    inputText = data[0].strip("\n")
    outputText = data[1].strip("\n")
    return inputText, outputText

# Get the input and output text needed for a test on which the output is controlled for QLS
def getInputOutputQLS(path, filename):
    file_object = open(path + "/" + filename, "r")
    data = file_object.read().split("---\n")
    file_object.close()
    qlText = data[0].strip("\n")
    qlsText = data[1].strip("\n")
    outputText = data[2].strip("\n")
    return qlText, qlsText, outputText

def getQLSAstFromString(inputText):
    input_stream = InputStream(inputText)
    lexer = QLSGrammarLexer(input_stream)
    stream = CommonTokenStream(lexer)
    parser = QLSGrammarParser(stream)
    tree = parser.stylesheet()
    qlsVisitor = QLSVisitor()
    qlsVisitor.visit(tree)

    qlsAST = qlsVisitor.stylesheet
    return qlsAST

# apply the lexer to a string, in order to test the lexer
def getLexerFromStringQLS(inputText):
    input_stream = InputStream(inputText)
    lexer = QLSGrammarLexer(input_stream)
    token_stream = CommonTokenStream(lexer)
    parser = QLSGrammarParser(token_stream)
    parser._listeners = [MyErrorListener()]

    tree = parser.form()
    tree_str = tree.toStringTree(recog=parser)
    return str(tree_str)



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

    visitor = QLVisitor()
    visitor.visit(tree)

    ast = visitor.getAst()
    return ast

def printDict(dic):
    pp = pprint.PrettyPrinter(indent=4)
    pp.pprint(dic)

# Block a function from writing to stdout
def blockPrint():
    sys.stdout = open(os.devnull, 'w')

# Restore
def enablePrint():
    sys.stdout = sys.__stdout__
