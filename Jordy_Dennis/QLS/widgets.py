"""
    Base classes with some necessary methods
"""

class AttributeDefault:

    def getAttributeType(self):
        return self.attType

class WidgetDefault:
    def checkTypes(self):
        return self.type

    def getWidget(self):
        return self.widget


"""
    These are style settings that can be applied to the text label of a question. 
"""

class StyleWidth(AttributeDefault):
    def __init__(self, width=200):
        self.width = width
        self.attType = 'width'

    def getValue(self):
        return self.width

    def __repr__(self):
        return "WidgetWidth: {}".format(self.width)


class StyleFont(AttributeDefault):
    def __init__(self, font=15):
        self.font = font
        self.attType = 'font'

    def getValue(self):
        return self.font

    def __repr__(self):
        return "WidgetFont: {}".format(self.font)

class StyleFontSize(AttributeDefault):
    def __init__(self, fontSize=15):
        self.fontSize = fontSize
        self.attType = 'fontSize'

    def getValue(self):
        return self.fontSize

    def __repr__(self):
        return "WidgetFontSize: {}".format(self.fontSize)


class StyleColor(AttributeDefault):
    def __init__(self, color='aaaaaa'):
        self.color = color
        self.attType = 'color'

    def getValue(self):
        return self.color

    def __repr__(self):
        return "WidgetColor: {}".format(self.color)

"""
    These are classes that contain the needed variables for the actual widgets in the GUI.
    The types are the values supported by the widgets itself and are used for the typecheck

    Boolean Widgets
"""

class RadioWidget(AttributeDefault, WidgetDefault):
    def __init__(self, trueVal, falseVal):
        self.minVal = trueVal
        self.maxVal = falseVal
        self.type = [bool]
        self.attType = 'widget'
        self.widget = 'radio'

    def getOptions(self):
        return self.options

    def __repr__(self):
        return "RadioWidget: T:{} F:{}".format(self.minVal, self.maxVal)

class CheckBoxWidget(AttributeDefault, WidgetDefault):
    def __init__(self):
        self.type = [bool]
        self.attType = 'widget'
        self.widget = 'checkbox'

    def __repr__(self):
        return "CheckBoxWidget"

class DropdownWidget(AttributeDefault, WidgetDefault):
    def __init__(self):
        self.type = [bool]
        self.attType = 'widget'
        self.widget = 'dropdown'

    def __repr__(self):
        return "DropdownWidget"


"""
    Integer and Text Widgets
"""
class SpinboxWidget(AttributeDefault, WidgetDefault):
    def __init__(self, minVal, maxVal):
        self.minVal = minVal
        self.maxVal = maxVal
        self.type = [int, float]
        self.attType = 'widget'
        self.widget = 'spinbox'

    def __repr__(self):
        return "SpinBoxWidget: {},{}".format(self.minVal, self.maxVal)

class TextWidget(AttributeDefault, WidgetDefault):
    def __init__(self):
        self.type = [int, str, float]
        self.attType = 'widget'
        self.widget = 'text'

    def __repr__(self):
        return "TextWidget"

class SliderWidget(AttributeDefault, WidgetDefault):
    def __init__(self, minVal, maxVal):
        self.minVal = minVal
        self.maxVal = maxVal
        self.type = [int, float]
        self.attType = 'widget'
        self.widget = 'slider'

    def __repr__(self):
        return "SpinBoxWidget: {},{}".format(self.minVal, self.maxVal)


