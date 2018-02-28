class QLAst:
    def __init__(self):
        self.forms = []

    def addForm(self, form):
        self.forms.append(form)

    def checkTypes(self):
        types = []
        for form in self.forms:
            types.append(form.checkTypes())
        return types

    def __repr__(self):
        return "FORMS: {}".format(self.forms)
