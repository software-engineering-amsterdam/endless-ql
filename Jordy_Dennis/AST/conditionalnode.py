
class ConditionalNode:
    def __init__(self, expression):
        self.expression = expression
        self.ifChildren = []
        self.elifChildren = []
        self.elseChildren = []

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