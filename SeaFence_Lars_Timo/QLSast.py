# Lars Lokhoff, Timo Dobber
# This class defines the structure of the QLS AST.

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

    def __init__(self, variable):
        self.node_type = "question"
        self.variable = variable
        self.widget = None

    def __repr__(self):
        return "Question: {} Widget: {}".format(self.variable, self.widget)

    def getVariableName(self):
        return self.variable

    def getWidget(self):
        return self.widget


class WidgetNode(QLSast):

    def __init__(self, widget):
        self.node_type = "widget"
        self.widget = widget
        self.options = None
        self.min_value = 0
        self.max_value = 100

    def __repr__(self):
        return "Widget: {} Options: {} Min: {} Max: {}".format(self.widget, self.options, self.min_value, self.max_value)

    def getWidget(self):
        return self.widget


class StyleOptionsNode(QLSast):

    def __init__(self, variable_type):
        self.node_type = "options"
        self.variable_type = variable_type
        self.options = None

    def __repr__(self):
        return "Style variable type: {} Options: {}".format(self.variable_type, self.options)
