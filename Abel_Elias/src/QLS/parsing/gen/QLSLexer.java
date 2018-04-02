// Generated from C:/dev/uva/endless-ql/Abel_Elias/src/QLS/parsing\QLS.g4 by ANTLR 4.7
package QLS.parsing.gen;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLSLexer extends Lexer {
    public static final int
            WS = 1, BOOLEANTYPE = 2, STRINGTYPE = 3, INTEGERTYPE = 4, MONEYTYPE = 5, DATETYPE = 6,
            DECIMALTYPE = 7, SLIDER = 8, SPINBOX = 9, CHECKBOX = 10, TEXT = 11, RADIO = 12, DROPDOWN = 13,
            WIDTH = 14, FONT = 15, FONTSIZE = 16, COLOR = 17, STYLESHEET = 18, PAGE = 19, SECTION = 20,
            DEFAULT = 21, WIDGET = 22, QUESTION = 23, CURLY_BRACE_L = 24, CURLY_BRACE_R = 25,
            BRACE_L = 26, BRACE_R = 27, BRACKET_L = 28, BRACKET_R = 29, COLON = 30, COMMA = 31,
            BOOL = 32, IDENTIFIER = 33, STR = 34, INT = 35, MON = 36, DEC = 37, NEWLINE = 38, CLR = 39,
            WHITESPACE = 40, MULTICOMMENT = 41, SINGLECOMMENT = 42;
    public static final String[] ruleNames = {
            "WS", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE", "MONEYTYPE", "DATETYPE",
            "DECIMALTYPE", "SLIDER", "SPINBOX", "CHECKBOX", "TEXT", "RADIO", "DROPDOWN",
            "WIDTH", "FONT", "FONTSIZE", "COLOR", "STYLESHEET", "PAGE", "SECTION",
            "DEFAULT", "WIDGET", "QUESTION", "CURLY_BRACE_L", "CURLY_BRACE_R", "BRACE_L",
            "BRACE_R", "BRACKET_L", "BRACKET_R", "COLON", "COMMA", "DIGIT", "LETTER",
            "BOOL", "IDENTIFIER", "STR", "INT", "MON", "DEC", "NEWLINE", "CLR", "WHITESPACE",
            "MULTICOMMENT", "SINGLECOMMENT"
    };
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2,\u018b\b\1\4\2\t" +
                    "\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
                    "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
                    "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
                    "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
                    "\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4" +
                    ",\t,\4-\t-\3\2\6\2]\n\2\r\2\16\2^\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
                    "\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3" +
                    "\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0087\n\6\3\7\3\7\3" +
                    "\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t" +
                    "\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3" +
                    "\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3" +
                    "\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3" +
                    "\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3" +
                    "\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3" +
                    "\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3" +
                    "\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3" +
                    "\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3" +
                    "\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3" +
                    "#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u0129\n#\3$\3$\3$\3$\7$\u012f\n$\f$\16$\u0132" +
                    "\13$\3%\3%\7%\u0136\n%\f%\16%\u0139\13%\3%\3%\3&\5&\u013e\n&\3&\6&\u0141" +
                    "\n&\r&\16&\u0142\3\'\6\'\u0146\n\'\r\'\16\'\u0147\3\'\3\'\3\'\3\'\3(\5" +
                    "(\u014f\n(\3(\6(\u0152\n(\r(\16(\u0153\3(\3(\6(\u0158\n(\r(\16(\u0159" +
                    "\3)\5)\u015d\n)\3)\3)\3*\3*\6*\u0163\n*\r*\16*\u0164\3+\6+\u0168\n+\r" +
                    "+\16+\u0169\3+\3+\3,\3,\3,\3,\7,\u0172\n,\f,\16,\u0175\13,\3,\3,\3,\3" +
                    ",\3,\3-\3-\3-\3-\7-\u0180\n-\f-\16-\u0183\13-\3-\5-\u0186\n-\3-\3-\3-" +
                    "\3-\4\u0137\u0173\2.\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27" +
                    "\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33" +
                    "\65\34\67\359\36;\37= ?!A\2C\2E\"G#I$K%M&O\'Q(S)U*W+Y,\3\2\7\4\2\13\13" +
                    "\"\"\4\2C\\c|\4\2\62;ch\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u019b\2\3\3" +
                    "\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2" +
                    "\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3" +
                    "\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2" +
                    "%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61" +
                    "\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2" +
                    "\2=\3\2\2\2\2?\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M" +
                    "\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2" +
                    "\2\2\3\\\3\2\2\2\5b\3\2\2\2\7j\3\2\2\2\tq\3\2\2\2\13\u0086\3\2\2\2\r\u0088" +
                    "\3\2\2\2\17\u008d\3\2\2\2\21\u0095\3\2\2\2\23\u009c\3\2\2\2\25\u00a4\3" +
                    "\2\2\2\27\u00ad\3\2\2\2\31\u00b2\3\2\2\2\33\u00b8\3\2\2\2\35\u00c1\3\2" +
                    "\2\2\37\u00c7\3\2\2\2!\u00cc\3\2\2\2#\u00d5\3\2\2\2%\u00db\3\2\2\2\'\u00e6" +
                    "\3\2\2\2)\u00eb\3\2\2\2+\u00f3\3\2\2\2-\u00fb\3\2\2\2/\u0102\3\2\2\2\61" +
                    "\u010b\3\2\2\2\63\u010d\3\2\2\2\65\u010f\3\2\2\2\67\u0111\3\2\2\29\u0113" +
                    "\3\2\2\2;\u0115\3\2\2\2=\u0117\3\2\2\2?\u0119\3\2\2\2A\u011b\3\2\2\2C" +
                    "\u011d\3\2\2\2E\u0128\3\2\2\2G\u012a\3\2\2\2I\u0133\3\2\2\2K\u013d\3\2" +
                    "\2\2M\u0145\3\2\2\2O\u014e\3\2\2\2Q\u015c\3\2\2\2S\u0160\3\2\2\2U\u0167" +
                    "\3\2\2\2W\u016d\3\2\2\2Y\u017b\3\2\2\2[]\t\2\2\2\\[\3\2\2\2]^\3\2\2\2" +
                    "^\\\3\2\2\2^_\3\2\2\2_`\3\2\2\2`a\b\2\2\2a\4\3\2\2\2bc\7d\2\2cd\7q\2\2" +
                    "de\7q\2\2ef\7n\2\2fg\7g\2\2gh\7c\2\2hi\7p\2\2i\6\3\2\2\2jk\7u\2\2kl\7" +
                    "v\2\2lm\7t\2\2mn\7k\2\2no\7p\2\2op\7i\2\2p\b\3\2\2\2qr\7k\2\2rs\7p\2\2" +
                    "st\7v\2\2tu\7g\2\2uv\7i\2\2vw\7g\2\2wx\7t\2\2x\n\3\2\2\2yz\7o\2\2z{\7" +
                    "q\2\2{|\7p\2\2|}\7g\2\2}\u0087\7{\2\2~\177\7e\2\2\177\u0080\7w\2\2\u0080" +
                    "\u0081\7t\2\2\u0081\u0082\7t\2\2\u0082\u0083\7g\2\2\u0083\u0084\7p\2\2" +
                    "\u0084\u0085\7e\2\2\u0085\u0087\7{\2\2\u0086y\3\2\2\2\u0086~\3\2\2\2\u0087" +
                    "\f\3\2\2\2\u0088\u0089\7f\2\2\u0089\u008a\7c\2\2\u008a\u008b\7v\2\2\u008b" +
                    "\u008c\7g\2\2\u008c\16\3\2\2\2\u008d\u008e\7f\2\2\u008e\u008f\7g\2\2\u008f" +
                    "\u0090\7e\2\2\u0090\u0091\7k\2\2\u0091\u0092\7o\2\2\u0092\u0093\7c\2\2" +
                    "\u0093\u0094\7n\2\2\u0094\20\3\2\2\2\u0095\u0096\7u\2\2\u0096\u0097\7" +
                    "n\2\2\u0097\u0098\7k\2\2\u0098\u0099\7f\2\2\u0099\u009a\7g\2\2\u009a\u009b" +
                    "\7t\2\2\u009b\22\3\2\2\2\u009c\u009d\7u\2\2\u009d\u009e\7r\2\2\u009e\u009f" +
                    "\7k\2\2\u009f\u00a0\7p\2\2\u00a0\u00a1\7d\2\2\u00a1\u00a2\7q\2\2\u00a2" +
                    "\u00a3\7z\2\2\u00a3\24\3\2\2\2\u00a4\u00a5\7e\2\2\u00a5\u00a6\7j\2\2\u00a6" +
                    "\u00a7\7g\2\2\u00a7\u00a8\7e\2\2\u00a8\u00a9\7m\2\2\u00a9\u00aa\7d\2\2" +
                    "\u00aa\u00ab\7q\2\2\u00ab\u00ac\7z\2\2\u00ac\26\3\2\2\2\u00ad\u00ae\7" +
                    "v\2\2\u00ae\u00af\7g\2\2\u00af\u00b0\7z\2\2\u00b0\u00b1\7v\2\2\u00b1\30" +
                    "\3\2\2\2\u00b2\u00b3\7t\2\2\u00b3\u00b4\7c\2\2\u00b4\u00b5\7f\2\2\u00b5" +
                    "\u00b6\7k\2\2\u00b6\u00b7\7q\2\2\u00b7\32\3\2\2\2\u00b8\u00b9\7f\2\2\u00b9" +
                    "\u00ba\7t\2\2\u00ba\u00bb\7q\2\2\u00bb\u00bc\7r\2\2\u00bc\u00bd\7f\2\2" +
                    "\u00bd\u00be\7q\2\2\u00be\u00bf\7y\2\2\u00bf\u00c0\7p\2\2\u00c0\34\3\2" +
                    "\2\2\u00c1\u00c2\7y\2\2\u00c2\u00c3\7k\2\2\u00c3\u00c4\7f\2\2\u00c4\u00c5" +
                    "\7v\2\2\u00c5\u00c6\7j\2\2\u00c6\36\3\2\2\2\u00c7\u00c8\7h\2\2\u00c8\u00c9" +
                    "\7q\2\2\u00c9\u00ca\7p\2\2\u00ca\u00cb\7v\2\2\u00cb \3\2\2\2\u00cc\u00cd" +
                    "\7h\2\2\u00cd\u00ce\7q\2\2\u00ce\u00cf\7p\2\2\u00cf\u00d0\7v\2\2\u00d0" +
                    "\u00d1\7u\2\2\u00d1\u00d2\7k\2\2\u00d2\u00d3\7|\2\2\u00d3\u00d4\7g\2\2" +
                    "\u00d4\"\3\2\2\2\u00d5\u00d6\7e\2\2\u00d6\u00d7\7q\2\2\u00d7\u00d8\7n" +
                    "\2\2\u00d8\u00d9\7q\2\2\u00d9\u00da\7t\2\2\u00da$\3\2\2\2\u00db\u00dc" +
                    "\7u\2\2\u00dc\u00dd\7v\2\2\u00dd\u00de\7{\2\2\u00de\u00df\7n\2\2\u00df" +
                    "\u00e0\7g\2\2\u00e0\u00e1\7u\2\2\u00e1\u00e2\7j\2\2\u00e2\u00e3\7g\2\2" +
                    "\u00e3\u00e4\7g\2\2\u00e4\u00e5\7v\2\2\u00e5&\3\2\2\2\u00e6\u00e7\7r\2" +
                    "\2\u00e7\u00e8\7c\2\2\u00e8\u00e9\7i\2\2\u00e9\u00ea\7g\2\2\u00ea(\3\2" +
                    "\2\2\u00eb\u00ec\7u\2\2\u00ec\u00ed\7g\2\2\u00ed\u00ee\7e\2\2\u00ee\u00ef" +
                    "\7v\2\2\u00ef\u00f0\7k\2\2\u00f0\u00f1\7q\2\2\u00f1\u00f2\7p\2\2\u00f2" +
                    "*\3\2\2\2\u00f3\u00f4\7f\2\2\u00f4\u00f5\7g\2\2\u00f5\u00f6\7h\2\2\u00f6" +
                    "\u00f7\7c\2\2\u00f7\u00f8\7w\2\2\u00f8\u00f9\7n\2\2\u00f9\u00fa\7v\2\2" +
                    "\u00fa,\3\2\2\2\u00fb\u00fc\7y\2\2\u00fc\u00fd\7k\2\2\u00fd\u00fe\7f\2" +
                    "\2\u00fe\u00ff\7i\2\2\u00ff\u0100\7g\2\2\u0100\u0101\7v\2\2\u0101.\3\2" +
                    "\2\2\u0102\u0103\7s\2\2\u0103\u0104\7w\2\2\u0104\u0105\7g\2\2\u0105\u0106" +
                    "\7u\2\2\u0106\u0107\7v\2\2\u0107\u0108\7k\2\2\u0108\u0109\7q\2\2\u0109" +
                    "\u010a\7p\2\2\u010a\60\3\2\2\2\u010b\u010c\7}\2\2\u010c\62\3\2\2\2\u010d" +
                    "\u010e\7\177\2\2\u010e\64\3\2\2\2\u010f\u0110\7*\2\2\u0110\66\3\2\2\2" +
                    "\u0111\u0112\7+\2\2\u01128\3\2\2\2\u0113\u0114\7]\2\2\u0114:\3\2\2\2\u0115" +
                    "\u0116\7_\2\2\u0116<\3\2\2\2\u0117\u0118\7<\2\2\u0118>\3\2\2\2\u0119\u011a" +
                    "\7.\2\2\u011a@\3\2\2\2\u011b\u011c\4\62;\2\u011cB\3\2\2\2\u011d\u011e" +
                    "\t\3\2\2\u011eD\3\2\2\2\u011f\u0120\7v\2\2\u0120\u0121\7t\2\2\u0121\u0122" +
                    "\7w\2\2\u0122\u0129\7g\2\2\u0123\u0124\7h\2\2\u0124\u0125\7c\2\2\u0125" +
                    "\u0126\7n\2\2\u0126\u0127\7u\2\2\u0127\u0129\7g\2\2\u0128\u011f\3\2\2" +
                    "\2\u0128\u0123\3\2\2\2\u0129F\3\2\2\2\u012a\u0130\5C\"\2\u012b\u012f\5" +
                    "C\"\2\u012c\u012f\5A!\2\u012d\u012f\7a\2\2\u012e\u012b\3\2\2\2\u012e\u012c" +
                    "\3\2\2\2\u012e\u012d\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0130" +
                    "\u0131\3\2\2\2\u0131H\3\2\2\2\u0132\u0130\3\2\2\2\u0133\u0137\7$\2\2\u0134" +
                    "\u0136\13\2\2\2\u0135\u0134\3\2\2\2\u0136\u0139\3\2\2\2\u0137\u0138\3" +
                    "\2\2\2\u0137\u0135\3\2\2\2\u0138\u013a\3\2\2\2\u0139\u0137\3\2\2\2\u013a" +
                    "\u013b\7$\2\2\u013bJ\3\2\2\2\u013c\u013e\7/\2\2\u013d\u013c\3\2\2\2\u013d" +
                    "\u013e\3\2\2\2\u013e\u0140\3\2\2\2\u013f\u0141\5A!\2\u0140\u013f\3\2\2" +
                    "\2\u0141\u0142\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143L" +
                    "\3\2\2\2\u0144\u0146\5A!\2\u0145\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147" +
                    "\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u014a\7\60" +
                    "\2\2\u014a\u014b\5A!\2\u014b\u014c\5A!\2\u014cN\3\2\2\2\u014d\u014f\7" +
                    "/\2\2\u014e\u014d\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0151\3\2\2\2\u0150" +
                    "\u0152\5A!\2\u0151\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0151\3\2\2" +
                    "\2\u0153\u0154\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0157\7\60\2\2\u0156" +
                    "\u0158\5A!\2\u0157\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u0157\3\2\2" +
                    "\2\u0159\u015a\3\2\2\2\u015aP\3\2\2\2\u015b\u015d\7\17\2\2\u015c\u015b" +
                    "\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u015f\7\f\2\2\u015f" +
                    "R\3\2\2\2\u0160\u0162\7%\2\2\u0161\u0163\t\4\2\2\u0162\u0161\3\2\2\2\u0163" +
                    "\u0164\3\2\2\2\u0164\u0162\3\2\2\2\u0164\u0165\3\2\2\2\u0165T\3\2\2\2" +
                    "\u0166\u0168\t\5\2\2\u0167\u0166\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u0167" +
                    "\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u016c\b+\3\2\u016c" +
                    "V\3\2\2\2\u016d\u016e\7\61\2\2\u016e\u016f\7,\2\2\u016f\u0173\3\2\2\2" +
                    "\u0170\u0172\13\2\2\2\u0171\u0170\3\2\2\2\u0172\u0175\3\2\2\2\u0173\u0174" +
                    "\3\2\2\2\u0173\u0171\3\2\2\2\u0174\u0176\3\2\2\2\u0175\u0173\3\2\2\2\u0176" +
                    "\u0177\7,\2\2\u0177\u0178\7\61\2\2\u0178\u0179\3\2\2\2\u0179\u017a\b," +
                    "\3\2\u017aX\3\2\2\2\u017b\u017c\7\61\2\2\u017c\u017d\7\61\2\2\u017d\u0181" +
                    "\3\2\2\2\u017e\u0180\n\6\2\2\u017f\u017e\3\2\2\2\u0180\u0183\3\2\2\2\u0181" +
                    "\u017f\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0185\3\2\2\2\u0183\u0181\3\2" +
                    "\2\2\u0184\u0186\7\17\2\2\u0185\u0184\3\2\2\2\u0185\u0186\3\2\2\2\u0186" +
                    "\u0187\3\2\2\2\u0187\u0188\7\f\2\2\u0188\u0189\3\2\2\2\u0189\u018a\b-" +
                    "\3\2\u018aZ\3\2\2\2\25\2^\u0086\u0128\u012e\u0130\u0137\u013d\u0142\u0147" +
                    "\u014e\u0153\u0159\u015c\u0164\u0169\u0173\u0181\u0185\4\2\3\2\b\2\2";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = {
            null, null, "'boolean'", "'string'", "'integer'", null, "'date'", "'decimal'",
            "'slider'", "'spinbox'", "'checkbox'", "'text'", "'radio'", "'dropdown'",
            "'width'", "'font'", "'fontsize'", "'color'", "'stylesheet'", "'page'",
            "'section'", "'default'", "'widget'", "'question'", "'{'", "'}'", "'('",
            "')'", "'['", "']'", "':'", "','"
    };
    private static final String[] _SYMBOLIC_NAMES = {
            null, "WS", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE", "MONEYTYPE", "DATETYPE",
            "DECIMALTYPE", "SLIDER", "SPINBOX", "CHECKBOX", "TEXT", "RADIO", "DROPDOWN",
            "WIDTH", "FONT", "FONTSIZE", "COLOR", "STYLESHEET", "PAGE", "SECTION",
            "DEFAULT", "WIDGET", "QUESTION", "CURLY_BRACE_L", "CURLY_BRACE_R", "BRACE_L",
            "BRACE_R", "BRACKET_L", "BRACKET_R", "COLON", "COMMA", "BOOL", "IDENTIFIER",
            "STR", "INT", "MON", "DEC", "NEWLINE", "CLR", "WHITESPACE", "MULTICOMMENT",
            "SINGLECOMMENT"
    };
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);
    public static String[] channelNames = {
            "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
    };
    public static String[] modeNames = {
            "DEFAULT_MODE"
    };

    static {
        RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION);
    }

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }

    public QLSLexer(CharStream input) {
        super(input);
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override
    public String getGrammarFileName() {
        return "QLS.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public String[] getChannelNames() {
        return channelNames;
    }

    @Override
    public String[] getModeNames() {
        return modeNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }
}