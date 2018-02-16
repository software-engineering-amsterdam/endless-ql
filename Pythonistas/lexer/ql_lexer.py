from lexer.lexer import lex

# QL token categories, todo: remove from being a global variable, config?
RESERVED = 'RESERVED'
INT      = 'INT'
ID       = 'ID'
FORM     = 'FORM'
BOOLEAN  = 'BOOLEAN'
VALUE    = 'VALUE'


token_exprs = [
    # todo: write tokens for util/input.ql lexer
    (r' +',                    None),
    (r'form',                  FORM),
    (r'[\n]',                  RESERVED),
    (r'{',                     RESERVED),
    (r'}',                     RESERVED),
    (r'"(.*?)"',               VALUE),
    (r':',                     RESERVED),
    (r'\(',                    RESERVED),
    (r'\)',                    RESERVED),
    (r'-',                     RESERVED),
    (r'=',                     RESERVED),
    (r'[0-9]+',                INT),
    (r'boolean',               RESERVED),
    (r'[A-Za-z][A-Za-z0-9_]*', ID)
]


def ql_lex(characters):
    return lex(characters, token_exprs)
