from PyQt5.QtWidgets import QDoubleSpinBox


class DoubleSpinBox(QDoubleSpinBox):
    def __init__(self):
        super(QDoubleSpinBox, self).__init__()

    def on_change(self, change_event_function):
        self.valueChanged.connect(lambda: change_event_function(self))
