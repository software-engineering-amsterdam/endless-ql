from re import findall

from ply.lex import lex


class QLLexer:
    def __init__(self):
        self.__errors = []
        self.__lexer = None

    @property
    def lexer(self):
        return self.__lexer

    @property
    def errors(self):
        return self.__errors

    def build(self):
        self.__lexer = lex(module=self)

    def tokenize(self, data):
        self.__errors = []
        self.lexer.input(data)
        token = self.lexer.token()
        tokens = [token]

        while token:
            token = self.lexer.token()
            tokens.append(token)

        return tokens

    tokens = [
        'PLUS', 'MINUS', 'TIMES', 'DIVIDE', 'COLON',
        'ASSIGN',
        'EQUALS', 'NOT_EQUALS',
        'LESS_EQUALS', 'LESS_THAN', 'GREATER_EQUALS', 'GREATER_THAN',
        'AND', 'OR',
        'DOLLAR', 'RUBLE',
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

    t_LESS_EQUALS = r'<='
    t_LESS_THAN = r'<'
    t_GREATER_EQUALS = r'>='
    t_GREATER_THAN = r'>'
    t_EQUALS = r'=='
    t_NOT_EQUALS = r'!='
    t_AND = r'&&'
    t_OR = r'\|\|'

    t_DOLLAR = r'\$'
    t_RUBLE = r'\₽'

    t_LEFT_BRACE = r'\{'
    t_RIGHT_BRACE = r'\}'

    t_LEFT_BRACKET = r'\('
    t_RIGHT_BRACKET = r'\)'

    # Literals
    @staticmethod
    def t_FALSE(token):
        r'False'
        token.value = False
        return token

    @staticmethod
    def t_TRUE(token):
        r'True'
        token.value = True
        return token

    def t_DATE_LITERAL(self, token):
        r'date\(\s*\d{1,2}\s*,\s*\d{1,2}\s*,\s*\d{1,4}\s*\)'

        try:
            token.value = findall(r'\d\d*', token.value)
            return token
        except SyntaxError:
            self.errors.append('Invalid date.')

    @staticmethod
    def t_DECIMAL_LITERAL(token):
        r'\d+[.]\d+'
        token.value = token.value
        return token

    @staticmethod
    def t_INTEGER_LITERAL(token):
        r'\d+'
        token.value = token.value
        return token

    @staticmethod
    def t_STRING_LITERAL(token):
        r'\"(.+?)\"'
        token.value = token.value[1:-1]
        return token

    # Other
    def t_IDENTIFIER(self, token):
        r'[a-z][a-zA-Z_0-9]*'
        token.type = self.reserved_keywords.get(token.value, 'IDENTIFIER')
        return token

    @staticmethod
    def t_comment(token):
        r'//.*'
        pass

    @staticmethod
    def t_newline(token):
        r'\n+'
        token.lexer.lineno += len(token.value)

    # Error handling
    def t_error(self, token):
        self.errors.append("Illegal character '%s'" % token.value[0])
        token.lexer.skip(1)
