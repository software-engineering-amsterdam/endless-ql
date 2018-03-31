from antlr4 import ParseTreeVisitor
from antlr.generated.QLParser import QLParser

from PyQt5 import QtWidgets
from PyQt5 import QtCore
from gui import question_classes


def visit_ql(ast):
    """ Traverse the parsed AST """
    walker = QLVisitor()
    walker.visit(ast)
    warning_message = check_duplicate_question_strings(walker.question_ids, walker.questions)
    return [walker.question_ids, walker.questions, walker.error_message, warning_message]


def check_duplicate_question_strings(question_ids, questions):
    """ returns warning if duplicate question strings """
    question_list = []
    warning_string = None

    for question_id in question_ids:
        question = questions[question_id]
        question_list.append(question.question_string)

    duplicates = set([duplicate for duplicate in question_list if question_list.count(duplicate) > 1])
    if len(duplicates) > 0:
        warning_string = "Warning: duplicate question strings:{}".format(str(duplicates)[1:-1])
    return warning_string


class QLVisitor(ParseTreeVisitor):
    def __init__(self):
        self.error_message = None
        self.question_ids = []  # Ordered list of question IDs.
        self.questions = {}  # {question_id: question object}

    def defaultResult(self):
        return []

    def visitChildren(self, node):
        """ Top down traversal """
        result = self.defaultResult()
        child_count = node.getChildCount()
        for i in range(child_count):
            child = node.getChild(i)
            # child.accept() calls the visit function for the particular node type of child
            child_result = child.accept(self)
            if self.error_message:
                return
            result.extend(child_result)

        return result

    def visitForm(self, ctx: QLParser.FormContext):
        return self.visitChildren(ctx)

    def visitBlock(self, ctx: QLParser.BlockContext):
        return self.visitChildren(ctx)

    def visitStmt(self, ctx: QLParser.StmtContext):
        return self.visitChildren(ctx)

    def visitQuestion(self, ctx: QLParser.QuestionContext):
        """ Initiates question object """
        # todo: instead of initiating question, simply save the ID and node to lists
        question_string = ctx.STRING().getText()
        question_id = ctx.ID().getText()
        data_type = ctx.type().getText()

        if question_id in self.question_ids:
            self.error_message = ["Error: duplicate question IDs: {}".format(question_id)]
            return

        if data_type == 'boolean':
            question_object = question_classes.BooleanQuestion(question_id, question_string)
        elif data_type == 'money':
            question_object = question_classes.MoneyQuestion(question_id, question_string)
        else:
            self.error_message = ["Error: unknown data_type: {}".format(data_type)]
            return

        self.question_ids.append(question_id)
        self.questions[question_id] = question_object

        return self.visitChildren(ctx)

    def visitDeclaration(self, ctx: QLParser.DeclarationContext):
        """ Sets declared answer and error catching with set_answer_label """
        result = self.visitChildren(ctx)
        declared_value = str(result.pop())

        question_id = ctx.parentCtx.ID().getText()
        question = self.questions[question_id]
        self.error_message = question.set_answer_label(declared_value)
        return result

    def visitExpression(self, ctx: QLParser.ExpressionContext):
        return self.visitChildren(ctx)

    def visitIf_(self, ctx: QLParser.If_Context):
        """
        Gets the question IDs of the if argument and the question contained in the if, then links them so that the
        contained question becomes invisible when the argument becomes False.
        """
        conditional_id = ctx.expression().getText()

        if conditional_id not in self.question_ids:
            self.error_message = ["Error: if argument is undefined: {}".format(conditional_id)]
            return
        elif self.questions[conditional_id].data_type != 'boolean':
            self.error_message = ["Error: if argument is not boolean: {}".format(conditional_id)]
            return

        conditional_question = self.questions[conditional_id]
        statements = ctx.block().stmt()
        result = self.visitChildren(ctx)

        for statement in statements:
            question_node = statement.question()
            question_id = question_node.ID().getText()
            question = self.questions[question_id]
            conditional_question.add_if_question(question)

        return result

    def visitType(self, ctx: QLParser.TypeContext):
        return self.visitChildren(ctx)

    def visitValue(self, ctx: QLParser.ValueContext):
        result = self.visitChildren(ctx)
        if result:
            return result
        else:
            return [ctx.getText()]

    def visitCompute(self, ctx: QLParser.ComputeContext):
        return self.visitChildren(ctx)

    @staticmethod
    def visitArithmetic_(ctx: QLParser.Arithmetic_Context):
        result = eval(ctx.INT()[0].getText() + ctx.ARITHMETIC_OP().getText() + ctx.INT()[1].getText())
        return [result]

    def visitBoolean_(self, ctx: QLParser.Boolean_Context):
        return self.visitChildren(ctx)
