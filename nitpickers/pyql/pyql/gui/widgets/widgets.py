from tkinter import ttk
from pyql.ast.expression.literals import *


class ValidatingEntry(ttk.Entry):

    def __init__(self, parent, **kw):
        super().__init__(parent, validate='all', validatecommand=(parent.register(self.validate), '%P'), **kw) #

    def validate(self, new_value):
        return new_value


class IntegerWidget(ValidatingEntry):

    def __init__(self, parent, identifier, value=""):
        super().__init__(parent, width=20, name=identifier)
        # super().insert(0, value)

    def get(self):
        return IntegerLiteral("", super().get())

    def validate(self, new_value):
        try:
            print(new_value)
            if int(new_value) == int(new_value):
                return True
        except ValueError:
            return False
        return True

    def __repr__(self):
        return "IntegerWidget"


class StringWidget(ttk.Entry):

    def __init__(self, parent, identifier, value=""):
        super().__init__(parent, width=20, name=identifier)
        super().insert(0, value)

    def get(self):
        return StringLiteral("", super().get())

    def __repr__(self):
        return "StringWidget"


class DecimalWidget(ttk.Entry):

    def __init__(self, parent, identifier, value=""):
        super().__init__(parent, width=20, name=identifier)
        super().insert(0, value)

    def get(self):
        return DecimalLiteral("", super().get())

    def __repr__(self):
        return "DecimalWidget"


class MoneyWidget(ttk.Entry):

    def __init__(self, parent, identifier, value=""):
        super().__init__(parent, width=20, name=identifier)
        super().insert(0, value)

    def get(self):
        return MoneyLiteral("", super().get())

    def __repr__(self):
        return "MoneyWidget"


class BooleanWidget(ttk.Checkbutton):

    def __init__(self, parent, identifier, value=False):
        super().__init__(parent, width=20, name=identifier)
        if value:
            super().state(['selected'])

    def get(self):
        return BooleanLiteral("", super().instate(['selected']))

    def __repr__(self):
        return "BooleanWidget"
