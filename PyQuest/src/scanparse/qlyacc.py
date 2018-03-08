import ply.yacc as yacc
from scanparse import qllex

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
from AST.types.type_boolean import TypeBoolean
from AST.types.type_integer import TypeInteger
from AST.types.type_string import TypeString
from AST.types.type_date import TypeDate
from AST.types.type_money import TypeMoney
from AST.types.type_decimal import TypeDecimal
from AST.types.type_undefined import TypeUndefined


class QLParser:
    def __init__(self):
        self.tokens = qllex.LexTokenizer.tokens
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
        p[0] = FormNode(Position(p.lineno(1), p.lexpos(1)), p[4], p[2])

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
        p[0] = VariableNode(Position(p.lineno(1), p.lexpos(1)), TypeUndefined, p[1], None)

    # Unary operators TODO: unary minus
    @staticmethod
    def p_not(p):
        """expression : NOT expression"""
        p[0] = NegationOperatorNode(Position(p.lineno(1), p.lexpos(1)), TypeBoolean, p[2], None)

    # Binary operators
    @staticmethod
    def p_and(p):
        """expression : expression AND expression"""
        p[0] = AndOperatorNode(Position(p.lineno(2), p.lexpos(2)), TypeBoolean, p[1], p[3], None)

    @staticmethod
    def p_or(p):
        """expression : expression OR expression"""
        p[0] = OrOperatorNode(Position(p.lineno(2), p.lexpos(2)), TypeBoolean, p[1], p[3], None)

    @staticmethod
    def p_plus(p):
        """expression : expression PLUS expression"""
        p[0] = AdditionOperatorNode(Position(p.lineno(2), p.lexpos(2)), TypeUndefined, p[1], p[3], None)

    @staticmethod
    def p_minus(p):
        """expression : expression MINUS expression"""
        p[0] = SubtractionOperatorNode(Position(p.lineno(2), p.lexpos(2)), TypeUndefined, p[1], p[3], None)

    @staticmethod
    def p_times(p):
        """expression : expression TIMES expression"""
        p[0] = MultiplicationOperatorNode(Position(p.lineno(2), p.lexpos(2)), TypeUndefined, p[1], p[3], None)

    @staticmethod
    def p_divide(p):
        """expression : expression DIVIDE expression"""
        p[0] = DivisionOperatorNode(Position(p.lineno(2), p.lexpos(2)), TypeUndefined, p[1], p[3], None)

    @staticmethod
    def p_equals(p):
        """expression : expression EQ expression"""
        p[0] = EqualsOperatorNode(Position(p.lineno(2), p.lexpos(2)), TypeBoolean, p[1], p[3], None)

    @staticmethod
    def p_not_equals(p):
        """expression : expression NE expression"""
        p[0] = NotEqualsOperatorNode(Position(p.lineno(2), p.lexpos(2)), TypeBoolean, p[1], p[3], None)

    @staticmethod
    def p_less_equals(p):
        """expression : expression LE expression"""
        p[0] = LessEqualsOperatorNode(Position(p.lineno(2), p.lexpos(2)), TypeBoolean, p[1], p[3], None)

    @staticmethod
    def p_less_than(p):
        """expression : expression LT expression"""
        p[0] = LessThanOperatorNode(Position(p.lineno(2), p.lexpos(2)), TypeBoolean, p[1], p[3], None)

    @staticmethod
    def p_greater_equals(p):
        """expression : expression GE expression"""
        p[0] = GreaterEqualsOperatorNode(Position(p.lineno(2), p.lexpos(2)), TypeBoolean, p[1], p[3], None)

    @staticmethod
    def p_greater_than(p):
        """expression : expression GT expression"""
        p[0] = GreaterThanOperatorNode(Position(p.lineno(2), p.lexpos(2)), TypeBoolean, p[1], p[3], None)

    # Literals
    @staticmethod
    def p_number(p):
        """expression : NUMBER"""
        p[0] = IntegerNode(Position(p.lineno(1), p.lexpos(1)), TypeInteger, p[1])

    @staticmethod
    def p_float(p):
        """expression : FLOAT"""
        p[0] = DecimalNode(Position(p.lineno(1), p.lexpos(1)), TypeDecimal, p[1])

    # Types
    @staticmethod
    def p_boolean(p):
        """type : BOOLEAN"""
        p[0] = TypeBoolean

    @staticmethod
    def p_decimal(p):
        """type : DECIMAL"""
        p[0] = TypeDecimal

    @staticmethod
    def p_string(p):
        """type : STRING"""
        p[0] = TypeString

    @staticmethod
    def p_date(p):
        """type : DATE"""
        p[0] = TypeDate

    @staticmethod
    def p_money(p):
        """type : MONEY"""
        p[0] = TypeMoney

    @staticmethod
    def p_integer(p):
        """type : INTEGER"""
        p[0] = TypeInteger

    # Error handling
    @staticmethod
    def p_if_condition_error(p):
        """if   : IF LPAREN expression PLUS expression RPAREN LBRACKET statements RBRACKET
                | IF LPAREN expression MINUS expression RPAREN LBRACKET statements RBRACKET
                | IF LPAREN expression TIMES expression RPAREN LBRACKET statements RBRACKET
                | IF LPAREN expression DIVIDE expression RPAREN LBRACKET statements RBRACKET"""
        print('Condition of if statement does not evaluate to boolean.')
        raise SyntaxError

    # @staticmethod
    # def p_form_error(p):
    #     """form : FORM VAR LBRACKET RBRACKET"""
    #     print('Empty form.')
    #     raise SyntaxError

    def p_error(self, p):
        raise SyntaxError
