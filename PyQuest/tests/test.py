import os
import scanparse.qllex as qll
import scanparse.qlyacc as qly

from termcolor import colored


def test_directory(directory, parser, lexer):
    success_counter = 0
    files = sorted(os.listdir(directory))

    print('Performing {} tests:'.format(directory[:-1]))

    for file in files:
        test = open(directory + file, 'r').read()
        success_counter = test_parser(file, test, parser, lexer)

    print('{} out of {} tests successful.\n'.format(success_counter, len(files)))


def test_parser(file, test, parser, lexer):
    try:
        parser.parser.parse(test, lexer.lexer)
        print('{} {}'.format(colored('[success]', 'green'), file))
        return 1
    except (ValueError, EnvironmentError, EOFError, TimeoutError):
        print('{} {}'.format(colored('[fail]', 'red'), file))
        return 0


if __name__ == '__main__':
    parser = qly.QLParser()
    lexer = qll.LexTokenizer()
    directories = ['parser/']

    for directory in directories:
        test_directory(directory, parser, lexer)