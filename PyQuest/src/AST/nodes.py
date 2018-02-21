# ----------------------------------------------------


class BaseNode(object):
    def __init__(self, line_number):
        self.line_number = line_number

    def accept(self, visitor):
        visitor.visit(self)

# ----------------------------------------------------


class BlockStatementNode(BaseNode):
    def __init__(self, line_number, block):
        super(BlockStatementNode, self).__init__(line_number)
        self.block = block

    def get_block(self):
        return self.block

# ----------------------------------------------------


class FormNode(BlockStatementNode):
    def __init__(self, line_number, block, label):
        super(FormNode, self).__init__(line_number, block)
        self.label = label

    def get_label(self):
        return self.label

# ----------------------------------------------------


class IfNode(BlockStatementNode):
    def __init__(self, line_number, block, condition):
        super(IfNode, self).__init__(line_number, block)
        self.condition = condition

    def get_condition(self):
        return self.condition

# ----------------------------------------------------


class QuestionNode(BaseNode):
    def __init__(self, line_number, question, label, val_type, expression):
        super(QuestionNode, self).__init__(line_number)
        self.question = question
        self.label = label
        self.val_type = val_type
        self.expression = expression

    def get_question(self):
        return self.question

    def get_label(self):
        return self.label

    def get_val_type(self):
        return self.val_type

    def get_expression(self):
        return self.expression

# ----------------------------------------------------


class ExpressionNode(BaseNode):
    def __init__(self, line_number, expression_type):
        super(ExpressionNode, self).__init__(line_number)
        self.expression_type = expression_type

    def get_expression_type(self):
        return self.expression_type


class BinaryOperatorNode(ExpressionNode):
    def __init__(self, line_number, expression_type, operator, lhs, rhs):
        super(BinaryOperatorNode, self).__init__(line_number, expression_type)
        self.operator = operator
        self.lhs = lhs
        self.rhs = rhs


class UnaryOperatorNode(ExpressionNode):
    def __init__(self, line_number, expression_type, operator, expression):
        super(UnaryOperatorNode, self).__init__(line_number, expression_type)
        self.operator = operator
        self.expression = expression


class LiteralNode(ExpressionNode):
    def __init__(self, line_number, expression_type, value):
        super(LiteralNode, self).__init__(line_number, expression_type)
        self.value = value


class VariableNode(ExpressionNode):
    def __init__(self, line_number, expression_type, name):
        super(VariableNode, self).__init__(line_number, expression_type)
        self.name = name
