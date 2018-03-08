#!/usr/bin/env python
import sys
from PyQt5 import QtCore, QtGui, QtWidgets

def onResize(event):
    print(event)

if __name__ == "__main__":
    app = QtWidgets.QApplication(sys.argv)
    widget = QtWidgets.QPushButton('Test')
    widget.resizeEvent = onResize
    widget.resize(640, 480)
    widget.show()
    sys.exit(app.exec_())