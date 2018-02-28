import pprint

class QLAst:
    def __init__(self):
        self.forms = []
        self.types = []

    def addForm(self, form):
        self.forms.append(form)

    def checkTypes(self):
        types = []
        for form in self.forms:
            types.append(form.checkTypes())
        self.types = types
        self.printTypes()
        return types

    def printTypes(self):
        pprint.pprint(self.types)

    def __repr__(self):
        return "FORMS: {}".format(self.forms)
