"""
    These are style settings that can be applied to the text label of a question. 
"""
class WidgetDefault:
    
    def checkTypes(self):
        return self.type


class StyleWidth(WidgetDefault):
    def __init__(self, width=200):
        self.width = width
        self.type = ['width']

    def getWidth(self):
        return self.width

    def __repr__(self):
        return "WidgetWidth: {}".format(self.width)


class StyleFont(WidgetDefault):
    def __init__(self, font=15):
        self.font = font
        self.type = ['font']

    def getFont(self):
        return self.font

    def __repr__(self):
        return "WidgetFont: {}".format(self.font)

class StyleFontSize(WidgetDefault):
    def __init__(self, fontSize=15):
        self.fontSize = fontSize
        self.type = ['fontSize']

    def getFontSize(self):
        return self.fontSize

    def __repr__(self):
        return "WidgetFontSize: {}".format(self.fontSize)


class StyleColor(WidgetDefault):
    def __init__(self, color='aaaaaa'):
        self.color = color
        self.type = ['color']

    def getColor(self):
        return self.color

    def __repr__(self):
        return "WidgetColor: {}".format(self.color)

"""
    These are classes that contain the needed variables for the actual widgets in the GUI.
    The types are the values supported by the widgets itself and are used for the typecheck

    Boolean Widgets
"""


class RadioWidget(WidgetDefault):
    def __init__(self, trueVal, falseVal):
        self.trueVal = trueVal
        self.falseVal = falseVal
        self.type = [bool]

    def getOptions(self):
        return self.options

    def __repr__(self):
        return "RadioWidget: T:{} F:{}".format(self.trueVal, self.falseVal)

class CheckBoxWidget(WidgetDefault):
    def __init__(self):
        self.type = [bool]

    def __repr__(self):
        return "CheckBoxWidget"

class DropdownWidget(WidgetDefault):
    def __init__(self):
        self.type = [bool]

    def __repr__(self):
        return "DropdownWidget"


"""
    Integer and Text Widgets
"""
class SpinboxWidget(WidgetDefault):
    def __init__(self, min, max):
        self.min = min
        self.max = max
        self.type = [int, float]

    def __repr__(self):
        return "SpinBoxWidget: {},{}".format(self.min, self.max)

class TextWidget(WidgetDefault):
    def __init__(self):
        self.type = [int, str, float]

    def __repr__(self):
        return "TextWidget"

class SliderWidget(WidgetDefault):
    def __init__(self, min, max):
        self.min = min
        self.max = max
        self.type = [int, float]

    def __repr__(self):
        return "SpinBoxWidget: {},{}".format(self.min, self.max)


