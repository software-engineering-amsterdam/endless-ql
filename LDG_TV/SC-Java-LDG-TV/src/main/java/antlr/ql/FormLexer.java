// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/SC-Java-LDG-TV/src/main/java/antlr\Form.g4 by ANTLR 4.7
package antlr.ql;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, NUMBERS=6, CURLY_BRACKET_OPEN=7, 
		CURLY_BRACKET_CLOSE=8, BRACKET_OPEN=9, BRACKET_CLOSE=10, OR=11, AND=12, 
		PLUS=13, MINUS=14, TIMES=15, DIV=16, GT=17, GTOEQ=18, STOEQ=19, ST=20, 
		EQ=21, NEQ=22, QUESTION_LABEL=23, QUESTION_VARIABLE_SEPERATOR=24, QUESTION_VARIABLE_VALUE_SEPERATOR=25, 
		IF=26, WHITESPACE=27, NEWLINE=28, CHARACTERS=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "LOWERCASE", "UPPERCASE", "NUMBERS", 
		"CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", "BRACKET_OPEN", "BRACKET_CLOSE", 
		"OR", "AND", "PLUS", "MINUS", "TIMES", "DIV", "GT", "GTOEQ", "STOEQ", 
		"ST", "EQ", "NEQ", "QUESTION_LABEL", "QUESTION_VARIABLE_SEPERATOR", "QUESTION_VARIABLE_VALUE_SEPERATOR", 
		"IF", "WHITESPACE", "NEWLINE", "CHARACTERS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'boolean'", "'money'", "'string'", "'!'", null, "'{'", 
		"'}'", "'('", "')'", "'||'", "'&&'", "'+'", "'-'", "'*'", "'/'", "'>'", 
		"'>='", "'=<'", "'<'", "'=='", "'!='", null, "':'", "'='", "'if'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "NUMBERS", "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", 
		"BRACKET_OPEN", "BRACKET_CLOSE", "OR", "AND", "PLUS", "MINUS", "TIMES", 
		"DIV", "GT", "GTOEQ", "STOEQ", "ST", "EQ", "NEQ", "QUESTION_LABEL", "QUESTION_VARIABLE_SEPERATOR", 
		"QUESTION_VARIABLE_VALUE_SEPERATOR", "IF", "WHITESPACE", "NEWLINE", "CHARACTERS"
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


	public FormLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Form.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\37\u00bd\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\6\tc"+
		"\n\t\r\t\16\td\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3"+
		"\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\6\32\u008e"+
		"\n\32\r\32\16\32\u008f\3\32\3\32\3\32\6\32\u0095\n\32\r\32\16\32\u0096"+
		"\6\32\u0099\n\32\r\32\16\32\u009a\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3"+
		"\35\3\35\3\36\6\36\u00a7\n\36\r\36\16\36\u00a8\3\36\3\36\3\37\5\37\u00ae"+
		"\n\37\3\37\3\37\6\37\u00b2\n\37\r\37\16\37\u00b3\3\37\3\37\3 \3 \6 \u00ba"+
		"\n \r \16 \u00bb\2\2!\3\3\5\4\7\5\t\6\13\7\r\2\17\2\21\b\23\t\25\n\27"+
		"\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63\31"+
		"\65\32\67\339\34;\35=\36?\37\3\2\6\3\2c|\3\2C\\\5\2\"\"<<AA\5\2\13\f\17"+
		"\17\"\"\2\u00c6\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\3A\3\2\2\2\5F\3\2\2\2\7N\3\2\2\2\tT\3\2\2\2\13[\3\2"+
		"\2\2\r]\3\2\2\2\17_\3\2\2\2\21b\3\2\2\2\23f\3\2\2\2\25h\3\2\2\2\27j\3"+
		"\2\2\2\31l\3\2\2\2\33n\3\2\2\2\35q\3\2\2\2\37t\3\2\2\2!v\3\2\2\2#x\3\2"+
		"\2\2%z\3\2\2\2\'|\3\2\2\2)~\3\2\2\2+\u0081\3\2\2\2-\u0084\3\2\2\2/\u0086"+
		"\3\2\2\2\61\u0089\3\2\2\2\63\u008d\3\2\2\2\65\u009e\3\2\2\2\67\u00a0\3"+
		"\2\2\29\u00a2\3\2\2\2;\u00a6\3\2\2\2=\u00b1\3\2\2\2?\u00b9\3\2\2\2AB\7"+
		"h\2\2BC\7q\2\2CD\7t\2\2DE\7o\2\2E\4\3\2\2\2FG\7d\2\2GH\7q\2\2HI\7q\2\2"+
		"IJ\7n\2\2JK\7g\2\2KL\7c\2\2LM\7p\2\2M\6\3\2\2\2NO\7o\2\2OP\7q\2\2PQ\7"+
		"p\2\2QR\7g\2\2RS\7{\2\2S\b\3\2\2\2TU\7u\2\2UV\7v\2\2VW\7t\2\2WX\7k\2\2"+
		"XY\7p\2\2YZ\7i\2\2Z\n\3\2\2\2[\\\7#\2\2\\\f\3\2\2\2]^\t\2\2\2^\16\3\2"+
		"\2\2_`\t\3\2\2`\20\3\2\2\2ac\4\62;\2ba\3\2\2\2cd\3\2\2\2db\3\2\2\2de\3"+
		"\2\2\2e\22\3\2\2\2fg\7}\2\2g\24\3\2\2\2hi\7\177\2\2i\26\3\2\2\2jk\7*\2"+
		"\2k\30\3\2\2\2lm\7+\2\2m\32\3\2\2\2no\7~\2\2op\7~\2\2p\34\3\2\2\2qr\7"+
		"(\2\2rs\7(\2\2s\36\3\2\2\2tu\7-\2\2u \3\2\2\2vw\7/\2\2w\"\3\2\2\2xy\7"+
		",\2\2y$\3\2\2\2z{\7\61\2\2{&\3\2\2\2|}\7@\2\2}(\3\2\2\2~\177\7@\2\2\177"+
		"\u0080\7?\2\2\u0080*\3\2\2\2\u0081\u0082\7?\2\2\u0082\u0083\7>\2\2\u0083"+
		",\3\2\2\2\u0084\u0085\7>\2\2\u0085.\3\2\2\2\u0086\u0087\7?\2\2\u0087\u0088"+
		"\7?\2\2\u0088\60\3\2\2\2\u0089\u008a\7#\2\2\u008a\u008b\7?\2\2\u008b\62"+
		"\3\2\2\2\u008c\u008e\7$\2\2\u008d\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f"+
		"\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0098\3\2\2\2\u0091\u0095\5?"+
		" \2\u0092\u0095\5\21\t\2\u0093\u0095\t\4\2\2\u0094\u0091\3\2\2\2\u0094"+
		"\u0092\3\2\2\2\u0094\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0094\3\2"+
		"\2\2\u0096\u0097\3\2\2\2\u0097\u0099\3\2\2\2\u0098\u0094\3\2\2\2\u0099"+
		"\u009a\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c\3\2"+
		"\2\2\u009c\u009d\7$\2\2\u009d\64\3\2\2\2\u009e\u009f\7<\2\2\u009f\66\3"+
		"\2\2\2\u00a0\u00a1\7?\2\2\u00a18\3\2\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4"+
		"\7h\2\2\u00a4:\3\2\2\2\u00a5\u00a7\t\5\2\2\u00a6\u00a5\3\2\2\2\u00a7\u00a8"+
		"\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\u00ab\b\36\2\2\u00ab<\3\2\2\2\u00ac\u00ae\7\17\2\2\u00ad\u00ac\3\2\2"+
		"\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b2\7\f\2\2\u00b0\u00b2"+
		"\7\17\2\2\u00b1\u00ad\3\2\2\2\u00b1\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2"+
		"\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6"+
		"\b\37\2\2\u00b6>\3\2\2\2\u00b7\u00ba\5\r\7\2\u00b8\u00ba\5\17\b\2\u00b9"+
		"\u00b7\3\2\2\2\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2"+
		"\2\2\u00bb\u00bc\3\2\2\2\u00bc@\3\2\2\2\16\2d\u008f\u0094\u0096\u009a"+
		"\u00a8\u00ad\u00b1\u00b3\u00b9\u00bb\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}