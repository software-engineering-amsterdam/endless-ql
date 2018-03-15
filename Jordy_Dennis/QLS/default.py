
from .qlast_methods import *

class DefaultStyle:
    def __init__(self, defaultType, line):
        self.attributes = []
        self.line = line
        self.varDict = None
        self.type = defaultType

    """
        Check if the attribute types only occur once. 
        We check the styling attributes by adding them to a list and see if they occur only once.

        We check the widgets by using a boolean
    """
    def checkTypes(self):
        styleTypes = []
        hasWidget = False
        allStyleTypes = ["font", "fontSize", "width", "color"]
        for attribute in self.attributes:
            attType = attribute.getAttributeType()

            # In case of styling attributes, add them to the list
            if attType in allStyleTypes:
                styleTypes.append(attType)

            # In case of widgets first check if the type of widget is correct, and check if the widget only occurs once
            elif(attType == 'widget'):
                if hasWidget == False:
                    hasWidget = True
                    if self.type not in attribute.checkTypes():
                        errorstring = "Incompatible types of widget in default near line " + str(self.line) + \
                        "\nType " + str(self.type) + " not supported by this widget"
                        throwError(errorstring)

                else:
                    errorstring = "Double declaration of widget in default near line: " + str(self.line)
                    throwError(errorstring)
            else:
                throwError("Internal error, unknown attribute in default")

        # Lastly check if styles are not double declarated
        styleSet = list(set(styleTypes))
        leftover = listDif(styleSet, styleTypes)
        if leftover != []:
            errorstring = "Double declaration(s) of style attribute(s) " + str(leftover)\
            + " in default near line: " + str(self.line)
            throwError(errorstring)

        return self.type


    """
        An attribute can be a widget or a styling class
    """
    def addAttribute(self, attribute):
        self.attributes.append(attribute)

    def addVarDict(self, varDict):
        self.varDict = varDict

    def __repr__(self):
        return "DefaultStyle attributes: {}".format(self.attributes)