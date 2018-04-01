import tkinter

from pyql.gui.gui_window import GUIWindow
from pyql.gui.messages_window import MessageWindow

from util.message_handler import MessageHandler


class GUI:
    def __init__(self, ast):
        root = tkinter.Tk()

        messages = MessageHandler().messages
        if not messages:
            GUIWindow(root, ast)
        else:
            MessageWindow(root, messages)

        root.mainloop()
