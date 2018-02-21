from lexer.lexer import lex


def get_token_exprs():
    # todo: find a clean solution for this, config?
    return [
        (r' +',                    None),
        (r'form',                  'form'),
        (r'[\n]',                  'reserved'),
        (r'{',                     'reserved'),
        (r'}',                     'reserved'),
        (r'"(.*?)"',               'value'),
        (r':',                     'reserved'),
        (r'if',                    'reserved'),
        (r'\(',                    'reserved'),
        (r'\)',                    'reserved'),
        (r'-',                     'reserved'),
        (r'=',                     'reserved'),
        (r'[0-9]+',                'int'),
        (r'boolean',               'reserved'),
        (r'[A-Za-z][A-Za-z0-9_]*', 'id')
    ]


def ql_lex(characters):
    return lex(characters, get_token_exprs())
