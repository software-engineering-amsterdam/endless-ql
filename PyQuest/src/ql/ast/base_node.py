class BaseNode(object):
    def __init__(self, position):
        self.__position = position

    def accept(self, visitor):
        visitor.visit(self)

    @property
    def position(self):
        return self.__position
