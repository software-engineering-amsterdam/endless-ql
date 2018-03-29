class QLast(object):
    
    def getNodeType(self):
        return self.node_type


class FormNode(QLast):
    def __init__(self, name):
        self.node_type = "form"
        self.name = name
        self.statements = []

    def __repr__(self):
        return "Form: {}, statements: {}".format(self.name, self.statements)


class QuestionNode(QLast):
    def __init__(self, question, var, vartype):
        self.node_type = "question"
        self.question = question
        self.var = var
        self.vartype = vartype

    def __repr__(self):
        return "Question({}, {}, {})".format(self.question, self.var, self.vartype)

    def getVariableName(self):
        return self.var

    def getVariableType(self):
        return self.vartype


class AssignmentNode(QLast):
    def __init__(self, name, var, vartype, expression):
        self.node_type = "assignment"
        self.name = name
        self.var = var
        self.vartype = vartype
        self.expression = expression

    def __repr__(self):
        return "Assignment: {} {} {} = {}".format(self.name, self.vartype, self.var, self.expression)

    def getVariableName(self):
        return self.var

    def getVariableType(self):
        return self.vartype


class BinOpNode(QLast):
    def __init__(self, left, op, right, negate=False):
        self.node_type = "binop"
        self.left = left
        self.op = op
        self.right = right
        self.negate = negate

    def __repr__(self):
        return "binop: negate:{} {} {} {}".format(self.negate, self.left, self.op, self.right)


class UnOpNode(QLast):
    def __init__(self, var, negate=False):
        self.node_type = "unop"
        self.var = var
        self.negate = negate

    def __repr__(self):
        return "unop: negate:{} {}".format(self.negate, self.var)


class IfNode(QLast):
    def __init__(self, expression):
        self.node_type = "if"
        self.expression = expression
        self.statements = []

    def __repr__(self):
        return "If expression: {}, statements: {}".format(self.expression, self.statements)


class ElifNode(QLast):
    def __init__(self, expression):
        self.node_type = "elif"
        self.expression = expression
        self.statements = []

    def __repr__(self):
        return "Elif expression: {}, statements: {}".format(self.expression, self.statements)


class ElseNode(QLast):
    def __init__(self):
        self.node_type = "else"
        self.statements = []

    def __repr__(self):
        return "Else statements: {}".format(self.statements)


class LiteralNode(QLast):
    def __init__(self, literal, vartype, negate=False):
        self.node_type = "literal"
        self.literal = literal
        self.vartype = vartype
        self.negate = negate

    def __repr__(self):
        return "Litaral negate: {} {}".format(self.negate, self.literal)
