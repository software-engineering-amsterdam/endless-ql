from PyQt5.QtWidgets import QLabel


class Question:
    def __init__(self, label, identifier, answer_type, answer, show):
        self.__label = label
        self.__identifier = identifier
        self.__answer_type = answer_type
        self.__answer = answer
        self.__show = show

    @property
    def label(self):
        return self.__label

    @label.setter
    def label(self, value):
        self.__label = value

    @property
    def identifier(self):
        return self.__identifier

    @property
    def answer_type(self):
        return self.__answer_type

    @property
    def answer(self):
        return self.__answer

    @property
    def show(self):
        return self.__show

    @show.setter
    def show(self, value):
        self.__show = value

    def pyqt5_render(self, layout):
        layout.addRow(QLabel(self.label), self.answer_type.pyqt5_default_widget())
