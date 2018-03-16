
from .qlast_methods import *

class Question:
    def __init__(self, varName, widget, line, default=None):
        self.varName = varName
        self.widget = widget
        self.line = line
        self.default = default
        self.varDict = None
    
    """
		If the question has a default, that means that the widget is still unset, here we check if the default
		(type, widgetType) has not yet been declared, and then we add them to the dictionary and set the widget of
		the question
    """
    def getDefaults(self, defaultDict):
    	print(self.widget)
    	print(self.default)

    """
		Check in the varDict if the type of the widget is compatible with the type of the question
    """
    def checkTypes(self):
    	print(self.widget)
    	# supportedTypes = self.widget.checkTypes()
    	# print(supportedTypes)
    	pass

    def addVarDict(self, varDict):
    	self.varDict = varDict

    def getVarName(self):
        return self.varName

    def getWidget(self):
        return self.widget

    def __repr__(self):
        return "Question {}: widget: {}".format(self.varName, self.widget)