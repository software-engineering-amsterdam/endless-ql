import ply.yacc as yacc
import src.scanparse.qllex
from src.AST.nodes import *


class QLParser:
    def __init__(self):
        self.tokens = src.scanparse.qllex.LexTokenizer.tokens
        self.precedence = (
            ('left', 'OR'),
            ('left', 'AND'),
            ('nonassoc', 'EQ', 'NE'),
            ('nonassoc', 'LE', 'LT', 'GE', 'GT'),
            ('left', 'PLUS', 'MINUS'),
            ('left', 'TIMES', 'DIVIDE'),
            ('right', 'NOT'),
        )
        self.parser = yacc.yacc(module=self)

    def parse(self, data, lexer):
        self.parser.parse(data, lexer)

    # Statements
    @staticmethod
    def p_form(p):
        """form : FORM VAR LBRACKET statements RBRACKET"""
        p[0] = ('FORM', p[2], p[4])

    @staticmethod
    def p_statements(p):
        """statements   : statement statements
                        | statement"""
        if len(p) == 3:
            p[0] = [p[1]] + p[2]
        elif len(p) == 2:
            p[0] = [p[1]]

    @staticmethod
    def p_statement(p):
        """statement    : if
                        | elif
                        | else
                        | question
                        | form"""
        p[0] = p[1]

    # Questions and answers
    @staticmethod
    def p_question(p):
        """question : QUESTION answer"""
        p[0] = ('QUESTION', p[1], p[2])

    @staticmethod
    def p_answer(p):
        """answer : VAR COLON type"""
        p[0] = ('ANSWER', p[1], p[3])

    @staticmethod
    def p_answer_assign(p):
        """answer : VAR COLON type ASSIGN expression"""
        p[0] = ('ANSWER', p[1], p[3], p[5])

    # Control Flow
    @staticmethod
    def p_if(p):
        """if : IF LPAREN expression RPAREN LBRACKET statements RBRACKET"""
        p[0] = ('IF', p[3], p[6])

    @staticmethod
    def p_elif(p):
        """elif : ELIF LPAREN expression RPAREN LBRACKET statements RBRACKET"""
        p[0] = ('ELIF', p[3], p[6])

    @staticmethod
    def p_else(p):
        """else : ELSE LBRACKET statements RBRACKET"""
        p[0] = ('ELSE', p[3])

    # Expressions
    @staticmethod
    def p_parenthesis(p):
        """expression : LPAREN expression RPAREN"""
        p[0] = p[2]

    @staticmethod
    def p_variable(p):
        """expression : VAR"""
        p[0] = p[1]

    # Unary operators
    @staticmethod
    def p_not(p):
        """expression : NOT expression"""
        p[0] = UnaryOperatorNode(p.lineno(1), bool, 'not', p[2])

    # Binary operators
    @staticmethod
    def p_and(p):
        """expression : expression AND expression"""
        p[0] = BinaryOperatorNode(p.lineno(2), bool, 'and', p[1], p[3])

    @staticmethod
    def p_or(p):
        """expression : expression OR expression"""
        p[0] = BinaryOperatorNode(p.lineno(2), bool, 'OR', p[1], p[3])

    @staticmethod
    def p_plus(p):
        """expression : expression PLUS expression"""
        p[0] = BinaryOperatorNode(p.lineno(2), None, 'PLUS', p[1], p[3])

    @staticmethod
    def p_minus(p):
        """expression : expression MINUS expression"""
        p[0] = BinaryOperatorNode(p.lineno(2), None, 'MINUS', p[1], p[3])

    @staticmethod
    def p_times(p):
        """expression : expression TIMES expression"""
        p[0] = BinaryOperatorNode(p.lineno(2), None, 'TIMES', p[1], p[3])

    @staticmethod
    def p_divide(p):
        """expression : expression DIVIDE expression"""
        p[0] = BinaryOperatorNode(p.lineno(2), None, 'DIVIDE', p[1], p[3])

    @staticmethod
    def p_equals(p):
        """expression : expression EQ expression"""
        p[0] = BinaryOperatorNode(p.lineno(2), bool, 'EQ', p[1], p[3])

    @staticmethod
    def p_not_equals(p):
        """expression : expression NE expression"""
        p[0] = BinaryOperatorNode(p.lineno(2), bool, 'NE', p[1], p[3])

    @staticmethod
    def p_less_equals(p):
        """expression : expression LE expression"""
        p[0] = BinaryOperatorNode(p.lineno(2), bool, 'LE', p[1], p[3])

    @staticmethod
    def p_less_than(p):
        """expression : expression LT expression"""
        p[0] = BinaryOperatorNode(p.lineno(2), bool, 'LT', p[1], p[3])

    @staticmethod
    def p_greater_equals(p):
        """expression : expression GE expression"""
        p[0] = BinaryOperatorNode(p.lineno(2), bool, 'EQ', p[1], p[3])

    @staticmethod
    def p_greater_than(p):
        """expression : expression GT expression"""
        p[0] = BinaryOperatorNode(p.lineno(2), bool, 'GT', p[1], p[3])

    # Literals
    @staticmethod
    def p_number(p):
        """expression : NUMBER"""
        p[0] = LiteralNode(p.lineno(1), int, p[1])

    @staticmethod
    def p_float(p):
        """expression : FLOAT"""
        p[0] = LiteralNode(p.lineno(1), float, p[1])

    # Other
    @staticmethod
    def p_type(p):
        """type : INTEGER
                | DECIMAL
                | BOOLEAN
                | STRING
                | DATE
                | MONEY"""
        p[0] = p[1]

    def p_error(self, p):
        print('error')
        print(p)
        if not p:
            print("End of File!")
            return

        # Read ahead looking for a closing '}'
        while True:
            tok = self.parser.token()  # Get the next token
            if not tok or tok.type == 'RBRACE':
                break
        self.parser.restart()
