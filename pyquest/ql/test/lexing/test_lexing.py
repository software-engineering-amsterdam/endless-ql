from ql.test.test import Test


class TestLexing(Test):
    def __init__(self, directory, lexer):
        super(TestLexing, self).__init__('lexing', directory)
        self.__lexer = lexer

    def test_file(self, file):
        self.__lexer.tokenize(file)

        if self.__lexer.errors:
            return False

        return True
