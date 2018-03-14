class WidgetWidth:
    def __init__(self, width=200):
        self.width = width

    def getWidth(self):
        return self.width

    def __repr__(self):
        return "WidgetWidth: {}".format(self.width)


class WidgetFont:
    def __init__(self, font=15):
        self.font = font

    def getFont(self):
        return self.font

    def __repr__(self):
        return "WidgetFont: {}".format(self.font)

class WidgetFontSize:
    def __init__(self, fontSize=15):
        self.fontSize = fontSize

    def getFontSize(self):
        return self.fontSize

    def __repr__(self):
        return "WidgetFontSize: {}".format(self.fontSize)


class ColorWidget:
    def __init__(self, color='aaaaaa'):
        self.color = color

    def getColor(self):
        return self.color

    def __repr__(self):
        return "WidgetColor: {}".format(self.color)


class RadioWidget:
    def __init__(self, options):
        self.options = options

    def getOptions(self):
        return self.options

    def __repr__(self):
        return "RadioWidget: {}".format(self.options)

class CheckBoxWidget:
    def __repr__(self):
        return "CheckBoxWidget"


class SpinBoxWidget:
    def __init__(self, min, max):
        self.min = min
        self.max = max

    def __repr__(self):
        return "SpinBoxWidget: {},{}".format(self.min, self.max)
