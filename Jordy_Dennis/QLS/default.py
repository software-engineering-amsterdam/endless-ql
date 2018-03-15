
class DefaultStyle:
    def __init__(self):
        self.widgets = []
        self.varDict = None

    def checkTypes(self):
        pass

    def addWidget(self, widget):
        self.widgets.append(widget)

    def addVarDict(self, varDict):
        self.varDict = varDict

    def __repr__(self):
        return "DefaultStyle widgets: {}".format(self.widgets)