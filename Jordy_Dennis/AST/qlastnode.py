class QLAst:
    def __init__(self):
        self.forms = []

    def addForm(self, form):
        self.forms.append(form)

    def checkTypes(self):
        for form in self.forms:
            form.checkTypes()

    def __repr__(self):
        return "FORMS: {}".format(self.forms)
