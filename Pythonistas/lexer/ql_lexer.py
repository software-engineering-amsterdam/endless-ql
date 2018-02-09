from lexer.lexer import lex

# QL token categories
RESERVED = 'RESERVED'
INT      = 'INT'
ID       = 'ID'
FORM     = 'FORM'


token_exprs = [
    # todo: write tokens for util/input.ql lexer
    (r'form',                  FORM),
    (r'{',                     RESERVED),
    (r'}',                     RESERVED),
    (r'"(.*?)"',               RESERVED),
    (r':',                     RESERVED)
]


def ql_lex(characters):
    return lex(characters, token_exprs)
