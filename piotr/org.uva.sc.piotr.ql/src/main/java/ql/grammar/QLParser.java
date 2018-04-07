// Generated from /Users/piotrkosytorz/Library/Mobile Documents/com~apple~CloudDocs/Study/SC/endless-ql/piotr/org.uva.sc.piotr.ql/src/main/java/ql/grammar/QL.g4 by ANTLR 4.7
package ql.grammar;
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
		T__0=1, T__1=2, T__2=3, T__3=4, OP_AND=5, OP_OR=6, OP_NOT=7, OP_ASSIG=8, 
		OP_MULT=9, OP_DIV=10, OP_PLUS=11, OP_MINUS=12, OP_GT=13, OP_GE=14, OP_LT=15, 
		OP_LE=16, OP_EQ=17, OP_NEQ=18, IF=19, ELSE=20, BEGIN=21, END=22, TYPE_BOOLEAN=23, 
		TYPE_STRING=24, TYPE_INTEGER=25, TYPE_DECIMAL=26, TYPE_MONEY=27, TYPE_DATE=28, 
		BOOL_TRUE=29, BOOL_FALSE=30, WS=31, COMMENT=32, MONEY=33, IDENTIFIER=34, 
		INTEGER=35, STRING=36, DECIMAL=37, DAY=38, MONTH=39, YEAR=40, DATE=41;
	public static final int
		RULE_form = 0, RULE_statement = 1, RULE_question = 2, RULE_dataType = 3, 
		RULE_ifStatement = 4, RULE_elseStatement = 5, RULE_expression = 6;
	public static final String[] ruleNames = {
		"form", "statement", "question", "dataType", "ifStatement", "elseStatement", 
		"expression"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "':'", "'('", "')'", "'&&'", "'||'", "'!'", "'='", "'*'", 
		"'/'", "'+'", "'-'", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'if'", 
		"'else'", "'{'", "'}'", "'boolean'", "'string'", "'integer'", "'decimal'", 
		"'money'", "'date'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "OP_AND", "OP_OR", "OP_NOT", "OP_ASSIG", 
		"OP_MULT", "OP_DIV", "OP_PLUS", "OP_MINUS", "OP_GT", "OP_GE", "OP_LT", 
		"OP_LE", "OP_EQ", "OP_NEQ", "IF", "ELSE", "BEGIN", "END", "TYPE_BOOLEAN", 
		"TYPE_STRING", "TYPE_INTEGER", "TYPE_DECIMAL", "TYPE_MONEY", "TYPE_DATE", 
		"BOOL_TRUE", "BOOL_FALSE", "WS", "COMMENT", "MONEY", "IDENTIFIER", "INTEGER", 
		"STRING", "DECIMAL", "DAY", "MONTH", "YEAR", "DATE"
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
		public Token id;
		public TerminalNode BEGIN() { return getToken(QLParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(QLParser.END, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QLParser.IDENTIFIER, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
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
		enterRule(_localctx, 0, RULE_form);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			match(T__0);
			setState(15);
			((FormContext)_localctx).id = match(IDENTIFIER);
			setState(16);
			match(BEGIN);
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IF || _la==STRING) {
				{
				{
				setState(17);
				statement();
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
			match(END);
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
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
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
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(27);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(25);
				question();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				ifStatement();
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

	public static class QuestionContext extends ParserRuleContext {
		public Token label;
		public Token variableName;
		public DataTypeContext variableType;
		public ExpressionContext assignment;
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QLParser.IDENTIFIER, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TerminalNode OP_ASSIG() { return getToken(QLParser.OP_ASSIG, 0); }
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			((QuestionContext)_localctx).label = match(STRING);
			setState(30);
			((QuestionContext)_localctx).variableName = match(IDENTIFIER);
			setState(31);
			match(T__1);
			setState(32);
			((QuestionContext)_localctx).variableType = dataType();
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OP_ASSIG) {
				{
				setState(33);
				match(OP_ASSIG);
				setState(34);
				((QuestionContext)_localctx).assignment = expression(0);
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

	public static class DataTypeContext extends ParserRuleContext {
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
	 
		public DataTypeContext() { }
		public void copyFrom(DataTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeDeclarationIntegerContext extends DataTypeContext {
		public TerminalNode TYPE_INTEGER() { return getToken(QLParser.TYPE_INTEGER, 0); }
		public TypeDeclarationIntegerContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitTypeDeclarationInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeDeclarationDecimalContext extends DataTypeContext {
		public TerminalNode TYPE_DECIMAL() { return getToken(QLParser.TYPE_DECIMAL, 0); }
		public TypeDeclarationDecimalContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitTypeDeclarationDecimal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeDeclarationDateContext extends DataTypeContext {
		public TerminalNode TYPE_DATE() { return getToken(QLParser.TYPE_DATE, 0); }
		public TypeDeclarationDateContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitTypeDeclarationDate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeDeclarationMoneyContext extends DataTypeContext {
		public TerminalNode TYPE_MONEY() { return getToken(QLParser.TYPE_MONEY, 0); }
		public TypeDeclarationMoneyContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitTypeDeclarationMoney(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeDeclarationStringContext extends DataTypeContext {
		public TerminalNode TYPE_STRING() { return getToken(QLParser.TYPE_STRING, 0); }
		public TypeDeclarationStringContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitTypeDeclarationString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeDeclarationBooleanContext extends DataTypeContext {
		public TerminalNode TYPE_BOOLEAN() { return getToken(QLParser.TYPE_BOOLEAN, 0); }
		public TypeDeclarationBooleanContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitTypeDeclarationBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_dataType);
		try {
			setState(43);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE_BOOLEAN:
				_localctx = new TypeDeclarationBooleanContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(37);
				match(TYPE_BOOLEAN);
				}
				break;
			case TYPE_STRING:
				_localctx = new TypeDeclarationStringContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(38);
				match(TYPE_STRING);
				}
				break;
			case TYPE_INTEGER:
				_localctx = new TypeDeclarationIntegerContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(39);
				match(TYPE_INTEGER);
				}
				break;
			case TYPE_DECIMAL:
				_localctx = new TypeDeclarationDecimalContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(40);
				match(TYPE_DECIMAL);
				}
				break;
			case TYPE_MONEY:
				_localctx = new TypeDeclarationMoneyContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(41);
				match(TYPE_MONEY);
				}
				break;
			case TYPE_DATE:
				_localctx = new TypeDeclarationDateContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(42);
				match(TYPE_DATE);
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

	public static class IfStatementContext extends ParserRuleContext {
		public ExpressionContext condition;
		public TerminalNode IF() { return getToken(QLParser.IF, 0); }
		public TerminalNode BEGIN() { return getToken(QLParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(QLParser.END, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ElseStatementContext elseStatement() {
			return getRuleContext(ElseStatementContext.class,0);
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
		enterRule(_localctx, 8, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(IF);
			setState(46);
			match(T__2);
			setState(47);
			((IfStatementContext)_localctx).condition = expression(0);
			setState(48);
			match(T__3);
			setState(49);
			match(BEGIN);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IF || _la==STRING) {
				{
				{
				setState(50);
				statement();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			match(END);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(57);
				elseStatement();
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

	public static class ElseStatementContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(QLParser.ELSE, 0); }
		public TerminalNode BEGIN() { return getToken(QLParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(QLParser.END, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ElseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitElseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseStatementContext elseStatement() throws RecognitionException {
		ElseStatementContext _localctx = new ElseStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_elseStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(ELSE);
			setState(61);
			match(BEGIN);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IF || _la==STRING) {
				{
				{
				setState(62);
				statement();
				}
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(68);
			match(END);
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
	public static class ExpressionParenthesisesContext extends ExpressionContext {
		public ExpressionContext subExpression;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionParenthesisesContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionParenthesises(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionComparisionLessThanContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_LT() { return getToken(QLParser.OP_LT, 0); }
		public ExpressionComparisionLessThanContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionComparisionLessThan(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionComparisionNotEqualContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_NEQ() { return getToken(QLParser.OP_NEQ, 0); }
		public ExpressionComparisionNotEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionComparisionNotEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionComparisionEqualContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_EQ() { return getToken(QLParser.OP_EQ, 0); }
		public ExpressionComparisionEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionComparisionEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionSingleValueContext extends ExpressionContext {
		public Token value;
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public TerminalNode INTEGER() { return getToken(QLParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(QLParser.DECIMAL, 0); }
		public TerminalNode MONEY() { return getToken(QLParser.MONEY, 0); }
		public TerminalNode DATE() { return getToken(QLParser.DATE, 0); }
		public TerminalNode BOOL_TRUE() { return getToken(QLParser.BOOL_TRUE, 0); }
		public TerminalNode BOOL_FALSE() { return getToken(QLParser.BOOL_FALSE, 0); }
		public ExpressionSingleValueContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionSingleValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionArithmeticMultiplicationContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_MULT() { return getToken(QLParser.OP_MULT, 0); }
		public ExpressionArithmeticMultiplicationContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionArithmeticMultiplication(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionNegationContext extends ExpressionContext {
		public ExpressionContext subExpression;
		public TerminalNode OP_NOT() { return getToken(QLParser.OP_NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionNegationContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionNegation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionLogicalOrContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_OR() { return getToken(QLParser.OP_OR, 0); }
		public ExpressionLogicalOrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionLogicalOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionComparisionLessEqualContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_LE() { return getToken(QLParser.OP_LE, 0); }
		public ExpressionComparisionLessEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionComparisionLessEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionLogicalAndContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_AND() { return getToken(QLParser.OP_AND, 0); }
		public ExpressionLogicalAndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionLogicalAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionArithmeticDivisionContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_DIV() { return getToken(QLParser.OP_DIV, 0); }
		public ExpressionArithmeticDivisionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionArithmeticDivision(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionComparisionGreaterEqualContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_GE() { return getToken(QLParser.OP_GE, 0); }
		public ExpressionComparisionGreaterEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionComparisionGreaterEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionVariableReferenceContext extends ExpressionContext {
		public Token variableReference;
		public TerminalNode IDENTIFIER() { return getToken(QLParser.IDENTIFIER, 0); }
		public ExpressionVariableReferenceContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionVariableReference(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionArithmeticMinusContext extends ExpressionContext {
		public ExpressionContext subExpression;
		public TerminalNode OP_MINUS() { return getToken(QLParser.OP_MINUS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionArithmeticMinusContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionArithmeticMinus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionComparisionGreaterThanContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_GT() { return getToken(QLParser.OP_GT, 0); }
		public ExpressionComparisionGreaterThanContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionComparisionGreaterThan(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionArithmeticSubtractionContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_MINUS() { return getToken(QLParser.OP_MINUS, 0); }
		public ExpressionArithmeticSubtractionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionArithmeticSubtraction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionArithmeticAdditionContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_PLUS() { return getToken(QLParser.OP_PLUS, 0); }
		public ExpressionArithmeticAdditionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionArithmeticAddition(this);
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
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				_localctx = new ExpressionParenthesisesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(71);
				match(T__2);
				setState(72);
				((ExpressionParenthesisesContext)_localctx).subExpression = expression(0);
				setState(73);
				match(T__3);
				}
				break;
			case OP_NOT:
				{
				_localctx = new ExpressionNegationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(75);
				match(OP_NOT);
				setState(76);
				((ExpressionNegationContext)_localctx).subExpression = expression(4);
				}
				break;
			case OP_MINUS:
				{
				_localctx = new ExpressionArithmeticMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(77);
				match(OP_MINUS);
				setState(78);
				((ExpressionArithmeticMinusContext)_localctx).subExpression = expression(3);
				}
				break;
			case IDENTIFIER:
				{
				_localctx = new ExpressionVariableReferenceContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(79);
				((ExpressionVariableReferenceContext)_localctx).variableReference = match(IDENTIFIER);
				}
				break;
			case BOOL_TRUE:
			case BOOL_FALSE:
			case MONEY:
			case INTEGER:
			case STRING:
			case DECIMAL:
			case DATE:
				{
				_localctx = new ExpressionSingleValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(80);
				((ExpressionSingleValueContext)_localctx).value = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL_TRUE) | (1L << BOOL_FALSE) | (1L << MONEY) | (1L << INTEGER) | (1L << STRING) | (1L << DECIMAL) | (1L << DATE))) != 0)) ) {
					((ExpressionSingleValueContext)_localctx).value = (Token)_errHandler.recoverInline(this);
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
			setState(121);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(119);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionLogicalAndContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionLogicalAndContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(83);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(84);
						((ExpressionLogicalAndContext)_localctx).binaryOperator = match(OP_AND);
						setState(85);
						((ExpressionLogicalAndContext)_localctx).rhs = expression(17);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionLogicalOrContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionLogicalOrContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(86);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(87);
						((ExpressionLogicalOrContext)_localctx).binaryOperator = match(OP_OR);
						setState(88);
						((ExpressionLogicalOrContext)_localctx).rhs = expression(16);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionComparisionGreaterThanContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionComparisionGreaterThanContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(89);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(90);
						((ExpressionComparisionGreaterThanContext)_localctx).binaryOperator = match(OP_GT);
						setState(91);
						((ExpressionComparisionGreaterThanContext)_localctx).rhs = expression(15);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionComparisionGreaterEqualContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionComparisionGreaterEqualContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(92);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(93);
						((ExpressionComparisionGreaterEqualContext)_localctx).binaryOperator = match(OP_GE);
						setState(94);
						((ExpressionComparisionGreaterEqualContext)_localctx).rhs = expression(14);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionComparisionLessThanContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionComparisionLessThanContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(95);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(96);
						((ExpressionComparisionLessThanContext)_localctx).binaryOperator = match(OP_LT);
						setState(97);
						((ExpressionComparisionLessThanContext)_localctx).rhs = expression(13);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionComparisionLessEqualContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionComparisionLessEqualContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(98);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(99);
						((ExpressionComparisionLessEqualContext)_localctx).binaryOperator = match(OP_LE);
						setState(100);
						((ExpressionComparisionLessEqualContext)_localctx).rhs = expression(12);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionComparisionEqualContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionComparisionEqualContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(101);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(102);
						((ExpressionComparisionEqualContext)_localctx).binaryOperator = match(OP_EQ);
						setState(103);
						((ExpressionComparisionEqualContext)_localctx).rhs = expression(11);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionComparisionNotEqualContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionComparisionNotEqualContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(104);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(105);
						((ExpressionComparisionNotEqualContext)_localctx).binaryOperator = match(OP_NEQ);
						setState(106);
						((ExpressionComparisionNotEqualContext)_localctx).rhs = expression(10);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionArithmeticMultiplicationContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionArithmeticMultiplicationContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(107);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(108);
						((ExpressionArithmeticMultiplicationContext)_localctx).binaryOperator = match(OP_MULT);
						setState(109);
						((ExpressionArithmeticMultiplicationContext)_localctx).rhs = expression(9);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionArithmeticDivisionContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionArithmeticDivisionContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(110);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(111);
						((ExpressionArithmeticDivisionContext)_localctx).binaryOperator = match(OP_DIV);
						setState(112);
						((ExpressionArithmeticDivisionContext)_localctx).rhs = expression(8);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionArithmeticAdditionContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionArithmeticAdditionContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(113);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(114);
						((ExpressionArithmeticAdditionContext)_localctx).binaryOperator = match(OP_PLUS);
						setState(115);
						((ExpressionArithmeticAdditionContext)_localctx).rhs = expression(7);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionArithmeticSubtractionContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionArithmeticSubtractionContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(116);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(117);
						((ExpressionArithmeticSubtractionContext)_localctx).binaryOperator = match(OP_MINUS);
						setState(118);
						((ExpressionArithmeticSubtractionContext)_localctx).rhs = expression(6);
						}
						break;
					}
					} 
				}
				setState(123);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 16);
		case 1:
			return precpred(_ctx, 15);
		case 2:
			return precpred(_ctx, 14);
		case 3:
			return precpred(_ctx, 13);
		case 4:
			return precpred(_ctx, 12);
		case 5:
			return precpred(_ctx, 11);
		case 6:
			return precpred(_ctx, 10);
		case 7:
			return precpred(_ctx, 9);
		case 8:
			return precpred(_ctx, 8);
		case 9:
			return precpred(_ctx, 7);
		case 10:
			return precpred(_ctx, 6);
		case 11:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3+\177\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\7\2\25\n"+
		"\2\f\2\16\2\30\13\2\3\2\3\2\3\3\3\3\5\3\36\n\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\5\4&\n\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5.\n\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6"+
		"\66\n\6\f\6\16\69\13\6\3\6\3\6\5\6=\n\6\3\7\3\7\3\7\7\7B\n\7\f\7\16\7"+
		"E\13\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bT\n\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\7\bz\n\b\f\b\16\b}\13\b\3\b\2\3\16\t\2\4\6\b\n\f\16\2\3\6\2\37 ##%"+
		"\'++\2\u0092\2\20\3\2\2\2\4\35\3\2\2\2\6\37\3\2\2\2\b-\3\2\2\2\n/\3\2"+
		"\2\2\f>\3\2\2\2\16S\3\2\2\2\20\21\7\3\2\2\21\22\7$\2\2\22\26\7\27\2\2"+
		"\23\25\5\4\3\2\24\23\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3\2\2\2"+
		"\27\31\3\2\2\2\30\26\3\2\2\2\31\32\7\30\2\2\32\3\3\2\2\2\33\36\5\6\4\2"+
		"\34\36\5\n\6\2\35\33\3\2\2\2\35\34\3\2\2\2\36\5\3\2\2\2\37 \7&\2\2 !\7"+
		"$\2\2!\"\7\4\2\2\"%\5\b\5\2#$\7\n\2\2$&\5\16\b\2%#\3\2\2\2%&\3\2\2\2&"+
		"\7\3\2\2\2\'.\7\31\2\2(.\7\32\2\2).\7\33\2\2*.\7\34\2\2+.\7\35\2\2,.\7"+
		"\36\2\2-\'\3\2\2\2-(\3\2\2\2-)\3\2\2\2-*\3\2\2\2-+\3\2\2\2-,\3\2\2\2."+
		"\t\3\2\2\2/\60\7\25\2\2\60\61\7\5\2\2\61\62\5\16\b\2\62\63\7\6\2\2\63"+
		"\67\7\27\2\2\64\66\5\4\3\2\65\64\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\67"+
		"8\3\2\2\28:\3\2\2\29\67\3\2\2\2:<\7\30\2\2;=\5\f\7\2<;\3\2\2\2<=\3\2\2"+
		"\2=\13\3\2\2\2>?\7\26\2\2?C\7\27\2\2@B\5\4\3\2A@\3\2\2\2BE\3\2\2\2CA\3"+
		"\2\2\2CD\3\2\2\2DF\3\2\2\2EC\3\2\2\2FG\7\30\2\2G\r\3\2\2\2HI\b\b\1\2I"+
		"J\7\5\2\2JK\5\16\b\2KL\7\6\2\2LT\3\2\2\2MN\7\t\2\2NT\5\16\b\6OP\7\16\2"+
		"\2PT\5\16\b\5QT\7$\2\2RT\t\2\2\2SH\3\2\2\2SM\3\2\2\2SO\3\2\2\2SQ\3\2\2"+
		"\2SR\3\2\2\2T{\3\2\2\2UV\f\22\2\2VW\7\7\2\2Wz\5\16\b\23XY\f\21\2\2YZ\7"+
		"\b\2\2Zz\5\16\b\22[\\\f\20\2\2\\]\7\17\2\2]z\5\16\b\21^_\f\17\2\2_`\7"+
		"\20\2\2`z\5\16\b\20ab\f\16\2\2bc\7\21\2\2cz\5\16\b\17de\f\r\2\2ef\7\22"+
		"\2\2fz\5\16\b\16gh\f\f\2\2hi\7\23\2\2iz\5\16\b\rjk\f\13\2\2kl\7\24\2\2"+
		"lz\5\16\b\fmn\f\n\2\2no\7\13\2\2oz\5\16\b\13pq\f\t\2\2qr\7\f\2\2rz\5\16"+
		"\b\nst\f\b\2\2tu\7\r\2\2uz\5\16\b\tvw\f\7\2\2wx\7\16\2\2xz\5\16\b\byU"+
		"\3\2\2\2yX\3\2\2\2y[\3\2\2\2y^\3\2\2\2ya\3\2\2\2yd\3\2\2\2yg\3\2\2\2y"+
		"j\3\2\2\2ym\3\2\2\2yp\3\2\2\2ys\3\2\2\2yv\3\2\2\2z}\3\2\2\2{y\3\2\2\2"+
		"{|\3\2\2\2|\17\3\2\2\2}{\3\2\2\2\f\26\35%-\67<CSy{";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}