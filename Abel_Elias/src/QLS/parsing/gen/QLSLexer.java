// Generated from /home/ajm/Desktop/newEndless/endless-ql/Abel_Elias/src/QLS/parsing/QLS.g4 by ANTLR 4.7
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
		STYLESHEET=14, PAGE=15, SECTION=16, DEFAULT=17, WIDGET=18, QUESTION=19, 
		CURLY_BRACE_L=20, CURLY_BRACE_R=21, BRACE_L=22, BRACE_R=23, COMMA=24, 
		COLON=25, BOOL=26, IDENTIFIER=27, STR=28, INT=29, MON=30, DEC=31, NEWLINE=32, 
		WHITESPACE=33, MULTICOMMENT=34, SINGLECOMMENT=35;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE", "MONEYTYPE", "DATETYPE", 
		"DECIMALTYPE", "SLIDER", "SPINBOX", "CHECKBOX", "TEXT", "RADIO", "DROPDOWN", 
		"STYLESHEET", "PAGE", "SECTION", "DEFAULT", "WIDGET", "QUESTION", "CURLY_BRACE_L", 
		"CURLY_BRACE_R", "BRACE_L", "BRACE_R", "COMMA", "COLON", "DIGIT", "LETTER", 
		"BOOL", "IDENTIFIER", "STR", "INT", "MON", "DEC", "NEWLINE", "WHITESPACE", 
		"MULTICOMMENT", "SINGLECOMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'boolean'", "'string'", "'integer'", null, "'date'", "'decimal'", 
		"'slider'", "'spinbox'", "'checkbox'", "'text'", "'radio'", "'dropdown'", 
		"'stylesheet'", "'page'", "'section'", "'default'", "'widget'", "'question'", 
		"'{'", "'}'", "'('", "')'", "','", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE", "MONEYTYPE", "DATETYPE", 
		"DECIMALTYPE", "SLIDER", "SPINBOX", "CHECKBOX", "TEXT", "RADIO", "DROPDOWN", 
		"STYLESHEET", "PAGE", "SECTION", "DEFAULT", "WIDGET", "QUESTION", "CURLY_BRACE_L", 
		"CURLY_BRACE_R", "BRACE_L", "BRACE_R", "COMMA", "COLON", "BOOL", "IDENTIFIER", 
		"STR", "INT", "MON", "DEC", "NEWLINE", "WHITESPACE", "MULTICOMMENT", "SINGLECOMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2%\u0159\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\6\2O\n\2\r\2\16\2P\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\5\6y\n\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3"+
		"\33\3\33\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u00fd"+
		"\n\35\3\36\3\36\3\36\3\36\7\36\u0103\n\36\f\36\16\36\u0106\13\36\3\37"+
		"\3\37\7\37\u010a\n\37\f\37\16\37\u010d\13\37\3\37\3\37\3 \5 \u0112\n "+
		"\3 \6 \u0115\n \r \16 \u0116\3!\6!\u011a\n!\r!\16!\u011b\3!\3!\3!\3!\3"+
		"\"\5\"\u0123\n\"\3\"\6\"\u0126\n\"\r\"\16\"\u0127\3\"\3\"\6\"\u012c\n"+
		"\"\r\"\16\"\u012d\3#\5#\u0131\n#\3#\3#\3$\6$\u0136\n$\r$\16$\u0137\3$"+
		"\3$\3%\3%\3%\3%\7%\u0140\n%\f%\16%\u0143\13%\3%\3%\3%\3%\3%\3&\3&\3&\3"+
		"&\7&\u014e\n&\f&\16&\u0151\13&\3&\5&\u0154\n&\3&\3&\3&\3&\4\u010b\u0141"+
		"\2\'\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\2\67\29\34;"+
		"\35=\36?\37A C!E\"G#I$K%\3\2\6\4\2\13\13\"\"\4\2C\\c|\5\2\13\f\17\17\""+
		"\"\4\2\f\f\17\17\2\u0168\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\29\3\2\2\2\2;\3\2\2"+
		"\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2"+
		"I\3\2\2\2\2K\3\2\2\2\3N\3\2\2\2\5T\3\2\2\2\7\\\3\2\2\2\tc\3\2\2\2\13x"+
		"\3\2\2\2\rz\3\2\2\2\17\177\3\2\2\2\21\u0087\3\2\2\2\23\u008e\3\2\2\2\25"+
		"\u0096\3\2\2\2\27\u009f\3\2\2\2\31\u00a4\3\2\2\2\33\u00aa\3\2\2\2\35\u00b3"+
		"\3\2\2\2\37\u00be\3\2\2\2!\u00c3\3\2\2\2#\u00cb\3\2\2\2%\u00d3\3\2\2\2"+
		"\'\u00da\3\2\2\2)\u00e3\3\2\2\2+\u00e5\3\2\2\2-\u00e7\3\2\2\2/\u00e9\3"+
		"\2\2\2\61\u00eb\3\2\2\2\63\u00ed\3\2\2\2\65\u00ef\3\2\2\2\67\u00f1\3\2"+
		"\2\29\u00fc\3\2\2\2;\u00fe\3\2\2\2=\u0107\3\2\2\2?\u0111\3\2\2\2A\u0119"+
		"\3\2\2\2C\u0122\3\2\2\2E\u0130\3\2\2\2G\u0135\3\2\2\2I\u013b\3\2\2\2K"+
		"\u0149\3\2\2\2MO\t\2\2\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QR\3\2"+
		"\2\2RS\b\2\2\2S\4\3\2\2\2TU\7d\2\2UV\7q\2\2VW\7q\2\2WX\7n\2\2XY\7g\2\2"+
		"YZ\7c\2\2Z[\7p\2\2[\6\3\2\2\2\\]\7u\2\2]^\7v\2\2^_\7t\2\2_`\7k\2\2`a\7"+
		"p\2\2ab\7i\2\2b\b\3\2\2\2cd\7k\2\2de\7p\2\2ef\7v\2\2fg\7g\2\2gh\7i\2\2"+
		"hi\7g\2\2ij\7t\2\2j\n\3\2\2\2kl\7o\2\2lm\7q\2\2mn\7p\2\2no\7g\2\2oy\7"+
		"{\2\2pq\7e\2\2qr\7w\2\2rs\7t\2\2st\7t\2\2tu\7g\2\2uv\7p\2\2vw\7e\2\2w"+
		"y\7{\2\2xk\3\2\2\2xp\3\2\2\2y\f\3\2\2\2z{\7f\2\2{|\7c\2\2|}\7v\2\2}~\7"+
		"g\2\2~\16\3\2\2\2\177\u0080\7f\2\2\u0080\u0081\7g\2\2\u0081\u0082\7e\2"+
		"\2\u0082\u0083\7k\2\2\u0083\u0084\7o\2\2\u0084\u0085\7c\2\2\u0085\u0086"+
		"\7n\2\2\u0086\20\3\2\2\2\u0087\u0088\7u\2\2\u0088\u0089\7n\2\2\u0089\u008a"+
		"\7k\2\2\u008a\u008b\7f\2\2\u008b\u008c\7g\2\2\u008c\u008d\7t\2\2\u008d"+
		"\22\3\2\2\2\u008e\u008f\7u\2\2\u008f\u0090\7r\2\2\u0090\u0091\7k\2\2\u0091"+
		"\u0092\7p\2\2\u0092\u0093\7d\2\2\u0093\u0094\7q\2\2\u0094\u0095\7z\2\2"+
		"\u0095\24\3\2\2\2\u0096\u0097\7e\2\2\u0097\u0098\7j\2\2\u0098\u0099\7"+
		"g\2\2\u0099\u009a\7e\2\2\u009a\u009b\7m\2\2\u009b\u009c\7d\2\2\u009c\u009d"+
		"\7q\2\2\u009d\u009e\7z\2\2\u009e\26\3\2\2\2\u009f\u00a0\7v\2\2\u00a0\u00a1"+
		"\7g\2\2\u00a1\u00a2\7z\2\2\u00a2\u00a3\7v\2\2\u00a3\30\3\2\2\2\u00a4\u00a5"+
		"\7t\2\2\u00a5\u00a6\7c\2\2\u00a6\u00a7\7f\2\2\u00a7\u00a8\7k\2\2\u00a8"+
		"\u00a9\7q\2\2\u00a9\32\3\2\2\2\u00aa\u00ab\7f\2\2\u00ab\u00ac\7t\2\2\u00ac"+
		"\u00ad\7q\2\2\u00ad\u00ae\7r\2\2\u00ae\u00af\7f\2\2\u00af\u00b0\7q\2\2"+
		"\u00b0\u00b1\7y\2\2\u00b1\u00b2\7p\2\2\u00b2\34\3\2\2\2\u00b3\u00b4\7"+
		"u\2\2\u00b4\u00b5\7v\2\2\u00b5\u00b6\7{\2\2\u00b6\u00b7\7n\2\2\u00b7\u00b8"+
		"\7g\2\2\u00b8\u00b9\7u\2\2\u00b9\u00ba\7j\2\2\u00ba\u00bb\7g\2\2\u00bb"+
		"\u00bc\7g\2\2\u00bc\u00bd\7v\2\2\u00bd\36\3\2\2\2\u00be\u00bf\7r\2\2\u00bf"+
		"\u00c0\7c\2\2\u00c0\u00c1\7i\2\2\u00c1\u00c2\7g\2\2\u00c2 \3\2\2\2\u00c3"+
		"\u00c4\7u\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\7e\2\2\u00c6\u00c7\7v\2\2"+
		"\u00c7\u00c8\7k\2\2\u00c8\u00c9\7q\2\2\u00c9\u00ca\7p\2\2\u00ca\"\3\2"+
		"\2\2\u00cb\u00cc\7f\2\2\u00cc\u00cd\7g\2\2\u00cd\u00ce\7h\2\2\u00ce\u00cf"+
		"\7c\2\2\u00cf\u00d0\7w\2\2\u00d0\u00d1\7n\2\2\u00d1\u00d2\7v\2\2\u00d2"+
		"$\3\2\2\2\u00d3\u00d4\7y\2\2\u00d4\u00d5\7k\2\2\u00d5\u00d6\7f\2\2\u00d6"+
		"\u00d7\7i\2\2\u00d7\u00d8\7g\2\2\u00d8\u00d9\7v\2\2\u00d9&\3\2\2\2\u00da"+
		"\u00db\7s\2\2\u00db\u00dc\7w\2\2\u00dc\u00dd\7g\2\2\u00dd\u00de\7u\2\2"+
		"\u00de\u00df\7v\2\2\u00df\u00e0\7k\2\2\u00e0\u00e1\7q\2\2\u00e1\u00e2"+
		"\7p\2\2\u00e2(\3\2\2\2\u00e3\u00e4\7}\2\2\u00e4*\3\2\2\2\u00e5\u00e6\7"+
		"\177\2\2\u00e6,\3\2\2\2\u00e7\u00e8\7*\2\2\u00e8.\3\2\2\2\u00e9\u00ea"+
		"\7+\2\2\u00ea\60\3\2\2\2\u00eb\u00ec\7.\2\2\u00ec\62\3\2\2\2\u00ed\u00ee"+
		"\7<\2\2\u00ee\64\3\2\2\2\u00ef\u00f0\4\62;\2\u00f0\66\3\2\2\2\u00f1\u00f2"+
		"\t\3\2\2\u00f28\3\2\2\2\u00f3\u00f4\7v\2\2\u00f4\u00f5\7t\2\2\u00f5\u00f6"+
		"\7w\2\2\u00f6\u00fd\7g\2\2\u00f7\u00f8\7h\2\2\u00f8\u00f9\7c\2\2\u00f9"+
		"\u00fa\7n\2\2\u00fa\u00fb\7u\2\2\u00fb\u00fd\7g\2\2\u00fc\u00f3\3\2\2"+
		"\2\u00fc\u00f7\3\2\2\2\u00fd:\3\2\2\2\u00fe\u0104\5\67\34\2\u00ff\u0103"+
		"\5\67\34\2\u0100\u0103\5\65\33\2\u0101\u0103\7a\2\2\u0102\u00ff\3\2\2"+
		"\2\u0102\u0100\3\2\2\2\u0102\u0101\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102"+
		"\3\2\2\2\u0104\u0105\3\2\2\2\u0105<\3\2\2\2\u0106\u0104\3\2\2\2\u0107"+
		"\u010b\7$\2\2\u0108\u010a\13\2\2\2\u0109\u0108\3\2\2\2\u010a\u010d\3\2"+
		"\2\2\u010b\u010c\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010e\3\2\2\2\u010d"+
		"\u010b\3\2\2\2\u010e\u010f\7$\2\2\u010f>\3\2\2\2\u0110\u0112\7/\2\2\u0111"+
		"\u0110\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0114\3\2\2\2\u0113\u0115\5\65"+
		"\33\2\u0114\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0114\3\2\2\2\u0116"+
		"\u0117\3\2\2\2\u0117@\3\2\2\2\u0118\u011a\5\65\33\2\u0119\u0118\3\2\2"+
		"\2\u011a\u011b\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d"+
		"\3\2\2\2\u011d\u011e\7\60\2\2\u011e\u011f\5\65\33\2\u011f\u0120\5\65\33"+
		"\2\u0120B\3\2\2\2\u0121\u0123\7/\2\2\u0122\u0121\3\2\2\2\u0122\u0123\3"+
		"\2\2\2\u0123\u0125\3\2\2\2\u0124\u0126\5\65\33\2\u0125\u0124\3\2\2\2\u0126"+
		"\u0127\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0129\3\2"+
		"\2\2\u0129\u012b\7\60\2\2\u012a\u012c\5\65\33\2\u012b\u012a\3\2\2\2\u012c"+
		"\u012d\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012eD\3\2\2\2"+
		"\u012f\u0131\7\17\2\2\u0130\u012f\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0132"+
		"\3\2\2\2\u0132\u0133\7\f\2\2\u0133F\3\2\2\2\u0134\u0136\t\4\2\2\u0135"+
		"\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0135\3\2\2\2\u0137\u0138\3\2"+
		"\2\2\u0138\u0139\3\2\2\2\u0139\u013a\b$\3\2\u013aH\3\2\2\2\u013b\u013c"+
		"\7\61\2\2\u013c\u013d\7,\2\2\u013d\u0141\3\2\2\2\u013e\u0140\13\2\2\2"+
		"\u013f\u013e\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u0142\3\2\2\2\u0141\u013f"+
		"\3\2\2\2\u0142\u0144\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u0145\7,\2\2\u0145"+
		"\u0146\7\61\2\2\u0146\u0147\3\2\2\2\u0147\u0148\b%\3\2\u0148J\3\2\2\2"+
		"\u0149\u014a\7\61\2\2\u014a\u014b\7\61\2\2\u014b\u014f\3\2\2\2\u014c\u014e"+
		"\n\5\2\2\u014d\u014c\3\2\2\2\u014e\u0151\3\2\2\2\u014f\u014d\3\2\2\2\u014f"+
		"\u0150\3\2\2\2\u0150\u0153\3\2\2\2\u0151\u014f\3\2\2\2\u0152\u0154\7\17"+
		"\2\2\u0153\u0152\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0155\3\2\2\2\u0155"+
		"\u0156\7\f\2\2\u0156\u0157\3\2\2\2\u0157\u0158\b&\3\2\u0158L\3\2\2\2\24"+
		"\2Px\u00fc\u0102\u0104\u010b\u0111\u0116\u011b\u0122\u0127\u012d\u0130"+
		"\u0137\u0141\u014f\u0153\4\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}