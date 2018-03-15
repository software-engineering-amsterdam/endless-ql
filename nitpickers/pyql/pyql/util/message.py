class Message:

    def __init__(self, text="Default message"):
        self._text = text

    def __str__(self):
        return self._text

    __repr__ = __str__

    @property
    def text(self):
        return self._text


class Warning(Message):

    def __init__(self, text="Warning message"):
        super().__init__(text)

    def __repr__(self):
        return "WARNING " + super().__repr__()


class Error(Message):

    def __init__(self, text="Error message"):
        super().__init__(text)

    def __repr__(self):
        return "ERROR " + super().__str__()


if __name__ == "__main__":
    print(Warning())
