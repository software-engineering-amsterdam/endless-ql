"""
TODO: Add optimise=1 to the lexer when file is production ready
"""

from re import findall

from ply.lex import lex

from ql.types.boolean import QLBoolean
from ql.types.date import QLDate
from ql.types.decimal import QLDecimal
from ql.types.integer import QLInteger


class QLLexer:
    def __init__(self):
        self.lexer = lex(module=self)
        self.errors = []

    # @property
    # def lexer(self):
    #     return self.__lexer

    # @property
    # def errors(self):
    #     return self.__errors

    tokens = [
        'PLUS', 'MINUS', 'TIMES', 'DIVIDE', 'COLON',
        'ASSIGN',
        'LE', 'LT', 'GE', 'GT', 'EQ', 'NE', 'AND', 'OR',  # TODO: expand abbreviation
        'DOLLAR',
        'LEFT_BRACE', 'RIGHT_BRACE',
        'LEFT_BRACKET', 'RIGHT_BRACKET',
        'INTEGER_LITERAL', 'DECIMAL_LITERAL',
        'TRUE', 'FALSE',
        'DATE_LITERAL',
        'STRING_LITERAL',
        'IDENTIFIER',
    ]

    reserved_keywords = {
        'form':     'FORM',
        'if':       'IF',
        'not':      'NOT',

        # Types
        'boolean':  'BOOLEAN',
        'date':     'DATE',
        'decimal':  'DECIMAL',
        'integer':  'INTEGER',
        'money':    'MONEY',
        'string':   'STRING',
    }

    tokens += list(reserved_keywords.values())

    # Regular expression rules for simple tokens
    t_ignore = ' \t'

    t_PLUS = r'\+'
    t_MINUS = r'-'
    t_TIMES = r'\*'
    t_DIVIDE = r'/'
    t_COLON = r':'
    t_ASSIGN = r'='

    t_LE = r'<='
    t_LT = r'<'
    t_GE = r'>='
    t_GT = r'>'
    t_EQ = r'=='
    t_NE = r'!='
    t_AND = r'&&'
    t_OR = r'\|\|'

    t_DOLLAR = r'\$'

    t_LEFT_BRACE = r'\{'
    t_RIGHT_BRACE = r'\}'

    t_LEFT_BRACKET = r'\('
    t_RIGHT_BRACKET = r'\)'

    # Define a rule so we can track line numbers
    @staticmethod
    def t_newline(token):
        r'\n+'
        token.lexer.lineno += len(token.value)

    # Literals
    @staticmethod
    def t_FALSE(token):
        r'False'
        token.value = QLBoolean(False)
        return token

    @staticmethod
    def t_TRUE(token):
        r'True'
        token.value = QLBoolean(True)
        return token

    def t_DATE_LITERAL(self, token):
        r'date\(\s*\d{1,2}\s*,\s*\d{1,2}\s*,\s*\d{1,4}\s*\)'
        numbers = findall(r'\d\d*', token.value)

        try:
            token.value = QLDate(numbers)
            return token
        except SyntaxError:
            self.errors.append('Invalid date.')

    @staticmethod
    def t_DECIMAL_LITERAL(token):
        r'\d+[.]\d+'
        token.value = QLDecimal(token.value)
        return token

    @staticmethod
    def t_INTEGER_LITERAL(token):
        r'\d+'
        token.value = QLInteger(token.value)
        return token

    @staticmethod
    def t_STRING_LITERAL(token):
        r'\"(.+?)\"'
        token.value = token.value[1:-1]
        return token

    # Other
    def t_IDENTIFIER(self, token):
        r'[a-z][a-zA-Z_0-9]*'
        token.type = self.reserved_keywords.get(token.value, 'IDENTIFIER')  # Check for reserved words
        return token

    @staticmethod
    def t_comment(token):
        r'//.*'
        pass

    # Error handling
    def t_error(self, token):
        self.errors.append("Illegal character '%s'" % token.value[0])
        token.lexer.skip(1)

    # Test the lexer output
    def test(self, data):
        self.errors = []
        self.lexer.input(data)
        while True:
            token = self.lexer.token()
            if not token:
                break
