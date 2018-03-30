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

    for valid_file in valid_files:
        test = open(directory_valid_tests + valid_file, 'r').read()
        result = test_parser(test, parser, lexer)
        print_result(valid_file, result, test.split('\n')[0])

        if result:
            success_counter += 1

    print()
    print('Performing invalid {} test:'.format(directory[:-1]))

    for invalid_file in invalid_files:
        test = open(directory_invalid_tests + invalid_file, 'r').read()
        result = not test_parser(test, parser, lexer)
        print_result(invalid_file, result, test.split('\n')[0])

        if result:
            success_counter += 1

    print()
    print('{} out of {} test successful.\n'.format(success_counter, len(valid_files) + len(invalid_files)))


def test_parser(test, parser, lexer):
    parser.parse(test, lexer.lexer)

    if parser.errors:
        return False

    return True


def print_result(file, result=True, test=''):
    if result:
        tag = colored('[success]', 'green')
    else:
        tag = colored('[failure]', 'red')

    if test.startswith('//'):
        test = test[2:].strip()
    else:
        test = ''

    print(tag, file, test)


if __name__ == '__main__':
    parser = QLParser()
    lexer = QLLexer()
    test_directory('parsing', parser, lexer)