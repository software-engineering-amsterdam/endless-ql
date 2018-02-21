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
		public TerminalNode CURLY_BRACE_R() { return getToken(QLParser.CURLY_BRACE_R, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(QLParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(QLParser.NEWLINE, i);
		}
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(CURLY_BRACE_L);
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(28);
				match(NEWLINE);
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IF || _la==IDENTIFIER) {
				{
				{
				setState(37);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(34);
					ifStatement();
					}
					break;
				case 2:
					{
					setState(35);
					question();
					}
					break;
				case 3:
					{
					setState(36);
					statement();
					}
					break;
				}
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(39);
					match(NEWLINE);
					}
					}
					setState(44);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
			match(CURLY_BRACE_R);
			setState(54);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(51);
					match(NEWLINE);
					}
					} 
				}
				setState(56);
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
			setState(57);
			match(IDENTIFIER);
			setState(58);
			match(COLON);
			setState(59);
			match(STR);
			setState(60);
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
			setState(62);
			match(IDENTIFIER);
			setState(63);
			match(COLON);
			setState(64);
			match(STR);
			setState(65);
			type();
			setState(66);
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
			setState(81);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
				{
				setState(69);
				match(BOOL);
				}
				break;
			case MON:
				{
				setState(70);
				match(MON);
				}
				break;
			case INT:
				{
				setState(71);
				match(INT);
				}
				break;
			case DEC:
				{
				setState(72);
				match(DEC);
				}
				break;
			case STR:
				{
				setState(73);
				match(STR);
				}
				break;
			case IDENTIFIER:
				{
				setState(74);
				match(IDENTIFIER);
				}
				break;
			case BRACE_L:
				{
				setState(75);
				match(BRACE_L);
				setState(76);
				expression(0);
				setState(77);
				match(BRACE_R);
				}
				break;
			case NOT:
				{
				setState(79);
				match(NOT);
				setState(80);
				expression(1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(89);
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
					setState(83);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(84);
					operator();
					setState(85);
					expression(3);
					}
					} 
				}
				setState(91);
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
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				boolOperator();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
				comparisonOperator();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(94);
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
			setState(97);
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
			setState(99);
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
			setState(101);
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
			setState(103);
			match(IF);
			setState(104);
			match(BRACE_L);
			setState(105);
			expression(0);
			setState(106);
			match(BRACE_R);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CURLY_BRACE_L) {
				{
				{
				setState(107);
				block();
				}
				}
				setState(112);
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
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StringtypeContext extends TypeContext {
		public TerminalNode STRINGTYPE() { return getToken(QLParser.STRINGTYPE, 0); }
		public StringtypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitStringtype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DatetypeContext extends TypeContext {
		public TerminalNode DATETYPE() { return getToken(QLParser.DATETYPE, 0); }
		public DatetypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitDatetype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegertypeContext extends TypeContext {
		public TerminalNode INTEGERTYPE() { return getToken(QLParser.INTEGERTYPE, 0); }
		public IntegertypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIntegertype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MoneytypeContext extends TypeContext {
		public TerminalNode MONEYTYPE() { return getToken(QLParser.MONEYTYPE, 0); }
		public MoneytypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitMoneytype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DecimaltypeContext extends TypeContext {
		public TerminalNode DECIMALTYPE() { return getToken(QLParser.DECIMALTYPE, 0); }
		public DecimaltypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitDecimaltype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooltypeContext extends TypeContext {
		public TerminalNode BOOLEANTYPE() { return getToken(QLParser.BOOLEANTYPE, 0); }
		public BooltypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBooltype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_type);
		try {
			setState(119);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEANTYPE:
				_localctx = new BooltypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				match(BOOLEANTYPE);
				}
				break;
			case STRINGTYPE:
				_localctx = new StringtypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				match(STRINGTYPE);
				}
				break;
			case INTEGERTYPE:
				_localctx = new IntegertypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(115);
				match(INTEGERTYPE);
				}
				break;
			case MONEYTYPE:
				_localctx = new MoneytypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(116);
				match(MONEYTYPE);
				}
				break;
			case DATETYPE:
				_localctx = new DatetypeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(117);
				match(DATETYPE);
				}
				break;
			case DECIMALTYPE:
				_localctx = new DecimaltypeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(118);
				match(DECIMALTYPE);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3%|\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f"+
		"\t\f\3\2\3\2\3\2\3\2\3\2\3\3\3\3\7\3 \n\3\f\3\16\3#\13\3\3\3\3\3\3\3\5"+
		"\3(\n\3\3\3\7\3+\n\3\f\3\16\3.\13\3\7\3\60\n\3\f\3\16\3\63\13\3\3\3\3"+
		"\3\7\3\67\n\3\f\3\16\3:\13\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6T\n\6\3\6\3"+
		"\6\3\6\3\6\7\6Z\n\6\f\6\16\6]\13\6\3\7\3\7\3\7\5\7b\n\7\3\b\3\b\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\7\13o\n\13\f\13\16\13r\13\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\5\fz\n\f\3\f\2\3\n\r\2\4\6\b\n\f\16\20\22\24\26\2\5"+
		"\4\2\26\26\33\35\3\2\26\32\3\2\21\25\2\u0086\2\30\3\2\2\2\4\35\3\2\2\2"+
		"\6;\3\2\2\2\b@\3\2\2\2\nS\3\2\2\2\fa\3\2\2\2\16c\3\2\2\2\20e\3\2\2\2\22"+
		"g\3\2\2\2\24i\3\2\2\2\26y\3\2\2\2\30\31\7\n\2\2\31\32\7\37\2\2\32\33\5"+
		"\4\3\2\33\34\7\2\2\3\34\3\3\2\2\2\35!\7\r\2\2\36 \7%\2\2\37\36\3\2\2\2"+
		" #\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\61\3\2\2\2#!\3\2\2\2$(\5\24\13\2%"+
		"(\5\6\4\2&(\5\b\5\2\'$\3\2\2\2\'%\3\2\2\2\'&\3\2\2\2(,\3\2\2\2)+\7%\2"+
		"\2*)\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\60\3\2\2\2.,\3\2\2\2/\'\3"+
		"\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\64\3\2\2\2\63\61\3\2"+
		"\2\2\648\7\16\2\2\65\67\7%\2\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\28"+
		"9\3\2\2\29\5\3\2\2\2:8\3\2\2\2;<\7\37\2\2<=\7\f\2\2=>\7 \2\2>?\5\26\f"+
		"\2?\7\3\2\2\2@A\7\37\2\2AB\7\f\2\2BC\7 \2\2CD\5\26\f\2DE\5\n\6\2E\t\3"+
		"\2\2\2FG\b\6\1\2GT\7\"\2\2HT\7#\2\2IT\7!\2\2JT\7$\2\2KT\7 \2\2LT\7\37"+
		"\2\2MN\7\17\2\2NO\5\n\6\2OP\7\20\2\2PT\3\2\2\2QR\7\36\2\2RT\5\n\6\3SF"+
		"\3\2\2\2SH\3\2\2\2SI\3\2\2\2SJ\3\2\2\2SK\3\2\2\2SL\3\2\2\2SM\3\2\2\2S"+
		"Q\3\2\2\2T[\3\2\2\2UV\f\4\2\2VW\5\f\7\2WX\5\n\6\5XZ\3\2\2\2YU\3\2\2\2"+
		"Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\\13\3\2\2\2][\3\2\2\2^b\5\16\b\2_b\5"+
		"\20\t\2`b\5\22\n\2a^\3\2\2\2a_\3\2\2\2a`\3\2\2\2b\r\3\2\2\2cd\t\2\2\2"+
		"d\17\3\2\2\2ef\t\3\2\2f\21\3\2\2\2gh\t\4\2\2h\23\3\2\2\2ij\7\13\2\2jk"+
		"\7\17\2\2kl\5\n\6\2lp\7\20\2\2mo\5\4\3\2nm\3\2\2\2or\3\2\2\2pn\3\2\2\2"+
		"pq\3\2\2\2q\25\3\2\2\2rp\3\2\2\2sz\7\4\2\2tz\7\5\2\2uz\7\6\2\2vz\7\7\2"+
		"\2wz\7\b\2\2xz\7\t\2\2ys\3\2\2\2yt\3\2\2\2yu\3\2\2\2yv\3\2\2\2yw\3\2\2"+
		"\2yx\3\2\2\2z\27\3\2\2\2\f!\',\618S[apy";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}