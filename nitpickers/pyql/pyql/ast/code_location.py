class CodeLocation:

    def __init__(self, line, column):
        self._line = line
        self._column = column

    @property
    def column(self):
        return self._column

    @property
    def line(self):
        return self._line

    def __repr__(self):
        return "(" + str(self.line) + "," + str(self.column) + ")"
