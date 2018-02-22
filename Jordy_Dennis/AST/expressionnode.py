# An expression can be a single variable. Or a combination of a variable, an operator (negation) and multiple other expressions

# if there is only a single variable, it will always be in the LEFT node.
# A NOT-expression is represented as a -1 in the 'op' node, and an expression in the right node.

# Maybe we have to give each expression a type for the right and left node, we do this in order to make type checking very easy.
# (so we can check, if type_left != type_right, exception)


class BinaryNode:
    def __init__(self, left, right, op, line):
        self.left = left
        self.right = right
        self.op = op
        self.line = line

    def __repr__(self):
        return "Binop: {} {} {}".format(self.left, self.op, self.right)

class UnaryNode:
    def __init__(self, left, op, line):
        self.left = left
        self.op = op
        self.line = line

    def __repr__(self):
        return "Monop: {} {}".format(self.op, self.left)

class LiteralNode:
    def __init__(self, value, _type, line):
        self.value = value
        self.line = line
        self.type = _type

    def __repr__(self):
        return "literal: {} ".format(self.value)
