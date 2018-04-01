from pyql.gui.widgets.widgets import *
from pyql.gui.widgets.widget_factory import WidgetFactory
from pyql.gui.gui_visitor import GUIVisitor
from pyql.static_analysis.symbol_table import SymbolTable
from util import errors


class GUIWindow(ttk.Frame):

    def __init__(self, parent, ast):
        ttk.Frame.__init__(self)
        self.root = parent
        self.root.title("Questionnaire")
        self.grid(column=0, row=0, padx=10, pady=10, sticky='nsew')

        self._ast = ast
        self._symbol_table = SymbolTable()
        self._gui_visitor = GUIVisitor(self, self._symbol_table)
        self._widget_factory = WidgetFactory()

        self._statements = {}
        self.load_statements()

    def build_statement(self, identifier, type, text, value):
        frame = ttk.Frame(self, width=60)
        frame.grid(padx=5, pady=5)
        frame.columnconfigure(0, minsize=400)
        frame.columnconfigure(1, minsize=200)

        label = ttk.Label(frame, text=text, anchor="w")
        label.grid(column=0, row=0, padx=5, pady=5, sticky='w')

        widget = self._widget_factory.widget(frame, identifier, type, value)
        widget.grid(column=1, row=0, padx=5, pady=5, sticky='w')
        widget.bind("<FocusOut>", self.update_form)
        widget.bind("<Return>", self.update_form)
        widget.lift()
        return label, widget

    def add_question(self, identifier, type, text, value):
        label, widget = self.build_statement(identifier, type, text, value)
        self._statements[identifier] = (label, widget)

    def add_computed_question(self, identifier, type, text, value):
        label, widget = self.build_statement(identifier, type, text, value)
        widget.configure(state='disabled')
        self._statements[identifier] = (label, widget)

    def clear_statements(self):
        for widget in self.winfo_children():
            widget.destroy()

        self._statements.clear()

    def load_statements(self):
        try:
            self._ast.accept(self._gui_visitor)
        except errors.Error as e:
            self.show_error_message(e)

    def show_error_message(self, message):
        label = ttk.Label(self, text=message, foreground='red')
        label.grid(padx=5, pady=20)

    def update_form(self, w):
        identifier = w.widget.identifier
        value = w.widget.get()

        if value is not None:
            self._symbol_table.update_or_create(identifier, value)

        self.clear_statements()
        self.load_statements()
