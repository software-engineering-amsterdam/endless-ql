// Generated from C:/Users/Joana Magalhães/Documents/GitHub/endless-ql/ForcePush/src/parser/antlr\Grammar.g4 by ANTLR 4.7
package parser.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Grammar extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NEWLINE=1, WHITESPACE=2, FORM=3, BOOL=4, STR=5, INT=6, DATE=7, DECIMAL=8, 
		MONEY=9, ASSIGN=10, LPAREN=11, RPAREN=12, LCURLYBRAKET=13, RCURLYBRAKET=14, 
		IF=15, ELSE=16, IFELSE=17, PLUS=18, MINUS=19, ASTERISK=20, DIVISION=21, 
		LESS=22, HIGHER=23, EQUALHIGHER=24, EQUALLESS=25, DIFF=26, EQUAL=27, AND=28, 
		OR=29, NOT=30, VAR=31, LABEL=32;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"NEWLINE", "WHITESPACE", "FORM", "BOOL", "STR", "INT", "DATE", "DECIMAL", 
		"MONEY", "ASSIGN", "LPAREN", "RPAREN", "LCURLYBRAKET", "RCURLYBRAKET", 
		"IF", "ELSE", "IFELSE", "PLUS", "MINUS", "ASTERISK", "DIVISION", "LESS", 
		"HIGHER", "EQUALHIGHER", "EQUALLESS", "DIFF", "EQUAL", "AND", "OR", "NOT", 
		"VAR", "LABEL"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, "'form'", "'boolean'", "'string'", "'integer'", "'date'", 
		"'decimal'", null, "':'", "'('", "')'", "'{'", "'}'", "'if'", "'else'", 
		"'ifelse'", "'+'", "'-'", "'*'", "'/'", "'<'", "'>'", "'>='", "'<='", 
		"'!='", "'=='", "'&&'", "'||'", "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "NEWLINE", "WHITESPACE", "FORM", "BOOL", "STR", "INT", "DATE", "DECIMAL", 
		"MONEY", "ASSIGN", "LPAREN", "RPAREN", "LCURLYBRAKET", "RCURLYBRAKET", 
		"IF", "ELSE", "IFELSE", "PLUS", "MINUS", "ASTERISK", "DIVISION", "LESS", 
		"HIGHER", "EQUALHIGHER", "EQUALLESS", "DIFF", "EQUAL", "AND", "OR", "NOT", 
		"VAR", "LABEL"
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


	public Grammar(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\"\u00d3\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\2\5\2G\n\2\3\3\6\3J\n\3\r\3\16\3K\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0084\n\n\3"+
		"\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37"+
		"\3 \7 \u00c0\n \f \16 \u00c3\13 \3 \3 \7 \u00c7\n \f \16 \u00ca\13 \3"+
		"!\3!\6!\u00ce\n!\r!\16!\u00cf\3!\3!\2\2\"\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"\3\2\b\4\2\f\ftt\3\2"+
		"\13\13\3\2aa\3\2c|\6\2\62;C\\aac|\6\2\13\13\62;C\\c|\2\u00d8\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\2A\3\2\2\2\3F\3\2\2\2\5I\3\2\2\2\7M\3\2\2\2\tR\3\2\2"+
		"\2\13Z\3\2\2\2\ra\3\2\2\2\17i\3\2\2\2\21n\3\2\2\2\23\u0083\3\2\2\2\25"+
		"\u0085\3\2\2\2\27\u0087\3\2\2\2\31\u0089\3\2\2\2\33\u008b\3\2\2\2\35\u008d"+
		"\3\2\2\2\37\u008f\3\2\2\2!\u0092\3\2\2\2#\u0097\3\2\2\2%\u009e\3\2\2\2"+
		"\'\u00a0\3\2\2\2)\u00a2\3\2\2\2+\u00a4\3\2\2\2-\u00a6\3\2\2\2/\u00a8\3"+
		"\2\2\2\61\u00aa\3\2\2\2\63\u00ad\3\2\2\2\65\u00b0\3\2\2\2\67\u00b3\3\2"+
		"\2\29\u00b6\3\2\2\2;\u00b9\3\2\2\2=\u00bc\3\2\2\2?\u00c1\3\2\2\2A\u00cb"+
		"\3\2\2\2CD\7\17\2\2DG\7\f\2\2EG\t\2\2\2FC\3\2\2\2FE\3\2\2\2G\4\3\2\2\2"+
		"HJ\t\3\2\2IH\3\2\2\2JK\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\6\3\2\2\2MN\7h\2\2"+
		"NO\7q\2\2OP\7t\2\2PQ\7o\2\2Q\b\3\2\2\2RS\7d\2\2ST\7q\2\2TU\7q\2\2UV\7"+
		"n\2\2VW\7g\2\2WX\7c\2\2XY\7p\2\2Y\n\3\2\2\2Z[\7u\2\2[\\\7v\2\2\\]\7t\2"+
		"\2]^\7k\2\2^_\7p\2\2_`\7i\2\2`\f\3\2\2\2ab\7k\2\2bc\7p\2\2cd\7v\2\2de"+
		"\7g\2\2ef\7i\2\2fg\7g\2\2gh\7t\2\2h\16\3\2\2\2ij\7f\2\2jk\7c\2\2kl\7v"+
		"\2\2lm\7g\2\2m\20\3\2\2\2no\7f\2\2op\7g\2\2pq\7e\2\2qr\7k\2\2rs\7o\2\2"+
		"st\7c\2\2tu\7n\2\2u\22\3\2\2\2vw\7o\2\2wx\7q\2\2xy\7p\2\2yz\7g\2\2z\u0084"+
		"\7{\2\2{|\7e\2\2|}\7w\2\2}~\7t\2\2~\177\7t\2\2\177\u0080\7g\2\2\u0080"+
		"\u0081\7p\2\2\u0081\u0082\7e\2\2\u0082\u0084\7{\2\2\u0083v\3\2\2\2\u0083"+
		"{\3\2\2\2\u0084\24\3\2\2\2\u0085\u0086\7<\2\2\u0086\26\3\2\2\2\u0087\u0088"+
		"\7*\2\2\u0088\30\3\2\2\2\u0089\u008a\7+\2\2\u008a\32\3\2\2\2\u008b\u008c"+
		"\7}\2\2\u008c\34\3\2\2\2\u008d\u008e\7\177\2\2\u008e\36\3\2\2\2\u008f"+
		"\u0090\7k\2\2\u0090\u0091\7h\2\2\u0091 \3\2\2\2\u0092\u0093\7g\2\2\u0093"+
		"\u0094\7n\2\2\u0094\u0095\7u\2\2\u0095\u0096\7g\2\2\u0096\"\3\2\2\2\u0097"+
		"\u0098\7k\2\2\u0098\u0099\7h\2\2\u0099\u009a\7g\2\2\u009a\u009b\7n\2\2"+
		"\u009b\u009c\7u\2\2\u009c\u009d\7g\2\2\u009d$\3\2\2\2\u009e\u009f\7-\2"+
		"\2\u009f&\3\2\2\2\u00a0\u00a1\7/\2\2\u00a1(\3\2\2\2\u00a2\u00a3\7,\2\2"+
		"\u00a3*\3\2\2\2\u00a4\u00a5\7\61\2\2\u00a5,\3\2\2\2\u00a6\u00a7\7>\2\2"+
		"\u00a7.\3\2\2\2\u00a8\u00a9\7@\2\2\u00a9\60\3\2\2\2\u00aa\u00ab\7@\2\2"+
		"\u00ab\u00ac\7?\2\2\u00ac\62\3\2\2\2\u00ad\u00ae\7>\2\2\u00ae\u00af\7"+
		"?\2\2\u00af\64\3\2\2\2\u00b0\u00b1\7#\2\2\u00b1\u00b2\7?\2\2\u00b2\66"+
		"\3\2\2\2\u00b3\u00b4\7?\2\2\u00b4\u00b5\7?\2\2\u00b58\3\2\2\2\u00b6\u00b7"+
		"\7(\2\2\u00b7\u00b8\7(\2\2\u00b8:\3\2\2\2\u00b9\u00ba\7~\2\2\u00ba\u00bb"+
		"\7~\2\2\u00bb<\3\2\2\2\u00bc\u00bd\7#\2\2\u00bd>\3\2\2\2\u00be\u00c0\t"+
		"\4\2\2\u00bf\u00be\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1"+
		"\u00c2\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00c8\t\5"+
		"\2\2\u00c5\u00c7\t\6\2\2\u00c6\u00c5\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8"+
		"\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9@\3\2\2\2\u00ca\u00c8\3\2\2\2"+
		"\u00cb\u00cd\7$\2\2\u00cc\u00ce\t\7\2\2\u00cd\u00cc\3\2\2\2\u00ce\u00cf"+
		"\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00d2\7$\2\2\u00d2B\3\2\2\2\t\2FK\u0083\u00c1\u00c8\u00cf\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}