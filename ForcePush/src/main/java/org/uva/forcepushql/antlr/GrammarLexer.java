// Generated from C:/Users/Joana Magalhães/Documents/GitHub/endless-ql/ForcePush/src/main/resources/antlr\GrammarLexer.g4 by ANTLR 4.7
package org.uva.forcepushql.antlr;
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
		WHITESPACE=1, COMMENT=2, LINE_COMMENT=3, FORM=4, BOOL=5, STR=6, INT=7, 
		DATE=8, DECIMAL=9, MULTIPLEANSWER=10, MONEY=11, ASSIGN=12, IF=13, ELSE=14, 
		IFELSE=15, LPAREN=16, RPAREN=17, LBRACE=18, RBRACE=19, SEMI=20, COMMA=21, 
		DOT=22, PLUS=23, MINUS=24, MULTIPLY=25, DIVIDE=26, EQUAL=27, LESS=28, 
		GREATER=29, EQUALGREATER=30, EQUALLESS=31, NOTEQUAL=32, ISEQUAL=33, AND=34, 
		OR=35, NOT=36, NUM=37, VAR=38, LABEL=39, DEC=40;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WHITESPACE", "COMMENT", "LINE_COMMENT", "FORM", "BOOL", "STR", "INT", 
		"DATE", "DECIMAL", "MULTIPLEANSWER", "MONEY", "ASSIGN", "IF", "ELSE", 
		"IFELSE", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "SEMI", "COMMA", "DOT", 
		"PLUS", "MINUS", "MULTIPLY", "DIVIDE", "EQUAL", "LESS", "GREATER", "EQUALGREATER", 
		"EQUALLESS", "NOTEQUAL", "ISEQUAL", "AND", "OR", "NOT", "NUM", "VAR", 
		"LABEL", "DEC"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, "'form'", "'boolean'", "'string'", "'integer'", 
		"'date'", "'decimal'", "'multipleAnswer'", null, "':'", "'if'", "'else'", 
		"'ifelse'", "'('", "')'", "'{'", "'}'", "';'", "','", "'.'", "'+'", "'-'", 
		"'*'", "'/'", "'='", "'<'", "'>'", "'>='", "'<='", "'!='", "'=='", "'&&'", 
		"'||'", "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WHITESPACE", "COMMENT", "LINE_COMMENT", "FORM", "BOOL", "STR", 
		"INT", "DATE", "DECIMAL", "MULTIPLEANSWER", "MONEY", "ASSIGN", "IF", "ELSE", 
		"IFELSE", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "SEMI", "COMMA", "DOT", 
		"PLUS", "MINUS", "MULTIPLY", "DIVIDE", "EQUAL", "LESS", "GREATER", "EQUALGREATER", 
		"EQUALLESS", "NOTEQUAL", "ISEQUAL", "AND", "OR", "NOT", "NUM", "VAR", 
		"LABEL", "DEC"
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
	public String getGrammarFileName() { return "GrammarLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2*\u011a\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\6\2U\n\2\r"+
		"\2\16\2V\3\2\3\2\3\3\3\3\3\3\3\3\7\3_\n\3\f\3\16\3b\13\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\7\4m\n\4\f\4\16\4p\13\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f"+
		"\u00b9\n\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3"+
		"\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3"+
		"\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3"+
		"#\3#\3#\3$\3$\3$\3%\3%\3&\6&\u00fd\n&\r&\16&\u00fe\3\'\3\'\7\'\u0103\n"+
		"\'\f\'\16\'\u0106\13\'\3(\3(\6(\u010a\n(\r(\16(\u010b\3(\3(\3)\6)\u0111"+
		"\n)\r)\16)\u0112\3)\3)\6)\u0117\n)\r)\16)\u0118\3`\2*\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K"+
		"\'M(O)Q*\3\2\b\5\2\13\f\17\17\"\"\4\2\f\f\17\17\3\2\62;\4\2C\\c|\5\2\62"+
		";C\\c|\b\2\"\"..\60<AAC\\c|\2\u0122\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
		"\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3"+
		"\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2"+
		"\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2"+
		"\2O\3\2\2\2\2Q\3\2\2\2\3T\3\2\2\2\5Z\3\2\2\2\7h\3\2\2\2\ts\3\2\2\2\13"+
		"x\3\2\2\2\r\u0080\3\2\2\2\17\u0087\3\2\2\2\21\u008f\3\2\2\2\23\u0094\3"+
		"\2\2\2\25\u009c\3\2\2\2\27\u00b8\3\2\2\2\31\u00ba\3\2\2\2\33\u00bc\3\2"+
		"\2\2\35\u00bf\3\2\2\2\37\u00c4\3\2\2\2!\u00cb\3\2\2\2#\u00cd\3\2\2\2%"+
		"\u00cf\3\2\2\2\'\u00d1\3\2\2\2)\u00d3\3\2\2\2+\u00d5\3\2\2\2-\u00d7\3"+
		"\2\2\2/\u00d9\3\2\2\2\61\u00db\3\2\2\2\63\u00dd\3\2\2\2\65\u00df\3\2\2"+
		"\2\67\u00e1\3\2\2\29\u00e3\3\2\2\2;\u00e5\3\2\2\2=\u00e7\3\2\2\2?\u00ea"+
		"\3\2\2\2A\u00ed\3\2\2\2C\u00f0\3\2\2\2E\u00f3\3\2\2\2G\u00f6\3\2\2\2I"+
		"\u00f9\3\2\2\2K\u00fc\3\2\2\2M\u0100\3\2\2\2O\u0107\3\2\2\2Q\u0110\3\2"+
		"\2\2SU\t\2\2\2TS\3\2\2\2UV\3\2\2\2VT\3\2\2\2VW\3\2\2\2WX\3\2\2\2XY\b\2"+
		"\2\2Y\4\3\2\2\2Z[\7\61\2\2[\\\7,\2\2\\`\3\2\2\2]_\13\2\2\2^]\3\2\2\2_"+
		"b\3\2\2\2`a\3\2\2\2`^\3\2\2\2ac\3\2\2\2b`\3\2\2\2cd\7,\2\2de\7\61\2\2"+
		"ef\3\2\2\2fg\b\3\2\2g\6\3\2\2\2hi\7\61\2\2ij\7\61\2\2jn\3\2\2\2km\n\3"+
		"\2\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2\2pn\3\2\2\2qr\b\4"+
		"\2\2r\b\3\2\2\2st\7h\2\2tu\7q\2\2uv\7t\2\2vw\7o\2\2w\n\3\2\2\2xy\7d\2"+
		"\2yz\7q\2\2z{\7q\2\2{|\7n\2\2|}\7g\2\2}~\7c\2\2~\177\7p\2\2\177\f\3\2"+
		"\2\2\u0080\u0081\7u\2\2\u0081\u0082\7v\2\2\u0082\u0083\7t\2\2\u0083\u0084"+
		"\7k\2\2\u0084\u0085\7p\2\2\u0085\u0086\7i\2\2\u0086\16\3\2\2\2\u0087\u0088"+
		"\7k\2\2\u0088\u0089\7p\2\2\u0089\u008a\7v\2\2\u008a\u008b\7g\2\2\u008b"+
		"\u008c\7i\2\2\u008c\u008d\7g\2\2\u008d\u008e\7t\2\2\u008e\20\3\2\2\2\u008f"+
		"\u0090\7f\2\2\u0090\u0091\7c\2\2\u0091\u0092\7v\2\2\u0092\u0093\7g\2\2"+
		"\u0093\22\3\2\2\2\u0094\u0095\7f\2\2\u0095\u0096\7g\2\2\u0096\u0097\7"+
		"e\2\2\u0097\u0098\7k\2\2\u0098\u0099\7o\2\2\u0099\u009a\7c\2\2\u009a\u009b"+
		"\7n\2\2\u009b\24\3\2\2\2\u009c\u009d\7o\2\2\u009d\u009e\7w\2\2\u009e\u009f"+
		"\7n\2\2\u009f\u00a0\7v\2\2\u00a0\u00a1\7k\2\2\u00a1\u00a2\7r\2\2\u00a2"+
		"\u00a3\7n\2\2\u00a3\u00a4\7g\2\2\u00a4\u00a5\7C\2\2\u00a5\u00a6\7p\2\2"+
		"\u00a6\u00a7\7u\2\2\u00a7\u00a8\7y\2\2\u00a8\u00a9\7g\2\2\u00a9\u00aa"+
		"\7t\2\2\u00aa\26\3\2\2\2\u00ab\u00ac\7o\2\2\u00ac\u00ad\7q\2\2\u00ad\u00ae"+
		"\7p\2\2\u00ae\u00af\7g\2\2\u00af\u00b9\7{\2\2\u00b0\u00b1\7e\2\2\u00b1"+
		"\u00b2\7w\2\2\u00b2\u00b3\7t\2\2\u00b3\u00b4\7t\2\2\u00b4\u00b5\7g\2\2"+
		"\u00b5\u00b6\7p\2\2\u00b6\u00b7\7e\2\2\u00b7\u00b9\7{\2\2\u00b8\u00ab"+
		"\3\2\2\2\u00b8\u00b0\3\2\2\2\u00b9\30\3\2\2\2\u00ba\u00bb\7<\2\2\u00bb"+
		"\32\3\2\2\2\u00bc\u00bd\7k\2\2\u00bd\u00be\7h\2\2\u00be\34\3\2\2\2\u00bf"+
		"\u00c0\7g\2\2\u00c0\u00c1\7n\2\2\u00c1\u00c2\7u\2\2\u00c2\u00c3\7g\2\2"+
		"\u00c3\36\3\2\2\2\u00c4\u00c5\7k\2\2\u00c5\u00c6\7h\2\2\u00c6\u00c7\7"+
		"g\2\2\u00c7\u00c8\7n\2\2\u00c8\u00c9\7u\2\2\u00c9\u00ca\7g\2\2\u00ca "+
		"\3\2\2\2\u00cb\u00cc\7*\2\2\u00cc\"\3\2\2\2\u00cd\u00ce\7+\2\2\u00ce$"+
		"\3\2\2\2\u00cf\u00d0\7}\2\2\u00d0&\3\2\2\2\u00d1\u00d2\7\177\2\2\u00d2"+
		"(\3\2\2\2\u00d3\u00d4\7=\2\2\u00d4*\3\2\2\2\u00d5\u00d6\7.\2\2\u00d6,"+
		"\3\2\2\2\u00d7\u00d8\7\60\2\2\u00d8.\3\2\2\2\u00d9\u00da\7-\2\2\u00da"+
		"\60\3\2\2\2\u00db\u00dc\7/\2\2\u00dc\62\3\2\2\2\u00dd\u00de\7,\2\2\u00de"+
		"\64\3\2\2\2\u00df\u00e0\7\61\2\2\u00e0\66\3\2\2\2\u00e1\u00e2\7?\2\2\u00e2"+
		"8\3\2\2\2\u00e3\u00e4\7>\2\2\u00e4:\3\2\2\2\u00e5\u00e6\7@\2\2\u00e6<"+
		"\3\2\2\2\u00e7\u00e8\7@\2\2\u00e8\u00e9\7?\2\2\u00e9>\3\2\2\2\u00ea\u00eb"+
		"\7>\2\2\u00eb\u00ec\7?\2\2\u00ec@\3\2\2\2\u00ed\u00ee\7#\2\2\u00ee\u00ef"+
		"\7?\2\2\u00efB\3\2\2\2\u00f0\u00f1\7?\2\2\u00f1\u00f2\7?\2\2\u00f2D\3"+
		"\2\2\2\u00f3\u00f4\7(\2\2\u00f4\u00f5\7(\2\2\u00f5F\3\2\2\2\u00f6\u00f7"+
		"\7~\2\2\u00f7\u00f8\7~\2\2\u00f8H\3\2\2\2\u00f9\u00fa\7#\2\2\u00faJ\3"+
		"\2\2\2\u00fb\u00fd\t\4\2\2\u00fc\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe"+
		"\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ffL\3\2\2\2\u0100\u0104\t\5\2\2"+
		"\u0101\u0103\t\6\2\2\u0102\u0101\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102"+
		"\3\2\2\2\u0104\u0105\3\2\2\2\u0105N\3\2\2\2\u0106\u0104\3\2\2\2\u0107"+
		"\u0109\7$\2\2\u0108\u010a\t\7\2\2\u0109\u0108\3\2\2\2\u010a\u010b\3\2"+
		"\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\u010e\7$\2\2\u010eP\3\2\2\2\u010f\u0111\t\4\2\2\u0110\u010f\3\2\2\2\u0111"+
		"\u0112\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0114\3\2"+
		"\2\2\u0114\u0116\7\60\2\2\u0115\u0117\t\4\2\2\u0116\u0115\3\2\2\2\u0117"+
		"\u0118\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119R\3\2\2\2"+
		"\f\2V`n\u00b8\u00fe\u0104\u010b\u0112\u0118\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}