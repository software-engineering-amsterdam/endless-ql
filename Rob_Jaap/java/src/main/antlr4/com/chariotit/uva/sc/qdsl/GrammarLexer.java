// Generated from Grammar.g4 by ANTLR 4.7
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FORM=1, IF=2, BOOLEAN_TYPE=3, STRING_TYPE=4, INTEGER_TYPE=5, MONEY_TYPE=6, 
		BRACKET_L=7, BRACKET_R=8, PARENTH_L=9, PARENTH_R=10, MINUS=11, PLUS=12, 
		MULTIPLY=13, DIVIDE=14, COLON=15, EQ=16, NEQ=17, GTE=18, GT=19, LTE=20, 
		LT=21, NOT=22, NEWLINE=23, WHITESPACE=24, NUMBER=25, WORD=26, STRING=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"FORM", "IF", "BOOLEAN_TYPE", "STRING_TYPE", "INTEGER_TYPE", "MONEY_TYPE", 
		"BRACKET_L", "BRACKET_R", "PARENTH_L", "PARENTH_R", "MINUS", "PLUS", "MULTIPLY", 
		"DIVIDE", "COLON", "EQ", "NEQ", "GTE", "GT", "LTE", "LT", "NOT", "NEWLINE", 
		"WHITESPACE", "NUMBER", "WORD", "STRING"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'if'", "'boolean'", "'string'", "'integer'", "'money'", 
		"'{'", "'}'", "'('", "')'", "'-'", "'+'", "'*'", "'/'", "':'", "'=='", 
		"'!='", "'>='", "'>'", "'<='", "'<'", "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "FORM", "IF", "BOOLEAN_TYPE", "STRING_TYPE", "INTEGER_TYPE", "MONEY_TYPE", 
		"BRACKET_L", "BRACKET_R", "PARENTH_L", "PARENTH_R", "MINUS", "PLUS", "MULTIPLY", 
		"DIVIDE", "COLON", "EQ", "NEQ", "GTE", "GT", "LTE", "LT", "NOT", "NEWLINE", 
		"WHITESPACE", "NUMBER", "WORD", "STRING"
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


	public GrammarLexer(CharStream input) {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\35\u00aa\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3"+
		"\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\30\5\30\u0084\n\30\3\30\3\30\6\30\u0088\n\30\r\30\16\30\u0089"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\32\6\32\u0093\n\32\r\32\16\32\u0094\3"+
		"\33\6\33\u0098\n\33\r\33\16\33\u0099\3\33\7\33\u009d\n\33\f\33\16\33\u00a0"+
		"\13\33\3\34\3\34\7\34\u00a4\n\34\f\34\16\34\u00a7\13\34\3\34\3\34\3\u00a5"+
		"\2\35\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35\3\2"+
		"\6\4\2\13\13\"\"\3\2\62;\4\2C\\c|\5\2\62;C\\c|\2\u00b0\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\39\3\2\2\2\5>\3\2\2\2\7A\3\2\2\2"+
		"\tI\3\2\2\2\13P\3\2\2\2\rX\3\2\2\2\17^\3\2\2\2\21`\3\2\2\2\23b\3\2\2\2"+
		"\25d\3\2\2\2\27f\3\2\2\2\31h\3\2\2\2\33j\3\2\2\2\35l\3\2\2\2\37n\3\2\2"+
		"\2!p\3\2\2\2#s\3\2\2\2%v\3\2\2\2\'y\3\2\2\2){\3\2\2\2+~\3\2\2\2-\u0080"+
		"\3\2\2\2/\u0087\3\2\2\2\61\u008d\3\2\2\2\63\u0092\3\2\2\2\65\u0097\3\2"+
		"\2\2\67\u00a1\3\2\2\29:\7h\2\2:;\7q\2\2;<\7t\2\2<=\7o\2\2=\4\3\2\2\2>"+
		"?\7k\2\2?@\7h\2\2@\6\3\2\2\2AB\7d\2\2BC\7q\2\2CD\7q\2\2DE\7n\2\2EF\7g"+
		"\2\2FG\7c\2\2GH\7p\2\2H\b\3\2\2\2IJ\7u\2\2JK\7v\2\2KL\7t\2\2LM\7k\2\2"+
		"MN\7p\2\2NO\7i\2\2O\n\3\2\2\2PQ\7k\2\2QR\7p\2\2RS\7v\2\2ST\7g\2\2TU\7"+
		"i\2\2UV\7g\2\2VW\7t\2\2W\f\3\2\2\2XY\7o\2\2YZ\7q\2\2Z[\7p\2\2[\\\7g\2"+
		"\2\\]\7{\2\2]\16\3\2\2\2^_\7}\2\2_\20\3\2\2\2`a\7\177\2\2a\22\3\2\2\2"+
		"bc\7*\2\2c\24\3\2\2\2de\7+\2\2e\26\3\2\2\2fg\7/\2\2g\30\3\2\2\2hi\7-\2"+
		"\2i\32\3\2\2\2jk\7,\2\2k\34\3\2\2\2lm\7\61\2\2m\36\3\2\2\2no\7<\2\2o "+
		"\3\2\2\2pq\7?\2\2qr\7?\2\2r\"\3\2\2\2st\7#\2\2tu\7?\2\2u$\3\2\2\2vw\7"+
		"@\2\2wx\7?\2\2x&\3\2\2\2yz\7@\2\2z(\3\2\2\2{|\7>\2\2|}\7?\2\2}*\3\2\2"+
		"\2~\177\7>\2\2\177,\3\2\2\2\u0080\u0081\7#\2\2\u0081.\3\2\2\2\u0082\u0084"+
		"\7\17\2\2\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\3\2\2\2"+
		"\u0085\u0088\7\f\2\2\u0086\u0088\7\17\2\2\u0087\u0083\3\2\2\2\u0087\u0086"+
		"\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\u008c\b\30\2\2\u008c\60\3\2\2\2\u008d\u008e\t\2\2"+
		"\2\u008e\u008f\3\2\2\2\u008f\u0090\b\31\2\2\u0090\62\3\2\2\2\u0091\u0093"+
		"\t\3\2\2\u0092\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0092\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\64\3\2\2\2\u0096\u0098\t\4\2\2\u0097\u0096\3\2\2"+
		"\2\u0098\u0099\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009e"+
		"\3\2\2\2\u009b\u009d\t\5\2\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e"+
		"\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\66\3\2\2\2\u00a0\u009e\3\2\2"+
		"\2\u00a1\u00a5\7$\2\2\u00a2\u00a4\13\2\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7"+
		"\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7"+
		"\u00a5\3\2\2\2\u00a8\u00a9\7$\2\2\u00a98\3\2\2\2\n\2\u0083\u0087\u0089"+
		"\u0094\u0099\u009e\u00a5\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}