class QLast:
    pass

class FormNode(QLast):
    def __init__(self, name):
        self.name = name
        self.statements = []

    def __repr__(self):
        return "Form: {}, statements: {}".format(self.name, self.statements)

class QuestionNode(QLast):
	def __init__(self, question, var, vartype):
		self.question = question
		self.var = var
		self.vartype = vartype

	def __repr__(self):
		return "Question({}, {}, {})".format(self.question, self.var, self.vartype)

class AssignmentNode:
	def __init__(self, name, var, vartype, expression):
		self.name = name
		self.var = var
		self.vartype = vartype
		self.expression = expression

	def __repr__(self):
		return "Assignment: {} {} {} = {}".format(self.name, self.vartype, self.var, sefl.expression)

# Can either be a solo value or an binary operator?
class ExpressionNode:
	def __init__(self, node):
		self.node = node

	def __repr__(self):
		return "Node: {}".format(self.node)

class BinOpNode:
	def __init__(self, left, op, right):
		self.left = left
		self.op = op
		self.right = right
		pass

	def __repr__(self):
		return "binop: {} {} {}".format(self.left, self.op, self.right)

class IfNode:
	def __init__(self, expression):
		self.expression = expression
		self.questions

	def __repr__(self):
		return "If expression: {}".format(self.expression)

class ElifNode:
	def __init__(self, expression):
		self.expression = expression

	def __repr__(self):
		return "Elif expression: {}".format(self.expression)

class ElseNode:
	def __init__(self, expression):
		self.expression = expression

	def __repr__(self):
		return "Else expression: {}".format(self.expression)

class NumberNode:
	def __init__(self, number):
		self.number = number

	def __repr__(self):
		return "Number: {}".format(self.number)