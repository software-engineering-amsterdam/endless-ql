class Question:
    def __init__(self, varName, widget, line):
        self.varName = varName
        self.widget = widget
        self.line = line
        self.varDict = None

    """
		Check in the varDict if the type of the widget is compatible with the type of the question
    """
    def checkTypes(self):
    	pass

    def addVarDict(self, varDict):
    	self.varDict = varDict

    def getVarName(self):
        return self.varName

    def getWidget(self):
        return self.widget

    def __repr__(self):
        return "Question {}: widget: {}".format(self.varName, self.widget)