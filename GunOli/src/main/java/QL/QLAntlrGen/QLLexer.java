// Generated from C:/Users/Michael/Desktop/Software Construction/endless-ql/GunOli/src/main/antlr\QL.g4 by ANTLR 4.7
package QL.QLAntlrGen;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, PLUS=7, MINUS=8, MUL=9, 
		DIV=10, GT=11, GE=12, LT=13, LE=14, EQ=15, NE=16, AND=17, OR=18, NOT=19, 
		FORM=20, IF=21, BOOLEANTYPE=22, STRINGTYPE=23, MONEYTYPE=24, INTEGERTYPE=25, 
		DATETYPE=26, DECIMALTYPE=27, BOOLEAN=28, INTEGER=29, DECIMAL=30, MONEY=31, 
		DATE=32, DAY=33, MONTH=34, YEAR=35, STRING=36, IDENTIFIER=37, WHITESPCAE=38, 
		MULTI_COMMENT=39, SINGLE_COMMENT=40;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "PLUS", "MINUS", "MUL", 
		"DIV", "GT", "GE", "LT", "LE", "EQ", "NE", "AND", "OR", "NOT", "FORM", 
		"IF", "BOOLEANTYPE", "STRINGTYPE", "MONEYTYPE", "INTEGERTYPE", "DATETYPE", 
		"DECIMALTYPE", "BOOLEAN", "INTEGER", "DECIMAL", "MONEY", "DATE", "DAY", 
		"MONTH", "YEAR", "STRING", "IDENTIFIER", "WHITESPCAE", "MULTI_COMMENT", 
		"SINGLE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "'('", "')'", "':'", "'='", "'+'", "'-'", "'*'", "'/'", 
		"'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'form'", 
		"'if'", "'boolean'", "'string'", "'money'", "'integer'", "'date'", "'decimal'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "PLUS", "MINUS", "MUL", "DIV", 
		"GT", "GE", "LT", "LE", "EQ", "NE", "AND", "OR", "NOT", "FORM", "IF", 
		"BOOLEANTYPE", "STRINGTYPE", "MONEYTYPE", "INTEGERTYPE", "DATETYPE", "DECIMALTYPE", 
		"BOOLEAN", "INTEGER", "DECIMAL", "MONEY", "DATE", "DAY", "MONTH", "YEAR", 
		"STRING", "IDENTIFIER", "WHITESPCAE", "MULTI_COMMENT", "SINGLE_COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2*\u0125\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3"+
		"\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u00bb"+
		"\n\35\3\36\6\36\u00be\n\36\r\36\16\36\u00bf\3\37\6\37\u00c3\n\37\r\37"+
		"\16\37\u00c4\3\37\3\37\6\37\u00c9\n\37\r\37\16\37\u00ca\3 \6 \u00ce\n"+
		" \r \16 \u00cf\3 \3 \6 \u00d4\n \r \16 \u00d5\3 \6 \u00d9\n \r \16 \u00da"+
		"\5 \u00dd\n \3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u00eb\n\"\3"+
		"#\3#\3#\3#\5#\u00f1\n#\3$\3$\3$\3$\3$\3%\3%\7%\u00fa\n%\f%\16%\u00fd\13"+
		"%\3%\3%\3&\6&\u0102\n&\r&\16&\u0103\3\'\6\'\u0107\n\'\r\'\16\'\u0108\3"+
		"\'\3\'\3(\3(\3(\3(\7(\u0111\n(\f(\16(\u0114\13(\3(\3(\3(\3(\3(\3)\3)\3"+
		")\3)\7)\u011f\n)\f)\16)\u0122\13)\3)\3)\4\u00fb\u0112\2*\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q*\3\2\n\3\2\62;\3\2\63\64\3\2\62\63\3\2\63;\3\2\62\64\5\2\62"+
		";C\\c|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u0134\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\3S\3\2\2\2\5U\3\2\2\2\7W\3\2\2\2\t"+
		"Y\3\2\2\2\13[\3\2\2\2\r]\3\2\2\2\17_\3\2\2\2\21a\3\2\2\2\23c\3\2\2\2\25"+
		"e\3\2\2\2\27g\3\2\2\2\31i\3\2\2\2\33l\3\2\2\2\35n\3\2\2\2\37q\3\2\2\2"+
		"!t\3\2\2\2#w\3\2\2\2%z\3\2\2\2\'}\3\2\2\2)\177\3\2\2\2+\u0084\3\2\2\2"+
		"-\u0087\3\2\2\2/\u008f\3\2\2\2\61\u0096\3\2\2\2\63\u009c\3\2\2\2\65\u00a4"+
		"\3\2\2\2\67\u00a9\3\2\2\29\u00ba\3\2\2\2;\u00bd\3\2\2\2=\u00c2\3\2\2\2"+
		"?\u00dc\3\2\2\2A\u00de\3\2\2\2C\u00ea\3\2\2\2E\u00f0\3\2\2\2G\u00f2\3"+
		"\2\2\2I\u00f7\3\2\2\2K\u0101\3\2\2\2M\u0106\3\2\2\2O\u010c\3\2\2\2Q\u011a"+
		"\3\2\2\2ST\7}\2\2T\4\3\2\2\2UV\7\177\2\2V\6\3\2\2\2WX\7*\2\2X\b\3\2\2"+
		"\2YZ\7+\2\2Z\n\3\2\2\2[\\\7<\2\2\\\f\3\2\2\2]^\7?\2\2^\16\3\2\2\2_`\7"+
		"-\2\2`\20\3\2\2\2ab\7/\2\2b\22\3\2\2\2cd\7,\2\2d\24\3\2\2\2ef\7\61\2\2"+
		"f\26\3\2\2\2gh\7@\2\2h\30\3\2\2\2ij\7@\2\2jk\7?\2\2k\32\3\2\2\2lm\7>\2"+
		"\2m\34\3\2\2\2no\7>\2\2op\7?\2\2p\36\3\2\2\2qr\7?\2\2rs\7?\2\2s \3\2\2"+
		"\2tu\7#\2\2uv\7?\2\2v\"\3\2\2\2wx\7(\2\2xy\7(\2\2y$\3\2\2\2z{\7~\2\2{"+
		"|\7~\2\2|&\3\2\2\2}~\7#\2\2~(\3\2\2\2\177\u0080\7h\2\2\u0080\u0081\7q"+
		"\2\2\u0081\u0082\7t\2\2\u0082\u0083\7o\2\2\u0083*\3\2\2\2\u0084\u0085"+
		"\7k\2\2\u0085\u0086\7h\2\2\u0086,\3\2\2\2\u0087\u0088\7d\2\2\u0088\u0089"+
		"\7q\2\2\u0089\u008a\7q\2\2\u008a\u008b\7n\2\2\u008b\u008c\7g\2\2\u008c"+
		"\u008d\7c\2\2\u008d\u008e\7p\2\2\u008e.\3\2\2\2\u008f\u0090\7u\2\2\u0090"+
		"\u0091\7v\2\2\u0091\u0092\7t\2\2\u0092\u0093\7k\2\2\u0093\u0094\7p\2\2"+
		"\u0094\u0095\7i\2\2\u0095\60\3\2\2\2\u0096\u0097\7o\2\2\u0097\u0098\7"+
		"q\2\2\u0098\u0099\7p\2\2\u0099\u009a\7g\2\2\u009a\u009b\7{\2\2\u009b\62"+
		"\3\2\2\2\u009c\u009d\7k\2\2\u009d\u009e\7p\2\2\u009e\u009f\7v\2\2\u009f"+
		"\u00a0\7g\2\2\u00a0\u00a1\7i\2\2\u00a1\u00a2\7g\2\2\u00a2\u00a3\7t\2\2"+
		"\u00a3\64\3\2\2\2\u00a4\u00a5\7f\2\2\u00a5\u00a6\7c\2\2\u00a6\u00a7\7"+
		"v\2\2\u00a7\u00a8\7g\2\2\u00a8\66\3\2\2\2\u00a9\u00aa\7f\2\2\u00aa\u00ab"+
		"\7g\2\2\u00ab\u00ac\7e\2\2\u00ac\u00ad\7k\2\2\u00ad\u00ae\7o\2\2\u00ae"+
		"\u00af\7c\2\2\u00af\u00b0\7n\2\2\u00b08\3\2\2\2\u00b1\u00b2\7v\2\2\u00b2"+
		"\u00b3\7t\2\2\u00b3\u00b4\7w\2\2\u00b4\u00bb\7g\2\2\u00b5\u00b6\7h\2\2"+
		"\u00b6\u00b7\7c\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7u\2\2\u00b9\u00bb"+
		"\7g\2\2\u00ba\u00b1\3\2\2\2\u00ba\u00b5\3\2\2\2\u00bb:\3\2\2\2\u00bc\u00be"+
		"\t\2\2\2\u00bd\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf"+
		"\u00c0\3\2\2\2\u00c0<\3\2\2\2\u00c1\u00c3\t\2\2\2\u00c2\u00c1\3\2\2\2"+
		"\u00c3\u00c4\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6"+
		"\3\2\2\2\u00c6\u00c8\7\60\2\2\u00c7\u00c9\t\2\2\2\u00c8\u00c7\3\2\2\2"+
		"\u00c9\u00ca\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb>\3"+
		"\2\2\2\u00cc\u00ce\t\2\2\2\u00cd\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf"+
		"\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d3\7\60"+
		"\2\2\u00d2\u00d4\t\2\2\2\u00d3\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5"+
		"\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00dd\3\2\2\2\u00d7\u00d9\t\2"+
		"\2\2\u00d8\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00d8\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\u00dd\3\2\2\2\u00dc\u00cd\3\2\2\2\u00dc\u00d8\3\2"+
		"\2\2\u00dd@\3\2\2\2\u00de\u00df\5C\"\2\u00df\u00e0\7/\2\2\u00e0\u00e1"+
		"\5E#\2\u00e1\u00e2\7/\2\2\u00e2\u00e3\5G$\2\u00e3B\3\2\2\2\u00e4\u00e5"+
		"\7\62\2\2\u00e5\u00eb\t\2\2\2\u00e6\u00e7\t\3\2\2\u00e7\u00eb\t\2\2\2"+
		"\u00e8\u00e9\7\65\2\2\u00e9\u00eb\t\4\2\2\u00ea\u00e4\3\2\2\2\u00ea\u00e6"+
		"\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ebD\3\2\2\2\u00ec\u00ed\7\62\2\2\u00ed"+
		"\u00f1\t\5\2\2\u00ee\u00ef\7\63\2\2\u00ef\u00f1\t\6\2\2\u00f0\u00ec\3"+
		"\2\2\2\u00f0\u00ee\3\2\2\2\u00f1F\3\2\2\2\u00f2\u00f3\t\6\2\2\u00f3\u00f4"+
		"\t\2\2\2\u00f4\u00f5\t\2\2\2\u00f5\u00f6\t\2\2\2\u00f6H\3\2\2\2\u00f7"+
		"\u00fb\7$\2\2\u00f8\u00fa\13\2\2\2\u00f9\u00f8\3\2\2\2\u00fa\u00fd\3\2"+
		"\2\2\u00fb\u00fc\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u00fe\3\2\2\2\u00fd"+
		"\u00fb\3\2\2\2\u00fe\u00ff\7$\2\2\u00ffJ\3\2\2\2\u0100\u0102\t\7\2\2\u0101"+
		"\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2"+
		"\2\2\u0104L\3\2\2\2\u0105\u0107\t\b\2\2\u0106\u0105\3\2\2\2\u0107\u0108"+
		"\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010a\3\2\2\2\u010a"+
		"\u010b\b\'\2\2\u010bN\3\2\2\2\u010c\u010d\7\61\2\2\u010d\u010e\7,\2\2"+
		"\u010e\u0112\3\2\2\2\u010f\u0111\13\2\2\2\u0110\u010f\3\2\2\2\u0111\u0114"+
		"\3\2\2\2\u0112\u0113\3\2\2\2\u0112\u0110\3\2\2\2\u0113\u0115\3\2\2\2\u0114"+
		"\u0112\3\2\2\2\u0115\u0116\7,\2\2\u0116\u0117\7\61\2\2\u0117\u0118\3\2"+
		"\2\2\u0118\u0119\b(\2\2\u0119P\3\2\2\2\u011a\u011b\7\61\2\2\u011b\u011c"+
		"\7\61\2\2\u011c\u0120\3\2\2\2\u011d\u011f\n\t\2\2\u011e\u011d\3\2\2\2"+
		"\u011f\u0122\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0123"+
		"\3\2\2\2\u0122\u0120\3\2\2\2\u0123\u0124\b)\2\2\u0124R\3\2\2\2\22\2\u00ba"+
		"\u00bf\u00c4\u00ca\u00cf\u00d5\u00da\u00dc\u00ea\u00f0\u00fb\u0103\u0108"+
		"\u0112\u0120\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}