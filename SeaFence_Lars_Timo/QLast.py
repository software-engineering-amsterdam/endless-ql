# Lars Lokhoff, Timo Dobber
# This class defines the structure of the QL AST.

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
    def __init__(self, question, variable, variable_type):
        self.node_type = "question"
        self.question = question
        self.variable = variable
        self.variable_type = variable_type

    def __repr__(self):
        return "Question({}, {}, {})".format(self.question, self.variable, self.variable_type)

    def getVariableName(self):
        return self.variable

    def getVariableType(self):
        return self.variable_type


class AssignmentNode(QLast):
    def __init__(self, name, variable, variable_type, expression):
        self.node_type = "assignment"
        self.name = name
        self.variable = variable
        self.variable_type = variable_type
        self.expression = expression

    def __repr__(self):
        return "Assignment: {} {} {} = {}".format(self.name, self.variable_type, self.variable, self.expression)

    def getVariableName(self):
        return self.variable

    def getVariableType(self):
        return self.variable_type


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
    def __init__(self, variable, negate=False):
        self.node_type = "unop"
        self.variable = variable
        self.negate = negate

    def __repr__(self):
        return "unop: negate:{} {}".format(self.negate, self.variable)


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
    def __init__(self, literal, variable_type, negate=False):
        self.node_type = "literal"
        self.literal = literal
        self.variable_type = variable_type
        self.negate = negate

    def __repr__(self):
        return "Litaral negate: {} {}".format(self.negate, self.literal)
