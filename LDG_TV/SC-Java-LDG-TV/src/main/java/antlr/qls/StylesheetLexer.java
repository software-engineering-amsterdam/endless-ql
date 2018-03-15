// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/SC-Java-LDG-TV/src/main/java/antlr/qls\Stylesheet.g4 by ANTLR 4.7
package antlr.qls;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StylesheetLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, NUMBERS=13, STRING=14, CURLY_BRACKET_OPEN=15, 
		CURLY_BRACKET_CLOSE=16, BRACKET_OPEN=17, BRACKET_CLOSE=18, WHITESPACE=19, 
		NEWLINE=20, CHARACTERS=21, SPECIAL_CHAR=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "LOWERCASE", "UPPERCASE", "NUMBERS", "STRING", 
		"CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", "BRACKET_OPEN", "BRACKET_CLOSE", 
		"WHITESPACE", "NEWLINE", "CHARACTERS", "SPECIAL_CHAR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'stylesheet'", "'page'", "'section'", "'question'", "'widget'", 
		"'default'", "'checkbox'", "'radio'", "','", "'spinbox'", "'money'", "':'", 
		null, null, "'{'", "'}'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "NUMBERS", "STRING", "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", 
		"BRACKET_OPEN", "BRACKET_CLOSE", "WHITESPACE", "NEWLINE", "CHARACTERS", 
		"SPECIAL_CHAR"
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


	public StylesheetLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Stylesheet.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u00b8\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3"+
		"\17\3\17\3\20\6\20\u008a\n\20\r\20\16\20\u008b\3\21\3\21\3\21\6\21\u0091"+
		"\n\21\r\21\16\21\u0092\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3"+
		"\25\3\26\6\26\u00a0\n\26\r\26\16\26\u00a1\3\26\3\26\3\27\5\27\u00a7\n"+
		"\27\3\27\3\27\6\27\u00ab\n\27\r\27\16\27\u00ac\3\27\3\27\3\30\3\30\6\30"+
		"\u00b3\n\30\r\30\16\30\u00b4\3\31\3\31\2\2\32\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\2\35\2\37\17!\20#\21%\22\'\23)\24"+
		"+\25-\26/\27\61\30\3\2\5\3\2c|\3\2C\\\5\2\13\f\17\17\"\"\2\u00be\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5>\3"+
		"\2\2\2\7C\3\2\2\2\tK\3\2\2\2\13T\3\2\2\2\r[\3\2\2\2\17c\3\2\2\2\21l\3"+
		"\2\2\2\23r\3\2\2\2\25t\3\2\2\2\27|\3\2\2\2\31\u0082\3\2\2\2\33\u0084\3"+
		"\2\2\2\35\u0086\3\2\2\2\37\u0089\3\2\2\2!\u008d\3\2\2\2#\u0096\3\2\2\2"+
		"%\u0098\3\2\2\2\'\u009a\3\2\2\2)\u009c\3\2\2\2+\u009f\3\2\2\2-\u00aa\3"+
		"\2\2\2/\u00b2\3\2\2\2\61\u00b6\3\2\2\2\63\64\7u\2\2\64\65\7v\2\2\65\66"+
		"\7{\2\2\66\67\7n\2\2\678\7g\2\289\7u\2\29:\7j\2\2:;\7g\2\2;<\7g\2\2<="+
		"\7v\2\2=\4\3\2\2\2>?\7r\2\2?@\7c\2\2@A\7i\2\2AB\7g\2\2B\6\3\2\2\2CD\7"+
		"u\2\2DE\7g\2\2EF\7e\2\2FG\7v\2\2GH\7k\2\2HI\7q\2\2IJ\7p\2\2J\b\3\2\2\2"+
		"KL\7s\2\2LM\7w\2\2MN\7g\2\2NO\7u\2\2OP\7v\2\2PQ\7k\2\2QR\7q\2\2RS\7p\2"+
		"\2S\n\3\2\2\2TU\7y\2\2UV\7k\2\2VW\7f\2\2WX\7i\2\2XY\7g\2\2YZ\7v\2\2Z\f"+
		"\3\2\2\2[\\\7f\2\2\\]\7g\2\2]^\7h\2\2^_\7c\2\2_`\7w\2\2`a\7n\2\2ab\7v"+
		"\2\2b\16\3\2\2\2cd\7e\2\2de\7j\2\2ef\7g\2\2fg\7e\2\2gh\7m\2\2hi\7d\2\2"+
		"ij\7q\2\2jk\7z\2\2k\20\3\2\2\2lm\7t\2\2mn\7c\2\2no\7f\2\2op\7k\2\2pq\7"+
		"q\2\2q\22\3\2\2\2rs\7.\2\2s\24\3\2\2\2tu\7u\2\2uv\7r\2\2vw\7k\2\2wx\7"+
		"p\2\2xy\7d\2\2yz\7q\2\2z{\7z\2\2{\26\3\2\2\2|}\7o\2\2}~\7q\2\2~\177\7"+
		"p\2\2\177\u0080\7g\2\2\u0080\u0081\7{\2\2\u0081\30\3\2\2\2\u0082\u0083"+
		"\7<\2\2\u0083\32\3\2\2\2\u0084\u0085\t\2\2\2\u0085\34\3\2\2\2\u0086\u0087"+
		"\t\3\2\2\u0087\36\3\2\2\2\u0088\u008a\4\62;\2\u0089\u0088\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c \3\2\2\2"+
		"\u008d\u0090\7$\2\2\u008e\u0091\5/\30\2\u008f\u0091\7\"\2\2\u0090\u008e"+
		"\3\2\2\2\u0090\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0090\3\2\2\2\u0092"+
		"\u0093\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\7$\2\2\u0095\"\3\2\2\2"+
		"\u0096\u0097\7}\2\2\u0097$\3\2\2\2\u0098\u0099\7\177\2\2\u0099&\3\2\2"+
		"\2\u009a\u009b\7*\2\2\u009b(\3\2\2\2\u009c\u009d\7+\2\2\u009d*\3\2\2\2"+
		"\u009e\u00a0\t\4\2\2\u009f\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u009f"+
		"\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\b\26\2\2"+
		"\u00a4,\3\2\2\2\u00a5\u00a7\7\17\2\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7\3"+
		"\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00ab\7\f\2\2\u00a9\u00ab\7\17\2\2\u00aa"+
		"\u00a6\3\2\2\2\u00aa\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00aa\3\2"+
		"\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\b\27\2\2\u00af"+
		".\3\2\2\2\u00b0\u00b3\5\33\16\2\u00b1\u00b3\5\35\17\2\u00b2\u00b0\3\2"+
		"\2\2\u00b2\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5\60\3\2\2\2\u00b6\u00b7\7%\2\2\u00b7\62\3\2\2\2\f"+
		"\2\u008b\u0090\u0092\u00a1\u00a6\u00aa\u00ac\u00b2\u00b4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}