
class ExpressionNode:
    def __init(self, left, right, op):
        self.left = left
        self.right = right
        self.op = op

    def __repr__(self):
        return "Expression: {} {} {}".format(self.left, self.op, self.right)
