class QLSast(object):
    pass


class StylesheetNode(QLSast):

    def __init__(self, name):
        self.name = name
        self.pages = []

    def __repr__(self):
        return "Stylesheet: {} Pages: {}".format(self.name, self.pages)


class PageNode(QLSast):

    def __init__(self, name):
        self.name = name
        self.sections = []
        self.default_style_widgets = []

    def __repr__(self):
        return "Page: {} Sections: {} Styles: {}".format(self.name, self.sections, self.default_style_widgets)


class SectionNode(QLSast):
    
    def __init__(self, name):
        self.name = name
        self.sections = []
        self.questions = []
        self.default_style_widgets = []

    def __repr__(self):
        return "Section: {} Sections: {} Questions: {} Styles: {}".format(self.name, self.sections, self.questions, self.default_style_widgets)


class QuestionNode(QLSast):

    def __init__(self, var):
        self.var = var
        self.widget = None

    def __repr__(self):
        return "Question: {} Widget: {}".format(self.var, self.widget)


class WidgetNode(QLSast):

    def __init__(self, widget):
        self.widget = widget
        self.options = None

    def __repr__(self):
        return "Widget: {} Options: {}".format(self.widget, self.options)


class StyleOptionsNode(QLSast):
    
    # def __init__(self, vartype, width=100, font="Arial", fontsize=11, color=0x000000):
        # self.vartype = vartype
        # self.width = width
        # self.font = font
        # self.fontsize = fontsize
        # self.color = color

    # todo: vartype in widget?
    def __init__(self, vartype):
        self.vartype = vartype
        self.options = None

    def __repr__(self):
        return "Style vartype: {} Options: {}".format(self.vartype, self.options)