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
		QUESTION_ANSWER_SEPERATOR=12, IF=13, WHITESPACE=14, NEWLINE=15, CHARACTERS=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "LOWERCASE", "UPPERCASE", "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", 
		"BRACKET_OPEN", "BRACKET_CLOSE", "PLUS", "MINUS", "TIMES", "DIV", "QUESTION_IDENTIFIER", 
		"QUESTION_VARIABLE_SEPERATOR", "QUESTION_ANSWER_SEPERATOR", "IF", "WHITESPACE", 
		"NEWLINE", "CHARACTERS", "NUMBERS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'('", "')'", "'+'", "'-'", "'*'", "'/'", 
		null, "':'", "'='", "'if'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", "BRACKET_OPEN", 
		"BRACKET_CLOSE", "PLUS", "MINUS", "TIMES", "DIV", "QUESTION_IDENTIFIER", 
		"QUESTION_VARIABLE_SEPERATOR", "QUESTION_ANSWER_SEPERATOR", "IF", "WHITESPACE", 
		"NEWLINE", "CHARACTERS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22\u0080\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5"+
		"\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\6\rD\n"+
		"\r\r\r\16\rE\3\r\3\r\3\r\6\rK\n\r\r\r\16\rL\6\rO\n\r\r\r\16\rP\3\r\3\r"+
		"\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\6\21]\n\21\r\21\16\21^\3\21\3"+
		"\21\3\22\5\22d\n\22\3\22\3\22\6\22h\n\22\r\22\16\22i\3\22\3\22\3\23\3"+
		"\23\6\23p\n\23\r\23\16\23q\3\24\6\24u\n\24\r\24\16\24v\3\24\3\24\6\24"+
		"{\n\24\r\24\16\24|\5\24\177\n\24\2\2\25\3\3\5\2\7\2\t\4\13\5\r\6\17\7"+
		"\21\b\23\t\25\n\27\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\2\3\2\6\3\2"+
		"c|\3\2C\\\5\2\"\"<<AA\5\2\13\f\17\17\"\"\2\u008a\2\3\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3)\3\2\2\2\5.\3\2\2\2\7\60\3\2\2"+
		"\2\t\62\3\2\2\2\13\64\3\2\2\2\r\66\3\2\2\2\178\3\2\2\2\21:\3\2\2\2\23"+
		"<\3\2\2\2\25>\3\2\2\2\27@\3\2\2\2\31C\3\2\2\2\33T\3\2\2\2\35V\3\2\2\2"+
		"\37X\3\2\2\2!\\\3\2\2\2#g\3\2\2\2%o\3\2\2\2\'t\3\2\2\2)*\7h\2\2*+\7q\2"+
		"\2+,\7t\2\2,-\7o\2\2-\4\3\2\2\2./\t\2\2\2/\6\3\2\2\2\60\61\t\3\2\2\61"+
		"\b\3\2\2\2\62\63\7}\2\2\63\n\3\2\2\2\64\65\7\177\2\2\65\f\3\2\2\2\66\67"+
		"\7*\2\2\67\16\3\2\2\289\7+\2\29\20\3\2\2\2:;\7-\2\2;\22\3\2\2\2<=\7/\2"+
		"\2=\24\3\2\2\2>?\7,\2\2?\26\3\2\2\2@A\7\61\2\2A\30\3\2\2\2BD\7$\2\2CB"+
		"\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2FN\3\2\2\2GK\5%\23\2HK\5\'\24\2"+
		"IK\t\4\2\2JG\3\2\2\2JH\3\2\2\2JI\3\2\2\2KL\3\2\2\2LJ\3\2\2\2LM\3\2\2\2"+
		"MO\3\2\2\2NJ\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RS\7$\2\2"+
		"S\32\3\2\2\2TU\7<\2\2U\34\3\2\2\2VW\7?\2\2W\36\3\2\2\2XY\7k\2\2YZ\7h\2"+
		"\2Z \3\2\2\2[]\t\5\2\2\\[\3\2\2\2]^\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_`\3\2"+
		"\2\2`a\b\21\2\2a\"\3\2\2\2bd\7\17\2\2cb\3\2\2\2cd\3\2\2\2de\3\2\2\2eh"+
		"\7\f\2\2fh\7\17\2\2gc\3\2\2\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2"+
		"jk\3\2\2\2kl\b\22\2\2l$\3\2\2\2mp\5\5\3\2np\5\7\4\2om\3\2\2\2on\3\2\2"+
		"\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2r&\3\2\2\2su\4\62;\2ts\3\2\2\2uv\3\2\2"+
		"\2vt\3\2\2\2vw\3\2\2\2w~\3\2\2\2xz\7\60\2\2y{\4\62;\2zy\3\2\2\2{|\3\2"+
		"\2\2|z\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~x\3\2\2\2~\177\3\2\2\2\177(\3\2"+
		"\2\2\20\2EJLP^cgioqv|~\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}