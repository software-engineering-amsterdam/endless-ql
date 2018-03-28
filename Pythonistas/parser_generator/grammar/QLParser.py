# Generated from grammar/QL.g4 by ANTLR 4.7.1
# encoding: utf-8
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys

def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#")
        buf.write("s\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b")
        buf.write("\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\3\2\3\2")
        buf.write("\3\2\3\2\3\2\3\3\3\3\7\3\"\n\3\f\3\16\3%\13\3\3\3\3\3")
        buf.write("\7\3)\n\3\f\3\16\3,\13\3\7\3.\n\3\f\3\16\3\61\13\3\3\3")
        buf.write("\3\3\3\4\3\4\5\4\67\n\4\3\5\3\5\3\5\3\5\3\5\7\5>\n\5\f")
        buf.write("\5\16\5A\13\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7")
        buf.write("\3\7\5\7N\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n")
        buf.write("\3\n\5\n[\n\n\3\13\3\13\5\13_\n\13\3\f\3\f\3\f\3\f\3\f")
        buf.write("\3\f\3\f\5\fh\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\rq\n\r")
        buf.write("\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30\2\3\4\2\6\7")
        buf.write("\n\n\2r\2\32\3\2\2\2\4\37\3\2\2\2\6\66\3\2\2\2\b8\3\2")
        buf.write("\2\2\nB\3\2\2\2\fM\3\2\2\2\16O\3\2\2\2\20U\3\2\2\2\22")
        buf.write("Z\3\2\2\2\24^\3\2\2\2\26g\3\2\2\2\30p\3\2\2\2\32\33\7")
        buf.write("\3\2\2\33\34\7\n\2\2\34\35\5\4\3\2\35\36\7\2\2\3\36\3")
        buf.write("\3\2\2\2\37#\7\r\2\2 \"\7\"\2\2! \3\2\2\2\"%\3\2\2\2#")
        buf.write("!\3\2\2\2#$\3\2\2\2$/\3\2\2\2%#\3\2\2\2&*\5\6\4\2\')\7")
        buf.write("\"\2\2(\'\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+.\3\2")
        buf.write("\2\2,*\3\2\2\2-&\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2")
        buf.write("\2\2\60\62\3\2\2\2\61/\3\2\2\2\62\63\7\16\2\2\63\5\3\2")
        buf.write("\2\2\64\67\5\b\5\2\65\67\5\16\b\2\66\64\3\2\2\2\66\65")
        buf.write("\3\2\2\2\67\7\3\2\2\289\7\13\2\29:\7\n\2\2:;\7\f\2\2;")
        buf.write("?\5\20\t\2<>\5\n\6\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3")
        buf.write("\2\2\2@\t\3\2\2\2A?\3\2\2\2BC\7\21\2\2CD\7\17\2\2DE\5")
        buf.write("\22\n\2EF\7\20\2\2F\13\3\2\2\2GN\7\n\2\2HI\7\17\2\2IJ")
        buf.write("\5\f\7\2JK\7\20\2\2KN\3\2\2\2LN\5\30\r\2MG\3\2\2\2MH\3")
        buf.write("\2\2\2ML\3\2\2\2N\r\3\2\2\2OP\7\4\2\2PQ\7\17\2\2QR\5\f")
        buf.write("\7\2RS\7\20\2\2ST\5\4\3\2T\17\3\2\2\2UV\t\2\2\2V\21\3")
        buf.write("\2\2\2W[\7\t\2\2X[\7\b\2\2Y[\5\24\13\2ZW\3\2\2\2ZX\3\2")
        buf.write("\2\2ZY\3\2\2\2[\23\3\2\2\2\\_\5\26\f\2]_\5\30\r\2^\\\3")
        buf.write("\2\2\2^]\3\2\2\2_\25\3\2\2\2`a\7\t\2\2ab\7\25\2\2bh\7")
        buf.write("\t\2\2cd\7\17\2\2de\5\26\f\2ef\7\20\2\2fh\3\2\2\2g`\3")
        buf.write("\2\2\2gc\3\2\2\2h\27\3\2\2\2ij\7\t\2\2jk\7\32\2\2kq\7")
        buf.write("\t\2\2lm\7\17\2\2mn\5\30\r\2no\7\20\2\2oq\3\2\2\2pi\3")
        buf.write("\2\2\2pl\3\2\2\2q\31\3\2\2\2\f#*/\66?MZ^gp")
        return buf.getvalue()


class QLParser ( Parser ):

    grammarFileName = "QL.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "'form'", "'if'", "'else'", "'boolean'", 
                     "'money'", "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "':'", "'{'", "'}'", "'('", "')'", "'='", "'!'", "'&&'", 
                     "'||'", "<INVALID>", "'*'", "'/'", "'+'", "'-'", "<INVALID>", 
                     "'>'", "'<'", "'<='", "'>='", "'=='", "'!='" ]

    symbolicNames = [ "<INVALID>", "FORM", "IF_", "ELSE_", "BOOLEAN", "MONEY", 
                      "BOOL", "INT", "ID", "STRING", "COL", "BRACKETL", 
                      "BRACKETR", "PARL", "PARR", "EQUAL", "NOT", "AND", 
                      "OR", "ARITHMETIC_OP", "MUL", "DIV", "ADD", "SUB", 
                      "BOOLEAN_OP", "GT", "LT", "LTE", "GTE", "EQ", "NEQ", 
                      "SPACE", "NEWLINE", "OTHER" ]

    RULE_form = 0
    RULE_block = 1
    RULE_stmt = 2
    RULE_question = 3
    RULE_declaration = 4
    RULE_expression = 5
    RULE_if_ = 6
    RULE_type = 7
    RULE_value = 8
    RULE_compute = 9
    RULE_arithmetic_ = 10
    RULE_boolean_ = 11

    ruleNames =  [ "form", "block", "stmt", "question", "declaration", "expression", 
                   "if_", "type", "value", "compute", "arithmetic_", "boolean_" ]

    EOF = Token.EOF
    FORM=1
    IF_=2
    ELSE_=3
    BOOLEAN=4
    MONEY=5
    BOOL=6
    INT=7
    ID=8
    STRING=9
    COL=10
    BRACKETL=11
    BRACKETR=12
    PARL=13
    PARR=14
    EQUAL=15
    NOT=16
    AND=17
    OR=18
    ARITHMETIC_OP=19
    MUL=20
    DIV=21
    ADD=22
    SUB=23
    BOOLEAN_OP=24
    GT=25
    LT=26
    LTE=27
    GTE=28
    EQ=29
    NEQ=30
    SPACE=31
    NEWLINE=32
    OTHER=33

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
            self.state = 24
            self.match(QLParser.FORM)
            self.state = 25
            self.match(QLParser.ID)
            self.state = 26
            self.block()
            self.state = 27
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

        def stmt(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(QLParser.StmtContext)
            else:
                return self.getTypedRuleContext(QLParser.StmtContext,i)


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
            self.state = 29
            self.match(QLParser.BRACKETL)
            self.state = 33
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==QLParser.NEWLINE:
                self.state = 30
                self.match(QLParser.NEWLINE)
                self.state = 35
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 45
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==QLParser.IF_ or _la==QLParser.STRING:
                self.state = 36
                self.stmt()
                self.state = 40
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                while _la==QLParser.NEWLINE:
                    self.state = 37
                    self.match(QLParser.NEWLINE)
                    self.state = 42
                    self._errHandler.sync(self)
                    _la = self._input.LA(1)

                self.state = 47
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 48
            self.match(QLParser.BRACKETR)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class StmtContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def question(self):
            return self.getTypedRuleContext(QLParser.QuestionContext,0)


        def if_(self):
            return self.getTypedRuleContext(QLParser.If_Context,0)


        def getRuleIndex(self):
            return QLParser.RULE_stmt

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterStmt" ):
                listener.enterStmt(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitStmt" ):
                listener.exitStmt(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitStmt" ):
                return visitor.visitStmt(self)
            else:
                return visitor.visitChildren(self)




    def stmt(self):

        localctx = QLParser.StmtContext(self, self._ctx, self.state)
        self.enterRule(localctx, 4, self.RULE_stmt)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 52
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [QLParser.STRING]:
                self.state = 50
                self.question()
                pass
            elif token in [QLParser.IF_]:
                self.state = 51
                self.if_()
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

        def type(self):
            return self.getTypedRuleContext(QLParser.TypeContext,0)


        def declaration(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(QLParser.DeclarationContext)
            else:
                return self.getTypedRuleContext(QLParser.DeclarationContext,i)


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
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 54
            self.match(QLParser.STRING)
            self.state = 55
            self.match(QLParser.ID)
            self.state = 56
            self.match(QLParser.COL)
            self.state = 57
            self.type()
            self.state = 61
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==QLParser.EQUAL:
                self.state = 58
                self.declaration()
                self.state = 63
                self._errHandler.sync(self)
                _la = self._input.LA(1)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class DeclarationContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def EQUAL(self):
            return self.getToken(QLParser.EQUAL, 0)

        def PARL(self):
            return self.getToken(QLParser.PARL, 0)

        def value(self):
            return self.getTypedRuleContext(QLParser.ValueContext,0)


        def PARR(self):
            return self.getToken(QLParser.PARR, 0)

        def getRuleIndex(self):
            return QLParser.RULE_declaration

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterDeclaration" ):
                listener.enterDeclaration(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitDeclaration" ):
                listener.exitDeclaration(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitDeclaration" ):
                return visitor.visitDeclaration(self)
            else:
                return visitor.visitChildren(self)




    def declaration(self):

        localctx = QLParser.DeclarationContext(self, self._ctx, self.state)
        self.enterRule(localctx, 8, self.RULE_declaration)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 64
            self.match(QLParser.EQUAL)
            self.state = 65
            self.match(QLParser.PARL)
            self.state = 66
            self.value()
            self.state = 67
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

        def ID(self):
            return self.getToken(QLParser.ID, 0)

        def PARL(self):
            return self.getToken(QLParser.PARL, 0)

        def expression(self):
            return self.getTypedRuleContext(QLParser.ExpressionContext,0)


        def PARR(self):
            return self.getToken(QLParser.PARR, 0)

        def boolean_(self):
            return self.getTypedRuleContext(QLParser.Boolean_Context,0)


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




    def expression(self):

        localctx = QLParser.ExpressionContext(self, self._ctx, self.state)
        self.enterRule(localctx, 10, self.RULE_expression)
        try:
            self.state = 75
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,5,self._ctx)
            if la_ == 1:
                self.enterOuterAlt(localctx, 1)
                self.state = 69
                self.match(QLParser.ID)
                pass

            elif la_ == 2:
                self.enterOuterAlt(localctx, 2)
                self.state = 70
                self.match(QLParser.PARL)
                self.state = 71
                self.expression()
                self.state = 72
                self.match(QLParser.PARR)
                pass

            elif la_ == 3:
                self.enterOuterAlt(localctx, 3)
                self.state = 74
                self.boolean_()
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class If_Context(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IF_(self):
            return self.getToken(QLParser.IF_, 0)

        def PARL(self):
            return self.getToken(QLParser.PARL, 0)

        def expression(self):
            return self.getTypedRuleContext(QLParser.ExpressionContext,0)


        def PARR(self):
            return self.getToken(QLParser.PARR, 0)

        def block(self):
            return self.getTypedRuleContext(QLParser.BlockContext,0)


        def getRuleIndex(self):
            return QLParser.RULE_if_

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterIf_" ):
                listener.enterIf_(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitIf_" ):
                listener.exitIf_(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitIf_" ):
                return visitor.visitIf_(self)
            else:
                return visitor.visitChildren(self)




    def if_(self):

        localctx = QLParser.If_Context(self, self._ctx, self.state)
        self.enterRule(localctx, 12, self.RULE_if_)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 77
            self.match(QLParser.IF_)
            self.state = 78
            self.match(QLParser.PARL)
            self.state = 79
            self.expression()
            self.state = 80
            self.match(QLParser.PARR)
            self.state = 81
            self.block()
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

        def BOOLEAN(self):
            return self.getToken(QLParser.BOOLEAN, 0)

        def MONEY(self):
            return self.getToken(QLParser.MONEY, 0)

        def ID(self):
            return self.getToken(QLParser.ID, 0)

        def getRuleIndex(self):
            return QLParser.RULE_type

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterType" ):
                listener.enterType(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitType" ):
                listener.exitType(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitType" ):
                return visitor.visitType(self)
            else:
                return visitor.visitChildren(self)




    def type(self):

        localctx = QLParser.TypeContext(self, self._ctx, self.state)
        self.enterRule(localctx, 14, self.RULE_type)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 83
            _la = self._input.LA(1)
            if not((((_la) & ~0x3f) == 0 and ((1 << _la) & ((1 << QLParser.BOOLEAN) | (1 << QLParser.MONEY) | (1 << QLParser.ID))) != 0)):
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

    class ValueContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def INT(self):
            return self.getToken(QLParser.INT, 0)

        def BOOL(self):
            return self.getToken(QLParser.BOOL, 0)

        def compute(self):
            return self.getTypedRuleContext(QLParser.ComputeContext,0)


        def getRuleIndex(self):
            return QLParser.RULE_value

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterValue" ):
                listener.enterValue(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitValue" ):
                listener.exitValue(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitValue" ):
                return visitor.visitValue(self)
            else:
                return visitor.visitChildren(self)




    def value(self):

        localctx = QLParser.ValueContext(self, self._ctx, self.state)
        self.enterRule(localctx, 16, self.RULE_value)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 88
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,6,self._ctx)
            if la_ == 1:
                self.state = 85
                self.match(QLParser.INT)
                pass

            elif la_ == 2:
                self.state = 86
                self.match(QLParser.BOOL)
                pass

            elif la_ == 3:
                self.state = 87
                self.compute()
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class ComputeContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def arithmetic_(self):
            return self.getTypedRuleContext(QLParser.Arithmetic_Context,0)


        def boolean_(self):
            return self.getTypedRuleContext(QLParser.Boolean_Context,0)


        def getRuleIndex(self):
            return QLParser.RULE_compute

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterCompute" ):
                listener.enterCompute(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitCompute" ):
                listener.exitCompute(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitCompute" ):
                return visitor.visitCompute(self)
            else:
                return visitor.visitChildren(self)




    def compute(self):

        localctx = QLParser.ComputeContext(self, self._ctx, self.state)
        self.enterRule(localctx, 18, self.RULE_compute)
        try:
            self.state = 92
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,7,self._ctx)
            if la_ == 1:
                self.enterOuterAlt(localctx, 1)
                self.state = 90
                self.arithmetic_()
                pass

            elif la_ == 2:
                self.enterOuterAlt(localctx, 2)
                self.state = 91
                self.boolean_()
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class Arithmetic_Context(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def INT(self, i:int=None):
            if i is None:
                return self.getTokens(QLParser.INT)
            else:
                return self.getToken(QLParser.INT, i)

        def ARITHMETIC_OP(self):
            return self.getToken(QLParser.ARITHMETIC_OP, 0)

        def PARL(self):
            return self.getToken(QLParser.PARL, 0)

        def arithmetic_(self):
            return self.getTypedRuleContext(QLParser.Arithmetic_Context,0)


        def PARR(self):
            return self.getToken(QLParser.PARR, 0)

        def getRuleIndex(self):
            return QLParser.RULE_arithmetic_

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterArithmetic_" ):
                listener.enterArithmetic_(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitArithmetic_" ):
                listener.exitArithmetic_(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitArithmetic_" ):
                return visitor.visitArithmetic_(self)
            else:
                return visitor.visitChildren(self)




    def arithmetic_(self):

        localctx = QLParser.Arithmetic_Context(self, self._ctx, self.state)
        self.enterRule(localctx, 20, self.RULE_arithmetic_)
        try:
            self.state = 101
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [QLParser.INT]:
                self.enterOuterAlt(localctx, 1)
                self.state = 94
                self.match(QLParser.INT)
                self.state = 95
                self.match(QLParser.ARITHMETIC_OP)
                self.state = 96
                self.match(QLParser.INT)
                pass
            elif token in [QLParser.PARL]:
                self.enterOuterAlt(localctx, 2)
                self.state = 97
                self.match(QLParser.PARL)
                self.state = 98
                self.arithmetic_()
                self.state = 99
                self.match(QLParser.PARR)
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

    class Boolean_Context(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def INT(self, i:int=None):
            if i is None:
                return self.getTokens(QLParser.INT)
            else:
                return self.getToken(QLParser.INT, i)

        def BOOLEAN_OP(self):
            return self.getToken(QLParser.BOOLEAN_OP, 0)

        def PARL(self):
            return self.getToken(QLParser.PARL, 0)

        def boolean_(self):
            return self.getTypedRuleContext(QLParser.Boolean_Context,0)


        def PARR(self):
            return self.getToken(QLParser.PARR, 0)

        def getRuleIndex(self):
            return QLParser.RULE_boolean_

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterBoolean_" ):
                listener.enterBoolean_(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitBoolean_" ):
                listener.exitBoolean_(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitBoolean_" ):
                return visitor.visitBoolean_(self)
            else:
                return visitor.visitChildren(self)




    def boolean_(self):

        localctx = QLParser.Boolean_Context(self, self._ctx, self.state)
        self.enterRule(localctx, 22, self.RULE_boolean_)
        try:
            self.state = 110
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [QLParser.INT]:
                self.enterOuterAlt(localctx, 1)
                self.state = 103
                self.match(QLParser.INT)
                self.state = 104
                self.match(QLParser.BOOLEAN_OP)
                self.state = 105
                self.match(QLParser.INT)
                pass
            elif token in [QLParser.PARL]:
                self.enterOuterAlt(localctx, 2)
                self.state = 106
                self.match(QLParser.PARL)
                self.state = 107
                self.boolean_()
                self.state = 108
                self.match(QLParser.PARR)
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





