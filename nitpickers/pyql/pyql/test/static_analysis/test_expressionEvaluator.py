import unittest
from pyql.ast.expression import expressions, literals
from pyql.static_analysis import expression_evaluator


class TestExpressionEvaluator(unittest.TestCase):

    def setUp(self):
        self.expression_visitor_test = expression_evaluator.ExpressionEvaluator()

    def test_visit_expression(self):
        self.fail()

    def test_visit_identifier(self):
        self.fail()

    def test_visit_unary_expression(self):
        self.fail()

    def test_visit_binary_expression(self):
        self.fail()

    def test_visit_multiplication(self):
        test_cases = [(3, 7, 21), (0, 6, 0), (2, 0, 0), (-1, 9, -9), (4, -6, -24), (-7, -5, 35), (6, 6, 36)]

        for (left, right, result) in test_cases:
            test_multiplication = expressions.Multiplication("",
                                                             literals.IntegerLiteral("", left),
                                                             literals.IntegerLiteral("", right))
            multiplication_result = test_multiplication.accept(self.expression_visitor_test)
            self.assertEqual(literals.IntegerLiteral("", result), multiplication_result)

    def test_visit_division(self):
        self.fail()

    def test_visit_addition(self):
        self.fail()

    def test_visit_subtraction(self):
        self.fail()

    def test_visit_greater_than(self):
        self.fail()

    def test_visit_less_than(self):
        self.fail()

    def test_visit_greater_than_or_equal(self):
        self.fail()

    def test_visit_less_than_or_equal(self):
        self.fail()

    def test_visit_equals(self):
        self.fail()

    def test_visit_not_equals(self):
        self.fail()

    def test_visit_and(self):
        self.fail()

    def test_visit_or(self):
        self.fail()

    def test_visit_not(self):
        self.fail()

    def test_visit_literal(self):
        self.fail()

    def test_visit_string_literal(self):
        self.fail()

    def test_visit_integer_literal(self):
        self.fail()

    def test_visit_decimal_literal(self):
        self.fail()

    def test_visit_boolean_literal(self):
        self.fail()

    def test_visit_money_literal(self):
        self.fail()


if __name__ == '__main__':
    unittest.main()
