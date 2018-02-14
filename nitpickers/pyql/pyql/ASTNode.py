from pyql import CodeLocation


class ASTNode:

    def __init__(self, loc: CodeLocation):
        self.location = loc

    def get_code_location(self):
        return self.location

    def __str__(self):
        return "AST node at: " + str(self.location)


if __name__ == '__main__':
    location = CodeLocation.CodeLocation(2, 3)
    node = ASTNode(location)
    print(node)
