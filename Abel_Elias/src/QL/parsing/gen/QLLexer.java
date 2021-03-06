// Generated from C:/dev/uva/endless-ql/Abel_Elias/src/QL/parsing\QL.g4 by ANTLR 4.7
package QL.parsing.gen;

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
public class QLLexer extends Lexer {
    public static final int
            WS = 1, COMMENT = 2, LINE_COMMENT = 3, BOOLEANTYPE = 4, STRINGTYPE = 5, INTEGERTYPE = 6,
            MONEYTYPE = 7, DATETYPE = 8, DECIMALTYPE = 9, FORM = 10, IF = 11, ELSE = 12, COLON = 13,
            CURLY_BRACE_L = 14, CURLY_BRACE_R = 15, BRACE_L = 16, BRACE_R = 17, ADD = 18, SUB = 19,
            MUL = 20, DIV = 21, REM = 22, EQT = 23, GRT = 24, LST = 25, GRTE = 26, LSTE = 27, AND = 28,
            NEQT = 29, OR = 30, NOT = 31, BOOL = 32, IDENTIFIER = 33, STR = 34, INT = 35, MON = 36,
            DEC = 37, NEWLINE = 38;
    public static final String[] ruleNames = {
            "WS", "COMMENT", "LINE_COMMENT", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE",
            "MONEYTYPE", "DATETYPE", "DECIMALTYPE", "FORM", "IF", "ELSE", "COLON",
            "CURLY_BRACE_L", "CURLY_BRACE_R", "BRACE_L", "BRACE_R", "ADD", "SUB",
            "MUL", "DIV", "REM", "EQT", "GRT", "LST", "GRTE", "LSTE", "AND", "NEQT",
            "OR", "NOT", "DIGIT", "LETTER", "BOOL", "IDENTIFIER", "STR", "INT", "MON",
            "DEC", "NEWLINE"
    };
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u011b\b\1\4\2\t" +
                    "\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
                    "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
                    "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
                    "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
                    "\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\6\2U\n\2\r" +
                    "\2\16\2V\3\2\3\2\3\3\3\3\3\3\3\3\7\3_\n\3\f\3\16\3b\13\3\3\3\3\3\3\3\3" +
                    "\3\3\3\3\4\3\4\3\4\3\4\7\4m\n\4\f\4\16\4p\13\4\3\4\3\4\3\5\3\5\3\5\3\5" +
                    "\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3" +
                    "\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n" +
                    "\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r" +
                    "\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24" +
                    "\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33" +
                    "\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37" +
                    "\3 \3 \3!\3!\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u00e4\n#\3$\3$\3$\3" +
                    "$\7$\u00ea\n$\f$\16$\u00ed\13$\3%\3%\7%\u00f1\n%\f%\16%\u00f4\13%\3%\3" +
                    "%\3&\5&\u00f9\n&\3&\6&\u00fc\n&\r&\16&\u00fd\3\'\6\'\u0101\n\'\r\'\16" +
                    "\'\u0102\3\'\3\'\3\'\3\'\3(\5(\u010a\n(\3(\6(\u010d\n(\r(\16(\u010e\3" +
                    "(\3(\6(\u0113\n(\r(\16(\u0114\3)\5)\u0118\n)\3)\3)\3`\2*\3\3\5\4\7\5\t" +
                    "\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23" +
                    "%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\2C\2E\"" +
                    "G#I$K%M&O\'Q(\3\2\6\4\2\13\13\"\"\4\2\f\f\17\17\4\2C\\c|\6\2\f\f\17\17" +
                    "$$^^\2\u0127\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2" +
                    "\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2" +
                    "\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2" +
                    "\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2" +
                    "\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3" +
                    "\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2" +
                    "\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\3T\3\2\2\2\5Z\3\2\2\2\7" +
                    "h\3\2\2\2\ts\3\2\2\2\13{\3\2\2\2\r\u0082\3\2\2\2\17\u008a\3\2\2\2\21\u0090" +
                    "\3\2\2\2\23\u0095\3\2\2\2\25\u009d\3\2\2\2\27\u00a2\3\2\2\2\31\u00a5\3" +
                    "\2\2\2\33\u00aa\3\2\2\2\35\u00ac\3\2\2\2\37\u00ae\3\2\2\2!\u00b0\3\2\2" +
                    "\2#\u00b2\3\2\2\2%\u00b4\3\2\2\2\'\u00b6\3\2\2\2)\u00b8\3\2\2\2+\u00ba" +
                    "\3\2\2\2-\u00bc\3\2\2\2/\u00be\3\2\2\2\61\u00c1\3\2\2\2\63\u00c3\3\2\2" +
                    "\2\65\u00c5\3\2\2\2\67\u00c8\3\2\2\29\u00cb\3\2\2\2;\u00ce\3\2\2\2=\u00d1" +
                    "\3\2\2\2?\u00d4\3\2\2\2A\u00d6\3\2\2\2C\u00d8\3\2\2\2E\u00e3\3\2\2\2G" +
                    "\u00e5\3\2\2\2I\u00ee\3\2\2\2K\u00f8\3\2\2\2M\u0100\3\2\2\2O\u0109\3\2" +
                    "\2\2Q\u0117\3\2\2\2SU\t\2\2\2TS\3\2\2\2UV\3\2\2\2VT\3\2\2\2VW\3\2\2\2" +
                    "WX\3\2\2\2XY\b\2\2\2Y\4\3\2\2\2Z[\7\61\2\2[\\\7,\2\2\\`\3\2\2\2]_\13\2" +
                    "\2\2^]\3\2\2\2_b\3\2\2\2`a\3\2\2\2`^\3\2\2\2ac\3\2\2\2b`\3\2\2\2cd\7," +
                    "\2\2de\7\61\2\2ef\3\2\2\2fg\b\3\2\2g\6\3\2\2\2hi\7\61\2\2ij\7\61\2\2j" +
                    "n\3\2\2\2km\n\3\2\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2\2" +
                    "pn\3\2\2\2qr\b\4\2\2r\b\3\2\2\2st\7d\2\2tu\7q\2\2uv\7q\2\2vw\7n\2\2wx" +
                    "\7g\2\2xy\7c\2\2yz\7p\2\2z\n\3\2\2\2{|\7u\2\2|}\7v\2\2}~\7t\2\2~\177\7" +
                    "k\2\2\177\u0080\7p\2\2\u0080\u0081\7i\2\2\u0081\f\3\2\2\2\u0082\u0083" +
                    "\7k\2\2\u0083\u0084\7p\2\2\u0084\u0085\7v\2\2\u0085\u0086\7g\2\2\u0086" +
                    "\u0087\7i\2\2\u0087\u0088\7g\2\2\u0088\u0089\7t\2\2\u0089\16\3\2\2\2\u008a" +
                    "\u008b\7o\2\2\u008b\u008c\7q\2\2\u008c\u008d\7p\2\2\u008d\u008e\7g\2\2" +
                    "\u008e\u008f\7{\2\2\u008f\20\3\2\2\2\u0090\u0091\7f\2\2\u0091\u0092\7" +
                    "c\2\2\u0092\u0093\7v\2\2\u0093\u0094\7g\2\2\u0094\22\3\2\2\2\u0095\u0096" +
                    "\7f\2\2\u0096\u0097\7g\2\2\u0097\u0098\7e\2\2\u0098\u0099\7k\2\2\u0099" +
                    "\u009a\7o\2\2\u009a\u009b\7c\2\2\u009b\u009c\7n\2\2\u009c\24\3\2\2\2\u009d" +
                    "\u009e\7h\2\2\u009e\u009f\7q\2\2\u009f\u00a0\7t\2\2\u00a0\u00a1\7o\2\2" +
                    "\u00a1\26\3\2\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4\7h\2\2\u00a4\30\3\2\2" +
                    "\2\u00a5\u00a6\7g\2\2\u00a6\u00a7\7n\2\2\u00a7\u00a8\7u\2\2\u00a8\u00a9" +
                    "\7g\2\2\u00a9\32\3\2\2\2\u00aa\u00ab\7<\2\2\u00ab\34\3\2\2\2\u00ac\u00ad" +
                    "\7}\2\2\u00ad\36\3\2\2\2\u00ae\u00af\7\177\2\2\u00af \3\2\2\2\u00b0\u00b1" +
                    "\7*\2\2\u00b1\"\3\2\2\2\u00b2\u00b3\7+\2\2\u00b3$\3\2\2\2\u00b4\u00b5" +
                    "\7-\2\2\u00b5&\3\2\2\2\u00b6\u00b7\7/\2\2\u00b7(\3\2\2\2\u00b8\u00b9\7" +
                    ",\2\2\u00b9*\3\2\2\2\u00ba\u00bb\7\61\2\2\u00bb,\3\2\2\2\u00bc\u00bd\7" +
                    "\'\2\2\u00bd.\3\2\2\2\u00be\u00bf\7?\2\2\u00bf\u00c0\7?\2\2\u00c0\60\3" +
                    "\2\2\2\u00c1\u00c2\7@\2\2\u00c2\62\3\2\2\2\u00c3\u00c4\7>\2\2\u00c4\64" +
                    "\3\2\2\2\u00c5\u00c6\7@\2\2\u00c6\u00c7\7?\2\2\u00c7\66\3\2\2\2\u00c8" +
                    "\u00c9\7>\2\2\u00c9\u00ca\7?\2\2\u00ca8\3\2\2\2\u00cb\u00cc\7(\2\2\u00cc" +
                    "\u00cd\7(\2\2\u00cd:\3\2\2\2\u00ce\u00cf\7#\2\2\u00cf\u00d0\7?\2\2\u00d0" +
                    "<\3\2\2\2\u00d1\u00d2\7~\2\2\u00d2\u00d3\7~\2\2\u00d3>\3\2\2\2\u00d4\u00d5" +
                    "\7#\2\2\u00d5@\3\2\2\2\u00d6\u00d7\4\62;\2\u00d7B\3\2\2\2\u00d8\u00d9" +
                    "\t\4\2\2\u00d9D\3\2\2\2\u00da\u00db\7v\2\2\u00db\u00dc\7t\2\2\u00dc\u00dd" +
                    "\7w\2\2\u00dd\u00e4\7g\2\2\u00de\u00df\7h\2\2\u00df\u00e0\7c\2\2\u00e0" +
                    "\u00e1\7n\2\2\u00e1\u00e2\7u\2\2\u00e2\u00e4\7g\2\2\u00e3\u00da\3\2\2" +
                    "\2\u00e3\u00de\3\2\2\2\u00e4F\3\2\2\2\u00e5\u00eb\5C\"\2\u00e6\u00ea\5" +
                    "C\"\2\u00e7\u00ea\5A!\2\u00e8\u00ea\7a\2\2\u00e9\u00e6\3\2\2\2\u00e9\u00e7" +
                    "\3\2\2\2\u00e9\u00e8\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb" +
                    "\u00ec\3\2\2\2\u00ecH\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00f2\7$\2\2\u00ef" +
                    "\u00f1\n\5\2\2\u00f0\u00ef\3\2\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2" +
                    "\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f5\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5" +
                    "\u00f6\7$\2\2\u00f6J\3\2\2\2\u00f7\u00f9\7/\2\2\u00f8\u00f7\3\2\2\2\u00f8" +
                    "\u00f9\3\2\2\2\u00f9\u00fb\3\2\2\2\u00fa\u00fc\5A!\2\u00fb\u00fa\3\2\2" +
                    "\2\u00fc\u00fd\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00feL" +
                    "\3\2\2\2\u00ff\u0101\5A!\2\u0100\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102" +
                    "\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0105\7\60" +
                    "\2\2\u0105\u0106\5A!\2\u0106\u0107\5A!\2\u0107N\3\2\2\2\u0108\u010a\7" +
                    "/\2\2\u0109\u0108\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010c\3\2\2\2\u010b" +
                    "\u010d\5A!\2\u010c\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010c\3\2\2" +
                    "\2\u010e\u010f\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0112\7\60\2\2\u0111" +
                    "\u0113\5A!\2\u0112\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0112\3\2\2" +
                    "\2\u0114\u0115\3\2\2\2\u0115P\3\2\2\2\u0116\u0118\7\17\2\2\u0117\u0116" +
                    "\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\7\f\2\2\u011a" +
                    "R\3\2\2\2\21\2V`n\u00e3\u00e9\u00eb\u00f2\u00f8\u00fd\u0102\u0109\u010e" +
                    "\u0114\u0117\3\2\3\2";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = {
            null, null, null, null, "'boolean'", "'string'", "'integer'", "'money'",
            "'date'", "'decimal'", "'form'", "'if'", "'else'", "':'", "'{'", "'}'",
            "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'%'", "'=='", "'>'", "'<'",
            "'>='", "'<='", "'&&'", "'!='", "'||'", "'!'"
    };
    private static final String[] _SYMBOLIC_NAMES = {
            null, "WS", "COMMENT", "LINE_COMMENT", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE",
            "MONEYTYPE", "DATETYPE", "DECIMALTYPE", "FORM", "IF", "ELSE", "COLON",
            "CURLY_BRACE_L", "CURLY_BRACE_R", "BRACE_L", "BRACE_R", "ADD", "SUB",
            "MUL", "DIV", "REM", "EQT", "GRT", "LST", "GRTE", "LSTE", "AND", "NEQT",
            "OR", "NOT", "BOOL", "IDENTIFIER", "STR", "INT", "MON", "DEC", "NEWLINE"
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

    public QLLexer(CharStream input) {
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
        return "QL.g4";
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