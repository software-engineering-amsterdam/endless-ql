class Question:
    def __init__(self, varName, widget):
        self.varName = varName
        self.widget = widget

    def getVarName(self):
        return self.varName

    def getWidget(self):
        return self.widget

    def __repr__(self):
        return "Question {}: widget: {}".format(self.varName, self.widget)