from PyQt5.QtWidgets import QLabel


class Label(QLabel):
    def __init__(self, label):
        super(QLabel, self).__init__(label)

    def on_change(self, change_event_function):
        pass
