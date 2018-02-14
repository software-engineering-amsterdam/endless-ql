import ply.lex as lex
import datetime

# List of token names.
tokens = [
    'QUESTION',
    'ASSIGN',
    'PLUS', 'MINUS', 'TIMES', 'DIVIDE', 'COLON',
    'NOT',
    'LE', 'LT', 'GE', 'GT', 'EQ', 'NE', 'AND', 'OR',
    'EXP',
    'LBRACKET', 'RBRACKET',
    'LPAREN', 'RPAREN',
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
    'money'   : 'MONEY'
}

tokens = tokens + list(reserved.values())

# Regular expression rules for simple tokens
t_ignore    = ' \t'

t_QUESTION  = r'\"(.+?)\"'

t_ASSIGN    = r'='

t_PLUS      = r'\+'
t_MINUS     = r'-'
t_TIMES     = r'\*'
t_DIVIDE    = r'/'
t_COLON     = r':'

t_NOT       = r'\!'

t_LE        = r'<='
t_LT        = r'<'
t_GE        = r'>='
t_GT        = r'>'
t_EQ        = r'=='
t_NE        = r'!='
t_AND       = r'&&'
t_OR        = r'\|\|'

t_LBRACKET  = r'\{'
t_RBRACKET  = r'\}'

t_LPAREN    = r'\('
t_RPAREN    = r'\)'

# Define a rule so we can track line numbers
def t_newline(t):
    r'\n+'
    t.lexer.lineno += len(t.value)

# Define a rule for handling all non-tokens
def t_VAR(t):
    r'[a-zA-Z_][a-zA-Z_0-9]*'
    t.type = reserved.get(t.value,'VAR')    # Check for reserved words
    return t

# Define a rule for handling erroneous characters
def t_error(t):
    print("Illegal character '%s'" % t.value[0])
    t.lexer.skip(1)

# Build the lexer
lexer = lex.lex()