from util.message import *


class MessageHandler(object):
    _instance = None

    def __new__(cls):
        if MessageHandler._instance is None:
            MessageHandler._instance = object.__new__(cls)
            MessageHandler._instance._messages = []
        return MessageHandler._instance

    @property
    def messages(self):
        return self._instance._messages

    def add(self, message):
        self._instance._messages.append(message)

    def remove(self, message):
        self._instance._messages = [q for q in self.messages if q != message]


if __name__ == "__main__":
    error = Error("Same error")
    MessageHandler().add(error)
    print(MessageHandler().messages)
    MessageHandler().remove(error)
    print(MessageHandler().messages)
