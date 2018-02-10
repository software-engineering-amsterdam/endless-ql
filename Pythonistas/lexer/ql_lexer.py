from lexer.lexer import lex

# QL token categories, todo: remove from being a global variable
RESERVED = 'RESERVED'
INT      = 'INT'
ID       = 'ID'
FORM     = 'FORM'
DATATYPE = 'DATATYPE'


token_exprs = [
    # todo: write tokens for util/input.ql lexer
    (r'[ \n\t]+',              None),
    (r'#[^\n]*',               None),
    (r'form',                  FORM),
    (r'{',                     RESERVED),
    (r'}',                     RESERVED),
    (r'"(.*?)"',               RESERVED),
    (r':',                     RESERVED),
    (r'\(',                    RESERVED),
    (r'\)',                    RESERVED),
    (r'-',                     RESERVED),
    (r'=',                     RESERVED),
    (r'[0-9]+',                INT),
    (r'boolean',               DATATYPE),
    (r'[A-Za-z][A-Za-z0-9_]*', ID)
]


def ql_lex(characters):
    return lex(characters, token_exprs)
