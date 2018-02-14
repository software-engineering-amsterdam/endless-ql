import ply.yacc as yacc
import lex

tokens = lex.LexTokenizer.tokens
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


def p_expr_float(p):
    """expr : FLOAT"""
    p[0] = ('FLOAT', p[1])


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


# Statements


def p_stmts(p):
    """stmts : stmt stmts
             | stmt"""
    # if (len(p) == 3):
    #     p[0] = ('STMTS', p[1], p[2])
    # elif (len(p) == 2):
    #     p[0] = p[1]


def p_stmt(p):
    """stmt : if
            | elif
            | else
            | question"""
    p[0] = p[1]


def p_form(p):
    """stmt : FORM VAR LBRACKET stmts RBRACKET"""
    p[0] = ('FORM', p[2], p[4])


# Questions and answers


def p_question(p):
    """question : QUESTION answer"""
    p[0] = ('QUESTION', p[1], p[2])


def p_answer(p):
    """answer : VAR COLON type"""
    p[0] = ('ANSWER', p[1], p[3])


def p_answer_assign(p):
    """answer : VAR COLON assign"""
    p[0] = ('ANSWER', p[1], p[3])


# Control Flow


def p_if(p):
    """if : IF LPAREN expr RPAREN LBRACKET stmts RBRACKET"""
    p[0] = ('IF', p[3], p[6])


def p_elif(p):
    """elif : ELIF LPAREN expr RPAREN LBRACKET stmts RBRACKET"""
    p[0] = ('ELIF', p[3], p[6])


def p_else(p):
    """else : ELSE LBRACKET stmts RBRACKET"""
    p[0] = ('ELSE', p[3])


# Other


def p_type(p):
    """type : INTEGER
            | DECIMAL
            | BOOLEAN
            | STRING
            | DATE
            | MONEY"""
    p[0] = p[1]


# Misc


def p_error(p):
    print("Whoa.")
    if not p:
        print("End of File!")
        return

    # Read ahead looking for a closing '}'
    while True:
        tok = parser.token()  # Get the next token
        if not tok or tok.type == 'RBRACE':
            break
    parser.restart()


# Build the parser
parser = yacc.yacc()
