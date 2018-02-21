class CodeLocation:

    def __init__(self, row, column):
        self._row = row
        self._column = column

    @property
    def column(self):
        return self._column

    @property
    def row(self):
        return self._row

    def __repr__(self):
        return "(" + str(self.row) + "," + str(self.column) + ")"
