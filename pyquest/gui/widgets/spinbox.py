from PyQt5.QtWidgets import QSpinBox


class SpinBox(QSpinBox):
    def __init__(self):
        super(QSpinBox, self).__init__()
        self.setMaximum(2**31 - 1)
        self.setMinimum(- self.maximum())

    def on_change(self, change_event_function):
        self.valueChanged.connect(lambda: change_event_function(self))
