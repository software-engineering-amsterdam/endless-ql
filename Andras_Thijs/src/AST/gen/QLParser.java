// Generated from /home/thijs/Documents/uva/sc/endless-ql/Andras_Thijs/src/AST/QL.g4 by ANTLR 4.7
package AST.gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, WS=8, NOT=9, EXPONENT=10, 
		ADDITION=11, SUBTRACTION=12, DIVISION=13, MULTIPLICATION=14, GREATER=15, 
		LESS=16, GREATEREQ=17, LESSEQ=18, EQUAL=19, NOTEQUAL=20, AND=21, OR=22, 
		TYPE=23, BOOLEAN=24, STRING=25, INTEGER=26, DECIMAL=27, VARIABLE=28;
	public static final int
		RULE_numeral = 0, RULE_form = 1, RULE_question = 2, RULE_condition = 3, 
		RULE_expression = 4, RULE_operator = 5, RULE_booloperator = 6, RULE_equaloperator = 7, 
		RULE_comparision = 8, RULE_arithmetic = 9, RULE_addsub = 10, RULE_muldiv = 11, 
		RULE_factor = 12;
	public static final String[] ruleNames = {
		"numeral", "form", "question", "condition", "expression", "operator", 
		"booloperator", "equaloperator", "comparision", "arithmetic", "addsub", 
		"muldiv", "factor"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "':'", "'if'", "'('", "')'", null, "'!'", 
		"'^'", "'+'", "'-'", "'/'", "'*'", "'>'", "'<'", "'>='", "'<='", "'=='", 
		"'!='", "'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "WS", "NOT", "EXPONENT", 
		"ADDITION", "SUBTRACTION", "DIVISION", "MULTIPLICATION", "GREATER", "LESS", 
		"GREATEREQ", "LESSEQ", "EQUAL", "NOTEQUAL", "AND", "OR", "TYPE", "BOOLEAN", 
		"STRING", "INTEGER", "DECIMAL", "VARIABLE"
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
	public String getGrammarFileName() { return "QL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class NumeralContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(QLParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(QLParser.DECIMAL, 0); }
		public NumeralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeral; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitNumeral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumeralContext numeral() throws RecognitionException {
		NumeralContext _localctx = new NumeralContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_numeral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==DECIMAL) ) {
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

	public static class FormContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(QLParser.VARIABLE, 0); }
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitForm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_form);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(T__0);
			setState(29);
			match(VARIABLE);
			setState(30);
			match(T__1);
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VARIABLE) {
				{
				{
				setState(31);
				question();
				}
				}
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(37);
				condition();
				}
				}
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(43);
			match(T__2);
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

	public static class QuestionContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(QLParser.VARIABLE, 0); }
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public TerminalNode TYPE() { return getToken(QLParser.TYPE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(VARIABLE);
			setState(46);
			match(T__3);
			setState(47);
			match(STRING);
			setState(48);
			match(TYPE);
			setState(50);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(49);
				expression(0);
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

	public static class ConditionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(T__4);
			setState(53);
			expression(0);
			setState(54);
			match(T__1);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VARIABLE) {
				{
				{
				setState(55);
				question();
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(61);
				condition();
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			match(T__2);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode NOT() { return getToken(QLParser.NOT, 0); }
		public TerminalNode VARIABLE() { return getToken(QLParser.VARIABLE, 0); }
		public TerminalNode BOOLEAN() { return getToken(QLParser.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public NumeralContext numeral() {
			return getRuleContext(NumeralContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(70);
				match(T__5);
				setState(71);
				expression(0);
				setState(72);
				match(T__6);
				}
				break;
			case NOT:
				{
				setState(74);
				match(NOT);
				setState(75);
				expression(6);
				}
				break;
			case VARIABLE:
				{
				setState(76);
				match(VARIABLE);
				}
				break;
			case BOOLEAN:
				{
				setState(77);
				match(BOOLEAN);
				}
				break;
			case STRING:
				{
				setState(78);
				match(STRING);
				}
				break;
			case INTEGER:
			case DECIMAL:
				{
				setState(79);
				numeral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(88);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(82);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(83);
					operator();
					setState(84);
					expression(2);
					}
					} 
				}
				setState(90);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class OperatorContext extends ParserRuleContext {
		public BooloperatorContext booloperator() {
			return getRuleContext(BooloperatorContext.class,0);
		}
		public EqualoperatorContext equaloperator() {
			return getRuleContext(EqualoperatorContext.class,0);
		}
		public ComparisionContext comparision() {
			return getRuleContext(ComparisionContext.class,0);
		}
		public ArithmeticContext arithmetic() {
			return getRuleContext(ArithmeticContext.class,0);
		}
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_operator);
		try {
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AND:
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				booloperator();
				}
				break;
			case EQUAL:
			case NOTEQUAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				equaloperator();
				}
				break;
			case GREATER:
			case LESS:
			case GREATEREQ:
			case LESSEQ:
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
				comparision();
				}
				break;
			case EXPONENT:
			case ADDITION:
			case SUBTRACTION:
			case DIVISION:
			case MULTIPLICATION:
				enterOuterAlt(_localctx, 4);
				{
				setState(94);
				arithmetic();
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

	public static class BooloperatorContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(QLParser.AND, 0); }
		public TerminalNode OR() { return getToken(QLParser.OR, 0); }
		public BooloperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booloperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBooloperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooloperatorContext booloperator() throws RecognitionException {
		BooloperatorContext _localctx = new BooloperatorContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_booloperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_la = _input.LA(1);
			if ( !(_la==AND || _la==OR) ) {
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

	public static class EqualoperatorContext extends ParserRuleContext {
		public TerminalNode EQUAL() { return getToken(QLParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(QLParser.NOTEQUAL, 0); }
		public EqualoperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equaloperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitEqualoperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualoperatorContext equaloperator() throws RecognitionException {
		EqualoperatorContext _localctx = new EqualoperatorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_equaloperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_la = _input.LA(1);
			if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
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

	public static class ComparisionContext extends ParserRuleContext {
		public TerminalNode LESS() { return getToken(QLParser.LESS, 0); }
		public TerminalNode GREATER() { return getToken(QLParser.GREATER, 0); }
		public TerminalNode LESSEQ() { return getToken(QLParser.LESSEQ, 0); }
		public TerminalNode GREATEREQ() { return getToken(QLParser.GREATEREQ, 0); }
		public ComparisionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparision; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitComparision(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisionContext comparision() throws RecognitionException {
		ComparisionContext _localctx = new ComparisionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_comparision);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << LESS) | (1L << GREATEREQ) | (1L << LESSEQ))) != 0)) ) {
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

	public static class ArithmeticContext extends ParserRuleContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public MuldivContext muldiv() {
			return getRuleContext(MuldivContext.class,0);
		}
		public AddsubContext addsub() {
			return getRuleContext(AddsubContext.class,0);
		}
		public ArithmeticContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitArithmetic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticContext arithmetic() throws RecognitionException {
		ArithmeticContext _localctx = new ArithmeticContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_arithmetic);
		try {
			setState(106);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXPONENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(103);
				factor();
				}
				break;
			case DIVISION:
			case MULTIPLICATION:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				muldiv();
				}
				break;
			case ADDITION:
			case SUBTRACTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(105);
				addsub();
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

	public static class AddsubContext extends ParserRuleContext {
		public TerminalNode ADDITION() { return getToken(QLParser.ADDITION, 0); }
		public TerminalNode SUBTRACTION() { return getToken(QLParser.SUBTRACTION, 0); }
		public AddsubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addsub; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitAddsub(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddsubContext addsub() throws RecognitionException {
		AddsubContext _localctx = new AddsubContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_addsub);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			_la = _input.LA(1);
			if ( !(_la==ADDITION || _la==SUBTRACTION) ) {
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

	public static class MuldivContext extends ParserRuleContext {
		public TerminalNode MULTIPLICATION() { return getToken(QLParser.MULTIPLICATION, 0); }
		public TerminalNode DIVISION() { return getToken(QLParser.DIVISION, 0); }
		public MuldivContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_muldiv; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitMuldiv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MuldivContext muldiv() throws RecognitionException {
		MuldivContext _localctx = new MuldivContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_muldiv);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			_la = _input.LA(1);
			if ( !(_la==DIVISION || _la==MULTIPLICATION) ) {
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

	public static class FactorContext extends ParserRuleContext {
		public TerminalNode EXPONENT() { return getToken(QLParser.EXPONENT, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_factor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(EXPONENT);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36u\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\3\3\3\3\7\3#\n\3\f\3\16\3&\13"+
		"\3\3\3\7\3)\n\3\f\3\16\3,\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\5\4\65\n\4"+
		"\3\5\3\5\3\5\3\5\7\5;\n\5\f\5\16\5>\13\5\3\5\7\5A\n\5\f\5\16\5D\13\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6S\n\6\3\6\3\6\3"+
		"\6\3\6\7\6Y\n\6\f\6\16\6\\\13\6\3\7\3\7\3\7\3\7\5\7b\n\7\3\b\3\b\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\13\5\13m\n\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\2"+
		"\3\n\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\b\3\2\34\35\3\2\27\30\3\2\25"+
		"\26\3\2\21\24\3\2\r\16\3\2\17\20\2w\2\34\3\2\2\2\4\36\3\2\2\2\6/\3\2\2"+
		"\2\b\66\3\2\2\2\nR\3\2\2\2\fa\3\2\2\2\16c\3\2\2\2\20e\3\2\2\2\22g\3\2"+
		"\2\2\24l\3\2\2\2\26n\3\2\2\2\30p\3\2\2\2\32r\3\2\2\2\34\35\t\2\2\2\35"+
		"\3\3\2\2\2\36\37\7\3\2\2\37 \7\36\2\2 $\7\4\2\2!#\5\6\4\2\"!\3\2\2\2#"+
		"&\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%*\3\2\2\2&$\3\2\2\2\')\5\b\5\2(\'\3\2\2"+
		"\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+-\3\2\2\2,*\3\2\2\2-.\7\5\2\2.\5\3\2"+
		"\2\2/\60\7\36\2\2\60\61\7\6\2\2\61\62\7\33\2\2\62\64\7\31\2\2\63\65\5"+
		"\n\6\2\64\63\3\2\2\2\64\65\3\2\2\2\65\7\3\2\2\2\66\67\7\7\2\2\678\5\n"+
		"\6\28<\7\4\2\29;\5\6\4\2:9\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=B\3\2"+
		"\2\2><\3\2\2\2?A\5\b\5\2@?\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2CE\3\2"+
		"\2\2DB\3\2\2\2EF\7\5\2\2F\t\3\2\2\2GH\b\6\1\2HI\7\b\2\2IJ\5\n\6\2JK\7"+
		"\t\2\2KS\3\2\2\2LM\7\13\2\2MS\5\n\6\bNS\7\36\2\2OS\7\32\2\2PS\7\33\2\2"+
		"QS\5\2\2\2RG\3\2\2\2RL\3\2\2\2RN\3\2\2\2RO\3\2\2\2RP\3\2\2\2RQ\3\2\2\2"+
		"SZ\3\2\2\2TU\f\3\2\2UV\5\f\7\2VW\5\n\6\4WY\3\2\2\2XT\3\2\2\2Y\\\3\2\2"+
		"\2ZX\3\2\2\2Z[\3\2\2\2[\13\3\2\2\2\\Z\3\2\2\2]b\5\16\b\2^b\5\20\t\2_b"+
		"\5\22\n\2`b\5\24\13\2a]\3\2\2\2a^\3\2\2\2a_\3\2\2\2a`\3\2\2\2b\r\3\2\2"+
		"\2cd\t\3\2\2d\17\3\2\2\2ef\t\4\2\2f\21\3\2\2\2gh\t\5\2\2h\23\3\2\2\2i"+
		"m\5\32\16\2jm\5\30\r\2km\5\26\f\2li\3\2\2\2lj\3\2\2\2lk\3\2\2\2m\25\3"+
		"\2\2\2no\t\6\2\2o\27\3\2\2\2pq\t\7\2\2q\31\3\2\2\2rs\7\f\2\2s\33\3\2\2"+
		"\2\13$*\64<BRZal";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}