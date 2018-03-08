from os import listdir
from scanparse.qllex import LexTokenizer
from scanparse.qlyacc import QLParser
from termcolor import colored


def test_directory(directory, parser, lexer):
    success_counter = 0
    directory_valid_tests = directory + '/valid/'
    directory_invalid_tests = directory + '/invalid/'
    valid_files = sorted(listdir(directory_valid_tests))
    invalid_files = sorted(listdir(directory_invalid_tests))

    print('Performing valid {} tests:'.format(directory[:-1]))

    for file in valid_files:
        test = open(directory_valid_tests + file, 'r').read()
        result = test_parser(test, parser, lexer)
        print_result(file, result)

        if result:
            success_counter += 1

    print('Performing invalid {} tests:'.format(directory[:-1]))

    for file in invalid_files:
        test = open(directory_invalid_tests + file, 'r').read()
        result = not test_parser(test, parser, lexer)
        print_result(file, result)

        if result:
            success_counter += 1

    print('{} out of {} tests successful.\n'.format(success_counter, len(valid_files) + len(invalid_files)))


def test_parser(test, parser, lexer):
    try:
        parser.parser.parse(test, lexer.lexer)
        return True
    except:
        return False


def print_result(file, result=True):
    if result:
        tag = colored('[success]', 'green')
    else:
        tag = colored('[fail]', 'red')

    print('{} {}'.format(tag, file))


if __name__ == '__main__':
    parser = QLParser()
    lexer = LexTokenizer()
    directories = ['parser/']

    for directory in directories:
        test_directory(directory, parser, lexer)