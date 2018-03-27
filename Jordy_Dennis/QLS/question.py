"""
    A Question has a widget (it comes either from the default or it is specified outside the default),
    every widget also has a widgetType wich is also set for convenience. A question might have a default. 
"""
from .qlast_methods import *

class Question:
    def __init__(self, varName, widget, widgetType, line, default=None):
        self.varName = varName
        self.widget = widget
        self.line = line
        self.default = default
        self.varDict = None
        self.widgetType = widgetType
    
    """
        Simply add the default to the question, since every question can have its own default
    """
    def checkDefaults(self, defaultDict):
        if self.default:
           defaultDict[self.varName] = self.default
        else:
            defaultDict[self.varName] = self.widget

    """
        Check in the varDict if the type of the widget is compatible with the type of the question, and check if
        a default is also compatible

        We also check if the question actually occurs in the QL language
    """
    def checkTypes(self):
        if self.default:
            self.default.checkTypes(True)
        if self.varName not in self.varDict:
            errorstring = "Variable '" + self.varName + "' not in QL definition but in QLS at line " + str(self.line)
            throwError(errorstring)
        varType = self.varDict[self.varName]['type']
        if varType not in self.widget.checkTypes():
            errorstring = "Incompatible types for variable '" + self.varName +  "' of type " \
            + str(varType) + " and widget '" + str(self.widget.getWidget()) + "' at line " + str(self.line)
            throwError(errorstring)

    """
        Check to see if all variables from QL have been used, and if they have not been used twice (or more)
        if the varList is not empty in the end, a question or assignment has not been used
    """
    def checkCompleteness(self, varList):
        if self.varName not in varList:
            errorstring = "Double declaration of question '" + self.varName + "' at line: " + str(self.line)
            throwError(errorstring)
        varList.remove(self.varName)

    def addVarDict(self, varDict):
        self.varDict = varDict

    def getVarName(self):
        return self.varName

    def getWidget(self):
        return self.widget

    def __repr__(self):
        return "Question {}: widget: {}".format(self.varName, self.widget)