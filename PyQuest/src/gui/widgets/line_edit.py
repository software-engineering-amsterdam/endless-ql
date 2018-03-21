from PyQt5.QtWidgets import QLineEdit


class LineEdit(QLineEdit):
    def __init__(self):
        super(QLineEdit, self).__init__()

    def value(self):
        return self.text()

    def on_change(self, change_event_function):
        self.textChanged.connect(lambda: change_event_function(self))
