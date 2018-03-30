from src.test.test import Test


class TestParsing(Test):
    def __init__(self, name, directory, lexer, parser):
        super(TestParsing, self).__init__(name, directory)
        self.lexer = lexer
        self.parser = parser

    def test_file(self, file):
        self.parser.parse(file, self.lexer.lexer)

        if self.parser.errors:
            return False

        return True


# TODO remove
if __name__ == '__main__':
    from ql.parser.lexer import QLLexer
    from ql.parser.parser import QLParser

    from os import listdir

    parser = QLParser()
    lexer = QLLexer()
    valid_files = sorted(listdir('valid'))
    invalid_files = sorted(listdir('invalid'))
    test = TestParsing('parsing', '', lexer, parser)
    test.test()
