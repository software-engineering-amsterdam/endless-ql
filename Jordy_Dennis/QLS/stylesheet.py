"""
    We do not support scoping of defaults, they can only be declared once and will be set everywhere.
    A default is connected to a widget and a type.
"""

from .qlast_methods import *
import collections

class Stylesheet:
    def __init__(self, name):
        self.pages = collections.OrderedDict()
        self.name = name
        self.varDict = None

    def getDefaults(self):
        defaultDict = {}
        for page in self.pages:
            self.pages[page].getDefaults(defaultDict)
        printDict(defaultDict)

    def checkTypes(self):
        for page in self.pages:
            self.pages[page].checkTypes()

    def addPage(self, page):
        self.pages[page.getName()] = page

    def addVarDict(self, varDict):
        self.varDict = varDict
        for page in self.pages:
            self.pages[page].addVarDict(varDict)

    def getPages(self):
        return self.pages

    def getNumberOfPages(self):
        return len(self.pages)

    def __repr__(self):
        return "Stylesheet {}: pages:{}".format(self.name, self.pages)