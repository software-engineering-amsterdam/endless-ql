class Metadata:
    def __init__(self, line):
        self.__line = line

    @property
    def line(self):
        return self.__line
