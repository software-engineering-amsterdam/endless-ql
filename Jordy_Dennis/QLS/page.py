
class Page:
    def __init__(self, name, id, default=None):
        self.name = name
        self.id = id
        self.sections = []
        self.default = []

    def addSection(self, section):
        self.sections.append(section)

    def addDefault(self, default):
        self.default.append(default)

    def __repr__(self):
        return "Page {}-{}: sections: {} default: {}".format(self.name, self.id, self.sections, self.default)
