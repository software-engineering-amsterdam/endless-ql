from PyQt5.QtWidgets import QLabel


class Label(QLabel):
    def __init__(self, label):
        super(QLabel, self).__init__(label)

    def get_value(self, ql_type):
        return self.text()

    def on_change(self, change_event_function):
        pass
