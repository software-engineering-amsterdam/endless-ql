import unittest
from pyql.util.values import *
from pyql.static_analysis.expression_evaluator import ExpressionEvaluator
from pyql.static_analysis.symbol_table import SymbolTable


class TestExpressionEvaluation(unittest.TestCase):

    def setUp(self):
        symbol_table = SymbolTable()
        self.expression_evaluator = ExpressionEvaluator(symbol_table)

    def test_multiplication(self):
        self.tester(self.assertEqual, Multiplication,
                    (IntegerValue, IntegerValue, IntegerValue),
                    [(3, 7, 21), (0, 6, 0), (2, 0, 0), (-1, 9, -9), (4, -6, -24), (-7, -5, 35), (6, 6, 36)])

        self.tester(self.assertEqual, Multiplication,
                    (DecimalValue, DecimalValue, DecimalValue),
                    [("3.0", "1.5", "4.5"), ("4.0", "0.0", "0.0"), ("-1.0", "9.0", "-9.0"), ("6.5", "-2.0", "-13.0")])

        self.tester(self.assertEqual, Multiplication,
                    (IntegerValue, DecimalValue, DecimalValue),
                    [(3, "1.5", "4.5"), (0, "4.0", "0.0"), (-1.0, "9.0", "-9.0"), (6, "-2.0", "-12.0")])

        self.tester(self.assertEqual, Multiplication,
                    (DecimalValue, MoneyValue, MoneyValue),
                    [("3.0", "1.50", "4.50"), ("4.0", "0.00", "0.00"), ("-1.0", "9.00", "-9.00"),
                     ("6.5", "-2.00", "-13.00")])

        self.tester(self.assertNotEqual, Multiplication,
                    (IntegerValue, IntegerValue, IntegerValue),
                    [(3, 7, 20), (0, 6, 1), (2, 0, 2), (-1, 9, 9), (4, -6, 24), (-7, -5, -35), (6, 6, 6)])

        self.tester(self.assertNotEqual, Multiplication,
                    (DecimalValue, MoneyValue, MoneyValue),
                    [("3.0", "1.50", "4.51"), ("4.0", "0.00", "4.00"), ("-1.0", "9.00", "9.00"),
                     ("6.5", "-2.00", "13.00")])

    def test_division(self):
        with self.assertRaises(ZeroDivisionError):
            test_cases = [(6, 0), (2, 0), (-3, 0), (0, 0), (1, 1)]
            for (left, right) in test_cases:
                Division().evaluate(IntegerValue(left), IntegerValue(right))

        test_cases_valid = [(3, 7, 21), (0, 6, 0), (-1, 9, -9), (4, -6, -24), (-7, -5, 35), (6, 6, 36)]
        for (result, right, left) in test_cases_valid:
            test_division = Division().evaluate(IntegerValue(left),
                                                IntegerValue(right))

            division_result = test_division
            test_mock_result = IntegerValue(result)

            self.assertEqual(test_mock_result, division_result)

        test_cases_invalid = [(20, 7, 3), (6, 1, 0), (-1, 9, 9), (24, -6, 4), (-35, -5, -7), (6, 6, 6)]
        for (left, right, result) in test_cases_invalid:
            test_division = Division().evaluate(IntegerValue(left), IntegerValue(right))

            division_result = test_division
            test_mock_result = IntegerValue(result)

            self.assertNotEqual(test_mock_result, division_result)

    def test_addition(self):
        self.fail()

    def test_subtraction(self):
        self.fail()

    def test_greater_than(self):
        self.fail()

    def test_less_than(self):
        self.fail()

    def test_greater_than_or_equal(self):
        self.fail()

    def test_less_than_or_equal(self):
        self.fail()

    def test_equals(self):
        self.fail()

    def test_not_equals(self):
        self.fail()

    def test_and(self):
        self.fail()

    def test_or(self):
        self.fail()

    def test_not(self):
        self.fail()

    def test_string_value(self):
        self.fail()

    def test_integer_value(self):
        self.fail()

    def test_decimal_value(self):
        self.fail()

    def test_boolean_value(self):
        self.fail()

    def test_money_value(self):
        self.fail()

    def tester(self, class_assert, expression, class_values, test_cases):
        values_count = len(class_values)
        result_index = values_count - 1
        for test_case in test_cases:
            if values_count == 2:
                left = test_case[0]
                test_expression = expression().evaluate(class_values[0](left))

            if values_count == 3:
                left = test_case[0]
                right = test_case[1]
                test_expression = expression().evaluate(class_values[0](left),
                                                        class_values[1](right))

            result = test_case[result_index]
            test_expression_result = test_expression
            test_mock_result = class_values[result_index](result)

            class_assert(test_mock_result, test_expression_result)


if __name__ == '__main__':
    unittest.main()
