import ply.yacc as yacc
import lex


tokens = lex.tokens
precedence = (
    ('left', 'OR'),
    ('left', 'AND'),
    ('nonassoc', 'EQ', 'NE'),
    ('nonassoc', 'LE', 'LT', 'GE', 'GT'),
    ('left', 'PLUS', 'MINUS'),
    ('left', 'TIMES', 'DIVIDE'),
    ('right', 'NOT'),
)


# Booleans


def p_expr_and(p):
    """expr : expr AND expr"""
    p[0] = ('AND', p[1], p[3])


def p_expr_or(p):
    """expr : expr OR expr"""
    p[0] = ('OR', p[1], p[3])


def p_expr_not(p):
    """expr : NOT expr"""
    p[0] = ('NOT', p[2])


# Numbers


def p_expr_plus(p):
    """expr : expr PLUS expr"""
    p[0] = ('PLUS', p[1], p[3])


def p_expr_minus(p):
    """expr : expr MINUS expr"""
    p[0] = ('MINUS', p[1], p[3])


def p_expr_times(p):
    """expr : expr TIMES expr"""
    p[0] = ('TIMES', p[1], p[3])


def p_expr_divide(p):
    """expr : expr DIVIDE expr"""
    p[0] = ('DIVIDE', p[1], p[3])


def p_expr_number(p):
    """expr : NUMBER"""
    p[0] = ('NUMBER', p[1])


# Comparisons


def p_eq(p):
    """expr : expr EQ expr"""
    p[0] = ('EQ', p[1], p[3])


def p_ne(p):
    """expr : expr NE expr"""
    p[0] = ('NE', p[1], p[3])


def p_le(p):
    """expr : expr LE expr"""
    p[0] = ('LE', p[1], p[3])


def p_lt(p):
    """expr : expr LT expr"""
    p[0] = ('LT', p[1], p[3])


def p_ge(p):
    """expr : expr GE expr"""
    p[0] = ('GE', p[1], p[3])


def p_gt(p):
    """expr : expr GT expr"""
    p[0] = ('GT', p[1], p[3])


# Other expression grammar


def p_expr_brackets(p):
    """expr : LPAREN expr RPAREN"""
    p[0] = p[2]


def p_assign(p):
    """assign : VAR ASSIGN expr"""
    p[0] = ('ASSIGN', p[1], p[2])


# Misc


def p_error(p):
    print("Whoa. You are seriously hosed.")
    if not p:
        print("End of File!")
        return

    # Read ahead looking for a closing '}'
    while True:
        tok = parser.token()  # Get the next token
        if not tok or tok.type == 'RBRACE':
            break
    parser.restart()


yacc.yacc()