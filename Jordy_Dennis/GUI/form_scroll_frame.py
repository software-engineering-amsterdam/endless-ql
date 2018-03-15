"""
    A scrollframe is basically a modifyable frame with a scrollbar

    Each scrollFrame contains a scrollbar, a canvas, and a contentsFrame.
    The contentsFrame can contain widgets. 
    The canvas is only used to attach the scrollbar to the contents frame
"""


from .gui_imports import *

class ScrollFrameGui:

    def __init__(self, parent):
        self.frame = create_frame(parent, 'black')
        self.frame.pack(expand=True, fill='both')
        self.canvas, self.contentsFrame = self.createScrollCanvas(self.frame)
        self.canvas.pack(expand=True, fill="both")

        # create a window for the contents frame inside the canvas
        self.window = self.canvas.create_window((0, 0), window=self.contentsFrame, anchor='nw')

        # binding correct update functions to canvas and contents
        self.contentsFrame.bind("<Configure>", self.onConfigureContentFrame)
        self.canvas.bind("<Configure>", self.onConfigureCanvas)

    """
        Create the canvas, together with the frame that will contain the contents
    """
    def createScrollCanvas(self, parent):
        canvas = Canvas(parent, background="pink")
        contentsFrame = create_frame(canvas, "blue")

        scrollbar = Scrollbar(parent, command=canvas.yview)
        scrollbar.pack(side=RIGHT, fill='both')
        canvas.configure(yscrollcommand=scrollbar.set)
        return canvas, contentsFrame

    """
        used to set the window of the canvas to the total width of the canvas
    """
    def onConfigureCanvas(self, event):
        canvas_width = event.width
        self.canvas.itemconfig(self.window, width=canvas_width)

    """
        Making sure the scroller stays on the canvas and doesnt allow to scroll to infinity
    """
    def onConfigureContentFrame(self, event):
        self.canvas.configure(scrollregion=self.canvas.bbox('all'))

    """
        Return contents so widgets can be added
    """
    def get_contents(self):
        return self.contentsFrame

    def get_frame(self):
        return self.frame
