// Generated from src/main/java/org/uva/sea/ql/parser/QL.g by ANTLR 4.7.1

    package org.uva.sea.ql.parser.antlr;
    import org.uva.sea.ql.parser.elements.*;
	import org.uva.sea.ql.parser.elements.expressions.*;
	import org.uva.sea.ql.parser.elements.types.*;
	import org.uva.sea.ql.parser.elements.expressions.*;
    import org.uva.sea.ql.parser.elements.types.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
    import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, TYPES=26, BOOLEAN_TRUE=27, BOOLEAN_FALSE=28, WS=29, COMMENT=30, 
		SINGLE_COMMENT=31, IDENT=32, INT=33, DECIMAL=34, STR=35;
	public static final int
		RULE_form = 0, RULE_statements = 1, RULE_statement = 2, RULE_question = 3, 
		RULE_label = 4, RULE_variable = 5, RULE_type = 6, RULE_condition = 7, 
		RULE_block = 8, RULE_expression = 9, RULE_orExpr = 10, RULE_andExpr = 11, 
		RULE_relExpr = 12, RULE_addExpr = 13, RULE_mulExpr = 14, RULE_unExpr = 15, 
		RULE_primary = 16, RULE_bool = 17, RULE_num = 18, RULE_dec = 19, RULE_str = 20, 
		RULE_money = 21, RULE_date = 22;
	public static final String[] ruleNames = {
		"form", "statements", "statement", "question", "label", "variable", "type", 
		"condition", "block", "expression", "orExpr", "andExpr", "relExpr", "addExpr", 
		"mulExpr", "unExpr", "primary", "bool", "num", "dec", "str", "money", 
		"date"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "':'", "'='", "'if'", "'('", "')'", "'else'", 
		"'||'", "'&&'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'+'", "'-'", 
		"'*'", "'/'", "'!'", "'$'", "'\u20AC'", "'@'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "TYPES", "BOOLEAN_TRUE", "BOOLEAN_FALSE", "WS", "COMMENT", 
		"SINGLE_COMMENT", "IDENT", "INT", "DECIMAL", "STR"
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
	public String getGrammarFileName() { return "QL.g"; }

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
		public Form result;
		public Token f;
		public Token IDENT;
		public StatementsContext stms;
		public TerminalNode IDENT() { return getToken(QLParser.IDENT, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
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
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_form);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			((FormContext)_localctx).f = match(T__0);
			setState(47);
			((FormContext)_localctx).IDENT = match(IDENT);
			setState(48);
			match(T__1);
			setState(49);
			((FormContext)_localctx).stms = statements();
			setState(50);
			match(T__2);

			            ((FormContext)_localctx).result =  new Form(((FormContext)_localctx).f, (((FormContext)_localctx).IDENT!=null?((FormContext)_localctx).IDENT.getText():null), ((FormContext)_localctx).stms.result);
			        
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

	public static class StatementsContext extends ParserRuleContext {
		public Statements result;
		public StatementContext stm;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitStatements(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statements);
		 Statements statements = new Statements(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5 || _la==STR) {
				{
				{
				setState(53);
				((StatementsContext)_localctx).stm = statement();

				        statements.addStatement(((StatementsContext)_localctx).stm.result);
				    
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);
			 ((StatementsContext)_localctx).result =  statements; 
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
		public ASTNode result;
		public QuestionContext quest;
		public ConditionContext cont;
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
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
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STR:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				((StatementContext)_localctx).quest = question();
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).quest.result; 
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				((StatementContext)_localctx).cont = condition();
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).cont.result; 
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
		public Question result;
		public LabelContext lab;
		public VariableContext var;
		public TypeContext t;
		public ExpressionContext ex;
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
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
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			((QuestionContext)_localctx).lab = label();
			setState(70);
			((QuestionContext)_localctx).var = variable();
			setState(71);
			match(T__3);
			setState(72);
			((QuestionContext)_localctx).t = type();
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(73);
				match(T__4);
				setState(74);
				((QuestionContext)_localctx).ex = expression();
				}
			}


			        ((QuestionContext)_localctx).result =  new Question((((QuestionContext)_localctx).lab!=null?(((QuestionContext)_localctx).lab.start):null), ((QuestionContext)_localctx).lab.result, ((QuestionContext)_localctx).var.result, ((QuestionContext)_localctx).t.result,(((QuestionContext)_localctx).ex!=null?_input.getText(((QuestionContext)_localctx).ex.start,((QuestionContext)_localctx).ex.stop):null) == null ? null : ((QuestionContext)_localctx).ex.result);
			    
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
		public String result;
		public Token STR;
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitLabel(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			((LabelContext)_localctx).STR = match(STR);

			        ((LabelContext)_localctx).result =  (((LabelContext)_localctx).STR!=null?((LabelContext)_localctx).STR.getText():null);
			    
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
		public Variable result;
		public Token IDENT;
		public TerminalNode IDENT() { return getToken(QLParser.IDENT, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			((VariableContext)_localctx).IDENT = match(IDENT);

			        ((VariableContext)_localctx).result =  new Variable(((VariableContext)_localctx).IDENT, (((VariableContext)_localctx).IDENT!=null?((VariableContext)_localctx).IDENT.getText():null));
			    
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
		public Type result;
		public Token TYPES;
		public TerminalNode TYPES() { return getToken(QLParser.TYPES, 0); }
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
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			((TypeContext)_localctx).TYPES = match(TYPES);

			        ((TypeContext)_localctx).result =  new Type(((TypeContext)_localctx).TYPES, (((TypeContext)_localctx).TYPES!=null?((TypeContext)_localctx).TYPES.getText():null));
			    
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
		public IfStatement result;
		public Token i;
		public ExpressionContext expr;
		public BlockContext then;
		public Token e;
		public BlockContext elseBlock;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			((ConditionContext)_localctx).i = match(T__5);
			setState(89);
			match(T__6);
			setState(90);
			((ConditionContext)_localctx).expr = expression();
			setState(91);
			match(T__7);
			setState(92);
			((ConditionContext)_localctx).then = block();
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(93);
				((ConditionContext)_localctx).e = match(T__8);
				setState(94);
				((ConditionContext)_localctx).elseBlock = block();
				}
			}


			        Statements elseBlock = (((ConditionContext)_localctx).elseBlock!=null?_input.getText(((ConditionContext)_localctx).elseBlock.start,((ConditionContext)_localctx).elseBlock.stop):null) != null ? ((ConditionContext)_localctx).elseBlock.result : null;
			        ((ConditionContext)_localctx).result =  new IfStatement(((ConditionContext)_localctx).i, ((ConditionContext)_localctx).expr.result, ((ConditionContext)_localctx).then.result, elseBlock);
			    
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
		public Statements result;
		public StatementsContext stms;
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
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
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_block);
		 Statements statements = new Statements(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__1);
			setState(100);
			((BlockContext)_localctx).stms = statements();
			setState(101);
			match(T__2);
			statements = ((BlockContext)_localctx).stms.result; 
			}
			_ctx.stop = _input.LT(-1);
			 ((BlockContext)_localctx).result =  statements; 
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
		public ASTNode result;
		public OrExprContext expr;
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
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
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			((ExpressionContext)_localctx).expr = orExpr();

			        ((ExpressionContext)_localctx).result =  ((ExpressionContext)_localctx).expr.result;
			    
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

	public static class OrExprContext extends ParserRuleContext {
		public ASTNode result;
		public AndExprContext leftHandSide;
		public Token or;
		public AndExprContext rightHandSide;
		public List<AndExprContext> andExpr() {
			return getRuleContexts(AndExprContext.class);
		}
		public AndExprContext andExpr(int i) {
			return getRuleContext(AndExprContext.class,i);
		}
		public OrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitOrExpr(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_orExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			((OrExprContext)_localctx).leftHandSide = andExpr();
			 ((OrExprContext)_localctx).result =  ((OrExprContext)_localctx).leftHandSide.result; 
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(109);
				((OrExprContext)_localctx).or = match(T__9);
				setState(110);
				((OrExprContext)_localctx).rightHandSide = andExpr();

				        ((OrExprContext)_localctx).result =  new Or(((OrExprContext)_localctx).or, _localctx.result, ((OrExprContext)_localctx).rightHandSide.result);
				       
				}
				}
				setState(117);
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

	public static class AndExprContext extends ParserRuleContext {
		public ASTNode result;
		public RelExprContext leftHandSide;
		public Token and;
		public RelExprContext rightHandSide;
		public List<RelExprContext> relExpr() {
			return getRuleContexts(RelExprContext.class);
		}
		public RelExprContext relExpr(int i) {
			return getRuleContext(RelExprContext.class,i);
		}
		public AndExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitAndExpr(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_andExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			((AndExprContext)_localctx).leftHandSide = relExpr();
			 ((AndExprContext)_localctx).result = ((AndExprContext)_localctx).leftHandSide.result; 
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(120);
				((AndExprContext)_localctx).and = match(T__10);
				setState(121);
				((AndExprContext)_localctx).rightHandSide = relExpr();

				        ((AndExprContext)_localctx).result =  new And(((AndExprContext)_localctx).and, _localctx.result, ((AndExprContext)_localctx).rightHandSide.result);
				    
				}
				}
				setState(128);
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

	public static class RelExprContext extends ParserRuleContext {
		public ASTNode result;
		public AddExprContext leftHandSide;
		public Token op;
		public AddExprContext rightHandSide;
		public List<AddExprContext> addExpr() {
			return getRuleContexts(AddExprContext.class);
		}
		public AddExprContext addExpr(int i) {
			return getRuleContext(AddExprContext.class,i);
		}
		public RelExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterRelExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitRelExpr(this);
		}
	}

	public final RelExprContext relExpr() throws RecognitionException {
		RelExprContext _localctx = new RelExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_relExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			((RelExprContext)_localctx).leftHandSide = addExpr();
			 ((RelExprContext)_localctx).result = ((RelExprContext)_localctx).leftHandSide.result; 
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) {
				{
				{
				setState(131);
				((RelExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) ) {
					((RelExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(132);
				((RelExprContext)_localctx).rightHandSide = addExpr();

				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals("<")) {
				        ((RelExprContext)_localctx).result =  new LessThan(((RelExprContext)_localctx).op, _localctx.result, ((RelExprContext)_localctx).rightHandSide.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals("<=")) {
				        ((RelExprContext)_localctx).result =  new LessOrEqual(((RelExprContext)_localctx).op, _localctx.result, ((RelExprContext)_localctx).rightHandSide.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals(">")) {
				        ((RelExprContext)_localctx).result =  new GreaterThan(((RelExprContext)_localctx).op, _localctx.result, ((RelExprContext)_localctx).rightHandSide.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals(">=")) {
				        ((RelExprContext)_localctx).result =  new GreaterOrEqual(((RelExprContext)_localctx).op, _localctx.result, ((RelExprContext)_localctx).rightHandSide.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals("==")) {
				        ((RelExprContext)_localctx).result =  new Equal(((RelExprContext)_localctx).op, _localctx.result, ((RelExprContext)_localctx).rightHandSide.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals("!=")) {
				        ((RelExprContext)_localctx).result =  new NotEqual(((RelExprContext)_localctx).op, _localctx.result, ((RelExprContext)_localctx).rightHandSide.result);
				      }
				    
				}
				}
				setState(139);
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

	public static class AddExprContext extends ParserRuleContext {
		public ASTNode result;
		public MulExprContext leftHandSide;
		public Token op;
		public MulExprContext rightHandSide;
		public List<MulExprContext> mulExpr() {
			return getRuleContexts(MulExprContext.class);
		}
		public MulExprContext mulExpr(int i) {
			return getRuleContext(MulExprContext.class,i);
		}
		public AddExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitAddExpr(this);
		}
	}

	public final AddExprContext addExpr() throws RecognitionException {
		AddExprContext _localctx = new AddExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_addExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			((AddExprContext)_localctx).leftHandSide = mulExpr();
			 ((AddExprContext)_localctx).result = ((AddExprContext)_localctx).leftHandSide.result; 
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__17 || _la==T__18) {
				{
				{
				setState(142);
				((AddExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__17 || _la==T__18) ) {
					((AddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(143);
				((AddExprContext)_localctx).rightHandSide = mulExpr();

				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("+")) {
				        ((AddExprContext)_localctx).result =  new Addition(((AddExprContext)_localctx).op, _localctx.result, ((AddExprContext)_localctx).rightHandSide.result);
				      }
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("-")) {
				        ((AddExprContext)_localctx).result =  new Subtraction(((AddExprContext)_localctx).op, _localctx.result, ((AddExprContext)_localctx).rightHandSide.result);
				      }
				    
				}
				}
				setState(150);
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

	public static class MulExprContext extends ParserRuleContext {
		public ASTNode result;
		public UnExprContext leftHandSide;
		public Token op;
		public UnExprContext rightHandSide;
		public List<UnExprContext> unExpr() {
			return getRuleContexts(UnExprContext.class);
		}
		public UnExprContext unExpr(int i) {
			return getRuleContext(UnExprContext.class,i);
		}
		public MulExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterMulExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitMulExpr(this);
		}
	}

	public final MulExprContext mulExpr() throws RecognitionException {
		MulExprContext _localctx = new MulExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_mulExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			((MulExprContext)_localctx).leftHandSide = unExpr();
			 ((MulExprContext)_localctx).result = ((MulExprContext)_localctx).leftHandSide.result; 
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__19 || _la==T__20) {
				{
				{
				setState(153);
				((MulExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
					((MulExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(154);
				((MulExprContext)_localctx).rightHandSide = unExpr();

				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("*")) {
				        ((MulExprContext)_localctx).result =  new Multiplication(((MulExprContext)_localctx).op, _localctx.result, ((MulExprContext)_localctx).rightHandSide.result);
				      }
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("/")) {
				        ((MulExprContext)_localctx).result =  new Division(((MulExprContext)_localctx).op, _localctx.result, ((MulExprContext)_localctx).rightHandSide.result);
				      }
				    
				}
				}
				setState(161);
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

	public static class UnExprContext extends ParserRuleContext {
		public ASTNode result;
		public Token plus;
		public UnExprContext x;
		public Token minus;
		public Token exl;
		public PrimaryContext p;
		public UnExprContext unExpr() {
			return getRuleContext(UnExprContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public UnExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterUnExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitUnExpr(this);
		}
	}

	public final UnExprContext unExpr() throws RecognitionException {
		UnExprContext _localctx = new UnExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_unExpr);
		try {
			setState(177);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__17:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				((UnExprContext)_localctx).plus = match(T__17);
				setState(163);
				((UnExprContext)_localctx).x = unExpr();

				        ((UnExprContext)_localctx).result =  new Positive(((UnExprContext)_localctx).plus, ((UnExprContext)_localctx).x.result);
				    
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				((UnExprContext)_localctx).minus = match(T__18);
				setState(167);
				((UnExprContext)_localctx).x = unExpr();

				        ((UnExprContext)_localctx).result =  new Negative(((UnExprContext)_localctx).minus, ((UnExprContext)_localctx).x.result);
				    
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 3);
				{
				setState(170);
				((UnExprContext)_localctx).exl = match(T__21);
				setState(171);
				((UnExprContext)_localctx).x = unExpr();

				        ((UnExprContext)_localctx).result =  new Not(((UnExprContext)_localctx).exl, ((UnExprContext)_localctx).x.result);
				    
				}
				break;
			case T__6:
			case T__22:
			case T__23:
			case T__24:
			case BOOLEAN_TRUE:
			case BOOLEAN_FALSE:
			case IDENT:
			case INT:
			case DECIMAL:
			case STR:
				enterOuterAlt(_localctx, 4);
				{
				setState(174);
				((UnExprContext)_localctx).p = primary();
				 ((UnExprContext)_localctx).result =  ((UnExprContext)_localctx).p.result; 
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

	public static class PrimaryContext extends ParserRuleContext {
		public ASTNode result;
		public BoolContext bool;
		public MoneyContext money;
		public VariableContext variable;
		public DateContext date;
		public NumContext num;
		public DecContext dec;
		public StrContext str;
		public ExpressionContext expression;
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public MoneyContext money() {
			return getRuleContext(MoneyContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public DateContext date() {
			return getRuleContext(DateContext.class,0);
		}
		public NumContext num() {
			return getRuleContext(NumContext.class,0);
		}
		public DecContext dec() {
			return getRuleContext(DecContext.class,0);
		}
		public StrContext str() {
			return getRuleContext(StrContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_primary);
		try {
			setState(205);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN_TRUE:
			case BOOLEAN_FALSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				((PrimaryContext)_localctx).bool = bool();
				((PrimaryContext)_localctx).result =  ((PrimaryContext)_localctx).bool.result; 
				}
				break;
			case T__22:
			case T__23:
				enterOuterAlt(_localctx, 2);
				{
				setState(182);
				((PrimaryContext)_localctx).money = money();
				 ((PrimaryContext)_localctx).result =  ((PrimaryContext)_localctx).money.result; 
				}
				break;
			case IDENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(185);
				((PrimaryContext)_localctx).variable = variable();
				 ((PrimaryContext)_localctx).result =  ((PrimaryContext)_localctx).variable.result; 
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 4);
				{
				setState(188);
				((PrimaryContext)_localctx).date = date();
				 ((PrimaryContext)_localctx).result =  ((PrimaryContext)_localctx).date.result; 
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 5);
				{
				setState(191);
				((PrimaryContext)_localctx).num = num();
				((PrimaryContext)_localctx).result =  ((PrimaryContext)_localctx).num.result;
				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(194);
				((PrimaryContext)_localctx).dec = dec();
				((PrimaryContext)_localctx).result =  ((PrimaryContext)_localctx).dec.result; 
				}
				break;
			case STR:
				enterOuterAlt(_localctx, 7);
				{
				setState(197);
				((PrimaryContext)_localctx).str = str();
				((PrimaryContext)_localctx).result =  ((PrimaryContext)_localctx).str.result; 
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 8);
				{
				setState(200);
				match(T__6);
				setState(201);
				((PrimaryContext)_localctx).expression = expression();
				setState(202);
				match(T__7);
				((PrimaryContext)_localctx).result =  ((PrimaryContext)_localctx).expression.result;
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

	public static class BoolContext extends ParserRuleContext {
		public ASTNode result;
		public Token BOOLEAN_TRUE;
		public Token BOOLEAN_FALSE;
		public TerminalNode BOOLEAN_TRUE() { return getToken(QLParser.BOOLEAN_TRUE, 0); }
		public TerminalNode BOOLEAN_FALSE() { return getToken(QLParser.BOOLEAN_FALSE, 0); }
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitBool(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_bool);
		try {
			setState(211);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN_TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(207);
				((BoolContext)_localctx).BOOLEAN_TRUE = match(BOOLEAN_TRUE);

				        ((BoolContext)_localctx).result =  new Bool(((BoolContext)_localctx).BOOLEAN_TRUE, true);
				    
				}
				break;
			case BOOLEAN_FALSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(209);
				((BoolContext)_localctx).BOOLEAN_FALSE = match(BOOLEAN_FALSE);

				        ((BoolContext)_localctx).result =  new Bool(((BoolContext)_localctx).BOOLEAN_FALSE, false);
				    
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

	public static class NumContext extends ParserRuleContext {
		public ASTNode result;
		public Token INT;
		public TerminalNode INT() { return getToken(QLParser.INT, 0); }
		public NumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_num; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitNum(this);
		}
	}

	public final NumContext num() throws RecognitionException {
		NumContext _localctx = new NumContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_num);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			((NumContext)_localctx).INT = match(INT);

			        ((NumContext)_localctx).result =  new Int(((NumContext)_localctx).INT, (((NumContext)_localctx).INT!=null?((NumContext)_localctx).INT.getText():null));
			    
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

	public static class DecContext extends ParserRuleContext {
		public ASTNode result;
		public Token DECIMAL;
		public TerminalNode DECIMAL() { return getToken(QLParser.DECIMAL, 0); }
		public DecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitDec(this);
		}
	}

	public final DecContext dec() throws RecognitionException {
		DecContext _localctx = new DecContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_dec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			((DecContext)_localctx).DECIMAL = match(DECIMAL);

			        ((DecContext)_localctx).result =  new Decimal(((DecContext)_localctx).DECIMAL, (((DecContext)_localctx).DECIMAL!=null?((DecContext)_localctx).DECIMAL.getText():null));
			    
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

	public static class StrContext extends ParserRuleContext {
		public ASTNode result;
		public Token STR;
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public StrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_str; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterStr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitStr(this);
		}
	}

	public final StrContext str() throws RecognitionException {
		StrContext _localctx = new StrContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_str);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			((StrContext)_localctx).STR = match(STR);

			        ((StrContext)_localctx).result =  new Str(((StrContext)_localctx).STR, (((StrContext)_localctx).STR!=null?((StrContext)_localctx).STR.getText():null));
			    
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

	public static class MoneyContext extends ParserRuleContext {
		public ASTNode result;
		public Token c;
		public Token v;
		public TerminalNode DECIMAL() { return getToken(QLParser.DECIMAL, 0); }
		public TerminalNode INT() { return getToken(QLParser.INT, 0); }
		public MoneyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_money; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterMoney(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitMoney(this);
		}
	}

	public final MoneyContext money() throws RecognitionException {
		MoneyContext _localctx = new MoneyContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_money);
		int _la;
		try {
			setState(228);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(222);
				((MoneyContext)_localctx).c = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__22 || _la==T__23) ) {
					((MoneyContext)_localctx).c = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(223);
				((MoneyContext)_localctx).v = match(DECIMAL);

				        ((MoneyContext)_localctx).result =  new Money(((MoneyContext)_localctx).v, (((MoneyContext)_localctx).c!=null?((MoneyContext)_localctx).c.getText():null), (((MoneyContext)_localctx).v!=null?((MoneyContext)_localctx).v.getText():null));
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(225);
				((MoneyContext)_localctx).c = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__22 || _la==T__23) ) {
					((MoneyContext)_localctx).c = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(226);
				((MoneyContext)_localctx).v = match(INT);

				        ((MoneyContext)_localctx).result =  new Money(((MoneyContext)_localctx).v, (((MoneyContext)_localctx).c!=null?((MoneyContext)_localctx).c.getText():null), (((MoneyContext)_localctx).v!=null?((MoneyContext)_localctx).v.getText():null));
				    
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

	public static class DateContext extends ParserRuleContext {
		public DateExpr result;
		public Token day;
		public Token month;
		public Token year;
		public List<TerminalNode> INT() { return getTokens(QLParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(QLParser.INT, i);
		}
		public DateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterDate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitDate(this);
		}
	}

	public final DateContext date() throws RecognitionException {
		DateContext _localctx = new DateContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_date);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(T__24);
			setState(231);
			((DateContext)_localctx).day = match(INT);
			setState(232);
			((DateContext)_localctx).month = match(INT);
			setState(233);
			((DateContext)_localctx).year = match(INT);
			setState(234);
			match(T__24);

			        ((DateContext)_localctx).result =  new DateExpr(((DateContext)_localctx).day, (((DateContext)_localctx).day!=null?((DateContext)_localctx).day.getText():null), (((DateContext)_localctx).month!=null?((DateContext)_localctx).month.getText():null), (((DateContext)_localctx).year!=null?((DateContext)_localctx).year.getText():null));
			    
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3%\u00f0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\7\3;\n\3\f\3\16\3>\13\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\5\4F\n\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5N\n\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tb\n\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\7\ft\n\f\f"+
		"\f\16\fw\13\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\177\n\r\f\r\16\r\u0082\13\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u008a\n\16\f\16\16\16\u008d\13\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u0095\n\17\f\17\16\17\u0098\13\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u00a0\n\20\f\20\16\20\u00a3\13\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\5\21\u00b4\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\5\22\u00d0\n\22\3\23\3\23\3\23\3\23\5\23\u00d6\n\23\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\5"+
		"\27\u00e7\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\2\2\31\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\2\6\3\2\16\23\3\2\24\25\3\2\26"+
		"\27\3\2\31\32\2\u00ed\2\60\3\2\2\2\4<\3\2\2\2\6E\3\2\2\2\bG\3\2\2\2\n"+
		"Q\3\2\2\2\fT\3\2\2\2\16W\3\2\2\2\20Z\3\2\2\2\22e\3\2\2\2\24j\3\2\2\2\26"+
		"m\3\2\2\2\30x\3\2\2\2\32\u0083\3\2\2\2\34\u008e\3\2\2\2\36\u0099\3\2\2"+
		"\2 \u00b3\3\2\2\2\"\u00cf\3\2\2\2$\u00d5\3\2\2\2&\u00d7\3\2\2\2(\u00da"+
		"\3\2\2\2*\u00dd\3\2\2\2,\u00e6\3\2\2\2.\u00e8\3\2\2\2\60\61\7\3\2\2\61"+
		"\62\7\"\2\2\62\63\7\4\2\2\63\64\5\4\3\2\64\65\7\5\2\2\65\66\b\2\1\2\66"+
		"\3\3\2\2\2\678\5\6\4\289\b\3\1\29;\3\2\2\2:\67\3\2\2\2;>\3\2\2\2<:\3\2"+
		"\2\2<=\3\2\2\2=\5\3\2\2\2><\3\2\2\2?@\5\b\5\2@A\b\4\1\2AF\3\2\2\2BC\5"+
		"\20\t\2CD\b\4\1\2DF\3\2\2\2E?\3\2\2\2EB\3\2\2\2F\7\3\2\2\2GH\5\n\6\2H"+
		"I\5\f\7\2IJ\7\6\2\2JM\5\16\b\2KL\7\7\2\2LN\5\24\13\2MK\3\2\2\2MN\3\2\2"+
		"\2NO\3\2\2\2OP\b\5\1\2P\t\3\2\2\2QR\7%\2\2RS\b\6\1\2S\13\3\2\2\2TU\7\""+
		"\2\2UV\b\7\1\2V\r\3\2\2\2WX\7\34\2\2XY\b\b\1\2Y\17\3\2\2\2Z[\7\b\2\2["+
		"\\\7\t\2\2\\]\5\24\13\2]^\7\n\2\2^a\5\22\n\2_`\7\13\2\2`b\5\22\n\2a_\3"+
		"\2\2\2ab\3\2\2\2bc\3\2\2\2cd\b\t\1\2d\21\3\2\2\2ef\7\4\2\2fg\5\4\3\2g"+
		"h\7\5\2\2hi\b\n\1\2i\23\3\2\2\2jk\5\26\f\2kl\b\13\1\2l\25\3\2\2\2mn\5"+
		"\30\r\2nu\b\f\1\2op\7\f\2\2pq\5\30\r\2qr\b\f\1\2rt\3\2\2\2so\3\2\2\2t"+
		"w\3\2\2\2us\3\2\2\2uv\3\2\2\2v\27\3\2\2\2wu\3\2\2\2xy\5\32\16\2y\u0080"+
		"\b\r\1\2z{\7\r\2\2{|\5\32\16\2|}\b\r\1\2}\177\3\2\2\2~z\3\2\2\2\177\u0082"+
		"\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\31\3\2\2\2\u0082\u0080"+
		"\3\2\2\2\u0083\u0084\5\34\17\2\u0084\u008b\b\16\1\2\u0085\u0086\t\2\2"+
		"\2\u0086\u0087\5\34\17\2\u0087\u0088\b\16\1\2\u0088\u008a\3\2\2\2\u0089"+
		"\u0085\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2"+
		"\2\2\u008c\33\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u008f\5\36\20\2\u008f"+
		"\u0096\b\17\1\2\u0090\u0091\t\3\2\2\u0091\u0092\5\36\20\2\u0092\u0093"+
		"\b\17\1\2\u0093\u0095\3\2\2\2\u0094\u0090\3\2\2\2\u0095\u0098\3\2\2\2"+
		"\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\35\3\2\2\2\u0098\u0096"+
		"\3\2\2\2\u0099\u009a\5 \21\2\u009a\u00a1\b\20\1\2\u009b\u009c\t\4\2\2"+
		"\u009c\u009d\5 \21\2\u009d\u009e\b\20\1\2\u009e\u00a0\3\2\2\2\u009f\u009b"+
		"\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2"+
		"\37\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a5\7\24\2\2\u00a5\u00a6\5 \21"+
		"\2\u00a6\u00a7\b\21\1\2\u00a7\u00b4\3\2\2\2\u00a8\u00a9\7\25\2\2\u00a9"+
		"\u00aa\5 \21\2\u00aa\u00ab\b\21\1\2\u00ab\u00b4\3\2\2\2\u00ac\u00ad\7"+
		"\30\2\2\u00ad\u00ae\5 \21\2\u00ae\u00af\b\21\1\2\u00af\u00b4\3\2\2\2\u00b0"+
		"\u00b1\5\"\22\2\u00b1\u00b2\b\21\1\2\u00b2\u00b4\3\2\2\2\u00b3\u00a4\3"+
		"\2\2\2\u00b3\u00a8\3\2\2\2\u00b3\u00ac\3\2\2\2\u00b3\u00b0\3\2\2\2\u00b4"+
		"!\3\2\2\2\u00b5\u00b6\5$\23\2\u00b6\u00b7\b\22\1\2\u00b7\u00d0\3\2\2\2"+
		"\u00b8\u00b9\5,\27\2\u00b9\u00ba\b\22\1\2\u00ba\u00d0\3\2\2\2\u00bb\u00bc"+
		"\5\f\7\2\u00bc\u00bd\b\22\1\2\u00bd\u00d0\3\2\2\2\u00be\u00bf\5.\30\2"+
		"\u00bf\u00c0\b\22\1\2\u00c0\u00d0\3\2\2\2\u00c1\u00c2\5&\24\2\u00c2\u00c3"+
		"\b\22\1\2\u00c3\u00d0\3\2\2\2\u00c4\u00c5\5(\25\2\u00c5\u00c6\b\22\1\2"+
		"\u00c6\u00d0\3\2\2\2\u00c7\u00c8\5*\26\2\u00c8\u00c9\b\22\1\2\u00c9\u00d0"+
		"\3\2\2\2\u00ca\u00cb\7\t\2\2\u00cb\u00cc\5\24\13\2\u00cc\u00cd\7\n\2\2"+
		"\u00cd\u00ce\b\22\1\2\u00ce\u00d0\3\2\2\2\u00cf\u00b5\3\2\2\2\u00cf\u00b8"+
		"\3\2\2\2\u00cf\u00bb\3\2\2\2\u00cf\u00be\3\2\2\2\u00cf\u00c1\3\2\2\2\u00cf"+
		"\u00c4\3\2\2\2\u00cf\u00c7\3\2\2\2\u00cf\u00ca\3\2\2\2\u00d0#\3\2\2\2"+
		"\u00d1\u00d2\7\35\2\2\u00d2\u00d6\b\23\1\2\u00d3\u00d4\7\36\2\2\u00d4"+
		"\u00d6\b\23\1\2\u00d5\u00d1\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6%\3\2\2\2"+
		"\u00d7\u00d8\7#\2\2\u00d8\u00d9\b\24\1\2\u00d9\'\3\2\2\2\u00da\u00db\7"+
		"$\2\2\u00db\u00dc\b\25\1\2\u00dc)\3\2\2\2\u00dd\u00de\7%\2\2\u00de\u00df"+
		"\b\26\1\2\u00df+\3\2\2\2\u00e0\u00e1\t\5\2\2\u00e1\u00e2\7$\2\2\u00e2"+
		"\u00e7\b\27\1\2\u00e3\u00e4\t\5\2\2\u00e4\u00e5\7#\2\2\u00e5\u00e7\b\27"+
		"\1\2\u00e6\u00e0\3\2\2\2\u00e6\u00e3\3\2\2\2\u00e7-\3\2\2\2\u00e8\u00e9"+
		"\7\33\2\2\u00e9\u00ea\7#\2\2\u00ea\u00eb\7#\2\2\u00eb\u00ec\7#\2\2\u00ec"+
		"\u00ed\7\33\2\2\u00ed\u00ee\b\30\1\2\u00ee/\3\2\2\2\17<EMau\u0080\u008b"+
		"\u0096\u00a1\u00b3\u00cf\u00d5\u00e6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}