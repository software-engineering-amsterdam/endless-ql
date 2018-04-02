// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/SC-Java-LDG-TV/src/main/java/antlr/ql\Form.g4 by ANTLR 4.7
package antlr.ql;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormLexer extends Lexer {
    public static final int
            T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
            T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, NUMBERS = 16,
            CURLY_BRACKET_OPEN = 17, CURLY_BRACKET_CLOSE = 18, BRACKET_OPEN = 19, BRACKET_CLOSE = 20,
            OR = 21, AND = 22, QUESTION_LABEL = 23, QUESTION_VARIABLE_SEPERATOR = 24, QUESTION_VARIABLE_VALUE_SEPERATOR = 25,
            IF = 26, ELSE = 27, WHITESPACE = 28, NEWLINE = 29, CHARACTERS = 30;
    public static final String[] ruleNames = {
            "T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8",
            "T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "LOWERCASE", "UPPERCASE",
            "NUMBERS", "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", "BRACKET_OPEN",
            "BRACKET_CLOSE", "OR", "AND", "QUESTION_LABEL", "QUESTION_VARIABLE_SEPERATOR",
            "QUESTION_VARIABLE_VALUE_SEPERATOR", "IF", "ELSE", "WHITESPACE", "NEWLINE",
            "CHARACTERS"
    };
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u00c4\b\1\4\2\t" +
                    "\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
                    "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
                    "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
                    "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
                    "\t!\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3" +
                    "\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t" +
                    "\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3" +
                    "\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\6\23}\n\23\r\23\16\23~\3\24\3\24" +
                    "\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\6\32" +
                    "\u0090\n\32\r\32\16\32\u0091\3\32\3\32\3\32\6\32\u0097\n\32\r\32\16\32" +
                    "\u0098\6\32\u009b\n\32\r\32\16\32\u009c\3\32\3\32\3\33\3\33\3\34\3\34" +
                    "\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\6\37\u00ae\n\37\r\37\16" +
                    "\37\u00af\3\37\3\37\3 \5 \u00b5\n \3 \3 \6 \u00b9\n \r \16 \u00ba\3 \3" +
                    " \3!\3!\6!\u00c1\n!\r!\16!\u00c2\2\2\"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21" +
                    "\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\2#\2%\22\'\23)\24+\25-\26" +
                    "/\27\61\30\63\31\65\32\67\339\34;\35=\36?\37A \3\2\6\3\2c|\3\2C\\\5\2" +
                    "\"\"<<AA\5\2\13\f\17\17\"\"\2\u00cd\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2" +
                    "\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23" +
                    "\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2" +
                    "\2\2\2\37\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2" +
                    "\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2" +
                    "9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\3C\3\2\2\2\5H\3" +
                    "\2\2\2\7P\3\2\2\2\tV\3\2\2\2\13]\3\2\2\2\r_\3\2\2\2\17a\3\2\2\2\21c\3" +
                    "\2\2\2\23e\3\2\2\2\25g\3\2\2\2\27i\3\2\2\2\31l\3\2\2\2\33o\3\2\2\2\35" +
                    "q\3\2\2\2\37t\3\2\2\2!w\3\2\2\2#y\3\2\2\2%|\3\2\2\2\'\u0080\3\2\2\2)\u0082" +
                    "\3\2\2\2+\u0084\3\2\2\2-\u0086\3\2\2\2/\u0088\3\2\2\2\61\u008b\3\2\2\2" +
                    "\63\u008f\3\2\2\2\65\u00a0\3\2\2\2\67\u00a2\3\2\2\29\u00a4\3\2\2\2;\u00a7" +
                    "\3\2\2\2=\u00ad\3\2\2\2?\u00b8\3\2\2\2A\u00c0\3\2\2\2CD\7h\2\2DE\7q\2" +
                    "\2EF\7t\2\2FG\7o\2\2G\4\3\2\2\2HI\7d\2\2IJ\7q\2\2JK\7q\2\2KL\7n\2\2LM" +
                    "\7g\2\2MN\7c\2\2NO\7p\2\2O\6\3\2\2\2PQ\7o\2\2QR\7q\2\2RS\7p\2\2ST\7g\2" +
                    "\2TU\7{\2\2U\b\3\2\2\2VW\7u\2\2WX\7v\2\2XY\7t\2\2YZ\7k\2\2Z[\7p\2\2[\\" +
                    "\7i\2\2\\\n\3\2\2\2]^\7,\2\2^\f\3\2\2\2_`\7-\2\2`\16\3\2\2\2ab\7/\2\2" +
                    "b\20\3\2\2\2cd\7\61\2\2d\22\3\2\2\2ef\7>\2\2f\24\3\2\2\2gh\7@\2\2h\26" +
                    "\3\2\2\2ij\7?\2\2jk\7>\2\2k\30\3\2\2\2lm\7@\2\2mn\7?\2\2n\32\3\2\2\2o" +
                    "p\7#\2\2p\34\3\2\2\2qr\7#\2\2rs\7?\2\2s\36\3\2\2\2tu\7?\2\2uv\7?\2\2v" +
                    " \3\2\2\2wx\t\2\2\2x\"\3\2\2\2yz\t\3\2\2z$\3\2\2\2{}\4\62;\2|{\3\2\2\2" +
                    "}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177&\3\2\2\2\u0080\u0081\7}\2\2\u0081" +
                    "(\3\2\2\2\u0082\u0083\7\177\2\2\u0083*\3\2\2\2\u0084\u0085\7*\2\2\u0085" +
                    ",\3\2\2\2\u0086\u0087\7+\2\2\u0087.\3\2\2\2\u0088\u0089\7~\2\2\u0089\u008a" +
                    "\7~\2\2\u008a\60\3\2\2\2\u008b\u008c\7(\2\2\u008c\u008d\7(\2\2\u008d\62" +
                    "\3\2\2\2\u008e\u0090\7$\2\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091" +
                    "\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u009a\3\2\2\2\u0093\u0097\5A" +
                    "!\2\u0094\u0097\5%\23\2\u0095\u0097\t\4\2\2\u0096\u0093\3\2\2\2\u0096" +
                    "\u0094\3\2\2\2\u0096\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0096\3\2" +
                    "\2\2\u0098\u0099\3\2\2\2\u0099\u009b\3\2\2\2\u009a\u0096\3\2\2\2\u009b" +
                    "\u009c\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2" +
                    "\2\2\u009e\u009f\7$\2\2\u009f\64\3\2\2\2\u00a0\u00a1\7<\2\2\u00a1\66\3" +
                    "\2\2\2\u00a2\u00a3\7?\2\2\u00a38\3\2\2\2\u00a4\u00a5\7k\2\2\u00a5\u00a6" +
                    "\7h\2\2\u00a6:\3\2\2\2\u00a7\u00a8\7g\2\2\u00a8\u00a9\7n\2\2\u00a9\u00aa" +
                    "\7u\2\2\u00aa\u00ab\7g\2\2\u00ab<\3\2\2\2\u00ac\u00ae\t\5\2\2\u00ad\u00ac" +
                    "\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0" +
                    "\u00b1\3\2\2\2\u00b1\u00b2\b\37\2\2\u00b2>\3\2\2\2\u00b3\u00b5\7\17\2" +
                    "\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b9" +
                    "\7\f\2\2\u00b7\u00b9\7\17\2\2\u00b8\u00b4\3\2\2\2\u00b8\u00b7\3\2\2\2" +
                    "\u00b9\u00ba\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc" +
                    "\3\2\2\2\u00bc\u00bd\b \2\2\u00bd@\3\2\2\2\u00be\u00c1\5!\21\2\u00bf\u00c1" +
                    "\5#\22\2\u00c0\u00be\3\2\2\2\u00c0\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2" +
                    "\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3B\3\2\2\2\16\2~\u0091\u0096\u0098" +
                    "\u009c\u00af\u00b4\u00b8\u00ba\u00c0\u00c2\3\b\2\2";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = {
            null, "'form'", "'boolean'", "'money'", "'string'", "'*'", "'+'", "'-'",
            "'/'", "'<'", "'>'", "'=<'", "'>='", "'!'", "'!='", "'=='", null, "'{'",
            "'}'", "'('", "')'", "'||'", "'&&'", null, "':'", "'='", "'if'", "'else'"
    };
    private static final String[] _SYMBOLIC_NAMES = {
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, "NUMBERS", "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE",
            "BRACKET_OPEN", "BRACKET_CLOSE", "OR", "AND", "QUESTION_LABEL", "QUESTION_VARIABLE_SEPERATOR",
            "QUESTION_VARIABLE_VALUE_SEPERATOR", "IF", "ELSE", "WHITESPACE", "NEWLINE",
            "CHARACTERS"
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

    public FormLexer(CharStream input) {
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
        return "Form.g4";
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