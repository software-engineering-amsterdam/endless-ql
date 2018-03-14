class WidgetWidth:
    def __init__(self,width=200):
        self.width = width

    def getWidth(self):
        return self.width

class WidgetFont:
    def __init__(self, font=15):
        self.font = font

    def getFont(self):
        return self.font

class WidgetFontSize:
    def __init__(self,fontSize=15):
        self.fontSize = fontSize

    def getFontSize(self):
        return self.fontSize

class ColorWidget:
    def __init__(self,color='aaaaaa'):
        self.color = color

    def getColor(self):
        return self.color



class RadioWidget:
    def __init__(self, options):
        self.options = options

    def getOptions(self):
        return self.options

class CheckBoxWidget:
    pass

class SpinBoxWidget:
    def __init__(self, min, max):
        self.min = min
        self.max = max