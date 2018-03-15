

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


<<<<<<< HEAD
=======
    def getPages(self):
        return self.pages

    def getNumberOfPages(self):
        return len(self.pages)
>>>>>>> f72660c77cd153702e369fb13784099e68383615

    def __repr__(self):
        return "Stylesheet {}: pages:{}".format(self.name, self.pages)