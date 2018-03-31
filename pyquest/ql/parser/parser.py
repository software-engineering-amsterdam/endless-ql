from ply.yacc import yacc

from ql.ast.nodes.expressions.binary_operators.addition_node import AdditionOperatorNode
from ql.ast.nodes.expressions.binary_operators.and_node import AndOperatorNode
from ql.ast.nodes.expressions.binary_operators.division_node import DivisionOperatorNode
from ql.ast.nodes.expressions.binary_operators.equals_node import EqualsOperatorNode
from ql.ast.nodes.expressions.binary_operators.greater_equals_node import GreaterEqualsOperatorNode
from ql.ast.nodes.expressions.binary_operators.greater_than_node import GreaterThanOperatorNode
from ql.ast.nodes.expressions.binary_operators.less_equals_node import LessEqualsOperatorNode
from ql.ast.nodes.expressions.binary_operators.less_than_node import LessThanOperatorNode
from ql.ast.nodes.expressions.binary_operators.multiplication_node import MultiplicationOperatorNode
from ql.ast.nodes.expressions.binary_operators.not_equals_node import NotEqualsOperatorNode
from ql.ast.nodes.expressions.binary_operators.or_node import OrOperatorNode
from ql.ast.nodes.expressions.binary_operators.subtraction_node import SubtractionOperatorNode
from ql.ast.nodes.expressions.literals.boolean_node import BooleanNode
from ql.ast.nodes.expressions.literals.date_node import DateNode
from ql.ast.nodes.expressions.literals.decimal_node import DecimalNode
from ql.ast.nodes.expressions.literals.integer_node import IntegerNode
from ql.ast.nodes.expressions.literals.money_node import MoneyNode
from ql.ast.nodes.expressions.literals.string_node import StringNode
from ql.ast.nodes.expressions.unary_operators.negation_node import NegationOperatorNode
from ql.ast.nodes.expressions.unary_operators.negative_node import NegativeOperatorNode
from ql.ast.nodes.expressions.variable_node import VariableNode
from ql.ast.nodes.statements.form_node import FormNode
from ql.ast.nodes.statements.if_node import IfNode
from ql.ast.nodes.statements.question_node import QuestionNode
from ql.parser.lexer import QLLexer
from ql.parser.metadata import Metadata
from ql.types.boolean import QLBoolean
from ql.types.date import QLDate
from ql.types.decimal import QLDecimal
from ql.types.integer import QLInteger
from ql.types.money import QLMoney
from ql.types.string import QLString
from ql.types.undefined import QLUndefined


class QLParser:
    def __init__(self):
        self.__errors = []
        self.__tokens = QLLexer.tokens
        self.__precedence = (
            ('left', 'OR'),
            ('left', 'AND'),
            ('nonassoc', 'EQ', 'NE'),
            ('nonassoc', 'LE', 'LT', 'GE', 'GT'),
            ('left', 'PLUS', 'MINUS'),
            ('left', 'TIMES', 'DIVIDE'),
            ('right', 'NOT'),
        )
        self.parser = yacc(module=self)

    @property
    def errors(self):
        return self.__errors

    @property
    def tokens(self):
        return self.__tokens

    @property
    def precedence(self):
        return self.__precedence

    def parse(self, data, lexer):
        self.__errors = []
        return self.parser.parse(data, lexer)

    # Grammar
    @staticmethod
    def p_form(production):
        """form : FORM IDENTIFIER block"""
        production[0] = FormNode(Metadata(production.lineno(1)), production[3], production[2])

    @staticmethod
    def p_block(production):
        """block : LEFT_BRACE statements RIGHT_BRACE"""
        production[0] = production[2]

    # Statements
    @staticmethod
    def p_statements(production):
        """statements   : statement statements
                        | statement"""
        if len(production) == 3:
            production[0] = [production[1]] + production[2]
        elif len(production) == 2:
            production[0] = [production[1]]

    @staticmethod
    def p_statement(production):
        """statement    : if
                        | question"""
        production[0] = production[1]

    # Questions
    @staticmethod
    def p_question(production):
        """question : STRING_LITERAL IDENTIFIER COLON type"""
        production[0] = QuestionNode(Metadata(production.lineno(1)), production[1], production[2],
                                     production[4], production[4].get_literal_node(production[4]()), False)

    @staticmethod
    def p_question_computed(production):
        """question : STRING_LITERAL IDENTIFIER COLON type ASSIGN expression"""
        production[0] = QuestionNode(Metadata(production.lineno(1)), production[1], production[2],
                                     production[4], production[6], True)

    # Control flow
    @staticmethod
    def p_if(production):
        """if : IF condition block"""
        production[0] = IfNode(Metadata(production.lineno(1)), production[3], production[2])

    @staticmethod
    def p_condition(production):
        """condition : LEFT_BRACKET expression RIGHT_BRACKET"""
        production[0] = production[2]

    # Expressions
    @staticmethod
    def p_parenthesis(production):
        """expression : LEFT_BRACKET expression RIGHT_BRACKET"""
        production[0] = production[2]

    @staticmethod
    def p_variable(production):
        """expression : IDENTIFIER"""
        production[0] = VariableNode(Metadata(production.lineno(1)), QLUndefined, production[1],
                                     QLUndefined())

    # Unary operators
    @staticmethod
    def p_not(production):
        """expression : NOT expression"""
        production[0] = NegationOperatorNode(Metadata(production.lineno(1)), QLBoolean,
                                             production[2], QLUndefined())

    @staticmethod
    def p_negative(production):
        """expression : MINUS expression"""
        production[0] = NegativeOperatorNode(Metadata(production.lineno(1)), QLUndefined,
                                             production[2], QLUndefined())

    # Binary operators
    @staticmethod
    def p_and(production):
        """expression : expression AND expression"""
        production[0] = AndOperatorNode(Metadata(production.lineno(2)), QLBoolean, production[1],
                                        production[3], QLUndefined())

    @staticmethod
    def p_or(production):
        """expression : expression OR expression"""
        production[0] = OrOperatorNode(Metadata(production.lineno(2)), QLBoolean, production[1],
                                       production[3], QLUndefined())

    @staticmethod
    def p_plus(production):
        """expression : expression PLUS expression"""
        production[0] = AdditionOperatorNode(Metadata(production.lineno(2)), QLUndefined,
                                             production[1], production[3], QLUndefined())

    @staticmethod
    def p_minus(production):
        """expression : expression MINUS expression"""
        production[0] = SubtractionOperatorNode(Metadata(production.lineno(2)), QLUndefined,
                                                production[1], production[3], QLUndefined())

    @staticmethod
    def p_times(production):
        """expression : expression TIMES expression"""
        production[0] = MultiplicationOperatorNode(Metadata(production.lineno(2)), QLUndefined,
                                                   production[1], production[3], QLUndefined())

    @staticmethod
    def p_divide(production):
        """expression : expression DIVIDE expression"""
        production[0] = DivisionOperatorNode(Metadata(production.lineno(2)), QLUndefined,
                                             production[1], production[3], QLUndefined())

    @staticmethod
    def p_equals(production):
        """expression : expression EQ expression"""
        production[0] = EqualsOperatorNode(Metadata(production.lineno(2)), QLBoolean,
                                           production[1], production[3], QLUndefined())

    @staticmethod
    def p_not_equals(production):
        """expression : expression NE expression"""
        production[0] = NotEqualsOperatorNode(Metadata(production.lineno(2)), QLBoolean,
                                              production[1], production[3], QLUndefined())

    @staticmethod
    def p_less_equals(production):
        """expression : expression LE expression"""
        production[0] = LessEqualsOperatorNode(Metadata(production.lineno(2)), QLBoolean,
                                               production[1], production[3], QLUndefined())

    @staticmethod
    def p_less_than(production):
        """expression : expression LT expression"""
        production[0] = LessThanOperatorNode(Metadata(production.lineno(2)), QLBoolean,
                                             production[1], production[3], QLUndefined())

    @staticmethod
    def p_greater_equals(production):
        """expression : expression GE expression"""
        production[0] = GreaterEqualsOperatorNode(Metadata(production.lineno(2)), QLBoolean,
                                                  production[1], production[3], QLUndefined())

    @staticmethod
    def p_greater_than(production):
        """expression : expression GT expression"""
        production[0] = GreaterThanOperatorNode(Metadata(production.lineno(2)), QLBoolean,
                                                production[1], production[3], QLUndefined())

    # Literals
    @staticmethod
    def p_boolean_literal(production):
        """expression   : FALSE
                        | TRUE"""
        production[0] = BooleanNode(Metadata(production.lineno(1)), QLBoolean, production[1])

    @staticmethod
    def p_date_literal(production):
        """expression : DATE_LITERAL"""
        production[0] = DateNode(Metadata(production.lineno(1)), QLDate, production[1])

    @staticmethod
    def p_integer_literal(production):
        """expression : INTEGER_LITERAL"""
        production[0] = IntegerNode(Metadata(production.lineno(1)), QLInteger, production[1])

    @staticmethod
    def p_decimal_literal(production):
        """expression : DECIMAL_LITERAL"""
        production[0] = DecimalNode(Metadata(production.lineno(1)), QLDecimal, production[1])

    @staticmethod
    def p_money_literal(production):
        """expression   : currency DECIMAL_LITERAL
                        | currency INTEGER_LITERAL"""
        production[0] = MoneyNode(Metadata(production.lineno(1)), QLMoney,
                                  QLMoney(production[2], production[1]))

    @staticmethod
    def p_string_literal(production):
        """expression : STRING_LITERAL"""
        production[0] = StringNode(Metadata(production.lineno(1)), QLString, QLString([1]))

    # Currencies
    @staticmethod
    def p_currency(production):
        """currency : DOLLAR"""
        production[0] = production[1]

    # Types
    @staticmethod
    def p_boolean(production):
        """type : BOOLEAN"""
        production[0] = QLBoolean

    @staticmethod
    def p_decimal(production):
        """type : DECIMAL"""
        production[0] = QLDecimal

    @staticmethod
    def p_string(production):
        """type : STRING"""
        production[0] = QLString

    @staticmethod
    def p_date(production):
        """type : DATE"""
        production[0] = QLDate

    @staticmethod
    def p_money(production):
        """type : MONEY"""
        production[0] = QLMoney

    @staticmethod
    def p_integer(production):
        """type : INTEGER"""
        production[0] = QLInteger

    # Error
    def p_error(self, production):
        if production is None:
            self.errors.append('Syntax error.')
        else:
            self.errors.append('Syntax error at line {}, token={}.'.format(production.lineno, production.type))

    def p_form_label(self, production):
        """form : FORM LEFT_BRACE"""
        self.errors.append('Missing form identifier at line {}.'.format(production.lineno(1)))

    def p_empty_form(self, production):
        """form : FORM IDENTIFIER LEFT_BRACE RIGHT_BRACE"""
        self.errors.append('Empty form at line {}.'.format(production.lineno(1)))

    def p_empty_if(self, production):
        """statement : IF condition LEFT_BRACE RIGHT_BRACE"""
        self.errors.append('Empty if block at line {}.'.format(production.lineno(1)))

    def p_empty_condition(self, production):
        """condition : LEFT_BRACKET RIGHT_BRACKET"""
        self.errors.append('Empty conditional at line {}.'.format(production.lineno(1)))
