class QLSast(object):
    pass


class StylesheetNode(QLSast):

    def __init__(self, name):
        self.name = name
        self.pages = []


class PageNode(QLSast):

    def __init__(self, name):
        self.name = name
        self.sections = []
        self.default_style = None


class SectionNode(QLSast):
    
    def __init__(self, name):
        self.name = name
        self.sections = []
        self.questions = []
        self.default_style = None


class QuestionNode(QLSast):

    def __init__(self, var):
        self.var = var
        self.widget = None


class WidgetNode(QLSast):

    def __init__(self, widget):
        self.widget = widget


class DefaultStyle(QLSast):
    
    def __init__(self, vartype, widget, width=100, font="Arial", fontsize=11, color=0x000000):
        self.vartype = vartype
        self.widget = widget
        self.width = width
        self.font = font
        self.fontsize = fontsize
        self.color = color
