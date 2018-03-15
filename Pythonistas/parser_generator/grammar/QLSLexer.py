# Generated from grammar/QLS.g4 by ANTLR 4.7.1
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys


def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\t")
        buf.write("<\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7")
        buf.write("\4\b\t\b\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\7")
        buf.write("\3\35\n\3\f\3\16\3 \13\3\3\4\3\4\7\4$\n\4\f\4\16\4\'\13")
        buf.write("\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\6\7\60\n\7\r\7\16\7\61")
        buf.write("\3\7\3\7\3\b\5\b\67\n\b\3\b\3\b\3\b\3\b\2\2\t\3\3\5\4")
        buf.write("\7\5\t\6\13\7\r\b\17\t\3\2\6\4\2C\\c|\6\2\62;C\\aac|\6")
        buf.write("\2\f\f\17\17$$^^\4\2\13\13\"\"\2?\2\3\3\2\2\2\2\5\3\2")
        buf.write("\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2")
        buf.write("\2\17\3\2\2\2\3\21\3\2\2\2\5\32\3\2\2\2\7!\3\2\2\2\t*")
        buf.write("\3\2\2\2\13,\3\2\2\2\r/\3\2\2\2\17\66\3\2\2\2\21\22\7")
        buf.write("e\2\2\22\23\7j\2\2\23\24\7g\2\2\24\25\7e\2\2\25\26\7m")
        buf.write("\2\2\26\27\7d\2\2\27\30\7q\2\2\30\31\7z\2\2\31\4\3\2\2")
        buf.write("\2\32\36\t\2\2\2\33\35\t\3\2\2\34\33\3\2\2\2\35 \3\2\2")
        buf.write("\2\36\34\3\2\2\2\36\37\3\2\2\2\37\6\3\2\2\2 \36\3\2\2")
        buf.write("\2!%\7$\2\2\"$\n\4\2\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2")
        buf.write("%&\3\2\2\2&(\3\2\2\2\'%\3\2\2\2()\7$\2\2)\b\3\2\2\2*+")
        buf.write("\7}\2\2+\n\3\2\2\2,-\7\177\2\2-\f\3\2\2\2.\60\t\5\2\2")
        buf.write("/.\3\2\2\2\60\61\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62")
        buf.write("\63\3\2\2\2\63\64\b\7\2\2\64\16\3\2\2\2\65\67\7\17\2\2")
        buf.write("\66\65\3\2\2\2\66\67\3\2\2\2\678\3\2\2\289\7\f\2\29:\3")
        buf.write("\2\2\2:;\b\b\2\2;\20\3\2\2\2\7\2\36%\61\66\3\b\2\2")
        return buf.getvalue()


class QLSLexer(Lexer):

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    CHECKBOX = 1
    ID = 2
    STRING = 3
    BRACKETL = 4
    BRACKETR = 5
    SPACE = 6
    NEWLINE = 7

    channelNames = [ u"DEFAULT_TOKEN_CHANNEL", u"HIDDEN" ]

    modeNames = [ "DEFAULT_MODE" ]

    literalNames = [ "<INVALID>",
            "'checkbox'", "'{'", "'}'" ]

    symbolicNames = [ "<INVALID>",
            "CHECKBOX", "ID", "STRING", "BRACKETL", "BRACKETR", "SPACE", 
            "NEWLINE" ]

    ruleNames = [ "CHECKBOX", "ID", "STRING", "BRACKETL", "BRACKETR", "SPACE", 
                  "NEWLINE" ]

    grammarFileName = "QLS.g4"

    def __init__(self, input=None, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.7.1")
        self._interp = LexerATNSimulator(self, self.atn, self.decisionsToDFA, PredictionContextCache())
        self._actions = None
        self._predicates = None


