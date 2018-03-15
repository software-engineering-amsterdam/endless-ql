
class DefaultStyle:
    def __init__(self):
        self.widgets = []

    def addWidget(self, widget):
        self.widgets.append(widget)

    def __repr__(self):
        return "DefaultStyle widgets: {}".format(self.widgets)