from ast_visitor import ASTVisitor
import nodes


example = """
form taxOfficeExample {
    "Did you sell a house in 2010?"
        hasSoldHouse: boolean
}
"""

print('Creating the abstract syntax tree for example:\n '  + example)

question = nodes.QuestionNode(1, "Did you sell a house in 2010", 'hasSoldHouse', 'boolean', [])
form = nodes.FormNode(0, 'taxOfficeExample', [question])
visitor = ASTVisitor()
visitor.visit(form)

print('')
