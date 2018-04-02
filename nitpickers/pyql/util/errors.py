class Error(Exception):

    def __init__(self, text="Default message"):
        self._text = text

    def __repr__(self):
        return "Error: " + self._text

    def __str__(self):
        return self.__repr__()

    @property
    def text(self):
        return self._text


class Value(Error):

    def __init__(self, text="Value error"):
        super().__init__(text)


class Type(Error):

    def __init__(self, text="Type error"):
        super().__init__(text)


class ZeroDivision(Error):

    def __init__(self, text="ZeroDivision error"):
        super().__init__(text)


class Syntax(Error):

    def __init__(self, text="Syntax error"):
        super().__init__(text)
