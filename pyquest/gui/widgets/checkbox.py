from PyQt5.QtWidgets import QCheckBox


class CheckBox(QCheckBox):
    def __init__(self):
        super(QCheckBox, self).__init__()

    def get_value(self, ql_type):
        return ql_type(self.isChecked())

    def on_change(self, change_event_function):
        self.stateChanged.connect(lambda: change_event_function(self))
