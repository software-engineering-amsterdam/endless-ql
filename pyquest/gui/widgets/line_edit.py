from PyQt5.QtWidgets import QLineEdit


class LineEdit(QLineEdit):
    def __init__(self):
        super(QLineEdit, self).__init__()

    def get_value(self, ql_type):
        return ql_type(self.text())

    def on_change(self, change_event_function):
        self.textChanged.connect(lambda: change_event_function(self))
