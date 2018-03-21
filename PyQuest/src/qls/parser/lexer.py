"""
TODO: Add optimise=1 to the lexer when file is production ready
"""

from ply.lex import lex


class QLSLexer:
    def __init__(self):
        self.lexer = lex(module=self)

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

    # Define a rule so we can track line numbers
    @staticmethod
    def t_newline(t):
        r'\n+'
        t.lexer.lineno += len(t.value)

    @staticmethod
    def t_INTEGER_LITERAL(t):
        r'\d+'
        t.value = int(t.value)
        return t

    @staticmethod
    def t_STRING_LITERAL(t):
        r'\"(.+?)\"'
        t.value = t.value[1:-1]
        return t

    # Define a rule for handling all non-tokens
    def t_IDENTIFIER(self, t):
        r'[a-zA-Z][a-zA-Z_0-9]*'
        t.type = self.reserved.get(t.value, 'IDENTIFIER')  # Check for reserved words
        return t

    # Define a rule for handling erroneous characters
    @staticmethod
    def t_error(t):
        print("Illegal character '%s'" % t.value[0])
        t.lexer.skip(1)

    # Test the lexer output
    def test(self, data):
        self.lexer.input(data)
        while True:
            tok = self.lexer.token()
            if not tok:
                break
            print(tok)
