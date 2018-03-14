from tkinter import ttk
from pyql.ast.expression.literals import *


class ValidatingEntryWidget(ttk.Entry):
    # base class for validating entry widgets

    def __init__(self, master, value="", **kw):
        apply(Entry.__init__, (self, master), kw)
        self.__value = value
        self.__variable = StringVar()
        self.__variable.set(value)
        self.__variable.trace("w", self.__callback)
        self.config(textvariable=self.__variable)

    def __callback(self, *dummy):
        value = self.__variable.get()
        newvalue = self.validate(value)
        if newvalue is None:
            self.__variable.set(self.__value)
        elif newvalue != value:
            self.__value = newvalue
            self.__variable.set(self.newvalue)
        else:
            self.__value = value

    def validate(self, value):
        # override: return value, new value, or None if invalid
        return value


class StringWidget(ttk.Entry):

    def __init__(self, parent, identifier, value=""):
        super().__init__(parent, width=20, name=identifier)
        super().insert(0, value)

    def get(self):
        return StringLiteral("", super().get())

    def __repr__(self):
        return "StringWidget"


class BooleanWidget(ttk.Checkbutton):

    def __init__(self, parent, identifier, value=False):
        super().__init__(parent, width=20, name=identifier)
        if value:
            super().state(['selected'])

    def get(self):
        return BooleanLiteral("", super().instate(['selected']))

    def __repr__(self):
        return "BooleanWidget"
