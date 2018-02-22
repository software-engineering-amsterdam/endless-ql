import unittest
from pyql.ast import code_location
from pyql.ast.form import ql_statements

from pyql.ast.expression import expressions
from pyql.ast.form import block


class TestQuestion(unittest.TestCase):

    def setUp(self):
        self.test_location = code_location.CodeLocation(1, 1)
        self.test_identifier = "identifier"
        self.test_text = "text"
        self.test_type = "boolean"
        self.test_question = ql_statements.Question(self.test_location,
                                                    self.test_identifier,
                                                    self.test_text,
                                                    self.test_type)

    def test_init(self):
        self.assertEqual(self.test_question.location, self.test_location)
        self.assertEqual(self.test_question.identifier, self.test_identifier)
        self.assertEqual(self.test_question.text, self.test_text)
        self.assertEqual(self.test_question.question_type, self.test_type)


class TestComputedQuestion(unittest.TestCase):

    def setUp(self):
        self.test_location = code_location.CodeLocation(1, 1)
        self.test_identifier = "identifier"
        self.test_text = "text"
        self.test_type = "boolean"
        self.test_expression = expressions.GreaterThan(self.test_location, 5, 4)
        self.test_computed_question = ql_statements.ComputedQuestion(self.test_location,
                                                                     self.test_identifier,
                                                                     self.test_text,
                                                                     self.test_type,
                                                                     self.test_expression)

    def test_init(self):
        self.assertEqual(self.test_computed_question.location, self.test_location)
        self.assertEqual(self.test_computed_question.identifier, self.test_identifier)
        self.assertEqual(self.test_computed_question.text, self.test_text)
        self.assertEqual(self.test_computed_question.question_type, self.test_type)
        self.assertEqual(self.test_computed_question.expression, self.test_expression)

g
class TestIf(unittest.TestCase):

    def setUp(self):
        self.test_location = code_location.CodeLocation(1, 1)
        self.test_expression = expressions.GreaterThan(self.test_location, 5, 4)
        self.test_block = block.Block(self.test_location, [])
        self.test_if = ql_statements.If(self.test_location,
                                        self.test_expression,
                                        self.test_block)

    def test_init(self):
        self.assertEqual(self.test_if.location, self.test_location)
        self.assertEqual(self.test_if.expression, self.test_expression)
        self.assertEqual(self.test_if.block, self.test_block)


class TestIfElse(unittest.TestCase):

    def setUp(self):
        self.test_location = code_location.CodeLocation(1, 1)
        self.test_expression = expressions.GreaterThan(self.test_location, 5, 4)
        self.test_if_block = block.Block(self.test_location, [])
        self.test_else_block = block.Block(self.test_location, [])
        self.test_if_else = ql_statements.IfElse(self.test_location,
                                                 self.test_expression,
                                                 self.test_if_block,
                                                 self.test_else_block)

    def test_init(self):
        self.assertEqual(self.test_if_else.location, self.test_location)
        self.assertEqual(self.test_if_else.expression, self.test_expression)
        self.assertEqual(self.test_if_else.if_block, self.test_if_block)
        self.assertEqual(self.test_if_else.else_block, self.test_else_block)


if __name__ == '__main__':
    unittest.main()
