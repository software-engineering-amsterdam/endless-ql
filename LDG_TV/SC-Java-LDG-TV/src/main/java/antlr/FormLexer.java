// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/SC-Java-LDG-TV/src/main/java/antlr\Form.g4 by ANTLR 4.7
package antlr;
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
		T__0=1, T__1=2, T__2=3, T__3=4, CURLY_BRACKET_OPEN=5, CURLY_BRACKET_CLOSE=6, 
		BRACKET_OPEN=7, BRACKET_CLOSE=8, PLUS=9, MINUS=10, TIMES=11, DIV=12, QUESTION_LABEL=13, 
		QUESTION_VARIABLE_SEPERATOR=14, QUESTION_VARIABLE_VALUE_SEPERATOR=15, 
		IF=16, WHITESPACE=17, NEWLINE=18, CHARACTERS=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "LOWERCASE", "UPPERCASE", "CURLY_BRACKET_OPEN", 
		"CURLY_BRACKET_CLOSE", "BRACKET_OPEN", "BRACKET_CLOSE", "PLUS", "MINUS", 
		"TIMES", "DIV", "QUESTION_LABEL", "QUESTION_VARIABLE_SEPERATOR", "QUESTION_VARIABLE_VALUE_SEPERATOR", 
		"IF", "WHITESPACE", "NEWLINE", "CHARACTERS", "NUMBERS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'boolean'", "'money'", "'string'", "'{'", "'}'", "'('", 
		"')'", "'+'", "'-'", "'*'", "'/'", null, "':'", "'='", "'if'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", 
		"BRACKET_OPEN", "BRACKET_CLOSE", "PLUS", "MINUS", "TIMES", "DIV", "QUESTION_LABEL", 
		"QUESTION_VARIABLE_SEPERATOR", "QUESTION_VARIABLE_VALUE_SEPERATOR", "IF", 
		"WHITESPACE", "NEWLINE", "CHARACTERS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25\u0093\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\6\20_\n\20\r\20\16\20`\3\20"+
		"\3\20\3\20\6\20f\n\20\r\20\16\20g\6\20j\n\20\r\20\16\20k\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\23\3\24\6\24x\n\24\r\24\16\24y\3\24\3\24\3"+
		"\25\5\25\177\n\25\3\25\3\25\6\25\u0083\n\25\r\25\16\25\u0084\3\25\3\25"+
		"\3\26\3\26\6\26\u008b\n\26\r\26\16\26\u008c\3\27\6\27\u0090\n\27\r\27"+
		"\16\27\u0091\2\2\30\3\3\5\4\7\5\t\6\13\2\r\2\17\7\21\b\23\t\25\n\27\13"+
		"\31\f\33\r\35\16\37\17!\20#\21%\22\'\23)\24+\25-\2\3\2\6\3\2c|\3\2C\\"+
		"\5\2\"\"<<AA\5\2\13\f\17\17\"\"\2\u009b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3/\3\2"+
		"\2\2\5\64\3\2\2\2\7<\3\2\2\2\tB\3\2\2\2\13I\3\2\2\2\rK\3\2\2\2\17M\3\2"+
		"\2\2\21O\3\2\2\2\23Q\3\2\2\2\25S\3\2\2\2\27U\3\2\2\2\31W\3\2\2\2\33Y\3"+
		"\2\2\2\35[\3\2\2\2\37^\3\2\2\2!o\3\2\2\2#q\3\2\2\2%s\3\2\2\2\'w\3\2\2"+
		"\2)\u0082\3\2\2\2+\u008a\3\2\2\2-\u008f\3\2\2\2/\60\7h\2\2\60\61\7q\2"+
		"\2\61\62\7t\2\2\62\63\7o\2\2\63\4\3\2\2\2\64\65\7d\2\2\65\66\7q\2\2\66"+
		"\67\7q\2\2\678\7n\2\289\7g\2\29:\7c\2\2:;\7p\2\2;\6\3\2\2\2<=\7o\2\2="+
		">\7q\2\2>?\7p\2\2?@\7g\2\2@A\7{\2\2A\b\3\2\2\2BC\7u\2\2CD\7v\2\2DE\7t"+
		"\2\2EF\7k\2\2FG\7p\2\2GH\7i\2\2H\n\3\2\2\2IJ\t\2\2\2J\f\3\2\2\2KL\t\3"+
		"\2\2L\16\3\2\2\2MN\7}\2\2N\20\3\2\2\2OP\7\177\2\2P\22\3\2\2\2QR\7*\2\2"+
		"R\24\3\2\2\2ST\7+\2\2T\26\3\2\2\2UV\7-\2\2V\30\3\2\2\2WX\7/\2\2X\32\3"+
		"\2\2\2YZ\7,\2\2Z\34\3\2\2\2[\\\7\61\2\2\\\36\3\2\2\2]_\7$\2\2^]\3\2\2"+
		"\2_`\3\2\2\2`^\3\2\2\2`a\3\2\2\2ai\3\2\2\2bf\5+\26\2cf\5-\27\2df\t\4\2"+
		"\2eb\3\2\2\2ec\3\2\2\2ed\3\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2\2hj\3\2\2"+
		"\2ie\3\2\2\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2lm\3\2\2\2mn\7$\2\2n \3\2\2"+
		"\2op\7<\2\2p\"\3\2\2\2qr\7?\2\2r$\3\2\2\2st\7k\2\2tu\7h\2\2u&\3\2\2\2"+
		"vx\t\5\2\2wv\3\2\2\2xy\3\2\2\2yw\3\2\2\2yz\3\2\2\2z{\3\2\2\2{|\b\24\2"+
		"\2|(\3\2\2\2}\177\7\17\2\2~}\3\2\2\2~\177\3\2\2\2\177\u0080\3\2\2\2\u0080"+
		"\u0083\7\f\2\2\u0081\u0083\7\17\2\2\u0082~\3\2\2\2\u0082\u0081\3\2\2\2"+
		"\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086"+
		"\3\2\2\2\u0086\u0087\b\25\2\2\u0087*\3\2\2\2\u0088\u008b\5\13\6\2\u0089"+
		"\u008b\5\r\7\2\u008a\u0088\3\2\2\2\u008a\u0089\3\2\2\2\u008b\u008c\3\2"+
		"\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d,\3\2\2\2\u008e\u0090"+
		"\4\62;\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u008f\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092.\3\2\2\2\16\2`egky~\u0082\u0084\u008a\u008c\u0091"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}