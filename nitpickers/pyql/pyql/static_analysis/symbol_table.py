from pyql.ast.base_visitor import StatementVisitor


class SymbolTable:

    def __init__(self):
        self._dictionary = {}

    @property
    def dictionary(self):
        return self._dictionary

    def create(self, key, value):
        if key in self._dictionary:
            raise Exception("Key {0} already exists".format(key))
        self._dictionary[key] = value

    def update(self, key, value):
        if key not in self._dictionary:
            raise Exception("Invalid key: {0}".format(key))
        self._dictionary[key] = value

    def get(self, key):
        if key not in self._dictionary:
            raise Exception("Invalid key: {0}".format(key))
        return self._dictionary[key]

    def remove(self, key):
        if key not in self._dictionary:
            raise Exception("Invalid key: {0}".format(key))
        del self._dictionary[key]


class SymbolTableBuilder(StatementVisitor):

    def __init__(self):
        self.symbol_table = SymbolTable()

    def build(self, tree):
        tree.accept(self)
        return self.symbol_table

    def visit_form(self, form):
        form.block.accept(self)

    def visit_block(self, block):
        [q.accept(self) for q in block.statements]

    def visit_statement(self, statement):
        pass

    def visit_question(self, question):
        self.symbol_table.create(question.identifier.identifier, question)

    def visit_computed_question(self, question):
        print(question.identifier)

    def visit_if(self, if_statement):
        if_statement.block.accept(self)

    def visit_if_else(self, if_else_statement):
        if_else_statement.if_block.accept(self)
        if_else_statement.else_block.accept(self)

    def visit_ast_node(self, node):
        pass

    def visit_identifier(self, identifier):
        pass

    def visit_question_type(self, question_type):
        pass
