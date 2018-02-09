from lexer.lexer import lex

# QL token categories
RESERVED = 'RESERVED'
INT      = 'INT'
ID       = 'ID'
FORM     = 'FORM'


token_exprs = [
    # todo: write tokens for util/input.ql lexer
    (r'[ \n\t]+',              None),
    (r'#[^\n]*',               None),
    (r'form',                  FORM),
    (r'[A-Za-z][A-Za-z0-9_]*', ID),
    (r'{',                     RESERVED),
    (r'}',                     RESERVED),
    (r'"(.*?)"',               RESERVED),
    (r':',                     RESERVED),
    (r'\(',                    RESERVED),
    (r'\)',                    RESERVED),
    (r'-',                     RESERVED),
    (r'=',                     RESERVED),
    (r'[0-9]+',                INT)
]


def ql_lex(characters):
    return lex(characters, token_exprs)
