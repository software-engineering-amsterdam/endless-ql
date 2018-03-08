// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/SC-Java-LDG-TV/src/main/java/antlr\Form.g4 by ANTLR 4.7
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
		IF=26, WHITESPACE=27, NEWLINE=28, CHARACTERS=29;
	public static final int
		RULE_formBuilder = 0, RULE_formData = 1, RULE_questionStructure = 2, RULE_ifStructure = 3, 
		RULE_statementBlockStructure = 4, RULE_value = 5, RULE_label = 6, RULE_variable = 7, 
		RULE_variableType = 8, RULE_variableValue = 9, RULE_expression = 10, RULE_booleanExpression = 11, 
		RULE_unaryBooleanExpression = 12, RULE_gtExpression = 13, RULE_gteoqExpression = 14, 
		RULE_stExpression = 15, RULE_stoeqExpression = 16, RULE_eqExpression = 17, 
		RULE_neqExpression = 18, RULE_aritmaticExpression = 19, RULE_mulExpression = 20, 
		RULE_addExpression = 21, RULE_minExpression = 22, RULE_divExpression = 23, 
		RULE_condition = 24, RULE_conditions = 25, RULE_booleanOperator = 26;
	public static final String[] ruleNames = {
		"formBuilder", "formData", "questionStructure", "ifStructure", "statementBlockStructure", 
		"value", "label", "variable", "variableType", "variableValue", "expression", 
		"booleanExpression", "unaryBooleanExpression", "gtExpression", "gteoqExpression", 
		"stExpression", "stoeqExpression", "eqExpression", "neqExpression", "aritmaticExpression", 
		"mulExpression", "addExpression", "minExpression", "divExpression", "condition", 
		"conditions", "booleanOperator"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'boolean'", "'money'", "'string'", "'!'", null, "'{'", 
		"'}'", "'('", "')'", "'||'", "'&&'", "'+'", "'-'", "'*'", "'/'", "'>'", 
		"'>='", "'=<'", "'<'", "'=='", "'!='", null, "':'", "'='", "'if'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "NUMBERS", "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", 
		"BRACKET_OPEN", "BRACKET_CLOSE", "OR", "AND", "PLUS", "MINUS", "TIMES", 
		"DIV", "GT", "GTOEQ", "STOEQ", "ST", "EQ", "NEQ", "QUESTION_LABEL", "QUESTION_VARIABLE_SEPERATOR", 
		"QUESTION_VARIABLE_VALUE_SEPERATOR", "IF", "WHITESPACE", "NEWLINE", "CHARACTERS"
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
			setState(54);
			match(T__0);
			setState(55);
			match(CHARACTERS);
			setState(56);
			match(CURLY_BRACKET_OPEN);
			setState(57);
			formData();
			setState(58);
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
		public List<QuestionStructureContext> questionStructure() {
			return getRuleContexts(QuestionStructureContext.class);
		}
		public QuestionStructureContext questionStructure(int i) {
			return getRuleContext(QuestionStructureContext.class,i);
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(61); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(60);
				questionStructure();
				}
				}
				setState(63); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUESTION_LABEL );
			setState(66); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(65);
					ifStructure();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(68); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class QuestionStructureContext extends ParserRuleContext {
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
		public QuestionStructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionStructure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterQuestionStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitQuestionStructure(this);
		}
	}

	public final QuestionStructureContext questionStructure() throws RecognitionException {
		QuestionStructureContext _localctx = new QuestionStructureContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_questionStructure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			label();
			setState(71);
			variable();
			setState(72);
			match(QUESTION_VARIABLE_SEPERATOR);
			setState(73);
			variableType();
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION_VARIABLE_VALUE_SEPERATOR) {
				{
				setState(74);
				match(QUESTION_VARIABLE_VALUE_SEPERATOR);
				}
			}

			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBERS) | (1L << BRACKET_OPEN) | (1L << CHARACTERS))) != 0)) {
				{
				setState(77);
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
		public List<QuestionStructureContext> questionStructure() {
			return getRuleContexts(QuestionStructureContext.class);
		}
		public QuestionStructureContext questionStructure(int i) {
			return getRuleContext(QuestionStructureContext.class,i);
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
			setState(80);
			match(IF);
			setState(81);
			statementBlockStructure();
			setState(82);
			match(CURLY_BRACKET_OPEN);
			setState(84); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(83);
				questionStructure();
				}
				}
				setState(86); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUESTION_LABEL );
			setState(88);
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
		enterRule(_localctx, 8, RULE_statementBlockStructure);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(BRACKET_OPEN);
			setState(91);
			conditions();
			setState(92);
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
		enterRule(_localctx, 10, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
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
		enterRule(_localctx, 12, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
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
		enterRule(_localctx, 14, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
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
		enterRule(_localctx, 16, RULE_variableType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
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
		enterRule(_localctx, 18, RULE_variableValue);
		try {
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BRACKET_OPEN:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				expression();
				}
				break;
			case NUMBERS:
			case CHARACTERS:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
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
		public AritmaticExpressionContext aritmaticExpression() {
			return getRuleContext(AritmaticExpressionContext.class,0);
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
		enterRule(_localctx, 20, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(BRACKET_OPEN);
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(107);
				booleanExpression();
				}
				break;
			case 2:
				{
				setState(108);
				aritmaticExpression();
				}
				break;
			}
			setState(111);
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
		enterRule(_localctx, 22, RULE_booleanExpression);
		try {
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				unaryBooleanExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				gteoqExpression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(115);
				gtExpression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(116);
				stExpression();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(117);
				stoeqExpression();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(118);
				eqExpression();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(119);
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
		enterRule(_localctx, 24, RULE_unaryBooleanExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(T__4);
			setState(123);
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
		enterRule(_localctx, 26, RULE_gtExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			variable();
			setState(126);
			match(GT);
			setState(127);
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
		enterRule(_localctx, 28, RULE_gteoqExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			variable();
			setState(130);
			match(GTOEQ);
			setState(131);
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
		enterRule(_localctx, 30, RULE_stExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			variable();
			setState(134);
			match(ST);
			setState(135);
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
		enterRule(_localctx, 32, RULE_stoeqExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			variable();
			setState(138);
			match(STOEQ);
			setState(139);
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
		enterRule(_localctx, 34, RULE_eqExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			variable();
			setState(142);
			match(EQ);
			setState(143);
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
		enterRule(_localctx, 36, RULE_neqExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			variable();
			setState(146);
			match(NEQ);
			setState(147);
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

	public static class AritmaticExpressionContext extends ParserRuleContext {
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
		public AritmaticExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aritmaticExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterAritmaticExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitAritmaticExpression(this);
		}
	}

	public final AritmaticExpressionContext aritmaticExpression() throws RecognitionException {
		AritmaticExpressionContext _localctx = new AritmaticExpressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_aritmaticExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(149);
				mulExpression();
				}
				break;
			case 2:
				{
				setState(150);
				divExpression();
				}
				break;
			case 3:
				{
				setState(151);
				addExpression();
				}
				break;
			case 4:
				{
				setState(152);
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
		enterRule(_localctx, 40, RULE_mulExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			variable();
			setState(156);
			match(TIMES);
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
		enterRule(_localctx, 42, RULE_addExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			variable();
			setState(160);
			match(PLUS);
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
		enterRule(_localctx, 44, RULE_minExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			variable();
			setState(164);
			match(MINUS);
			setState(165);
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
		enterRule(_localctx, 46, RULE_divExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			variable();
			setState(168);
			match(DIV);
			setState(169);
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
		enterRule(_localctx, 48, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBERS:
			case CHARACTERS:
				{
				setState(171);
				value();
				}
				break;
			case BRACKET_OPEN:
				{
				setState(172);
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
		enterRule(_localctx, 50, RULE_conditions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(175);
				condition();
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OR || _la==AND) {
					{
					setState(176);
					booleanOperator();
					}
				}

				setState(180);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(179);
					condition();
					}
					break;
				}
				}
				}
				setState(184); 
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
		enterRule(_localctx, 52, RULE_booleanOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\37\u00bf\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3@\n\3\r"+
		"\3\16\3A\3\3\6\3E\n\3\r\3\16\3F\3\4\3\4\3\4\3\4\3\4\5\4N\n\4\3\4\5\4Q"+
		"\n\4\3\5\3\5\3\5\3\5\6\5W\n\5\r\5\16\5X\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\5\13k\n\13\3\f\3\f\3\f\5\fp\n\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r{\n\r\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\5\25\u009c"+
		"\n\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\3\32\3\32\5\32\u00b0\n\32\3\33\3\33\5\33\u00b4\n\33\3"+
		"\33\5\33\u00b7\n\33\6\33\u00b9\n\33\r\33\16\33\u00ba\3\34\3\34\3\34\3"+
		"F\2\35\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66\2\5"+
		"\4\2\b\b\37\37\3\2\4\6\3\2\r\16\2\u00b7\28\3\2\2\2\4?\3\2\2\2\6H\3\2\2"+
		"\2\bR\3\2\2\2\n\\\3\2\2\2\f`\3\2\2\2\16b\3\2\2\2\20d\3\2\2\2\22f\3\2\2"+
		"\2\24j\3\2\2\2\26l\3\2\2\2\30z\3\2\2\2\32|\3\2\2\2\34\177\3\2\2\2\36\u0083"+
		"\3\2\2\2 \u0087\3\2\2\2\"\u008b\3\2\2\2$\u008f\3\2\2\2&\u0093\3\2\2\2"+
		"(\u009b\3\2\2\2*\u009d\3\2\2\2,\u00a1\3\2\2\2.\u00a5\3\2\2\2\60\u00a9"+
		"\3\2\2\2\62\u00af\3\2\2\2\64\u00b8\3\2\2\2\66\u00bc\3\2\2\289\7\3\2\2"+
		"9:\7\37\2\2:;\7\t\2\2;<\5\4\3\2<=\7\n\2\2=\3\3\2\2\2>@\5\6\4\2?>\3\2\2"+
		"\2@A\3\2\2\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CE\5\b\5\2DC\3\2\2\2EF\3\2\2"+
		"\2FG\3\2\2\2FD\3\2\2\2G\5\3\2\2\2HI\5\16\b\2IJ\5\20\t\2JK\7\32\2\2KM\5"+
		"\22\n\2LN\7\33\2\2ML\3\2\2\2MN\3\2\2\2NP\3\2\2\2OQ\5\24\13\2PO\3\2\2\2"+
		"PQ\3\2\2\2Q\7\3\2\2\2RS\7\34\2\2ST\5\n\6\2TV\7\t\2\2UW\5\6\4\2VU\3\2\2"+
		"\2WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z[\7\n\2\2[\t\3\2\2\2\\]\7\13"+
		"\2\2]^\5\64\33\2^_\7\f\2\2_\13\3\2\2\2`a\t\2\2\2a\r\3\2\2\2bc\7\31\2\2"+
		"c\17\3\2\2\2de\7\37\2\2e\21\3\2\2\2fg\t\3\2\2g\23\3\2\2\2hk\5\26\f\2i"+
		"k\5\f\7\2jh\3\2\2\2ji\3\2\2\2k\25\3\2\2\2lo\7\13\2\2mp\5\30\r\2np\5(\25"+
		"\2om\3\2\2\2on\3\2\2\2pq\3\2\2\2qr\7\f\2\2r\27\3\2\2\2s{\5\32\16\2t{\5"+
		"\36\20\2u{\5\34\17\2v{\5 \21\2w{\5\"\22\2x{\5$\23\2y{\5&\24\2zs\3\2\2"+
		"\2zt\3\2\2\2zu\3\2\2\2zv\3\2\2\2zw\3\2\2\2zx\3\2\2\2zy\3\2\2\2{\31\3\2"+
		"\2\2|}\7\7\2\2}~\5\f\7\2~\33\3\2\2\2\177\u0080\5\20\t\2\u0080\u0081\7"+
		"\23\2\2\u0081\u0082\5\20\t\2\u0082\35\3\2\2\2\u0083\u0084\5\20\t\2\u0084"+
		"\u0085\7\24\2\2\u0085\u0086\5\20\t\2\u0086\37\3\2\2\2\u0087\u0088\5\20"+
		"\t\2\u0088\u0089\7\26\2\2\u0089\u008a\5\20\t\2\u008a!\3\2\2\2\u008b\u008c"+
		"\5\20\t\2\u008c\u008d\7\25\2\2\u008d\u008e\5\20\t\2\u008e#\3\2\2\2\u008f"+
		"\u0090\5\20\t\2\u0090\u0091\7\27\2\2\u0091\u0092\5\20\t\2\u0092%\3\2\2"+
		"\2\u0093\u0094\5\20\t\2\u0094\u0095\7\30\2\2\u0095\u0096\5\20\t\2\u0096"+
		"\'\3\2\2\2\u0097\u009c\5*\26\2\u0098\u009c\5\60\31\2\u0099\u009c\5,\27"+
		"\2\u009a\u009c\5.\30\2\u009b\u0097\3\2\2\2\u009b\u0098\3\2\2\2\u009b\u0099"+
		"\3\2\2\2\u009b\u009a\3\2\2\2\u009c)\3\2\2\2\u009d\u009e\5\20\t\2\u009e"+
		"\u009f\7\21\2\2\u009f\u00a0\5\20\t\2\u00a0+\3\2\2\2\u00a1\u00a2\5\20\t"+
		"\2\u00a2\u00a3\7\17\2\2\u00a3\u00a4\5\20\t\2\u00a4-\3\2\2\2\u00a5\u00a6"+
		"\5\20\t\2\u00a6\u00a7\7\20\2\2\u00a7\u00a8\5\20\t\2\u00a8/\3\2\2\2\u00a9"+
		"\u00aa\5\20\t\2\u00aa\u00ab\7\22\2\2\u00ab\u00ac\5\20\t\2\u00ac\61\3\2"+
		"\2\2\u00ad\u00b0\5\f\7\2\u00ae\u00b0\5\26\f\2\u00af\u00ad\3\2\2\2\u00af"+
		"\u00ae\3\2\2\2\u00b0\63\3\2\2\2\u00b1\u00b3\5\62\32\2\u00b2\u00b4\5\66"+
		"\34\2\u00b3\u00b2\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b6\3\2\2\2\u00b5"+
		"\u00b7\5\62\32\2\u00b6\u00b5\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\3"+
		"\2\2\2\u00b8\u00b1\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba"+
		"\u00bb\3\2\2\2\u00bb\65\3\2\2\2\u00bc\u00bd\t\4\2\2\u00bd\67\3\2\2\2\17"+
		"AFMPXjoz\u009b\u00af\u00b3\u00b6\u00ba";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}