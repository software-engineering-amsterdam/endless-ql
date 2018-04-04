from util.message import *
from util.multimethods import multimethod


class MessageHandler(object):
    _instance = None

    def __new__(cls):
        if MessageHandler._instance is None:
            MessageHandler._instance = object.__new__(cls)
            MessageHandler._instance._errors = []
            MessageHandler._instance._warnings = []
        return MessageHandler._instance

    @property
    def messages(self):
        return self.errors + self.warnings

    @property
    def errors(self):
        return self._instance._errors


    @property
    def warnings(self):
        return self._instance._warnings

    @multimethod(Error)
    def add(self, message):
        self._instance._errors.append(message)

    @multimethod(Warning)
    def add(self, message):
        self._instance._warnings.append(message)

    @multimethod(Error)
    def remove(self, message):
        self._instance._errors = [q for q in self.errors if q != message]

    @multimethod(Warning)
    def remove(self, message):
        self._instance._warnings = [q for q in self.warnings if q != message]
