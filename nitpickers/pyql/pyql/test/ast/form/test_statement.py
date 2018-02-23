import unittest
from pyql.ast import code_location
from pyql.ast.form import statement


class TestStatement(unittest.TestCase):

    def setUp(self):
        self.test_location = code_location.CodeLocation(1, 1)
        self.test_statement = statement.Statement(self.test_location)

    def test_init(self):
        self.assertEqual(self.test_statement.location, self.test_location)


if __name__ == '__main__':
    unittest.main()
