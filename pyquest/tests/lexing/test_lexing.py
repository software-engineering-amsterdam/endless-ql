from tests.test import Test


class TestLexing(Test):
    def __init__(self, directory, lexer):
        super(TestLexing, self).__init__('lexing', directory)
        self.lexer = lexer

    def test_file(self, file):
        self.lexer.test(file)

        if self.lexer.errors:
            return False

        return True
