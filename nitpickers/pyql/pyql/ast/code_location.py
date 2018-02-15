class CodeLocation:

    def __init__(self, row, column):
        self.row = row
        self._column = column

    @property
    def column(self):
        return self._column

    @column.setter
    def column(self, value):
        self._column = value

    def get_row(self):
        return self.row

    def __repr__(self):
        return "Row: " + str(self.row) + "; Column: " + str(self.column)