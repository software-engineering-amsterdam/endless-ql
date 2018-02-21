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

class ifNode:
	def __init__(self, expression):
		self.expression = expression

	def __repr__(self):
		return "Expression: {}".format(self.expression)