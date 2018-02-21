// Generated from C:/Users/georg/Documents/GitHub/endless-ql/ForcePush/src/main/resources/antlr\GrammarParser.g4 by ANTLR 4.7
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
public class GrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WHITESPACE=1, FORM=2, BOOL=3, STR=4, INT=5, DATE=6, DECIMAL=7, MONEY=8, 
		ASSIGN=9, LPAREN=10, RPAREN=11, LCURLYBRAKET=12, RCURLYBRAKET=13, IF=14, 
		ELSE=15, IFELSE=16, PLUS=17, MINUS=18, ASTERISK=19, DIVISION=20, EQUAL=21, 
		LESS=22, HIGHER=23, EQUALHIGHER=24, EQUALLESS=25, DIFF=26, ISEQUAL=27, 
		AND=28, OR=29, NOT=30, VAR=31, LABEL=32;
	public static final int
		RULE_a = 0, RULE_b = 1, RULE_r = 2, RULE_variable = 3, RULE_expression = 4, 
		RULE_question = 5, RULE_conditional = 6, RULE_form = 7;
	public static final String[] ruleNames = {
		"a", "b", "r", "variable", "expression", "question", "conditional", "form"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'form'", "'boolean'", "'string'", "'integer'", "'date'", 
		"'decimal'", null, "':'", "'('", "')'", "'{'", "'}'", "'if'", "'else'", 
		"'ifelse'", "'+'", "'-'", "'*'", "'/'", "'='", "'<'", "'>'", "'>='", "'<='", 
		"'!='", "'=='", "'&&'", "'||'", "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WHITESPACE", "FORM", "BOOL", "STR", "INT", "DATE", "DECIMAL", "MONEY", 
		"ASSIGN", "LPAREN", "RPAREN", "LCURLYBRAKET", "RCURLYBRAKET", "IF", "ELSE", 
		"IFELSE", "PLUS", "MINUS", "ASTERISK", "DIVISION", "EQUAL", "LESS", "HIGHER", 
		"EQUALHIGHER", "EQUALLESS", "DIFF", "ISEQUAL", "AND", "OR", "NOT", "VAR", 
		"LABEL"
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
	public String getGrammarFileName() { return "GrammarParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class AContext extends ParserRuleContext {
		public BContext b() {
			return getRuleContext(BContext.class,0);
		}
		public TerminalNode INT() { return getToken(GrammarParser.INT, 0); }
		public AContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_a; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterA(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitA(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitA(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AContext a() throws RecognitionException {
		AContext _localctx = new AContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_a);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			b();
			setState(17);
			match(INT);
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

	public static class BContext extends ParserRuleContext {
		public List<TerminalNode> VAR() { return getTokens(GrammarParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(GrammarParser.VAR, i);
		}
		public BContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_b; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterB(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitB(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitB(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BContext b() throws RecognitionException {
		BContext _localctx = new BContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_b);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			match(VAR);
			setState(20);
			match(VAR);
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

	public static class RContext extends ParserRuleContext {
		public Token VAR;
		public TerminalNode VAR() { return getToken(GrammarParser.VAR, 0); }
		public RContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitR(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RContext r() throws RecognitionException {
		RContext _localctx = new RContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_r);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			((RContext)_localctx).VAR = match(VAR);
			System.out.println("invoke "+(((RContext)_localctx).VAR!=null?((RContext)_localctx).VAR.getText():null));
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
		public TerminalNode VAR() { return getToken(GrammarParser.VAR, 0); }
		public TerminalNode NOT() { return getToken(GrammarParser.NOT, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_variable);
		try {
			setState(28);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(25);
				match(VAR);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				match(NOT);
				setState(27);
				match(VAR);
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
		public Token arithmetic;
		public Token decisions;
		public Token comparison;
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(GrammarParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(GrammarParser.MINUS, 0); }
		public TerminalNode ASTERISK() { return getToken(GrammarParser.ASTERISK, 0); }
		public TerminalNode DIVISION() { return getToken(GrammarParser.DIVISION, 0); }
		public TerminalNode AND() { return getToken(GrammarParser.AND, 0); }
		public TerminalNode OR() { return getToken(GrammarParser.OR, 0); }
		public TerminalNode LESS() { return getToken(GrammarParser.LESS, 0); }
		public TerminalNode HIGHER() { return getToken(GrammarParser.HIGHER, 0); }
		public TerminalNode EQUALHIGHER() { return getToken(GrammarParser.EQUALHIGHER, 0); }
		public TerminalNode EQUALLESS() { return getToken(GrammarParser.EQUALLESS, 0); }
		public TerminalNode DIFF() { return getToken(GrammarParser.DIFF, 0); }
		public TerminalNode ISEQUAL() { return getToken(GrammarParser.ISEQUAL, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_expression);
		int _la;
		try {
			setState(42);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				variable();
				setState(31);
				((ExpressionContext)_localctx).arithmetic = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << ASTERISK) | (1L << DIVISION))) != 0)) ) {
					((ExpressionContext)_localctx).arithmetic = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(32);
				variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				variable();
				setState(35);
				((ExpressionContext)_localctx).decisions = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==AND || _la==OR) ) {
					((ExpressionContext)_localctx).decisions = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(36);
				variable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(38);
				variable();
				setState(39);
				((ExpressionContext)_localctx).comparison = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS) | (1L << HIGHER) | (1L << EQUALHIGHER) | (1L << EQUALLESS) | (1L << DIFF) | (1L << ISEQUAL))) != 0)) ) {
					((ExpressionContext)_localctx).comparison = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(40);
				variable();
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

	public static class QuestionContext extends ParserRuleContext {
		public Token type;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(GrammarParser.ASSIGN, 0); }
		public TerminalNode LABEL() { return getToken(GrammarParser.LABEL, 0); }
		public TerminalNode BOOL() { return getToken(GrammarParser.BOOL, 0); }
		public TerminalNode STR() { return getToken(GrammarParser.STR, 0); }
		public TerminalNode DATE() { return getToken(GrammarParser.DATE, 0); }
		public TerminalNode DECIMAL() { return getToken(GrammarParser.DECIMAL, 0); }
		public TerminalNode MONEY() { return getToken(GrammarParser.MONEY, 0); }
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(GrammarParser.EQUAL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> LPAREN() { return getTokens(GrammarParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(GrammarParser.LPAREN, i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(GrammarParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(GrammarParser.RPAREN, i);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		return question(0);
	}

	private QuestionContext question(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		QuestionContext _localctx = new QuestionContext(_ctx, _parentState);
		QuestionContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_question, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(45);
			variable();
			setState(46);
			match(ASSIGN);
			setState(47);
			match(LABEL);
			setState(48);
			((QuestionContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << STR) | (1L << DATE) | (1L << DECIMAL) | (1L << MONEY))) != 0)) ) {
				((QuestionContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(67);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new QuestionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_question);
					setState(50);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(51);
					match(EQUAL);
					setState(55);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==LPAREN) {
						{
						{
						setState(52);
						match(LPAREN);
						}
						}
						setState(57);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(58);
					expression();
					setState(62);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(59);
							match(RPAREN);
							}
							} 
						}
						setState(64);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
					}
					}
					} 
				}
				setState(69);
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

	public static class ConditionalContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode LPAREN() { return getToken(GrammarParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(GrammarParser.RPAREN, 0); }
		public TerminalNode LCURLYBRAKET() { return getToken(GrammarParser.LCURLYBRAKET, 0); }
		public TerminalNode RCURLYBRAKET() { return getToken(GrammarParser.RCURLYBRAKET, 0); }
		public TerminalNode IF() { return getToken(GrammarParser.IF, 0); }
		public TerminalNode ELSE() { return getToken(GrammarParser.ELSE, 0); }
		public TerminalNode IFELSE() { return getToken(GrammarParser.IFELSE, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public List<ConditionalContext> conditional() {
			return getRuleContexts(ConditionalContext.class);
		}
		public ConditionalContext conditional(int i) {
			return getRuleContext(ConditionalContext.class,i);
		}
		public ConditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitConditional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitConditional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalContext conditional() throws RecognitionException {
		ConditionalContext _localctx = new ConditionalContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_conditional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			((ConditionalContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << ELSE) | (1L << IFELSE))) != 0)) ) {
				((ConditionalContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(71);
			match(LPAREN);
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(72);
				variable();
				}
				break;
			case 2:
				{
				setState(73);
				expression();
				}
				break;
			}
			setState(76);
			match(RPAREN);
			setState(77);
			match(LCURLYBRAKET);
			setState(80); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(80);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NOT:
				case VAR:
					{
					setState(78);
					question(0);
					}
					break;
				case IF:
				case ELSE:
				case IFELSE:
					{
					setState(79);
					conditional();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(82); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << ELSE) | (1L << IFELSE) | (1L << NOT) | (1L << VAR))) != 0) );
			setState(84);
			match(RCURLYBRAKET);
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
		public TerminalNode FORM() { return getToken(GrammarParser.FORM, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode LCURLYBRAKET() { return getToken(GrammarParser.LCURLYBRAKET, 0); }
		public TerminalNode RCURLYBRAKET() { return getToken(GrammarParser.RCURLYBRAKET, 0); }
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public List<ConditionalContext> conditional() {
			return getRuleContexts(ConditionalContext.class);
		}
		public ConditionalContext conditional(int i) {
			return getRuleContext(ConditionalContext.class,i);
		}
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitForm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitForm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_form);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(FORM);
			setState(87);
			variable();
			setState(88);
			match(LCURLYBRAKET);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << ELSE) | (1L << IFELSE) | (1L << NOT) | (1L << VAR))) != 0)) {
				{
				setState(91);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NOT:
				case VAR:
					{
					setState(89);
					question(0);
					}
					break;
				case IF:
				case ELSE:
				case IFELSE:
					{
					setState(90);
					conditional();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(96);
			match(RCURLYBRAKET);
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
		case 5:
			return question_sempred((QuestionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean question_sempred(QuestionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\"e\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\5\3\5\3\5\5\5\37\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\5\6-\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\78\n"+
		"\7\f\7\16\7;\13\7\3\7\3\7\7\7?\n\7\f\7\16\7B\13\7\7\7D\n\7\f\7\16\7G\13"+
		"\7\3\b\3\b\3\b\3\b\5\bM\n\b\3\b\3\b\3\b\3\b\6\bS\n\b\r\b\16\bT\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\7\t^\n\t\f\t\16\ta\13\t\3\t\3\t\3\t\2\3\f\n\2\4\6"+
		"\b\n\f\16\20\2\7\3\2\23\26\3\2\36\37\3\2\30\35\4\2\5\6\b\n\3\2\20\22\2"+
		"g\2\22\3\2\2\2\4\25\3\2\2\2\6\30\3\2\2\2\b\36\3\2\2\2\n,\3\2\2\2\f.\3"+
		"\2\2\2\16H\3\2\2\2\20X\3\2\2\2\22\23\5\4\3\2\23\24\7\7\2\2\24\3\3\2\2"+
		"\2\25\26\7!\2\2\26\27\7!\2\2\27\5\3\2\2\2\30\31\7!\2\2\31\32\b\4\1\2\32"+
		"\7\3\2\2\2\33\37\7!\2\2\34\35\7 \2\2\35\37\7!\2\2\36\33\3\2\2\2\36\34"+
		"\3\2\2\2\37\t\3\2\2\2 !\5\b\5\2!\"\t\2\2\2\"#\5\b\5\2#-\3\2\2\2$%\5\b"+
		"\5\2%&\t\3\2\2&\'\5\b\5\2\'-\3\2\2\2()\5\b\5\2)*\t\4\2\2*+\5\b\5\2+-\3"+
		"\2\2\2, \3\2\2\2,$\3\2\2\2,(\3\2\2\2-\13\3\2\2\2./\b\7\1\2/\60\5\b\5\2"+
		"\60\61\7\13\2\2\61\62\7\"\2\2\62\63\t\5\2\2\63E\3\2\2\2\64\65\f\3\2\2"+
		"\659\7\27\2\2\668\7\f\2\2\67\66\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2"+
		"\2:<\3\2\2\2;9\3\2\2\2<@\5\n\6\2=?\7\r\2\2>=\3\2\2\2?B\3\2\2\2@>\3\2\2"+
		"\2@A\3\2\2\2AD\3\2\2\2B@\3\2\2\2C\64\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2"+
		"\2\2F\r\3\2\2\2GE\3\2\2\2HI\t\6\2\2IL\7\f\2\2JM\5\b\5\2KM\5\n\6\2LJ\3"+
		"\2\2\2LK\3\2\2\2MN\3\2\2\2NO\7\r\2\2OR\7\16\2\2PS\5\f\7\2QS\5\16\b\2R"+
		"P\3\2\2\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2TU\3\2\2\2UV\3\2\2\2VW\7\17\2\2"+
		"W\17\3\2\2\2XY\7\4\2\2YZ\5\b\5\2Z_\7\16\2\2[^\5\f\7\2\\^\5\16\b\2][\3"+
		"\2\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`b\3\2\2\2a_\3\2\2\2bc"+
		"\7\17\2\2c\21\3\2\2\2\f\36,9@ELRT]_";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}