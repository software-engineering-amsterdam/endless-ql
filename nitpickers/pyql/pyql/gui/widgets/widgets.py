import tkinter as tk
from tkinter import ttk
from pyql.util.values import *


class ValidatingEntry(ttk.Entry):

    def __init__(self, parent, **kw):
        super().__init__(parent, validate='all', validatecommand=(parent.register(self.validate), '%P'), **kw)

    def validate(self, new_value):
        pass


class IntegerWidget(ValidatingEntry):

    def __init__(self, parent, value=""):
        super().__init__(parent)
        super().insert(0, value)

    def get(self):
        try:
            return IntegerValue(super().get())
        except ValueError:
            return None

    def validate(self, new_value):
        if new_value == "" or IntegerValue.is_valid_input(new_value):
            return True
        return False

    def __repr__(self):
        return "IntegerWidget"


class DecimalWidget(ValidatingEntry):

    def __init__(self, parent, value=""):
        super().__init__(parent)
        super().insert(0, value)

    def get(self):
        try:
            return DecimalValue(super().get())
        except (ValueError, InvalidOperation):
            return None

    def validate(self, new_value):
        if new_value == "" or DecimalValue.is_valid_input(new_value):
            return True
        return False

    def __repr__(self):
        return "DecimalWidget"


class MoneyWidget(ValidatingEntry):

    def __init__(self, parent, value=""):
        super().__init__(parent)
        super().insert(0, value)

    def get(self):
        try:
            return MoneyValue(super().get())
        except (ValueError, InvalidOperation):
            return None

    def validate(self, new_value):
        if new_value == "" or MoneyValue.is_valid_input(new_value):
            return True
        return False

    def __repr__(self):
        return "MoneyWidget"


class StringWidget(ValidatingEntry):

    def __init__(self, parent, value=""):
        super().__init__(parent)
        super().insert(0, value)

    def get(self):
        try:
            return StringValue(super().get())
        except ValueError:
            return None

    def validate(self, new_value):
        if new_value == "" or StringValue.is_valid_input(new_value):
            return True
        return False

    def __repr__(self):
        return "StringWidget"


class BooleanWidget(ttk.Checkbutton):

    def __init__(self, parent, value=False):
        self._value = tk.BooleanVar()

        if value:
            self._value.set(True)

        super().__init__(parent, command=(parent.register(self.onchange)), variable=self._value)
        self.onchange()

    def get(self):
        try:
            return BooleanValue(self._value.get())
        except ValueError:
            return None

    def onchange(self):
        selected = self._value.get()
        if selected:
            self.config(text="yes")
        else:
            self.config(text="no")

    def __repr__(self):
        return "BooleanWidget"


class RadioWidget(ttk.Frame):

    def __init__(self, parent, state=False):
        super().__init__(parent)
        self.root = parent

        self._state = tk.BooleanVar()
        if state:
            self._state.set(True)

        self._radio_yes = tk.Radiobutton(self, text="yes", variable=self._state, value=True)
        self._radio_no = tk.Radiobutton(self, text="no", variable=self._state, value=False)

        self._radio_yes.grid(column=0, row=0, padx=5)
        self._radio_no.grid(column=1, row=0, padx=5)

    def get(self):
        try:
            return BooleanValue(self._state.get())
        except ValueError:
            return None

    def __repr__(self):
        return "RadioWidget"
