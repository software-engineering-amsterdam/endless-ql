from pyql.gui.widgets.widgets import *
from util.types import *
from util.multimethods import multimethod


class WidgetFactory:

    def __init__(self):
        pass

    def widget(self, root, identifier, type, value):
        w = self.create_widget(type)
        return w(root, identifier, value)

    @multimethod(String)
    def create_widget(self, type):
        return TextWidget

    @multimethod(Integer)
    def create_widget(self, type):
        return NumberWidget

    @multimethod(Decimal)
    def create_widget(self, type):
        return DecimalWidget

    @multimethod(Money)
    def create_widget(self, type):
        return MoneyWidget

    @multimethod(Boolean)
    def create_widget(self, type):
        return CheckWidget

