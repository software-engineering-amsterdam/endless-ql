class BaseNode(object):
    def __init__(self, position):
        self._position = position

    def accept(self, visitor):
        visitor.visit(self)

    @property
    def position(self):
        return self._position