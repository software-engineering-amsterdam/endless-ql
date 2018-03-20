// Generated from /Users/piotrkosytorz/Library/Mobile Documents/com~apple~CloudDocs/Study/SC/endless-ql/piotr/org.uva.sc.piotr.ql/src/main/java/grammar/QL.g4 by ANTLR 4.7
package grammar;
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
		TYPE_STRING=24, TYPE_INTEGER=25, TYPE_DECIMAL=26, TYPE_MONEY=27, BOOL_TRUE=28, 
		BOOL_FALSE=29, WS=30, COMMENT=31, IDENTIFIER=32, INTEGER=33, STRING=34, 
		DECIMAL=35;
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
		"TYPE_STRING", "TYPE_INTEGER", "TYPE_DECIMAL", "TYPE_MONEY", "BOOL_TRUE", 
		"BOOL_FALSE", "WS", "COMMENT", "IDENTIFIER", "INTEGER", "STRING", "DECIMAL"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "':'", "'('", "')'", "'&&'", "'||'", "'!'", "'='", "'*'", 
		"'/'", "'+'", "'-'", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'if'", 
		"'else'", "'{'", "'}'", "'boolean'", "'string'", "'integer'", "'decimal'", 
		"'money'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "OP_AND", "OP_OR", "OP_NOT", "OP_ASSIG", 
		"OP_MULT", "OP_DIV", "OP_PLUS", "OP_MINUS", "OP_GT", "OP_GE", "OP_LT", 
		"OP_LE", "OP_EQ", "OP_NEQ", "IF", "ELSE", "BEGIN", "END", "TYPE_BOOLEAN", 
		"TYPE_STRING", "TYPE_INTEGER", "TYPE_DECIMAL", "TYPE_MONEY", "BOOL_TRUE", 
		"BOOL_FALSE", "WS", "COMMENT", "IDENTIFIER", "INTEGER", "STRING", "DECIMAL"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2%\u00ef\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3"+
		"\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\5\35\u00b0\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\5\36\u00bc\n\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \7 \u00c6"+
		"\n \f \16 \u00c9\13 \3 \3 \3 \3 \3 \3!\3!\7!\u00d2\n!\f!\16!\u00d5\13"+
		"!\3\"\6\"\u00d8\n\"\r\"\16\"\u00d9\3#\3#\7#\u00de\n#\f#\16#\u00e1\13#"+
		"\3#\3#\3$\6$\u00e6\n$\r$\16$\u00e7\3$\3$\6$\u00ec\n$\r$\16$\u00ed\4\u00c7"+
		"\u00df\2%\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67"+
		"\359\36;\37= ?!A\"C#E$G%\3\2\6\5\2\13\f\17\17\"\"\4\2C\\c|\6\2\62;C\\"+
		"aac|\3\2\62;\2\u00f6\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2"+
		"\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2"+
		"\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2"+
		"\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2"+
		"\2E\3\2\2\2\2G\3\2\2\2\3I\3\2\2\2\5N\3\2\2\2\7P\3\2\2\2\tR\3\2\2\2\13"+
		"T\3\2\2\2\rW\3\2\2\2\17Z\3\2\2\2\21\\\3\2\2\2\23^\3\2\2\2\25`\3\2\2\2"+
		"\27b\3\2\2\2\31d\3\2\2\2\33f\3\2\2\2\35h\3\2\2\2\37k\3\2\2\2!m\3\2\2\2"+
		"#p\3\2\2\2%s\3\2\2\2\'v\3\2\2\2)y\3\2\2\2+~\3\2\2\2-\u0080\3\2\2\2/\u0082"+
		"\3\2\2\2\61\u008a\3\2\2\2\63\u0091\3\2\2\2\65\u0099\3\2\2\2\67\u00a1\3"+
		"\2\2\29\u00af\3\2\2\2;\u00bb\3\2\2\2=\u00bd\3\2\2\2?\u00c1\3\2\2\2A\u00cf"+
		"\3\2\2\2C\u00d7\3\2\2\2E\u00db\3\2\2\2G\u00e5\3\2\2\2IJ\7h\2\2JK\7q\2"+
		"\2KL\7t\2\2LM\7o\2\2M\4\3\2\2\2NO\7<\2\2O\6\3\2\2\2PQ\7*\2\2Q\b\3\2\2"+
		"\2RS\7+\2\2S\n\3\2\2\2TU\7(\2\2UV\7(\2\2V\f\3\2\2\2WX\7~\2\2XY\7~\2\2"+
		"Y\16\3\2\2\2Z[\7#\2\2[\20\3\2\2\2\\]\7?\2\2]\22\3\2\2\2^_\7,\2\2_\24\3"+
		"\2\2\2`a\7\61\2\2a\26\3\2\2\2bc\7-\2\2c\30\3\2\2\2de\7/\2\2e\32\3\2\2"+
		"\2fg\7@\2\2g\34\3\2\2\2hi\7@\2\2ij\7?\2\2j\36\3\2\2\2kl\7>\2\2l \3\2\2"+
		"\2mn\7>\2\2no\7?\2\2o\"\3\2\2\2pq\7?\2\2qr\7?\2\2r$\3\2\2\2st\7#\2\2t"+
		"u\7?\2\2u&\3\2\2\2vw\7k\2\2wx\7h\2\2x(\3\2\2\2yz\7g\2\2z{\7n\2\2{|\7u"+
		"\2\2|}\7g\2\2}*\3\2\2\2~\177\7}\2\2\177,\3\2\2\2\u0080\u0081\7\177\2\2"+
		"\u0081.\3\2\2\2\u0082\u0083\7d\2\2\u0083\u0084\7q\2\2\u0084\u0085\7q\2"+
		"\2\u0085\u0086\7n\2\2\u0086\u0087\7g\2\2\u0087\u0088\7c\2\2\u0088\u0089"+
		"\7p\2\2\u0089\60\3\2\2\2\u008a\u008b\7u\2\2\u008b\u008c\7v\2\2\u008c\u008d"+
		"\7t\2\2\u008d\u008e\7k\2\2\u008e\u008f\7p\2\2\u008f\u0090\7i\2\2\u0090"+
		"\62\3\2\2\2\u0091\u0092\7k\2\2\u0092\u0093\7p\2\2\u0093\u0094\7v\2\2\u0094"+
		"\u0095\7g\2\2\u0095\u0096\7i\2\2\u0096\u0097\7g\2\2\u0097\u0098\7t\2\2"+
		"\u0098\64\3\2\2\2\u0099\u009a\7f\2\2\u009a\u009b\7g\2\2\u009b\u009c\7"+
		"e\2\2\u009c\u009d\7k\2\2\u009d\u009e\7o\2\2\u009e\u009f\7c\2\2\u009f\u00a0"+
		"\7n\2\2\u00a0\66\3\2\2\2\u00a1\u00a2\7o\2\2\u00a2\u00a3\7q\2\2\u00a3\u00a4"+
		"\7p\2\2\u00a4\u00a5\7g\2\2\u00a5\u00a6\7{\2\2\u00a68\3\2\2\2\u00a7\u00a8"+
		"\7v\2\2\u00a8\u00a9\7t\2\2\u00a9\u00aa\7w\2\2\u00aa\u00b0\7g\2\2\u00ab"+
		"\u00ac\7V\2\2\u00ac\u00ad\7T\2\2\u00ad\u00ae\7W\2\2\u00ae\u00b0\7G\2\2"+
		"\u00af\u00a7\3\2\2\2\u00af\u00ab\3\2\2\2\u00b0:\3\2\2\2\u00b1\u00b2\7"+
		"h\2\2\u00b2\u00b3\7c\2\2\u00b3\u00b4\7n\2\2\u00b4\u00b5\7u\2\2\u00b5\u00bc"+
		"\7g\2\2\u00b6\u00b7\7H\2\2\u00b7\u00b8\7C\2\2\u00b8\u00b9\7N\2\2\u00b9"+
		"\u00ba\7U\2\2\u00ba\u00bc\7G\2\2\u00bb\u00b1\3\2\2\2\u00bb\u00b6\3\2\2"+
		"\2\u00bc<\3\2\2\2\u00bd\u00be\t\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0"+
		"\b\37\2\2\u00c0>\3\2\2\2\u00c1\u00c2\7\61\2\2\u00c2\u00c3\7,\2\2\u00c3"+
		"\u00c7\3\2\2\2\u00c4\u00c6\13\2\2\2\u00c5\u00c4\3\2\2\2\u00c6\u00c9\3"+
		"\2\2\2\u00c7\u00c8\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00ca\3\2\2\2\u00c9"+
		"\u00c7\3\2\2\2\u00ca\u00cb\7,\2\2\u00cb\u00cc\7\61\2\2\u00cc\u00cd\3\2"+
		"\2\2\u00cd\u00ce\b \2\2\u00ce@\3\2\2\2\u00cf\u00d3\t\3\2\2\u00d0\u00d2"+
		"\t\4\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3"+
		"\u00d4\3\2\2\2\u00d4B\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00d8\4\62;\2"+
		"\u00d7\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da"+
		"\3\2\2\2\u00daD\3\2\2\2\u00db\u00df\7$\2\2\u00dc\u00de\13\2\2\2\u00dd"+
		"\u00dc\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00e0\3\2\2\2\u00df\u00dd\3\2"+
		"\2\2\u00e0\u00e2\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e3\7$\2\2\u00e3"+
		"F\3\2\2\2\u00e4\u00e6\t\5\2\2\u00e5\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2"+
		"\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00eb"+
		"\7\60\2\2\u00ea\u00ec\t\5\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2"+
		"\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00eeH\3\2\2\2\13\2\u00af\u00bb"+
		"\u00c7\u00d3\u00d9\u00df\u00e7\u00ed\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}