import unittest
from pyql.ast import code_location
from pyql.ast.expression import expressions


class TestExpression(unittest.TestCase):

    def setUp(self):
        self.test_location = code_location.CodeLocation(1, 1)
        self.test_expression = expressions.Expression(self.test_location)

    def test_init(self):
        self.assertEqual(self.test_expression.location, self.test_location)


class TestIdentifier(unittest.TestCase):

    def setUp(self):
        self.test_location = code_location.CodeLocation(1, 1)
        self.test_identifier = "identifier"
        self.test_identifier_class = expressions.Identifier(self.test_location, self.test_identifier)

    def test_init(self):
        self.assertEqual(self.test_identifier_class.location, self.test_location)
        self.assertEqual(self.test_identifier_class.identifier, self.test_identifier)


class TestUnaryExpression(unittest.TestCase):

    def setUp(self):
        self.test_location = code_location.CodeLocation(1, 1)
        self.test_expression = expressions.GreaterThan(self.test_location, 5, 4)
        self.test_unary_expression = expressions.UnaryExpression(self.test_location, self.test_expression)

    def test_init(self):
        self.assertEqual(self.test_unary_expression.location, self.test_location)
        self.assertEqual(self.test_unary_expression.expression, self.test_expression)


class TestBinaryExpression(unittest.TestCase):

    def setUp(self):
        self.test_location = code_location.CodeLocation(1, 1)
        self.test_expression_1 = expressions.GreaterThan(self.test_location, 5, 4)
        self.test_expression_2 = expressions.Addition(self.test_location, 2, 2)
        self.test_binary_expression = expressions.BinaryExpression(self.test_location,
                                                                   self.test_expression_1,
                                                                   self.test_expression_2)

    def test_init(self):
        self.assertEqual(self.test_binary_expression.location, self.test_location)
        self.assertEqual(self.test_binary_expression.left, self.test_expression_1)
        self.assertEqual(self.test_binary_expression.right, self.test_expression_2)


class TestExpressions(unittest.TestCase):

    def setUp(self):
        self.test_location = code_location.CodeLocation(1, 1)
        self.test_expression_1 = expressions.GreaterThan(self.test_location, 5, 4)
        self.test_expression_2 = expressions.Addition(self.test_location, 2, 2)

        self.expressions = [expressions.Multiplication, expressions.Division, expressions.Addition,
                            expressions.Subtraction, expressions.GreaterThan, expressions.LessThan,
                            expressions.GreaterThanOrEqual, expressions.LessThanOrEqual, expressions.Equals,
                            expressions.NotEquals, expressions.And, expressions.Or]

        self.test_expressions = []
        for expression in self.expressions:
            self.test_expressions.append(expression(self.test_location, self.test_expression_1, self.test_expression_2))

    def test_init(self):
        for test_expression in self.test_expressions:
            self.assertEqual(test_expression.location, self.test_location)
            self.assertEqual(test_expression.left, self.test_expression_1)
            self.assertEqual(test_expression.right, self.test_expression_2)


class TestLiteral(unittest.TestCase):

    def setUp(self):
        self.test_location = code_location.CodeLocation(1, 1)
        self.test_literal = expressions.Literal(self.test_location)

    def test_init(self):
        self.assertEqual(self.test_literal.location, self.test_location)


if __name__ == '__main__':
    unittest.main()
