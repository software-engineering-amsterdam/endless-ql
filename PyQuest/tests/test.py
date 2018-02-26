import os
import scanparse.qllex as qll
import scanparse.qlyacc as qly

from termcolor import colored


def test_directory(directory):
    tests_success_counter = 0
    tests_fail_counter = 0

    print('Performing {} tests:'.format(directory[:-1]))

    for file in os.listdir('parser/'):
        test = open(directory + file, 'r').read()

        try:
            parser.parser.parse(test, lexer.lexer)
            tests_success_counter += 1

            print('{} {}'.format(colored('[success]', 'green'), file))
        except:
            tests_fail_counter += 1

            print('{} {}'.format(colored('[fail]', 'red'), file))

    print('{} out of {} tests successful.\n'.format(tests_success_counter, tests_success_counter + tests_fail_counter))


if __name__ == '__main__':
    parser = qly.QLParser()
    lexer = qll.LexTokenizer()
    directories = ['parser/']

    for directory in directories:
        test_directory(directory)