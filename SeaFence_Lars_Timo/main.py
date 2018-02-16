import sys
from antlr4 import *
from antlr_files_2.QLLexer import QLLexer
from antlr_files_2.QLParser import QLParser
from antlr_files_2.QLVisitor import QLVisitor
try:
    from Tkinter import *
except ImportError:
    from _tkinter import *

def main(argv):
    # input = FileStream(argv[1])
    # lexer = QLLexer(input)
    # stream = CommonTokenStream(lexer)
    # parser = QLParser(stream)

    # tree = parser.form()
    # print(tree.toStringTree(recog=parser))

    root = Tk()
    w = Label(root, text="Hello, world!")
    w.pack()
    root.mainloop()

if __name__ == '__main__':
    main(sys.argv)