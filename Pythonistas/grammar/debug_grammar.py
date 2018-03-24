import antlr4
import re
import os

from commons.error_handling import MyErrorListener
from parser_generator.grammar.QLLexer import QLLexer
from parser_generator.grammar.QLParser import QLParser
from parser_generator.grammar.QLSLexer import QLSLexer
from parser_generator.grammar.QLSParser import QLSParser
from commons.utility import open_file


# todo: refactoring and error if file not compatible for debugging (no ql/qls extension)
class GrammarDebugger:
    """ Used to debug grammer """
    def __init__(self, file_path):
        self.file_path = file_path
        self.file_extension = file_path.split('.')[-1]
        self.parser = None
        self.tokens = None

    def debug_grammar(self):
        """ Main function - runs through parsing and print out tokens and tree in CLI """
        assert os.path.isfile(self.file_path)

        file = open_file(self.file_path)
        input_form = antlr4.InputStream(file)
        lexer = QLLexer(input_form) if self.file_extension == 'ql' else QLSLexer(input_form)

        self.tokens = antlr4.CommonTokenStream(lexer)
        self.print_tokens()

        self.parser = QLParser(self.tokens) if self.file_extension == 'ql' else QLSParser(self.tokens)
        self.parser._listeners = [MyErrorListener()]
        self.print_tree()

    def print_tokens(self):
        """ Prints token stream """
        print('\n', end='')
        print('[TOKENS]')
        self.tokens.fill()
        token_types = self.get_token_types()
        for token in self.tokens.tokens:
            token_type = token_types[str(token.type)]
            print_width = 15
            print(f"  {token_type: <{print_width}} '{token.text}'")

    def print_tree(self):
        """ Prints AST """
        string = ''
        indentation = 0
        tree = self.parser.form().toStringTree(recog=self.parser) if self.file_extension == 'ql' else \
            self.parser.stylesheet().toStringTree(recog=self.parser)

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

        print('\n', end='')
        print('[PARSE-TREE]')
        print(string)

    def get_token_types(self):
        """ Gets token types corrensponding with lexer grammar """
        tokens = open_file('parser_generator/grammar/{}.tokens'.format(self.file_extension.upper()))
        tokens = tokens.split('\n')
        x = {}
        for token in tokens:
            if token != '' and "'" not in token:
                t = re.split(r'(\=\d*)\Z', token)
                x[t[1].replace("=", "")] = t[0]
        x['-1'] = '<EOF>'
        return x
