import antlr4
import re
import os
import sys
from commons.error_handling import MyErrorListener
from antlr.generated import *
from commons.utility import open_file


class ParserInterface:
    """
    Uses antlr4 generated lexer parser, dynamically abstracts depending on input
    """
    def __init__(self, parser_input, grammar_name=None):
        self.grammar_text = parser_input
        self.grammar_name = grammar_name
        self.parse_input_type(parser_input)
        self.tokens = None
        self.parser = None
        self.errors = None
        self.ast = None
        self.run_antlr()

    def run_antlr(self):
        """ Generates AST according to grammar input """
        self.lexing()
        self.parsing()

    def lexing(self):
        """ Tokenizes the parser input """
        text_input = antlr4.InputStream(self.grammar_text)
        lexer = self.get_generated(f'{self.grammar_name}Lexer', text_input)
        self.tokens = antlr4.CommonTokenStream(lexer)

    def parsing(self):
        """ Parses the tokens """
        self.parser = self.get_generated(f'{self.grammar_name}Parser', self.tokens)
        self.parser._listeners = [MyErrorListener()]
        # rulenName[0] is the first token of grammar file to dynamically run parser
        self.ast = getattr(self.parser, f'{self.parser.ruleNames[0]}')()
        self.errors = self.parser._listeners[0].get_errors()

    def print_structure(self):
        """ Prints stream of tokens and ast """
        self.print_tokens()
        self.print_ast()
        self.print_errors()

    def print_tokens(self):
        """ Prints found token stream """
        self.print_header('TOKENS')
        self.tokens.fill()
        token_types = self.get_token_types()
        for token in self.tokens.tokens:
            token_type = token_types[str(token.type)]
            print_width = 15
            print(f"  {token_type: <{print_width}} '{token.text}'")

    def print_ast(self):
        """ Prints AST """
        self.print_header('PARSE-TREE')
        string = ''
        indentation = 0

        for char in self.ast.toStringTree(recog=self.parser):
            if char == '(':
                string += ('\n' + ' '*(indentation+2) + char)
                indentation += 2
            elif char == ')':
                string += char
                indentation -= 2
            else:
                string += char
        print(string[1:])

    def print_errors(self):
        """ Prints antlr found grammar errors """
        self.print_header('ERRORS')
        if self.errors:
            print(f'  {self.errors}')
        else:
            print('  No grammar errors found')

    @staticmethod
    def print_header(header_title):
        print('\n', end='')
        print(f'[{header_title}]')

    def get_token_types(self):
        """ Gets token types corrensponding with lexer grammar """
        token_types = {'-1': '<EOF>'}
        tokens = open_file('antlr/generated/{}.tokens'.format(self.grammar_name)).split('\n')

        for token in tokens:
            if token != '' and "'" not in token:
                t = re.split(r'(\=\d*)\Z', token)
                token_types[t[1].replace("=", "")] = t[0]

        return token_types

    def parse_input_type(self, parser_input):
        """ parse input if its a path or text """
        if self.check_if_file(parser_input):
            self.grammar_name = parser_input.split('.')[-1].upper()
            self.grammar_text = open_file(parser_input)
        elif type(parser_input) == str:
            if not self.grammar_name:
                raise ValueError('Grammar name has to be given if parser_input is a string or given path does not exist')
            self.check_grammar_exist()
        else:
            raise TypeError('Unknown input for parsing, accepted format: file_path, string')

    def check_grammar_exist(self):
        """ Checks if grammar input exists in grammar dir """
        grammar_dir = f'{os.getcwd()}/antlr/grammar'
        if f'{self.grammar_name}.g4' not in os.listdir(grammar_dir):
            raise ValueError('Request for parsing with grammar that does not exist')

    def get_generated(self, generated_class, class_input):
        """ Factory for delegating correctly antlr4 generated class """
        return getattr(sys.modules[
                           f'antlr.generated.{generated_class}'], f'{generated_class}')(class_input)

    @staticmethod
    def check_if_file(input):
        try:
            return os.path.isfile(input)
        except ValueError:
            return False
