# Generated from QL.g4 by ANTLR 4.7
# encoding: utf-8
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys

def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\30")
        buf.write("\61\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4")
        buf.write("\b\t\b\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3")
        buf.write("\3\3\3\3\3\4\6\4 \n\4\r\4\16\4!\3\5\3\5\3\6\3\6\5\6(\n")
        buf.write("\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\2\2\t\2\4\6\b\n\f\16")
        buf.write("\2\4\3\2\t\n\4\2\f\16\25\27\2+\2\20\3\2\2\2\4\26\3\2\2")
        buf.write("\2\6\37\3\2\2\2\b#\3\2\2\2\n\'\3\2\2\2\f)\3\2\2\2\16.")
        buf.write("\3\2\2\2\20\21\7\3\2\2\21\22\7\30\2\2\22\23\7\4\2\2\23")
        buf.write("\24\5\6\4\2\24\25\7\5\2\2\25\3\3\2\2\2\26\27\7\6\2\2\27")
        buf.write("\30\7\7\2\2\30\31\5\b\5\2\31\32\7\b\2\2\32\33\7\4\2\2")
        buf.write("\33\34\5\6\4\2\34\35\7\5\2\2\35\5\3\2\2\2\36 \5\n\6\2")
        buf.write("\37\36\3\2\2\2 !\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\7\3")
        buf.write("\2\2\2#$\t\2\2\2$\t\3\2\2\2%(\5\f\7\2&(\5\4\3\2\'%\3\2")
        buf.write("\2\2\'&\3\2\2\2(\13\3\2\2\2)*\7\30\2\2*+\7\13\2\2+,\7")
        buf.write("\23\2\2,-\5\16\b\2-\r\3\2\2\2./\t\3\2\2/\17\3\2\2\2\4")
        buf.write("!\'")
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
    RULE_block = 2
    RULE_expression = 3
    RULE_statement = 4
    RULE_quest = 5
    RULE_type = 6

    ruleNames =  [ "form", "conditional_block", "block", "expression", "statement", 
                   "quest", "type" ]

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

        def block(self):
            return self.getTypedRuleContext(QLParser.BlockContext,0)


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
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 14
            self.match(QLParser.T__0)
            self.state = 15
            self.match(QLParser.IDENTIFIER)
            self.state = 16
            self.match(QLParser.T__1)
            self.state = 17
            self.block()
            self.state = 18
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


        def block(self):
            return self.getTypedRuleContext(QLParser.BlockContext,0)


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
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 20
            self.match(QLParser.T__3)
            self.state = 21
            self.match(QLParser.T__4)
            self.state = 22
            self.expression()
            self.state = 23
            self.match(QLParser.T__5)
            self.state = 24
            self.match(QLParser.T__1)
            self.state = 25
            self.block()
            self.state = 26
            self.match(QLParser.T__2)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class BlockContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def statement(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(QLParser.StatementContext)
            else:
                return self.getTypedRuleContext(QLParser.StatementContext,i)


        def getRuleIndex(self):
            return QLParser.RULE_block

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterBlock" ):
                listener.enterBlock(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitBlock" ):
                listener.exitBlock(self)




    def block(self):

        localctx = QLParser.BlockContext(self, self._ctx, self.state)
        self.enterRule(localctx, 4, self.RULE_block)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 29 
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while True:
                self.state = 28
                self.statement()
                self.state = 31 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if not (_la==QLParser.T__3 or _la==QLParser.IDENTIFIER):
                    break

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
        self.enterRule(localctx, 6, self.RULE_expression)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 33
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
        self.enterRule(localctx, 8, self.RULE_statement)
        try:
            self.state = 37
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [QLParser.IDENTIFIER]:
                self.enterOuterAlt(localctx, 1)
                self.state = 35
                self.quest()
                pass
            elif token in [QLParser.T__3]:
                self.enterOuterAlt(localctx, 2)
                self.state = 36
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

        def type(self):
            return self.getTypedRuleContext(QLParser.TypeContext,0)


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
        self.enterRule(localctx, 10, self.RULE_quest)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 39
            self.match(QLParser.IDENTIFIER)
            self.state = 40
            self.match(QLParser.T__8)
            self.state = 41
            self.match(QLParser.STR)
            self.state = 42
            self.type()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class TypeContext(ParserRuleContext):

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
            return QLParser.RULE_type

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterType" ):
                listener.enterType(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitType" ):
                listener.exitType(self)




    def type(self):

        localctx = QLParser.TypeContext(self, self._ctx, self.state)
        self.enterRule(localctx, 12, self.RULE_type)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 44
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





