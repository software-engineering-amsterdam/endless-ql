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
		T__0=1, T__1=2, CHARACTERS=3, NUMBERS=4, CURLY_BRACKET_OPEN=5, CURLY_BRACKET_CLOSE=6, 
		BRACKET_OPEN=7, BRACKET_CLOSE=8, QUESTION_IDENTIFIER=9, QUESTION_VARIABLE_SEPERATOR=10, 
		QUESTION_ANSWER_SEPERATOR=11, QUESTION_ANSWER=12, WHITESPACE=13, NEWLINE=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "CHARACTERS", "NUMBERS", "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", 
		"BRACKET_OPEN", "BRACKET_CLOSE", "QUESTION_IDENTIFIER", "QUESTION_VARIABLE_SEPERATOR", 
		"QUESTION_ANSWER_SEPERATOR", "QUESTION_ANSWER", "WHITESPACE", "NEWLINE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'if'", null, null, "'{'", "'}'", "'('", "')'", null, 
		"':'", "'='", null, "' '", "'\n'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "CHARACTERS", "NUMBERS", "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", 
		"BRACKET_OPEN", "BRACKET_CLOSE", "QUESTION_IDENTIFIER", "QUESTION_VARIABLE_SEPERATOR", 
		"QUESTION_ANSWER_SEPERATOR", "QUESTION_ANSWER", "WHITESPACE", "NEWLINE"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20d\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\4\6\4)\n\4\r\4\16\4*\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n"+
		"\6\n8\n\n\r\n\16\n9\3\n\3\n\3\n\6\n?\n\n\r\n\16\n@\6\nC\n\n\r\n\16\nD"+
		"\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\6\rO\n\r\r\r\16\rP\3\r\3\r\3\r\3\r"+
		"\6\rW\n\r\r\r\16\rX\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\2"+
		"\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\3\2\3\4\2C\\c|\2l\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3\37\3\2"+
		"\2\2\5$\3\2\2\2\7(\3\2\2\2\t,\3\2\2\2\13.\3\2\2\2\r\60\3\2\2\2\17\62\3"+
		"\2\2\2\21\64\3\2\2\2\23\67\3\2\2\2\25I\3\2\2\2\27K\3\2\2\2\31N\3\2\2\2"+
		"\33\\\3\2\2\2\35`\3\2\2\2\37 \7h\2\2 !\7q\2\2!\"\7t\2\2\"#\7o\2\2#\4\3"+
		"\2\2\2$%\7k\2\2%&\7h\2\2&\6\3\2\2\2\')\t\2\2\2(\'\3\2\2\2)*\3\2\2\2*("+
		"\3\2\2\2*+\3\2\2\2+\b\3\2\2\2,-\4\62;\2-\n\3\2\2\2./\7}\2\2/\f\3\2\2\2"+
		"\60\61\7\177\2\2\61\16\3\2\2\2\62\63\7*\2\2\63\20\3\2\2\2\64\65\7+\2\2"+
		"\65\22\3\2\2\2\668\7$\2\2\67\66\3\2\2\289\3\2\2\29\67\3\2\2\29:\3\2\2"+
		"\2:B\3\2\2\2;?\5\7\4\2<?\5\t\5\2=?\7\"\2\2>;\3\2\2\2><\3\2\2\2>=\3\2\2"+
		"\2?@\3\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3\2\2\2B>\3\2\2\2CD\3\2\2\2DB\3\2\2"+
		"\2DE\3\2\2\2EF\3\2\2\2FG\7A\2\2GH\7$\2\2H\24\3\2\2\2IJ\7<\2\2J\26\3\2"+
		"\2\2KL\7?\2\2L\30\3\2\2\2MO\7*\2\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2"+
		"\2\2QV\3\2\2\2RW\5\7\4\2ST\7\"\2\2TU\7/\2\2UW\7\"\2\2VR\3\2\2\2VS\3\2"+
		"\2\2WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z[\7+\2\2[\32\3\2\2\2\\]\7"+
		"\"\2\2]^\3\2\2\2^_\b\16\2\2_\34\3\2\2\2`a\7\f\2\2ab\3\2\2\2bc\b\17\2\2"+
		"c\36\3\2\2\2\13\2*9>@DPVX\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}