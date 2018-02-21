// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/src/main/antlr4\Form.g4 by ANTLR 4.7
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, CURLY_BRACKET_OPEN=2, CURLY_BRACKET_CLOSE=3, BRACKET_OPEN=4, BRACKET_CLOSE=5, 
		PLUS=6, MINUS=7, TIMES=8, DIV=9, QUESTION_IDENTIFIER=10, QUESTION_VARIABLE_SEPERATOR=11, 
		QUESTION_ANSWER_SEPERATOR=12, IF=13, WHITESPACE=14, NEWLINE=15, CHARACTERS=16;
	public static final int
		RULE_form_builder = 0, RULE_form_data = 1, RULE_question_structure = 2, 
		RULE_if_structure = 3, RULE_statement_block_structure = 4, RULE_question_identifier = 5, 
		RULE_question_variable = 6, RULE_question_answer_type = 7, RULE_question_answer = 8;
	public static final String[] ruleNames = {
		"form_builder", "form_data", "question_structure", "if_structure", "statement_block_structure", 
		"question_identifier", "question_variable", "question_answer_type", "question_answer"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'('", "')'", "'+'", "'-'", "'*'", "'/'", 
		null, "':'", "'='", "'if'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", "BRACKET_OPEN", 
		"BRACKET_CLOSE", "PLUS", "MINUS", "TIMES", "DIV", "QUESTION_IDENTIFIER", 
		"QUESTION_VARIABLE_SEPERATOR", "QUESTION_ANSWER_SEPERATOR", "IF", "WHITESPACE", 
		"NEWLINE", "CHARACTERS"
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

	@Override
	public String getGrammarFileName() { return "Form.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FormParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Form_builderContext extends ParserRuleContext {
		public TerminalNode CHARACTERS() { return getToken(FormParser.CHARACTERS, 0); }
		public TerminalNode CURLY_BRACKET_OPEN() { return getToken(FormParser.CURLY_BRACKET_OPEN, 0); }
		public Form_dataContext form_data() {
			return getRuleContext(Form_dataContext.class,0);
		}
		public TerminalNode CURLY_BRACKET_CLOSE() { return getToken(FormParser.CURLY_BRACKET_CLOSE, 0); }
		public Form_builderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form_builder; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterForm_builder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitForm_builder(this);
		}
	}

	public final Form_builderContext form_builder() throws RecognitionException {
		Form_builderContext _localctx = new Form_builderContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_form_builder);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			match(T__0);
			setState(19);
			match(CHARACTERS);
			setState(20);
			match(CURLY_BRACKET_OPEN);
			setState(21);
			form_data();
			setState(22);
			match(CURLY_BRACKET_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Form_dataContext extends ParserRuleContext {
		public List<Question_structureContext> question_structure() {
			return getRuleContexts(Question_structureContext.class);
		}
		public Question_structureContext question_structure(int i) {
			return getRuleContext(Question_structureContext.class,i);
		}
		public If_structureContext if_structure() {
			return getRuleContext(If_structureContext.class,0);
		}
		public Form_dataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form_data; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterForm_data(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitForm_data(this);
		}
	}

	public final Form_dataContext form_data() throws RecognitionException {
		Form_dataContext _localctx = new Form_dataContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_form_data);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(24);
				question_structure();
				}
				}
				setState(27); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUESTION_IDENTIFIER );
			setState(30);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(29);
				if_structure();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Question_structureContext extends ParserRuleContext {
		public Question_identifierContext question_identifier() {
			return getRuleContext(Question_identifierContext.class,0);
		}
		public Question_variableContext question_variable() {
			return getRuleContext(Question_variableContext.class,0);
		}
		public TerminalNode QUESTION_VARIABLE_SEPERATOR() { return getToken(FormParser.QUESTION_VARIABLE_SEPERATOR, 0); }
		public Question_answer_typeContext question_answer_type() {
			return getRuleContext(Question_answer_typeContext.class,0);
		}
		public TerminalNode QUESTION_ANSWER_SEPERATOR() { return getToken(FormParser.QUESTION_ANSWER_SEPERATOR, 0); }
		public Question_answerContext question_answer() {
			return getRuleContext(Question_answerContext.class,0);
		}
		public Question_structureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_structure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterQuestion_structure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitQuestion_structure(this);
		}
	}

	public final Question_structureContext question_structure() throws RecognitionException {
		Question_structureContext _localctx = new Question_structureContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_question_structure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			question_identifier();
			setState(33);
			question_variable();
			setState(34);
			match(QUESTION_VARIABLE_SEPERATOR);
			setState(35);
			question_answer_type();
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION_ANSWER_SEPERATOR) {
				{
				setState(36);
				match(QUESTION_ANSWER_SEPERATOR);
				}
			}

			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_OPEN) {
				{
				setState(39);
				question_answer();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_structureContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(FormParser.IF, 0); }
		public Statement_block_structureContext statement_block_structure() {
			return getRuleContext(Statement_block_structureContext.class,0);
		}
		public TerminalNode CURLY_BRACKET_OPEN() { return getToken(FormParser.CURLY_BRACKET_OPEN, 0); }
		public TerminalNode CURLY_BRACKET_CLOSE() { return getToken(FormParser.CURLY_BRACKET_CLOSE, 0); }
		public List<Question_structureContext> question_structure() {
			return getRuleContexts(Question_structureContext.class);
		}
		public Question_structureContext question_structure(int i) {
			return getRuleContext(Question_structureContext.class,i);
		}
		public If_structureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_structure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterIf_structure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitIf_structure(this);
		}
	}

	public final If_structureContext if_structure() throws RecognitionException {
		If_structureContext _localctx = new If_structureContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_if_structure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(IF);
			setState(43);
			statement_block_structure();
			setState(44);
			match(CURLY_BRACKET_OPEN);
			setState(46); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(45);
				question_structure();
				}
				}
				setState(48); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUESTION_IDENTIFIER );
			setState(50);
			match(CURLY_BRACKET_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Statement_block_structureContext extends ParserRuleContext {
		public TerminalNode BRACKET_OPEN() { return getToken(FormParser.BRACKET_OPEN, 0); }
		public Question_variableContext question_variable() {
			return getRuleContext(Question_variableContext.class,0);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(FormParser.BRACKET_CLOSE, 0); }
		public Statement_block_structureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement_block_structure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterStatement_block_structure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitStatement_block_structure(this);
		}
	}

	public final Statement_block_structureContext statement_block_structure() throws RecognitionException {
		Statement_block_structureContext _localctx = new Statement_block_structureContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_statement_block_structure);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(BRACKET_OPEN);
			setState(53);
			question_variable();
			setState(54);
			match(BRACKET_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Question_identifierContext extends ParserRuleContext {
		public TerminalNode QUESTION_IDENTIFIER() { return getToken(FormParser.QUESTION_IDENTIFIER, 0); }
		public Question_identifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterQuestion_identifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitQuestion_identifier(this);
		}
	}

	public final Question_identifierContext question_identifier() throws RecognitionException {
		Question_identifierContext _localctx = new Question_identifierContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_question_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(QUESTION_IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Question_variableContext extends ParserRuleContext {
		public TerminalNode CHARACTERS() { return getToken(FormParser.CHARACTERS, 0); }
		public Question_variableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterQuestion_variable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitQuestion_variable(this);
		}
	}

	public final Question_variableContext question_variable() throws RecognitionException {
		Question_variableContext _localctx = new Question_variableContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_question_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(CHARACTERS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Question_answer_typeContext extends ParserRuleContext {
		public TerminalNode CHARACTERS() { return getToken(FormParser.CHARACTERS, 0); }
		public Question_answer_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_answer_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterQuestion_answer_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitQuestion_answer_type(this);
		}
	}

	public final Question_answer_typeContext question_answer_type() throws RecognitionException {
		Question_answer_typeContext _localctx = new Question_answer_typeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_question_answer_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(CHARACTERS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Question_answerContext extends ParserRuleContext {
		public TerminalNode BRACKET_OPEN() { return getToken(FormParser.BRACKET_OPEN, 0); }
		public List<Question_variableContext> question_variable() {
			return getRuleContexts(Question_variableContext.class);
		}
		public Question_variableContext question_variable(int i) {
			return getRuleContext(Question_variableContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(FormParser.BRACKET_CLOSE, 0); }
		public TerminalNode PLUS() { return getToken(FormParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(FormParser.MINUS, 0); }
		public TerminalNode TIMES() { return getToken(FormParser.TIMES, 0); }
		public TerminalNode DIV() { return getToken(FormParser.DIV, 0); }
		public Question_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterQuestion_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitQuestion_answer(this);
		}
	}

	public final Question_answerContext question_answer() throws RecognitionException {
		Question_answerContext _localctx = new Question_answerContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_question_answer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(BRACKET_OPEN);
			setState(63);
			question_variable();
			setState(64);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << TIMES) | (1L << DIV))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(65);
			question_variable();
			setState(66);
			match(BRACKET_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\22G\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\6\3\34\n\3\r\3\16\3\35\3\3\5\3!\n\3\3\4\3\4\3\4\3\4\3"+
		"\4\5\4(\n\4\3\4\5\4+\n\4\3\5\3\5\3\5\3\5\6\5\61\n\5\r\5\16\5\62\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\2\2\13\2\4\6\b\n\f\16\20\22\2\3\3\2\b\13\2B\2\24\3\2\2\2\4\33\3\2\2\2"+
		"\6\"\3\2\2\2\b,\3\2\2\2\n\66\3\2\2\2\f:\3\2\2\2\16<\3\2\2\2\20>\3\2\2"+
		"\2\22@\3\2\2\2\24\25\7\3\2\2\25\26\7\22\2\2\26\27\7\4\2\2\27\30\5\4\3"+
		"\2\30\31\7\5\2\2\31\3\3\2\2\2\32\34\5\6\4\2\33\32\3\2\2\2\34\35\3\2\2"+
		"\2\35\33\3\2\2\2\35\36\3\2\2\2\36 \3\2\2\2\37!\5\b\5\2 \37\3\2\2\2 !\3"+
		"\2\2\2!\5\3\2\2\2\"#\5\f\7\2#$\5\16\b\2$%\7\r\2\2%\'\5\20\t\2&(\7\16\2"+
		"\2\'&\3\2\2\2\'(\3\2\2\2(*\3\2\2\2)+\5\22\n\2*)\3\2\2\2*+\3\2\2\2+\7\3"+
		"\2\2\2,-\7\17\2\2-.\5\n\6\2.\60\7\4\2\2/\61\5\6\4\2\60/\3\2\2\2\61\62"+
		"\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65\7\5\2\2\65\t"+
		"\3\2\2\2\66\67\7\6\2\2\678\5\16\b\289\7\7\2\29\13\3\2\2\2:;\7\f\2\2;\r"+
		"\3\2\2\2<=\7\22\2\2=\17\3\2\2\2>?\7\22\2\2?\21\3\2\2\2@A\7\6\2\2AB\5\16"+
		"\b\2BC\t\2\2\2CD\5\16\b\2DE\7\7\2\2E\23\3\2\2\2\7\35 \'*\62";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}