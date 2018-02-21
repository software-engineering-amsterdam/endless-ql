// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/src/main/antlr4\FormParser.g4 by ANTLR 4.7
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormParserParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, CURLY_BRACKET_OPEN=2, CURLY_BRACKET_CLOSE=3, BRACKET_OPEN=4, BRACKET_CLOSE=5, 
		PLUS=6, MINUS=7, TIMES=8, DIV=9, QUESTION_IDENTIFIER=10, QUESTION_VARIABLE_SEPERATOR=11, 
		QUESTION_ANSWER_SEPERATOR=12, QUESTION_ANSWER=13, IF=14, WHITESPACE=15, 
		NEWLINE=16, CHARACTERS=17;
	public static final int
		RULE_form_builder = 0, RULE_form_data = 1, RULE_question_structure = 2, 
		RULE_if_structure = 3, RULE_stat_block = 4, RULE_question_identifier = 5, 
		RULE_question_variable = 6, RULE_question_answer_type = 7, RULE_question_answer = 8;
	public static final String[] ruleNames = {
		"form_builder", "form_data", "question_structure", "if_structure", "stat_block", 
		"question_identifier", "question_variable", "question_answer_type", "question_answer"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'('", "')'", "'+'", "'-'", "'*'", "'/'", 
		null, "':'", "'='", null, "'if'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", "BRACKET_OPEN", 
		"BRACKET_CLOSE", "PLUS", "MINUS", "TIMES", "DIV", "QUESTION_IDENTIFIER", 
		"QUESTION_VARIABLE_SEPERATOR", "QUESTION_ANSWER_SEPERATOR", "QUESTION_ANSWER", 
		"IF", "WHITESPACE", "NEWLINE", "CHARACTERS"
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
	public String getGrammarFileName() { return "FormParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FormParserParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Form_builderContext extends ParserRuleContext {
		public TerminalNode CHARACTERS() { return getToken(FormParserParser.CHARACTERS, 0); }
		public TerminalNode CURLY_BRACKET_OPEN() { return getToken(FormParserParser.CURLY_BRACKET_OPEN, 0); }
		public Form_dataContext form_data() {
			return getRuleContext(Form_dataContext.class,0);
		}
		public TerminalNode CURLY_BRACKET_CLOSE() { return getToken(FormParserParser.CURLY_BRACKET_CLOSE, 0); }
		public Form_builderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form_builder; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).enterForm_builder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).exitForm_builder(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormParserVisitor ) return ((FormParserVisitor<? extends T>)visitor).visitForm_builder(this);
			else return visitor.visitChildren(this);
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
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).enterForm_data(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).exitForm_data(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormParserVisitor ) return ((FormParserVisitor<? extends T>)visitor).visitForm_data(this);
			else return visitor.visitChildren(this);
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
		public TerminalNode QUESTION_VARIABLE_SEPERATOR() { return getToken(FormParserParser.QUESTION_VARIABLE_SEPERATOR, 0); }
		public Question_answer_typeContext question_answer_type() {
			return getRuleContext(Question_answer_typeContext.class,0);
		}
		public TerminalNode QUESTION_ANSWER_SEPERATOR() { return getToken(FormParserParser.QUESTION_ANSWER_SEPERATOR, 0); }
		public Question_answerContext question_answer() {
			return getRuleContext(Question_answerContext.class,0);
		}
		public Question_structureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_structure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).enterQuestion_structure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).exitQuestion_structure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormParserVisitor ) return ((FormParserVisitor<? extends T>)visitor).visitQuestion_structure(this);
			else return visitor.visitChildren(this);
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
			if (_la==QUESTION_ANSWER) {
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
		public TerminalNode IF() { return getToken(FormParserParser.IF, 0); }
		public Stat_blockContext stat_block() {
			return getRuleContext(Stat_blockContext.class,0);
		}
		public TerminalNode CURLY_BRACKET_OPEN() { return getToken(FormParserParser.CURLY_BRACKET_OPEN, 0); }
		public TerminalNode CURLY_BRACKET_CLOSE() { return getToken(FormParserParser.CURLY_BRACKET_CLOSE, 0); }
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
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).enterIf_structure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).exitIf_structure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormParserVisitor ) return ((FormParserVisitor<? extends T>)visitor).visitIf_structure(this);
			else return visitor.visitChildren(this);
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
			stat_block();
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

	public static class Stat_blockContext extends ParserRuleContext {
		public TerminalNode BRACKET_OPEN() { return getToken(FormParserParser.BRACKET_OPEN, 0); }
		public Question_variableContext question_variable() {
			return getRuleContext(Question_variableContext.class,0);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(FormParserParser.BRACKET_CLOSE, 0); }
		public Stat_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).enterStat_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).exitStat_block(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormParserVisitor ) return ((FormParserVisitor<? extends T>)visitor).visitStat_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Stat_blockContext stat_block() throws RecognitionException {
		Stat_blockContext _localctx = new Stat_blockContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_stat_block);
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
		public TerminalNode QUESTION_IDENTIFIER() { return getToken(FormParserParser.QUESTION_IDENTIFIER, 0); }
		public Question_identifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).enterQuestion_identifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).exitQuestion_identifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormParserVisitor ) return ((FormParserVisitor<? extends T>)visitor).visitQuestion_identifier(this);
			else return visitor.visitChildren(this);
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
		public TerminalNode CHARACTERS() { return getToken(FormParserParser.CHARACTERS, 0); }
		public Question_variableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).enterQuestion_variable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).exitQuestion_variable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormParserVisitor ) return ((FormParserVisitor<? extends T>)visitor).visitQuestion_variable(this);
			else return visitor.visitChildren(this);
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
		public TerminalNode CHARACTERS() { return getToken(FormParserParser.CHARACTERS, 0); }
		public Question_answer_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_answer_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).enterQuestion_answer_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).exitQuestion_answer_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormParserVisitor ) return ((FormParserVisitor<? extends T>)visitor).visitQuestion_answer_type(this);
			else return visitor.visitChildren(this);
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
		public TerminalNode QUESTION_ANSWER() { return getToken(FormParserParser.QUESTION_ANSWER, 0); }
		public Question_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).enterQuestion_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).exitQuestion_answer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormParserVisitor ) return ((FormParserVisitor<? extends T>)visitor).visitQuestion_answer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Question_answerContext question_answer() throws RecognitionException {
		Question_answerContext _localctx = new Question_answerContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_question_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(QUESTION_ANSWER);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\23C\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\6\3\34\n\3\r\3\16\3\35\3\3\5\3!\n\3\3\4\3\4\3\4\3\4\3"+
		"\4\5\4(\n\4\3\4\5\4+\n\4\3\5\3\5\3\5\3\5\6\5\61\n\5\r\5\16\5\62\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\2\2\13\2\4\6\b"+
		"\n\f\16\20\22\2\2\2>\2\24\3\2\2\2\4\33\3\2\2\2\6\"\3\2\2\2\b,\3\2\2\2"+
		"\n\66\3\2\2\2\f:\3\2\2\2\16<\3\2\2\2\20>\3\2\2\2\22@\3\2\2\2\24\25\7\3"+
		"\2\2\25\26\7\23\2\2\26\27\7\4\2\2\27\30\5\4\3\2\30\31\7\5\2\2\31\3\3\2"+
		"\2\2\32\34\5\6\4\2\33\32\3\2\2\2\34\35\3\2\2\2\35\33\3\2\2\2\35\36\3\2"+
		"\2\2\36 \3\2\2\2\37!\5\b\5\2 \37\3\2\2\2 !\3\2\2\2!\5\3\2\2\2\"#\5\f\7"+
		"\2#$\5\16\b\2$%\7\r\2\2%\'\5\20\t\2&(\7\16\2\2\'&\3\2\2\2\'(\3\2\2\2("+
		"*\3\2\2\2)+\5\22\n\2*)\3\2\2\2*+\3\2\2\2+\7\3\2\2\2,-\7\20\2\2-.\5\n\6"+
		"\2.\60\7\4\2\2/\61\5\6\4\2\60/\3\2\2\2\61\62\3\2\2\2\62\60\3\2\2\2\62"+
		"\63\3\2\2\2\63\64\3\2\2\2\64\65\7\5\2\2\65\t\3\2\2\2\66\67\7\6\2\2\67"+
		"8\5\16\b\289\7\7\2\29\13\3\2\2\2:;\7\f\2\2;\r\3\2\2\2<=\7\23\2\2=\17\3"+
		"\2\2\2>?\7\23\2\2?\21\3\2\2\2@A\7\17\2\2A\23\3\2\2\2\7\35 \'*\62";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}