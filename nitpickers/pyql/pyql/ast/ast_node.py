from pyql.ast import code_location


class ASTNode:

    def __init__(self, loc: code_location):
        self.location = loc

    def get_code_location(self):
        return self.location

    def __repr__(self):
        return "AST node at: " + str(self.location)


if __name__ == '__main__':
    location = code_location.CodeLocation(2, 3)
    node = ASTNode(location)
    print(node)
