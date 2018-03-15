
class Page:
    def __init__(self, name):
        self.name = name
        self.sections = []
        self.defaults = []
        self.varDict = None

    def checkTypes(self):
        for section in self.sections:
            section.checkTypes()
        for default in self.defaults:
            default.checkTypes()

    def addSection(self, section):
        self.sections.append(section)

    def addDefault(self, default):
        self.defaults.append(default)

    def addVarDict(self, varDict):
        self.varDict = varDict
        for section in self.sections:
            section.addVarDict(varDict)
        for default in self.defaults:
            default.addVarDict(varDict)

    def getName(self):
        return self.name

    def getSection(self):
        return self.sections

    def getName(self):
        return self.name

    def __repr__(self):
        return "Page {}: sections: {} defaults: {}".format(self.name, self.sections, self.defaults)
