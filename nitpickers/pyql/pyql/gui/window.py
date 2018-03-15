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

        self._statements = {}

    def build_statement(self, identifier, text, widget, value):
        current_row = len(self._statements) + 1

        question_label = ttk.Label(self, text=text, width=40)
        question_label.grid(column=0, row=current_row)

        question_widget = widget(self, identifier, value)
        question_widget.grid(column=1, row=current_row)

        return question_label, question_widget

    def add_question(self, identifier, text, widget, value):
        question_label, question_widget = self.build_statement(identifier, text, widget, value)
        self._statements[identifier] = (question_label, question_widget)

    def add_computed_question(self, identifier, text, widget, value):
        computed_question_label, computed_question_widget = self.build_statement(identifier, text, widget, value)
        computed_question_widget.configure(state='disabled')
        self._statements[identifier] = (computed_question_label, computed_question_widget)

    def clear(self):
        for i, (l, w) in self._statements.items():
            l.destroy()
            w.destroy()
        self._statements = {}

    def btn_update(self):
        for identifier, (_, widget) in self._statements.items():
            widget_value = widget.get()
            if widget_value is not None:
                self._symbol_table.update_or_create(identifier, widget_value)
        self._gui_visitor.build()
