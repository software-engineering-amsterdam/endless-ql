from tkinter import ttk
from pyql.ast.expression.literals import *
from decimal import Decimal, InvalidOperation


class ValidatingEntry(ttk.Entry):

    def __init__(self, parent, **kw):
        super().__init__(parent, validate='all', validatecommand=(parent.register(self.validate), '%P'), **kw)  #

    def validate(self, new_value):
        return new_value


class IntegerWidget(ValidatingEntry):

    def __init__(self, parent, identifier, value=""):
        super().__init__(parent, width=20, name=identifier)
        super().insert(0, value)

    def get(self):
        try:
            return IntegerLiteral("", super().get())
        except ValueError:
            return None

    def validate(self, new_value):
        if new_value == "":
            return True
        try:
            print(new_value)
            if int(new_value) == int(new_value):
                return True
        except ValueError:
            return False
        return False

    def __repr__(self):
        return "IntegerWidget"


class StringWidget(ttk.Entry):

    def __init__(self, parent, identifier, value=""):
        super().__init__(parent, width=20, name=identifier)
        super().insert(0, value)

    def get(self):
        try:
            return StringLiteral("", super().get())
        except ValueError:
            return None

    def __repr__(self):
        return "StringWidget"


class DecimalWidget(ValidatingEntry):

    def __init__(self, parent, identifier, value=""):
        super().__init__(parent, width=20, name=identifier)
        super().insert(0, value)

    def get(self):
        try:
            return DecimalLiteral("", super().get())
        except (ValueError, InvalidOperation):
            return None

    def validate(self, new_value):
        if new_value == "":
            return True
        try:
            if Decimal(new_value) == Decimal(new_value):
                return True
        except (ValueError, InvalidOperation):
            return False
        return False

    def __repr__(self):
        return "DecimalWidget"


class MoneyWidget(ValidatingEntry):

    def __init__(self, parent, identifier, value=""):
        super().__init__(parent, width=20, name=identifier)
        super().insert(0, value)

    def get(self):
        try:
            return MoneyLiteral("", super().get())
        except (ValueError, InvalidOperation):
            return None

    def validate(self, new_value):
        if new_value == "":
            return True
        try:
            print(new_value)
            new_value_as_decimal = Decimal(new_value)
            decimal_places = new_value_as_decimal.as_tuple().exponent
            if new_value_as_decimal == new_value_as_decimal and decimal_places >= -2:
                return True
        except (ValueError, InvalidOperation):
            return False
        return False

    def __repr__(self):
        return "MoneyWidget"


class BooleanWidget(ttk.Checkbutton):

    def __init__(self, parent, identifier, value=False):
        super().__init__(parent, width=20, name=identifier)
        if value:
            super().state(['selected'])

    def get(self):
        try:
            return BooleanLiteral("", super().instate(['selected']))
        except ValueError:
            return None

    def __repr__(self):
        return "BooleanWidget"
