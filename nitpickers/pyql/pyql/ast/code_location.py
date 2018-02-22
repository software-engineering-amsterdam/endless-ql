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


def build(l):
    if len(l) == 2:
        return CodeLocation(l[0], l[1])
    head, *tail = l
    return CodeLocation(head, build(tail))


if __name__ == "__main__":
    list = [1, 2, 3, 4, 5]
    cc = build(list)
    print(cc)