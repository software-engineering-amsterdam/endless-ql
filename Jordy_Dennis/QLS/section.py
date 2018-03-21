
from .qlast_methods import *
class Section:
    def __init__(self, name):
        self.name = name
        self.defaults = []
        self.questions = []
        self.sections = []
        self.varDict = None

    """
        Typechecker for the defaults, make sure no (type, widgetType) is declared twice on section level
    """
    def checkDefaults(self, defaultDict):
        defaultDict[self.name] = {}

        # Add defaults recursively to questions
        sectionDict = {}
        for section in self.sections:
            section.checkDefaults(sectionDict)

        questionDict = {}
        for question in self.questions:
            question.checkDefaults(questionDict)

        # tmpDict is used to check if the default types are declarated twice, and to eventually
        # store them in the defaultDict which is used for debugging purposes
        tmpDict = {}
        for default in self.defaults:
            default.checkDefaultsQuestion(tmpDict)
        defaultDict[self.name]['sectionDefaults'] = tmpDict
        defaultDict[self.name]['questionDefaults'] = questionDict
        defaultDict[self.name]['subsectionDefaults'] = sectionDict


    """
        Check for children if the widget type is compatible with the question type
    """
    def checkTypes(self):
        for question in self.questions:
            question.checkTypes()
        for default in self.defaults:
            default.checkTypes()

    """
        Check if each question from QL is used in QLS exactly once
    """
    def checkCompleteness(self, varList):
        for section in self.sections:
            section.checkCompleteness(varList)
        for question in self.questions:
            question.checkCompleteness(varList)

    def addVarDict(self, varDict):
        self.varDict = varDict
        for question in self.questions:
            question.addVarDict(varDict)
        for default in self.defaults:
            default.addVarDict(varDict)


    def addSection(self, section):
        self.sections.append(section)

    def addQuestion(self, question):
        self.questions.append(question)

    def addDefault(self, default):
        self.defaults.append(default)

    def getName(self):
        return self.name

    def getQuestions(self):
        return self.questions

    def getSections(self):
        return self.sections


    def __repr__(self):
        return "Section {}: questions: {} sections:{} defaults: {}".format(self.name, self.questions, self.sections, self.defaults)