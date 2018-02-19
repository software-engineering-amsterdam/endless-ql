// Generated from C:/Users/Michael/Desktop/Software Construction/endless-ql/GunOli/src/main/antlr\QL.g4 by ANTLR 4.7
package antlrGen;
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, PLUS=6, MINUS=7, MUL=8, DIV=9, 
		GT=10, GE=11, LT=12, LE=13, EQ=14, NE=15, AND=16, OR=17, NOT=18, FORM=19, 
		IF=20, BOOLEANTYPE=21, STRINGTYPE=22, MONEYTYPE=23, INTEGERTYPE=24, DATETYPE=25, 
		DECIMALTYPE=26, INTEGER=27, DECIMAL=28, MONEY=29, DATE=30, DAY=31, MONTH=32, 
		YEAR=33, STRING=34, IDENTIFIER=35, WHITESPCAE=36, MULTI_COMMENT=37, SINGLE_COMMENT=38;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "PLUS", "MINUS", "MUL", "DIV", 
		"GT", "GE", "LT", "LE", "EQ", "NE", "AND", "OR", "NOT", "FORM", "IF", 
		"BOOLEANTYPE", "STRINGTYPE", "MONEYTYPE", "INTEGERTYPE", "DATETYPE", "DECIMALTYPE", 
		"INTEGER", "DECIMAL", "MONEY", "DATE", "DAY", "MONTH", "YEAR", "STRING", 
		"IDENTIFIER", "WHITESPCAE", "MULTI_COMMENT", "SINGLE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "'('", "')'", "':'", "'+'", "'-'", "'*'", "'/'", "'>'", 
		"'>='", "'<'", "'<='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'form'", 
		"'if'", "'boolean'", "'string'", "'money'", "'integer'", "'date'", "'decimal'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "PLUS", "MINUS", "MUL", "DIV", "GT", 
		"GE", "LT", "LE", "EQ", "NE", "AND", "OR", "NOT", "FORM", "IF", "BOOLEANTYPE", 
		"STRINGTYPE", "MONEYTYPE", "INTEGERTYPE", "DATETYPE", "DECIMALTYPE", "INTEGER", 
		"DECIMAL", "MONEY", "DATE", "DAY", "MONTH", "YEAR", "STRING", "IDENTIFIER", 
		"WHITESPCAE", "MULTI_COMMENT", "SINGLE_COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u0114\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\3\3\3\3\4\3\4\3"+
		"\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\6\34"+
		"\u00ad\n\34\r\34\16\34\u00ae\3\35\6\35\u00b2\n\35\r\35\16\35\u00b3\3\35"+
		"\3\35\6\35\u00b8\n\35\r\35\16\35\u00b9\3\36\6\36\u00bd\n\36\r\36\16\36"+
		"\u00be\3\36\3\36\6\36\u00c3\n\36\r\36\16\36\u00c4\3\36\6\36\u00c8\n\36"+
		"\r\36\16\36\u00c9\5\36\u00cc\n\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 "+
		"\3 \3 \3 \3 \5 \u00da\n \3!\3!\3!\3!\5!\u00e0\n!\3\"\3\"\3\"\3\"\3\"\3"+
		"#\3#\7#\u00e9\n#\f#\16#\u00ec\13#\3#\3#\3$\6$\u00f1\n$\r$\16$\u00f2\3"+
		"%\6%\u00f6\n%\r%\16%\u00f7\3%\3%\3&\3&\3&\3&\7&\u0100\n&\f&\16&\u0103"+
		"\13&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\7\'\u010e\n\'\f\'\16\'\u0111\13\'"+
		"\3\'\3\'\4\u00ea\u0101\2(\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(\3\2\n\3\2\62;\3\2\63\64"+
		"\3\2\62\63\3\2\63;\3\2\62\64\5\2\62;C\\c|\5\2\13\f\17\17\"\"\4\2\f\f\17"+
		"\17\2\u0122\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\3O\3\2\2\2\5Q\3\2\2\2\7S"+
		"\3\2\2\2\tU\3\2\2\2\13W\3\2\2\2\rY\3\2\2\2\17[\3\2\2\2\21]\3\2\2\2\23"+
		"_\3\2\2\2\25a\3\2\2\2\27c\3\2\2\2\31f\3\2\2\2\33h\3\2\2\2\35k\3\2\2\2"+
		"\37n\3\2\2\2!q\3\2\2\2#t\3\2\2\2%w\3\2\2\2\'y\3\2\2\2)~\3\2\2\2+\u0081"+
		"\3\2\2\2-\u0089\3\2\2\2/\u0090\3\2\2\2\61\u0096\3\2\2\2\63\u009e\3\2\2"+
		"\2\65\u00a3\3\2\2\2\67\u00ac\3\2\2\29\u00b1\3\2\2\2;\u00cb\3\2\2\2=\u00cd"+
		"\3\2\2\2?\u00d9\3\2\2\2A\u00df\3\2\2\2C\u00e1\3\2\2\2E\u00e6\3\2\2\2G"+
		"\u00f0\3\2\2\2I\u00f5\3\2\2\2K\u00fb\3\2\2\2M\u0109\3\2\2\2OP\7}\2\2P"+
		"\4\3\2\2\2QR\7\177\2\2R\6\3\2\2\2ST\7*\2\2T\b\3\2\2\2UV\7+\2\2V\n\3\2"+
		"\2\2WX\7<\2\2X\f\3\2\2\2YZ\7-\2\2Z\16\3\2\2\2[\\\7/\2\2\\\20\3\2\2\2]"+
		"^\7,\2\2^\22\3\2\2\2_`\7\61\2\2`\24\3\2\2\2ab\7@\2\2b\26\3\2\2\2cd\7@"+
		"\2\2de\7?\2\2e\30\3\2\2\2fg\7>\2\2g\32\3\2\2\2hi\7>\2\2ij\7?\2\2j\34\3"+
		"\2\2\2kl\7?\2\2lm\7?\2\2m\36\3\2\2\2no\7#\2\2op\7?\2\2p \3\2\2\2qr\7("+
		"\2\2rs\7(\2\2s\"\3\2\2\2tu\7~\2\2uv\7~\2\2v$\3\2\2\2wx\7#\2\2x&\3\2\2"+
		"\2yz\7h\2\2z{\7q\2\2{|\7t\2\2|}\7o\2\2}(\3\2\2\2~\177\7k\2\2\177\u0080"+
		"\7h\2\2\u0080*\3\2\2\2\u0081\u0082\7d\2\2\u0082\u0083\7q\2\2\u0083\u0084"+
		"\7q\2\2\u0084\u0085\7n\2\2\u0085\u0086\7g\2\2\u0086\u0087\7c\2\2\u0087"+
		"\u0088\7p\2\2\u0088,\3\2\2\2\u0089\u008a\7u\2\2\u008a\u008b\7v\2\2\u008b"+
		"\u008c\7t\2\2\u008c\u008d\7k\2\2\u008d\u008e\7p\2\2\u008e\u008f\7i\2\2"+
		"\u008f.\3\2\2\2\u0090\u0091\7o\2\2\u0091\u0092\7q\2\2\u0092\u0093\7p\2"+
		"\2\u0093\u0094\7g\2\2\u0094\u0095\7{\2\2\u0095\60\3\2\2\2\u0096\u0097"+
		"\7k\2\2\u0097\u0098\7p\2\2\u0098\u0099\7v\2\2\u0099\u009a\7g\2\2\u009a"+
		"\u009b\7i\2\2\u009b\u009c\7g\2\2\u009c\u009d\7t\2\2\u009d\62\3\2\2\2\u009e"+
		"\u009f\7f\2\2\u009f\u00a0\7c\2\2\u00a0\u00a1\7v\2\2\u00a1\u00a2\7g\2\2"+
		"\u00a2\64\3\2\2\2\u00a3\u00a4\7f\2\2\u00a4\u00a5\7g\2\2\u00a5\u00a6\7"+
		"e\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8\7o\2\2\u00a8\u00a9\7c\2\2\u00a9\u00aa"+
		"\7n\2\2\u00aa\66\3\2\2\2\u00ab\u00ad\t\2\2\2\u00ac\u00ab\3\2\2\2\u00ad"+
		"\u00ae\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af8\3\2\2\2"+
		"\u00b0\u00b2\t\2\2\2\u00b1\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b1"+
		"\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b7\7\60\2\2"+
		"\u00b6\u00b8\t\2\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00b7"+
		"\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba:\3\2\2\2\u00bb\u00bd\t\2\2\2\u00bc"+
		"\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2"+
		"\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\7\60\2\2\u00c1\u00c3\t\2\2\2\u00c2"+
		"\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2"+
		"\2\2\u00c5\u00cc\3\2\2\2\u00c6\u00c8\t\2\2\2\u00c7\u00c6\3\2\2\2\u00c8"+
		"\u00c9\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\3\2"+
		"\2\2\u00cb\u00bc\3\2\2\2\u00cb\u00c7\3\2\2\2\u00cc<\3\2\2\2\u00cd\u00ce"+
		"\5? \2\u00ce\u00cf\7/\2\2\u00cf\u00d0\5A!\2\u00d0\u00d1\7/\2\2\u00d1\u00d2"+
		"\5C\"\2\u00d2>\3\2\2\2\u00d3\u00d4\7\62\2\2\u00d4\u00da\t\2\2\2\u00d5"+
		"\u00d6\t\3\2\2\u00d6\u00da\t\2\2\2\u00d7\u00d8\7\65\2\2\u00d8\u00da\t"+
		"\4\2\2\u00d9\u00d3\3\2\2\2\u00d9\u00d5\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da"+
		"@\3\2\2\2\u00db\u00dc\7\62\2\2\u00dc\u00e0\t\5\2\2\u00dd\u00de\7\63\2"+
		"\2\u00de\u00e0\t\6\2\2\u00df\u00db\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0B"+
		"\3\2\2\2\u00e1\u00e2\t\6\2\2\u00e2\u00e3\t\2\2\2\u00e3\u00e4\t\2\2\2\u00e4"+
		"\u00e5\t\2\2\2\u00e5D\3\2\2\2\u00e6\u00ea\7$\2\2\u00e7\u00e9\13\2\2\2"+
		"\u00e8\u00e7\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00eb\3\2\2\2\u00ea\u00e8"+
		"\3\2\2\2\u00eb\u00ed\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ee\7$\2\2\u00ee"+
		"F\3\2\2\2\u00ef\u00f1\t\7\2\2\u00f0\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2"+
		"\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3H\3\2\2\2\u00f4\u00f6\t"+
		"\b\2\2\u00f5\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7"+
		"\u00f8\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa\b%\2\2\u00faJ\3\2\2\2\u00fb"+
		"\u00fc\7\61\2\2\u00fc\u00fd\7,\2\2\u00fd\u0101\3\2\2\2\u00fe\u0100\13"+
		"\2\2\2\u00ff\u00fe\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u0102\3\2\2\2\u0101"+
		"\u00ff\3\2\2\2\u0102\u0104\3\2\2\2\u0103\u0101\3\2\2\2\u0104\u0105\7,"+
		"\2\2\u0105\u0106\7\61\2\2\u0106\u0107\3\2\2\2\u0107\u0108\b&\2\2\u0108"+
		"L\3\2\2\2\u0109\u010a\7\61\2\2\u010a\u010b\7\61\2\2\u010b\u010f\3\2\2"+
		"\2\u010c\u010e\n\t\2\2\u010d\u010c\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d"+
		"\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0112\3\2\2\2\u0111\u010f\3\2\2\2\u0112"+
		"\u0113\b\'\2\2\u0113N\3\2\2\2\21\2\u00ae\u00b3\u00b9\u00be\u00c4\u00c9"+
		"\u00cb\u00d9\u00df\u00ea\u00f2\u00f7\u0101\u010f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}