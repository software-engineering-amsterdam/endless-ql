// Generated from C:/dev/uva/endless-ql/Abel_Elias/src/QL.parsing\QL.g4 by ANTLR 4.7
package QL.parsing.gen;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, BOOLEANTYPE=2, STRINGTYPE=3, INTEGERTYPE=4, MONEYTYPE=5, DATETYPE=6, 
		DECIMALTYPE=7, FORM=8, IF=9, ELSE=10, COLON=11, CURLY_BRACE_L=12, CURLY_BRACE_R=13, 
		BRACE_L=14, BRACE_R=15, ADD=16, SUB=17, MUL=18, DIV=19, REM=20, EQT=21, 
		GRT=22, LST=23, GRTE=24, LSTE=25, AND=26, NEQT=27, OR=28, NOT=29, BOOL=30, 
		IDENTIFIER=31, STR=32, INT=33, MON=34, DEC=35, NEWLINE=36;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE", "MONEYTYPE", "DATETYPE", 
		"DECIMALTYPE", "FORM", "IF", "ELSE", "COLON", "CURLY_BRACE_L", "CURLY_BRACE_R", 
		"BRACE_L", "BRACE_R", "ADD", "SUB", "MUL", "DIV", "REM", "EQT", "GRT", 
		"LST", "GRTE", "LSTE", "AND", "NEQT", "OR", "NOT", "DIGIT", "LETTER", 
		"BOOL", "IDENTIFIER", "STR", "INT", "MON", "DEC", "NEWLINE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'boolean'", "'string'", "'integer'", null, "'date'", "'decimal'", 
		"'form'", "'if'", "'else'", "':'", "'{'", "'}'", "'('", "')'", "'+'", 
		"'-'", "'*'", "'/'", "'%'", "'=='", "'>'", "'<'", "'>='", "'<='", "'&&'", 
		"'!='", "'||'", "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE", "MONEYTYPE", "DATETYPE", 
		"DECIMALTYPE", "FORM", "IF", "ELSE", "COLON", "CURLY_BRACE_L", "CURLY_BRACE_R", 
		"BRACE_L", "BRACE_R", "ADD", "SUB", "MUL", "DIV", "REM", "EQT", "GRT", 
		"LST", "GRTE", "LSTE", "AND", "NEQT", "OR", "NOT", "BOOL", "IDENTIFIER", 
		"STR", "INT", "MON", "DEC", "NEWLINE"
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


	public QLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QL.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2&\u0107\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\6\2Q\n\2\r\2\16\2R\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\5\6{\n\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37"+
		"\3\37\3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u00d0\n!\3\"\3\"\3\"\3\"\7\""+
		"\u00d6\n\"\f\"\16\"\u00d9\13\"\3#\3#\7#\u00dd\n#\f#\16#\u00e0\13#\3#\3"+
		"#\3$\5$\u00e5\n$\3$\6$\u00e8\n$\r$\16$\u00e9\3%\6%\u00ed\n%\r%\16%\u00ee"+
		"\3%\3%\3%\3%\3&\5&\u00f6\n&\3&\6&\u00f9\n&\r&\16&\u00fa\3&\3&\6&\u00ff"+
		"\n&\r&\16&\u0100\3\'\5\'\u0104\n\'\3\'\3\'\3\u00de\2(\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37=\2?\2A C!E\"G#I"+
		"$K%M&\3\2\4\4\2\13\13\"\"\4\2C\\c|\2\u0112\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2"+
		"\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\3P\3\2\2\2\5V"+
		"\3\2\2\2\7^\3\2\2\2\te\3\2\2\2\13z\3\2\2\2\r|\3\2\2\2\17\u0081\3\2\2\2"+
		"\21\u0089\3\2\2\2\23\u008e\3\2\2\2\25\u0091\3\2\2\2\27\u0096\3\2\2\2\31"+
		"\u0098\3\2\2\2\33\u009a\3\2\2\2\35\u009c\3\2\2\2\37\u009e\3\2\2\2!\u00a0"+
		"\3\2\2\2#\u00a2\3\2\2\2%\u00a4\3\2\2\2\'\u00a6\3\2\2\2)\u00a8\3\2\2\2"+
		"+\u00aa\3\2\2\2-\u00ad\3\2\2\2/\u00af\3\2\2\2\61\u00b1\3\2\2\2\63\u00b4"+
		"\3\2\2\2\65\u00b7\3\2\2\2\67\u00ba\3\2\2\29\u00bd\3\2\2\2;\u00c0\3\2\2"+
		"\2=\u00c2\3\2\2\2?\u00c4\3\2\2\2A\u00cf\3\2\2\2C\u00d1\3\2\2\2E\u00da"+
		"\3\2\2\2G\u00e4\3\2\2\2I\u00ec\3\2\2\2K\u00f5\3\2\2\2M\u0103\3\2\2\2O"+
		"Q\t\2\2\2PO\3\2\2\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2ST\3\2\2\2TU\b\2\2\2"+
		"U\4\3\2\2\2VW\7d\2\2WX\7q\2\2XY\7q\2\2YZ\7n\2\2Z[\7g\2\2[\\\7c\2\2\\]"+
		"\7p\2\2]\6\3\2\2\2^_\7u\2\2_`\7v\2\2`a\7t\2\2ab\7k\2\2bc\7p\2\2cd\7i\2"+
		"\2d\b\3\2\2\2ef\7k\2\2fg\7p\2\2gh\7v\2\2hi\7g\2\2ij\7i\2\2jk\7g\2\2kl"+
		"\7t\2\2l\n\3\2\2\2mn\7o\2\2no\7q\2\2op\7p\2\2pq\7g\2\2q{\7{\2\2rs\7e\2"+
		"\2st\7w\2\2tu\7t\2\2uv\7t\2\2vw\7g\2\2wx\7p\2\2xy\7e\2\2y{\7{\2\2zm\3"+
		"\2\2\2zr\3\2\2\2{\f\3\2\2\2|}\7f\2\2}~\7c\2\2~\177\7v\2\2\177\u0080\7"+
		"g\2\2\u0080\16\3\2\2\2\u0081\u0082\7f\2\2\u0082\u0083\7g\2\2\u0083\u0084"+
		"\7e\2\2\u0084\u0085\7k\2\2\u0085\u0086\7o\2\2\u0086\u0087\7c\2\2\u0087"+
		"\u0088\7n\2\2\u0088\20\3\2\2\2\u0089\u008a\7h\2\2\u008a\u008b\7q\2\2\u008b"+
		"\u008c\7t\2\2\u008c\u008d\7o\2\2\u008d\22\3\2\2\2\u008e\u008f\7k\2\2\u008f"+
		"\u0090\7h\2\2\u0090\24\3\2\2\2\u0091\u0092\7g\2\2\u0092\u0093\7n\2\2\u0093"+
		"\u0094\7u\2\2\u0094\u0095\7g\2\2\u0095\26\3\2\2\2\u0096\u0097\7<\2\2\u0097"+
		"\30\3\2\2\2\u0098\u0099\7}\2\2\u0099\32\3\2\2\2\u009a\u009b\7\177\2\2"+
		"\u009b\34\3\2\2\2\u009c\u009d\7*\2\2\u009d\36\3\2\2\2\u009e\u009f\7+\2"+
		"\2\u009f \3\2\2\2\u00a0\u00a1\7-\2\2\u00a1\"\3\2\2\2\u00a2\u00a3\7/\2"+
		"\2\u00a3$\3\2\2\2\u00a4\u00a5\7,\2\2\u00a5&\3\2\2\2\u00a6\u00a7\7\61\2"+
		"\2\u00a7(\3\2\2\2\u00a8\u00a9\7\'\2\2\u00a9*\3\2\2\2\u00aa\u00ab\7?\2"+
		"\2\u00ab\u00ac\7?\2\2\u00ac,\3\2\2\2\u00ad\u00ae\7@\2\2\u00ae.\3\2\2\2"+
		"\u00af\u00b0\7>\2\2\u00b0\60\3\2\2\2\u00b1\u00b2\7@\2\2\u00b2\u00b3\7"+
		"?\2\2\u00b3\62\3\2\2\2\u00b4\u00b5\7>\2\2\u00b5\u00b6\7?\2\2\u00b6\64"+
		"\3\2\2\2\u00b7\u00b8\7(\2\2\u00b8\u00b9\7(\2\2\u00b9\66\3\2\2\2\u00ba"+
		"\u00bb\7#\2\2\u00bb\u00bc\7?\2\2\u00bc8\3\2\2\2\u00bd\u00be\7~\2\2\u00be"+
		"\u00bf\7~\2\2\u00bf:\3\2\2\2\u00c0\u00c1\7#\2\2\u00c1<\3\2\2\2\u00c2\u00c3"+
		"\4\62;\2\u00c3>\3\2\2\2\u00c4\u00c5\t\3\2\2\u00c5@\3\2\2\2\u00c6\u00c7"+
		"\7v\2\2\u00c7\u00c8\7t\2\2\u00c8\u00c9\7w\2\2\u00c9\u00d0\7g\2\2\u00ca"+
		"\u00cb\7h\2\2\u00cb\u00cc\7c\2\2\u00cc\u00cd\7n\2\2\u00cd\u00ce\7u\2\2"+
		"\u00ce\u00d0\7g\2\2\u00cf\u00c6\3\2\2\2\u00cf\u00ca\3\2\2\2\u00d0B\3\2"+
		"\2\2\u00d1\u00d7\5? \2\u00d2\u00d6\5? \2\u00d3\u00d6\5=\37\2\u00d4\u00d6"+
		"\7a\2\2\u00d5\u00d2\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d4\3\2\2\2\u00d6"+
		"\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8D\3\2\2\2"+
		"\u00d9\u00d7\3\2\2\2\u00da\u00de\7$\2\2\u00db\u00dd\13\2\2\2\u00dc\u00db"+
		"\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de\u00df\3\2\2\2\u00de\u00dc\3\2\2\2\u00df"+
		"\u00e1\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00e2\7$\2\2\u00e2F\3\2\2\2\u00e3"+
		"\u00e5\7/\2\2\u00e4\u00e3\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e7\3\2"+
		"\2\2\u00e6\u00e8\5=\37\2\u00e7\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9"+
		"\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00eaH\3\2\2\2\u00eb\u00ed\5=\37\2"+
		"\u00ec\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef"+
		"\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1\7\60\2\2\u00f1\u00f2\5=\37\2"+
		"\u00f2\u00f3\5=\37\2\u00f3J\3\2\2\2\u00f4\u00f6\7/\2\2\u00f5\u00f4\3\2"+
		"\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f8\3\2\2\2\u00f7\u00f9\5=\37\2\u00f8"+
		"\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2"+
		"\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fe\7\60\2\2\u00fd\u00ff\5=\37\2\u00fe"+
		"\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2"+
		"\2\2\u0101L\3\2\2\2\u0102\u0104\7\17\2\2\u0103\u0102\3\2\2\2\u0103\u0104"+
		"\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0106\7\f\2\2\u0106N\3\2\2\2\20\2R"+
		"z\u00cf\u00d5\u00d7\u00de\u00e4\u00e9\u00ee\u00f5\u00fa\u0100\u0103\3"+
		"\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}