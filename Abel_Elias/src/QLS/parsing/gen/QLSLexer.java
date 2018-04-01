// Generated from /home/ajm/Desktop/endless-sunday/endless-ql/Abel_Elias/src/QLS/parsing/QLS.g4 by ANTLR 4.7
package QLS.parsing.gen;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLSLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, BOOLEANTYPE=2, STRINGTYPE=3, INTEGERTYPE=4, MONEYTYPE=5, DATETYPE=6, 
		DECIMALTYPE=7, SLIDER=8, SPINBOX=9, CHECKBOX=10, TEXT=11, RADIO=12, DROPDOWN=13, 
		WIDTH=14, FONT=15, FONTSIZE=16, COLOR=17, STYLESHEET=18, PAGE=19, SECTION=20, 
		DEFAULT=21, WIDGET=22, QUESTION=23, CURLY_BRACE_L=24, CURLY_BRACE_R=25, 
		BRACE_L=26, BRACE_R=27, COMMA=28, COLON=29, BOOL=30, IDENTIFIER=31, STR=32, 
		INT=33, MON=34, DEC=35, NEWLINE=36, CLR=37, WHITESPACE=38, MULTICOMMENT=39, 
		SINGLECOMMENT=40;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE", "MONEYTYPE", "DATETYPE", 
		"DECIMALTYPE", "SLIDER", "SPINBOX", "CHECKBOX", "TEXT", "RADIO", "DROPDOWN", 
		"WIDTH", "FONT", "FONTSIZE", "COLOR", "STYLESHEET", "PAGE", "SECTION", 
		"DEFAULT", "WIDGET", "QUESTION", "CURLY_BRACE_L", "CURLY_BRACE_R", "BRACE_L", 
		"BRACE_R", "COMMA", "COLON", "DIGIT", "LETTER", "BOOL", "IDENTIFIER", 
		"STR", "INT", "MON", "DEC", "NEWLINE", "CLR", "WHITESPACE", "MULTICOMMENT", 
		"SINGLECOMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'boolean'", "'string'", "'integer'", null, "'date'", "'decimal'", 
		"'slider'", "'spinbox'", "'checkbox'", "'text'", "'radio'", "'dropdown'", 
		"'width'", "'font'", "'fontsize'", "'color'", "'stylesheet'", "'page'", 
		"'section'", "'default'", "'widget'", "'question'", "'{'", "'}'", "'('", 
		"')'", "','", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE", "MONEYTYPE", "DATETYPE", 
		"DECIMALTYPE", "SLIDER", "SPINBOX", "CHECKBOX", "TEXT", "RADIO", "DROPDOWN", 
		"WIDTH", "FONT", "FONTSIZE", "COLOR", "STYLESHEET", "PAGE", "SECTION", 
		"DEFAULT", "WIDGET", "QUESTION", "CURLY_BRACE_L", "CURLY_BRACE_R", "BRACE_L", 
		"BRACE_R", "COMMA", "COLON", "BOOL", "IDENTIFIER", "STR", "INT", "MON", 
		"DEC", "NEWLINE", "CLR", "WHITESPACE", "MULTICOMMENT", "SINGLECOMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
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

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public QLSLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QLS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2*\u0183\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\6\2Y\n\2\r\2\16\2Z\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0083\n\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34"+
		"\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\5"+
		"!\u0121\n!\3\"\3\"\3\"\3\"\7\"\u0127\n\"\f\"\16\"\u012a\13\"\3#\3#\7#"+
		"\u012e\n#\f#\16#\u0131\13#\3#\3#\3$\5$\u0136\n$\3$\6$\u0139\n$\r$\16$"+
		"\u013a\3%\6%\u013e\n%\r%\16%\u013f\3%\3%\3%\3%\3&\5&\u0147\n&\3&\6&\u014a"+
		"\n&\r&\16&\u014b\3&\3&\6&\u0150\n&\r&\16&\u0151\3\'\5\'\u0155\n\'\3\'"+
		"\3\'\3(\3(\6(\u015b\n(\r(\16(\u015c\3)\6)\u0160\n)\r)\16)\u0161\3)\3)"+
		"\3*\3*\3*\3*\7*\u016a\n*\f*\16*\u016d\13*\3*\3*\3*\3*\3*\3+\3+\3+\3+\7"+
		"+\u0178\n+\f+\16+\u017b\13+\3+\5+\u017e\n+\3+\3+\3+\3+\4\u012f\u016b\2"+
		",\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37"+
		"=\2?\2A C!E\"G#I$K%M&O\'Q(S)U*\3\2\7\4\2\13\13\"\"\4\2C\\c|\4\2\62;ch"+
		"\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u0193\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2"+
		"\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q"+
		"\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\3X\3\2\2\2\5^\3\2\2\2\7f\3\2\2\2\tm\3\2"+
		"\2\2\13\u0082\3\2\2\2\r\u0084\3\2\2\2\17\u0089\3\2\2\2\21\u0091\3\2\2"+
		"\2\23\u0098\3\2\2\2\25\u00a0\3\2\2\2\27\u00a9\3\2\2\2\31\u00ae\3\2\2\2"+
		"\33\u00b4\3\2\2\2\35\u00bd\3\2\2\2\37\u00c3\3\2\2\2!\u00c8\3\2\2\2#\u00d1"+
		"\3\2\2\2%\u00d7\3\2\2\2\'\u00e2\3\2\2\2)\u00e7\3\2\2\2+\u00ef\3\2\2\2"+
		"-\u00f7\3\2\2\2/\u00fe\3\2\2\2\61\u0107\3\2\2\2\63\u0109\3\2\2\2\65\u010b"+
		"\3\2\2\2\67\u010d\3\2\2\29\u010f\3\2\2\2;\u0111\3\2\2\2=\u0113\3\2\2\2"+
		"?\u0115\3\2\2\2A\u0120\3\2\2\2C\u0122\3\2\2\2E\u012b\3\2\2\2G\u0135\3"+
		"\2\2\2I\u013d\3\2\2\2K\u0146\3\2\2\2M\u0154\3\2\2\2O\u0158\3\2\2\2Q\u015f"+
		"\3\2\2\2S\u0165\3\2\2\2U\u0173\3\2\2\2WY\t\2\2\2XW\3\2\2\2YZ\3\2\2\2Z"+
		"X\3\2\2\2Z[\3\2\2\2[\\\3\2\2\2\\]\b\2\2\2]\4\3\2\2\2^_\7d\2\2_`\7q\2\2"+
		"`a\7q\2\2ab\7n\2\2bc\7g\2\2cd\7c\2\2de\7p\2\2e\6\3\2\2\2fg\7u\2\2gh\7"+
		"v\2\2hi\7t\2\2ij\7k\2\2jk\7p\2\2kl\7i\2\2l\b\3\2\2\2mn\7k\2\2no\7p\2\2"+
		"op\7v\2\2pq\7g\2\2qr\7i\2\2rs\7g\2\2st\7t\2\2t\n\3\2\2\2uv\7o\2\2vw\7"+
		"q\2\2wx\7p\2\2xy\7g\2\2y\u0083\7{\2\2z{\7e\2\2{|\7w\2\2|}\7t\2\2}~\7t"+
		"\2\2~\177\7g\2\2\177\u0080\7p\2\2\u0080\u0081\7e\2\2\u0081\u0083\7{\2"+
		"\2\u0082u\3\2\2\2\u0082z\3\2\2\2\u0083\f\3\2\2\2\u0084\u0085\7f\2\2\u0085"+
		"\u0086\7c\2\2\u0086\u0087\7v\2\2\u0087\u0088\7g\2\2\u0088\16\3\2\2\2\u0089"+
		"\u008a\7f\2\2\u008a\u008b\7g\2\2\u008b\u008c\7e\2\2\u008c\u008d\7k\2\2"+
		"\u008d\u008e\7o\2\2\u008e\u008f\7c\2\2\u008f\u0090\7n\2\2\u0090\20\3\2"+
		"\2\2\u0091\u0092\7u\2\2\u0092\u0093\7n\2\2\u0093\u0094\7k\2\2\u0094\u0095"+
		"\7f\2\2\u0095\u0096\7g\2\2\u0096\u0097\7t\2\2\u0097\22\3\2\2\2\u0098\u0099"+
		"\7u\2\2\u0099\u009a\7r\2\2\u009a\u009b\7k\2\2\u009b\u009c\7p\2\2\u009c"+
		"\u009d\7d\2\2\u009d\u009e\7q\2\2\u009e\u009f\7z\2\2\u009f\24\3\2\2\2\u00a0"+
		"\u00a1\7e\2\2\u00a1\u00a2\7j\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7e\2\2"+
		"\u00a4\u00a5\7m\2\2\u00a5\u00a6\7d\2\2\u00a6\u00a7\7q\2\2\u00a7\u00a8"+
		"\7z\2\2\u00a8\26\3\2\2\2\u00a9\u00aa\7v\2\2\u00aa\u00ab\7g\2\2\u00ab\u00ac"+
		"\7z\2\2\u00ac\u00ad\7v\2\2\u00ad\30\3\2\2\2\u00ae\u00af\7t\2\2\u00af\u00b0"+
		"\7c\2\2\u00b0\u00b1\7f\2\2\u00b1\u00b2\7k\2\2\u00b2\u00b3\7q\2\2\u00b3"+
		"\32\3\2\2\2\u00b4\u00b5\7f\2\2\u00b5\u00b6\7t\2\2\u00b6\u00b7\7q\2\2\u00b7"+
		"\u00b8\7r\2\2\u00b8\u00b9\7f\2\2\u00b9\u00ba\7q\2\2\u00ba\u00bb\7y\2\2"+
		"\u00bb\u00bc\7p\2\2\u00bc\34\3\2\2\2\u00bd\u00be\7y\2\2\u00be\u00bf\7"+
		"k\2\2\u00bf\u00c0\7f\2\2\u00c0\u00c1\7v\2\2\u00c1\u00c2\7j\2\2\u00c2\36"+
		"\3\2\2\2\u00c3\u00c4\7h\2\2\u00c4\u00c5\7q\2\2\u00c5\u00c6\7p\2\2\u00c6"+
		"\u00c7\7v\2\2\u00c7 \3\2\2\2\u00c8\u00c9\7h\2\2\u00c9\u00ca\7q\2\2\u00ca"+
		"\u00cb\7p\2\2\u00cb\u00cc\7v\2\2\u00cc\u00cd\7u\2\2\u00cd\u00ce\7k\2\2"+
		"\u00ce\u00cf\7|\2\2\u00cf\u00d0\7g\2\2\u00d0\"\3\2\2\2\u00d1\u00d2\7e"+
		"\2\2\u00d2\u00d3\7q\2\2\u00d3\u00d4\7n\2\2\u00d4\u00d5\7q\2\2\u00d5\u00d6"+
		"\7t\2\2\u00d6$\3\2\2\2\u00d7\u00d8\7u\2\2\u00d8\u00d9\7v\2\2\u00d9\u00da"+
		"\7{\2\2\u00da\u00db\7n\2\2\u00db\u00dc\7g\2\2\u00dc\u00dd\7u\2\2\u00dd"+
		"\u00de\7j\2\2\u00de\u00df\7g\2\2\u00df\u00e0\7g\2\2\u00e0\u00e1\7v\2\2"+
		"\u00e1&\3\2\2\2\u00e2\u00e3\7r\2\2\u00e3\u00e4\7c\2\2\u00e4\u00e5\7i\2"+
		"\2\u00e5\u00e6\7g\2\2\u00e6(\3\2\2\2\u00e7\u00e8\7u\2\2\u00e8\u00e9\7"+
		"g\2\2\u00e9\u00ea\7e\2\2\u00ea\u00eb\7v\2\2\u00eb\u00ec\7k\2\2\u00ec\u00ed"+
		"\7q\2\2\u00ed\u00ee\7p\2\2\u00ee*\3\2\2\2\u00ef\u00f0\7f\2\2\u00f0\u00f1"+
		"\7g\2\2\u00f1\u00f2\7h\2\2\u00f2\u00f3\7c\2\2\u00f3\u00f4\7w\2\2\u00f4"+
		"\u00f5\7n\2\2\u00f5\u00f6\7v\2\2\u00f6,\3\2\2\2\u00f7\u00f8\7y\2\2\u00f8"+
		"\u00f9\7k\2\2\u00f9\u00fa\7f\2\2\u00fa\u00fb\7i\2\2\u00fb\u00fc\7g\2\2"+
		"\u00fc\u00fd\7v\2\2\u00fd.\3\2\2\2\u00fe\u00ff\7s\2\2\u00ff\u0100\7w\2"+
		"\2\u0100\u0101\7g\2\2\u0101\u0102\7u\2\2\u0102\u0103\7v\2\2\u0103\u0104"+
		"\7k\2\2\u0104\u0105\7q\2\2\u0105\u0106\7p\2\2\u0106\60\3\2\2\2\u0107\u0108"+
		"\7}\2\2\u0108\62\3\2\2\2\u0109\u010a\7\177\2\2\u010a\64\3\2\2\2\u010b"+
		"\u010c\7*\2\2\u010c\66\3\2\2\2\u010d\u010e\7+\2\2\u010e8\3\2\2\2\u010f"+
		"\u0110\7.\2\2\u0110:\3\2\2\2\u0111\u0112\7<\2\2\u0112<\3\2\2\2\u0113\u0114"+
		"\4\62;\2\u0114>\3\2\2\2\u0115\u0116\t\3\2\2\u0116@\3\2\2\2\u0117\u0118"+
		"\7v\2\2\u0118\u0119\7t\2\2\u0119\u011a\7w\2\2\u011a\u0121\7g\2\2\u011b"+
		"\u011c\7h\2\2\u011c\u011d\7c\2\2\u011d\u011e\7n\2\2\u011e\u011f\7u\2\2"+
		"\u011f\u0121\7g\2\2\u0120\u0117\3\2\2\2\u0120\u011b\3\2\2\2\u0121B\3\2"+
		"\2\2\u0122\u0128\5? \2\u0123\u0127\5? \2\u0124\u0127\5=\37\2\u0125\u0127"+
		"\7a\2\2\u0126\u0123\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0125\3\2\2\2\u0127"+
		"\u012a\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129D\3\2\2\2"+
		"\u012a\u0128\3\2\2\2\u012b\u012f\7$\2\2\u012c\u012e\13\2\2\2\u012d\u012c"+
		"\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u0130\3\2\2\2\u012f\u012d\3\2\2\2\u0130"+
		"\u0132\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u0133\7$\2\2\u0133F\3\2\2\2\u0134"+
		"\u0136\7/\2\2\u0135\u0134\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0138\3\2"+
		"\2\2\u0137\u0139\5=\37\2\u0138\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a"+
		"\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013bH\3\2\2\2\u013c\u013e\5=\37\2"+
		"\u013d\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u0140"+
		"\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0142\7\60\2\2\u0142\u0143\5=\37\2"+
		"\u0143\u0144\5=\37\2\u0144J\3\2\2\2\u0145\u0147\7/\2\2\u0146\u0145\3\2"+
		"\2\2\u0146\u0147\3\2\2\2\u0147\u0149\3\2\2\2\u0148\u014a\5=\37\2\u0149"+
		"\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2"+
		"\2\2\u014c\u014d\3\2\2\2\u014d\u014f\7\60\2\2\u014e\u0150\5=\37\2\u014f"+
		"\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u014f\3\2\2\2\u0151\u0152\3\2"+
		"\2\2\u0152L\3\2\2\2\u0153\u0155\7\17\2\2\u0154\u0153\3\2\2\2\u0154\u0155"+
		"\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0157\7\f\2\2\u0157N\3\2\2\2\u0158"+
		"\u015a\7%\2\2\u0159\u015b\t\4\2\2\u015a\u0159\3\2\2\2\u015b\u015c\3\2"+
		"\2\2\u015c\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015dP\3\2\2\2\u015e\u0160"+
		"\t\5\2\2\u015f\u015e\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u015f\3\2\2\2\u0161"+
		"\u0162\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0164\b)\3\2\u0164R\3\2\2\2\u0165"+
		"\u0166\7\61\2\2\u0166\u0167\7,\2\2\u0167\u016b\3\2\2\2\u0168\u016a\13"+
		"\2\2\2\u0169\u0168\3\2\2\2\u016a\u016d\3\2\2\2\u016b\u016c\3\2\2\2\u016b"+
		"\u0169\3\2\2\2\u016c\u016e\3\2\2\2\u016d\u016b\3\2\2\2\u016e\u016f\7,"+
		"\2\2\u016f\u0170\7\61\2\2\u0170\u0171\3\2\2\2\u0171\u0172\b*\3\2\u0172"+
		"T\3\2\2\2\u0173\u0174\7\61\2\2\u0174\u0175\7\61\2\2\u0175\u0179\3\2\2"+
		"\2\u0176\u0178\n\6\2\2\u0177\u0176\3\2\2\2\u0178\u017b\3\2\2\2\u0179\u0177"+
		"\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u017d\3\2\2\2\u017b\u0179\3\2\2\2\u017c"+
		"\u017e\7\17\2\2\u017d\u017c\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u017f\3"+
		"\2\2\2\u017f\u0180\7\f\2\2\u0180\u0181\3\2\2\2\u0181\u0182\b+\3\2\u0182"+
		"V\3\2\2\2\25\2Z\u0082\u0120\u0126\u0128\u012f\u0135\u013a\u013f\u0146"+
		"\u014b\u0151\u0154\u015c\u0161\u016b\u0179\u017d\4\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}