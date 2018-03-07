import unittest
from pyql.ast.expression import expressions, literals
from pyql.static_analysis.expression_visitor import ExpressionVisitor


class TestExpressionVisitor(unittest.TestCase):

    def setUp(self):
        self.expression_visitor_test = ExpressionVisitor()

    def test_visit_expression(self):
        self.fail()

    def test_visit_identifier(self):
        self.fail()

    def test_visit_unary_expression(self):
        self.fail()

    def test_visit_binary_expression(self):
        self.fail()

    def test_visit_multiplication(self):
        self.tester(self.assertEqual, expressions.Multiplication,
                    (literals.IntegerLiteral, literals.IntegerLiteral, literals.IntegerLiteral),
                    [(3, 7, 21), (0, 6, 0), (2, 0, 0), (-1, 9, -9), (4, -6, -24), (-7, -5, 35), (6, 6, 36)])

        self.tester(self.assertEqual, expressions.Multiplication,
                    (literals.DecimalLiteral, literals.DecimalLiteral, literals.DecimalLiteral),
                    [("3.0", "1.5", "4.5"), ("4.0", "0.0", "0.0"), ("-1.0", "9.0", "-9.0"), ("6.5", "-2.0", "-13.0")])

        self.tester(self.assertEqual, expressions.Multiplication,
                    (literals.IntegerLiteral, literals.DecimalLiteral, literals.DecimalLiteral),
                    [(3, "1.5", "4.5"), (0, "4.0", "0.0"), (-1.0, "9.0", "-9.0"), (6, "-2.0", "-12.0")])

        self.tester(self.assertEqual, expressions.Multiplication,
                    (literals.DecimalLiteral, literals.MoneyLiteral, literals.MoneyLiteral),
                    [("3.0", "1.50", "4.50"), ("4.0", "0.00", "0.00"), ("-1.0", "9.00", "-9.00"),
                     ("6.5", "-2.00", "-13.00")])

        self.tester(self.assertNotEqual, expressions.Multiplication,
                    (literals.IntegerLiteral, literals.IntegerLiteral, literals.IntegerLiteral),
                    [(3, 7, 20), (0, 6, 1), (2, 0, 2), (-1, 9, 9), (4, -6, 24), (-7, -5, -35), (6, 6, 6)])

        self.tester(self.assertNotEqual, expressions.Multiplication,
                    (literals.DecimalLiteral, literals.MoneyLiteral, literals.MoneyLiteral),
                    [("3.0", "1.50", "4.51"), ("4.0", "0.00", "4.00"), ("-1.0", "9.00", "9.00"),
                     ("6.5", "-2.00", "13.00")])

    def test_visit_division(self):
        pass
        # self.tester(lambda: self.assertRaises(ZeroDivisionError), expressions.Division,
        #             (literals.IntegerLiteral, literals.IntegerLiteral, ),
        #             [(6, 0), (2, 0), (-3, 0), (0, 0)])

        with self.assertRaises(ZeroDivisionError):
            test_cases = [(6, 0), (2, 0), (-3, 0), (0, 0), (1, 1)]
            for (left, right) in test_cases:
                test_division = expressions.Division("",
                                                     literals.IntegerLiteral("", left),
                                                     literals.IntegerLiteral("", right))

                test_division.accept(self.expression_visitor_test)

        test_cases_valid = [(3, 7, 21), (0, 6, 0), (-1, 9, -9), (4, -6, -24), (-7, -5, 35), (6, 6, 36)]
        for (result, right, left) in test_cases_valid:
            test_division = expressions.Division("",
                                                 literals.IntegerLiteral("", left),
                                                 literals.IntegerLiteral("", right))

            division_result = test_division.accept(self.expression_visitor_test)
            test_mock_result = literals.IntegerLiteral("", result)

            self.assertEqual(test_mock_result, division_result)

        test_cases_invalid = [(20, 7, 3), (6, 1, 0), (-1, 9, 9), (24, -6, 4), (-35, -5, -7), (6, 6, 6)]
        for (left, right, result) in test_cases_invalid:
            test_division = expressions.Division("",
                                                 literals.IntegerLiteral("", left),
                                                 literals.IntegerLiteral("", right))

            division_result = test_division.accept(self.expression_visitor_test)
            test_mock_result = literals.IntegerLiteral("", result)

            self.assertNotEqual(test_mock_result, division_result)

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

    def tester(self, class_assert, class_expression, class_literals, test_cases):
        literals_count = len(class_literals)
        result_index = literals_count - 1
        for test_case in test_cases:
            if literals_count == 2:
                left = test_case[0]
                test_expression = class_expression("",
                                                   class_literals[0]("", left))
            if literals_count == 3:
                left = test_case[0]
                right = test_case[1]
                test_expression = class_expression("",
                                                   class_literals[0]("", left),
                                                   class_literals[1]("", right))

            result = test_case[result_index]
            test_expression_result = test_expression.accept(self.expression_visitor_test)
            test_mock_result = class_literals[result_index]("", result)

            class_assert(test_mock_result, test_expression_result)


if __name__ == '__main__':
    unittest.main()
