# Generated from QL.g by ANTLR 4.7
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys


def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\16")
        buf.write("]\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7")
        buf.write("\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\3\2")
        buf.write("\3\2\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3")
        buf.write("\7\3\b\3\b\3\b\3\b\7\b/\n\b\f\b\16\b\62\13\b\3\b\3\b\3")
        buf.write("\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\6\n?\n\n\r\n\16\n@")
        buf.write("\3\13\3\13\7\13E\n\13\f\13\16\13H\13\13\3\13\3\13\3\f")
        buf.write("\3\f\7\fN\n\f\f\f\16\fQ\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3")
        buf.write("\r\3\r\3\r\5\r\\\n\r\2\2\16\3\3\5\4\7\5\t\6\13\7\r\b\17")
        buf.write("\t\21\n\23\13\25\f\27\r\31\16\3\2\5\5\2\13\f\17\17\"\"")
        buf.write("\4\2C\\c|\6\2\62;C\\aac|\2a\2\3\3\2\2\2\2\5\3\2\2\2\2")
        buf.write("\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3")
        buf.write("\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2")
        buf.write("\2\2\2\31\3\2\2\2\3\33\3\2\2\2\5\35\3\2\2\2\7\37\3\2\2")
        buf.write("\2\t\"\3\2\2\2\13$\3\2\2\2\r&\3\2\2\2\17*\3\2\2\2\218")
        buf.write("\3\2\2\2\23>\3\2\2\2\25B\3\2\2\2\27K\3\2\2\2\31[\3\2\2")
        buf.write("\2\33\34\7}\2\2\34\4\3\2\2\2\35\36\7\177\2\2\36\6\3\2")
        buf.write("\2\2\37 \7k\2\2 !\7h\2\2!\b\3\2\2\2\"#\7*\2\2#\n\3\2\2")
        buf.write("\2$%\7+\2\2%\f\3\2\2\2&\'\t\2\2\2\'(\3\2\2\2()\b\7\2\2")
        buf.write(")\16\3\2\2\2*+\7\61\2\2+,\7,\2\2,\60\3\2\2\2-/\13\2\2")
        buf.write("\2.-\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61")
        buf.write("\63\3\2\2\2\62\60\3\2\2\2\63\64\7,\2\2\64\65\7\61\2\2")
        buf.write("\65\66\3\2\2\2\66\67\b\b\2\2\67\20\3\2\2\289\7\61\2\2")
        buf.write("9:\7\61\2\2:;\3\2\2\2;<\b\t\2\2<\22\3\2\2\2=?\4\62;\2")
        buf.write(">=\3\2\2\2?@\3\2\2\2@>\3\2\2\2@A\3\2\2\2A\24\3\2\2\2B")
        buf.write("F\7$\2\2CE\13\2\2\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3")
        buf.write("\2\2\2GI\3\2\2\2HF\3\2\2\2IJ\7$\2\2J\26\3\2\2\2KO\t\3")
        buf.write("\2\2LN\t\4\2\2ML\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2")
        buf.write("P\30\3\2\2\2QO\3\2\2\2RS\7v\2\2ST\7t\2\2TU\7w\2\2U\\\7")
        buf.write("g\2\2VW\7h\2\2WX\7c\2\2XY\7n\2\2YZ\7u\2\2Z\\\7g\2\2[R")
        buf.write("\3\2\2\2[V\3\2\2\2\\\32\3\2\2\2\b\2\60@FO[\3\2\3\2")
        return buf.getvalue()


class QLLexer(Lexer):

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    T__0 = 1
    T__1 = 2
    T__2 = 3
    T__3 = 4
    T__4 = 5
    WHITESPACE = 6
    COMMENT = 7
    LINE_COMMENT = 8
    INT = 9
    STR = 10
    IDENTIFIER = 11
    BOOL = 12

    channelNames = [ u"DEFAULT_TOKEN_CHANNEL", u"HIDDEN" ]

    modeNames = [ "DEFAULT_MODE" ]

    literalNames = [ "<INVALID>",
            "'{'", "'}'", "'if'", "'('", "')'", "'//'" ]

    symbolicNames = [ "<INVALID>",
            "WHITESPACE", "COMMENT", "LINE_COMMENT", "INT", "STR", "IDENTIFIER", 
            "BOOL" ]

    ruleNames = [ "T__0", "T__1", "T__2", "T__3", "T__4", "WHITESPACE", 
                  "COMMENT", "LINE_COMMENT", "INT", "STR", "IDENTIFIER", 
                  "BOOL" ]

    grammarFileName = "QL.g"

    def __init__(self, input=None, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.7")
        self._interp = LexerATNSimulator(self, self.atn, self.decisionsToDFA, PredictionContextCache())
        self._actions = None
        self._predicates = None


