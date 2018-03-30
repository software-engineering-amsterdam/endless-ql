from termcolor import colored

from os import listdir


class Test:
    def __init__(self, name, directory):
        self.__name = name
        self.directory = directory
        self.valid_files = sorted(listdir(directory + 'valid'))
        self.invalid_files = sorted(listdir(directory + 'invalid'))

    @property
    def name(self):
        return self.__name

    def test(self):
        successes = 0
        print('-------------------------------------------------------------------------------------------\n')
        print('Performing {} tests.\n'.format(self.name))
        print('-------------------------------------------------------------------------------------------\n')
        print('Performing valid tests:')
        successes += self.test_valid_files()

        print()
        print('Performing invalid tests:')
        successes += self.test_invalid_files()

        print()
        print('{} out of {} tests successful.\n'.format(successes, len(self.valid_files) + len(self.invalid_files)))

    def test_file(self, file):
        pass

    def test_valid_files(self, valid=True):
        successes = 0

        for file in self.valid_files:
            test = open(self.directory + 'valid/' + file, 'r').read()
            result = valid == self.test_file(test)
            self.print_result(file, result, test.split('\n')[0])

            if result:
                successes += 1

        return successes

    def test_invalid_files(self, valid=False):
        successes = 0

        for file in self.invalid_files:
            test = open(self.directory + 'invalid/' + file, 'r').read()
            result = valid == self.test_file(test)
            self.print_result(file, result, test.split('\n')[0])

            if result:
                successes += 1

        return successes

    @staticmethod
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
