from antlr4 import ParseTreeVisitor
from antlr.generated.QLSParser import QLSParser


def visit_qls(tree, question_ids, questions):
    """ Traverse the parsed tree """
    walker = QLSVisitor(question_ids, questions)
    walker.visit(tree)
    return [walker.question_ids, walker.questions, walker.error_message]


class QLSVisitor(ParseTreeVisitor):
    def __init__(self, question_ids, questions):
        self.error_message = None
        self.question_ids = question_ids
        self.placed_questions = []
        self.questions = questions

    def defaultResult(self):
        return {}

    def visitChildren(self, node):
        result = self.defaultResult()
        n = node.getChildCount()
        for i in range(n):
            if not self.shouldVisitNextChild(node, result):
                return
            child = node.getChild(i)

            # child.accept() calls the visit function for the particular node type of child
            child_result = child.accept(self)

            # If error, nothing more is visited
            if self.error_message:
                return {}

            for key in child_result:
                if key in result:
                    result[key].extend(child_result[key])
                else:
                    result[key] = child_result[key]
        return result


    def visitStylesheet(self, ctx: QLSParser.StylesheetContext):
        return self.visitChildren(ctx)


    def visitPage(self, ctx: QLSParser.PageContext):
        return self.assign_question_attributes(ctx)

    def visitSection(self, ctx: QLSParser.SectionContext):
        return self.assign_question_attributes(ctx)

    def assign_question_attributes(self,ctx):
        result = self.visitChildren(ctx)
        if 'questions' in result:
            question_ids = result['questions']

            if 'defaults' in result:
                default_attributes = result['defaults']
            else:
                default_attributes = {}

            for question_id in question_ids:
                question = self.questions[question_id]
                question.set_attributes(default_attributes)

            result = {'questions': question_ids}
        else:
            result = self.defaultResult()
        return result


    def visitQuestion(self, ctx: QLSParser.QuestionContext):
        if not ctx.ID().getText() in self.questions:
            self.error_message = ["Error: undefined reference to QL ID"]
            return

        question_id = ctx.ID().getText()

        if question_id in self.placed_questions:
            self.error_message = ["Error: question defined multiple times: {}".format(question_id)]
            return
        self.placed_questions.append(question_id)

        question = self.questions[ctx.ID().getText()]
        attributes = self.visitChildren(ctx)
        question.set_attributes(attributes['attributes'])

        return {'questions': [question_id]}


    def visitDefault(self, ctx: QLSParser.DefaultContext):
        children = self.visitChildren(ctx)
        return {'defaults': {children['data_type']: children['attributes']}}


    def visitType(self, ctx: QLSParser.TypeContext):
        result = self.visitChildren(ctx)
        result.update({'data_type': ctx.getText()})
        return result


    def visitAttributes(self, ctx: QLSParser.AttributesContext):
        attributes = self.visitChildren(ctx)
        return {'attributes': attributes}


    def visitWidth(self, ctx: QLSParser.WidthContext):
        result = self.visitChildren(ctx)
        result.update({'width': int(ctx.INT().getText())})
        return result

    def visitFont(self, ctx: QLSParser.FontContext):
        result = self.visitChildren(ctx)
        result.update({'font': ctx.STRING().getText()})
        return result

    def visitFontsize(self, ctx: QLSParser.FontsizeContext):
        result = self.visitChildren(ctx)
        result.update({'font_size': int(ctx.INT().getText())})
        return result

    def visitColor(self, ctx: QLSParser.ColorContext):
        result = self.visitChildren(ctx)
        result.update({'color': ctx.HEX().getText()})
        return result


    def visitCheckbox(self, ctx: QLSParser.CheckboxContext):
        result = self.visitChildren(ctx)
        result.update({'widget': 'checkbox'})
        return result

    def visitRadio(self, ctx: QLSParser.RadioContext):
        result = self.visitChildren(ctx)
        result.update({'widget': 'radio'})
        return result

    def visitSpinbox(self, ctx: QLSParser.SpinboxContext):
        result = self.visitChildren(ctx)
        result.update({'widget': 'spinbox'})
        return result

    def visitChoices(self, ctx: QLSParser.ChoicesContext):
        result = self.visitChildren(ctx)
        choices = ctx.getText().replace("'","\"").split("\"")
        result.update({'choices': [choices[1],choices[3]]})
        return result
