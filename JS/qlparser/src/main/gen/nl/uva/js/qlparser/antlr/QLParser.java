// Generated from /home/jouker/Downloads/endless-ql/JS/qlparser/src/main/java/nl/uva/js/qlparser/antlr/QL.g4 by ANTLR 4.7
package nl.uva.js.qlparser.antlr;
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
		WS=1, FORM=2, DATE=3, MONEY=4, STRING=5, DECIMAL=6, BOOLEAN=7, INTEGER=8, 
		TRUE=9, FALSE=10, INTVAL=11, DECVAL=12, STRVAL=13, IF=14, NAME=15, COLON=16, 
		ASSIGN=17, LP=18, RP=19, LB=20, RB=21, OR=22, AND=23, NOT=24, LT=25, GT=26, 
		LTE=27, GTE=28, EQ=29, NEQ=30, PLUS=31, MIN=32, MULT=33, DIV=34;
	public static final int
		RULE_datatype = 0, RULE_values = 1, RULE_boolOp = 2, RULE_compOp = 3, 
		RULE_arithOp = 4, RULE_form = 5, RULE_formBlock = 6, RULE_formExpression = 7, 
		RULE_expression = 8, RULE_question = 9;
	public static final String[] ruleNames = {
		"datatype", "values", "boolOp", "compOp", "arithOp", "form", "formBlock", 
		"formExpression", "expression", "question"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'form'", "'date'", "'money'", "'string'", "'decimal'", "'boolean'", 
		"'integer'", "'true'", "'false'", null, null, null, "'if'", null, "':'", 
		"'='", "'('", "')'", "'{'", "'}'", "'||'", "'&&'", "'!'", "'<'", "'>'", 
		"'<='", "'>='", "'=='", "'!='", "'+'", "'-'", "'*'", "'/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "FORM", "DATE", "MONEY", "STRING", "DECIMAL", "BOOLEAN", "INTEGER", 
		"TRUE", "FALSE", "INTVAL", "DECVAL", "STRVAL", "IF", "NAME", "COLON", 
		"ASSIGN", "LP", "RP", "LB", "RB", "OR", "AND", "NOT", "LT", "GT", "LTE", 
		"GTE", "EQ", "NEQ", "PLUS", "MIN", "MULT", "DIV"
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
	public static class DatatypeContext extends ParserRuleContext {
		public TerminalNode DATE() { return getToken(QLParser.DATE, 0); }
		public TerminalNode MONEY() { return getToken(QLParser.MONEY, 0); }
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public TerminalNode DECIMAL() { return getToken(QLParser.DECIMAL, 0); }
		public TerminalNode BOOLEAN() { return getToken(QLParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(QLParser.INTEGER, 0); }
		public DatatypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_datatype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterDatatype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitDatatype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitDatatype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DatatypeContext datatype() throws RecognitionException {
		DatatypeContext _localctx = new DatatypeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_datatype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DATE) | (1L << MONEY) | (1L << STRING) | (1L << DECIMAL) | (1L << BOOLEAN) | (1L << INTEGER))) != 0)) ) {
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

	public static class ValuesContext extends ParserRuleContext {
		public TerminalNode STRVAL() { return getToken(QLParser.STRVAL, 0); }
		public TerminalNode INTVAL() { return getToken(QLParser.INTVAL, 0); }
		public TerminalNode DECVAL() { return getToken(QLParser.DECVAL, 0); }
		public ValuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_values; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterValues(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitValues(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitValues(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValuesContext values() throws RecognitionException {
		ValuesContext _localctx = new ValuesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_values);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTVAL) | (1L << DECVAL) | (1L << STRVAL))) != 0)) ) {
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

	public static class BoolOpContext extends ParserRuleContext {
		public TerminalNode OR() { return getToken(QLParser.OR, 0); }
		public TerminalNode AND() { return getToken(QLParser.AND, 0); }
		public BoolOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterBoolOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitBoolOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBoolOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolOpContext boolOp() throws RecognitionException {
		BoolOpContext _localctx = new BoolOpContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_boolOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
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

	public static class CompOpContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(QLParser.LT, 0); }
		public TerminalNode LTE() { return getToken(QLParser.LTE, 0); }
		public TerminalNode GT() { return getToken(QLParser.GT, 0); }
		public TerminalNode GTE() { return getToken(QLParser.GTE, 0); }
		public TerminalNode EQ() { return getToken(QLParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(QLParser.NEQ, 0); }
		public CompOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterCompOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitCompOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitCompOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompOpContext compOp() throws RecognitionException {
		CompOpContext _localctx = new CompOpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_compOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << GT) | (1L << LTE) | (1L << GTE) | (1L << EQ) | (1L << NEQ))) != 0)) ) {
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

	public static class ArithOpContext extends ParserRuleContext {
		public TerminalNode MIN() { return getToken(QLParser.MIN, 0); }
		public TerminalNode PLUS() { return getToken(QLParser.PLUS, 0); }
		public TerminalNode DIV() { return getToken(QLParser.DIV, 0); }
		public TerminalNode MULT() { return getToken(QLParser.MULT, 0); }
		public ArithOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterArithOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitArithOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitArithOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithOpContext arithOp() throws RecognitionException {
		ArithOpContext _localctx = new ArithOpContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_arithOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MIN) | (1L << MULT) | (1L << DIV))) != 0)) ) {
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
		public TerminalNode FORM() { return getToken(QLParser.FORM, 0); }
		public TerminalNode NAME() { return getToken(QLParser.NAME, 0); }
		public FormBlockContext formBlock() {
			return getRuleContext(FormBlockContext.class,0);
		}
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitForm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitForm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_form);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(FORM);
			setState(31);
			match(NAME);
			setState(32);
			formBlock();
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

	public static class FormBlockContext extends ParserRuleContext {
		public TerminalNode LB() { return getToken(QLParser.LB, 0); }
		public TerminalNode RB() { return getToken(QLParser.RB, 0); }
		public List<FormExpressionContext> formExpression() {
			return getRuleContexts(FormExpressionContext.class);
		}
		public FormExpressionContext formExpression(int i) {
			return getRuleContext(FormExpressionContext.class,i);
		}
		public FormBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterFormBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitFormBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitFormBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormBlockContext formBlock() throws RecognitionException {
		FormBlockContext _localctx = new FormBlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_formBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(LB);
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRVAL || _la==IF) {
				{
				{
				setState(35);
				formExpression();
				}
				}
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(41);
			match(RB);
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

	public static class FormExpressionContext extends ParserRuleContext {
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public TerminalNode IF() { return getToken(QLParser.IF, 0); }
		public TerminalNode LP() { return getToken(QLParser.LP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RP() { return getToken(QLParser.RP, 0); }
		public FormBlockContext formBlock() {
			return getRuleContext(FormBlockContext.class,0);
		}
		public FormExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterFormExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitFormExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitFormExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormExpressionContext formExpression() throws RecognitionException {
		FormExpressionContext _localctx = new FormExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_formExpression);
		try {
			setState(50);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRVAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				question();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				match(IF);
				setState(45);
				match(LP);
				setState(46);
				expression(0);
				setState(47);
				match(RP);
				setState(48);
				formBlock();
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
		public TerminalNode NAME() { return getToken(QLParser.NAME, 0); }
		public ValuesContext values() {
			return getRuleContext(ValuesContext.class,0);
		}
		public TerminalNode LP() { return getToken(QLParser.LP, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RP() { return getToken(QLParser.RP, 0); }
		public TerminalNode NOT() { return getToken(QLParser.NOT, 0); }
		public BoolOpContext boolOp() {
			return getRuleContext(BoolOpContext.class,0);
		}
		public CompOpContext compOp() {
			return getRuleContext(CompOpContext.class,0);
		}
		public ArithOpContext arithOp() {
			return getRuleContext(ArithOpContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpression(this);
		}
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
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				{
				setState(53);
				match(NAME);
				}
				break;
			case INTVAL:
			case DECVAL:
			case STRVAL:
				{
				setState(54);
				values();
				}
				break;
			case LP:
				{
				setState(55);
				match(LP);
				setState(56);
				expression(0);
				setState(57);
				match(RP);
				}
				break;
			case NOT:
				{
				setState(59);
				match(NOT);
				setState(60);
				expression(4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(77);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(75);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(63);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(64);
						boolOp();
						setState(65);
						expression(4);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(67);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(68);
						compOp();
						setState(69);
						expression(3);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(71);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(72);
						arithOp();
						setState(73);
						expression(2);
						}
						break;
					}
					} 
				}
				setState(79);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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

	public static class QuestionContext extends ParserRuleContext {
		public TerminalNode STRVAL() { return getToken(QLParser.STRVAL, 0); }
		public TerminalNode NAME() { return getToken(QLParser.NAME, 0); }
		public TerminalNode COLON() { return getToken(QLParser.COLON, 0); }
		public DatatypeContext datatype() {
			return getRuleContext(DatatypeContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(QLParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(STRVAL);
			setState(81);
			match(NAME);
			setState(82);
			match(COLON);
			setState(83);
			datatype();
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(84);
				match(ASSIGN);
				setState(85);
				expression(0);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 8:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3$[\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\7\b\'\n\b"+
		"\f\b\16\b*\13\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\65\n\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n@\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\7\nN\n\n\f\n\16\nQ\13\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\5\13Y\n\13\3\13\2\3\22\f\2\4\6\b\n\f\16\20\22\24\2\7\3\2\5\n\3\2\r\17"+
		"\3\2\30\31\3\2\33 \3\2!$\2Y\2\26\3\2\2\2\4\30\3\2\2\2\6\32\3\2\2\2\b\34"+
		"\3\2\2\2\n\36\3\2\2\2\f \3\2\2\2\16$\3\2\2\2\20\64\3\2\2\2\22?\3\2\2\2"+
		"\24R\3\2\2\2\26\27\t\2\2\2\27\3\3\2\2\2\30\31\t\3\2\2\31\5\3\2\2\2\32"+
		"\33\t\4\2\2\33\7\3\2\2\2\34\35\t\5\2\2\35\t\3\2\2\2\36\37\t\6\2\2\37\13"+
		"\3\2\2\2 !\7\4\2\2!\"\7\21\2\2\"#\5\16\b\2#\r\3\2\2\2$(\7\26\2\2%\'\5"+
		"\20\t\2&%\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)+\3\2\2\2*(\3\2\2\2+"+
		",\7\27\2\2,\17\3\2\2\2-\65\5\24\13\2./\7\20\2\2/\60\7\24\2\2\60\61\5\22"+
		"\n\2\61\62\7\25\2\2\62\63\5\16\b\2\63\65\3\2\2\2\64-\3\2\2\2\64.\3\2\2"+
		"\2\65\21\3\2\2\2\66\67\b\n\1\2\67@\7\21\2\28@\5\4\3\29:\7\24\2\2:;\5\22"+
		"\n\2;<\7\25\2\2<@\3\2\2\2=>\7\32\2\2>@\5\22\n\6?\66\3\2\2\2?8\3\2\2\2"+
		"?9\3\2\2\2?=\3\2\2\2@O\3\2\2\2AB\f\5\2\2BC\5\6\4\2CD\5\22\n\6DN\3\2\2"+
		"\2EF\f\4\2\2FG\5\b\5\2GH\5\22\n\5HN\3\2\2\2IJ\f\3\2\2JK\5\n\6\2KL\5\22"+
		"\n\4LN\3\2\2\2MA\3\2\2\2ME\3\2\2\2MI\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2"+
		"\2\2P\23\3\2\2\2QO\3\2\2\2RS\7\17\2\2ST\7\21\2\2TU\7\22\2\2UX\5\2\2\2"+
		"VW\7\23\2\2WY\5\22\n\2XV\3\2\2\2XY\3\2\2\2Y\25\3\2\2\2\b(\64?MOX";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}