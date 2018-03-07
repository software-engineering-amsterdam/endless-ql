// Generated from C:/Users/Joana Magalhães/Documents/GitHub/endless-ql/ForcePush/src/main/resources/antlr\GrammarParser.g4 by ANTLR 4.7
package org.uva.forcepushql.antlr;
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
		WHITESPACE=1, COMMENT=2, LINE_COMMENT=3, FORM=4, BOOL=5, STR=6, INT=7, 
		DATE=8, DECIMAL=9, MULTIPLEANSWER=10, MONEY=11, ASSIGN=12, IF=13, ELSE=14, 
		IFELSE=15, LPAREN=16, RPAREN=17, LBRACE=18, RBRACE=19, SEMI=20, COMMA=21, 
		DOT=22, PLUS=23, MINUS=24, MULTIPLY=25, DIVIDE=26, EQUAL=27, LESS=28, 
		GREATER=29, EQUALGREATER=30, EQUALLESS=31, NOTEQUAL=32, ISEQUAL=33, AND=34, 
		OR=35, NOT=36, NUM=37, VAR=38, LABEL=39, DEC=40;
	public static final int
		RULE_compileUnit = 0, RULE_mathUnit = 1, RULE_variable = 2, RULE_type = 3, 
		RULE_questionTypes = 4, RULE_expression = 5, RULE_questionFormat = 6, 
		RULE_questionAssignValue = 7, RULE_conditionalIf = 8, RULE_conditionalIfElse = 9, 
		RULE_conditionalElse = 10, RULE_questionMultiAns = 11, RULE_formStructure = 12;
	public static final String[] ruleNames = {
		"compileUnit", "mathUnit", "variable", "type", "questionTypes", "expression", 
		"questionFormat", "questionAssignValue", "conditionalIf", "conditionalIfElse", 
		"conditionalElse", "questionMultiAns", "formStructure"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, "'form'", "'boolean'", "'string'", "'integer'", 
		"'date'", "'decimal'", "'multipleAnswer'", null, "':'", "'if'", "'else'", 
		"'ifelse'", "'('", "')'", "'{'", "'}'", "';'", "','", "'.'", "'+'", "'-'", 
		"'*'", "'/'", "'='", "'<'", "'>'", "'>='", "'<='", "'!='", "'=='", "'&&'", 
		"'||'", "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WHITESPACE", "COMMENT", "LINE_COMMENT", "FORM", "BOOL", "STR", 
		"INT", "DATE", "DECIMAL", "MULTIPLEANSWER", "MONEY", "ASSIGN", "IF", "ELSE", 
		"IFELSE", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "SEMI", "COMMA", "DOT", 
		"PLUS", "MINUS", "MULTIPLY", "DIVIDE", "EQUAL", "LESS", "GREATER", "EQUALGREATER", 
		"EQUALLESS", "NOTEQUAL", "ISEQUAL", "AND", "OR", "NOT", "NUM", "VAR", 
		"LABEL", "DEC"
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
	public static class CompileUnitContext extends ParserRuleContext {
		public FormStructureContext formStructure() {
			return getRuleContext(FormStructureContext.class,0);
		}
		public TerminalNode EOF() { return getToken(GrammarParser.EOF, 0); }
		public CompileUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compileUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterCompileUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitCompileUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitCompileUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompileUnitContext compileUnit() throws RecognitionException {
		CompileUnitContext _localctx = new CompileUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compileUnit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			formStructure();
			setState(27);
			match(EOF);
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

	public static class MathUnitContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EOF() { return getToken(GrammarParser.EOF, 0); }
		public MathUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mathUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterMathUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitMathUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitMathUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MathUnitContext mathUnit() throws RecognitionException {
		MathUnitContext _localctx = new MathUnitContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mathUnit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			expression(0);
			setState(30);
			match(EOF);
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
		public TerminalNode DEC() { return getToken(GrammarParser.DEC, 0); }
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
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
		enterRule(_localctx, 4, RULE_variable);
		try {
			setState(37);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				match(VAR);
				}
				break;
			case DEC:
				enterOuterAlt(_localctx, 2);
				{
				setState(33);
				match(DEC);
				}
				break;
			case NUM:
				enterOuterAlt(_localctx, 3);
				{
				setState(34);
				match(NUM);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 4);
				{
				setState(35);
				match(NOT);
				setState(36);
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(GrammarParser.BOOL, 0); }
		public TerminalNode STR() { return getToken(GrammarParser.STR, 0); }
		public TerminalNode DATE() { return getToken(GrammarParser.DATE, 0); }
		public TerminalNode DECIMAL() { return getToken(GrammarParser.DECIMAL, 0); }
		public TerminalNode MONEY() { return getToken(GrammarParser.MONEY, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << STR) | (1L << DATE) | (1L << DECIMAL) | (1L << MONEY))) != 0)) ) {
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

	public static class QuestionTypesContext extends ParserRuleContext {
		public QuestionFormatContext questionFormat() {
			return getRuleContext(QuestionFormatContext.class,0);
		}
		public ConditionalIfContext conditionalIf() {
			return getRuleContext(ConditionalIfContext.class,0);
		}
		public QuestionAssignValueContext questionAssignValue() {
			return getRuleContext(QuestionAssignValueContext.class,0);
		}
		public QuestionMultiAnsContext questionMultiAns() {
			return getRuleContext(QuestionMultiAnsContext.class,0);
		}
		public QuestionTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterQuestionTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitQuestionTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitQuestionTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionTypesContext questionTypes() throws RecognitionException {
		QuestionTypesContext _localctx = new QuestionTypesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_questionTypes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(41);
				questionFormat();
				}
				break;
			case 2:
				{
				setState(42);
				conditionalIf();
				}
				break;
			case 3:
				{
				setState(43);
				questionAssignValue();
				}
				break;
			case 4:
				{
				setState(44);
				questionMultiAns();
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

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class InfixExpressionContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MULTIPLY() { return getToken(GrammarParser.MULTIPLY, 0); }
		public TerminalNode DIVIDE() { return getToken(GrammarParser.DIVIDE, 0); }
		public TerminalNode PLUS() { return getToken(GrammarParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(GrammarParser.MINUS, 0); }
		public InfixExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterInfixExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitInfixExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitInfixExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberExpressionContext extends ExpressionContext {
		public Token value;
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
		public TerminalNode VAR() { return getToken(GrammarParser.VAR, 0); }
		public NumberExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterNumberExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitNumberExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitNumberExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesisExpressionContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(GrammarParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(GrammarParser.RPAREN, 0); }
		public ParenthesisExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterParenthesisExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitParenthesisExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitParenthesisExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ComparisonExpressionContext extends ExpressionContext {
		public ExpressionContext left;
		public Token comp;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LESS() { return getToken(GrammarParser.LESS, 0); }
		public TerminalNode GREATER() { return getToken(GrammarParser.GREATER, 0); }
		public TerminalNode EQUALGREATER() { return getToken(GrammarParser.EQUALGREATER, 0); }
		public TerminalNode EQUALLESS() { return getToken(GrammarParser.EQUALLESS, 0); }
		public TerminalNode NOTEQUAL() { return getToken(GrammarParser.NOTEQUAL, 0); }
		public TerminalNode ISEQUAL() { return getToken(GrammarParser.ISEQUAL, 0); }
		public ComparisonExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterComparisonExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitComparisonExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitComparisonExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryExpressionContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(GrammarParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(GrammarParser.MINUS, 0); }
		public UnaryExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitUnaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalExpressionContext extends ExpressionContext {
		public ExpressionContext left;
		public Token log;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(GrammarParser.AND, 0); }
		public TerminalNode OR() { return getToken(GrammarParser.OR, 0); }
		public LogicalExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLogicalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLogicalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitLogicalExpression(this);
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
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				_localctx = new ParenthesisExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(48);
				match(LPAREN);
				setState(49);
				expression(0);
				setState(50);
				match(RPAREN);
				}
				break;
			case PLUS:
			case MINUS:
				{
				_localctx = new UnaryExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(52);
				((UnaryExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
					((UnaryExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(53);
				expression(6);
				}
				break;
			case NUM:
			case VAR:
				{
				_localctx = new NumberExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(54);
				((NumberExpressionContext)_localctx).value = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==NUM || _la==VAR) ) {
					((NumberExpressionContext)_localctx).value = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(71);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(69);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new InfixExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((InfixExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(57);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(58);
						((InfixExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULTIPLY || _la==DIVIDE) ) {
							((InfixExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(59);
						((InfixExpressionContext)_localctx).right = expression(6);
						}
						break;
					case 2:
						{
						_localctx = new InfixExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((InfixExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(60);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(61);
						((InfixExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((InfixExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(62);
						((InfixExpressionContext)_localctx).right = expression(5);
						}
						break;
					case 3:
						{
						_localctx = new LogicalExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((LogicalExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(63);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(64);
						((LogicalExpressionContext)_localctx).log = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==AND || _la==OR) ) {
							((LogicalExpressionContext)_localctx).log = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(65);
						((LogicalExpressionContext)_localctx).right = expression(4);
						}
						break;
					case 4:
						{
						_localctx = new ComparisonExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ComparisonExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(66);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(67);
						((ComparisonExpressionContext)_localctx).comp = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS) | (1L << GREATER) | (1L << EQUALGREATER) | (1L << EQUALLESS) | (1L << NOTEQUAL) | (1L << ISEQUAL))) != 0)) ) {
							((ComparisonExpressionContext)_localctx).comp = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(68);
						((ComparisonExpressionContext)_localctx).right = expression(3);
						}
						break;
					}
					} 
				}
				setState(73);
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

	public static class QuestionFormatContext extends ParserRuleContext {
		public TerminalNode LABEL() { return getToken(GrammarParser.LABEL, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(GrammarParser.ASSIGN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public QuestionFormatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionFormat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterQuestionFormat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitQuestionFormat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitQuestionFormat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionFormatContext questionFormat() throws RecognitionException {
		QuestionFormatContext _localctx = new QuestionFormatContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_questionFormat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(LABEL);
			setState(75);
			variable();
			setState(76);
			match(ASSIGN);
			setState(77);
			type();
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

	public static class QuestionAssignValueContext extends ParserRuleContext {
		public QuestionFormatContext questionFormat() {
			return getRuleContext(QuestionFormatContext.class,0);
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
		public QuestionAssignValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionAssignValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterQuestionAssignValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitQuestionAssignValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitQuestionAssignValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionAssignValueContext questionAssignValue() throws RecognitionException {
		QuestionAssignValueContext _localctx = new QuestionAssignValueContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_questionAssignValue);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			questionFormat();
			setState(80);
			match(EQUAL);
			setState(84);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(81);
					match(LPAREN);
					}
					} 
				}
				setState(86);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(87);
			expression(0);
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==RPAREN) {
				{
				{
				setState(88);
				match(RPAREN);
				}
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ConditionalIfContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(GrammarParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(GrammarParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(GrammarParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(GrammarParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(GrammarParser.RBRACE, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<QuestionTypesContext> questionTypes() {
			return getRuleContexts(QuestionTypesContext.class);
		}
		public QuestionTypesContext questionTypes(int i) {
			return getRuleContext(QuestionTypesContext.class,i);
		}
		public List<ConditionalElseContext> conditionalElse() {
			return getRuleContexts(ConditionalElseContext.class);
		}
		public ConditionalElseContext conditionalElse(int i) {
			return getRuleContext(ConditionalElseContext.class,i);
		}
		public List<ConditionalIfElseContext> conditionalIfElse() {
			return getRuleContexts(ConditionalIfElseContext.class);
		}
		public ConditionalIfElseContext conditionalIfElse(int i) {
			return getRuleContext(ConditionalIfElseContext.class,i);
		}
		public ConditionalIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterConditionalIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitConditionalIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitConditionalIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalIfContext conditionalIf() throws RecognitionException {
		ConditionalIfContext _localctx = new ConditionalIfContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_conditionalIf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(IF);
			setState(95);
			match(LPAREN);
			setState(98);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(96);
				variable();
				}
				break;
			case 2:
				{
				setState(97);
				expression(0);
				}
				break;
			}
			setState(100);
			match(RPAREN);
			setState(101);
			match(LBRACE);
			setState(103); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(102);
				questionTypes();
				}
				}
				setState(105); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IF || _la==LABEL );
			setState(107);
			match(RBRACE);
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELSE || _la==IFELSE) {
				{
				setState(110);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ELSE:
					{
					setState(108);
					conditionalElse();
					}
					break;
				case IFELSE:
					{
					setState(109);
					conditionalIfElse();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ConditionalIfElseContext extends ParserRuleContext {
		public TerminalNode IFELSE() { return getToken(GrammarParser.IFELSE, 0); }
		public TerminalNode LPAREN() { return getToken(GrammarParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(GrammarParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(GrammarParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(GrammarParser.RBRACE, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<QuestionTypesContext> questionTypes() {
			return getRuleContexts(QuestionTypesContext.class);
		}
		public QuestionTypesContext questionTypes(int i) {
			return getRuleContext(QuestionTypesContext.class,i);
		}
		public List<ConditionalElseContext> conditionalElse() {
			return getRuleContexts(ConditionalElseContext.class);
		}
		public ConditionalElseContext conditionalElse(int i) {
			return getRuleContext(ConditionalElseContext.class,i);
		}
		public ConditionalIfElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalIfElse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterConditionalIfElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitConditionalIfElse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitConditionalIfElse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalIfElseContext conditionalIfElse() throws RecognitionException {
		ConditionalIfElseContext _localctx = new ConditionalIfElseContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_conditionalIfElse);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(IFELSE);
			setState(116);
			match(LPAREN);
			setState(119);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(117);
				variable();
				}
				break;
			case 2:
				{
				setState(118);
				expression(0);
				}
				break;
			}
			setState(121);
			match(RPAREN);
			setState(122);
			match(LBRACE);
			setState(124); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(123);
				questionTypes();
				}
				}
				setState(126); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IF || _la==LABEL );
			setState(128);
			match(RBRACE);
			setState(131); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(131);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						setState(129);
						conditionalElse();
						}
						break;
					case 2:
						{
						setState(130);
						conditionalElse();
						}
						break;
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(133); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class ConditionalElseContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(GrammarParser.ELSE, 0); }
		public TerminalNode LBRACE() { return getToken(GrammarParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(GrammarParser.RBRACE, 0); }
		public List<QuestionTypesContext> questionTypes() {
			return getRuleContexts(QuestionTypesContext.class);
		}
		public QuestionTypesContext questionTypes(int i) {
			return getRuleContext(QuestionTypesContext.class,i);
		}
		public ConditionalElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalElse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterConditionalElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitConditionalElse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitConditionalElse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalElseContext conditionalElse() throws RecognitionException {
		ConditionalElseContext _localctx = new ConditionalElseContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_conditionalElse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(ELSE);
			setState(136);
			match(LBRACE);
			setState(138); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(137);
				questionTypes();
				}
				}
				setState(140); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IF || _la==LABEL );
			setState(142);
			match(RBRACE);
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

	public static class QuestionMultiAnsContext extends ParserRuleContext {
		public TerminalNode LABEL() { return getToken(GrammarParser.LABEL, 0); }
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(GrammarParser.ASSIGN, 0); }
		public TerminalNode MULTIPLEANSWER() { return getToken(GrammarParser.MULTIPLEANSWER, 0); }
		public TerminalNode LPAREN() { return getToken(GrammarParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(GrammarParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(GrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GrammarParser.COMMA, i);
		}
		public QuestionMultiAnsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionMultiAns; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterQuestionMultiAns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitQuestionMultiAns(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitQuestionMultiAns(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionMultiAnsContext questionMultiAns() throws RecognitionException {
		QuestionMultiAnsContext _localctx = new QuestionMultiAnsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_questionMultiAns);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(LABEL);
			setState(145);
			variable();
			setState(146);
			match(ASSIGN);
			setState(147);
			match(MULTIPLEANSWER);
			setState(148);
			match(LPAREN);
			{
			setState(149);
			variable();
			}
			setState(152); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(150);
				match(COMMA);
				setState(151);
				variable();
				}
				}
				setState(154); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==COMMA );
			setState(156);
			match(RPAREN);
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

	public static class FormStructureContext extends ParserRuleContext {
		public TerminalNode FORM() { return getToken(GrammarParser.FORM, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(GrammarParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(GrammarParser.RBRACE, 0); }
		public List<QuestionTypesContext> questionTypes() {
			return getRuleContexts(QuestionTypesContext.class);
		}
		public QuestionTypesContext questionTypes(int i) {
			return getRuleContext(QuestionTypesContext.class,i);
		}
		public FormStructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formStructure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterFormStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitFormStructure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarParserVisitor ) return ((GrammarParserVisitor<? extends T>)visitor).visitFormStructure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormStructureContext formStructure() throws RecognitionException {
		FormStructureContext _localctx = new FormStructureContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_formStructure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(FORM);
			setState(159);
			variable();
			setState(160);
			match(LBRACE);
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IF || _la==LABEL) {
				{
				{
				setState(161);
				questionTypes();
				}
				}
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(167);
			match(RBRACE);
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
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*\u00ac\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4"+
		"\3\4\5\4(\n\4\3\5\3\5\3\6\3\6\3\6\3\6\5\6\60\n\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\5\7:\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7"+
		"\7H\n\7\f\7\16\7K\13\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\7\tU\n\t\f\t\16"+
		"\tX\13\t\3\t\3\t\7\t\\\n\t\f\t\16\t_\13\t\3\n\3\n\3\n\3\n\5\ne\n\n\3\n"+
		"\3\n\3\n\6\nj\n\n\r\n\16\nk\3\n\3\n\3\n\7\nq\n\n\f\n\16\nt\13\n\3\13\3"+
		"\13\3\13\3\13\5\13z\n\13\3\13\3\13\3\13\6\13\177\n\13\r\13\16\13\u0080"+
		"\3\13\3\13\3\13\6\13\u0086\n\13\r\13\16\13\u0087\3\f\3\f\3\f\6\f\u008d"+
		"\n\f\r\f\16\f\u008e\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u009b"+
		"\n\r\r\r\16\r\u009c\3\r\3\r\3\16\3\16\3\16\3\16\7\16\u00a5\n\16\f\16\16"+
		"\16\u00a8\13\16\3\16\3\16\3\16\2\3\f\17\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\2\b\5\2\7\b\n\13\r\r\3\2\31\32\3\2\'(\3\2\33\34\3\2$%\3\2\36#\2\u00b7"+
		"\2\34\3\2\2\2\4\37\3\2\2\2\6\'\3\2\2\2\b)\3\2\2\2\n/\3\2\2\2\f9\3\2\2"+
		"\2\16L\3\2\2\2\20Q\3\2\2\2\22`\3\2\2\2\24u\3\2\2\2\26\u0089\3\2\2\2\30"+
		"\u0092\3\2\2\2\32\u00a0\3\2\2\2\34\35\5\32\16\2\35\36\7\2\2\3\36\3\3\2"+
		"\2\2\37 \5\f\7\2 !\7\2\2\3!\5\3\2\2\2\"(\7(\2\2#(\7*\2\2$(\7\'\2\2%&\7"+
		"&\2\2&(\7(\2\2\'\"\3\2\2\2\'#\3\2\2\2\'$\3\2\2\2\'%\3\2\2\2(\7\3\2\2\2"+
		")*\t\2\2\2*\t\3\2\2\2+\60\5\16\b\2,\60\5\22\n\2-\60\5\20\t\2.\60\5\30"+
		"\r\2/+\3\2\2\2/,\3\2\2\2/-\3\2\2\2/.\3\2\2\2\60\13\3\2\2\2\61\62\b\7\1"+
		"\2\62\63\7\22\2\2\63\64\5\f\7\2\64\65\7\23\2\2\65:\3\2\2\2\66\67\t\3\2"+
		"\2\67:\5\f\7\b8:\t\4\2\29\61\3\2\2\29\66\3\2\2\298\3\2\2\2:I\3\2\2\2;"+
		"<\f\7\2\2<=\t\5\2\2=H\5\f\7\b>?\f\6\2\2?@\t\3\2\2@H\5\f\7\7AB\f\5\2\2"+
		"BC\t\6\2\2CH\5\f\7\6DE\f\4\2\2EF\t\7\2\2FH\5\f\7\5G;\3\2\2\2G>\3\2\2\2"+
		"GA\3\2\2\2GD\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\r\3\2\2\2KI\3\2\2"+
		"\2LM\7)\2\2MN\5\6\4\2NO\7\16\2\2OP\5\b\5\2P\17\3\2\2\2QR\5\16\b\2RV\7"+
		"\35\2\2SU\7\22\2\2TS\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WY\3\2\2\2X"+
		"V\3\2\2\2Y]\5\f\7\2Z\\\7\23\2\2[Z\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2"+
		"\2^\21\3\2\2\2_]\3\2\2\2`a\7\17\2\2ad\7\22\2\2be\5\6\4\2ce\5\f\7\2db\3"+
		"\2\2\2dc\3\2\2\2ef\3\2\2\2fg\7\23\2\2gi\7\24\2\2hj\5\n\6\2ih\3\2\2\2j"+
		"k\3\2\2\2ki\3\2\2\2kl\3\2\2\2lm\3\2\2\2mr\7\25\2\2nq\5\26\f\2oq\5\24\13"+
		"\2pn\3\2\2\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2s\23\3\2\2\2tr\3\2"+
		"\2\2uv\7\21\2\2vy\7\22\2\2wz\5\6\4\2xz\5\f\7\2yw\3\2\2\2yx\3\2\2\2z{\3"+
		"\2\2\2{|\7\23\2\2|~\7\24\2\2}\177\5\n\6\2~}\3\2\2\2\177\u0080\3\2\2\2"+
		"\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0085\7"+
		"\25\2\2\u0083\u0086\5\26\f\2\u0084\u0086\5\26\f\2\u0085\u0083\3\2\2\2"+
		"\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088"+
		"\3\2\2\2\u0088\25\3\2\2\2\u0089\u008a\7\20\2\2\u008a\u008c\7\24\2\2\u008b"+
		"\u008d\5\n\6\2\u008c\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008c\3\2"+
		"\2\2\u008e\u008f\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\7\25\2\2\u0091"+
		"\27\3\2\2\2\u0092\u0093\7)\2\2\u0093\u0094\5\6\4\2\u0094\u0095\7\16\2"+
		"\2\u0095\u0096\7\f\2\2\u0096\u0097\7\22\2\2\u0097\u009a\5\6\4\2\u0098"+
		"\u0099\7\27\2\2\u0099\u009b\5\6\4\2\u009a\u0098\3\2\2\2\u009b\u009c\3"+
		"\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e"+
		"\u009f\7\23\2\2\u009f\31\3\2\2\2\u00a0\u00a1\7\6\2\2\u00a1\u00a2\5\6\4"+
		"\2\u00a2\u00a6\7\24\2\2\u00a3\u00a5\5\n\6\2\u00a4\u00a3\3\2\2\2\u00a5"+
		"\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\3\2"+
		"\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00aa\7\25\2\2\u00aa\33\3\2\2\2\24\'/9"+
		"GIV]dkpry\u0080\u0085\u0087\u008e\u009c\u00a6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}