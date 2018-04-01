from PyQt5.QtWidgets import QDoubleSpinBox


class MoneySpinbox(QDoubleSpinBox):
    def __init__(self):
        super(QDoubleSpinBox, self).__init__()
        self.setPrefix('$')
        self.setMaximum(2**31 - 1)
        self.setMinimum(-self.maximum())

    def get_value(self, ql_type):
        return ql_type(self.value(), self.prefix())

    def on_change(self, change_event_function):
        self.valueChanged.connect(lambda: change_event_function(self))
