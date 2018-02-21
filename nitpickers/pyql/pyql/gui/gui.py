import tkinter
from tkinter import ttk

class Frm(ttk.Frame):
    def __init__(self, parent, *args, **kwargs):
        ttk.Frame.__init__(self, parent, *args, **kwargs)
        self.root = parent

        self.root.title("Questionnaire")
        self.grid(column=0, row=0, sticky='nsew')
        self.menubar = tkinter.Menu(self.root)

        self.add_question("Am I asking a question?", bool)
        self.add_question("Am I asking another question?", bool)

        self.btn_send = ttk.Button(self, text='Send', command=self.btn_send)
        self.btn_send.grid(column=0, row=3, columnspan=4)

        for child in self.winfo_children():
            child.grid_configure(padx=5, pady=5)

    def add_question(self, question_text, question_type):
        self.num_value1 = ttk.Label(self, text=question_text, width=40)\
            .grid(column=0)
        self.num_value = ttk.Entry(self, width=20).grid(column=1)

    def btn_send(self):
        print("yeahh button click", self.num_value.get())


if __name__ == '__main__':
    root = tkinter.Tk()
    Frm(root)
    root.mainloop()
