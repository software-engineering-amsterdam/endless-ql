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
		STYLESHEET=14, PAGE=15, SECTION=16, DEFAULT=17, WIDGET=18, CURLY_BRACE_L=19, 
		CURLY_BRACE_R=20, BRACE_L=21, BRACE_R=22, COMMA=23, COLON=24, BOOL=25, 
		IDENTIFIER=26, STR=27, INT=28, MON=29, DEC=30, NEWLINE=31, WHITESPACE=32, 
		MULTICOMMENT=33, SINGLECOMMENT=34;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE", "MONEYTYPE", "DATETYPE", 
		"DECIMALTYPE", "SLIDER", "SPINBOX", "CHECKBOX", "TEXT", "RADIO", "DROPDOWN", 
		"STYLESHEET", "PAGE", "SECTION", "DEFAULT", "WIDGET", "CURLY_BRACE_L", 
		"CURLY_BRACE_R", "BRACE_L", "BRACE_R", "COMMA", "COLON", "DIGIT", "LETTER", 
		"BOOL", "IDENTIFIER", "STR", "INT", "MON", "DEC", "NEWLINE", "WHITESPACE", 
		"MULTICOMMENT", "SINGLECOMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'boolean'", "'string'", "'integer'", null, "'date'", "'decimal'", 
		"'slider'", "'spinbox'", "'checkbox'", "'text'", "'radio'", "'dropdown'", 
		"'stylesheet'", "'page'", "'section'", "'default'", "'widget'", "'{'", 
		"'}'", "'('", "')'", "','", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE", "MONEYTYPE", "DATETYPE", 
		"DECIMALTYPE", "SLIDER", "SPINBOX", "CHECKBOX", "TEXT", "RADIO", "DROPDOWN", 
		"STYLESHEET", "PAGE", "SECTION", "DEFAULT", "WIDGET", "CURLY_BRACE_L", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2$\u014e\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\6\2M\n\2\r\2\16\2N\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6w"+
		"\n\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\5\34\u00f2\n\34\3\35\3\35\3\35\3\35\7\35\u00f8\n\35\f\35\16"+
		"\35\u00fb\13\35\3\36\3\36\7\36\u00ff\n\36\f\36\16\36\u0102\13\36\3\36"+
		"\3\36\3\37\5\37\u0107\n\37\3\37\6\37\u010a\n\37\r\37\16\37\u010b\3 \6"+
		" \u010f\n \r \16 \u0110\3 \3 \3 \3 \3!\5!\u0118\n!\3!\6!\u011b\n!\r!\16"+
		"!\u011c\3!\3!\6!\u0121\n!\r!\16!\u0122\3\"\5\"\u0126\n\"\3\"\3\"\3#\6"+
		"#\u012b\n#\r#\16#\u012c\3#\3#\3$\3$\3$\3$\7$\u0135\n$\f$\16$\u0138\13"+
		"$\3$\3$\3$\3$\3$\3%\3%\3%\3%\7%\u0143\n%\f%\16%\u0146\13%\3%\5%\u0149"+
		"\n%\3%\3%\3%\3%\4\u0100\u0136\2&\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\2\65\2\67\339\34;\35=\36?\37A C!E\"G#I$\3\2\6\4\2\13\13\"\""+
		"\4\2C\\c|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u015d\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\3L\3\2\2\2\5R\3\2\2\2\7Z\3\2\2\2\t"+
		"a\3\2\2\2\13v\3\2\2\2\rx\3\2\2\2\17}\3\2\2\2\21\u0085\3\2\2\2\23\u008c"+
		"\3\2\2\2\25\u0094\3\2\2\2\27\u009d\3\2\2\2\31\u00a2\3\2\2\2\33\u00a8\3"+
		"\2\2\2\35\u00b1\3\2\2\2\37\u00bc\3\2\2\2!\u00c1\3\2\2\2#\u00c9\3\2\2\2"+
		"%\u00d1\3\2\2\2\'\u00d8\3\2\2\2)\u00da\3\2\2\2+\u00dc\3\2\2\2-\u00de\3"+
		"\2\2\2/\u00e0\3\2\2\2\61\u00e2\3\2\2\2\63\u00e4\3\2\2\2\65\u00e6\3\2\2"+
		"\2\67\u00f1\3\2\2\29\u00f3\3\2\2\2;\u00fc\3\2\2\2=\u0106\3\2\2\2?\u010e"+
		"\3\2\2\2A\u0117\3\2\2\2C\u0125\3\2\2\2E\u012a\3\2\2\2G\u0130\3\2\2\2I"+
		"\u013e\3\2\2\2KM\t\2\2\2LK\3\2\2\2MN\3\2\2\2NL\3\2\2\2NO\3\2\2\2OP\3\2"+
		"\2\2PQ\b\2\2\2Q\4\3\2\2\2RS\7d\2\2ST\7q\2\2TU\7q\2\2UV\7n\2\2VW\7g\2\2"+
		"WX\7c\2\2XY\7p\2\2Y\6\3\2\2\2Z[\7u\2\2[\\\7v\2\2\\]\7t\2\2]^\7k\2\2^_"+
		"\7p\2\2_`\7i\2\2`\b\3\2\2\2ab\7k\2\2bc\7p\2\2cd\7v\2\2de\7g\2\2ef\7i\2"+
		"\2fg\7g\2\2gh\7t\2\2h\n\3\2\2\2ij\7o\2\2jk\7q\2\2kl\7p\2\2lm\7g\2\2mw"+
		"\7{\2\2no\7e\2\2op\7w\2\2pq\7t\2\2qr\7t\2\2rs\7g\2\2st\7p\2\2tu\7e\2\2"+
		"uw\7{\2\2vi\3\2\2\2vn\3\2\2\2w\f\3\2\2\2xy\7f\2\2yz\7c\2\2z{\7v\2\2{|"+
		"\7g\2\2|\16\3\2\2\2}~\7f\2\2~\177\7g\2\2\177\u0080\7e\2\2\u0080\u0081"+
		"\7k\2\2\u0081\u0082\7o\2\2\u0082\u0083\7c\2\2\u0083\u0084\7n\2\2\u0084"+
		"\20\3\2\2\2\u0085\u0086\7u\2\2\u0086\u0087\7n\2\2\u0087\u0088\7k\2\2\u0088"+
		"\u0089\7f\2\2\u0089\u008a\7g\2\2\u008a\u008b\7t\2\2\u008b\22\3\2\2\2\u008c"+
		"\u008d\7u\2\2\u008d\u008e\7r\2\2\u008e\u008f\7k\2\2\u008f\u0090\7p\2\2"+
		"\u0090\u0091\7d\2\2\u0091\u0092\7q\2\2\u0092\u0093\7z\2\2\u0093\24\3\2"+
		"\2\2\u0094\u0095\7e\2\2\u0095\u0096\7j\2\2\u0096\u0097\7g\2\2\u0097\u0098"+
		"\7e\2\2\u0098\u0099\7m\2\2\u0099\u009a\7d\2\2\u009a\u009b\7q\2\2\u009b"+
		"\u009c\7z\2\2\u009c\26\3\2\2\2\u009d\u009e\7v\2\2\u009e\u009f\7g\2\2\u009f"+
		"\u00a0\7z\2\2\u00a0\u00a1\7v\2\2\u00a1\30\3\2\2\2\u00a2\u00a3\7t\2\2\u00a3"+
		"\u00a4\7c\2\2\u00a4\u00a5\7f\2\2\u00a5\u00a6\7k\2\2\u00a6\u00a7\7q\2\2"+
		"\u00a7\32\3\2\2\2\u00a8\u00a9\7f\2\2\u00a9\u00aa\7t\2\2\u00aa\u00ab\7"+
		"q\2\2\u00ab\u00ac\7r\2\2\u00ac\u00ad\7f\2\2\u00ad\u00ae\7q\2\2\u00ae\u00af"+
		"\7y\2\2\u00af\u00b0\7p\2\2\u00b0\34\3\2\2\2\u00b1\u00b2\7u\2\2\u00b2\u00b3"+
		"\7v\2\2\u00b3\u00b4\7{\2\2\u00b4\u00b5\7n\2\2\u00b5\u00b6\7g\2\2\u00b6"+
		"\u00b7\7u\2\2\u00b7\u00b8\7j\2\2\u00b8\u00b9\7g\2\2\u00b9\u00ba\7g\2\2"+
		"\u00ba\u00bb\7v\2\2\u00bb\36\3\2\2\2\u00bc\u00bd\7r\2\2\u00bd\u00be\7"+
		"c\2\2\u00be\u00bf\7i\2\2\u00bf\u00c0\7g\2\2\u00c0 \3\2\2\2\u00c1\u00c2"+
		"\7u\2\2\u00c2\u00c3\7g\2\2\u00c3\u00c4\7e\2\2\u00c4\u00c5\7v\2\2\u00c5"+
		"\u00c6\7k\2\2\u00c6\u00c7\7q\2\2\u00c7\u00c8\7p\2\2\u00c8\"\3\2\2\2\u00c9"+
		"\u00ca\7f\2\2\u00ca\u00cb\7g\2\2\u00cb\u00cc\7h\2\2\u00cc\u00cd\7c\2\2"+
		"\u00cd\u00ce\7w\2\2\u00ce\u00cf\7n\2\2\u00cf\u00d0\7v\2\2\u00d0$\3\2\2"+
		"\2\u00d1\u00d2\7y\2\2\u00d2\u00d3\7k\2\2\u00d3\u00d4\7f\2\2\u00d4\u00d5"+
		"\7i\2\2\u00d5\u00d6\7g\2\2\u00d6\u00d7\7v\2\2\u00d7&\3\2\2\2\u00d8\u00d9"+
		"\7}\2\2\u00d9(\3\2\2\2\u00da\u00db\7\177\2\2\u00db*\3\2\2\2\u00dc\u00dd"+
		"\7*\2\2\u00dd,\3\2\2\2\u00de\u00df\7+\2\2\u00df.\3\2\2\2\u00e0\u00e1\7"+
		".\2\2\u00e1\60\3\2\2\2\u00e2\u00e3\7<\2\2\u00e3\62\3\2\2\2\u00e4\u00e5"+
		"\4\62;\2\u00e5\64\3\2\2\2\u00e6\u00e7\t\3\2\2\u00e7\66\3\2\2\2\u00e8\u00e9"+
		"\7v\2\2\u00e9\u00ea\7t\2\2\u00ea\u00eb\7w\2\2\u00eb\u00f2\7g\2\2\u00ec"+
		"\u00ed\7h\2\2\u00ed\u00ee\7c\2\2\u00ee\u00ef\7n\2\2\u00ef\u00f0\7u\2\2"+
		"\u00f0\u00f2\7g\2\2\u00f1\u00e8\3\2\2\2\u00f1\u00ec\3\2\2\2\u00f28\3\2"+
		"\2\2\u00f3\u00f9\5\65\33\2\u00f4\u00f8\5\65\33\2\u00f5\u00f8\5\63\32\2"+
		"\u00f6\u00f8\7a\2\2\u00f7\u00f4\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f6"+
		"\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa"+
		":\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u0100\7$\2\2\u00fd\u00ff\13\2\2\2"+
		"\u00fe\u00fd\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u0101\3\2\2\2\u0100\u00fe"+
		"\3\2\2\2\u0101\u0103\3\2\2\2\u0102\u0100\3\2\2\2\u0103\u0104\7$\2\2\u0104"+
		"<\3\2\2\2\u0105\u0107\7/\2\2\u0106\u0105\3\2\2\2\u0106\u0107\3\2\2\2\u0107"+
		"\u0109\3\2\2\2\u0108\u010a\5\63\32\2\u0109\u0108\3\2\2\2\u010a\u010b\3"+
		"\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c>\3\2\2\2\u010d\u010f"+
		"\5\63\32\2\u010e\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u010e\3\2\2\2"+
		"\u0110\u0111\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\7\60\2\2\u0113\u0114"+
		"\5\63\32\2\u0114\u0115\5\63\32\2\u0115@\3\2\2\2\u0116\u0118\7/\2\2\u0117"+
		"\u0116\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u011a\3\2\2\2\u0119\u011b\5\63"+
		"\32\2\u011a\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011a\3\2\2\2\u011c"+
		"\u011d\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u0120\7\60\2\2\u011f\u0121\5"+
		"\63\32\2\u0120\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0120\3\2\2\2\u0122"+
		"\u0123\3\2\2\2\u0123B\3\2\2\2\u0124\u0126\7\17\2\2\u0125\u0124\3\2\2\2"+
		"\u0125\u0126\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0128\7\f\2\2\u0128D\3"+
		"\2\2\2\u0129\u012b\t\4\2\2\u012a\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c"+
		"\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\b#"+
		"\3\2\u012fF\3\2\2\2\u0130\u0131\7\61\2\2\u0131\u0132\7,\2\2\u0132\u0136"+
		"\3\2\2\2\u0133\u0135\13\2\2\2\u0134\u0133\3\2\2\2\u0135\u0138\3\2\2\2"+
		"\u0136\u0137\3\2\2\2\u0136\u0134\3\2\2\2\u0137\u0139\3\2\2\2\u0138\u0136"+
		"\3\2\2\2\u0139\u013a\7,\2\2\u013a\u013b\7\61\2\2\u013b\u013c\3\2\2\2\u013c"+
		"\u013d\b$\3\2\u013dH\3\2\2\2\u013e\u013f\7\61\2\2\u013f\u0140\7\61\2\2"+
		"\u0140\u0144\3\2\2\2\u0141\u0143\n\5\2\2\u0142\u0141\3\2\2\2\u0143\u0146"+
		"\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0148\3\2\2\2\u0146"+
		"\u0144\3\2\2\2\u0147\u0149\7\17\2\2\u0148\u0147\3\2\2\2\u0148\u0149\3"+
		"\2\2\2\u0149\u014a\3\2\2\2\u014a\u014b\7\f\2\2\u014b\u014c\3\2\2\2\u014c"+
		"\u014d\b%\3\2\u014dJ\3\2\2\2\24\2Nv\u00f1\u00f7\u00f9\u0100\u0106\u010b"+
		"\u0110\u0117\u011c\u0122\u0125\u012c\u0136\u0144\u0148\4\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}