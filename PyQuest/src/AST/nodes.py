# ----------------------------------------------------

class BaseNode(object):

    def __init__(self, line_number, children):
        self.line_number = line_number
        self.children = children

    def accept(self, visitor):
        visitor.visit(self)

    def get_children(self):
        return self.children

# ----------------------------------------------------

class FormNode(BaseNode):

    def __init__(self, line_number, label, children):
        super(FormNode, self).__init__(line_number, children)
        self.label = label

    def get_label(self):
        return self.label

# ----------------------------------------------------

class QuestionNode(BaseNode):

    def __init__(self, line_number, question, label, val_type, children):
        super(QuestionNode, self).__init__(line_number, children)
        self.question = question
        self.label = label
        self.val_type = val_type

    def get_question(self):
        return self.question

    def get_label(self):
        return self.label

    def get_val_type(self):
        return self.val_type

# ----------------------------------------------------