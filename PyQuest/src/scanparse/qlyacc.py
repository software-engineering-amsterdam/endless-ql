import ply.yacc as yacc
import src.scanparse.qllex

from AST.position import Position
from AST.expressions.variable_node import VariableNode
from AST.expressions.binary_operators.addition_node import AdditionOperatorNode
from AST.expressions.binary_operators.and_node import AndOperatorNode
from AST.expressions.binary_operators.division_node import DivisionOperatorNode
from AST.expressions.binary_operators.equals_node import EqualsOperatorNode
from AST.expressions.binary_operators.greater_equals_node import GreaterEqualsOperatorNode
from AST.expressions.binary_operators.greater_than_node import GreaterThanOperatorNode
from AST.expressions.binary_operators.less_equals_node import LessEqualsOperatorNode
from AST.expressions.binary_operators.less_than_node import LessThanOperatorNode
from AST.expressions.binary_operators.multiplication_node import MultiplicationOperatorNode
from AST.expressions.binary_operators.not_equals_node import NotEqualsOperatorNode
from AST.expressions.binary_operators.or_node import OrOperatorNode
from AST.expressions.binary_operators.subtraction_node import SubtractionOperatorNode
from AST.expressions.literals.integer_node import IntegerNode
from AST.expressions.literals.decimal_node import DecimalNode
from AST.expressions.unary_operators.negation import NegationOperatorNode
from AST.statements.form_node import FormNode
from AST.statements.if_node import IfNode
from AST.statements.question_node import QuestionNode


class QLParser:
    def __init__(self):
        self.tokens = src.scanparse.qllex.LexTokenizer.tokens
        self.precedence = (
            ('left', 'OR'),
            ('left', 'AND'),
            ('nonassoc', 'EQ', 'NE'),
            ('nonassoc', 'LE', 'LT', 'GE', 'GT'),
            ('left', 'TIMES', 'DIVIDE'),
            ('left', 'PLUS', 'MINUS'),
            ('right', 'NOT'),
        )
        self.parser = yacc.yacc(module=self)

    def parse(self, data, lexer):
        self.parser.parse(data, lexer)

    # Statements
    @staticmethod
    def p_form(p):
        """form : FORM VAR LBRACKET statements RBRACKET"""
        p[0] = FormNode(p.lineno(1), p[4], p[2])

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

    # Questions
    @staticmethod
    def p_question(p):
        """question : QUESTION VAR COLON type"""
        p[0] = QuestionNode(Position(p.lineno(1), p.lexpos(1)), p[1], p[2], p[4], None)

    @staticmethod
    def p_question_computed(p):
        """question : QUESTION VAR COLON type ASSIGN expression"""
        p[0] = QuestionNode(Position(p.lineno(1), p.lexpos(1)), p[1], p[2], p[4], p[6])

    # Control Flow
    @staticmethod
    def p_if(p):
        """if : IF LPAREN expression RPAREN LBRACKET statements RBRACKET"""
        p[0] = IfNode(Position(p.lineno(1), p.lexpos(1)), p[6], p[3])

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
        p[0] = VariableNode(Position(p.lineno(1), p.lexpos(1)), None, p[1])

    # Unary operators TODO: unary minus
    @staticmethod
    def p_not(p):
        """expression : NOT expression"""
        p[0] = NegationOperatorNode(Position(p.lineno(1), p.lexpos(1)), bool, p[2])

    # Binary operators
    @staticmethod
    def p_and(p):
        """expression : expression AND expression"""
        p[0] = AndOperatorNode(Position(p.lineno(2), p.lexpos(2)), None, p[1], p[3])

    @staticmethod
    def p_or(p):
        """expression : expression OR expression"""
        p[0] = OrOperatorNode(Position(p.lineno(2), p.lexpos(2)), None, p[1], p[3])

    @staticmethod
    def p_plus(p):
        """expression : expression PLUS expression"""
        p[0] = AdditionOperatorNode(Position(p.lineno(2), p.lexpos(2)), None, p[1], p[3])

    @staticmethod
    def p_minus(p):
        """expression : expression MINUS expression"""
        p[0] = SubtractionOperatorNode(Position(p.lineno(2), p.lexpos(2)), None, p[1], p[3])

    @staticmethod
    def p_times(p):
        """expression : expression TIMES expression"""
        p[0] = MultiplicationOperatorNode(Position(p.lineno(2), p.lexpos(2)), None, p[1], p[3])

    @staticmethod
    def p_divide(p):
        """expression : expression DIVIDE expression"""
        p[0] = DivisionOperatorNode(Position(p.lineno(2), p.lexpos(2)), None, p[1], p[3])

    @staticmethod
    def p_equals(p):
        """expression : expression EQ expression"""
        p[0] = EqualsOperatorNode(Position(p.lineno(2), p.lexpos(2)), None, p[1], p[3])

    @staticmethod
    def p_not_equals(p):
        """expression : expression NE expression"""
        p[0] = NotEqualsOperatorNode(Position(p.lineno(2), p.lexpos(2)), None, p[1], p[3])

    @staticmethod
    def p_less_equals(p):
        """expression : expression LE expression"""
        p[0] = LessEqualsOperatorNode(Position(p.lineno(2), p.lexpos(2)), None, p[1], p[3])

    @staticmethod
    def p_less_than(p):
        """expression : expression LT expression"""
        p[0] = LessThanOperatorNode(Position(p.lineno(2), p.lexpos(2)), None, p[1], p[3])

    @staticmethod
    def p_greater_equals(p):
        """expression : expression GE expression"""
        p[0] = GreaterEqualsOperatorNode(Position(p.lineno(2), p.lexpos(2)), None, p[1], p[3])

    @staticmethod
    def p_greater_than(p):
        """expression : expression GT expression"""
        p[0] = GreaterThanOperatorNode(Position(p.lineno(2), p.lexpos(2)), None, p[1], p[3])

    # Literals
    @staticmethod
    def p_number(p):
        """expression : NUMBER"""
        p[0] = IntegerNode(Position(p.lineno(1), p.lexpos(1)), int, p[1])

    @staticmethod
    def p_float(p):
        """expression : FLOAT"""
        p[0] = DecimalNode(Position(p.lineno(1), p.lexpos(1)), int, p[1])

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

    # Error handling
    @staticmethod
    def p_if_condition_error(p):
        """if : IF LPAREN expression MINUS expression RPAREN LBRACKET statements RBRACKET"""
        print('Condition does not evaluate to boolean.')
        raise SyntaxError

    @staticmethod
    def p_form_error(p):
        """form : FORM VAR LBRACKET RBRACKET"""
        print('Empty form.')
        raise SyntaxError

    def p_error(self, p):
        if not p:
            print("End of File!")
            return

        # Read ahead looking for a closing '}'
        while True:
            tok = self.parser.token()  # Get the next token
            if not tok or tok.type == 'RBRACE':
                break
        self.parser.restart()
