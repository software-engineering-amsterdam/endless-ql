// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/SC-Java-LDG-TV/src/main/java/antlr\Form.g4 by ANTLR 4.7
package antlr;
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
		T__0=1, T__1=2, T__2=3, T__3=4, CURLY_BRACKET_OPEN=5, CURLY_BRACKET_CLOSE=6, 
		BRACKET_OPEN=7, BRACKET_CLOSE=8, OR=9, AND=10, PLUS=11, MINUS=12, TIMES=13, 
		DIV=14, GT=15, GTOEQ=16, STOEQ=17, ST=18, EQ=19, NEQ=20, QUESTION_LABEL=21, 
		QUESTION_VARIABLE_SEPERATOR=22, QUESTION_VARIABLE_VALUE_SEPERATOR=23, 
		IF=24, WHITESPACE=25, NEWLINE=26, CHARACTERS=27, NUMBERS=28;
	public static final int
		RULE_formBuilder = 0, RULE_formData = 1, RULE_questionStructure = 2, RULE_ifStructure = 3, 
		RULE_statementBlockStructure = 4, RULE_questionLabel = 5, RULE_questionVariable = 6, 
		RULE_questionVariableType = 7, RULE_questionVariableValue = 8, RULE_plainValue = 9, 
		RULE_expression = 10, RULE_booleanExpression = 11, RULE_condition = 12, 
		RULE_conditions = 13, RULE_comparisonOperator = 14, RULE_booleanOperator = 15, 
		RULE_operator = 16;
	public static final String[] ruleNames = {
		"formBuilder", "formData", "questionStructure", "ifStructure", "statementBlockStructure", 
		"questionLabel", "questionVariable", "questionVariableType", "questionVariableValue", 
		"plainValue", "expression", "booleanExpression", "condition", "conditions", 
		"comparisonOperator", "booleanOperator", "operator"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'boolean'", "'money'", "'string'", "'{'", "'}'", "'('", 
		"')'", "'||'", "'&&'", "'+'", "'-'", "'*'", "'/'", "'>'", "'>='", "'=<'", 
		"'<'", "'=='", "'!='", null, "':'", "'='", "'if'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", 
		"BRACKET_OPEN", "BRACKET_CLOSE", "OR", "AND", "PLUS", "MINUS", "TIMES", 
		"DIV", "GT", "GTOEQ", "STOEQ", "ST", "EQ", "NEQ", "QUESTION_LABEL", "QUESTION_VARIABLE_SEPERATOR", 
		"QUESTION_VARIABLE_VALUE_SEPERATOR", "IF", "WHITESPACE", "NEWLINE", "CHARACTERS", 
		"NUMBERS"
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
			setState(34);
			match(T__0);
			setState(35);
			match(CHARACTERS);
			setState(36);
			match(CURLY_BRACKET_OPEN);
			setState(37);
			formData();
			setState(38);
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
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				questionStructure();
				}
				}
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUESTION_LABEL );
			setState(46); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(45);
					ifStructure();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(48); 
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
		public QuestionLabelContext questionLabel() {
			return getRuleContext(QuestionLabelContext.class,0);
		}
		public QuestionVariableContext questionVariable() {
			return getRuleContext(QuestionVariableContext.class,0);
		}
		public TerminalNode QUESTION_VARIABLE_SEPERATOR() { return getToken(FormParser.QUESTION_VARIABLE_SEPERATOR, 0); }
		public QuestionVariableTypeContext questionVariableType() {
			return getRuleContext(QuestionVariableTypeContext.class,0);
		}
		public TerminalNode QUESTION_VARIABLE_VALUE_SEPERATOR() { return getToken(FormParser.QUESTION_VARIABLE_VALUE_SEPERATOR, 0); }
		public QuestionVariableValueContext questionVariableValue() {
			return getRuleContext(QuestionVariableValueContext.class,0);
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
			setState(50);
			questionLabel();
			setState(51);
			questionVariable();
			setState(52);
			match(QUESTION_VARIABLE_SEPERATOR);
			setState(53);
			questionVariableType();
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION_VARIABLE_VALUE_SEPERATOR) {
				{
				setState(54);
				match(QUESTION_VARIABLE_VALUE_SEPERATOR);
				}
			}

			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BRACKET_OPEN) | (1L << CHARACTERS) | (1L << NUMBERS))) != 0)) {
				{
				setState(57);
				questionVariableValue();
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
			setState(60);
			match(IF);
			setState(61);
			statementBlockStructure();
			setState(62);
			match(CURLY_BRACKET_OPEN);
			setState(64); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(63);
				questionStructure();
				}
				}
				setState(66); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUESTION_LABEL );
			setState(68);
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
			setState(70);
			match(BRACKET_OPEN);
			setState(71);
			conditions();
			setState(72);
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

	public static class QuestionLabelContext extends ParserRuleContext {
		public TerminalNode QUESTION_LABEL() { return getToken(FormParser.QUESTION_LABEL, 0); }
		public QuestionLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterQuestionLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitQuestionLabel(this);
		}
	}

	public final QuestionLabelContext questionLabel() throws RecognitionException {
		QuestionLabelContext _localctx = new QuestionLabelContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_questionLabel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
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

	public static class QuestionVariableContext extends ParserRuleContext {
		public TerminalNode CHARACTERS() { return getToken(FormParser.CHARACTERS, 0); }
		public QuestionVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterQuestionVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitQuestionVariable(this);
		}
	}

	public final QuestionVariableContext questionVariable() throws RecognitionException {
		QuestionVariableContext _localctx = new QuestionVariableContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_questionVariable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
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

	public static class QuestionVariableTypeContext extends ParserRuleContext {
		public QuestionVariableTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionVariableType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterQuestionVariableType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitQuestionVariableType(this);
		}
	}

	public final QuestionVariableTypeContext questionVariableType() throws RecognitionException {
		QuestionVariableTypeContext _localctx = new QuestionVariableTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_questionVariableType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
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

	public static class QuestionVariableValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PlainValueContext plainValue() {
			return getRuleContext(PlainValueContext.class,0);
		}
		public QuestionVariableValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionVariableValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterQuestionVariableValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitQuestionVariableValue(this);
		}
	}

	public final QuestionVariableValueContext questionVariableValue() throws RecognitionException {
		QuestionVariableValueContext _localctx = new QuestionVariableValueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_questionVariableValue);
		try {
			setState(82);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BRACKET_OPEN:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				expression();
				}
				break;
			case CHARACTERS:
			case NUMBERS:
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				plainValue();
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

	public static class PlainValueContext extends ParserRuleContext {
		public TerminalNode CHARACTERS() { return getToken(FormParser.CHARACTERS, 0); }
		public TerminalNode NUMBERS() { return getToken(FormParser.NUMBERS, 0); }
		public PlainValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plainValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterPlainValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitPlainValue(this);
		}
	}

	public final PlainValueContext plainValue() throws RecognitionException {
		PlainValueContext _localctx = new PlainValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_plainValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_la = _input.LA(1);
			if ( !(_la==CHARACTERS || _la==NUMBERS) ) {
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

	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode BRACKET_OPEN() { return getToken(FormParser.BRACKET_OPEN, 0); }
		public List<QuestionVariableContext> questionVariable() {
			return getRuleContexts(QuestionVariableContext.class);
		}
		public QuestionVariableContext questionVariable(int i) {
			return getRuleContext(QuestionVariableContext.class,i);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(FormParser.BRACKET_CLOSE, 0); }
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
			setState(86);
			match(BRACKET_OPEN);
			setState(87);
			questionVariable();
			setState(88);
			operator();
			setState(89);
			questionVariable();
			setState(90);
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
		public TerminalNode BRACKET_OPEN() { return getToken(FormParser.BRACKET_OPEN, 0); }
		public List<QuestionVariableContext> questionVariable() {
			return getRuleContexts(QuestionVariableContext.class);
		}
		public QuestionVariableContext questionVariable(int i) {
			return getRuleContext(QuestionVariableContext.class,i);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(FormParser.BRACKET_CLOSE, 0); }
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
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(BRACKET_OPEN);
			setState(93);
			questionVariable();
			setState(94);
			comparisonOperator();
			setState(95);
			questionVariable();
			setState(96);
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

	public static class ConditionContext extends ParserRuleContext {
		public QuestionVariableContext questionVariable() {
			return getRuleContext(QuestionVariableContext.class,0);
		}
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
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
		enterRule(_localctx, 24, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CHARACTERS:
				{
				setState(98);
				questionVariable();
				}
				break;
			case BRACKET_OPEN:
				{
				setState(99);
				booleanExpression();
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
		enterRule(_localctx, 26, RULE_conditions);
		int _la;
		try {
			setState(111);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				condition();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				condition();
				setState(107); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(104);
					booleanOperator();
					setState(105);
					condition();
					}
					}
					setState(109); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==OR || _la==AND );
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

	public static class ComparisonOperatorContext extends ParserRuleContext {
		public TerminalNode GT() { return getToken(FormParser.GT, 0); }
		public TerminalNode GTOEQ() { return getToken(FormParser.GTOEQ, 0); }
		public TerminalNode STOEQ() { return getToken(FormParser.STOEQ, 0); }
		public TerminalNode ST() { return getToken(FormParser.ST, 0); }
		public TerminalNode EQ() { return getToken(FormParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(FormParser.NEQ, 0); }
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitComparisonOperator(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << GTOEQ) | (1L << STOEQ) | (1L << ST) | (1L << EQ) | (1L << NEQ))) != 0)) ) {
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
		enterRule(_localctx, 30, RULE_booleanOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
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

	public static class OperatorContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(FormParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(FormParser.MINUS, 0); }
		public TerminalNode TIMES() { return getToken(FormParser.TIMES, 0); }
		public TerminalNode DIV() { return getToken(FormParser.DIV, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormListener ) ((FormListener)listener).exitOperator(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << TIMES) | (1L << DIV))) != 0)) ) {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36z\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\6\3,\n\3\r\3\16\3-\3\3\6\3\61\n\3\r\3\16\3\62\3"+
		"\4\3\4\3\4\3\4\3\4\5\4:\n\4\3\4\5\4=\n\4\3\5\3\5\3\5\3\5\6\5C\n\5\r\5"+
		"\16\5D\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\5\nU\n"+
		"\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\5\16g\n\16\3\17\3\17\3\17\3\17\3\17\6\17n\n\17\r\17\16\17o\5\17r\n\17"+
		"\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\62\2\23\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"\2\7\3\2\4\6\3\2\35\36\3\2\21\26\3\2\13\f\3\2\r\20\2"+
		"q\2$\3\2\2\2\4+\3\2\2\2\6\64\3\2\2\2\b>\3\2\2\2\nH\3\2\2\2\fL\3\2\2\2"+
		"\16N\3\2\2\2\20P\3\2\2\2\22T\3\2\2\2\24V\3\2\2\2\26X\3\2\2\2\30^\3\2\2"+
		"\2\32f\3\2\2\2\34q\3\2\2\2\36s\3\2\2\2 u\3\2\2\2\"w\3\2\2\2$%\7\3\2\2"+
		"%&\7\35\2\2&\'\7\7\2\2\'(\5\4\3\2()\7\b\2\2)\3\3\2\2\2*,\5\6\4\2+*\3\2"+
		"\2\2,-\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\60\3\2\2\2/\61\5\b\5\2\60/\3\2\2\2"+
		"\61\62\3\2\2\2\62\63\3\2\2\2\62\60\3\2\2\2\63\5\3\2\2\2\64\65\5\f\7\2"+
		"\65\66\5\16\b\2\66\67\7\30\2\2\679\5\20\t\28:\7\31\2\298\3\2\2\29:\3\2"+
		"\2\2:<\3\2\2\2;=\5\22\n\2<;\3\2\2\2<=\3\2\2\2=\7\3\2\2\2>?\7\32\2\2?@"+
		"\5\n\6\2@B\7\7\2\2AC\5\6\4\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2E"+
		"F\3\2\2\2FG\7\b\2\2G\t\3\2\2\2HI\7\t\2\2IJ\5\34\17\2JK\7\n\2\2K\13\3\2"+
		"\2\2LM\7\27\2\2M\r\3\2\2\2NO\7\35\2\2O\17\3\2\2\2PQ\t\2\2\2Q\21\3\2\2"+
		"\2RU\5\26\f\2SU\5\24\13\2TR\3\2\2\2TS\3\2\2\2U\23\3\2\2\2VW\t\3\2\2W\25"+
		"\3\2\2\2XY\7\t\2\2YZ\5\16\b\2Z[\5\"\22\2[\\\5\16\b\2\\]\7\n\2\2]\27\3"+
		"\2\2\2^_\7\t\2\2_`\5\16\b\2`a\5\36\20\2ab\5\16\b\2bc\7\n\2\2c\31\3\2\2"+
		"\2dg\5\16\b\2eg\5\30\r\2fd\3\2\2\2fe\3\2\2\2g\33\3\2\2\2hr\5\32\16\2i"+
		"m\5\32\16\2jk\5 \21\2kl\5\32\16\2ln\3\2\2\2mj\3\2\2\2no\3\2\2\2om\3\2"+
		"\2\2op\3\2\2\2pr\3\2\2\2qh\3\2\2\2qi\3\2\2\2r\35\3\2\2\2st\t\4\2\2t\37"+
		"\3\2\2\2uv\t\5\2\2v!\3\2\2\2wx\t\6\2\2x#\3\2\2\2\13-\629<DTfoq";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}