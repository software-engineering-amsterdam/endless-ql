from tkinter import ttk
from tkinter.font import *
from .gui_methods import *
from functools import partial
from .question_generator import *

def printDict(dic):
    pp = pprint.PrettyPrinter(indent=4)
    pp.pprint(dic)
