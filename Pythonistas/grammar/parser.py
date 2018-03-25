import antlr4
import re
import os
from commons.error_handling import MyErrorListener
from parser_generator.grammar.QLLexer import QLLexer
from parser_generator.grammar.QLParser import QLParser
from parser_generator.grammar.QLSLexer import QLSLexer
from parser_generator.grammar.QLSParser import QLSParser
from commons.utility import open_file


# todo: refactoring - merge ql/qls
class Parser:
    def __init__(self):
        # Debugging
        self.file_path = None
        self.file_extension = None
        self.parser = None
        self.tokens = None

        # QL
        self.ql_grammar_text = None
        self.ql_tokens = None
        self.ql_lexer = None
        self.ql_parser = None
        self.ql_errors = None
        self.ql_tree = None

        # QLS
        self.qls_grammar_text = None
        self.qls_tokens = None
        self.qls_lexer = None
        self.qls_parser = None
        self.qls_errors = None
        self.qls_tree = None

    def set_ql_grammar_text(self, ql_text):
        self.ql_grammar_text = ql_text

    def set_qls_grammar_text(self, qls_text):
        self.qls_grammar_text = qls_text

    def run_antlr_ql(self):
        ql_input = antlr4.InputStream(self.ql_grammar_text)
        self.ql_lexer = QLLexer(ql_input)
        stream = antlr4.CommonTokenStream(self.ql_lexer)
        self.ql_parser = QLParser(stream)
        self.ql_parser._listeners = [MyErrorListener()]
        self.ql_tree = self.ql_parser.form()
        self.ql_errors = self.ql_parser._listeners[0].get_errors()

    def run_antlr_qls(self):
        qls_input = antlr4.InputStream(self.qls_grammar_text)
        self.qls_lexer = QLSLexer(qls_input)
        stream = antlr4.CommonTokenStream(self.qls_lexer)
        self.qls_parser = QLSParser(stream)
        self.qls_parser._listeners = [MyErrorListener()]
        self.qls_tree = self.qls_parser.stylesheet()
        self.qls_errors = self.qls_parser._listeners[0].get_errors()

    def debug_grammar(self, file_path):
        """ Runs through parsing and print out tokens and tree in CLI """
        self.file_path = file_path
        self.file_extension = file_path.split('.')[-1]
        assert os.path.isfile(self.file_path)

        file = open_file(self.file_path)
        input_form = antlr4.InputStream(file)
        lexer = QLLexer(input_form) if self.file_extension == 'ql' else QLSLexer(input_form)

        self.tokens = antlr4.CommonTokenStream(lexer)
        self.print_tokens()

        self.parser = QLParser(self.tokens) if self.file_extension == 'ql' else QLSParser(self.tokens)
        self.parser._listeners = [MyErrorListener()]
        self.print_tree()
        print(self.parser._listeners[0].get_errors())

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
