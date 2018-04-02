from util.ast import ASTNode


class QLSObject(ASTNode):

    def __init__(self, location, stylesheet, ql_filename):
        super().__init__(location)
        self._stylesheet = stylesheet
        self._ql_filename = ql_filename

    @property
    def stylesheet(self):
        return self._stylesheet

    @property
    def ql_filename(self):
        return self._ql_filename

    def __repr__(self):
        return "QLS Object"

    __str__ = __repr__
