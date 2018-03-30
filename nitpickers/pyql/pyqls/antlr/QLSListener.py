# Generated from QLS.g4 by ANTLR 4.7
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .QLSParser import QLSParser
else:
    from QLSParser import QLSParser

# This class defines a complete listener for a parse tree produced by QLSParser.
class QLSListener(ParseTreeListener):

    # Enter a parse tree produced by QLSParser#stylesheet.
    def enterStylesheet(self, ctx:QLSParser.StylesheetContext):
        pass

    # Exit a parse tree produced by QLSParser#stylesheet.
    def exitStylesheet(self, ctx:QLSParser.StylesheetContext):
        pass


    # Enter a parse tree produced by QLSParser#stylesheetBlock.
    def enterStylesheetBlock(self, ctx:QLSParser.StylesheetBlockContext):
        pass

    # Exit a parse tree produced by QLSParser#stylesheetBlock.
    def exitStylesheetBlock(self, ctx:QLSParser.StylesheetBlockContext):
        pass


    # Enter a parse tree produced by QLSParser#page.
    def enterPage(self, ctx:QLSParser.PageContext):
        pass

    # Exit a parse tree produced by QLSParser#page.
    def exitPage(self, ctx:QLSParser.PageContext):
        pass


    # Enter a parse tree produced by QLSParser#pageBlock.
    def enterPageBlock(self, ctx:QLSParser.PageBlockContext):
        pass

    # Exit a parse tree produced by QLSParser#pageBlock.
    def exitPageBlock(self, ctx:QLSParser.PageBlockContext):
        pass


    # Enter a parse tree produced by QLSParser#singleStatementSection.
    def enterSingleStatementSection(self, ctx:QLSParser.SingleStatementSectionContext):
        pass

    # Exit a parse tree produced by QLSParser#singleStatementSection.
    def exitSingleStatementSection(self, ctx:QLSParser.SingleStatementSectionContext):
        pass


    # Enter a parse tree produced by QLSParser#multiStatementSection.
    def enterMultiStatementSection(self, ctx:QLSParser.MultiStatementSectionContext):
        pass

    # Exit a parse tree produced by QLSParser#multiStatementSection.
    def exitMultiStatementSection(self, ctx:QLSParser.MultiStatementSectionContext):
        pass


    # Enter a parse tree produced by QLSParser#sectionBlock.
    def enterSectionBlock(self, ctx:QLSParser.SectionBlockContext):
        pass

    # Exit a parse tree produced by QLSParser#sectionBlock.
    def exitSectionBlock(self, ctx:QLSParser.SectionBlockContext):
        pass


    # Enter a parse tree produced by QLSParser#questionStatement.
    def enterQuestionStatement(self, ctx:QLSParser.QuestionStatementContext):
        pass

    # Exit a parse tree produced by QLSParser#questionStatement.
    def exitQuestionStatement(self, ctx:QLSParser.QuestionStatementContext):
        pass


    # Enter a parse tree produced by QLSParser#questionStyleStatement.
    def enterQuestionStyleStatement(self, ctx:QLSParser.QuestionStyleStatementContext):
        pass

    # Exit a parse tree produced by QLSParser#questionStyleStatement.
    def exitQuestionStyleStatement(self, ctx:QLSParser.QuestionStyleStatementContext):
        pass


    # Enter a parse tree produced by QLSParser#sectionStatement.
    def enterSectionStatement(self, ctx:QLSParser.SectionStatementContext):
        pass

    # Exit a parse tree produced by QLSParser#sectionStatement.
    def exitSectionStatement(self, ctx:QLSParser.SectionStatementContext):
        pass


    # Enter a parse tree produced by QLSParser#defaultStatement.
    def enterDefaultStatement(self, ctx:QLSParser.DefaultStatementContext):
        pass

    # Exit a parse tree produced by QLSParser#defaultStatement.
    def exitDefaultStatement(self, ctx:QLSParser.DefaultStatementContext):
        pass


    # Enter a parse tree produced by QLSParser#questionStyle.
    def enterQuestionStyle(self, ctx:QLSParser.QuestionStyleContext):
        pass

    # Exit a parse tree produced by QLSParser#questionStyle.
    def exitQuestionStyle(self, ctx:QLSParser.QuestionStyleContext):
        pass


    # Enter a parse tree produced by QLSParser#widget.
    def enterWidget(self, ctx:QLSParser.WidgetContext):
        pass

    # Exit a parse tree produced by QLSParser#widget.
    def exitWidget(self, ctx:QLSParser.WidgetContext):
        pass


    # Enter a parse tree produced by QLSParser#radioWidget.
    def enterRadioWidget(self, ctx:QLSParser.RadioWidgetContext):
        pass

    # Exit a parse tree produced by QLSParser#radioWidget.
    def exitRadioWidget(self, ctx:QLSParser.RadioWidgetContext):
        pass


    # Enter a parse tree produced by QLSParser#dropdownWidget.
    def enterDropdownWidget(self, ctx:QLSParser.DropdownWidgetContext):
        pass

    # Exit a parse tree produced by QLSParser#dropdownWidget.
    def exitDropdownWidget(self, ctx:QLSParser.DropdownWidgetContext):
        pass


    # Enter a parse tree produced by QLSParser#spinboxWidget.
    def enterSpinboxWidget(self, ctx:QLSParser.SpinboxWidgetContext):
        pass

    # Exit a parse tree produced by QLSParser#spinboxWidget.
    def exitSpinboxWidget(self, ctx:QLSParser.SpinboxWidgetContext):
        pass


    # Enter a parse tree produced by QLSParser#checkboxWidget.
    def enterCheckboxWidget(self, ctx:QLSParser.CheckboxWidgetContext):
        pass

    # Exit a parse tree produced by QLSParser#checkboxWidget.
    def exitCheckboxWidget(self, ctx:QLSParser.CheckboxWidgetContext):
        pass


    # Enter a parse tree produced by QLSParser#sliderWidget.
    def enterSliderWidget(self, ctx:QLSParser.SliderWidgetContext):
        pass

    # Exit a parse tree produced by QLSParser#sliderWidget.
    def exitSliderWidget(self, ctx:QLSParser.SliderWidgetContext):
        pass


    # Enter a parse tree produced by QLSParser#textWidget.
    def enterTextWidget(self, ctx:QLSParser.TextWidgetContext):
        pass

    # Exit a parse tree produced by QLSParser#textWidget.
    def exitTextWidget(self, ctx:QLSParser.TextWidgetContext):
        pass


    # Enter a parse tree produced by QLSParser#default.
    def enterDefault(self, ctx:QLSParser.DefaultContext):
        pass

    # Exit a parse tree produced by QLSParser#default.
    def exitDefault(self, ctx:QLSParser.DefaultContext):
        pass


    # Enter a parse tree produced by QLSParser#booleanType.
    def enterBooleanType(self, ctx:QLSParser.BooleanTypeContext):
        pass

    # Exit a parse tree produced by QLSParser#booleanType.
    def exitBooleanType(self, ctx:QLSParser.BooleanTypeContext):
        pass


    # Enter a parse tree produced by QLSParser#stringType.
    def enterStringType(self, ctx:QLSParser.StringTypeContext):
        pass

    # Exit a parse tree produced by QLSParser#stringType.
    def exitStringType(self, ctx:QLSParser.StringTypeContext):
        pass


    # Enter a parse tree produced by QLSParser#integerType.
    def enterIntegerType(self, ctx:QLSParser.IntegerTypeContext):
        pass

    # Exit a parse tree produced by QLSParser#integerType.
    def exitIntegerType(self, ctx:QLSParser.IntegerTypeContext):
        pass


    # Enter a parse tree produced by QLSParser#dateType.
    def enterDateType(self, ctx:QLSParser.DateTypeContext):
        pass

    # Exit a parse tree produced by QLSParser#dateType.
    def exitDateType(self, ctx:QLSParser.DateTypeContext):
        pass


    # Enter a parse tree produced by QLSParser#decimalType.
    def enterDecimalType(self, ctx:QLSParser.DecimalTypeContext):
        pass

    # Exit a parse tree produced by QLSParser#decimalType.
    def exitDecimalType(self, ctx:QLSParser.DecimalTypeContext):
        pass


    # Enter a parse tree produced by QLSParser#moneyType.
    def enterMoneyType(self, ctx:QLSParser.MoneyTypeContext):
        pass

    # Exit a parse tree produced by QLSParser#moneyType.
    def exitMoneyType(self, ctx:QLSParser.MoneyTypeContext):
        pass


    # Enter a parse tree produced by QLSParser#identifier.
    def enterIdentifier(self, ctx:QLSParser.IdentifierContext):
        pass

    # Exit a parse tree produced by QLSParser#identifier.
    def exitIdentifier(self, ctx:QLSParser.IdentifierContext):
        pass


