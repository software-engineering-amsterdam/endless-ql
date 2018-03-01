class Position(object):
    def __init__(self, line, column):
        self.__line = line
        self.__column = column

    @property
    def line(self):
        return self.__line

    @property
    def column(self):
        return self.__column
