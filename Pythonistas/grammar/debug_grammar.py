import antlr4
import re
import io
import sys
from contextlib import redirect_stdout

from parser_generator.grammar.QLLexer import QLLexer
from parser_generator.grammar.QLParser import QLParser
from commons.utility import open_file


# todo: clean
class GrammarDebugger:
    def __init__(self, file_path):
        self.file_path = file_path
        self.error = None


    def debug_grammar(self):
        file = open_file(self.file_path)
        ql_input = antlr4.InputStream(file)
        lexer = QLLexer(ql_input)
        tokens = antlr4.CommonTokenStream(lexer)
        self.print_tokens(tokens)

        parser = QLParser(tokens)
        tree, errors = self.print_tree(parser)

        return tree, errors

    def print_tokens(self, tokens):
        """ Prints token stream """
        print('\n', end='')
        print('[TOKENS]')
        tokens.fill()
        token_types = self.get_token_types()
        for token in tokens.tokens:
            token_type = token_types[str(token.type)]
            print_width = 15
            print(f"  {token_type: <{print_width}} '{token.text}'")

    def print_tree(self, parser):
        """ Prints AST """
        print('\n', end='')
        print('[PARSE-TREE]')
        indentation = 0
        tree = parser.form().toStringTree(recog=parser)

        string = ''

        for char in tree:
            if char == '(':
                string += '\n'
                indentation += 1
                string += '  '*indentation
                string += char
            elif char == ')':
                string += char
                indentation -= 1
            else:
                string += char
        print(string)

        return tree, tree

    def get_token_types(self):
        """ Gets token types corrensponding with lexer grammar """
        # todo: fix ql var for QLS
        tokens = open_file('parser_generator/grammar/{}.tokens'.format('QL'))
        tokens = tokens.split('\n')
        x = {}
        for token in tokens:
            if token != '' and "'" not in token:
                t = re.split(r'(\=\d*)\Z', token)
                x[t[1].replace("=", "")] = t[0]
        x['-1'] = '<EOF>'
        return x
