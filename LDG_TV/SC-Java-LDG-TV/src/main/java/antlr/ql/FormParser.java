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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, NUMBERS=16, 
		CURLY_BRACKET_OPEN=17, CURLY_BRACKET_CLOSE=18, BRACKET_OPEN=19, BRACKET_CLOSE=20, 
		OR=21, AND=22, QUESTION_LABEL=23, QUESTION_VARIABLE_SEPERATOR=24, QUESTION_VARIABLE_VALUE_SEPERATOR=25, 
		IF=26, ELSE=27, WHITESPACE=28, NEWLINE=29, CHARACTERS=30;
	public static final int
		RULE_formBuilder = 0, RULE_formData = 1, RULE_questionNodeStructure = 2, 
		RULE_ifStructure = 3, RULE_elseStructure = 4, RULE_statementBlockStructure = 5, 
		RULE_value = 6, RULE_label = 7, RULE_variable = 8, RULE_variableType = 9, 
		RULE_variableValue = 10, RULE_expression = 11, RULE_booleanExpression = 12, 
		RULE_arithmeticExpression = 13, RULE_arithmeticExpressionOperator = 14, 
		RULE_booleanExpressionOperator = 15, RULE_condition = 16, RULE_conditions = 17, 
		RULE_booleanOperator = 18;
	public static final String[] ruleNames = {
		"formBuilder", "formData", "questionNodeStructure", "ifStructure", "elseStructure", 
		"statementBlockStructure", "value", "label", "variable", "variableType", 
		"variableValue", "expression", "booleanExpression", "arithmeticExpression", 
		"arithmeticExpressionOperator", "booleanExpressionOperator", "condition", 
		"conditions", "booleanOperator"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'boolean'", "'money'", "'string'", "'*'", "'+'", "'-'", 
		"'/'", "'<'", "'>'", "'=<'", "'>='", "'!'", "'!='", "'=='", null, "'{'", 
		"'}'", "'('", "')'", "'||'", "'&&'", null, "':'", "'='", "'if'", "'else'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "NUMBERS", "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", 
		"BRACKET_OPEN", "BRACKET_CLOSE", "OR", "AND", "QUESTION_LABEL", "QUESTION_VARIABLE_SEPERATOR", 
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
			setState(38);
			match(T__0);
			setState(39);
			match(CHARACTERS);
			setState(40);
			match(CURLY_BRACKET_OPEN);
			setState(41);
			formData();
			setState(42);
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
			setState(51); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(44);
				questionNodeStructure();
				setState(46);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(45);
					questionNodeStructure();
					}
					break;
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IF) {
					{
					setState(48);
					ifStructure();
					}
				}

				}
				}
				setState(53); 
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
			setState(55);
			label();
			setState(56);
			variable();
			setState(57);
			match(QUESTION_VARIABLE_SEPERATOR);
			setState(58);
			variableType();
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION_VARIABLE_VALUE_SEPERATOR) {
				{
				setState(59);
				match(QUESTION_VARIABLE_VALUE_SEPERATOR);
				}
			}

			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBERS) | (1L << BRACKET_OPEN) | (1L << CHARACTERS))) != 0)) {
				{
				setState(62);
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
			setState(65);
			match(IF);
			setState(66);
			statementBlockStructure();
			setState(67);
			match(CURLY_BRACKET_OPEN);
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68);
				questionNodeStructure();
				}
				}
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUESTION_LABEL );
			setState(73);
			match(CURLY_BRACKET_CLOSE);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(74);
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
			setState(77);
			match(ELSE);
			setState(78);
			match(CURLY_BRACKET_OPEN);
			setState(80); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(79);
				questionNodeStructure();
				}
				}
				setState(82); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUESTION_LABEL );
			setState(84);
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
			setState(86);
			match(BRACKET_OPEN);
			setState(87);
			conditions();
			setState(88);
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
			setState(90);
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
			setState(92);
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
			setState(94);
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
			setState(96);
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
			setState(100);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BRACKET_OPEN:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				expression();
				}
				break;
			case NUMBERS:
			case CHARACTERS:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
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
			setState(102);
			match(BRACKET_OPEN);
			setState(105);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(103);
				booleanExpression();
				}
				break;
			case 2:
				{
				setState(104);
				arithmeticExpression();
				}
				break;
			}
			setState(107);
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
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public BooleanExpressionOperatorContext booleanExpressionOperator() {
			return getRuleContext(BooleanExpressionOperatorContext.class,0);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			variable();
			setState(110);
			booleanExpressionOperator();
			setState(111);
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
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public ArithmeticExpressionOperatorContext arithmeticExpressionOperator() {
			return getRuleContext(ArithmeticExpressionOperatorContext.class,0);
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
		enterRule(_localctx, 26, RULE_arithmeticExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			variable();
			setState(114);
			arithmeticExpressionOperator();
			setState(115);
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

	public static class ArithmeticExpressionOperatorContext extends ParserRuleContext {
		public ArithmeticExpressionOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticExpressionOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterArithmeticExpressionOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitArithmeticExpressionOperator(this);
		}
	}

	public final ArithmeticExpressionOperatorContext arithmeticExpressionOperator() throws RecognitionException {
		ArithmeticExpressionOperatorContext _localctx = new ArithmeticExpressionOperatorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_arithmeticExpressionOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) ) {
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

	public static class BooleanExpressionOperatorContext extends ParserRuleContext {
		public BooleanExpressionOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanExpressionOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterBooleanExpressionOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitBooleanExpressionOperator(this);
		}
	}

	public final BooleanExpressionOperatorContext booleanExpressionOperator() throws RecognitionException {
		BooleanExpressionOperatorContext _localctx = new BooleanExpressionOperatorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_booleanExpressionOperator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				{
				setState(119);
				match(T__8);
				}
				break;
			case T__9:
				{
				setState(120);
				match(T__9);
				}
				break;
			case T__10:
				{
				setState(121);
				match(T__10);
				setState(122);
				match(T__11);
				}
				break;
			case T__12:
				{
				setState(123);
				match(T__12);
				}
				break;
			case T__13:
				{
				setState(124);
				match(T__13);
				setState(125);
				match(T__14);
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
		enterRule(_localctx, 32, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBERS:
			case CHARACTERS:
				{
				setState(128);
				value();
				}
				break;
			case BRACKET_OPEN:
				{
				setState(129);
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
		enterRule(_localctx, 34, RULE_conditions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(132);
				condition();
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OR || _la==AND) {
					{
					setState(133);
					booleanOperator();
					}
				}

				setState(137);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(136);
					condition();
					}
					break;
				}
				}
				}
				setState(141); 
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
		enterRule(_localctx, 36, RULE_booleanOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u0094\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\5\3\61\n\3\3\3\5"+
		"\3\64\n\3\6\3\66\n\3\r\3\16\3\67\3\4\3\4\3\4\3\4\3\4\5\4?\n\4\3\4\5\4"+
		"B\n\4\3\5\3\5\3\5\3\5\6\5H\n\5\r\5\16\5I\3\5\3\5\5\5N\n\5\3\6\3\6\3\6"+
		"\6\6S\n\6\r\6\16\6T\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3"+
		"\13\3\13\3\f\3\f\5\fg\n\f\3\r\3\r\3\r\5\rl\n\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\5\21\u0081\n\21\3\22\3\22\5\22\u0085\n\22\3\23\3\23\5\23\u0089\n\23\3"+
		"\23\5\23\u008c\n\23\6\23\u008e\n\23\r\23\16\23\u008f\3\24\3\24\3\24\2"+
		"\2\25\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&\2\6\4\2\22\22  \3\2"+
		"\4\6\3\2\7\n\3\2\27\30\2\u0092\2(\3\2\2\2\4\65\3\2\2\2\69\3\2\2\2\bC\3"+
		"\2\2\2\nO\3\2\2\2\fX\3\2\2\2\16\\\3\2\2\2\20^\3\2\2\2\22`\3\2\2\2\24b"+
		"\3\2\2\2\26f\3\2\2\2\30h\3\2\2\2\32o\3\2\2\2\34s\3\2\2\2\36w\3\2\2\2 "+
		"\u0080\3\2\2\2\"\u0084\3\2\2\2$\u008d\3\2\2\2&\u0091\3\2\2\2()\7\3\2\2"+
		")*\7 \2\2*+\7\23\2\2+,\5\4\3\2,-\7\24\2\2-\3\3\2\2\2.\60\5\6\4\2/\61\5"+
		"\6\4\2\60/\3\2\2\2\60\61\3\2\2\2\61\63\3\2\2\2\62\64\5\b\5\2\63\62\3\2"+
		"\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65.\3\2\2\2\66\67\3\2\2\2\67\65\3\2\2"+
		"\2\678\3\2\2\28\5\3\2\2\29:\5\20\t\2:;\5\22\n\2;<\7\32\2\2<>\5\24\13\2"+
		"=?\7\33\2\2>=\3\2\2\2>?\3\2\2\2?A\3\2\2\2@B\5\26\f\2A@\3\2\2\2AB\3\2\2"+
		"\2B\7\3\2\2\2CD\7\34\2\2DE\5\f\7\2EG\7\23\2\2FH\5\6\4\2GF\3\2\2\2HI\3"+
		"\2\2\2IG\3\2\2\2IJ\3\2\2\2JK\3\2\2\2KM\7\24\2\2LN\5\n\6\2ML\3\2\2\2MN"+
		"\3\2\2\2N\t\3\2\2\2OP\7\35\2\2PR\7\23\2\2QS\5\6\4\2RQ\3\2\2\2ST\3\2\2"+
		"\2TR\3\2\2\2TU\3\2\2\2UV\3\2\2\2VW\7\24\2\2W\13\3\2\2\2XY\7\25\2\2YZ\5"+
		"$\23\2Z[\7\26\2\2[\r\3\2\2\2\\]\t\2\2\2]\17\3\2\2\2^_\7\31\2\2_\21\3\2"+
		"\2\2`a\7 \2\2a\23\3\2\2\2bc\t\3\2\2c\25\3\2\2\2dg\5\30\r\2eg\5\16\b\2"+
		"fd\3\2\2\2fe\3\2\2\2g\27\3\2\2\2hk\7\25\2\2il\5\32\16\2jl\5\34\17\2ki"+
		"\3\2\2\2kj\3\2\2\2lm\3\2\2\2mn\7\26\2\2n\31\3\2\2\2op\5\22\n\2pq\5 \21"+
		"\2qr\5\22\n\2r\33\3\2\2\2st\5\22\n\2tu\5\36\20\2uv\5\22\n\2v\35\3\2\2"+
		"\2wx\t\4\2\2x\37\3\2\2\2y\u0081\7\13\2\2z\u0081\7\f\2\2{|\7\r\2\2|\u0081"+
		"\7\16\2\2}\u0081\7\17\2\2~\177\7\20\2\2\177\u0081\7\21\2\2\u0080y\3\2"+
		"\2\2\u0080z\3\2\2\2\u0080{\3\2\2\2\u0080}\3\2\2\2\u0080~\3\2\2\2\u0081"+
		"!\3\2\2\2\u0082\u0085\5\16\b\2\u0083\u0085\5\30\r\2\u0084\u0082\3\2\2"+
		"\2\u0084\u0083\3\2\2\2\u0085#\3\2\2\2\u0086\u0088\5\"\22\2\u0087\u0089"+
		"\5&\24\2\u0088\u0087\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008b\3\2\2\2\u008a"+
		"\u008c\5\"\22\2\u008b\u008a\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008e\3"+
		"\2\2\2\u008d\u0086\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u008d\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090%\3\2\2\2\u0091\u0092\t\5\2\2\u0092\'\3\2\2\2\21\60"+
		"\63\67>AIMTfk\u0080\u0084\u0088\u008b\u008f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}