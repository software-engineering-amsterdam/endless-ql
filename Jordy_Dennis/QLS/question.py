
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
        Simply add the default to the question
    """
    def checkDefaults(self, defaultDict):
        if self.default:
           defaultDict[self.varName] = self.default
        else:
            defaultDict[self.varName] = self.widget

    """
        Check in the varDict if the type of the widget is compatible with the type of the question, and check if
        a default is also compatible
    """
    def checkTypes(self):
        if self.default:
            self.default.checkTypes()
        if self.varName not in self.varDict:
            errorstring = "Variable " + self.varName + " not in QL definition but in QLS at line " + str(self.line)
            throwError(errorstring)
        varType = self.varDict[self.varName]['type']
        if varType not in self.widget.checkTypes():
            errorstring = "Incompatible types for variable " + self.varName +  " of type " \
            + str(varType) + " and widget " + str(self.widget.getWidget()) + " at line " + str(self.line)
            throwError(errorstring)

    def addVarDict(self, varDict):
        self.varDict = varDict

    def getVarName(self):
        return self.varName

    def getWidget(self):
        return self.widget

    def __repr__(self):
        return "Question {}: widget: {}".format(self.varName, self.widget)