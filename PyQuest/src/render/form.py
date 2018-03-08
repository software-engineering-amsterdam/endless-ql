class Form:
    def __init__(self, identifier):
        self.__identifier = identifier
        self.__block = []

    @property
    def identifier(self):
        return self.__identifier

    @property
    def block(self):
        return self.__block

    def find_question_of_widget(self, widget):
        for question in self.block:
            if question.widget == widget:
                return question.identifier

        return None
