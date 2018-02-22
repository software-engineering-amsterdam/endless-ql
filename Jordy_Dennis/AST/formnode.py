class FormNode:
    def __init__(self, name):
        self.name = name
        self.children = []

    def addChild(self, child):
        self.children.append(child)

    def  __repr__(self):
        return "Form: {}, children: {}".format(self.name, self.children)