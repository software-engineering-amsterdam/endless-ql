import tkinter
from tkinter import ttk

class Frm(ttk.Frame):
    def __init__(self, parent, *args, **kwargs):
        ttk.Frame.__init__(self, parent, *args, **kwargs)
        self.root = parent
        self.init_frm()

    def init_frm(self):
        self.root.title("Questionaire")
        self.grid(column=0, row=0, sticky='nsew')
        self.menubar = tkinter.Menu(self.root)

        self.num1_entry = ttk.Entry(self, width=5)
        self.num1_entry.grid(column=1, row = 2)

        self.calc_button = ttk.Button(self, text='Calculate',
                command=self.calculate)
        self.calc_button.grid(column=0, row=3, columnspan=4)

        for child in self.winfo_children():
            child.grid_configure(padx=5, pady=5)


if __name__ == '__main__':
    root = tkinter.Tk()
    Frm(root)
    root.mainloop()
