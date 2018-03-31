from tests.test import Test


class TestParsing(Test):
    def __init__(self, directory, lexer, parser):
        super(TestParsing, self).__init__('parsing', directory)
        self.lexer = lexer
        self.parser = parser

    def test_file(self, file):
        self.parser.parse(file, self.lexer.lexer)

        if self.parser.errors:
            return False

        return True
