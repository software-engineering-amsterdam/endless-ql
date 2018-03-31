class BaseNode:
    def __init__(self, metadata):
        self.__metadata = metadata

    def accept(self, visitor):
        visitor.visit(self)

    @property
    def position(self):
        return self.__metadata
