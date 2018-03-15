

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

    def getName(self):
        return self.name

    def getQuestions(self):
        return self.questions

    def getSections(self):
        return self.sections


    def __repr__(self):
        return "Section {}: questions: {} sections:{} defaults: {}".format(self.name, self.questions, self.sections, self.defaults)