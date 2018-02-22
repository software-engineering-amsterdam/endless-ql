
class ConditionalNode:
    def __init__(self, expression, line):
        self.expression = expression
        self.ifChildren = []
        self.elifChildren = []
        self.elseChildren = []
        self.line = line

    def addIfChild(self, child):
        for i in child:
            self.ifChildren.append(i)

    def addElifChild(self, child):
        for i in child:
            self.elifChildren.append(i)

    def addElseChild(self, child):
        self.elseChildren += child

    def __repr__(self):
        return "Conditional: if({}): {} elif: {} else: {}".format(self.expression, self.ifChildren, self.elifChildren,self.elseChildren)