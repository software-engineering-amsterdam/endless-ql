from ql.test.test import Test


class TestParsing(Test):
    def __init__(self, directory, lexer, parser):
        super(TestParsing, self).__init__('parsing', directory)
        self.__lexer = lexer
        self.__parser = parser

    def test_file(self, file):
        self.__parser.parse(file, self.__lexer.lexer)

        if self.__parser.errors:
            return False

        return True
