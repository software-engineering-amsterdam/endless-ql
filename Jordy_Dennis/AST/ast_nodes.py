class QLNode:
    def __init__(self):
        self.forms = []

    def addForm(self, form):
        self.forms.append(form)

    def __repr__(self):
        return "FORMS: {}".format(self.forms)

class formNode:
    def __init__(self, name):
        self.name = name
        self.children = []

    def addChild(self, child):
        self.children.append(child)

    def  __repr__(self):
        return "Form: {}, children: {}".format(self.name, self.children)

class questionNode:
    def __init__(self, question, varName, varType):
        self.question = question
        self.varName = varName
        self.varType = varType

    def __repr__(self):
        return "Question: {}, {}:{}".format(self.question,self.varName, self.varType)

class conditionalNode:
    def __init__(self):
        self.ifChild = None
        self.elifChildren = []
        self.elseChild = None

    def addIfChild(self, child):
        self.ifChild = child

    def addElifChild(self, child):
        self.elifChildren.append(child)

    def addElseChild(self, child):
        self.elseChildren = child 

    def __repr__(self):
        return "Conditional: if: {} elif: {} else: {}".format(self.ifChild, self.elifChildren,self.elseChild)

class conditionNode:
    def __init__(self, expression):
        self.expression = expression
        self.questions = []

    def addQuestions(self, questions):
        for i in questions:
            self.questions.append(i)

    def __repr__(self):
        return "({}) {}".format(self.expression, self.questions)


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

