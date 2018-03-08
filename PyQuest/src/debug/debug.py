from termcolor import colored


def error(line, message):
    tag = colored('[error]', 'red')
    print('{} [line:{}] {}'.format(tag, line, message))


def warning(line, message):
    tag = colored('[warning]', 'yellow')
    print('{} [line:{}] {}'.format(tag, line, message))