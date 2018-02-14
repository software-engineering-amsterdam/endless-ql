class CodeLocation:

    def __init__(self, row, column):
        self.row = row
        self.column = column

    def get_column(self):
        return self.column

    def get_row(self):
        return self.row

    def __repr__(self):
        return "Row: " + str(self.row) + "; Column: " + str(self.column)
