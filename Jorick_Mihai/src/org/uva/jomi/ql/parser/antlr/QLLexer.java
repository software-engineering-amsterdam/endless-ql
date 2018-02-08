// Generated from QL.g by ANTLR 4.7.1
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
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, BOOLEAN=8, MONEY=9, 
		LINE_COMMENT=10, COMMENT=11, WS=12, IDENTIFIER=13, STRING=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "BOOLEAN", "MONEY", 
		"LINE_COMMENT", "COMMENT", "WS", "IDENTIFIER", "STRING"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'if'", "'('", "')'", "':'", "'boolean'", 
		"'money'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "BOOLEAN", "MONEY", "LINE_COMMENT", 
		"COMMENT", "WS", "IDENTIFIER", "STRING"
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
	public String getGrammarFileName() { return "QL.g"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20s\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13D\n\13\f\13\16\13"+
		"G\13\13\3\13\5\13J\n\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\7\fT\n\f\f"+
		"\f\16\fW\13\f\3\f\3\f\3\f\3\f\3\f\3\r\6\r_\n\r\r\r\16\r`\3\r\3\r\3\16"+
		"\3\16\6\16g\n\16\r\16\16\16h\3\17\3\17\7\17m\n\17\f\17\16\17p\13\17\3"+
		"\17\3\17\5EUn\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\3\2\5\5\2\13\f\17\17\"\"\4\2C\\c|\5\2\62;C\\c|\2x\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3\37\3\2\2\2\5$\3\2\2\2\7&\3\2\2"+
		"\2\t(\3\2\2\2\13+\3\2\2\2\r-\3\2\2\2\17/\3\2\2\2\21\61\3\2\2\2\239\3\2"+
		"\2\2\25?\3\2\2\2\27O\3\2\2\2\31^\3\2\2\2\33d\3\2\2\2\35j\3\2\2\2\37 \7"+
		"h\2\2 !\7q\2\2!\"\7t\2\2\"#\7o\2\2#\4\3\2\2\2$%\7}\2\2%\6\3\2\2\2&\'\7"+
		"\177\2\2\'\b\3\2\2\2()\7k\2\2)*\7h\2\2*\n\3\2\2\2+,\7*\2\2,\f\3\2\2\2"+
		"-.\7+\2\2.\16\3\2\2\2/\60\7<\2\2\60\20\3\2\2\2\61\62\7d\2\2\62\63\7q\2"+
		"\2\63\64\7q\2\2\64\65\7n\2\2\65\66\7g\2\2\66\67\7c\2\2\678\7p\2\28\22"+
		"\3\2\2\29:\7o\2\2:;\7q\2\2;<\7p\2\2<=\7g\2\2=>\7{\2\2>\24\3\2\2\2?@\7"+
		"\61\2\2@A\7\61\2\2AE\3\2\2\2BD\13\2\2\2CB\3\2\2\2DG\3\2\2\2EF\3\2\2\2"+
		"EC\3\2\2\2FI\3\2\2\2GE\3\2\2\2HJ\7\17\2\2IH\3\2\2\2IJ\3\2\2\2JK\3\2\2"+
		"\2KL\7\f\2\2LM\3\2\2\2MN\b\13\2\2N\26\3\2\2\2OP\7\61\2\2PQ\7,\2\2QU\3"+
		"\2\2\2RT\13\2\2\2SR\3\2\2\2TW\3\2\2\2UV\3\2\2\2US\3\2\2\2VX\3\2\2\2WU"+
		"\3\2\2\2XY\7,\2\2YZ\7\61\2\2Z[\3\2\2\2[\\\b\f\2\2\\\30\3\2\2\2]_\t\2\2"+
		"\2^]\3\2\2\2_`\3\2\2\2`^\3\2\2\2`a\3\2\2\2ab\3\2\2\2bc\b\r\2\2c\32\3\2"+
		"\2\2df\t\3\2\2eg\t\4\2\2fe\3\2\2\2gh\3\2\2\2hf\3\2\2\2hi\3\2\2\2i\34\3"+
		"\2\2\2jn\7$\2\2km\13\2\2\2lk\3\2\2\2mp\3\2\2\2no\3\2\2\2nl\3\2\2\2oq\3"+
		"\2\2\2pn\3\2\2\2qr\7$\2\2r\36\3\2\2\2\t\2EIU`hn\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}