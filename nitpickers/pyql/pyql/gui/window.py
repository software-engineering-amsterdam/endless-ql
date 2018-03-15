import tkinter

from pyql.gui.widgets.widgets import *


class Window(ttk.Frame):
    def __init__(self, parent, gui_visitor, symbol_table, *args, **kwargs):
        ttk.Frame.__init__(self, parent, *args, **kwargs)
        self.root = parent
        self._gui_visitor = gui_visitor
        self._symbol_table = symbol_table

        self.root.title("Questionnaire")
        self.grid(column=0, row=0, sticky='nsew')
        self.menubar = tkinter.Menu(self.root)

        self.btn_update = ttk.Button(self, text='Update', command=self.btn_update)
        self.btn_update.grid(column=0, row=0, columnspan=4)

        self._labels = []
        self._widgets = {}

    def add_question(self, identifier, text, widget):
        current_row = len(self._widgets) + 1

        question_label = ttk.Label(self, text=text, width=40)
        question_label.grid(column=0, row=current_row)
        self._labels.append(question_label)

        value = ""
        try:
            value = self._symbol_table.get(identifier)
        except KeyError:
            pass

        question_widget = widget(self, identifier, value)
        question_widget.grid(column=1, row=current_row)
        self._widgets[identifier] = question_widget

    def clear(self):
        for l in self._labels:
            l.destroy()
        self._labels = []

        for i, w in self._widgets.items():
            w.destroy()
        self._widgets = {}

    def btn_update(self):
        for identifier, w in self._widgets.items():
            widget_value = w.get()
            if widget_value is not None:
                self._symbol_table.update_or_create(identifier, widget_value)
        self._gui_visitor.build()
