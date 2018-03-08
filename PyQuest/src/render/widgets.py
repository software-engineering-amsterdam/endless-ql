from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QCheckBox
from PyQt5.QtWidgets import QSpinBox
from PyQt5.QtWidgets import QDoubleSpinBox
from PyQt5.QtWidgets import QCalendarWidget


class Label(QLabel):
    def __init__(self):
        super(QLabel, self).__init__()

    def value(self):
        return self.text()


class LineEdit(QLineEdit):
    def __init__(self):
        super(QLineEdit, self).__init__()

    def value(self):
        return self.text()


class CheckBox(QCheckBox):
    def __init__(self):
        super(QCheckBox, self).__init__()

    def value(self):
        return self.isChecked()


class SpinBox(QSpinBox):
    def __init__(self):
        super(QSpinBox, self).__init__()


class DoubleSpinBox(QDoubleSpinBox):
    def __init__(self):
        super(QDoubleSpinBox, self).__init__()


class CalendarWidget(QCalendarWidget):
    def __init__(self):
        super(QCalendarWidget, self).__init__()

    def value(self):
        date = self.selectedDate()
        print(date)
        return date.day(), date.month(), date.year()
