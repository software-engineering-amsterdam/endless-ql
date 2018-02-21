class QLast:
    pass

class formNode(QLast):
    def __init__(self, name):
        self.name = name

    def __repr__(self):
        return "Form: {}".format(self.name)

class questionNode(QLast):
	def __init__(self, question, var, vartype):
		self.question = question
		self.var = var
		self.vartype = vartype

	def __repr__(self):
		return "Question({}, {}, {})".format(self.question, self.var, self.vartype)

class assignmentNode:
	def __init__(self, name, var, vartype, expression):
		self.name = name
		self.var = var
		self.vartype = vartype
		self.expression = expression

	def __repr__(self):
		return "Assignment: {} {} {} = {}".format(self.name, self.vartype, self.var, sefl.expression)

# Can either be a solo value or an binary operator?
class expressionNode:
	def __init__(self, node):
		self.node = node

	def __repr__(self):
		return "Node: {}".format(self.node)

class binOpNode:
	def __init__(self, left, op, right):
		self.left = left
		self.op = op
		self.right = right
		pass

	def __repr__(self):
		return "binop: {} {} {}".format(self.left, self.op, self.right)

class ifNode:
	def __init__(self, expression):
		self.expression = expression

	def __repr__(self):
		return "Expression: {}".format(self.expression)

class numberNode:
	def __init__(self, number):
		self.number = number

	def __repr__(self):
		return "Number: {}".format(self.number)