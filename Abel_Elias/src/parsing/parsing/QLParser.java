// Generated from C:/dev/uva/endless-ql/Abel_Elias/src/parsing\QL.g4 by ANTLR 4.7
package parsing;
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
		WS=1, BOOLEANTYPE=2, STRINGTYPE=3, INTEGERTYPE=4, MONEYTYPE=5, DATETYPE=6, 
		DECIMALTYPE=7, FORM=8, IF=9, COLON=10, CURLY_BRACE_L=11, CURLY_BRACE_R=12, 
		BRACE_L=13, BRACE_R=14, ADD=15, SUB=16, MUL=17, DIV=18, REM=19, EQT=20, 
		GRT=21, LST=22, GRTE=23, LSTE=24, AND=25, NEQT=26, OR=27, NOT=28, IDENTIFIER=29, 
		STR=30, INT=31, BOOL=32, MON=33, DEC=34, NEWLINE=35;
	public static final int
		RULE_form = 0, RULE_block = 1, RULE_question = 2, RULE_statement = 3, 
		RULE_expression = 4, RULE_operator = 5, RULE_boolOperator = 6, RULE_comparisonOperator = 7, 
		RULE_numberOperator = 8, RULE_ifStatement = 9, RULE_type = 10;
	public static final String[] ruleNames = {
		"form", "block", "question", "statement", "expression", "operator", "boolOperator", 
		"comparisonOperator", "numberOperator", "ifStatement", "type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'boolean'", "'string'", "'integer'", null, "'date'", "'decimal'", 
		"'form'", "'if'", "':'", "'{'", "'}'", "'('", "')'", "'+'", "'-'", "'*'", 
		"'/'", "'%'", "'=='", "'>'", "'<'", "'>='", "'<='", "'&&'", "'!='", "'||'", 
		"'!'", null, null, null, null, null, null, "'\n'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE", "MONEYTYPE", "DATETYPE", 
		"DECIMALTYPE", "FORM", "IF", "COLON", "CURLY_BRACE_L", "CURLY_BRACE_R", 
		"BRACE_L", "BRACE_R", "ADD", "SUB", "MUL", "DIV", "REM", "EQT", "GRT", 
		"LST", "GRTE", "LSTE", "AND", "NEQT", "OR", "NOT", "IDENTIFIER", "STR", 
		"INT", "BOOL", "MON", "DEC", "NEWLINE"
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
	public static class FormContext extends ParserRuleContext {
		public TerminalNode FORM() { return getToken(QLParser.FORM, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QLParser.IDENTIFIER, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode EOF() { return getToken(QLParser.EOF, 0); }
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
		enterRule(_localctx, 0, RULE_form);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			match(FORM);
			setState(23);
			match(IDENTIFIER);
			setState(24);
			block();
			setState(25);
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

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode CURLY_BRACE_L() { return getToken(QLParser.CURLY_BRACE_L, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(QLParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(QLParser.NEWLINE, i);
		}
		public TerminalNode CURLY_BRACE_R() { return getToken(QLParser.CURLY_BRACE_R, 0); }
		public List<IfStatementContext> ifStatement() {
			return getRuleContexts(IfStatementContext.class);
		}
		public IfStatementContext ifStatement(int i) {
			return getRuleContext(IfStatementContext.class,i);
		}
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(CURLY_BRACE_L);
			setState(28);
			match(NEWLINE);
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IF || _la==IDENTIFIER) {
				{
				{
				setState(32);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(29);
					ifStatement();
					}
					break;
				case 2:
					{
					setState(30);
					question();
					}
					break;
				case 3:
					{
					setState(31);
					statement();
					}
					break;
				}
				setState(34);
				match(NEWLINE);
				}
				}
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(41);
			match(CURLY_BRACE_R);
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
		public TerminalNode IDENTIFIER() { return getToken(QLParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(QLParser.COLON, 0); }
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
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
		enterRule(_localctx, 4, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(IDENTIFIER);
			setState(44);
			match(COLON);
			setState(45);
			match(STR);
			setState(46);
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

	public static class StatementContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QLParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(QLParser.COLON, 0); }
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(IDENTIFIER);
			setState(49);
			match(COLON);
			setState(50);
			match(STR);
			setState(51);
			type();
			setState(52);
			expression(0);
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
		public TerminalNode BOOL() { return getToken(QLParser.BOOL, 0); }
		public TerminalNode MON() { return getToken(QLParser.MON, 0); }
		public TerminalNode INT() { return getToken(QLParser.INT, 0); }
		public TerminalNode DEC() { return getToken(QLParser.DEC, 0); }
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QLParser.IDENTIFIER, 0); }
		public TerminalNode BRACE_L() { return getToken(QLParser.BRACE_L, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BRACE_R() { return getToken(QLParser.BRACE_R, 0); }
		public TerminalNode NOT() { return getToken(QLParser.NOT, 0); }
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
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
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
				{
				setState(55);
				match(BOOL);
				}
				break;
			case MON:
				{
				setState(56);
				match(MON);
				}
				break;
			case INT:
				{
				setState(57);
				match(INT);
				}
				break;
			case DEC:
				{
				setState(58);
				match(DEC);
				}
				break;
			case STR:
				{
				setState(59);
				match(STR);
				}
				break;
			case IDENTIFIER:
				{
				setState(60);
				match(IDENTIFIER);
				}
				break;
			case BRACE_L:
				{
				setState(61);
				match(BRACE_L);
				setState(62);
				expression(0);
				setState(63);
				match(BRACE_R);
				}
				break;
			case NOT:
				{
				setState(65);
				match(NOT);
				setState(66);
				expression(1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(75);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(69);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(70);
					operator();
					setState(71);
					expression(3);
					}
					} 
				}
				setState(77);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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
		public BoolOperatorContext boolOperator() {
			return getRuleContext(BoolOperatorContext.class,0);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public NumberOperatorContext numberOperator() {
			return getRuleContext(NumberOperatorContext.class,0);
		}
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitOperator(this);
		}
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
			setState(81);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				boolOperator();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				comparisonOperator();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(80);
				numberOperator();
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

	public static class BoolOperatorContext extends ParserRuleContext {
		public TerminalNode EQT() { return getToken(QLParser.EQT, 0); }
		public TerminalNode NEQT() { return getToken(QLParser.NEQT, 0); }
		public TerminalNode AND() { return getToken(QLParser.AND, 0); }
		public TerminalNode OR() { return getToken(QLParser.OR, 0); }
		public BoolOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterBoolOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitBoolOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBoolOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolOperatorContext boolOperator() throws RecognitionException {
		BoolOperatorContext _localctx = new BoolOperatorContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_boolOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQT) | (1L << AND) | (1L << NEQT) | (1L << OR))) != 0)) ) {
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

	public static class ComparisonOperatorContext extends ParserRuleContext {
		public TerminalNode EQT() { return getToken(QLParser.EQT, 0); }
		public TerminalNode GRT() { return getToken(QLParser.GRT, 0); }
		public TerminalNode LST() { return getToken(QLParser.LST, 0); }
		public TerminalNode GRTE() { return getToken(QLParser.GRTE, 0); }
		public TerminalNode LSTE() { return getToken(QLParser.LSTE, 0); }
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitComparisonOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQT) | (1L << GRT) | (1L << LST) | (1L << GRTE) | (1L << LSTE))) != 0)) ) {
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

	public static class NumberOperatorContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(QLParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(QLParser.SUB, 0); }
		public TerminalNode MUL() { return getToken(QLParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(QLParser.DIV, 0); }
		public TerminalNode REM() { return getToken(QLParser.REM, 0); }
		public NumberOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numberOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterNumberOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitNumberOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitNumberOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberOperatorContext numberOperator() throws RecognitionException {
		NumberOperatorContext _localctx = new NumberOperatorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_numberOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << SUB) | (1L << MUL) | (1L << DIV) | (1L << REM))) != 0)) ) {
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

	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(QLParser.IF, 0); }
		public TerminalNode BRACE_L() { return getToken(QLParser.BRACE_L, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode BRACE_R() { return getToken(QLParser.BRACE_R, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(IF);
			setState(90);
			match(BRACE_L);
			setState(91);
			expression(0);
			setState(92);
			match(BRACE_R);
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CURLY_BRACE_L) {
				{
				{
				setState(93);
				block();
				}
				}
				setState(98);
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode BOOLEANTYPE() { return getToken(QLParser.BOOLEANTYPE, 0); }
		public TerminalNode STRINGTYPE() { return getToken(QLParser.STRINGTYPE, 0); }
		public TerminalNode INTEGERTYPE() { return getToken(QLParser.INTEGERTYPE, 0); }
		public TerminalNode MONEYTYPE() { return getToken(QLParser.MONEYTYPE, 0); }
		public TerminalNode DATETYPE() { return getToken(QLParser.DATETYPE, 0); }
		public TerminalNode DECIMALTYPE() { return getToken(QLParser.DECIMALTYPE, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEANTYPE) | (1L << STRINGTYPE) | (1L << INTEGERTYPE) | (1L << MONEYTYPE) | (1L << DATETYPE) | (1L << DECIMALTYPE))) != 0)) ) {
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
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3%h\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f"+
		"\t\f\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3#\n\3\3\3\3\3\7\3\'\n"+
		"\3\f\3\16\3*\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6F\n\6\3\6\3\6"+
		"\3\6\3\6\7\6L\n\6\f\6\16\6O\13\6\3\7\3\7\3\7\5\7T\n\7\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\7\13a\n\13\f\13\16\13d\13\13\3\f\3\f"+
		"\3\f\2\3\n\r\2\4\6\b\n\f\16\20\22\24\26\2\6\4\2\26\26\33\35\3\2\26\32"+
		"\3\2\21\25\3\2\4\t\2j\2\30\3\2\2\2\4\35\3\2\2\2\6-\3\2\2\2\b\62\3\2\2"+
		"\2\nE\3\2\2\2\fS\3\2\2\2\16U\3\2\2\2\20W\3\2\2\2\22Y\3\2\2\2\24[\3\2\2"+
		"\2\26e\3\2\2\2\30\31\7\n\2\2\31\32\7\37\2\2\32\33\5\4\3\2\33\34\7\2\2"+
		"\3\34\3\3\2\2\2\35\36\7\r\2\2\36(\7%\2\2\37#\5\24\13\2 #\5\6\4\2!#\5\b"+
		"\5\2\"\37\3\2\2\2\" \3\2\2\2\"!\3\2\2\2#$\3\2\2\2$%\7%\2\2%\'\3\2\2\2"+
		"&\"\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)+\3\2\2\2*(\3\2\2\2+,\7\16"+
		"\2\2,\5\3\2\2\2-.\7\37\2\2./\7\f\2\2/\60\7 \2\2\60\61\5\26\f\2\61\7\3"+
		"\2\2\2\62\63\7\37\2\2\63\64\7\f\2\2\64\65\7 \2\2\65\66\5\26\f\2\66\67"+
		"\5\n\6\2\67\t\3\2\2\289\b\6\1\29F\7\"\2\2:F\7#\2\2;F\7!\2\2<F\7$\2\2="+
		"F\7 \2\2>F\7\37\2\2?@\7\17\2\2@A\5\n\6\2AB\7\20\2\2BF\3\2\2\2CD\7\36\2"+
		"\2DF\5\n\6\3E8\3\2\2\2E:\3\2\2\2E;\3\2\2\2E<\3\2\2\2E=\3\2\2\2E>\3\2\2"+
		"\2E?\3\2\2\2EC\3\2\2\2FM\3\2\2\2GH\f\4\2\2HI\5\f\7\2IJ\5\n\6\5JL\3\2\2"+
		"\2KG\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2N\13\3\2\2\2OM\3\2\2\2PT\5\16"+
		"\b\2QT\5\20\t\2RT\5\22\n\2SP\3\2\2\2SQ\3\2\2\2SR\3\2\2\2T\r\3\2\2\2UV"+
		"\t\2\2\2V\17\3\2\2\2WX\t\3\2\2X\21\3\2\2\2YZ\t\4\2\2Z\23\3\2\2\2[\\\7"+
		"\13\2\2\\]\7\17\2\2]^\5\n\6\2^b\7\20\2\2_a\5\4\3\2`_\3\2\2\2ad\3\2\2\2"+
		"b`\3\2\2\2bc\3\2\2\2c\25\3\2\2\2db\3\2\2\2ef\t\5\2\2f\27\3\2\2\2\b\"("+
		"EMSb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}