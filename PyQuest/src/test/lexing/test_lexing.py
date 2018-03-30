from src.test.test import Test


class TestLexing(Test):
    def __init__(self, name, directory, lexer):
        super(TestLexing, self).__init__(name, directory)
        self.lexer = lexer

    def test_file(self, file):
        self.lexer.test(file)

        if self.lexer.errors:
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
    test = TestLexing('parsing', '', lexer)
    test.test()
