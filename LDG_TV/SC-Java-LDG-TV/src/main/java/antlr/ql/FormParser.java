// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/SC-Java-LDG-TV/src/main/java/antlr/ql\Form.g4 by ANTLR 4.7
package antlr.ql;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, NUMBERS=6, CURLY_BRACKET_OPEN=7, 
		CURLY_BRACKET_CLOSE=8, BRACKET_OPEN=9, BRACKET_CLOSE=10, OR=11, AND=12, 
		PLUS=13, MINUS=14, TIMES=15, DIV=16, GT=17, GTOEQ=18, STOEQ=19, ST=20, 
		EQ=21, NEQ=22, QUESTION_LABEL=23, QUESTION_VARIABLE_SEPERATOR=24, QUESTION_VARIABLE_VALUE_SEPERATOR=25, 
		IF=26, ELSE=27, WHITESPACE=28, NEWLINE=29, CHARACTERS=30;
	public static final int
		RULE_formBuilder = 0, RULE_formData = 1, RULE_questionNodeStructure = 2, 
		RULE_ifStructure = 3, RULE_elseStructure = 4, RULE_statementBlockStructure = 5, 
		RULE_value = 6, RULE_label = 7, RULE_variable = 8, RULE_variableType = 9, 
		RULE_variableValue = 10, RULE_expression = 11, RULE_booleanExpression = 12, 
		RULE_unaryBooleanExpression = 13, RULE_gtExpression = 14, RULE_gteoqExpression = 15, 
		RULE_stExpression = 16, RULE_stoeqExpression = 17, RULE_eqExpression = 18, 
		RULE_neqExpression = 19, RULE_arithmeticExpression = 20, RULE_mulExpression = 21, 
		RULE_addExpression = 22, RULE_minExpression = 23, RULE_divExpression = 24, 
		RULE_condition = 25, RULE_conditions = 26, RULE_booleanOperator = 27;
	public static final String[] ruleNames = {
		"formBuilder", "formData", "questionNodeStructure", "ifStructure", "elseStructure", 
		"statementBlockStructure", "value", "label", "variable", "variableType", 
		"variableValue", "expression", "booleanExpression", "unaryBooleanExpression", 
		"gtExpression", "gteoqExpression", "stExpression", "stoeqExpression", 
		"eqExpression", "neqExpression", "arithmeticExpression", "mulExpression", 
		"addExpression", "minExpression", "divExpression", "condition", "conditions", 
		"booleanOperator"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'boolean'", "'money'", "'string'", "'!'", null, "'{'", 
		"'}'", "'('", "')'", "'||'", "'&&'", "'+'", "'-'", "'*'", "'/'", "'>'", 
		"'>='", "'=<'", "'<'", "'=='", "'!='", null, "':'", "'='", "'if'", "'else'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "NUMBERS", "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", 
		"BRACKET_OPEN", "BRACKET_CLOSE", "OR", "AND", "PLUS", "MINUS", "TIMES", 
		"DIV", "GT", "GTOEQ", "STOEQ", "ST", "EQ", "NEQ", "QUESTION_LABEL", "QUESTION_VARIABLE_SEPERATOR", 
		"QUESTION_VARIABLE_VALUE_SEPERATOR", "IF", "ELSE", "WHITESPACE", "NEWLINE", 
		"CHARACTERS"
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
	public static class FormBuilderContext extends ParserRuleContext {
		public TerminalNode CHARACTERS() { return getToken(FormParser.CHARACTERS, 0); }
		public TerminalNode CURLY_BRACKET_OPEN() { return getToken(FormParser.CURLY_BRACKET_OPEN, 0); }
		public FormDataContext formData() {
			return getRuleContext(FormDataContext.class,0);
		}
		public TerminalNode CURLY_BRACKET_CLOSE() { return getToken(FormParser.CURLY_BRACKET_CLOSE, 0); }
		public FormBuilderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formBuilder; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterFormBuilder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitFormBuilder(this);
		}
	}

	public final FormBuilderContext formBuilder() throws RecognitionException {
		FormBuilderContext _localctx = new FormBuilderContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_formBuilder);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T__0);
			setState(57);
			match(CHARACTERS);
			setState(58);
			match(CURLY_BRACKET_OPEN);
			setState(59);
			formData();
			setState(60);
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

	public static class FormDataContext extends ParserRuleContext {
		public List<QuestionNodeStructureContext> questionNodeStructure() {
			return getRuleContexts(QuestionNodeStructureContext.class);
		}
		public QuestionNodeStructureContext questionNodeStructure(int i) {
			return getRuleContext(QuestionNodeStructureContext.class,i);
		}
		public List<IfStructureContext> ifStructure() {
			return getRuleContexts(IfStructureContext.class);
		}
		public IfStructureContext ifStructure(int i) {
			return getRuleContext(IfStructureContext.class,i);
		}
		public FormDataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formData; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterFormData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitFormData(this);
		}
	}

	public final FormDataContext formData() throws RecognitionException {
		FormDataContext _localctx = new FormDataContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_formData);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(62);
				questionNodeStructure();
				setState(64);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(63);
					questionNodeStructure();
					}
					break;
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IF) {
					{
					setState(66);
					ifStructure();
					}
				}

				}
				}
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUESTION_LABEL );
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

	public static class QuestionNodeStructureContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode QUESTION_VARIABLE_SEPERATOR() { return getToken(FormParser.QUESTION_VARIABLE_SEPERATOR, 0); }
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public TerminalNode QUESTION_VARIABLE_VALUE_SEPERATOR() { return getToken(FormParser.QUESTION_VARIABLE_VALUE_SEPERATOR, 0); }
		public VariableValueContext variableValue() {
			return getRuleContext(VariableValueContext.class,0);
		}
		public QuestionNodeStructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionNodeStructure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterQuestionNodeStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitQuestionNodeStructure(this);
		}
	}

	public final QuestionNodeStructureContext questionNodeStructure() throws RecognitionException {
		QuestionNodeStructureContext _localctx = new QuestionNodeStructureContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_questionNodeStructure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			label();
			setState(74);
			variable();
			setState(75);
			match(QUESTION_VARIABLE_SEPERATOR);
			setState(76);
			variableType();
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION_VARIABLE_VALUE_SEPERATOR) {
				{
				setState(77);
				match(QUESTION_VARIABLE_VALUE_SEPERATOR);
				}
			}

			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBERS) | (1L << BRACKET_OPEN) | (1L << CHARACTERS))) != 0)) {
				{
				setState(80);
				variableValue();
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

	public static class IfStructureContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(FormParser.IF, 0); }
		public StatementBlockStructureContext statementBlockStructure() {
			return getRuleContext(StatementBlockStructureContext.class,0);
		}
		public TerminalNode CURLY_BRACKET_OPEN() { return getToken(FormParser.CURLY_BRACKET_OPEN, 0); }
		public TerminalNode CURLY_BRACKET_CLOSE() { return getToken(FormParser.CURLY_BRACKET_CLOSE, 0); }
		public List<QuestionNodeStructureContext> questionNodeStructure() {
			return getRuleContexts(QuestionNodeStructureContext.class);
		}
		public QuestionNodeStructureContext questionNodeStructure(int i) {
			return getRuleContext(QuestionNodeStructureContext.class,i);
		}
		public ElseStructureContext elseStructure() {
			return getRuleContext(ElseStructureContext.class,0);
		}
		public IfStructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStructure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterIfStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitIfStructure(this);
		}
	}

	public final IfStructureContext ifStructure() throws RecognitionException {
		IfStructureContext _localctx = new IfStructureContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ifStructure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(IF);
			setState(84);
			statementBlockStructure();
			setState(85);
			match(CURLY_BRACKET_OPEN);
			setState(87); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(86);
				questionNodeStructure();
				}
				}
				setState(89); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUESTION_LABEL );
			setState(91);
			match(CURLY_BRACKET_CLOSE);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(92);
				elseStructure();
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

	public static class ElseStructureContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(FormParser.ELSE, 0); }
		public TerminalNode CURLY_BRACKET_OPEN() { return getToken(FormParser.CURLY_BRACKET_OPEN, 0); }
		public TerminalNode CURLY_BRACKET_CLOSE() { return getToken(FormParser.CURLY_BRACKET_CLOSE, 0); }
		public List<QuestionNodeStructureContext> questionNodeStructure() {
			return getRuleContexts(QuestionNodeStructureContext.class);
		}
		public QuestionNodeStructureContext questionNodeStructure(int i) {
			return getRuleContext(QuestionNodeStructureContext.class,i);
		}
		public ElseStructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseStructure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterElseStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitElseStructure(this);
		}
	}

	public final ElseStructureContext elseStructure() throws RecognitionException {
		ElseStructureContext _localctx = new ElseStructureContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_elseStructure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(ELSE);
			setState(96);
			match(CURLY_BRACKET_OPEN);
			setState(98); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(97);
				questionNodeStructure();
				}
				}
				setState(100); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUESTION_LABEL );
			setState(102);
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

	public static class StatementBlockStructureContext extends ParserRuleContext {
		public TerminalNode BRACKET_OPEN() { return getToken(FormParser.BRACKET_OPEN, 0); }
		public ConditionsContext conditions() {
			return getRuleContext(ConditionsContext.class,0);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(FormParser.BRACKET_CLOSE, 0); }
		public StatementBlockStructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementBlockStructure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterStatementBlockStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitStatementBlockStructure(this);
		}
	}

	public final StatementBlockStructureContext statementBlockStructure() throws RecognitionException {
		StatementBlockStructureContext _localctx = new StatementBlockStructureContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statementBlockStructure);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(BRACKET_OPEN);
			setState(105);
			conditions();
			setState(106);
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

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode CHARACTERS() { return getToken(FormParser.CHARACTERS, 0); }
		public TerminalNode NUMBERS() { return getToken(FormParser.NUMBERS, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			_la = _input.LA(1);
			if ( !(_la==NUMBERS || _la==CHARACTERS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class LabelContext extends ParserRuleContext {
		public TerminalNode QUESTION_LABEL() { return getToken(FormParser.QUESTION_LABEL, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitLabel(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(QUESTION_LABEL);
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode CHARACTERS() { return getToken(FormParser.CHARACTERS, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
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

	public static class VariableTypeContext extends ParserRuleContext {
		public VariableTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterVariableType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitVariableType(this);
		}
	}

	public final VariableTypeContext variableType() throws RecognitionException {
		VariableTypeContext _localctx = new VariableTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_variableType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class VariableValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public VariableValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterVariableValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitVariableValue(this);
		}
	}

	public final VariableValueContext variableValue() throws RecognitionException {
		VariableValueContext _localctx = new VariableValueContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_variableValue);
		try {
			setState(118);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BRACKET_OPEN:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				expression();
				}
				break;
			case NUMBERS:
			case CHARACTERS:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				value();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode BRACKET_OPEN() { return getToken(FormParser.BRACKET_OPEN, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(FormParser.BRACKET_CLOSE, 0); }
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public ArithmeticExpressionContext arithmeticExpression() {
			return getRuleContext(ArithmeticExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(BRACKET_OPEN);
			setState(123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(121);
				booleanExpression();
				}
				break;
			case 2:
				{
				setState(122);
				arithmeticExpression();
				}
				break;
			}
			setState(125);
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

	public static class BooleanExpressionContext extends ParserRuleContext {
		public UnaryBooleanExpressionContext unaryBooleanExpression() {
			return getRuleContext(UnaryBooleanExpressionContext.class,0);
		}
		public GteoqExpressionContext gteoqExpression() {
			return getRuleContext(GteoqExpressionContext.class,0);
		}
		public GtExpressionContext gtExpression() {
			return getRuleContext(GtExpressionContext.class,0);
		}
		public StExpressionContext stExpression() {
			return getRuleContext(StExpressionContext.class,0);
		}
		public StoeqExpressionContext stoeqExpression() {
			return getRuleContext(StoeqExpressionContext.class,0);
		}
		public EqExpressionContext eqExpression() {
			return getRuleContext(EqExpressionContext.class,0);
		}
		public NeqExpressionContext neqExpression() {
			return getRuleContext(NeqExpressionContext.class,0);
		}
		public BooleanExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterBooleanExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitBooleanExpression(this);
		}
	}

	public final BooleanExpressionContext booleanExpression() throws RecognitionException {
		BooleanExpressionContext _localctx = new BooleanExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_booleanExpression);
		try {
			setState(134);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				unaryBooleanExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				gteoqExpression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(129);
				gtExpression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(130);
				stExpression();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(131);
				stoeqExpression();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(132);
				eqExpression();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(133);
				neqExpression();
				}
				break;
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

	public static class UnaryBooleanExpressionContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public UnaryBooleanExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryBooleanExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterUnaryBooleanExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitUnaryBooleanExpression(this);
		}
	}

	public final UnaryBooleanExpressionContext unaryBooleanExpression() throws RecognitionException {
		UnaryBooleanExpressionContext _localctx = new UnaryBooleanExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_unaryBooleanExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__4);
			setState(137);
			value();
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

	public static class GtExpressionContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode GT() { return getToken(FormParser.GT, 0); }
		public GtExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gtExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterGtExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitGtExpression(this);
		}
	}

	public final GtExpressionContext gtExpression() throws RecognitionException {
		GtExpressionContext _localctx = new GtExpressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_gtExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			variable();
			setState(140);
			match(GT);
			setState(141);
			variable();
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

	public static class GteoqExpressionContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode GTOEQ() { return getToken(FormParser.GTOEQ, 0); }
		public GteoqExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gteoqExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterGteoqExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitGteoqExpression(this);
		}
	}

	public final GteoqExpressionContext gteoqExpression() throws RecognitionException {
		GteoqExpressionContext _localctx = new GteoqExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_gteoqExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			variable();
			setState(144);
			match(GTOEQ);
			setState(145);
			variable();
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

	public static class StExpressionContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode ST() { return getToken(FormParser.ST, 0); }
		public StExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterStExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitStExpression(this);
		}
	}

	public final StExpressionContext stExpression() throws RecognitionException {
		StExpressionContext _localctx = new StExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_stExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			variable();
			setState(148);
			match(ST);
			setState(149);
			variable();
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

	public static class StoeqExpressionContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode STOEQ() { return getToken(FormParser.STOEQ, 0); }
		public StoeqExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stoeqExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterStoeqExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitStoeqExpression(this);
		}
	}

	public final StoeqExpressionContext stoeqExpression() throws RecognitionException {
		StoeqExpressionContext _localctx = new StoeqExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_stoeqExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			variable();
			setState(152);
			match(STOEQ);
			setState(153);
			variable();
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

	public static class EqExpressionContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode EQ() { return getToken(FormParser.EQ, 0); }
		public EqExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eqExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterEqExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitEqExpression(this);
		}
	}

	public final EqExpressionContext eqExpression() throws RecognitionException {
		EqExpressionContext _localctx = new EqExpressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_eqExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			variable();
			setState(156);
			match(EQ);
			setState(157);
			variable();
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

	public static class NeqExpressionContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode NEQ() { return getToken(FormParser.NEQ, 0); }
		public NeqExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_neqExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterNeqExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitNeqExpression(this);
		}
	}

	public final NeqExpressionContext neqExpression() throws RecognitionException {
		NeqExpressionContext _localctx = new NeqExpressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_neqExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			variable();
			setState(160);
			match(NEQ);
			setState(161);
			variable();
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

	public static class ArithmeticExpressionContext extends ParserRuleContext {
		public MulExpressionContext mulExpression() {
			return getRuleContext(MulExpressionContext.class,0);
		}
		public DivExpressionContext divExpression() {
			return getRuleContext(DivExpressionContext.class,0);
		}
		public AddExpressionContext addExpression() {
			return getRuleContext(AddExpressionContext.class,0);
		}
		public MinExpressionContext minExpression() {
			return getRuleContext(MinExpressionContext.class,0);
		}
		public ArithmeticExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterArithmeticExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitArithmeticExpression(this);
		}
	}

	public final ArithmeticExpressionContext arithmeticExpression() throws RecognitionException {
		ArithmeticExpressionContext _localctx = new ArithmeticExpressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_arithmeticExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(163);
				mulExpression();
				}
				break;
			case 2:
				{
				setState(164);
				divExpression();
				}
				break;
			case 3:
				{
				setState(165);
				addExpression();
				}
				break;
			case 4:
				{
				setState(166);
				minExpression();
				}
				break;
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

	public static class MulExpressionContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode TIMES() { return getToken(FormParser.TIMES, 0); }
		public MulExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterMulExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitMulExpression(this);
		}
	}

	public final MulExpressionContext mulExpression() throws RecognitionException {
		MulExpressionContext _localctx = new MulExpressionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_mulExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			variable();
			setState(170);
			match(TIMES);
			setState(171);
			variable();
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

	public static class AddExpressionContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(FormParser.PLUS, 0); }
		public AddExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterAddExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitAddExpression(this);
		}
	}

	public final AddExpressionContext addExpression() throws RecognitionException {
		AddExpressionContext _localctx = new AddExpressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_addExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			variable();
			setState(174);
			match(PLUS);
			setState(175);
			variable();
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

	public static class MinExpressionContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode MINUS() { return getToken(FormParser.MINUS, 0); }
		public MinExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterMinExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitMinExpression(this);
		}
	}

	public final MinExpressionContext minExpression() throws RecognitionException {
		MinExpressionContext _localctx = new MinExpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_minExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			variable();
			setState(178);
			match(MINUS);
			setState(179);
			variable();
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

	public static class DivExpressionContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode DIV() { return getToken(FormParser.DIV, 0); }
		public DivExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_divExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterDivExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitDivExpression(this);
		}
	}

	public final DivExpressionContext divExpression() throws RecognitionException {
		DivExpressionContext _localctx = new DivExpressionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_divExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			variable();
			setState(182);
			match(DIV);
			setState(183);
			variable();
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

	public static class ConditionContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBERS:
			case CHARACTERS:
				{
				setState(185);
				value();
				}
				break;
			case BRACKET_OPEN:
				{
				setState(186);
				expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ConditionsContext extends ParserRuleContext {
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<BooleanOperatorContext> booleanOperator() {
			return getRuleContexts(BooleanOperatorContext.class);
		}
		public BooleanOperatorContext booleanOperator(int i) {
			return getRuleContext(BooleanOperatorContext.class,i);
		}
		public ConditionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterConditions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitConditions(this);
		}
	}

	public final ConditionsContext conditions() throws RecognitionException {
		ConditionsContext _localctx = new ConditionsContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_conditions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(189);
				condition();
				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OR || _la==AND) {
					{
					setState(190);
					booleanOperator();
					}
				}

				setState(194);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(193);
					condition();
					}
					break;
				}
				}
				}
				setState(198); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBERS) | (1L << BRACKET_OPEN) | (1L << CHARACTERS))) != 0) );
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

	public static class BooleanOperatorContext extends ParserRuleContext {
		public TerminalNode OR() { return getToken(FormParser.OR, 0); }
		public TerminalNode AND() { return getToken(FormParser.AND, 0); }
		public BooleanOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterBooleanOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitBooleanOperator(this);
		}
	}

	public final BooleanOperatorContext booleanOperator() throws RecognitionException {
		BooleanOperatorContext _localctx = new BooleanOperatorContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_booleanOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			_la = _input.LA(1);
			if ( !(_la==OR || _la==AND) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u00cd\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3"+
		"\3\5\3C\n\3\3\3\5\3F\n\3\6\3H\n\3\r\3\16\3I\3\4\3\4\3\4\3\4\3\4\5\4Q\n"+
		"\4\3\4\5\4T\n\4\3\5\3\5\3\5\3\5\6\5Z\n\5\r\5\16\5[\3\5\3\5\5\5`\n\5\3"+
		"\6\3\6\3\6\6\6e\n\6\r\6\16\6f\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\5\fy\n\f\3\r\3\r\3\r\5\r~\n\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0089\n\16\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\5\26\u00aa"+
		"\n\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\32\3\33\3\33\5\33\u00be\n\33\3\34\3\34\5\34\u00c2\n\34\3"+
		"\34\5\34\u00c5\n\34\6\34\u00c7\n\34\r\34\16\34\u00c8\3\35\3\35\3\35\2"+
		"\2\36\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668\2\5"+
		"\4\2\b\b  \3\2\4\6\3\2\r\16\2\u00c7\2:\3\2\2\2\4G\3\2\2\2\6K\3\2\2\2\b"+
		"U\3\2\2\2\na\3\2\2\2\fj\3\2\2\2\16n\3\2\2\2\20p\3\2\2\2\22r\3\2\2\2\24"+
		"t\3\2\2\2\26x\3\2\2\2\30z\3\2\2\2\32\u0088\3\2\2\2\34\u008a\3\2\2\2\36"+
		"\u008d\3\2\2\2 \u0091\3\2\2\2\"\u0095\3\2\2\2$\u0099\3\2\2\2&\u009d\3"+
		"\2\2\2(\u00a1\3\2\2\2*\u00a9\3\2\2\2,\u00ab\3\2\2\2.\u00af\3\2\2\2\60"+
		"\u00b3\3\2\2\2\62\u00b7\3\2\2\2\64\u00bd\3\2\2\2\66\u00c6\3\2\2\28\u00ca"+
		"\3\2\2\2:;\7\3\2\2;<\7 \2\2<=\7\t\2\2=>\5\4\3\2>?\7\n\2\2?\3\3\2\2\2@"+
		"B\5\6\4\2AC\5\6\4\2BA\3\2\2\2BC\3\2\2\2CE\3\2\2\2DF\5\b\5\2ED\3\2\2\2"+
		"EF\3\2\2\2FH\3\2\2\2G@\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\5\3\2\2"+
		"\2KL\5\20\t\2LM\5\22\n\2MN\7\32\2\2NP\5\24\13\2OQ\7\33\2\2PO\3\2\2\2P"+
		"Q\3\2\2\2QS\3\2\2\2RT\5\26\f\2SR\3\2\2\2ST\3\2\2\2T\7\3\2\2\2UV\7\34\2"+
		"\2VW\5\f\7\2WY\7\t\2\2XZ\5\6\4\2YX\3\2\2\2Z[\3\2\2\2[Y\3\2\2\2[\\\3\2"+
		"\2\2\\]\3\2\2\2]_\7\n\2\2^`\5\n\6\2_^\3\2\2\2_`\3\2\2\2`\t\3\2\2\2ab\7"+
		"\35\2\2bd\7\t\2\2ce\5\6\4\2dc\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2gh"+
		"\3\2\2\2hi\7\n\2\2i\13\3\2\2\2jk\7\13\2\2kl\5\66\34\2lm\7\f\2\2m\r\3\2"+
		"\2\2no\t\2\2\2o\17\3\2\2\2pq\7\31\2\2q\21\3\2\2\2rs\7 \2\2s\23\3\2\2\2"+
		"tu\t\3\2\2u\25\3\2\2\2vy\5\30\r\2wy\5\16\b\2xv\3\2\2\2xw\3\2\2\2y\27\3"+
		"\2\2\2z}\7\13\2\2{~\5\32\16\2|~\5*\26\2}{\3\2\2\2}|\3\2\2\2~\177\3\2\2"+
		"\2\177\u0080\7\f\2\2\u0080\31\3\2\2\2\u0081\u0089\5\34\17\2\u0082\u0089"+
		"\5 \21\2\u0083\u0089\5\36\20\2\u0084\u0089\5\"\22\2\u0085\u0089\5$\23"+
		"\2\u0086\u0089\5&\24\2\u0087\u0089\5(\25\2\u0088\u0081\3\2\2\2\u0088\u0082"+
		"\3\2\2\2\u0088\u0083\3\2\2\2\u0088\u0084\3\2\2\2\u0088\u0085\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0088\u0087\3\2\2\2\u0089\33\3\2\2\2\u008a\u008b\7\7\2"+
		"\2\u008b\u008c\5\16\b\2\u008c\35\3\2\2\2\u008d\u008e\5\22\n\2\u008e\u008f"+
		"\7\23\2\2\u008f\u0090\5\22\n\2\u0090\37\3\2\2\2\u0091\u0092\5\22\n\2\u0092"+
		"\u0093\7\24\2\2\u0093\u0094\5\22\n\2\u0094!\3\2\2\2\u0095\u0096\5\22\n"+
		"\2\u0096\u0097\7\26\2\2\u0097\u0098\5\22\n\2\u0098#\3\2\2\2\u0099\u009a"+
		"\5\22\n\2\u009a\u009b\7\25\2\2\u009b\u009c\5\22\n\2\u009c%\3\2\2\2\u009d"+
		"\u009e\5\22\n\2\u009e\u009f\7\27\2\2\u009f\u00a0\5\22\n\2\u00a0\'\3\2"+
		"\2\2\u00a1\u00a2\5\22\n\2\u00a2\u00a3\7\30\2\2\u00a3\u00a4\5\22\n\2\u00a4"+
		")\3\2\2\2\u00a5\u00aa\5,\27\2\u00a6\u00aa\5\62\32\2\u00a7\u00aa\5.\30"+
		"\2\u00a8\u00aa\5\60\31\2\u00a9\u00a5\3\2\2\2\u00a9\u00a6\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00a9\u00a8\3\2\2\2\u00aa+\3\2\2\2\u00ab\u00ac\5\22\n\2"+
		"\u00ac\u00ad\7\21\2\2\u00ad\u00ae\5\22\n\2\u00ae-\3\2\2\2\u00af\u00b0"+
		"\5\22\n\2\u00b0\u00b1\7\17\2\2\u00b1\u00b2\5\22\n\2\u00b2/\3\2\2\2\u00b3"+
		"\u00b4\5\22\n\2\u00b4\u00b5\7\20\2\2\u00b5\u00b6\5\22\n\2\u00b6\61\3\2"+
		"\2\2\u00b7\u00b8\5\22\n\2\u00b8\u00b9\7\22\2\2\u00b9\u00ba\5\22\n\2\u00ba"+
		"\63\3\2\2\2\u00bb\u00be\5\16\b\2\u00bc\u00be\5\30\r\2\u00bd\u00bb\3\2"+
		"\2\2\u00bd\u00bc\3\2\2\2\u00be\65\3\2\2\2\u00bf\u00c1\5\64\33\2\u00c0"+
		"\u00c2\58\35\2\u00c1\u00c0\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c4\3\2"+
		"\2\2\u00c3\u00c5\5\64\33\2\u00c4\u00c3\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5"+
		"\u00c7\3\2\2\2\u00c6\u00bf\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c6\3\2"+
		"\2\2\u00c8\u00c9\3\2\2\2\u00c9\67\3\2\2\2\u00ca\u00cb\t\4\2\2\u00cb9\3"+
		"\2\2\2\22BEIPS[_fx}\u0088\u00a9\u00bd\u00c1\u00c4\u00c8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}