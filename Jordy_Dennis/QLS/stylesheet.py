

class Stylesheet:
    def __init__(self, name):
        self.pages = {}
        self.name = name
        self.varDict = None

    def checkTypes(self):
        for page in self.pages:
            self.pages[page].checkTypes()

    def addPage(self, page):
        self.pages[page.getName()] = page

    def addVarDict(self, varDict):
        self.varDict = varDict
        for page in self.pages:
            self.pages[page].addVarDict(varDict) 



    def __repr__(self):
        return "Stylesheet {}: pages:{}".format(self.name, self.pages)