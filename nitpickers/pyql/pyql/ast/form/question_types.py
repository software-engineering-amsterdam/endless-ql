from pyql.ast.ast import ASTNode


class Type(ASTNode):

    def __init__(self, location, type_name="UnknownType"):
        super().__init__(location)
        self._type_name = type_name

    @property
    def type_name(self):
        return self._type_name


class String(Type):

    def __init__(self, location):
        super().__init__(location, "String")


class Integer(Type):

    def __init__(self, location):
        super().__init__(location, "Integer")


class Boolean(Type):

    def __init__(self, location):
        super().__init__(location, "Boolean")


class Decimal(Type):

    def __init__(self, location):
        super().__init__(location, "Decimal")


class Date(Type):

    def __init__(self, location):
        super().__init__(location, "Date")


class Money(Type):

    def __init__(self, location):
        super().__init__(location, "Money")


if __name__ == "__main__":
    b = Decimal(code_location.CodeLocation(2, 3))
    print(b.type_name)
