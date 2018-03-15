class Question:
    def __init__(self, varName, widget):
        self.varName = varName
        self.widget = widget

    def __repr__(self):
        return "Question {}: widget: {}".format(self.varName, self.widget)