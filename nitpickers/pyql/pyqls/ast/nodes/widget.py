from util.ast import ASTNode


class Widget(ASTNode):

    def __init__(self, location, type):
        super().__init__(location)
        self._type = type

    @property
    def type(self):
        return self._type


class WidgetType(ASTNode):

    def __init__(self, location):
        super().__init__(location)


class Radio(WidgetType):

    def __init__(self, location, options: []):
        super().__init__(location)
        self._options = options

    @property
    def options(self):
        return self._options

    def __repr__(self):
        return "Radio button at {0}".format(self.location)

    __str__ = __repr__


class DropDown(WidgetType):

    def __init__(self, location, options: []):
        super().__init__(location)
        self._options = options

    @property
    def options(self):
        return self._options

    def __repr__(self):
        return "DropDown at {0}".format(self.location)

    __str__ = __repr__


class SpinBox(WidgetType):

    def __init__(self, location):
        super().__init__(location)

    def __repr__(self):
        return "SpinBox at {0}".format(self.location)

    __str__ = __repr__


class CheckBox(WidgetType):

    def __init__(self, location):
        super().__init__(location)

    def __repr__(self):
        return "CheckBox at {0}".format(self.location)

    __str__ = __repr__


class Slider(WidgetType):

    def __init__(self, location):
        super().__init__(location)

    def __repr__(self):
        return "Slider at {0}".format(self.location)

    __str__ = __repr__


class Text(WidgetType):

    def __init__(self, location):
        super().__init__(location)

    def __repr__(self):
        return "Text at {0}".format(self.location)

    __str__ = __repr__
