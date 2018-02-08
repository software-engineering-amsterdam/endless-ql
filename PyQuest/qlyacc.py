import ply.yacc as yacc
import lex

tokens = lex.tokens

def p_expr_plus(p):
    '''expr : expr PLUS term'''
    p[0] = p[1] + p[3]

def p_expr_minus(p):
    '''expr : expr MINUS term'''
    p[0] = p[1] - p[3]

def p_expr_term(p):
    '''expr : term'''
    p[0] = p[1]

def p_term_times(p):
    '''term : term TIMES factor'''
    p[0] = p[1] * p[3]

def p_term_divide(p):
    '''term : term DIVIDE factor'''
    p[0] = p[1] / p[3]

def p_factor(p):
    '''term : factor'''
    p[0] = p[1]

def p_factor_num(p):
    '''term : NUMBER'''
    p[0] = p[1]

def p_factor_expr(p):
    '''factor : LBRACKET expr RBRACKET'''
    p[0] = p[2]