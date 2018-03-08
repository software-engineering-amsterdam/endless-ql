class Type:

    def __init__(self, type_name="UnknownType"):
        self._type_name = type_name

    def accept(self, visitor):
        return visitor.visit(self)

    @property
    def type_name(self):
        return self._type_name

    def __repr__(self):
        return self._type_name


class String(Type):

    def __init__(self):
        super().__init__("String")


class Integer(Type):

    def __init__(self):
        super().__init__("Integer")


class Boolean(Type):

    def __init__(self):
        super().__init__("Boolean")


class Decimal(Type):

    def __init__(self):
        super().__init__("Decimal")


class Date(Type):

    def __init__(self):
        super().__init__("Date")


class Money(Type):

    def __init__(self):
        super().__init__("Money")
