// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/src/main/antlr4\FormParser.g4 by ANTLR 4.7
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormParserLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, CURLY_BRACKET_OPEN=2, CURLY_BRACKET_CLOSE=3, BRACKET_OPEN=4, BRACKET_CLOSE=5, 
		PLUS=6, MINUS=7, TIMES=8, DIV=9, QUESTION_IDENTIFIER=10, QUESTION_VARIABLE_SEPERATOR=11, 
		QUESTION_ANSWER_SEPERATOR=12, QUESTION_ANSWER=13, IF=14, WHITESPACE=15, 
		NEWLINE=16, CHARACTERS=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "LOWERCASE", "UPPERCASE", "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", 
		"BRACKET_OPEN", "BRACKET_CLOSE", "PLUS", "MINUS", "TIMES", "DIV", "QUESTION_IDENTIFIER", 
		"QUESTION_VARIABLE_SEPERATOR", "QUESTION_ANSWER_SEPERATOR", "QUESTION_ANSWER", 
		"IF", "WHITESPACE", "NEWLINE", "CHARACTERS", "NUMBERS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'('", "')'", "'+'", "'-'", "'*'", "'/'", 
		null, "':'", "'='", null, "'if'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", "BRACKET_OPEN", 
		"BRACKET_CLOSE", "PLUS", "MINUS", "TIMES", "DIV", "QUESTION_IDENTIFIER", 
		"QUESTION_VARIABLE_SEPERATOR", "QUESTION_ANSWER_SEPERATOR", "QUESTION_ANSWER", 
		"IF", "WHITESPACE", "NEWLINE", "CHARACTERS"
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


	public FormParserLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FormParser.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23\u0090\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3"+
		"\r\6\rF\n\r\r\r\16\rG\3\r\3\r\3\r\6\rM\n\r\r\r\16\rN\6\rQ\n\r\r\r\16\r"+
		"R\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\6\20c\n\20\r\20\16\20d\3\20\3\20\3\21\3\21\3\21\3\22\6\22m\n\22\r\22"+
		"\16\22n\3\22\3\22\3\23\5\23t\n\23\3\23\3\23\6\23x\n\23\r\23\16\23y\3\23"+
		"\3\23\3\24\3\24\6\24\u0080\n\24\r\24\16\24\u0081\3\25\6\25\u0085\n\25"+
		"\r\25\16\25\u0086\3\25\3\25\6\25\u008b\n\25\r\25\16\25\u008c\5\25\u008f"+
		"\n\25\2\2\26\3\3\5\2\7\2\t\4\13\5\r\6\17\7\21\b\23\t\25\n\27\13\31\f\33"+
		"\r\35\16\37\17!\20#\21%\22\'\23)\2\3\2\6\3\2c|\3\2C\\\5\2\"\"<<AA\5\2"+
		"\13\f\17\17\"\"\2\u00a1\2\3\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\3+\3\2\2\2\5\60\3\2\2\2\7\62\3\2\2\2\t\64\3"+
		"\2\2\2\13\66\3\2\2\2\r8\3\2\2\2\17:\3\2\2\2\21<\3\2\2\2\23>\3\2\2\2\25"+
		"@\3\2\2\2\27B\3\2\2\2\31E\3\2\2\2\33V\3\2\2\2\35X\3\2\2\2\37Z\3\2\2\2"+
		"!h\3\2\2\2#l\3\2\2\2%w\3\2\2\2\'\177\3\2\2\2)\u0084\3\2\2\2+,\7h\2\2,"+
		"-\7q\2\2-.\7t\2\2./\7o\2\2/\4\3\2\2\2\60\61\t\2\2\2\61\6\3\2\2\2\62\63"+
		"\t\3\2\2\63\b\3\2\2\2\64\65\7}\2\2\65\n\3\2\2\2\66\67\7\177\2\2\67\f\3"+
		"\2\2\289\7*\2\29\16\3\2\2\2:;\7+\2\2;\20\3\2\2\2<=\7-\2\2=\22\3\2\2\2"+
		">?\7/\2\2?\24\3\2\2\2@A\7,\2\2A\26\3\2\2\2BC\7\61\2\2C\30\3\2\2\2DF\7"+
		"$\2\2ED\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2HP\3\2\2\2IM\5\'\24\2JM\5"+
		")\25\2KM\t\4\2\2LI\3\2\2\2LJ\3\2\2\2LK\3\2\2\2MN\3\2\2\2NL\3\2\2\2NO\3"+
		"\2\2\2OQ\3\2\2\2PL\3\2\2\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2ST\3\2\2\2TU\7"+
		"$\2\2U\32\3\2\2\2VW\7<\2\2W\34\3\2\2\2XY\7?\2\2Y\36\3\2\2\2Zb\5\r\7\2"+
		"[c\5\'\24\2\\c\5)\25\2]c\5\21\t\2^c\5\23\n\2_c\5\25\13\2`c\5\27\f\2ac"+
		"\5#\22\2b[\3\2\2\2b\\\3\2\2\2b]\3\2\2\2b^\3\2\2\2b_\3\2\2\2b`\3\2\2\2"+
		"ba\3\2\2\2cd\3\2\2\2db\3\2\2\2de\3\2\2\2ef\3\2\2\2fg\5\17\b\2g \3\2\2"+
		"\2hi\7k\2\2ij\7h\2\2j\"\3\2\2\2km\t\5\2\2lk\3\2\2\2mn\3\2\2\2nl\3\2\2"+
		"\2no\3\2\2\2op\3\2\2\2pq\b\22\2\2q$\3\2\2\2rt\7\17\2\2sr\3\2\2\2st\3\2"+
		"\2\2tu\3\2\2\2ux\7\f\2\2vx\7\17\2\2ws\3\2\2\2wv\3\2\2\2xy\3\2\2\2yw\3"+
		"\2\2\2yz\3\2\2\2z{\3\2\2\2{|\b\23\2\2|&\3\2\2\2}\u0080\5\5\3\2~\u0080"+
		"\5\7\4\2\177}\3\2\2\2\177~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\177\3\2\2"+
		"\2\u0081\u0082\3\2\2\2\u0082(\3\2\2\2\u0083\u0085\4\62;\2\u0084\u0083"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\u008e\3\2\2\2\u0088\u008a\7\60\2\2\u0089\u008b\4\62;\2\u008a\u0089\3"+
		"\2\2\2\u008b\u008c\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\u008f\3\2\2\2\u008e\u0088\3\2\2\2\u008e\u008f\3\2\2\2\u008f*\3\2\2\2"+
		"\22\2GLNRbdnswy\177\u0081\u0086\u008c\u008e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}