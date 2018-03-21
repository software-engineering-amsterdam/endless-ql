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
        self.defaultDict = None

    """
        Perform all necessary steps to check the QLS AST and make sure the program is correct for generating
        the questions
    """
    def prepareAndCheckAst(self, varDict):
        self.addVarDict(varDict)
        self.checkDefaults()
        self.checkTypes()
        self.checkCompleteness()

    """
        Check if the defaults are not duplicated and are of correct type.
    """
    def checkDefaults(self):
        defaultDict = {}
        for page in self.pages:
            self.pages[page].checkDefaults(defaultDict)
        self.defaultDict = defaultDict

    """
        Check if the declared defaults and widgets are compatible with the question types
        We also check here if all questions are present in the QL language
    """
    def checkTypes(self):
        for page in self.pages:
            self.pages[page].checkTypes()

    """
        Check if each question from QL is used in QLS exactly once.
        If the varList is not empty in the end, a question or assignment has not been used.
        Double declarations will be caught in the question node. 
    """
    def checkCompleteness(self):
        varList = list(self.varDict.keys())
        for page in self.pages:
            self.pages[page].checkCompleteness(varList)
        if varList != []:
            errorstring = "QL question(s) " + str(varList) + " have not been declared in QLS, please add them"
            throwError(errorstring)


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

    def getDefaultDict(self):
        return self.defaultDict

    def __repr__(self):
        return "Stylesheet {}: pages:{}".format(self.name, self.pages)