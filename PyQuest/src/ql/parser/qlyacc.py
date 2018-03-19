from ply.yacc import yacc
from ql.parser import qllex
from ql.ast.position import Position
from ql.ast.expressions.variable_node import VariableNode
from ql.ast.expressions.binary_operators.addition_node import AdditionOperatorNode
from ql.ast.expressions.binary_operators.and_node import AndOperatorNode
from ql.ast.expressions.binary_operators.division_node import DivisionOperatorNode
from ql.ast.expressions.binary_operators.equals_node import EqualsOperatorNode
from ql.ast.expressions.binary_operators.greater_equals_node import GreaterEqualsOperatorNode
from ql.ast.expressions.binary_operators.greater_than_node import GreaterThanOperatorNode
from ql.ast.expressions.binary_operators.less_equals_node import LessEqualsOperatorNode
from ql.ast.expressions.binary_operators.less_than_node import LessThanOperatorNode
from ql.ast.expressions.binary_operators.multiplication_node import MultiplicationOperatorNode
from ql.ast.expressions.binary_operators.not_equals_node import NotEqualsOperatorNode
from ql.ast.expressions.binary_operators.or_node import OrOperatorNode
from ql.ast.expressions.binary_operators.subtraction_node import SubtractionOperatorNode
from ql.ast.expressions.literals.boolean_node import BooleanNode
from ql.ast.expressions.literals.date_node import DateNode
from ql.ast.expressions.literals.integer_node import IntegerNode
from ql.ast.expressions.literals.decimal_node import DecimalNode
from ql.ast.expressions.literals.string_node import StringNode
from ql.ast.expressions.unary_operators.negation import NegationOperatorNode
from ql.ast.expressions.unary_operators.negative import NegativeOperatorNode
from ql.ast.statements.form_node import FormNode
from ql.ast.statements.if_node import IfNode
from ql.ast.statements.question_node import QuestionNode
from ql.types.boolean import QLBoolean
from ql.types.integer import QLInteger
from ql.types.string import QLString
from ql.types.date import QLDate
from ql.types.money import QLMoney
from ql.types.decimal import QLDecimal
from ql.types.undefined import QLUndefined


class QLParser:
    def __init__(self):
        self.tokens = qllex.QLLexer.tokens
        self.precedence = (
            ('left', 'OR'),
            ('left', 'AND'),
            ('nonassoc', 'EQ', 'NE'),
            ('nonassoc', 'LE', 'LT', 'GE', 'GT'),
            ('left', 'PLUS', 'MINUS'),
            ('left', 'TIMES', 'DIVIDE'),
            ('right', 'NOT'),
        )
        self.parser = yacc(module=self)

    def parse(self, data, lexer):
        self.parser.parse(data, lexer)

    # Statements
    @staticmethod
    def p_form(p):
        """form : FORM VARIABLE LEFT_BRACE statements RIGHT_BRACE"""
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
        """question : STRING_LITERAL VARIABLE COLON type"""
        p[0] = QuestionNode(Position(p.lineno(1), p.lexpos(1)), p[1], p[2], p[4],
                            p[4].get_literal_node(), False)

    @staticmethod
    def p_question_computed(p):
        """question : STRING_LITERAL VARIABLE COLON type ASSIGN expression"""
        p[0] = QuestionNode(Position(p.lineno(1), p.lexpos(1)), p[1], p[2], p[4], p[6], True)

    # Control Flow
    @staticmethod
    def p_if(p):
        """if : IF LEFT_BRACKET expression RIGHT_BRACKET LEFT_BRACE statements RIGHT_BRACE"""
        p[0] = IfNode(Position(p.lineno(1), p.lexpos(1)), p[6], p[3])

    @staticmethod
    def p_elif(p):
        """elif : ELSE_IF LEFT_BRACKET expression RIGHT_BRACKET LEFT_BRACE statements RIGHT_BRACE"""
        p[0] = ('ELSE_IF', p[3], p[6])

    @staticmethod
    def p_else(p):
        """else : ELSE LEFT_BRACE statements RIGHT_BRACE"""
        p[0] = ('ELSE', p[3])

    # Expressions
    @staticmethod
    def p_parenthesis(p):
        """expression : LEFT_BRACKET expression RIGHT_BRACKET"""
        p[0] = p[2]

    @staticmethod
    def p_variable(p):
        """expression : VARIABLE"""
        p[0] = VariableNode(Position(p.lineno(1), p.lexpos(1)), QLUndefined, p[1], QLUndefined())

    @staticmethod
    def p_not(p):
        """expression : NOT expression"""
        p[0] = NegationOperatorNode(Position(p.lineno(1), p.lexpos(1)), QLBoolean, p[2], QLUndefined())

    @staticmethod
    def p_negative(p):
        """expression : MINUS expression"""
        p[0] = NegativeOperatorNode(Position(p.lineno(1), p.lexpos(1)), QLUndefined, p[2], QLUndefined())

    # Binary operators
    @staticmethod
    def p_and(p):
        """expression : expression AND expression"""
        p[0] = AndOperatorNode(Position(p.lineno(2), p.lexpos(2)), QLBoolean, p[1], p[3], QLUndefined())

    @staticmethod
    def p_or(p):
        """expression : expression OR expression"""
        p[0] = OrOperatorNode(Position(p.lineno(2), p.lexpos(2)), QLBoolean, p[1], p[3], QLUndefined())

    @staticmethod
    def p_plus(p):
        """expression : expression PLUS expression"""
        p[0] = AdditionOperatorNode(Position(p.lineno(2), p.lexpos(2)), QLUndefined, p[1], p[3], QLUndefined())

    @staticmethod
    def p_minus(p):
        """expression : expression MINUS expression"""
        p[0] = SubtractionOperatorNode(Position(p.lineno(2), p.lexpos(2)), QLUndefined, p[1], p[3], QLUndefined())

    @staticmethod
    def p_times(p):
        """expression : expression TIMES expression"""
        p[0] = MultiplicationOperatorNode(Position(p.lineno(2), p.lexpos(2)), QLUndefined, p[1], p[3], QLUndefined())

    @staticmethod
    def p_divide(p):
        """expression : expression DIVIDE expression"""
        p[0] = DivisionOperatorNode(Position(p.lineno(2), p.lexpos(2)), QLUndefined, p[1], p[3], QLUndefined())

    @staticmethod
    def p_equals(p):
        """expression : expression EQ expression"""
        p[0] = EqualsOperatorNode(Position(p.lineno(2), p.lexpos(2)), QLBoolean, p[1], p[3], QLUndefined())

    @staticmethod
    def p_not_equals(p):
        """expression : expression NE expression"""
        p[0] = NotEqualsOperatorNode(Position(p.lineno(2), p.lexpos(2)), QLBoolean, p[1], p[3], QLUndefined())

    @staticmethod
    def p_less_equals(p):
        """expression : expression LE expression"""
        p[0] = LessEqualsOperatorNode(Position(p.lineno(2), p.lexpos(2)), QLBoolean, p[1], p[3], QLUndefined())

    @staticmethod
    def p_less_than(p):
        """expression : expression LT expression"""
        p[0] = LessThanOperatorNode(Position(p.lineno(2), p.lexpos(2)), QLBoolean, p[1], p[3], QLUndefined())

    @staticmethod
    def p_greater_equals(p):
        """expression : expression GE expression"""
        p[0] = GreaterEqualsOperatorNode(Position(p.lineno(2), p.lexpos(2)), QLBoolean, p[1], p[3], QLUndefined())

    @staticmethod
    def p_greater_than(p):
        """expression : expression GT expression"""
        p[0] = GreaterThanOperatorNode(Position(p.lineno(2), p.lexpos(2)), QLBoolean, p[1], p[3], QLUndefined())

    # Literals
    @staticmethod
    def p_boolean_literal(p):
        """expression   : FALSE
                        | TRUE"""
        p[0] = BooleanNode(Position(p.lineno(1), p.lexpos(1)), QLBoolean, p[1])

    @staticmethod
    def p_date_literal(p):
        """expression : DATE_LITERAL"""
        p[0] = DateNode(Position(p.lineno(1), p.lexpos(1)), QLDate, p[1])

    @staticmethod
    def p_integer_literal(p):
        """expression : INTEGER_LITERAL"""
        p[0] = IntegerNode(Position(p.lineno(1), p.lexpos(1)), QLInteger, p[1])

    @staticmethod
    def p_decimal_literal(p):
        """expression : DECIMAL_LITERAL"""
        p[0] = DecimalNode(Position(p.lineno(1), p.lexpos(1)), QLDecimal, p[1])

    @staticmethod
    def p_string_literal(p):
        """expression : STRING_LITERAL"""
        p[0] = StringNode(Position(p.lineno(1), p.lexpos(1)), QLString, QLString([1]))

    # Types
    @staticmethod
    def p_boolean(p):
        """type : BOOLEAN"""
        p[0] = QLBoolean

    @staticmethod
    def p_decimal(p):
        """type : DECIMAL"""
        p[0] = QLDecimal

    @staticmethod
    def p_string(p):
        """type : STRING"""
        p[0] = QLString

    @staticmethod
    def p_date(p):
        """type : DATE"""
        p[0] = QLDate

    @staticmethod
    def p_money(p):
        """type : MONEY"""
        p[0] = QLMoney

    @staticmethod
    def p_integer(p):
        """type : INTEGER"""
        p[0] = QLInteger

    # Error handling
    @staticmethod
    def p_if_condition_error(p):
        """if   : IF LEFT_BRACKET expression PLUS expression RIGHT_BRACKET LEFT_BRACE statements RIGHT_BRACE
                | IF LEFT_BRACKET expression MINUS expression RIGHT_BRACKET LEFT_BRACE statements RIGHT_BRACE
                | IF LEFT_BRACKET expression TIMES expression RIGHT_BRACKET LEFT_BRACE statements RIGHT_BRACE
                | IF LEFT_BRACKET expression DIVIDE expression RIGHT_BRACKET LEFT_BRACE statements RIGHT_BRACE"""
        print('Condition of if statement does not evaluate to boolean.')
        raise SyntaxError

    # @staticmethod
    # def p_form_error(p):
    #     """form : FORM VARIABLE LEFT_BRACE RIGHT_BRACE"""
    #     print('Empty form.')
    #     raise SyntaxError

    def p_error(self, p):
        raise SyntaxError
