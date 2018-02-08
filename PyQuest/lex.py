import ply.lex as lex

# List of token names.
tokens = (
    'NUMBER', 'STRING', 'BOOLEAN',
    'PLUS', 'MINUS', 'TIMES', 'DIVIDE',
    'NOT',
    'LE', 'LT', 'GE', 'GT', 'EQ', 'NE', 'AND', 'OR',
    'BRACKET_L', 'BRACKET_R',
)

# Regular expression rules for simple tokens
t_ignore    = ' \t'
t_PLUS    = r'\+'
t_MINUS   = r'-'
t_TIMES   = r'\*'
t_DIVIDE  = r'/'
t_LPAREN  = r'\('
t_RPAREN  = r'\)'