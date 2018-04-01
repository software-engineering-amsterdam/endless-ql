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
		INT=33, MON=34, DEC=35, NEWLINE=36, WHITESPACE=37, MULTICOMMENT=38, SINGLECOMMENT=39;
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
		"STR", "INT", "MON", "DEC", "NEWLINE", "WHITESPACE", "MULTICOMMENT", "SINGLECOMMENT"
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
		"DEC", "NEWLINE", "WHITESPACE", "MULTICOMMENT", "SINGLECOMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2)\u017b\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\6\2"+
		"W\n\2\r\2\16\2X\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0081\n\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34"+
		"\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u011f"+
		"\n!\3\"\3\"\3\"\3\"\7\"\u0125\n\"\f\"\16\"\u0128\13\"\3#\3#\7#\u012c\n"+
		"#\f#\16#\u012f\13#\3#\3#\3$\5$\u0134\n$\3$\6$\u0137\n$\r$\16$\u0138\3"+
		"%\6%\u013c\n%\r%\16%\u013d\3%\3%\3%\3%\3&\5&\u0145\n&\3&\6&\u0148\n&\r"+
		"&\16&\u0149\3&\3&\6&\u014e\n&\r&\16&\u014f\3\'\5\'\u0153\n\'\3\'\3\'\3"+
		"(\6(\u0158\n(\r(\16(\u0159\3(\3(\3)\3)\3)\3)\7)\u0162\n)\f)\16)\u0165"+
		"\13)\3)\3)\3)\3)\3)\3*\3*\3*\3*\7*\u0170\n*\f*\16*\u0173\13*\3*\5*\u0176"+
		"\n*\3*\3*\3*\3*\4\u012d\u0163\2+\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37=\2?\2A C!E\"G#I$K%M&O\'Q(S)\3\2\6\4\2"+
		"\13\13\"\"\4\2C\\c|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u018a\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2"+
		"\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\3V\3\2\2\2\5\\\3\2\2\2\7d\3\2\2\2"+
		"\tk\3\2\2\2\13\u0080\3\2\2\2\r\u0082\3\2\2\2\17\u0087\3\2\2\2\21\u008f"+
		"\3\2\2\2\23\u0096\3\2\2\2\25\u009e\3\2\2\2\27\u00a7\3\2\2\2\31\u00ac\3"+
		"\2\2\2\33\u00b2\3\2\2\2\35\u00bb\3\2\2\2\37\u00c1\3\2\2\2!\u00c6\3\2\2"+
		"\2#\u00cf\3\2\2\2%\u00d5\3\2\2\2\'\u00e0\3\2\2\2)\u00e5\3\2\2\2+\u00ed"+
		"\3\2\2\2-\u00f5\3\2\2\2/\u00fc\3\2\2\2\61\u0105\3\2\2\2\63\u0107\3\2\2"+
		"\2\65\u0109\3\2\2\2\67\u010b\3\2\2\29\u010d\3\2\2\2;\u010f\3\2\2\2=\u0111"+
		"\3\2\2\2?\u0113\3\2\2\2A\u011e\3\2\2\2C\u0120\3\2\2\2E\u0129\3\2\2\2G"+
		"\u0133\3\2\2\2I\u013b\3\2\2\2K\u0144\3\2\2\2M\u0152\3\2\2\2O\u0157\3\2"+
		"\2\2Q\u015d\3\2\2\2S\u016b\3\2\2\2UW\t\2\2\2VU\3\2\2\2WX\3\2\2\2XV\3\2"+
		"\2\2XY\3\2\2\2YZ\3\2\2\2Z[\b\2\2\2[\4\3\2\2\2\\]\7d\2\2]^\7q\2\2^_\7q"+
		"\2\2_`\7n\2\2`a\7g\2\2ab\7c\2\2bc\7p\2\2c\6\3\2\2\2de\7u\2\2ef\7v\2\2"+
		"fg\7t\2\2gh\7k\2\2hi\7p\2\2ij\7i\2\2j\b\3\2\2\2kl\7k\2\2lm\7p\2\2mn\7"+
		"v\2\2no\7g\2\2op\7i\2\2pq\7g\2\2qr\7t\2\2r\n\3\2\2\2st\7o\2\2tu\7q\2\2"+
		"uv\7p\2\2vw\7g\2\2w\u0081\7{\2\2xy\7e\2\2yz\7w\2\2z{\7t\2\2{|\7t\2\2|"+
		"}\7g\2\2}~\7p\2\2~\177\7e\2\2\177\u0081\7{\2\2\u0080s\3\2\2\2\u0080x\3"+
		"\2\2\2\u0081\f\3\2\2\2\u0082\u0083\7f\2\2\u0083\u0084\7c\2\2\u0084\u0085"+
		"\7v\2\2\u0085\u0086\7g\2\2\u0086\16\3\2\2\2\u0087\u0088\7f\2\2\u0088\u0089"+
		"\7g\2\2\u0089\u008a\7e\2\2\u008a\u008b\7k\2\2\u008b\u008c\7o\2\2\u008c"+
		"\u008d\7c\2\2\u008d\u008e\7n\2\2\u008e\20\3\2\2\2\u008f\u0090\7u\2\2\u0090"+
		"\u0091\7n\2\2\u0091\u0092\7k\2\2\u0092\u0093\7f\2\2\u0093\u0094\7g\2\2"+
		"\u0094\u0095\7t\2\2\u0095\22\3\2\2\2\u0096\u0097\7u\2\2\u0097\u0098\7"+
		"r\2\2\u0098\u0099\7k\2\2\u0099\u009a\7p\2\2\u009a\u009b\7d\2\2\u009b\u009c"+
		"\7q\2\2\u009c\u009d\7z\2\2\u009d\24\3\2\2\2\u009e\u009f\7e\2\2\u009f\u00a0"+
		"\7j\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7e\2\2\u00a2\u00a3\7m\2\2\u00a3"+
		"\u00a4\7d\2\2\u00a4\u00a5\7q\2\2\u00a5\u00a6\7z\2\2\u00a6\26\3\2\2\2\u00a7"+
		"\u00a8\7v\2\2\u00a8\u00a9\7g\2\2\u00a9\u00aa\7z\2\2\u00aa\u00ab\7v\2\2"+
		"\u00ab\30\3\2\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae\7c\2\2\u00ae\u00af\7"+
		"f\2\2\u00af\u00b0\7k\2\2\u00b0\u00b1\7q\2\2\u00b1\32\3\2\2\2\u00b2\u00b3"+
		"\7f\2\2\u00b3\u00b4\7t\2\2\u00b4\u00b5\7q\2\2\u00b5\u00b6\7r\2\2\u00b6"+
		"\u00b7\7f\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9\7y\2\2\u00b9\u00ba\7p\2\2"+
		"\u00ba\34\3\2\2\2\u00bb\u00bc\7y\2\2\u00bc\u00bd\7k\2\2\u00bd\u00be\7"+
		"f\2\2\u00be\u00bf\7v\2\2\u00bf\u00c0\7j\2\2\u00c0\36\3\2\2\2\u00c1\u00c2"+
		"\7h\2\2\u00c2\u00c3\7q\2\2\u00c3\u00c4\7p\2\2\u00c4\u00c5\7v\2\2\u00c5"+
		" \3\2\2\2\u00c6\u00c7\7h\2\2\u00c7\u00c8\7q\2\2\u00c8\u00c9\7p\2\2\u00c9"+
		"\u00ca\7v\2\2\u00ca\u00cb\7u\2\2\u00cb\u00cc\7k\2\2\u00cc\u00cd\7|\2\2"+
		"\u00cd\u00ce\7g\2\2\u00ce\"\3\2\2\2\u00cf\u00d0\7e\2\2\u00d0\u00d1\7q"+
		"\2\2\u00d1\u00d2\7n\2\2\u00d2\u00d3\7q\2\2\u00d3\u00d4\7t\2\2\u00d4$\3"+
		"\2\2\2\u00d5\u00d6\7u\2\2\u00d6\u00d7\7v\2\2\u00d7\u00d8\7{\2\2\u00d8"+
		"\u00d9\7n\2\2\u00d9\u00da\7g\2\2\u00da\u00db\7u\2\2\u00db\u00dc\7j\2\2"+
		"\u00dc\u00dd\7g\2\2\u00dd\u00de\7g\2\2\u00de\u00df\7v\2\2\u00df&\3\2\2"+
		"\2\u00e0\u00e1\7r\2\2\u00e1\u00e2\7c\2\2\u00e2\u00e3\7i\2\2\u00e3\u00e4"+
		"\7g\2\2\u00e4(\3\2\2\2\u00e5\u00e6\7u\2\2\u00e6\u00e7\7g\2\2\u00e7\u00e8"+
		"\7e\2\2\u00e8\u00e9\7v\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7q\2\2\u00eb"+
		"\u00ec\7p\2\2\u00ec*\3\2\2\2\u00ed\u00ee\7f\2\2\u00ee\u00ef\7g\2\2\u00ef"+
		"\u00f0\7h\2\2\u00f0\u00f1\7c\2\2\u00f1\u00f2\7w\2\2\u00f2\u00f3\7n\2\2"+
		"\u00f3\u00f4\7v\2\2\u00f4,\3\2\2\2\u00f5\u00f6\7y\2\2\u00f6\u00f7\7k\2"+
		"\2\u00f7\u00f8\7f\2\2\u00f8\u00f9\7i\2\2\u00f9\u00fa\7g\2\2\u00fa\u00fb"+
		"\7v\2\2\u00fb.\3\2\2\2\u00fc\u00fd\7s\2\2\u00fd\u00fe\7w\2\2\u00fe\u00ff"+
		"\7g\2\2\u00ff\u0100\7u\2\2\u0100\u0101\7v\2\2\u0101\u0102\7k\2\2\u0102"+
		"\u0103\7q\2\2\u0103\u0104\7p\2\2\u0104\60\3\2\2\2\u0105\u0106\7}\2\2\u0106"+
		"\62\3\2\2\2\u0107\u0108\7\177\2\2\u0108\64\3\2\2\2\u0109\u010a\7*\2\2"+
		"\u010a\66\3\2\2\2\u010b\u010c\7+\2\2\u010c8\3\2\2\2\u010d\u010e\7.\2\2"+
		"\u010e:\3\2\2\2\u010f\u0110\7<\2\2\u0110<\3\2\2\2\u0111\u0112\4\62;\2"+
		"\u0112>\3\2\2\2\u0113\u0114\t\3\2\2\u0114@\3\2\2\2\u0115\u0116\7v\2\2"+
		"\u0116\u0117\7t\2\2\u0117\u0118\7w\2\2\u0118\u011f\7g\2\2\u0119\u011a"+
		"\7h\2\2\u011a\u011b\7c\2\2\u011b\u011c\7n\2\2\u011c\u011d\7u\2\2\u011d"+
		"\u011f\7g\2\2\u011e\u0115\3\2\2\2\u011e\u0119\3\2\2\2\u011fB\3\2\2\2\u0120"+
		"\u0126\5? \2\u0121\u0125\5? \2\u0122\u0125\5=\37\2\u0123\u0125\7a\2\2"+
		"\u0124\u0121\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0123\3\2\2\2\u0125\u0128"+
		"\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127D\3\2\2\2\u0128"+
		"\u0126\3\2\2\2\u0129\u012d\7$\2\2\u012a\u012c\13\2\2\2\u012b\u012a\3\2"+
		"\2\2\u012c\u012f\3\2\2\2\u012d\u012e\3\2\2\2\u012d\u012b\3\2\2\2\u012e"+
		"\u0130\3\2\2\2\u012f\u012d\3\2\2\2\u0130\u0131\7$\2\2\u0131F\3\2\2\2\u0132"+
		"\u0134\7/\2\2\u0133\u0132\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0136\3\2"+
		"\2\2\u0135\u0137\5=\37\2\u0136\u0135\3\2\2\2\u0137\u0138\3\2\2\2\u0138"+
		"\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139H\3\2\2\2\u013a\u013c\5=\37\2"+
		"\u013b\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e"+
		"\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0140\7\60\2\2\u0140\u0141\5=\37\2"+
		"\u0141\u0142\5=\37\2\u0142J\3\2\2\2\u0143\u0145\7/\2\2\u0144\u0143\3\2"+
		"\2\2\u0144\u0145\3\2\2\2\u0145\u0147\3\2\2\2\u0146\u0148\5=\37\2\u0147"+
		"\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2"+
		"\2\2\u014a\u014b\3\2\2\2\u014b\u014d\7\60\2\2\u014c\u014e\5=\37\2\u014d"+
		"\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u0150\3\2"+
		"\2\2\u0150L\3\2\2\2\u0151\u0153\7\17\2\2\u0152\u0151\3\2\2\2\u0152\u0153"+
		"\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0155\7\f\2\2\u0155N\3\2\2\2\u0156"+
		"\u0158\t\4\2\2\u0157\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u0157\3\2"+
		"\2\2\u0159\u015a\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015c\b(\3\2\u015c"+
		"P\3\2\2\2\u015d\u015e\7\61\2\2\u015e\u015f\7,\2\2\u015f\u0163\3\2\2\2"+
		"\u0160\u0162\13\2\2\2\u0161\u0160\3\2\2\2\u0162\u0165\3\2\2\2\u0163\u0164"+
		"\3\2\2\2\u0163\u0161\3\2\2\2\u0164\u0166\3\2\2\2\u0165\u0163\3\2\2\2\u0166"+
		"\u0167\7,\2\2\u0167\u0168\7\61\2\2\u0168\u0169\3\2\2\2\u0169\u016a\b)"+
		"\3\2\u016aR\3\2\2\2\u016b\u016c\7\61\2\2\u016c\u016d\7\61\2\2\u016d\u0171"+
		"\3\2\2\2\u016e\u0170\n\5\2\2\u016f\u016e\3\2\2\2\u0170\u0173\3\2\2\2\u0171"+
		"\u016f\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0175\3\2\2\2\u0173\u0171\3\2"+
		"\2\2\u0174\u0176\7\17\2\2\u0175\u0174\3\2\2\2\u0175\u0176\3\2\2\2\u0176"+
		"\u0177\3\2\2\2\u0177\u0178\7\f\2\2\u0178\u0179\3\2\2\2\u0179\u017a\b*"+
		"\3\2\u017aT\3\2\2\2\24\2X\u0080\u011e\u0124\u0126\u012d\u0133\u0138\u013d"+
		"\u0144\u0149\u014f\u0152\u0159\u0163\u0171\u0175\4\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}