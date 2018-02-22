import unittest
from pyql.ast import code_location
from pyql.ast.form import question_statement


class TestQuestionStatement(unittest.TestCase):

    def setUp(self):
        self._test_location = code_location.CodeLocation(1, 1)
        self._test_identifier = "identifier"
        self._test_text = "text"
        self._test_type = "boolean"
        self.question_statement = question_statement.QuestionStatement(self._test_location,
                                                                       self._test_identifier,
                                                                       self._test_text,
                                                                       self._test_type)

    def test_init(self):
        self.assertEqual(self.question_statement.location, self._test_location)
        self.assertEqual(self.question_statement.identifier, self._test_identifier)
        self.assertEqual(self.question_statement.text, self._test_text)
        self.assertEqual(self.question_statement.question_type, self._test_type)


if __name__ == '__main__':
    unittest.main()
