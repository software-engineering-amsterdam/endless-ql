"""
TODO: Add optimise=1 to the lexer when file is production ready
"""

import ply.lex as lex


class LexTokenizer(object):

    # List of token names.
    tokens = [
        'PLUS', 'MINUS', 'TIMES', 'DIVIDE', 'COLON',
        'ASSIGN',
        'LE', 'LT', 'GE', 'GT', 'EQ', 'NE', 'AND', 'OR',
        'NOT',
        'LBRACKET', 'RBRACKET',
        'LPAREN', 'RPAREN',
        'NUMBER', 'FLOAT',
        'QUESTION',
        'VAR']

    # List of reserved keywords
    reserved = {
        'form'    : 'FORM',
        'if'      : 'IF',
        'elif'    : 'ELIF',
        'else'    : 'ELSE',
        'boolean' : 'BOOLEAN',
        'string'  : 'STRING',
        'integer' : 'INTEGER',
        'date'    : 'DATE',
        'decimal' : 'DECIMAL',
        'money'   : 'MONEY'}

    tokens += list(reserved.values())

    # Regular expression rules for simple tokens
    t_ignore   = ' \t'

    t_PLUS     = r'\+'
    t_MINUS    = r'-'
    t_TIMES    = r'\*'
    t_DIVIDE   = r'/'
    t_COLON    = r':'

    t_ASSIGN   = r'='

    t_LE       = r'<='
    t_LT       = r'<'
    t_GE       = r'>='
    t_GT       = r'>'
    t_EQ       = r'=='
    t_NE       = r'!='
    t_AND      = r'&&'
    t_OR       = r'\|\|'

    t_NOT      = r'\!'

    t_LBRACKET = r'\{'
    t_RBRACKET = r'\}'

    t_LPAREN   = r'\('
    t_RPAREN   = r'\)'

    t_QUESTION = r'\"(.+?)\"'

    # Define a rule so we can track line numbers
    @staticmethod
    def t_newline(t):
        r'\n+'
        t.lexer.lineno += len(t.value)

    # Define a rule for detecting round numbers
    @staticmethod
    def t_NUMBER(t):
        r'\d+'
        t.value = int(t.value)
        return t

    # Define a rule for detecting decimal numbers
    @staticmethod
    def t_FLOAT(t):
        r'\d+\.(\d+)'
        return t

    # Define a rule for handling erroneous characters
    @staticmethod
    def t_error(t):
        print("Illegal character '%s'" % t.value[0])
        t.lexer.skip(1)

    # Define a rule for handling all non-tokens
    def t_VAR(self, t):
        r'[a-zA-Z_][a-zA-Z_0-9]*'
        t.type = self.reserved.get(t.value, 'VAR')  # Check for reserved words
        return t

    # Test the lexer output
    def test(self, data):
        self.lexer.input(data)
        while True:
            tok = self.lexer.token()
            if not tok:
                break
            print(tok)

    # Class constructor
    def __init__(self):
        self.lexer = lex.lex(module=self)
