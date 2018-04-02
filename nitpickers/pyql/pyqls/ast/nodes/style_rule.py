from util.ast import ASTNode


class Style(ASTNode):

    def __init__(self, location, value):
        super().__init__(location)
        self._value = value

    @property
    def value(self):
        return self._value

    def __repr__(self):
        return "Abstract style rule"

    def __str__(self):
        return self.__repr__()


class Width(Style):

    def __init__(self, location, value):
        super().__init__(location, value)

    def __repr__(self):
        return "Width style rule"


class Font(Style):

    def __init__(self, location, value):
        super().__init__(location, value)

    def __repr__(self):
        return "Font style rule"


class FontSize(Style):

    def __init__(self, location, value):
        super().__init__(location, value)

    def __repr__(self):
        return "FontSize style rule"


class Color(Style):

    def __init__(self, location, value):
        super().__init__(location, value)

    def __repr__(self):
        return "Color style rule"
