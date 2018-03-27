"""
    A Page can contain multiple sections, and page-wide default styling.
"""
from .qlast_methods import *
class Page:
    def __init__(self, name):
        self.name = name
        self.sections = []
        self.defaults = []

    """
        Typechecker for the defaults, make sure no (type, widgetType) is declared twice on page level
    """
    def checkDefaults(self, defaultDict):
        defaultDict[self.name] = {}
        sectionDict = {}
        for section in self.sections:
            section.checkDefaults(sectionDict)

        # tmpDict is used to check if the default types are declarated twice, and to eventually
        # store them in the defaultDict which is used for debugging purposes
        tmpDict = {}
        for default in self.defaults:
            default.checkDefaultsQuestion(tmpDict)
        defaultDict[self.name]['sectionDefaults'] = sectionDict
        defaultDict[self.name]['pageDefaults'] = tmpDict



    """
        Check for children if the widget type is compatible with the question type
    """
    def checkTypes(self):
        for section in self.sections:
            section.checkTypes()
        for default in self.defaults:
            default.checkTypes()

    """
        Check if each question from QL is used in QLS exactly once
    """
    def checkCompleteness(self, varList):
        for section in self.sections:
            section.checkCompleteness(varList)

    def addSection(self, section):
        self.sections.append(section)

    def addDefault(self, default):
        self.defaults.append(default)

    def addVarDict(self, varDict):
        for section in self.sections:
            section.addVarDict(varDict)

    def getName(self):
        return self.name

    def getSection(self):
        return self.sections

    def getName(self):
        return self.name

    def __repr__(self):
        return "Page {}: sections: {} defaults: {}".format(self.name, self.sections, self.defaults)
