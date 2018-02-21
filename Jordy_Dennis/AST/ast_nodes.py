class QLNode:
    def __init__(self):
        self.forms = []

    def addForm(self, form):
        self.forms += form

class formNode:
    def __init__(self, name):
        self.name = name
        self.children = []

    def addChild(self, child):
        self.children += child

    def  __repr__(self):
        return "Form: {}, children: {}".format(self.name, self.children)

class questionNode:
    def __init__(self, question, varName, varType):
        self.question = question
        self.varName = varName
        self.varType = varType

    def __repr__(self):
        return "Question: \"{}\",{}:{}".format(self.question,self.varName, self.varType)

class conditionalNode:
    def __init__(self, expression):
        self.expression = expression
        self.ifChildren = []
        self.elifChildren = []
        self.elseChildren = []

    def addIfChild(self, child):
        self.ifChildren += child

    def addElifChild(self, child):
        self.elifChildren += child

    def addElseChild(self, child):
        self.elseChildren += child

    def __repr__(self):
        return "Conditional: if({}): {} elif: {} else: {}".format(self.expression, self.ifChildren, self.elifChildren,self.elseChildren)

class assignmentNode:
    def __init__(self, question, var, varType, expression):
        self.question = question
        self.varName = varName
        self.varType = varType
        self.value = expression

    def changeValue(self, value):
        pass

    def __repr__(self):
        return "Assigment: \"{}\" {}:{} = {}".format(self.question,self.varName, self.varType, self.expression)

class expressionNode:
    def __init(self, left, right, op):
        self.left = left
        self.right = right
        self.op = op

    def __repr__(self):
        return "Expression: {} {} {}".format(self.left, self.op, self.right)





