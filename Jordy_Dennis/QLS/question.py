class Question:
    def __init__(self, varName, widget):
        self.varName = varName
        self.widget = widget
        self.varDict = None

    def checkTypes(self):
    	pass

    def addVarDict(self, varDict):
    	self.varDict = varDict

    def __repr__(self):
        return "Question {}: widget: {}".format(self.varName, self.widget)