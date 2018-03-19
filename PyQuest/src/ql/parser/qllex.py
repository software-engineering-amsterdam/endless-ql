"""
TODO: Add optimise=1 to the lexer when file is production ready
"""

from ply.lex import lex
from re import findall
from ql.types.boolean import QLBoolean
from ql.types.date import QLDate
from ql.types.decimal import QLDecimal
from ql.types.integer import QLInteger
from debug.debug import error


class LexTokenizer(object):

    # List of token names.
    tokens = [
        'PLUS', 'MINUS', 'TIMES', 'DIVIDE', 'COLON',
        'ASSIGN',
        'LE', 'LT', 'GE', 'GT', 'EQ', 'NE', 'AND', 'OR',  # TODO: expand abbreviation
        'NOT',
        'LEFT_BRACE', 'RIGHT_BRACE',
        'LEFT_BRACKET', 'RIGHT_BRACKET',
        'INTEGER_LITERAL', 'DECIMAL_LITERAL',
        'TRUE', 'FALSE',
        'DATE_LITERAL',
        'STRING_LITERAL',
        'VARIABLE']

    # List of reserved keywords
    reserved = {
        'form'    : 'FORM',
        'if'      : 'IF',
        'elif'    : 'ELSE_IF',
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

    t_LEFT_BRACE = r'\{'
    t_RIGHT_BRACE = r'\}'

    t_LEFT_BRACKET   = r'\('
    t_RIGHT_BRACKET   = r'\)'

    # Define a rule so we can track line numbers
    @staticmethod
    def t_newline(t):
        r'\n+'
        t.lexer.lineno += len(t.value)

    @staticmethod
    def t_FALSE(t):
        r'False'
        t.value = QLBoolean(False)
        return t

    @staticmethod
    def t_TRUE(t):
        r'True'
        t.value = QLBoolean(True)
        return t

    @staticmethod
    def t_DATE_LITERAL(t):
        r'date\(\W*\d{1,2},\W*\d{1,2},\W*\d{1,4}\W*\)'
        numbers = findall(r'\d\d*', t.value)

        try:
            t.value = QLDate(numbers)
            return t
        except SyntaxError:
            error([t.lineno], 'Invalid date.')

    @staticmethod
    def t_DECIMAL_LITERAL(t):
        r'\d+[.]\d+'
        t.value = QLDecimal(t.value)
        return t

    @staticmethod
    def t_INTEGER_LITERAL(t):
        r'\d+'
        t.value = QLInteger(t.value)
        return t

    @staticmethod
    def t_STRING_LITERAL(t):
        r'\"(.+?)\"'
        t.value = t.value[1:-1]
        return t

    # Define a rule for handling erroneous characters
    @staticmethod
    def t_error(t):
        print("Illegal character '%s'" % t.value[0])
        t.lexer.skip(1)

    # Define a rule for handling all non-tokens
    def t_VARIABLE(self, t):
        r'[a-zA-Z_][a-zA-Z_0-9]*'
        t.type = self.reserved.get(t.value, 'VARIABLE')  # Check for reserved words
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
        self.lexer = lex(module=self)
