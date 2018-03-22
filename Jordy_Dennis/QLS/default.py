"""
    A Default style can be created on two levels, on a page or section level, and on a question level.

    The question level defaults always need a widget type. The section and page level defaults do not need this.

    A default can have one single widget, and multiple styling attributes for this widget. 
    Default styling can be overwritten and is therefore scoped. 
"""
from .qlast_methods import *

class DefaultStyle:
    def __init__(self, defaultType, line):
        self.attributes = []
        self.line = line
        self.type = defaultType
        self.widgetType = None

    """
        Check if all the defaults are correct, a default is uncorrect when it is used multiple times.
    """
    def checkDefaultsQuestion(self, defaultDict):
        # If the entry is already present
        if self.type in defaultDict and self.widgetType in defaultDict[self.type]:
            errorstring = "Double declaration of default with types (" + str(self.type) + \
            ", " + str(self.widgetType) + ") near line " + str(self.line)
            throwError(errorstring)

        # If the type is present but the combination of (type, widget) is not, add it
        elif self.type in defaultDict:
            defaultDict[self.type][self.widgetType] = self
        else:
            defaultDict[self.type] = {}
            defaultDict[self.type][self.widgetType] = self
        return defaultDict

        

    """
        Check if the attribute types only occur once in the default. 
        We check the styling attributes by adding them to a list and see if they occur only once.

        We check the widgets by using a boolean
    """
    def checkTypes(self, isQuestion=False):
        styleTypes = []
        hasWidget = False
        widgetType = None
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
                    widgetType = attribute.getWidget()
                    if self.type not in attribute.checkTypes():
                        errorstring = "Incompatible types of widget in default near line " + str(self.line) + \
                        "\nType " + str(self.type) + " not supported by this widget"
                        throwError(errorstring)

                else:
                    errorstring = "Double declaration of widget in default near line: " + str(self.line)
                    throwError(errorstring)
            else:
                throwError("Internal error, unknown attribute in default")

        if hasWidget == False and isQuestion == True:
            errorstring = "Missing declaration of widget in default near line " + str(self.line)
            throwError(errorstring)

        # Lastly check if styles are not double declarated
        styleSet = list(set(styleTypes))
        leftover = listDif(styleSet, styleTypes)
        if leftover != []:
            errorstring = "Double declaration(s) of style attribute(s) " + str(leftover)\
            + " in default near line: " + str(self.line)
            throwError(errorstring)

        return self.type, widgetType

    """
        Check the attributes for the widget of the default (there can only be one), set your own widget if needed,
        and return the widget
    """
    def getWidget(self):
        for attribute in self.attributes:
            attType = attribute.getAttributeType()
            if attType == 'widget':
                return attribute
        throwError(("Could not find widget in default near line " + str(self.line)))


    """
        An attribute can be a widget or a styling class
    """
    def addAttribute(self, attribute):
        self.attributes.append(attribute)

    def setWidgetType(self, widgetType):
        self.widgetType = widgetType

    def getWidgetType(self):
        return self.widgetType

    def __repr__(self):
        return "DefaultStyle attributes: {}".format(self.attributes)