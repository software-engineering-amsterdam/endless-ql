// Generated from src/main/java/org/uva/sea/qls/parser/antlr/QLS.g by ANTLR 4.7.1

    package org.uva.sea.qls.parser.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLSLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, IDENT=16, NUM=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "IDENT", "NUM"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'stylesheet'", "'{'", "'}'", "'page'", "'section'", "'question'", 
		"'widget'", "','", "'\"'", "'width'", "':'", "'font'", "'fontsize'", "'color'", 
		"'#'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "IDENT", "NUM"
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


	public QLSLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QLS.g"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23\177\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\7\21"+
		"v\n\21\f\21\16\21y\13\21\3\22\6\22|\n\22\r\22\16\22}\2\2\23\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23\3\2\5\4\2C\\c|\6\2\62;C\\aac|\3\2\62;\2\u0080\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2\5\60\3"+
		"\2\2\2\7\62\3\2\2\2\t\64\3\2\2\2\139\3\2\2\2\rA\3\2\2\2\17J\3\2\2\2\21"+
		"Q\3\2\2\2\23S\3\2\2\2\25U\3\2\2\2\27[\3\2\2\2\31]\3\2\2\2\33b\3\2\2\2"+
		"\35k\3\2\2\2\37q\3\2\2\2!s\3\2\2\2#{\3\2\2\2%&\7u\2\2&\'\7v\2\2\'(\7{"+
		"\2\2()\7n\2\2)*\7g\2\2*+\7u\2\2+,\7j\2\2,-\7g\2\2-.\7g\2\2./\7v\2\2/\4"+
		"\3\2\2\2\60\61\7}\2\2\61\6\3\2\2\2\62\63\7\177\2\2\63\b\3\2\2\2\64\65"+
		"\7r\2\2\65\66\7c\2\2\66\67\7i\2\2\678\7g\2\28\n\3\2\2\29:\7u\2\2:;\7g"+
		"\2\2;<\7e\2\2<=\7v\2\2=>\7k\2\2>?\7q\2\2?@\7p\2\2@\f\3\2\2\2AB\7s\2\2"+
		"BC\7w\2\2CD\7g\2\2DE\7u\2\2EF\7v\2\2FG\7k\2\2GH\7q\2\2HI\7p\2\2I\16\3"+
		"\2\2\2JK\7y\2\2KL\7k\2\2LM\7f\2\2MN\7i\2\2NO\7g\2\2OP\7v\2\2P\20\3\2\2"+
		"\2QR\7.\2\2R\22\3\2\2\2ST\7$\2\2T\24\3\2\2\2UV\7y\2\2VW\7k\2\2WX\7f\2"+
		"\2XY\7v\2\2YZ\7j\2\2Z\26\3\2\2\2[\\\7<\2\2\\\30\3\2\2\2]^\7h\2\2^_\7q"+
		"\2\2_`\7p\2\2`a\7v\2\2a\32\3\2\2\2bc\7h\2\2cd\7q\2\2de\7p\2\2ef\7v\2\2"+
		"fg\7u\2\2gh\7k\2\2hi\7|\2\2ij\7g\2\2j\34\3\2\2\2kl\7e\2\2lm\7q\2\2mn\7"+
		"n\2\2no\7q\2\2op\7t\2\2p\36\3\2\2\2qr\7%\2\2r \3\2\2\2sw\t\2\2\2tv\t\3"+
		"\2\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2x\"\3\2\2\2yw\3\2\2\2z|\t"+
		"\4\2\2{z\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~$\3\2\2\2\5\2w}\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}