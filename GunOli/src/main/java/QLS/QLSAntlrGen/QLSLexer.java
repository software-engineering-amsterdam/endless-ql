// Generated from C:/Users/Michael/Desktop/Software Construction/endless-ql/GunOli/src/main/antlr\QLS.g4 by ANTLR 4.7
package QLS.QLSAntlrGen;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLSLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, STYLESHEET=6, PAGE=7, SECTION=8, 
		DEFAULT=9, QUESTION=10, WIDGET=11, RADIO=12, CHECKBOX=13, SPINBOX=14, 
		WIDTH=15, FONT=16, FONTSIZE=17, COLOR=18, BOOLEANTYPE=19, STRINGTYPE=20, 
		MONEYTYPE=21, INTEGERTYPE=22, DATETYPE=23, DECIMALTYPE=24, INTEGER=25, 
		DECIMAL=26, MONEY=27, DATE=28, DAY=29, MONTH=30, YEAR=31, STRING=32, IDENTIFIER=33, 
		HEXVALUE=34, WHITESPACE=35, MULTI_COMMENT=36, SINGLE_COMMENT=37;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "STYLESHEET", "PAGE", "SECTION", 
		"DEFAULT", "QUESTION", "WIDGET", "RADIO", "CHECKBOX", "SPINBOX", "WIDTH", 
		"FONT", "FONTSIZE", "COLOR", "BOOLEANTYPE", "STRINGTYPE", "MONEYTYPE", 
		"INTEGERTYPE", "DATETYPE", "DECIMALTYPE", "INTEGER", "DECIMAL", "MONEY", 
		"DATE", "DAY", "MONTH", "YEAR", "STRING", "IDENTIFIER", "HEXVALUE", "WHITESPACE", 
		"MULTI_COMMENT", "SINGLE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "'('", "','", "')'", "'stylesheet'", "'page'", "'section'", 
		"'default'", "'question'", "'widget'", "'radio'", "'checkbox'", "'spinbox'", 
		"'width:'", "'font:'", "'fontsize:'", "'color:'", "'boolean'", "'string'", 
		"'money'", "'integer'", "'date'", "'decimal'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "STYLESHEET", "PAGE", "SECTION", "DEFAULT", 
		"QUESTION", "WIDGET", "RADIO", "CHECKBOX", "SPINBOX", "WIDTH", "FONT", 
		"FONTSIZE", "COLOR", "BOOLEANTYPE", "STRINGTYPE", "MONEYTYPE", "INTEGERTYPE", 
		"DATETYPE", "DECIMALTYPE", "INTEGER", "DECIMAL", "MONEY", "DATE", "DAY", 
		"MONTH", "YEAR", "STRING", "IDENTIFIER", "HEXVALUE", "WHITESPACE", "MULTI_COMMENT", 
		"SINGLE_COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\'\u0162\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3"+
		"\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\6\32\u00e8\n\32"+
		"\r\32\16\32\u00e9\3\33\6\33\u00ed\n\33\r\33\16\33\u00ee\3\33\3\33\6\33"+
		"\u00f3\n\33\r\33\16\33\u00f4\3\34\6\34\u00f8\n\34\r\34\16\34\u00f9\3\34"+
		"\3\34\6\34\u00fe\n\34\r\34\16\34\u00ff\3\34\6\34\u0103\n\34\r\34\16\34"+
		"\u0104\5\34\u0107\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\5\36\u0115\n\36\3\37\3\37\3\37\3\37\5\37\u011b\n\37\3 \3"+
		" \3 \3 \3 \3!\3!\7!\u0124\n!\f!\16!\u0127\13!\3!\3!\3\"\6\"\u012c\n\""+
		"\r\"\16\"\u012d\3#\3#\5#\u0132\n#\3#\5#\u0135\n#\3#\5#\u0138\n#\3#\5#"+
		"\u013b\n#\3#\5#\u013e\n#\3#\5#\u0141\n#\3$\6$\u0144\n$\r$\16$\u0145\3"+
		"$\3$\3%\3%\3%\3%\7%\u014e\n%\f%\16%\u0151\13%\3%\3%\3%\3%\3%\3&\3&\3&"+
		"\3&\7&\u015c\n&\f&\16&\u015f\13&\3&\3&\4\u0125\u014f\2\'\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'\3\2\13\3\2\62;\3\2\63\64\3\2\62\63\3\2\63;\3\2\62\64\5\2\62;C\\"+
		"c|\4\2\62;ch\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u0170\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\3M\3\2\2\2\5O\3\2\2\2\7Q\3\2\2\2\tS\3\2\2\2\13U\3\2\2\2\rW\3"+
		"\2\2\2\17b\3\2\2\2\21g\3\2\2\2\23o\3\2\2\2\25w\3\2\2\2\27\u0080\3\2\2"+
		"\2\31\u0087\3\2\2\2\33\u008d\3\2\2\2\35\u0096\3\2\2\2\37\u009e\3\2\2\2"+
		"!\u00a5\3\2\2\2#\u00ab\3\2\2\2%\u00b5\3\2\2\2\'\u00bc\3\2\2\2)\u00c4\3"+
		"\2\2\2+\u00cb\3\2\2\2-\u00d1\3\2\2\2/\u00d9\3\2\2\2\61\u00de\3\2\2\2\63"+
		"\u00e7\3\2\2\2\65\u00ec\3\2\2\2\67\u0106\3\2\2\29\u0108\3\2\2\2;\u0114"+
		"\3\2\2\2=\u011a\3\2\2\2?\u011c\3\2\2\2A\u0121\3\2\2\2C\u012b\3\2\2\2E"+
		"\u012f\3\2\2\2G\u0143\3\2\2\2I\u0149\3\2\2\2K\u0157\3\2\2\2MN\7}\2\2N"+
		"\4\3\2\2\2OP\7\177\2\2P\6\3\2\2\2QR\7*\2\2R\b\3\2\2\2ST\7.\2\2T\n\3\2"+
		"\2\2UV\7+\2\2V\f\3\2\2\2WX\7u\2\2XY\7v\2\2YZ\7{\2\2Z[\7n\2\2[\\\7g\2\2"+
		"\\]\7u\2\2]^\7j\2\2^_\7g\2\2_`\7g\2\2`a\7v\2\2a\16\3\2\2\2bc\7r\2\2cd"+
		"\7c\2\2de\7i\2\2ef\7g\2\2f\20\3\2\2\2gh\7u\2\2hi\7g\2\2ij\7e\2\2jk\7v"+
		"\2\2kl\7k\2\2lm\7q\2\2mn\7p\2\2n\22\3\2\2\2op\7f\2\2pq\7g\2\2qr\7h\2\2"+
		"rs\7c\2\2st\7w\2\2tu\7n\2\2uv\7v\2\2v\24\3\2\2\2wx\7s\2\2xy\7w\2\2yz\7"+
		"g\2\2z{\7u\2\2{|\7v\2\2|}\7k\2\2}~\7q\2\2~\177\7p\2\2\177\26\3\2\2\2\u0080"+
		"\u0081\7y\2\2\u0081\u0082\7k\2\2\u0082\u0083\7f\2\2\u0083\u0084\7i\2\2"+
		"\u0084\u0085\7g\2\2\u0085\u0086\7v\2\2\u0086\30\3\2\2\2\u0087\u0088\7"+
		"t\2\2\u0088\u0089\7c\2\2\u0089\u008a\7f\2\2\u008a\u008b\7k\2\2\u008b\u008c"+
		"\7q\2\2\u008c\32\3\2\2\2\u008d\u008e\7e\2\2\u008e\u008f\7j\2\2\u008f\u0090"+
		"\7g\2\2\u0090\u0091\7e\2\2\u0091\u0092\7m\2\2\u0092\u0093\7d\2\2\u0093"+
		"\u0094\7q\2\2\u0094\u0095\7z\2\2\u0095\34\3\2\2\2\u0096\u0097\7u\2\2\u0097"+
		"\u0098\7r\2\2\u0098\u0099\7k\2\2\u0099\u009a\7p\2\2\u009a\u009b\7d\2\2"+
		"\u009b\u009c\7q\2\2\u009c\u009d\7z\2\2\u009d\36\3\2\2\2\u009e\u009f\7"+
		"y\2\2\u009f\u00a0\7k\2\2\u00a0\u00a1\7f\2\2\u00a1\u00a2\7v\2\2\u00a2\u00a3"+
		"\7j\2\2\u00a3\u00a4\7<\2\2\u00a4 \3\2\2\2\u00a5\u00a6\7h\2\2\u00a6\u00a7"+
		"\7q\2\2\u00a7\u00a8\7p\2\2\u00a8\u00a9\7v\2\2\u00a9\u00aa\7<\2\2\u00aa"+
		"\"\3\2\2\2\u00ab\u00ac\7h\2\2\u00ac\u00ad\7q\2\2\u00ad\u00ae\7p\2\2\u00ae"+
		"\u00af\7v\2\2\u00af\u00b0\7u\2\2\u00b0\u00b1\7k\2\2\u00b1\u00b2\7|\2\2"+
		"\u00b2\u00b3\7g\2\2\u00b3\u00b4\7<\2\2\u00b4$\3\2\2\2\u00b5\u00b6\7e\2"+
		"\2\u00b6\u00b7\7q\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7q\2\2\u00b9\u00ba"+
		"\7t\2\2\u00ba\u00bb\7<\2\2\u00bb&\3\2\2\2\u00bc\u00bd\7d\2\2\u00bd\u00be"+
		"\7q\2\2\u00be\u00bf\7q\2\2\u00bf\u00c0\7n\2\2\u00c0\u00c1\7g\2\2\u00c1"+
		"\u00c2\7c\2\2\u00c2\u00c3\7p\2\2\u00c3(\3\2\2\2\u00c4\u00c5\7u\2\2\u00c5"+
		"\u00c6\7v\2\2\u00c6\u00c7\7t\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9\7p\2\2"+
		"\u00c9\u00ca\7i\2\2\u00ca*\3\2\2\2\u00cb\u00cc\7o\2\2\u00cc\u00cd\7q\2"+
		"\2\u00cd\u00ce\7p\2\2\u00ce\u00cf\7g\2\2\u00cf\u00d0\7{\2\2\u00d0,\3\2"+
		"\2\2\u00d1\u00d2\7k\2\2\u00d2\u00d3\7p\2\2\u00d3\u00d4\7v\2\2\u00d4\u00d5"+
		"\7g\2\2\u00d5\u00d6\7i\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8\7t\2\2\u00d8"+
		".\3\2\2\2\u00d9\u00da\7f\2\2\u00da\u00db\7c\2\2\u00db\u00dc\7v\2\2\u00dc"+
		"\u00dd\7g\2\2\u00dd\60\3\2\2\2\u00de\u00df\7f\2\2\u00df\u00e0\7g\2\2\u00e0"+
		"\u00e1\7e\2\2\u00e1\u00e2\7k\2\2\u00e2\u00e3\7o\2\2\u00e3\u00e4\7c\2\2"+
		"\u00e4\u00e5\7n\2\2\u00e5\62\3\2\2\2\u00e6\u00e8\t\2\2\2\u00e7\u00e6\3"+
		"\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea"+
		"\64\3\2\2\2\u00eb\u00ed\t\2\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00ee\3\2\2"+
		"\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f2"+
		"\7\60\2\2\u00f1\u00f3\t\2\2\2\u00f2\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2"+
		"\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\66\3\2\2\2\u00f6\u00f8"+
		"\t\2\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9"+
		"\u00fa\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fd\7\60\2\2\u00fc\u00fe\t"+
		"\2\2\2\u00fd\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff"+
		"\u0100\3\2\2\2\u0100\u0107\3\2\2\2\u0101\u0103\t\2\2\2\u0102\u0101\3\2"+
		"\2\2\u0103\u0104\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105"+
		"\u0107\3\2\2\2\u0106\u00f7\3\2\2\2\u0106\u0102\3\2\2\2\u01078\3\2\2\2"+
		"\u0108\u0109\5;\36\2\u0109\u010a\7/\2\2\u010a\u010b\5=\37\2\u010b\u010c"+
		"\7/\2\2\u010c\u010d\5? \2\u010d:\3\2\2\2\u010e\u010f\7\62\2\2\u010f\u0115"+
		"\t\2\2\2\u0110\u0111\t\3\2\2\u0111\u0115\t\2\2\2\u0112\u0113\7\65\2\2"+
		"\u0113\u0115\t\4\2\2\u0114\u010e\3\2\2\2\u0114\u0110\3\2\2\2\u0114\u0112"+
		"\3\2\2\2\u0115<\3\2\2\2\u0116\u0117\7\62\2\2\u0117\u011b\t\5\2\2\u0118"+
		"\u0119\7\63\2\2\u0119\u011b\t\6\2\2\u011a\u0116\3\2\2\2\u011a\u0118\3"+
		"\2\2\2\u011b>\3\2\2\2\u011c\u011d\t\6\2\2\u011d\u011e\t\2\2\2\u011e\u011f"+
		"\t\2\2\2\u011f\u0120\t\2\2\2\u0120@\3\2\2\2\u0121\u0125\7$\2\2\u0122\u0124"+
		"\13\2\2\2\u0123\u0122\3\2\2\2\u0124\u0127\3\2\2\2\u0125\u0126\3\2\2\2"+
		"\u0125\u0123\3\2\2\2\u0126\u0128\3\2\2\2\u0127\u0125\3\2\2\2\u0128\u0129"+
		"\7$\2\2\u0129B\3\2\2\2\u012a\u012c\t\7\2\2\u012b\u012a\3\2\2\2\u012c\u012d"+
		"\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012eD\3\2\2\2\u012f"+
		"\u0131\7%\2\2\u0130\u0132\t\b\2\2\u0131\u0130\3\2\2\2\u0132\u0134\3\2"+
		"\2\2\u0133\u0135\t\b\2\2\u0134\u0133\3\2\2\2\u0135\u0137\3\2\2\2\u0136"+
		"\u0138\t\b\2\2\u0137\u0136\3\2\2\2\u0138\u013a\3\2\2\2\u0139\u013b\t\b"+
		"\2\2\u013a\u0139\3\2\2\2\u013b\u013d\3\2\2\2\u013c\u013e\t\b\2\2\u013d"+
		"\u013c\3\2\2\2\u013e\u0140\3\2\2\2\u013f\u0141\t\b\2\2\u0140\u013f\3\2"+
		"\2\2\u0141F\3\2\2\2\u0142\u0144\t\t\2\2\u0143\u0142\3\2\2\2\u0144\u0145"+
		"\3\2\2\2\u0145\u0143\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\3\2\2\2\u0147"+
		"\u0148\b$\2\2\u0148H\3\2\2\2\u0149\u014a\7\61\2\2\u014a\u014b\7,\2\2\u014b"+
		"\u014f\3\2\2\2\u014c\u014e\13\2\2\2\u014d\u014c\3\2\2\2\u014e\u0151\3"+
		"\2\2\2\u014f\u0150\3\2\2\2\u014f\u014d\3\2\2\2\u0150\u0152\3\2\2\2\u0151"+
		"\u014f\3\2\2\2\u0152\u0153\7,\2\2\u0153\u0154\7\61\2\2\u0154\u0155\3\2"+
		"\2\2\u0155\u0156\b%\2\2\u0156J\3\2\2\2\u0157\u0158\7\61\2\2\u0158\u0159"+
		"\7\61\2\2\u0159\u015d\3\2\2\2\u015a\u015c\n\n\2\2\u015b\u015a\3\2\2\2"+
		"\u015c\u015f\3\2\2\2\u015d\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u0160"+
		"\3\2\2\2\u015f\u015d\3\2\2\2\u0160\u0161\b&\2\2\u0161L\3\2\2\2\27\2\u00e9"+
		"\u00ee\u00f4\u00f9\u00ff\u0104\u0106\u0114\u011a\u0125\u012d\u0131\u0134"+
		"\u0137\u013a\u013d\u0140\u0145\u014f\u015d\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}