from os import listdir

from termcolor import colored

from ql.parser.lexer import QLLexer
from ql.parser.parser import QLParser


def test_directory(directory, parser, lexer):
    success_counter = 0
    directory_valid_tests = directory + '/valid/'
    directory_invalid_tests = directory + '/invalid/'
    valid_files = sorted(listdir(directory_valid_tests))
    invalid_files = sorted(listdir(directory_invalid_tests))

    print('Performing valid {} test:'.format(directory[:-1]))

    for file in valid_files:
        test = open(directory_valid_tests + file, 'r').read()
        result = test_parser(test, parser, lexer)
        print_result(file, result)

        if result:
            success_counter += 1

    print('Performing invalid {} test:'.format(directory[:-1]))

    for file in invalid_files:
        test = open(directory_invalid_tests + file, 'r').read()
        result = not test_parser(test, parser, lexer)
        print_result(file, result)

        if result:
            success_counter += 1

    print('{} out of {} test successful.\n'.format(success_counter, len(valid_files) + len(invalid_files)))


def test_parser(test, parser, lexer):
    parser.parse(test, lexer.lexer)

    if parser.errors:
        return False

    return True


def print_result(file, result=True):
    if result:
        tag = colored('[success]', 'green')
    else:
        tag = colored('[failure]', 'red')

    print('{} {}'.format(tag, file))


if __name__ == '__main__':
    parser = QLParser()
    lexer = QLLexer()
    directories = ['parsing/']

    for directory in directories:
        test_directory(directory, parser, lexer)