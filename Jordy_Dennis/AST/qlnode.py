class QLNode:
    def __init__(self):
        self.forms = []

    def addForm(self, form):
        self.forms.append(form)

    def __repr__(self):
        return "FORMS: {}".format(self.forms)
