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
		T__0=1, T__1=2, CHARACTERS=3, NUMBERS=4, CURLY_BRACKET_OPEN=5, CURLY_BRACKET_CLOSE=6, 
		BRACKET_OPEN=7, BRACKET_CLOSE=8, QUESTION_IDENTIFIER=9, QUESTION_VARIABLE_SEPERATOR=10, 
		QUESTION_ANSWER_SEPERATOR=11, QUESTION_ANSWER=12, WHITESPACE=13, NEWLINE=14;
	public static final int
		RULE_form_builder = 0, RULE_form_data = 1, RULE_question_structure = 2, 
		RULE_if_structure = 3, RULE_question_identifier = 4, RULE_question_variable = 5, 
		RULE_question_variable_seperator = 6, RULE_question_answer_type = 7, RULE_question_answer_seperator = 8, 
		RULE_question_answer = 9;
	public static final String[] ruleNames = {
		"form_builder", "form_data", "question_structure", "if_structure", "question_identifier", 
		"question_variable", "question_variable_seperator", "question_answer_type", 
		"question_answer_seperator", "question_answer"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'if'", null, null, "'{'", "'}'", "'('", "')'", null, 
		"':'", "'='", null, "' '", "'\n'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "CHARACTERS", "NUMBERS", "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", 
		"BRACKET_OPEN", "BRACKET_CLOSE", "QUESTION_IDENTIFIER", "QUESTION_VARIABLE_SEPERATOR", 
		"QUESTION_ANSWER_SEPERATOR", "QUESTION_ANSWER", "WHITESPACE", "NEWLINE"
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
			setState(20);
			match(T__0);
			setState(21);
			match(CHARACTERS);
			setState(22);
			match(CURLY_BRACKET_OPEN);
			setState(23);
			form_data();
			setState(24);
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
		public Question_structureContext question_structure() {
			return getRuleContext(Question_structureContext.class,0);
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
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION_IDENTIFIER) {
				{
				setState(26);
				question_structure();
				}
			}

			setState(30);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
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
		public Question_variable_seperatorContext question_variable_seperator() {
			return getRuleContext(Question_variable_seperatorContext.class,0);
		}
		public Question_answer_typeContext question_answer_type() {
			return getRuleContext(Question_answer_typeContext.class,0);
		}
		public Question_answer_seperatorContext question_answer_seperator() {
			return getRuleContext(Question_answer_seperatorContext.class,0);
		}
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
			question_variable_seperator();
			setState(35);
			question_answer_type();
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION_ANSWER_SEPERATOR) {
				{
				setState(36);
				question_answer_seperator();
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
		public TerminalNode BRACKET_OPEN() { return getToken(FormParserParser.BRACKET_OPEN, 0); }
		public Question_variableContext question_variable() {
			return getRuleContext(Question_variableContext.class,0);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(FormParserParser.BRACKET_CLOSE, 0); }
		public TerminalNode CURLY_BRACKET_OPEN() { return getToken(FormParserParser.CURLY_BRACKET_OPEN, 0); }
		public Question_structureContext question_structure() {
			return getRuleContext(Question_structureContext.class,0);
		}
		public TerminalNode CURLY_BRACKET_CLOSE() { return getToken(FormParserParser.CURLY_BRACKET_CLOSE, 0); }
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(T__1);
			setState(43);
			match(BRACKET_OPEN);
			setState(44);
			question_variable();
			setState(45);
			match(BRACKET_CLOSE);
			setState(46);
			match(CURLY_BRACKET_OPEN);
			setState(47);
			question_structure();
			setState(48);
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
		enterRule(_localctx, 8, RULE_question_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
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
		enterRule(_localctx, 10, RULE_question_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
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

	public static class Question_variable_seperatorContext extends ParserRuleContext {
		public TerminalNode QUESTION_VARIABLE_SEPERATOR() { return getToken(FormParserParser.QUESTION_VARIABLE_SEPERATOR, 0); }
		public Question_variable_seperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_variable_seperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).enterQuestion_variable_seperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).exitQuestion_variable_seperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormParserVisitor ) return ((FormParserVisitor<? extends T>)visitor).visitQuestion_variable_seperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Question_variable_seperatorContext question_variable_seperator() throws RecognitionException {
		Question_variable_seperatorContext _localctx = new Question_variable_seperatorContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_question_variable_seperator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(QUESTION_VARIABLE_SEPERATOR);
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
			setState(56);
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

	public static class Question_answer_seperatorContext extends ParserRuleContext {
		public TerminalNode QUESTION_ANSWER_SEPERATOR() { return getToken(FormParserParser.QUESTION_ANSWER_SEPERATOR, 0); }
		public Question_answer_seperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_answer_seperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).enterQuestion_answer_seperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormParserListener ) ((FormParserListener)listener).exitQuestion_answer_seperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormParserVisitor ) return ((FormParserVisitor<? extends T>)visitor).visitQuestion_answer_seperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Question_answer_seperatorContext question_answer_seperator() throws RecognitionException {
		Question_answer_seperatorContext _localctx = new Question_answer_seperatorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_question_answer_seperator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(QUESTION_ANSWER_SEPERATOR);
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
		enterRule(_localctx, 18, RULE_question_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\20A\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\5\3\36\n\3\3\3\5\3!\n\3\3\4\3\4\3\4\3\4\3\4"+
		"\5\4(\n\4\3\4\5\4+\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7"+
		"\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24"+
		"\2\2\2:\2\26\3\2\2\2\4\35\3\2\2\2\6\"\3\2\2\2\b,\3\2\2\2\n\64\3\2\2\2"+
		"\f\66\3\2\2\2\168\3\2\2\2\20:\3\2\2\2\22<\3\2\2\2\24>\3\2\2\2\26\27\7"+
		"\3\2\2\27\30\7\5\2\2\30\31\7\7\2\2\31\32\5\4\3\2\32\33\7\b\2\2\33\3\3"+
		"\2\2\2\34\36\5\6\4\2\35\34\3\2\2\2\35\36\3\2\2\2\36 \3\2\2\2\37!\5\b\5"+
		"\2 \37\3\2\2\2 !\3\2\2\2!\5\3\2\2\2\"#\5\n\6\2#$\5\f\7\2$%\5\16\b\2%\'"+
		"\5\20\t\2&(\5\22\n\2\'&\3\2\2\2\'(\3\2\2\2(*\3\2\2\2)+\5\24\13\2*)\3\2"+
		"\2\2*+\3\2\2\2+\7\3\2\2\2,-\7\4\2\2-.\7\t\2\2./\5\f\7\2/\60\7\n\2\2\60"+
		"\61\7\7\2\2\61\62\5\6\4\2\62\63\7\b\2\2\63\t\3\2\2\2\64\65\7\13\2\2\65"+
		"\13\3\2\2\2\66\67\7\5\2\2\67\r\3\2\2\289\7\f\2\29\17\3\2\2\2:;\7\5\2\2"+
		";\21\3\2\2\2<=\7\r\2\2=\23\3\2\2\2>?\7\16\2\2?\25\3\2\2\2\6\35 \'*";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}