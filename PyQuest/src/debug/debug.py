from termcolor import colored
from PyQt5.QtWidgets import QMessageBox


def error(lines, message):
    tag = colored('[error]', 'red')
    separated_lines = ' , '.join([str(line) for line in lines])
    print('{} [line(s): {}] {}'.format(tag, separated_lines, message))
    exit()


def warning(lines, message):
    tag = colored('[warning]', 'yellow')
    separated_lines = ' , '.join([str(line) for line in lines])
    print('{} [line(s): {}] {}'.format(tag, separated_lines, message))


def pyqt5_error(line, message):
    QMessageBox.critical(QMessageBox(), 'Warning', '[line:{}] {}'.format(line, message),
                         QMessageBox.Close, QMessageBox.Escape)


def pyqt5_warning(line, message):
    QMessageBox.warning(QMessageBox(), 'Warning', '[line:{}] {}'.format(line, message),
                        QMessageBox.Close, QMessageBox.Escape)
