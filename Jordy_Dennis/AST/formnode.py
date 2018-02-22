class FormNode:
    def __init__(self, name, line):
        self.name = name
        self.children = []
        self.line = line
        print(line)

    def addChild(self, child):
        self.children.append(child)

    def  __repr__(self):
        return "Form: {}, children: {}".format(self.name, self.children)