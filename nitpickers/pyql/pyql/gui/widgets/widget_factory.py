from multimethods import multimethod
from pyql.gui.widgets.widgets import *
from pyql.util.types import *


class WidgetFactory:

    def __init__(self):
        pass

    @multimethod(String)
    def widget(self):
        return StringWidget

    @multimethod(Boolean)
    def widget(self):
        return BooleanWidget
