import unittest
from pyql.ast import code_location
from pyql.ast.form import ql_statements

from pyql.ast.expression import expressions
from pyql.ast.form import block


class TestQuestion(unittest.TestCase):

    def setUp(self):
        self._test_location = code_location.CodeLocation(1, 1)
        self._test_identifier = "identifier"
        self._test_text = "text"
        self._test_type = "boolean"
        self.question_statement = ql_statements.Question(self._test_location,
                                                         self._test_identifier,
                                                         self._test_text,
                                                         self._test_type)

    def test_init(self):
        self.assertEqual(self.question_statement.location, self._test_location)
        self.assertEqual(self.question_statement.identifier, self._test_identifier)
        self.assertEqual(self.question_statement.text, self._test_text)
        self.assertEqual(self.question_statement.question_type, self._test_type)


class TestIf(unittest.TestCase):

    def setUp(self):
        self._test_location = code_location.CodeLocation(1, 1)
        self._test_expression = expressions.GreaterThan(self._test_location, 5, 4)
        self._test_block = block.Block(self._test_location, [])
        self.question_statement = ql_statements.If(self._test_location,
                                                   self._test_expression,
                                                   self._test_block)

    def test_init(self):
        self.assertEqual(self.question_statement.location, self._test_location)
        self.assertEqual(self.question_statement.expression, self._test_expression)
        self.assertEqual(self.question_statement.block, self._test_block)


class TestIfElse(unittest.TestCase):

    def setUp(self):
        self._test_location = code_location.CodeLocation(1, 1)
        self._test_expression = expressions.GreaterThan(self._test_location, 5, 4)
        self._test_if_block = block.Block(self._test_location, [])
        self._test_else_block = block.Block(self._test_location, [])
        self.question_statement = ql_statements.IfElse(self._test_location,
                                                       self._test_expression,
                                                       self._test_if_block,
                                                       self._test_else_block)

    def test_init(self):
        self.assertEqual(self.question_statement.location, self._test_location)
        self.assertEqual(self.question_statement.expression, self._test_expression)
        self.assertEqual(self.question_statement.if_block, self._test_if_block)
        self.assertEqual(self.question_statement.else_block, self._test_else_block)


if __name__ == '__main__':
    unittest.main()
