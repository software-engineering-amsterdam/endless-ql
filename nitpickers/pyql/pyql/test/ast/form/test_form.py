import unittest
from pyql.ast import code_location
from pyql.ast.form import form
from pyql.ast.form import block
from pyql.ast.form import question_statement


class TestForm(unittest.TestCase):

    def setUp(self):
        location_1 = code_location.CodeLocation(0, 0)
        statement_1 = question_statement.QuestionStatement(location_1, "identifier_1", "question_statement_1", "bool")
        statement_2 = question_statement.QuestionStatement(location_1, "identifier_2", "question_statement_2", "int")

        block_1 = block.Block(location_1, [statement_1, statement_2])
        self.form = form.Form("identifier", location_1, block_1)


if __name__ == '__main__':
    unittest.main()
