from PyQt5.QtWidgets import QDoubleSpinBox


class DoubleSpinBox(QDoubleSpinBox):
    def __init__(self):
        super(QDoubleSpinBox, self).__init__()
        self.setMaximum(2**31 - 1)
        self.setMinimum(-self.maximum())
        self.setDecimals(16)
        
    def get_value(self, ql_type):
        return ql_type(self.value())

    def on_change(self, change_event_function):
        self.valueChanged.connect(lambda: change_event_function(self))
