# Generated from grammar/QLS.g4 by ANTLR 4.7.1
# encoding: utf-8
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys

def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\t")
        buf.write("\66\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3")
        buf.write("\2\3\2\3\2\7\2\22\n\2\f\2\16\2\25\13\2\3\2\3\2\3\2\3\2")
        buf.write("\3\3\3\3\3\3\7\3\36\n\3\f\3\16\3!\13\3\3\3\3\3\3\4\3\4")
        buf.write("\3\4\7\4(\n\4\f\4\16\4+\13\4\3\4\3\4\3\5\3\5\3\5\3\6\3")
        buf.write("\6\3\7\3\7\3\7\2\2\b\2\4\6\b\n\f\2\2\2\62\2\16\3\2\2\2")
        buf.write("\4\32\3\2\2\2\6$\3\2\2\2\b.\3\2\2\2\n\61\3\2\2\2\f\63")
        buf.write("\3\2\2\2\16\17\7\4\2\2\17\23\7\6\2\2\20\22\5\4\3\2\21")
        buf.write("\20\3\2\2\2\22\25\3\2\2\2\23\21\3\2\2\2\23\24\3\2\2\2")
        buf.write("\24\26\3\2\2\2\25\23\3\2\2\2\26\27\7\7\2\2\27\30\3\2\2")
        buf.write("\2\30\31\7\2\2\3\31\3\3\2\2\2\32\33\7\4\2\2\33\37\7\6")
        buf.write("\2\2\34\36\5\6\4\2\35\34\3\2\2\2\36!\3\2\2\2\37\35\3\2")
        buf.write("\2\2\37 \3\2\2\2 \"\3\2\2\2!\37\3\2\2\2\"#\7\7\2\2#\5")
        buf.write("\3\2\2\2$%\7\5\2\2%)\7\6\2\2&(\5\b\5\2\'&\3\2\2\2(+\3")
        buf.write("\2\2\2)\'\3\2\2\2)*\3\2\2\2*,\3\2\2\2+)\3\2\2\2,-\7\7")
        buf.write("\2\2-\7\3\2\2\2./\7\4\2\2/\60\5\n\6\2\60\t\3\2\2\2\61")
        buf.write("\62\5\f\7\2\62\13\3\2\2\2\63\64\7\3\2\2\64\r\3\2\2\2\5")
        buf.write("\23\37)")
        return buf.getvalue()


class QLSParser ( Parser ):

    grammarFileName = "QLS.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "'checkbox'", "<INVALID>", "<INVALID>", 
                     "'{'", "'}'" ]

    symbolicNames = [ "<INVALID>", "CHECKBOX", "ID", "STRING", "BRACKETL", 
                      "BRACKETR", "SPACE", "NEWLINE" ]

    RULE_stylesheet = 0
    RULE_page = 1
    RULE_section = 2
    RULE_question = 3
    RULE_widget = 4
    RULE_checkbox = 5

    ruleNames =  [ "stylesheet", "page", "section", "question", "widget", 
                   "checkbox" ]

    EOF = Token.EOF
    CHECKBOX=1
    ID=2
    STRING=3
    BRACKETL=4
    BRACKETR=5
    SPACE=6
    NEWLINE=7

    def __init__(self, input:TokenStream, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.7.1")
        self._interp = ParserATNSimulator(self, self.atn, self.decisionsToDFA, self.sharedContextCache)
        self._predicates = None



    class StylesheetContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def ID(self):
            return self.getToken(QLSParser.ID, 0)

        def EOF(self):
            return self.getToken(QLSParser.EOF, 0)

        def BRACKETL(self):
            return self.getToken(QLSParser.BRACKETL, 0)

        def BRACKETR(self):
            return self.getToken(QLSParser.BRACKETR, 0)

        def page(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(QLSParser.PageContext)
            else:
                return self.getTypedRuleContext(QLSParser.PageContext,i)


        def getRuleIndex(self):
            return QLSParser.RULE_stylesheet

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterStylesheet" ):
                listener.enterStylesheet(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitStylesheet" ):
                listener.exitStylesheet(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitStylesheet" ):
                return visitor.visitStylesheet(self)
            else:
                return visitor.visitChildren(self)




    def stylesheet(self):

        localctx = QLSParser.StylesheetContext(self, self._ctx, self.state)
        self.enterRule(localctx, 0, self.RULE_stylesheet)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 12
            self.match(QLSParser.ID)

            self.state = 13
            self.match(QLSParser.BRACKETL)
            self.state = 17
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==QLSParser.ID:
                self.state = 14
                self.page()
                self.state = 19
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 20
            self.match(QLSParser.BRACKETR)
            self.state = 22
            self.match(QLSParser.EOF)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class PageContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def ID(self):
            return self.getToken(QLSParser.ID, 0)

        def BRACKETL(self):
            return self.getToken(QLSParser.BRACKETL, 0)

        def BRACKETR(self):
            return self.getToken(QLSParser.BRACKETR, 0)

        def section(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(QLSParser.SectionContext)
            else:
                return self.getTypedRuleContext(QLSParser.SectionContext,i)


        def getRuleIndex(self):
            return QLSParser.RULE_page

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterPage" ):
                listener.enterPage(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitPage" ):
                listener.exitPage(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitPage" ):
                return visitor.visitPage(self)
            else:
                return visitor.visitChildren(self)




    def page(self):

        localctx = QLSParser.PageContext(self, self._ctx, self.state)
        self.enterRule(localctx, 2, self.RULE_page)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 24
            self.match(QLSParser.ID)

            self.state = 25
            self.match(QLSParser.BRACKETL)
            self.state = 29
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==QLSParser.STRING:
                self.state = 26
                self.section()
                self.state = 31
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 32
            self.match(QLSParser.BRACKETR)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class SectionContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def STRING(self):
            return self.getToken(QLSParser.STRING, 0)

        def BRACKETL(self):
            return self.getToken(QLSParser.BRACKETL, 0)

        def BRACKETR(self):
            return self.getToken(QLSParser.BRACKETR, 0)

        def question(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(QLSParser.QuestionContext)
            else:
                return self.getTypedRuleContext(QLSParser.QuestionContext,i)


        def getRuleIndex(self):
            return QLSParser.RULE_section

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterSection" ):
                listener.enterSection(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitSection" ):
                listener.exitSection(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitSection" ):
                return visitor.visitSection(self)
            else:
                return visitor.visitChildren(self)




    def section(self):

        localctx = QLSParser.SectionContext(self, self._ctx, self.state)
        self.enterRule(localctx, 4, self.RULE_section)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 34
            self.match(QLSParser.STRING)

            self.state = 35
            self.match(QLSParser.BRACKETL)
            self.state = 39
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==QLSParser.ID:
                self.state = 36
                self.question()
                self.state = 41
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 42
            self.match(QLSParser.BRACKETR)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class QuestionContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def ID(self):
            return self.getToken(QLSParser.ID, 0)

        def widget(self):
            return self.getTypedRuleContext(QLSParser.WidgetContext,0)


        def getRuleIndex(self):
            return QLSParser.RULE_question

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterQuestion" ):
                listener.enterQuestion(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitQuestion" ):
                listener.exitQuestion(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitQuestion" ):
                return visitor.visitQuestion(self)
            else:
                return visitor.visitChildren(self)




    def question(self):

        localctx = QLSParser.QuestionContext(self, self._ctx, self.state)
        self.enterRule(localctx, 6, self.RULE_question)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 44
            self.match(QLSParser.ID)
            self.state = 45
            self.widget()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class WidgetContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def checkbox(self):
            return self.getTypedRuleContext(QLSParser.CheckboxContext,0)


        def getRuleIndex(self):
            return QLSParser.RULE_widget

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterWidget" ):
                listener.enterWidget(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitWidget" ):
                listener.exitWidget(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitWidget" ):
                return visitor.visitWidget(self)
            else:
                return visitor.visitChildren(self)




    def widget(self):

        localctx = QLSParser.WidgetContext(self, self._ctx, self.state)
        self.enterRule(localctx, 8, self.RULE_widget)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 47
            self.checkbox()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class CheckboxContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def CHECKBOX(self):
            return self.getToken(QLSParser.CHECKBOX, 0)

        def getRuleIndex(self):
            return QLSParser.RULE_checkbox

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterCheckbox" ):
                listener.enterCheckbox(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitCheckbox" ):
                listener.exitCheckbox(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitCheckbox" ):
                return visitor.visitCheckbox(self)
            else:
                return visitor.visitChildren(self)




    def checkbox(self):

        localctx = QLSParser.CheckboxContext(self, self._ctx, self.state)
        self.enterRule(localctx, 10, self.RULE_checkbox)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 49
            self.match(QLSParser.CHECKBOX)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx





