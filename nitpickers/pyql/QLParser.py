# Generated from QL.g4 by ANTLR 4.7
# encoding: utf-8
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys

def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\30")
        buf.write("\62\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3")
        buf.write("\2\3\2\3\2\3\2\6\2\23\n\2\r\2\16\2\24\3\2\3\2\3\3\3\3")
        buf.write("\3\3\3\3\3\3\3\3\6\3\37\n\3\r\3\16\3 \3\3\3\3\3\4\3\4")
        buf.write("\3\5\3\5\5\5)\n\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\2\2")
        buf.write("\b\2\4\6\b\n\f\2\4\3\2\t\n\4\2\f\16\25\27\2.\2\16\3\2")
        buf.write("\2\2\4\30\3\2\2\2\6$\3\2\2\2\b(\3\2\2\2\n*\3\2\2\2\f/")
        buf.write("\3\2\2\2\16\17\7\3\2\2\17\20\7\30\2\2\20\22\7\4\2\2\21")
        buf.write("\23\5\b\5\2\22\21\3\2\2\2\23\24\3\2\2\2\24\22\3\2\2\2")
        buf.write("\24\25\3\2\2\2\25\26\3\2\2\2\26\27\7\5\2\2\27\3\3\2\2")
        buf.write("\2\30\31\7\6\2\2\31\32\7\7\2\2\32\33\5\6\4\2\33\34\7\b")
        buf.write("\2\2\34\36\7\4\2\2\35\37\5\b\5\2\36\35\3\2\2\2\37 \3\2")
        buf.write("\2\2 \36\3\2\2\2 !\3\2\2\2!\"\3\2\2\2\"#\7\5\2\2#\5\3")
        buf.write("\2\2\2$%\t\2\2\2%\7\3\2\2\2&)\5\n\6\2\')\5\4\3\2(&\3\2")
        buf.write("\2\2(\'\3\2\2\2)\t\3\2\2\2*+\7\30\2\2+,\7\13\2\2,-\7\23")
        buf.write("\2\2-.\5\f\7\2.\13\3\2\2\2/\60\t\3\2\2\60\r\3\2\2\2\5")
        buf.write("\24 (")
        return buf.getvalue()


class QLParser ( Parser ):

    grammarFileName = "QL.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "'form'", "'{'", "'}'", "'if'", "'('", 
                     "')'", "'true'", "'false'", "':'", "'boolean'", "'text'", 
                     "'int'", "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "'date'" ]

    symbolicNames = [ "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "WHITESPACE", "COMMENT", "LINE_COMMENT", 
                      "INT", "STR", "BOOL", "MONEY", "DECIMAL", "DATE", 
                      "IDENTIFIER" ]

    RULE_form = 0
    RULE_conditional_block = 1
    RULE_expression = 2
    RULE_statement = 3
    RULE_quest = 4
    RULE_typ = 5

    ruleNames =  [ "form", "conditional_block", "expression", "statement", 
                   "quest", "typ" ]

    EOF = Token.EOF
    T__0=1
    T__1=2
    T__2=3
    T__3=4
    T__4=5
    T__5=6
    T__6=7
    T__7=8
    T__8=9
    T__9=10
    T__10=11
    T__11=12
    WHITESPACE=13
    COMMENT=14
    LINE_COMMENT=15
    INT=16
    STR=17
    BOOL=18
    MONEY=19
    DECIMAL=20
    DATE=21
    IDENTIFIER=22

    def __init__(self, input:TokenStream, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.7")
        self._interp = ParserATNSimulator(self, self.atn, self.decisionsToDFA, self.sharedContextCache)
        self._predicates = None



    class FormContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFIER(self):
            return self.getToken(QLParser.IDENTIFIER, 0)

        def statement(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(QLParser.StatementContext)
            else:
                return self.getTypedRuleContext(QLParser.StatementContext,i)


        def getRuleIndex(self):
            return QLParser.RULE_form

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterForm" ):
                listener.enterForm(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitForm" ):
                listener.exitForm(self)




    def form(self):

        localctx = QLParser.FormContext(self, self._ctx, self.state)
        self.enterRule(localctx, 0, self.RULE_form)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 12
            self.match(QLParser.T__0)
            self.state = 13
            self.match(QLParser.IDENTIFIER)
            self.state = 14
            self.match(QLParser.T__1)
            self.state = 16 
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while True:
                self.state = 15
                self.statement()
                self.state = 18 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if not (_la==QLParser.T__3 or _la==QLParser.IDENTIFIER):
                    break

            self.state = 20
            self.match(QLParser.T__2)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class Conditional_blockContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def expression(self):
            return self.getTypedRuleContext(QLParser.ExpressionContext,0)


        def statement(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(QLParser.StatementContext)
            else:
                return self.getTypedRuleContext(QLParser.StatementContext,i)


        def getRuleIndex(self):
            return QLParser.RULE_conditional_block

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterConditional_block" ):
                listener.enterConditional_block(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitConditional_block" ):
                listener.exitConditional_block(self)




    def conditional_block(self):

        localctx = QLParser.Conditional_blockContext(self, self._ctx, self.state)
        self.enterRule(localctx, 2, self.RULE_conditional_block)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 22
            self.match(QLParser.T__3)
            self.state = 23
            self.match(QLParser.T__4)
            self.state = 24
            self.expression()
            self.state = 25
            self.match(QLParser.T__5)
            self.state = 26
            self.match(QLParser.T__1)
            self.state = 28 
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while True:
                self.state = 27
                self.statement()
                self.state = 30 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if not (_la==QLParser.T__3 or _la==QLParser.IDENTIFIER):
                    break

            self.state = 32
            self.match(QLParser.T__2)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class ExpressionContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return QLParser.RULE_expression

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterExpression" ):
                listener.enterExpression(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitExpression" ):
                listener.exitExpression(self)




    def expression(self):

        localctx = QLParser.ExpressionContext(self, self._ctx, self.state)
        self.enterRule(localctx, 4, self.RULE_expression)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 34
            _la = self._input.LA(1)
            if not(_la==QLParser.T__6 or _la==QLParser.T__7):
                self._errHandler.recoverInline(self)
            else:
                self._errHandler.reportMatch(self)
                self.consume()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class StatementContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def quest(self):
            return self.getTypedRuleContext(QLParser.QuestContext,0)


        def conditional_block(self):
            return self.getTypedRuleContext(QLParser.Conditional_blockContext,0)


        def getRuleIndex(self):
            return QLParser.RULE_statement

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterStatement" ):
                listener.enterStatement(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitStatement" ):
                listener.exitStatement(self)




    def statement(self):

        localctx = QLParser.StatementContext(self, self._ctx, self.state)
        self.enterRule(localctx, 6, self.RULE_statement)
        try:
            self.state = 38
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [QLParser.IDENTIFIER]:
                self.enterOuterAlt(localctx, 1)
                self.state = 36
                self.quest()
                pass
            elif token in [QLParser.T__3]:
                self.enterOuterAlt(localctx, 2)
                self.state = 37
                self.conditional_block()
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class QuestContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFIER(self):
            return self.getToken(QLParser.IDENTIFIER, 0)

        def STR(self):
            return self.getToken(QLParser.STR, 0)

        def typ(self):
            return self.getTypedRuleContext(QLParser.TypContext,0)


        def getRuleIndex(self):
            return QLParser.RULE_quest

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterQuest" ):
                listener.enterQuest(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitQuest" ):
                listener.exitQuest(self)




    def quest(self):

        localctx = QLParser.QuestContext(self, self._ctx, self.state)
        self.enterRule(localctx, 8, self.RULE_quest)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 40
            self.match(QLParser.IDENTIFIER)
            self.state = 41
            self.match(QLParser.T__8)
            self.state = 42
            self.match(QLParser.STR)
            self.state = 43
            self.typ()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class TypContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def DATE(self):
            return self.getToken(QLParser.DATE, 0)

        def DECIMAL(self):
            return self.getToken(QLParser.DECIMAL, 0)

        def MONEY(self):
            return self.getToken(QLParser.MONEY, 0)

        def getRuleIndex(self):
            return QLParser.RULE_typ

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterTyp" ):
                listener.enterTyp(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitTyp" ):
                listener.exitTyp(self)




    def typ(self):

        localctx = QLParser.TypContext(self, self._ctx, self.state)
        self.enterRule(localctx, 10, self.RULE_typ)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 45
            _la = self._input.LA(1)
            if not((((_la) & ~0x3f) == 0 and ((1 << _la) & ((1 << QLParser.T__9) | (1 << QLParser.T__10) | (1 << QLParser.T__11) | (1 << QLParser.MONEY) | (1 << QLParser.DECIMAL) | (1 << QLParser.DATE))) != 0)):
                self._errHandler.recoverInline(self)
            else:
                self._errHandler.reportMatch(self)
                self.consume()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx





