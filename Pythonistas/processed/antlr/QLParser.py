# Generated from antlr/QL.g4 by ANTLR 4.7.1
# encoding: utf-8
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys

def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 ")
        buf.write("r\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b")
        buf.write("\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3\2\3\2\3\2\3\2\3\2\3\3")
        buf.write("\3\3\7\3\36\n\3\f\3\16\3!\13\3\3\3\3\3\7\3%\n\3\f\3\16")
        buf.write("\3(\13\3\7\3*\n\3\f\3\16\3-\13\3\3\3\3\3\3\4\3\4\3\4\5")
        buf.write("\4\64\n\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6")
        buf.write("\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5")
        buf.write("\7N\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3")
        buf.write("\7\7\7\\\n\7\f\7\16\7_\13\7\3\b\3\b\3\b\3\b\5\be\n\b\3")
        buf.write("\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\2\3")
        buf.write("\f\f\2\4\6\b\n\f\16\20\22\24\2\3\3\2\3\7\2u\2\26\3\2\2")
        buf.write("\2\4\33\3\2\2\2\6\63\3\2\2\2\b\65\3\2\2\2\n:\3\2\2\2\f")
        buf.write("M\3\2\2\2\16d\3\2\2\2\20f\3\2\2\2\22l\3\2\2\2\24o\3\2")
        buf.write("\2\2\26\27\7\b\2\2\27\30\7\25\2\2\30\31\5\4\3\2\31\32")
        buf.write("\7\2\2\3\32\3\3\2\2\2\33\37\7\27\2\2\34\36\7\37\2\2\35")
        buf.write("\34\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 +\3")
        buf.write("\2\2\2!\37\3\2\2\2\"&\5\6\4\2#%\7\37\2\2$#\3\2\2\2%(\3")
        buf.write("\2\2\2&$\3\2\2\2&\'\3\2\2\2\'*\3\2\2\2(&\3\2\2\2)\"\3")
        buf.write("\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,.\3\2\2\2-+\3\2\2")
        buf.write("\2./\7\30\2\2/\5\3\2\2\2\60\64\5\b\5\2\61\64\5\n\6\2\62")
        buf.write("\64\5\16\b\2\63\60\3\2\2\2\63\61\3\2\2\2\63\62\3\2\2\2")
        buf.write("\64\7\3\2\2\2\65\66\7\26\2\2\66\67\7\25\2\2\678\7\f\2")
        buf.write("\289\5\24\13\29\t\3\2\2\2:;\7\26\2\2;<\7\25\2\2<=\7\f")
        buf.write("\2\2=>\5\24\13\2>?\7\33\2\2?@\7\31\2\2@A\5\f\7\2AB\7\32")
        buf.write("\2\2B\13\3\2\2\2CD\b\7\1\2DN\7\23\2\2EN\7\24\2\2FN\7\25")
        buf.write("\2\2GH\7\31\2\2HI\5\f\7\2IJ\7\32\2\2JN\3\2\2\2KL\7\34")
        buf.write("\2\2LN\5\f\7\7MC\3\2\2\2ME\3\2\2\2MF\3\2\2\2MG\3\2\2\2")
        buf.write("MK\3\2\2\2N]\3\2\2\2OP\f\6\2\2PQ\7\22\2\2Q\\\5\f\7\7R")
        buf.write("S\f\5\2\2ST\7\r\2\2T\\\5\f\7\6UV\f\4\2\2VW\7\35\2\2W\\")
        buf.write("\5\f\7\5XY\f\3\2\2YZ\7\36\2\2Z\\\5\f\7\4[O\3\2\2\2[R\3")
        buf.write("\2\2\2[U\3\2\2\2[X\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2")
        buf.write("\2\2^\r\3\2\2\2_]\3\2\2\2`e\5\20\t\2ab\5\20\t\2bc\5\22")
        buf.write("\n\2ce\3\2\2\2d`\3\2\2\2da\3\2\2\2e\17\3\2\2\2fg\7\t\2")
        buf.write("\2gh\7\31\2\2hi\5\f\7\2ij\7\32\2\2jk\5\4\3\2k\21\3\2\2")
        buf.write("\2lm\7\13\2\2mn\5\4\3\2n\23\3\2\2\2op\t\2\2\2p\25\3\2")
        buf.write("\2\2\n\37&+\63M[]d")
        return buf.getvalue()


class QLParser ( Parser ):

    grammarFileName = "QL.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "'integer'", "'boolean'", "'string'", 
                     "'date'", "'money'", "'form'", "'if'", "'elif'", "'else'", 
                     "':'", "<INVALID>", "'*'", "'/'", "'+'", "'-'", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "'{'", "'}'", "'('", "')'", "'='", "'!'", "'&&'", "'||'" ]

    symbolicNames = [ "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "FORM", "IF_TOKEN", "ELIF_TOKEN", 
                      "ELSE_TOKEN", "COL", "MATH_OPERATOR", "MUL", "DIV", 
                      "ADD", "SUB", "BOOL_OPERATOR", "BOOL", "INT", "ID", 
                      "STRING", "BRACKETL", "BRACKETR", "PARL", "PARR", 
                      "ASSIGN", "NOT", "AND", "OR", "NEWLINE", "WHITESPACE" ]

    RULE_form = 0
    RULE_block = 1
    RULE_statement = 2
    RULE_question = 3
    RULE_assignment = 4
    RULE_expression = 5
    RULE_conditional = 6
    RULE_if_conditional = 7
    RULE_else_conditional = 8
    RULE_types = 9

    ruleNames =  [ "form", "block", "statement", "question", "assignment", 
                   "expression", "conditional", "if_conditional", "else_conditional", 
                   "types" ]

    EOF = Token.EOF
    T__0=1
    T__1=2
    T__2=3
    T__3=4
    T__4=5
    FORM=6
    IF_TOKEN=7
    ELIF_TOKEN=8
    ELSE_TOKEN=9
    COL=10
    MATH_OPERATOR=11
    MUL=12
    DIV=13
    ADD=14
    SUB=15
    BOOL_OPERATOR=16
    BOOL=17
    INT=18
    ID=19
    STRING=20
    BRACKETL=21
    BRACKETR=22
    PARL=23
    PARR=24
    ASSIGN=25
    NOT=26
    AND=27
    OR=28
    NEWLINE=29
    WHITESPACE=30

    def __init__(self, input:TokenStream, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.7.1")
        self._interp = ParserATNSimulator(self, self.atn, self.decisionsToDFA, self.sharedContextCache)
        self._predicates = None



    class FormContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def FORM(self):
            return self.getToken(QLParser.FORM, 0)

        def ID(self):
            return self.getToken(QLParser.ID, 0)

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
            self.match(QLParser.FORM)
            self.state = 21
            self.match(QLParser.ID)
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

        def BRACKETL(self):
            return self.getToken(QLParser.BRACKETL, 0)

        def BRACKETR(self):
            return self.getToken(QLParser.BRACKETR, 0)

        def NEWLINE(self, i:int=None):
            if i is None:
                return self.getTokens(QLParser.NEWLINE)
            else:
                return self.getToken(QLParser.NEWLINE, i)

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
            self.match(QLParser.BRACKETL)
            self.state = 29
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==QLParser.NEWLINE:
                self.state = 26
                self.match(QLParser.NEWLINE)
                self.state = 31
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 41
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==QLParser.IF_TOKEN or _la==QLParser.STRING:
                self.state = 32
                self.statement()
                self.state = 36
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                while _la==QLParser.NEWLINE:
                    self.state = 33
                    self.match(QLParser.NEWLINE)
                    self.state = 38
                    self._errHandler.sync(self)
                    _la = self._input.LA(1)

                self.state = 43
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 44
            self.match(QLParser.BRACKETR)
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


        def assignment(self):
            return self.getTypedRuleContext(QLParser.AssignmentContext,0)


        def conditional(self):
            return self.getTypedRuleContext(QLParser.ConditionalContext,0)


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
            self.enterOuterAlt(localctx, 1)
            self.state = 49
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,3,self._ctx)
            if la_ == 1:
                self.state = 46
                self.question()
                pass

            elif la_ == 2:
                self.state = 47
                self.assignment()
                pass

            elif la_ == 3:
                self.state = 48
                self.conditional()
                pass


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

        def STRING(self):
            return self.getToken(QLParser.STRING, 0)

        def ID(self):
            return self.getToken(QLParser.ID, 0)

        def COL(self):
            return self.getToken(QLParser.COL, 0)

        def types(self):
            return self.getTypedRuleContext(QLParser.TypesContext,0)


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
        self.enterRule(localctx, 6, self.RULE_question)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 51
            self.match(QLParser.STRING)
            self.state = 52
            self.match(QLParser.ID)
            self.state = 53
            self.match(QLParser.COL)
            self.state = 54
            self.types()
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

        def STRING(self):
            return self.getToken(QLParser.STRING, 0)

        def ID(self):
            return self.getToken(QLParser.ID, 0)

        def COL(self):
            return self.getToken(QLParser.COL, 0)

        def types(self):
            return self.getTypedRuleContext(QLParser.TypesContext,0)


        def ASSIGN(self):
            return self.getToken(QLParser.ASSIGN, 0)

        def PARL(self):
            return self.getToken(QLParser.PARL, 0)

        def expression(self):
            return self.getTypedRuleContext(QLParser.ExpressionContext,0)


        def PARR(self):
            return self.getToken(QLParser.PARR, 0)

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
        self.enterRule(localctx, 8, self.RULE_assignment)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 56
            self.match(QLParser.STRING)
            self.state = 57
            self.match(QLParser.ID)
            self.state = 58
            self.match(QLParser.COL)
            self.state = 59
            self.types()
            self.state = 60
            self.match(QLParser.ASSIGN)
            self.state = 61
            self.match(QLParser.PARL)
            self.state = 62
            self.expression(0)
            self.state = 63
            self.match(QLParser.PARR)
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

        def INT(self):
            return self.getToken(QLParser.INT, 0)

        def ID(self):
            return self.getToken(QLParser.ID, 0)

        def PARL(self):
            return self.getToken(QLParser.PARL, 0)

        def expression(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(QLParser.ExpressionContext)
            else:
                return self.getTypedRuleContext(QLParser.ExpressionContext,i)


        def PARR(self):
            return self.getToken(QLParser.PARR, 0)

        def NOT(self):
            return self.getToken(QLParser.NOT, 0)

        def BOOL_OPERATOR(self):
            return self.getToken(QLParser.BOOL_OPERATOR, 0)

        def MATH_OPERATOR(self):
            return self.getToken(QLParser.MATH_OPERATOR, 0)

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
        _startState = 10
        self.enterRecursionRule(localctx, 10, self.RULE_expression, _p)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 75
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [QLParser.BOOL]:
                self.state = 66
                self.match(QLParser.BOOL)
                pass
            elif token in [QLParser.INT]:
                self.state = 67
                self.match(QLParser.INT)
                pass
            elif token in [QLParser.ID]:
                self.state = 68
                self.match(QLParser.ID)
                pass
            elif token in [QLParser.PARL]:
                self.state = 69
                self.match(QLParser.PARL)
                self.state = 70
                self.expression(0)
                self.state = 71
                self.match(QLParser.PARR)
                pass
            elif token in [QLParser.NOT]:
                self.state = 73
                self.match(QLParser.NOT)
                self.state = 74
                self.expression(5)
                pass
            else:
                raise NoViableAltException(self)

            self._ctx.stop = self._input.LT(-1)
            self.state = 91
            self._errHandler.sync(self)
            _alt = self._interp.adaptivePredict(self._input,6,self._ctx)
            while _alt!=2 and _alt!=ATN.INVALID_ALT_NUMBER:
                if _alt==1:
                    if self._parseListeners is not None:
                        self.triggerExitRuleEvent()
                    _prevctx = localctx
                    self.state = 89
                    self._errHandler.sync(self)
                    la_ = self._interp.adaptivePredict(self._input,5,self._ctx)
                    if la_ == 1:
                        localctx = QLParser.ExpressionContext(self, _parentctx, _parentState)
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_expression)
                        self.state = 77
                        if not self.precpred(self._ctx, 4):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 4)")
                        self.state = 78
                        self.match(QLParser.BOOL_OPERATOR)
                        self.state = 79
                        self.expression(5)
                        pass

                    elif la_ == 2:
                        localctx = QLParser.ExpressionContext(self, _parentctx, _parentState)
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_expression)
                        self.state = 80
                        if not self.precpred(self._ctx, 3):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 3)")
                        self.state = 81
                        self.match(QLParser.MATH_OPERATOR)
                        self.state = 82
                        self.expression(4)
                        pass

                    elif la_ == 3:
                        localctx = QLParser.ExpressionContext(self, _parentctx, _parentState)
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_expression)
                        self.state = 83
                        if not self.precpred(self._ctx, 2):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 2)")
                        self.state = 84
                        self.match(QLParser.AND)
                        self.state = 85
                        self.expression(3)
                        pass

                    elif la_ == 4:
                        localctx = QLParser.ExpressionContext(self, _parentctx, _parentState)
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_expression)
                        self.state = 86
                        if not self.precpred(self._ctx, 1):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 1)")
                        self.state = 87
                        self.match(QLParser.OR)
                        self.state = 88
                        self.expression(2)
                        pass

             
                self.state = 93
                self._errHandler.sync(self)
                _alt = self._interp.adaptivePredict(self._input,6,self._ctx)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.unrollRecursionContexts(_parentctx)
        return localctx

    class ConditionalContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def if_conditional(self):
            return self.getTypedRuleContext(QLParser.If_conditionalContext,0)


        def else_conditional(self):
            return self.getTypedRuleContext(QLParser.Else_conditionalContext,0)


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
        self.enterRule(localctx, 12, self.RULE_conditional)
        try:
            self.state = 98
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,7,self._ctx)
            if la_ == 1:
                self.enterOuterAlt(localctx, 1)
                self.state = 94
                self.if_conditional()
                pass

            elif la_ == 2:
                self.enterOuterAlt(localctx, 2)
                self.state = 95
                self.if_conditional()
                self.state = 96
                self.else_conditional()
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class If_conditionalContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IF_TOKEN(self):
            return self.getToken(QLParser.IF_TOKEN, 0)

        def PARL(self):
            return self.getToken(QLParser.PARL, 0)

        def expression(self):
            return self.getTypedRuleContext(QLParser.ExpressionContext,0)


        def PARR(self):
            return self.getToken(QLParser.PARR, 0)

        def block(self):
            return self.getTypedRuleContext(QLParser.BlockContext,0)


        def getRuleIndex(self):
            return QLParser.RULE_if_conditional

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterIf_conditional" ):
                listener.enterIf_conditional(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitIf_conditional" ):
                listener.exitIf_conditional(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitIf_conditional" ):
                return visitor.visitIf_conditional(self)
            else:
                return visitor.visitChildren(self)




    def if_conditional(self):

        localctx = QLParser.If_conditionalContext(self, self._ctx, self.state)
        self.enterRule(localctx, 14, self.RULE_if_conditional)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 100
            self.match(QLParser.IF_TOKEN)
            self.state = 101
            self.match(QLParser.PARL)
            self.state = 102
            self.expression(0)
            self.state = 103
            self.match(QLParser.PARR)
            self.state = 104
            self.block()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class Else_conditionalContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def ELSE_TOKEN(self):
            return self.getToken(QLParser.ELSE_TOKEN, 0)

        def block(self):
            return self.getTypedRuleContext(QLParser.BlockContext,0)


        def getRuleIndex(self):
            return QLParser.RULE_else_conditional

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterElse_conditional" ):
                listener.enterElse_conditional(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitElse_conditional" ):
                listener.exitElse_conditional(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitElse_conditional" ):
                return visitor.visitElse_conditional(self)
            else:
                return visitor.visitChildren(self)




    def else_conditional(self):

        localctx = QLParser.Else_conditionalContext(self, self._ctx, self.state)
        self.enterRule(localctx, 16, self.RULE_else_conditional)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 106
            self.match(QLParser.ELSE_TOKEN)
            self.state = 107
            self.block()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class TypesContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return QLParser.RULE_types

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterTypes" ):
                listener.enterTypes(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitTypes" ):
                listener.exitTypes(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitTypes" ):
                return visitor.visitTypes(self)
            else:
                return visitor.visitChildren(self)




    def types(self):

        localctx = QLParser.TypesContext(self, self._ctx, self.state)
        self.enterRule(localctx, 18, self.RULE_types)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 109
            _la = self._input.LA(1)
            if not((((_la) & ~0x3f) == 0 and ((1 << _la) & ((1 << QLParser.T__0) | (1 << QLParser.T__1) | (1 << QLParser.T__2) | (1 << QLParser.T__3) | (1 << QLParser.T__4))) != 0)):
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
        self._predicates[5] = self.expression_sempred
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
         




