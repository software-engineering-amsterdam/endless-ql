import tkinter as tk
from tkinter import ttk
from pyql.ast.expression.literals import *
from decimal import Decimal, InvalidOperation


class ValidatingEntry(ttk.Entry):

    def __init__(self, parent, **kw):
        super().__init__(parent, validate='all', validatecommand=(parent.register(self.validate), '%P'), **kw)  #

    def validate(self, new_value):
        return new_value


class NumberWidget(ValidatingEntry):

    def __init__(self, parent, identifier, value=""):
        super().__init__(parent, width=20, name=identifier)
        super().insert(0, value)

    def get(self):
        try:
            return IntegerLiteral("", super().get())
        except ValueError:
            return None

    def validate(self, new_value):
        if not new_value:
            return True
        try:
            print(new_value)
            if int(new_value) == int(new_value):
                return True
        except ValueError:
            return False
        return False

    def __repr__(self):
        return "NumberWidget"


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
        if not new_value:
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
        if not new_value:
            return True
        try:
            is_decimal = Decimal(new_value) == Decimal(new_value)
            exponent = Decimal(new_value).as_tuple().exponent
            if is_decimal and exponent >= -2:
                return True
        except (ValueError, InvalidOperation):
            return False
        return False

    def __repr__(self):
        return "MoneyWidget"


class TextWidget(tk.Entry):

    def __init__(self, parent, identifier, value=""):
        super().__init__(parent, width=20, name=identifier)
        super().insert(0, value)

    def get(self):
        try:
            return StringLiteral("", super().get())
        except ValueError:
            return None

    def __repr__(self):
        return "TextWidget"


class CheckWidget(tk.Checkbutton):

    def __init__(self, parent, identifier, value=False):
        super().__init__(parent, width=20, name=identifier, command=(parent.register(self.onchange)))
        self.onchange()
        if value:
            super().state(['selected'])

    def get(self):
        try:
            return BooleanLiteral("", super().instate(['selected']))
        except ValueError:
            return None

    def onchange(self):
        print(super().state())
        if super().instate(['alternate']):
            self.config(text="undefined")
        elif super().instate(['selected']):
            self.config(text="yes")
        else:
            self.config(text="no")

    def __repr__(self):
        return "CheckWidget"


class RadioWidget(ttk.Frame):

    def __init__(self, parent, identifier, state=False):
        super().__init__(parent, name=identifier)
        print("radio", state, type(state))
        self.root = parent

        self._state = tk.BooleanVar()
        if state:
            self._state.set(True)

        self._radio_yes = tk.Radiobutton(self, text="yes", variable=self._state, value=True)
        self._radio_no = tk.Radiobutton(self, text="no", variable=self._state, value=False)

        self._radio_yes.grid(column=0, row=0, padx=5)
        self._radio_no.grid(column=1, row=0, padx=5)

    def get(self):
        print("self._state", self._state.get())
        try:
            return BooleanLiteral("", self._state.get())
        except ValueError:
            return None

    def __repr__(self):
        return "RadioWidget"
