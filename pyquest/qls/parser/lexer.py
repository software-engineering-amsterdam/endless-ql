from ply.lex import lex


class QLSLexer:
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
        'COLON', 'COMMA',
        'LEFT_BRACE', 'RIGHT_BRACE',
        'LEFT_BRACKET', 'RIGHT_BRACKET',
        'IDENTIFIER',
        'HEX_COLOR',
        'INTEGER_LITERAL', 'STRING_LITERAL',
    ]

    # List of reserved keywords
    reserved = {
        'stylesheet':   'STYLESHEET',
        'page':         'PAGE',
        'section':      'SECTION',
        'question':     'QUESTION',
        'default':      'DEFAULT',

        # Properties
        'height':       'HEIGHT',
        'width':        'WIDTH',
        'font':         'FONT',
        'fontsize':     'FONT_SIZE',
        'color':        'COLOR',
        'widget':       'WIDGET',

        # QL types
        'boolean':      'BOOLEAN',
        'date':         'DATE',
        'decimal':      'DECIMAL',
        'integer':      'INTEGER',
        'money':        'MONEY',
        'string':       'STRING',

        # Widgets
        'calendar':     'CALENDAR',
        'checkbox':     'CHECKBOX',
        'line_edit':    'LINE_EDIT',
        'spinbox':      'SPINBOX',
        'radio':        'RADIO',
    }

    tokens += list(reserved.values())

    # Regular expression rules for simple tokens
    t_ignore = ' \t'

    t_COLON = r':'
    t_COMMA = r','

    t_LEFT_BRACE = r'\{'
    t_RIGHT_BRACE = r'\}'

    t_LEFT_BRACKET = r'\('
    t_RIGHT_BRACKET = r'\)'

    t_HEX_COLOR = r'\#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})'

    @staticmethod
    def t_newline(token):
        r'\n+'
        token.lexer.lineno += len(token.value)

    @staticmethod
    def t_eof(token):
        token.lexer.lineno = 1

    @staticmethod
    def t_INTEGER_LITERAL(token):
        r'\d+'
        token.value = int(token.value)
        return token

    @staticmethod
    def t_STRING_LITERAL(token):
        r'\"(.+?)\"'
        token.value = token.value[1:-1]
        return token

    def t_IDENTIFIER(self, token):
        r'[a-zA-Z][a-zA-Z_0-9]*'
        token.type = self.reserved.get(token.value, 'IDENTIFIER')  # Check for reserved words
        return token

    def t_error(self, token):
        self.errors.append("Illegal character '%s'" % token.value[0])
        token.lexer.skip(1)
