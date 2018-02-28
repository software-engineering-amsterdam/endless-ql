# -*- coding: utf-8 -*-
from functools import reduce
from parse.combinators import *
from lexer.ql_lexer import *
from ql_ast.ql_ast import *


# Top level parser
def ql_parser(tokens):
    logger.debug('Start parser')
    ql_ast = parser()(tokens, 0)
    return ql_ast


def parser():
    return Phrase(stmt_list())


# Statements
def stmt_list():
    separator = keyword('\n') ^ (lambda x: lambda l, r: CompoundStatement(l, r))
    return Exp(stmt(), separator)


# Basic parsers

def keyword(kw):
    return Reserved(kw, 'reserved')


# Helper

def get_tags():
    num = Tag('int') ^ (lambda i: int(i))
    id = Tag('id')
    value = Tag('value')
    boolean = Tag('boolean')
    form = Tag('form')
    return num, id, value, boolean, form


# todo: make next line accept arbitrary amount
def next_line():
    return keyword('\n')


# Statements
def stmt():
    """
    Possible patterns to be recognized
    """
    return form_stmt() | assign_stmt() | if_stmt()


def form_stmt():
    """
    form Box1HouseOwning {...}
    """
    def process(parsed):
        ((((((_, name), _), _), parsed_form), _), _) = parsed
        return FormStatement(name, parsed_form)

    num, id, value, boolean, form = get_tags()
    return form + id + keyword('{') + next_line() + Lazy(stmt_list) + \
        next_line() + keyword('}') ^ process


def assign_stmt():
    """
    hasSoldHouse: "Did you sell a house in 2010?" boolean
    """
    def process(parsed):
        (((name, _), question), data_type) = parsed
        return AssignStatement(name, question, data_type)

    num, id, value, boolean, form = get_tags()
    return id + keyword(':') + value + (keyword('boolean') | keyword('money')) ^ process


def if_stmt():
    """
    if (hasSoldHouse) {...}
    """
    def process(parsed):
        ((((((((_, _), condition), _), _), _), true_stmt), _), _) = parsed
        return IfStatement(condition, true_stmt)

    num, id, value, boolean, form = get_tags()
    return keyword('if') + keyword('(') + id + keyword(')') + keyword('{') + next_line() + \
        Lazy(stmt_list) + next_line() + keyword('}') ^ process


# todo: result_stmt
def result_stmt():
    """
    valueResidue: "Value residue:" money(sellingPrice - privateDebt)
    """
    def process(parsed):
        (((name, _), question), data_type) = parsed
        return AssignStatement(name, question, data_type)

    num, id, value, boolean, form = get_tags()
    return id + keyword(':') + value + (keyword('boolean') | keyword('money') ) ^ process


# Boolean expressions
def bexp():
    return precedence(bexp_group(), bexp_precedence_levels, process_logic)


# def bexp_term():
#     return bexp_group()


# def bexp_not():
#     return keyword('not') + Lazy(bexp_term) ^ (lambda parsed: NotBexp(parsed[1]))


# def bexp_relop():
#     relops = ['<', '<=', '>', '>=', '=', '!=']
#     return aexp() + any_operator_in_list(relops) + aexp() ^ process_relop


def bexp_group():
    return keyword('(') + keyword('id') + keyword(')') ^ process_group


# Arithmetic expressions
def aexp():
    return precedence(aexp_term(),
                      aexp_precedence_levels,
                      process_binop)


def aexp_term():
    return aexp_value() | aexp_group()


def aexp_group():
    return keyword('(') + Lazy(aexp) + keyword(')') ^ process_group


def aexp_value():
    return (Tag('int') ^ (lambda i: int(i)) ^ (lambda i: IntAexp(i))) | \
           (Tag('id') ^ (lambda v: VarAexp(v)))


# Combinator for binary operator expressions (aexp and bexp)
def precedence(value_parser, precedence_levels, combine):
    def op_parser(precedence_level):
        return any_operator_in_list(precedence_level) ^ combine
    parser = value_parser * op_parser(precedence_levels[0])
    for precedence_level in precedence_levels[1:]:
        parser = parser * op_parser(precedence_level)
    return parser


# Miscellaneous functions for binary and relational operators
def process_binop(op):
    return lambda l, r: BinopAexp(op, l, r)


def process_relop(parsed):
    ((left, op), right) = parsed
    return RelopBexp(op, left, right)


def process_logic(op):
    if op == 'and':
        return lambda l, r: AndBexp(l, r)
    elif op == 'or':
        return lambda l, r: OrBexp(l, r)
    else:
        raise RuntimeError('unknown logic operator: ' + op)


def process_group(parsed):
    ((_, p), _) = parsed
    return p


def any_operator_in_list(ops):
    op_parsers = [keyword(op) for op in ops]
    parser = reduce(lambda l, r: l | r, op_parsers)
    return parser


# Operator keywords and precedence levels
aexp_precedence_levels = [
    ['*', '/'],
    ['+', '-'],
]

bexp_precedence_levels = [
    ['and'],
    ['or'],
]
