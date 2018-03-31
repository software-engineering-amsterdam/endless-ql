from PyQt5.QtWidgets import QDoubleSpinBox
from ql.ast.nodes.expressions.literals.money_node import MoneyNode


class MoneySpinbox(QDoubleSpinBox):
    def __init__(self):
        super(QDoubleSpinBox, self).__init__()
        self.setPrefix("$")
        self.setMaximum(2**31 - 1)
        self.setMinimum(0)

    def get_value(self, ql_type):
        return ql_type(self.value(), self.prefix())

    def on_change(self, change_event_function):
        self.valueChanged.connect(lambda: change_event_function(self))
