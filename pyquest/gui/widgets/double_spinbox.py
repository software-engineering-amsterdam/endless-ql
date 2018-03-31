from PyQt5.QtWidgets import QDoubleSpinBox


class DoubleSpinBox(QDoubleSpinBox):
    def __init__(self):
        super(QDoubleSpinBox, self).__init__()
        self.setMaximum(2**31 - 1)
        self.setMinimum(- self.maximum())

    def get_value(self):
        return self.value()

    def on_change(self, change_event_function):
        self.valueChanged.connect(lambda: change_event_function(self))
