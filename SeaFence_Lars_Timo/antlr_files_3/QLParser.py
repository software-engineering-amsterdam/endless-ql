# Generated from QL.g4 by ANTLR 4.7.1
# encoding: utf-8
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys

def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36")
        buf.write("b\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b")
        buf.write("\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3\2\3\2\3\2\3\2\3\2\3\3")
        buf.write("\3\3\7\3\36\n\3\f\3\16\3!\13\3\3\3\3\3\3\4\3\4\3\4\5\4")
        buf.write("(\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6")
        buf.write("\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3")
        buf.write("\b\3\b\3\b\3\b\3\b\3\b\5\bI\n\b\3\b\3\b\3\b\3\b\3\b\3")
        buf.write("\b\3\b\3\b\3\b\3\b\3\b\3\b\7\bW\n\b\f\b\16\bZ\13\b\3\t")
        buf.write("\3\t\3\n\3\n\3\13\3\13\3\13\2\3\16\f\2\4\6\b\n\f\16\20")
        buf.write("\22\24\2\3\3\2\13\f\2c\2\26\3\2\2\2\4\33\3\2\2\2\6\'\3")
        buf.write("\2\2\2\b)\3\2\2\2\n\62\3\2\2\2\f\67\3\2\2\2\16H\3\2\2")
        buf.write("\2\20[\3\2\2\2\22]\3\2\2\2\24_\3\2\2\2\26\27\7\3\2\2\27")
        buf.write("\30\5\20\t\2\30\31\5\4\3\2\31\32\7\2\2\3\32\3\3\2\2\2")
        buf.write("\33\37\7\4\2\2\34\36\5\6\4\2\35\34\3\2\2\2\36!\3\2\2\2")
        buf.write("\37\35\3\2\2\2\37 \3\2\2\2 \"\3\2\2\2!\37\3\2\2\2\"#\7")
        buf.write("\5\2\2#\5\3\2\2\2$(\5\n\6\2%(\5\f\7\2&(\5\b\5\2\'$\3\2")
        buf.write("\2\2\'%\3\2\2\2\'&\3\2\2\2(\7\3\2\2\2)*\7\35\2\2*+\5\22")
        buf.write("\n\2+,\7\6\2\2,-\5\24\13\2-.\7\7\2\2./\7\b\2\2/\60\5\16")
        buf.write("\b\2\60\61\7\t\2\2\61\t\3\2\2\2\62\63\7\35\2\2\63\64\5")
        buf.write("\22\n\2\64\65\7\6\2\2\65\66\5\24\13\2\66\13\3\2\2\2\67")
        buf.write("8\7\n\2\289\7\b\2\29:\5\16\b\2:;\7\t\2\2;<\5\4\3\2<\r")
        buf.write("\3\2\2\2=>\b\b\1\2>I\7\r\2\2?I\7\35\2\2@I\7\16\2\2AI\5")
        buf.write("\22\n\2BC\7\b\2\2CD\5\16\b\2DE\7\t\2\2EI\3\2\2\2FG\7\17")
        buf.write("\2\2GI\5\16\b\7H=\3\2\2\2H?\3\2\2\2H@\3\2\2\2HA\3\2\2")
        buf.write("\2HB\3\2\2\2HF\3\2\2\2IX\3\2\2\2JK\f\6\2\2KL\7\20\2\2")
        buf.write("LW\5\16\b\7MN\f\5\2\2NO\7\21\2\2OW\5\16\b\6PQ\f\4\2\2")
        buf.write("QR\7\26\2\2RW\5\16\b\5ST\f\3\2\2TU\7\27\2\2UW\5\16\b\4")
        buf.write("VJ\3\2\2\2VM\3\2\2\2VP\3\2\2\2VS\3\2\2\2WZ\3\2\2\2XV\3")
        buf.write("\2\2\2XY\3\2\2\2Y\17\3\2\2\2ZX\3\2\2\2[\\\7\36\2\2\\\21")
        buf.write("\3\2\2\2]^\7\36\2\2^\23\3\2\2\2_`\t\2\2\2`\25\3\2\2\2")
        buf.write("\7\37\'HVX")
        return buf.getvalue()


class QLParser ( Parser ):

    grammarFileName = "QL.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "'form'", "'{'", "'}'", "':'", "'='", 
                     "'('", "')'", "'if'", "'int'", "'boolean'", "<INVALID>", 
                     "<INVALID>", "'!'", "<INVALID>", "<INVALID>", "'+'", 
                     "'/'", "'-'", "'*'", "'&&'", "'OR'", "'true'", "'false'" ]

    symbolicNames = [ "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "BOOL", "INT", 
                      "NOT", "COMPARER", "OPERATOR", "ADD", "DIV", "SUB", 
                      "TIMES", "AND", "OR", "TRUE", "FALSE", "WS", "COMMENT", 
                      "NUMBER", "STR", "NAME" ]

    RULE_form = 0
    RULE_block = 1
    RULE_statement = 2
    RULE_assignment = 3
    RULE_question = 4
    RULE_conditional = 5
    RULE_expression = 6
    RULE_form_id = 7
    RULE_var = 8
    RULE_vartype = 9

    ruleNames =  [ "form", "block", "statement", "assignment", "question", 
                   "conditional", "expression", "form_id", "var", "vartype" ]

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
    BOOL=11
    INT=12
    NOT=13
    COMPARER=14
    OPERATOR=15
    ADD=16
    DIV=17
    SUB=18
    TIMES=19
    AND=20
    OR=21
    TRUE=22
    FALSE=23
    WS=24
    COMMENT=25
    NUMBER=26
    STR=27
    NAME=28

    def __init__(self, input:TokenStream, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.7.1")
        self._interp = ParserATNSimulator(self, self.atn, self.decisionsToDFA, self.sharedContextCache)
        self._predicates = None



    class FormContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def form_id(self):
            return self.getTypedRuleContext(QLParser.Form_idContext,0)


        def block(self):
            return self.getTypedRuleContext(QLParser.BlockContext,0)


        def EOF(self):
            return self.getToken(QLParser.EOF, 0)

        def getRuleIndex(self):
            return QLParser.RULE_form

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterForm" ):
                listener.enterForm(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitForm" ):
                listener.exitForm(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitForm" ):
                return visitor.visitForm(self)
            else:
                return visitor.visitChildren(self)




    def form(self):

        localctx = QLParser.FormContext(self, self._ctx, self.state)
        self.enterRule(localctx, 0, self.RULE_form)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 20
            self.match(QLParser.T__0)
            self.state = 21
            self.form_id()
            self.state = 22
            self.block()
            self.state = 23
            self.match(QLParser.EOF)
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

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitBlock" ):
                return visitor.visitBlock(self)
            else:
                return visitor.visitChildren(self)




    def block(self):

        localctx = QLParser.BlockContext(self, self._ctx, self.state)
        self.enterRule(localctx, 2, self.RULE_block)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 25
            self.match(QLParser.T__1)
            self.state = 29
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==QLParser.T__7 or _la==QLParser.STR:
                self.state = 26
                self.statement()
                self.state = 31
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 32
            self.match(QLParser.T__2)
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

        def question(self):
            return self.getTypedRuleContext(QLParser.QuestionContext,0)


        def conditional(self):
            return self.getTypedRuleContext(QLParser.ConditionalContext,0)


        def assignment(self):
            return self.getTypedRuleContext(QLParser.AssignmentContext,0)


        def getRuleIndex(self):
            return QLParser.RULE_statement

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterStatement" ):
                listener.enterStatement(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitStatement" ):
                listener.exitStatement(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitStatement" ):
                return visitor.visitStatement(self)
            else:
                return visitor.visitChildren(self)




    def statement(self):

        localctx = QLParser.StatementContext(self, self._ctx, self.state)
        self.enterRule(localctx, 4, self.RULE_statement)
        try:
            self.state = 37
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,1,self._ctx)
            if la_ == 1:
                self.enterOuterAlt(localctx, 1)
                self.state = 34
                self.question()
                pass

            elif la_ == 2:
                self.enterOuterAlt(localctx, 2)
                self.state = 35
                self.conditional()
                pass

            elif la_ == 3:
                self.enterOuterAlt(localctx, 3)
                self.state = 36
                self.assignment()
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class AssignmentContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def STR(self):
            return self.getToken(QLParser.STR, 0)

        def var(self):
            return self.getTypedRuleContext(QLParser.VarContext,0)


        def vartype(self):
            return self.getTypedRuleContext(QLParser.VartypeContext,0)


        def expression(self):
            return self.getTypedRuleContext(QLParser.ExpressionContext,0)


        def getRuleIndex(self):
            return QLParser.RULE_assignment

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterAssignment" ):
                listener.enterAssignment(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitAssignment" ):
                listener.exitAssignment(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitAssignment" ):
                return visitor.visitAssignment(self)
            else:
                return visitor.visitChildren(self)




    def assignment(self):

        localctx = QLParser.AssignmentContext(self, self._ctx, self.state)
        self.enterRule(localctx, 6, self.RULE_assignment)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 39
            self.match(QLParser.STR)
            self.state = 40
            self.var()
            self.state = 41
            self.match(QLParser.T__3)
            self.state = 42
            self.vartype()
            self.state = 43
            self.match(QLParser.T__4)
            self.state = 44
            self.match(QLParser.T__5)
            self.state = 45
            self.expression(0)
            self.state = 46
            self.match(QLParser.T__6)
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

        def STR(self):
            return self.getToken(QLParser.STR, 0)

        def var(self):
            return self.getTypedRuleContext(QLParser.VarContext,0)


        def vartype(self):
            return self.getTypedRuleContext(QLParser.VartypeContext,0)


        def getRuleIndex(self):
            return QLParser.RULE_question

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

        localctx = QLParser.QuestionContext(self, self._ctx, self.state)
        self.enterRule(localctx, 8, self.RULE_question)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 48
            self.match(QLParser.STR)
            self.state = 49
            self.var()
            self.state = 50
            self.match(QLParser.T__3)
            self.state = 51
            self.vartype()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class ConditionalContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def expression(self):
            return self.getTypedRuleContext(QLParser.ExpressionContext,0)


        def block(self):
            return self.getTypedRuleContext(QLParser.BlockContext,0)


        def getRuleIndex(self):
            return QLParser.RULE_conditional

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterConditional" ):
                listener.enterConditional(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitConditional" ):
                listener.exitConditional(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitConditional" ):
                return visitor.visitConditional(self)
            else:
                return visitor.visitChildren(self)




    def conditional(self):

        localctx = QLParser.ConditionalContext(self, self._ctx, self.state)
        self.enterRule(localctx, 10, self.RULE_conditional)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 53
            self.match(QLParser.T__7)
            self.state = 54
            self.match(QLParser.T__5)
            self.state = 55
            self.expression(0)
            self.state = 56
            self.match(QLParser.T__6)
            self.state = 57
            self.block()
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

        def BOOL(self):
            return self.getToken(QLParser.BOOL, 0)

        def STR(self):
            return self.getToken(QLParser.STR, 0)

        def INT(self):
            return self.getToken(QLParser.INT, 0)

        def var(self):
            return self.getTypedRuleContext(QLParser.VarContext,0)


        def expression(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(QLParser.ExpressionContext)
            else:
                return self.getTypedRuleContext(QLParser.ExpressionContext,i)


        def NOT(self):
            return self.getToken(QLParser.NOT, 0)

        def COMPARER(self):
            return self.getToken(QLParser.COMPARER, 0)

        def OPERATOR(self):
            return self.getToken(QLParser.OPERATOR, 0)

        def AND(self):
            return self.getToken(QLParser.AND, 0)

        def OR(self):
            return self.getToken(QLParser.OR, 0)

        def getRuleIndex(self):
            return QLParser.RULE_expression

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterExpression" ):
                listener.enterExpression(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitExpression" ):
                listener.exitExpression(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitExpression" ):
                return visitor.visitExpression(self)
            else:
                return visitor.visitChildren(self)



    def expression(self, _p:int=0):
        _parentctx = self._ctx
        _parentState = self.state
        localctx = QLParser.ExpressionContext(self, self._ctx, _parentState)
        _prevctx = localctx
        _startState = 12
        self.enterRecursionRule(localctx, 12, self.RULE_expression, _p)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 70
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [QLParser.BOOL]:
                self.state = 60
                self.match(QLParser.BOOL)
                pass
            elif token in [QLParser.STR]:
                self.state = 61
                self.match(QLParser.STR)
                pass
            elif token in [QLParser.INT]:
                self.state = 62
                self.match(QLParser.INT)
                pass
            elif token in [QLParser.NAME]:
                self.state = 63
                self.var()
                pass
            elif token in [QLParser.T__5]:
                self.state = 64
                self.match(QLParser.T__5)
                self.state = 65
                self.expression(0)
                self.state = 66
                self.match(QLParser.T__6)
                pass
            elif token in [QLParser.NOT]:
                self.state = 68
                self.match(QLParser.NOT)
                self.state = 69
                self.expression(5)
                pass
            else:
                raise NoViableAltException(self)

            self._ctx.stop = self._input.LT(-1)
            self.state = 86
            self._errHandler.sync(self)
            _alt = self._interp.adaptivePredict(self._input,4,self._ctx)
            while _alt!=2 and _alt!=ATN.INVALID_ALT_NUMBER:
                if _alt==1:
                    if self._parseListeners is not None:
                        self.triggerExitRuleEvent()
                    _prevctx = localctx
                    self.state = 84
                    self._errHandler.sync(self)
                    la_ = self._interp.adaptivePredict(self._input,3,self._ctx)
                    if la_ == 1:
                        localctx = QLParser.ExpressionContext(self, _parentctx, _parentState)
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_expression)
                        self.state = 72
                        if not self.precpred(self._ctx, 4):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 4)")
                        self.state = 73
                        self.match(QLParser.COMPARER)
                        self.state = 74
                        self.expression(5)
                        pass

                    elif la_ == 2:
                        localctx = QLParser.ExpressionContext(self, _parentctx, _parentState)
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_expression)
                        self.state = 75
                        if not self.precpred(self._ctx, 3):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 3)")
                        self.state = 76
                        self.match(QLParser.OPERATOR)
                        self.state = 77
                        self.expression(4)
                        pass

                    elif la_ == 3:
                        localctx = QLParser.ExpressionContext(self, _parentctx, _parentState)
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_expression)
                        self.state = 78
                        if not self.precpred(self._ctx, 2):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 2)")
                        self.state = 79
                        self.match(QLParser.AND)
                        self.state = 80
                        self.expression(3)
                        pass

                    elif la_ == 4:
                        localctx = QLParser.ExpressionContext(self, _parentctx, _parentState)
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_expression)
                        self.state = 81
                        if not self.precpred(self._ctx, 1):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 1)")
                        self.state = 82
                        self.match(QLParser.OR)
                        self.state = 83
                        self.expression(2)
                        pass

             
                self.state = 88
                self._errHandler.sync(self)
                _alt = self._interp.adaptivePredict(self._input,4,self._ctx)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.unrollRecursionContexts(_parentctx)
        return localctx

    class Form_idContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def NAME(self):
            return self.getToken(QLParser.NAME, 0)

        def getRuleIndex(self):
            return QLParser.RULE_form_id

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterForm_id" ):
                listener.enterForm_id(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitForm_id" ):
                listener.exitForm_id(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitForm_id" ):
                return visitor.visitForm_id(self)
            else:
                return visitor.visitChildren(self)




    def form_id(self):

        localctx = QLParser.Form_idContext(self, self._ctx, self.state)
        self.enterRule(localctx, 14, self.RULE_form_id)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 89
            self.match(QLParser.NAME)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class VarContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def NAME(self):
            return self.getToken(QLParser.NAME, 0)

        def getRuleIndex(self):
            return QLParser.RULE_var

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterVar" ):
                listener.enterVar(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitVar" ):
                listener.exitVar(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitVar" ):
                return visitor.visitVar(self)
            else:
                return visitor.visitChildren(self)




    def var(self):

        localctx = QLParser.VarContext(self, self._ctx, self.state)
        self.enterRule(localctx, 16, self.RULE_var)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 91
            self.match(QLParser.NAME)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class VartypeContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return QLParser.RULE_vartype

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterVartype" ):
                listener.enterVartype(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitVartype" ):
                listener.exitVartype(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitVartype" ):
                return visitor.visitVartype(self)
            else:
                return visitor.visitChildren(self)




    def vartype(self):

        localctx = QLParser.VartypeContext(self, self._ctx, self.state)
        self.enterRule(localctx, 18, self.RULE_vartype)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 93
            _la = self._input.LA(1)
            if not(_la==QLParser.T__8 or _la==QLParser.T__9):
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



    def sempred(self, localctx:RuleContext, ruleIndex:int, predIndex:int):
        if self._predicates == None:
            self._predicates = dict()
        self._predicates[6] = self.expression_sempred
        pred = self._predicates.get(ruleIndex, None)
        if pred is None:
            raise Exception("No predicate with index:" + str(ruleIndex))
        else:
            return pred(localctx, predIndex)

    def expression_sempred(self, localctx:ExpressionContext, predIndex:int):
            if predIndex == 0:
                return self.precpred(self._ctx, 4)
         

            if predIndex == 1:
                return self.precpred(self._ctx, 3)
         

            if predIndex == 2:
                return self.precpred(self._ctx, 2)
         

            if predIndex == 3:
                return self.precpred(self._ctx, 1)
         




