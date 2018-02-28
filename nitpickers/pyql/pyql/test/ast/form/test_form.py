import unittest
from pyql.ast import code_location
from pyql.ast.form import form
from pyql.ast.form import block


class TestForm(unittest.TestCase):

    def setUp(self):
        self.test_location = code_location.CodeLocation(1, 1)
        self.test_identifier = "identifier"
        self.test_block = block.Block(self.test_location, [])
        self.test_form = form.Form(self.test_location, self.test_identifier, self.test_block)

    def test_init(self):
        self.assertEqual(self.test_form.location, self.test_location)
        self.assertEqual(self.test_form.identifier, self.test_identifier)
        self.assertEqual(self.test_form.block, self.test_block)


if __name__ == '__main__':
    unittest.main()
