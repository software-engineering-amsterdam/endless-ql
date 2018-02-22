import unittest
from pyql.ast import code_location
from pyql.ast.form import block


class TestBlock(unittest.TestCase):

    def setUp(self):
        self.test_location = code_location.CodeLocation(1, 1)
        self.test_statements = []
        self.test_block = block.Block(self.test_location, self.test_statements)

    def test_init(self):
        self.assertEqual(self.test_block.location, self.test_location)
        self.assertEqual(self.test_block.statements, self.test_statements)


if __name__ == '__main__':
    unittest.main()
