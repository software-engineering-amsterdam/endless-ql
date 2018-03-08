# Generated from grammar/QL.g4 by ANTLR 4.7.1
# encoding: utf-8
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys

def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\"")
        buf.write("Y\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b")
        buf.write("\t\b\4\t\t\t\3\2\3\2\3\2\3\2\3\2\3\3\3\3\7\3\32\n\3\f")
        buf.write("\3\16\3\35\13\3\3\3\3\3\7\3!\n\3\f\3\16\3$\13\3\7\3&\n")
        buf.write("\3\f\3\16\3)\13\3\3\3\3\3\3\4\3\4\5\4/\n\4\3\5\3\5\3\5")
        buf.write("\3\5\3\5\7\5\66\n\5\f\5\16\59\13\5\3\6\3\6\6\6=\n\6\r")
        buf.write("\6\16\6>\3\6\3\6\6\6C\n\6\r\6\16\6D\3\7\3\7\3\b\3\b\6")
        buf.write("\bK\n\b\r\b\16\bL\3\b\3\b\6\bQ\n\b\r\b\16\bR\3\b\3\b\3")
        buf.write("\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\3\3\2\6\t\2Y\2\22")
        buf.write("\3\2\2\2\4\27\3\2\2\2\6.\3\2\2\2\b\60\3\2\2\2\n:\3\2\2")
        buf.write("\2\fF\3\2\2\2\16H\3\2\2\2\20V\3\2\2\2\22\23\7\3\2\2\23")
        buf.write("\24\7\t\2\2\24\25\5\4\3\2\25\26\7\2\2\3\26\3\3\2\2\2\27")
        buf.write("\33\7\f\2\2\30\32\7!\2\2\31\30\3\2\2\2\32\35\3\2\2\2\33")
        buf.write("\31\3\2\2\2\33\34\3\2\2\2\34\'\3\2\2\2\35\33\3\2\2\2\36")
        buf.write("\"\5\6\4\2\37!\7!\2\2 \37\3\2\2\2!$\3\2\2\2\" \3\2\2\2")
        buf.write("\"#\3\2\2\2#&\3\2\2\2$\"\3\2\2\2%\36\3\2\2\2&)\3\2\2\2")
        buf.write("\'%\3\2\2\2\'(\3\2\2\2(*\3\2\2\2)\'\3\2\2\2*+\7\r\2\2")
        buf.write("+\5\3\2\2\2,/\5\b\5\2-/\5\16\b\2.,\3\2\2\2.-\3\2\2\2/")
        buf.write("\7\3\2\2\2\60\61\7\n\2\2\61\62\7\t\2\2\62\63\7\13\2\2")
        buf.write("\63\67\5\20\t\2\64\66\5\n\6\2\65\64\3\2\2\2\669\3\2\2")
        buf.write("\2\67\65\3\2\2\2\678\3\2\2\28\t\3\2\2\29\67\3\2\2\2:<")
        buf.write("\7\20\2\2;=\7\16\2\2<;\3\2\2\2=>\3\2\2\2><\3\2\2\2>?\3")
        buf.write("\2\2\2?@\3\2\2\2@B\5\f\7\2AC\7\17\2\2BA\3\2\2\2CD\3\2")
        buf.write("\2\2DB\3\2\2\2DE\3\2\2\2E\13\3\2\2\2FG\5\20\t\2G\r\3\2")
        buf.write("\2\2HJ\7\4\2\2IK\7\16\2\2JI\3\2\2\2KL\3\2\2\2LJ\3\2\2")
        buf.write("\2LM\3\2\2\2MN\3\2\2\2NP\5\f\7\2OQ\7\17\2\2PO\3\2\2\2")
        buf.write("QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2ST\3\2\2\2TU\5\4\3\2U\17")
        buf.write("\3\2\2\2VW\t\2\2\2W\21\3\2\2\2\13\33\"\'.\67>DLR")
        return buf.getvalue()


class QLParser ( Parser ):

    grammarFileName = "QL.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "'form'", "'if'", "'else'", "'boolean'", 
                     "'money'", "<INVALID>", "<INVALID>", "<INVALID>", "':'", 
                     "'{'", "'}'", "'('", "')'", "'='", "'!'", "'&&'", "'||'", 
                     "<INVALID>", "'*'", "'/'", "'+'", "'-'", "<INVALID>", 
                     "'>'", "'<'", "'<='", "'>='", "'=='", "'!='" ]

    symbolicNames = [ "<INVALID>", "FORM", "IF_TOKEN", "ELSE_TOKEN", "BOOLEAN", 
                      "MONEY", "INT", "ID", "STRING", "COL", "BRACKETL", 
                      "BRACKETR", "PARL", "PARR", "ASSIGN", "NOT", "AND", 
                      "OR", "MATH_OPERATOR", "MUL", "DIV", "ADD", "SUB", 
                      "BOOL_OPERATOR", "GT", "LT", "LTE", "GTE", "EQ", "NEQ", 
                      "SPACE", "NEWLINE", "OTHER" ]

    RULE_form = 0
    RULE_block = 1
    RULE_stmt = 2
    RULE_question = 3
    RULE_declaration = 4
    RULE_expression = 5
    RULE_if_conditional = 6
    RULE_type_declaration = 7

    ruleNames =  [ "form", "block", "stmt", "question", "declaration", "expression", 
                   "if_conditional", "type_declaration" ]

    EOF = Token.EOF
    FORM=1
    IF_TOKEN=2
    ELSE_TOKEN=3
    BOOLEAN=4
    MONEY=5
    INT=6
    ID=7
    STRING=8
    COL=9
    BRACKETL=10
    BRACKETR=11
    PARL=12
    PARR=13
    ASSIGN=14
    NOT=15
    AND=16
    OR=17
    MATH_OPERATOR=18
    MUL=19
    DIV=20
    ADD=21
    SUB=22
    BOOL_OPERATOR=23
    GT=24
    LT=25
    LTE=26
    GTE=27
    EQ=28
    NEQ=29
    SPACE=30
    NEWLINE=31
    OTHER=32

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
            self.state = 16
            self.match(QLParser.FORM)
            self.state = 17
            self.match(QLParser.ID)
            self.state = 18
            self.block()
            self.state = 19
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
            self.state = 21
            self.match(QLParser.BRACKETL)
            self.state = 25
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==QLParser.NEWLINE:
                self.state = 22
                self.match(QLParser.NEWLINE)
                self.state = 27
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 37
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==QLParser.IF_TOKEN or _la==QLParser.STRING:
                self.state = 28
                self.stmt()
                self.state = 32
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                while _la==QLParser.NEWLINE:
                    self.state = 29
                    self.match(QLParser.NEWLINE)
                    self.state = 34
                    self._errHandler.sync(self)
                    _la = self._input.LA(1)

                self.state = 39
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 40
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


        def if_conditional(self):
            return self.getTypedRuleContext(QLParser.If_conditionalContext,0)


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
            self.state = 44
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [QLParser.STRING]:
                self.state = 42
                self.question()
                pass
            elif token in [QLParser.IF_TOKEN]:
                self.state = 43
                self.if_conditional()
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

        def type_declaration(self):
            return self.getTypedRuleContext(QLParser.Type_declarationContext,0)


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
            self.state = 46
            self.match(QLParser.STRING)
            self.state = 47
            self.match(QLParser.ID)
            self.state = 48
            self.match(QLParser.COL)
            self.state = 49
            self.type_declaration()
            self.state = 53
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==QLParser.ASSIGN:
                self.state = 50
                self.declaration()
                self.state = 55
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

        def ASSIGN(self):
            return self.getToken(QLParser.ASSIGN, 0)

        def expression(self):
            return self.getTypedRuleContext(QLParser.ExpressionContext,0)


        def PARL(self, i:int=None):
            if i is None:
                return self.getTokens(QLParser.PARL)
            else:
                return self.getToken(QLParser.PARL, i)

        def PARR(self, i:int=None):
            if i is None:
                return self.getTokens(QLParser.PARR)
            else:
                return self.getToken(QLParser.PARR, i)

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
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 56
            self.match(QLParser.ASSIGN)
            self.state = 58 
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while True:
                self.state = 57
                self.match(QLParser.PARL)
                self.state = 60 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if not (_la==QLParser.PARL):
                    break

            self.state = 62
            self.expression()
            self.state = 64 
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while True:
                self.state = 63
                self.match(QLParser.PARR)
                self.state = 66 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if not (_la==QLParser.PARR):
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

        def type_declaration(self):
            return self.getTypedRuleContext(QLParser.Type_declarationContext,0)


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
            self.enterOuterAlt(localctx, 1)
            self.state = 68
            self.type_declaration()
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

        def expression(self):
            return self.getTypedRuleContext(QLParser.ExpressionContext,0)


        def block(self):
            return self.getTypedRuleContext(QLParser.BlockContext,0)


        def PARL(self, i:int=None):
            if i is None:
                return self.getTokens(QLParser.PARL)
            else:
                return self.getToken(QLParser.PARL, i)

        def PARR(self, i:int=None):
            if i is None:
                return self.getTokens(QLParser.PARR)
            else:
                return self.getToken(QLParser.PARR, i)

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
        self.enterRule(localctx, 12, self.RULE_if_conditional)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 70
            self.match(QLParser.IF_TOKEN)
            self.state = 72 
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while True:
                self.state = 71
                self.match(QLParser.PARL)
                self.state = 74 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if not (_la==QLParser.PARL):
                    break

            self.state = 76
            self.expression()
            self.state = 78 
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while True:
                self.state = 77
                self.match(QLParser.PARR)
                self.state = 80 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if not (_la==QLParser.PARR):
                    break

            self.state = 82
            self.block()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class Type_declarationContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def BOOLEAN(self):
            return self.getToken(QLParser.BOOLEAN, 0)

        def MONEY(self):
            return self.getToken(QLParser.MONEY, 0)

        def INT(self):
            return self.getToken(QLParser.INT, 0)

        def ID(self):
            return self.getToken(QLParser.ID, 0)

        def getRuleIndex(self):
            return QLParser.RULE_type_declaration

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterType_declaration" ):
                listener.enterType_declaration(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitType_declaration" ):
                listener.exitType_declaration(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitType_declaration" ):
                return visitor.visitType_declaration(self)
            else:
                return visitor.visitChildren(self)




    def type_declaration(self):

        localctx = QLParser.Type_declarationContext(self, self._ctx, self.state)
        self.enterRule(localctx, 14, self.RULE_type_declaration)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 84
            _la = self._input.LA(1)
            if not((((_la) & ~0x3f) == 0 and ((1 << _la) & ((1 << QLParser.BOOLEAN) | (1 << QLParser.MONEY) | (1 << QLParser.INT) | (1 << QLParser.ID))) != 0)):
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





