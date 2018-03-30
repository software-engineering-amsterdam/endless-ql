// Generated from /Users/piotrkosytorz/Library/Mobile Documents/com~apple~CloudDocs/Study/SC/endless-ql/piotr/org.uva.sc.piotr.ql/src/main/java/ql/grammar/QL.g4 by ANTLR 4.7
package ql.grammar;
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
		T__0=1, T__1=2, T__2=3, T__3=4, OP_AND=5, OP_OR=6, OP_NOT=7, OP_ASSIG=8, 
		OP_MULT=9, OP_DIV=10, OP_PLUS=11, OP_MINUS=12, OP_GT=13, OP_GE=14, OP_LT=15, 
		OP_LE=16, OP_EQ=17, OP_NEQ=18, IF=19, ELSE=20, BEGIN=21, END=22, TYPE_BOOLEAN=23, 
		TYPE_STRING=24, TYPE_INTEGER=25, TYPE_DECIMAL=26, TYPE_MONEY=27, TYPE_DATE=28, 
		BOOL_TRUE=29, BOOL_FALSE=30, WS=31, COMMENT=32, MONEY=33, IDENTIFIER=34, 
		INTEGER=35, STRING=36, DECIMAL=37, DAY=38, MONTH=39, YEAR=40, DATE=41;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "OP_AND", "OP_OR", "OP_NOT", "OP_ASSIG", 
		"OP_MULT", "OP_DIV", "OP_PLUS", "OP_MINUS", "OP_GT", "OP_GE", "OP_LT", 
		"OP_LE", "OP_EQ", "OP_NEQ", "IF", "ELSE", "BEGIN", "END", "TYPE_BOOLEAN", 
		"TYPE_STRING", "TYPE_INTEGER", "TYPE_DECIMAL", "TYPE_MONEY", "TYPE_DATE", 
		"BOOL_TRUE", "BOOL_FALSE", "WS", "COMMENT", "MONEY", "IDENTIFIER", "INTEGER", 
		"STRING", "DECIMAL", "DAY", "MONTH", "YEAR", "DATE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "':'", "'('", "')'", "'&&'", "'||'", "'!'", "'='", "'*'", 
		"'/'", "'+'", "'-'", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'if'", 
		"'else'", "'{'", "'}'", "'boolean'", "'string'", "'integer'", "'decimal'", 
		"'money'", "'date'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "OP_AND", "OP_OR", "OP_NOT", "OP_ASSIG", 
		"OP_MULT", "OP_DIV", "OP_PLUS", "OP_MINUS", "OP_GT", "OP_GE", "OP_LT", 
		"OP_LE", "OP_EQ", "OP_NEQ", "IF", "ELSE", "BEGIN", "END", "TYPE_BOOLEAN", 
		"TYPE_STRING", "TYPE_INTEGER", "TYPE_DECIMAL", "TYPE_MONEY", "TYPE_DATE", 
		"BOOL_TRUE", "BOOL_FALSE", "WS", "COMMENT", "MONEY", "IDENTIFIER", "INTEGER", 
		"STRING", "DECIMAL", "DAY", "MONTH", "YEAR", "DATE"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2+\u011d\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3"+
		"\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20"+
		"\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\5\36\u00c1\n\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\5\37\u00cd\n\37\3 \3 \3 \3 \3!\3!\3!\3!\7!\u00d7\n!\f!\16!\u00da"+
		"\13!\3!\3!\3!\3!\3!\3\"\3\"\6\"\u00e3\n\"\r\"\16\"\u00e4\3\"\3\"\3\"\3"+
		"\"\3#\3#\7#\u00ed\n#\f#\16#\u00f0\13#\3$\6$\u00f3\n$\r$\16$\u00f4\3%\3"+
		"%\7%\u00f9\n%\f%\16%\u00fc\13%\3%\3%\3&\6&\u0101\n&\r&\16&\u0102\3&\3"+
		"&\6&\u0107\n&\r&\16&\u0108\3\'\3\'\3\'\3(\3(\3(\3)\3)\3)\3)\3)\3*\3*\3"+
		"*\3*\3*\3*\3*\3*\4\u00d8\u00fa\2+\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+\3\2\6\5\2\13"+
		"\f\17\17\"\"\3\2\62;\4\2C\\c|\6\2\62;C\\aac|\2\u0125\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63"+
		"\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2"+
		"?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3"+
		"\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\3U\3\2\2\2\5Z\3\2\2"+
		"\2\7\\\3\2\2\2\t^\3\2\2\2\13`\3\2\2\2\rc\3\2\2\2\17f\3\2\2\2\21h\3\2\2"+
		"\2\23j\3\2\2\2\25l\3\2\2\2\27n\3\2\2\2\31p\3\2\2\2\33r\3\2\2\2\35t\3\2"+
		"\2\2\37w\3\2\2\2!y\3\2\2\2#|\3\2\2\2%\177\3\2\2\2\'\u0082\3\2\2\2)\u0085"+
		"\3\2\2\2+\u008a\3\2\2\2-\u008c\3\2\2\2/\u008e\3\2\2\2\61\u0096\3\2\2\2"+
		"\63\u009d\3\2\2\2\65\u00a5\3\2\2\2\67\u00ad\3\2\2\29\u00b3\3\2\2\2;\u00c0"+
		"\3\2\2\2=\u00cc\3\2\2\2?\u00ce\3\2\2\2A\u00d2\3\2\2\2C\u00e0\3\2\2\2E"+
		"\u00ea\3\2\2\2G\u00f2\3\2\2\2I\u00f6\3\2\2\2K\u0100\3\2\2\2M\u010a\3\2"+
		"\2\2O\u010d\3\2\2\2Q\u0110\3\2\2\2S\u0115\3\2\2\2UV\7h\2\2VW\7q\2\2WX"+
		"\7t\2\2XY\7o\2\2Y\4\3\2\2\2Z[\7<\2\2[\6\3\2\2\2\\]\7*\2\2]\b\3\2\2\2^"+
		"_\7+\2\2_\n\3\2\2\2`a\7(\2\2ab\7(\2\2b\f\3\2\2\2cd\7~\2\2de\7~\2\2e\16"+
		"\3\2\2\2fg\7#\2\2g\20\3\2\2\2hi\7?\2\2i\22\3\2\2\2jk\7,\2\2k\24\3\2\2"+
		"\2lm\7\61\2\2m\26\3\2\2\2no\7-\2\2o\30\3\2\2\2pq\7/\2\2q\32\3\2\2\2rs"+
		"\7@\2\2s\34\3\2\2\2tu\7@\2\2uv\7?\2\2v\36\3\2\2\2wx\7>\2\2x \3\2\2\2y"+
		"z\7>\2\2z{\7?\2\2{\"\3\2\2\2|}\7?\2\2}~\7?\2\2~$\3\2\2\2\177\u0080\7#"+
		"\2\2\u0080\u0081\7?\2\2\u0081&\3\2\2\2\u0082\u0083\7k\2\2\u0083\u0084"+
		"\7h\2\2\u0084(\3\2\2\2\u0085\u0086\7g\2\2\u0086\u0087\7n\2\2\u0087\u0088"+
		"\7u\2\2\u0088\u0089\7g\2\2\u0089*\3\2\2\2\u008a\u008b\7}\2\2\u008b,\3"+
		"\2\2\2\u008c\u008d\7\177\2\2\u008d.\3\2\2\2\u008e\u008f\7d\2\2\u008f\u0090"+
		"\7q\2\2\u0090\u0091\7q\2\2\u0091\u0092\7n\2\2\u0092\u0093\7g\2\2\u0093"+
		"\u0094\7c\2\2\u0094\u0095\7p\2\2\u0095\60\3\2\2\2\u0096\u0097\7u\2\2\u0097"+
		"\u0098\7v\2\2\u0098\u0099\7t\2\2\u0099\u009a\7k\2\2\u009a\u009b\7p\2\2"+
		"\u009b\u009c\7i\2\2\u009c\62\3\2\2\2\u009d\u009e\7k\2\2\u009e\u009f\7"+
		"p\2\2\u009f\u00a0\7v\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7i\2\2\u00a2\u00a3"+
		"\7g\2\2\u00a3\u00a4\7t\2\2\u00a4\64\3\2\2\2\u00a5\u00a6\7f\2\2\u00a6\u00a7"+
		"\7g\2\2\u00a7\u00a8\7e\2\2\u00a8\u00a9\7k\2\2\u00a9\u00aa\7o\2\2\u00aa"+
		"\u00ab\7c\2\2\u00ab\u00ac\7n\2\2\u00ac\66\3\2\2\2\u00ad\u00ae\7o\2\2\u00ae"+
		"\u00af\7q\2\2\u00af\u00b0\7p\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7{\2\2"+
		"\u00b28\3\2\2\2\u00b3\u00b4\7f\2\2\u00b4\u00b5\7c\2\2\u00b5\u00b6\7v\2"+
		"\2\u00b6\u00b7\7g\2\2\u00b7:\3\2\2\2\u00b8\u00b9\7v\2\2\u00b9\u00ba\7"+
		"t\2\2\u00ba\u00bb\7w\2\2\u00bb\u00c1\7g\2\2\u00bc\u00bd\7V\2\2\u00bd\u00be"+
		"\7T\2\2\u00be\u00bf\7W\2\2\u00bf\u00c1\7G\2\2\u00c0\u00b8\3\2\2\2\u00c0"+
		"\u00bc\3\2\2\2\u00c1<\3\2\2\2\u00c2\u00c3\7h\2\2\u00c3\u00c4\7c\2\2\u00c4"+
		"\u00c5\7n\2\2\u00c5\u00c6\7u\2\2\u00c6\u00cd\7g\2\2\u00c7\u00c8\7H\2\2"+
		"\u00c8\u00c9\7C\2\2\u00c9\u00ca\7N\2\2\u00ca\u00cb\7U\2\2\u00cb\u00cd"+
		"\7G\2\2\u00cc\u00c2\3\2\2\2\u00cc\u00c7\3\2\2\2\u00cd>\3\2\2\2\u00ce\u00cf"+
		"\t\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\b \2\2\u00d1@\3\2\2\2\u00d2\u00d3"+
		"\7\61\2\2\u00d3\u00d4\7,\2\2\u00d4\u00d8\3\2\2\2\u00d5\u00d7\13\2\2\2"+
		"\u00d6\u00d5\3\2\2\2\u00d7\u00da\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d8\u00d6"+
		"\3\2\2\2\u00d9\u00db\3\2\2\2\u00da\u00d8\3\2\2\2\u00db\u00dc\7,\2\2\u00dc"+
		"\u00dd\7\61\2\2\u00dd\u00de\3\2\2\2\u00de\u00df\b!\2\2\u00dfB\3\2\2\2"+
		"\u00e0\u00e2\7&\2\2\u00e1\u00e3\t\3\2\2\u00e2\u00e1\3\2\2\2\u00e3\u00e4"+
		"\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"+
		"\u00e7\7\60\2\2\u00e7\u00e8\t\3\2\2\u00e8\u00e9\t\3\2\2\u00e9D\3\2\2\2"+
		"\u00ea\u00ee\t\4\2\2\u00eb\u00ed\t\5\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00f0"+
		"\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00efF\3\2\2\2\u00f0"+
		"\u00ee\3\2\2\2\u00f1\u00f3\4\62;\2\u00f2\u00f1\3\2\2\2\u00f3\u00f4\3\2"+
		"\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5H\3\2\2\2\u00f6\u00fa"+
		"\7$\2\2\u00f7\u00f9\13\2\2\2\u00f8\u00f7\3\2\2\2\u00f9\u00fc\3\2\2\2\u00fa"+
		"\u00fb\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00fd\3\2\2\2\u00fc\u00fa\3\2"+
		"\2\2\u00fd\u00fe\7$\2\2\u00feJ\3\2\2\2\u00ff\u0101\t\3\2\2\u0100\u00ff"+
		"\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103"+
		"\u0104\3\2\2\2\u0104\u0106\7\60\2\2\u0105\u0107\t\3\2\2\u0106\u0105\3"+
		"\2\2\2\u0107\u0108\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109"+
		"L\3\2\2\2\u010a\u010b\4\62\65\2\u010b\u010c\4\62;\2\u010cN\3\2\2\2\u010d"+
		"\u010e\4\62\63\2\u010e\u010f\4\62;\2\u010fP\3\2\2\2\u0110\u0111\4\62;"+
		"\2\u0111\u0112\4\62;\2\u0112\u0113\4\62;\2\u0113\u0114\4\62;\2\u0114R"+
		"\3\2\2\2\u0115\u0116\7]\2\2\u0116\u0117\5Q)\2\u0117\u0118\7/\2\2\u0118"+
		"\u0119\5O(\2\u0119\u011a\7/\2\2\u011a\u011b\5M\'\2\u011b\u011c\7_\2\2"+
		"\u011cT\3\2\2\2\f\2\u00c0\u00cc\u00d8\u00e4\u00ee\u00f4\u00fa\u0102\u0108"+
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