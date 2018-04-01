from pyql.gui.widgets.widgets import *


class MessageWindow(ttk.Frame):

    def __init__(self, parent, messages):
        ttk.Frame.__init__(self, parent)
        self.root = parent

        self.root.title("Questionnaire error window")
        self.grid(column=0, row=0, padx=10, pady=10, sticky='nsew')

        for message in messages:
            self.show_error_message(message)

    def show_error_message(self, message):
        label = ttk.Label(self, text=message, foreground='red')
        label.grid(padx=5, pady=20)
