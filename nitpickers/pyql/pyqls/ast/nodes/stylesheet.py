from util.ast import ASTNode


class StyleSheet(ASTNode):

    def __init__(self, location, name, block):
        super().__init__(location)
        self._name = name
        self._block = block

    @property
    def name(self):
        return self._name

    @property
    def block(self):
        return self._block

    def __repr__(self):
        return "StyleSheet {0}".format(self.name)

    __str__ = __repr__
