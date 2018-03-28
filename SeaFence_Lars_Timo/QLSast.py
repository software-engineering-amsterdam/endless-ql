class QLSast(object):

    def getNodeType(self):
        return self.node_type


class StylesheetNode(QLSast):

    def __init__(self, name):
        node_type = "stylesheet"
        self.name = name
        self.pages = []

    def __repr__(self):
        return "Stylesheet: {} Pages: {}".format(self.name, self.pages)


class PageNode(QLSast):

    def __init__(self, name):
        self.node_type = "page"
        self.name = name
        self.sections = []
        self.default_style_widgets = []

    def __repr__(self):
        return "Page: {} Sections: {} Styles: {}".format(self.name, self.sections, self.default_style_widgets)


class SectionNode(QLSast):
    
    def __init__(self, name):
        self.node_type = "section"
        self.name = name
        self.sections = []
        self.questions = []
        self.default_style_widgets = []

    def __repr__(self):
        return "Section: {} Sections: {} Questions: {} Styles: {}".format(self.name, self.sections, self.questions, self.default_style_widgets)


class QuestionNode(QLSast):

    def __init__(self, var):
        self.node_type = "question"
        self.var = var
        self.widget = None

    def __repr__(self):
        return "Question: {} Widget: {}".format(self.var, self.widget)

    def getVariableName(self):
        return self.var

    def getWidget(self):
        return self.widget


class WidgetNode(QLSast):

    def __init__(self, widget):
        self.node_type = "widget"
        self.widget = widget
        self.options = None
        self.min_value = 0
        self.max_value = 0

    def __repr__(self):
        return "Widget: {} Options: {}".format(self.widget, self.options)

    def getWidget(self):
        return self.widget


class StyleOptionsNode(QLSast):
    
    # def __init__(self, vartype, width=100, font="Arial", fontsize=11, color=0x000000):
        # self.vartype = vartype
        # self.width = width
        # self.font = font
        # self.fontsize = fontsize
        # self.color = color

    # todo: vartype in widget?
    def __init__(self, vartype):
        self.node_type = "options"
        self.vartype = vartype
        self.options = None

    def __repr__(self):
        return "Style vartype: {} Options: {}".format(self.vartype, self.options)
