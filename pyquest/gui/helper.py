from PyQt5.QtWidgets import QMessageBox


def append_file_extension(file_name, extension):
        if not file_name.endswith('.' + extension):
            return '{}.{}'.format(file_name, extension)
        return file_name


def error(errors):
    message = '\n'.join(errors)
    QMessageBox.critical(QMessageBox(), 'Error', message, QMessageBox.Close, QMessageBox.Escape)


def warning(warnings):
    message = '\n'.join(warnings)
    QMessageBox.warning(QMessageBox(), 'Warning', message, QMessageBox.Close, QMessageBox.Escape)


def check_errors(errors):
    if errors:
        error(errors)
        raise SyntaxError
