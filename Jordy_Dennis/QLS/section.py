

class Section:
    def __init__(self, name):
        self.name = name
        self.defaults = []
        self.questions = []
        self.sections = []

    def addSection(self, section):
        self.sections.append(section)

    def addQuestion(self, question):
        self.questions.append(question)

    def addDefault(self, default):
        self.defaults.append(default)

    def __repr__(self):
        return "Section {}: questions: {} sections: {} defaults: {}".format(self.name, self.questions, self.sections, self.defaults)