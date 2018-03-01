from pyql.ast.base_visitor import StatementVisitor


class TypeChecker(StatementVisitor):

    def visit_form(self, form):
        print("Visiting form")
        form.block.accept(self)

    def visit_block(self, block):
        print("Visiting block")
        questions = block.statements
        for q in questions:
            q.accept(self)

    def visit_statement(self, statement):
        print("Visiting statement")

    def visit_question(self, question):
        print("Visiting questions")

    def visit_computed_question(self, question):
        print("Visiting computed questions")

    def visit_if(self, if_statement):
        print("Visiting if statement")
        if_statement.block.accept(self)

    def visit_if_else(self, if_else_statement):
        print("Visiting if else statement")
        if_else_statement.if_block.accept(self)
        if_else_statement.else_block.accept(self)

    def visit_ast_node(self, node):
        print("Visiting generic AST node. Is accept method implemented?")

    def visit_identifier(self, identifier):
        print("Visiting identifier")

    def visit_question_type(self, question_type):
        print("Visiting question type")
