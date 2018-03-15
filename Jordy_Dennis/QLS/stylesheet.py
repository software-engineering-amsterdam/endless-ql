

class Stylesheet:
    def __init__(self, name):
        self.pages = []
        self.name = name

    def addPage(self, page):
        self.pages.append(page)

    def getNumberOfPages(self):
        return len(self.pages)

    def __repr__(self):
        return "Stylesheet {}: pages:{}".format(self.name, self.pages)