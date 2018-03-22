// Generated from QLS.g4 by ANTLR 4.7.1

	package org.uva.jomi.qls.parser.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLSParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, TYPE=19, IDENTIFIER=20, LABEL=21, LINE_COMMENT=22, COMMENT=23, 
		WS=24;
	public static final int
		RULE_parse = 0, RULE_stylesheetStmt = 1, RULE_pageStmt = 2, RULE_blockStmt = 3, 
		RULE_command = 4, RULE_sectionStmt = 5, RULE_questionStmt = 6, RULE_defaultWidgetStmt = 7, 
		RULE_widgetStmt = 8, RULE_widgetType = 9, RULE_widgetRadioStmt = 10, RULE_widgetSpinboxStmt = 11, 
		RULE_widgetTextStmt = 12, RULE_widgetYesNoRadiosStmt = 13, RULE_widgetCheckboxStmt = 14, 
		RULE_widgetDropdownStmt = 15, RULE_widgetYesNoDropdownStmt = 16;
	public static final String[] ruleNames = {
		"parse", "stylesheetStmt", "pageStmt", "blockStmt", "command", "sectionStmt", 
		"questionStmt", "defaultWidgetStmt", "widgetStmt", "widgetType", "widgetRadioStmt", 
		"widgetSpinboxStmt", "widgetTextStmt", "widgetYesNoRadiosStmt", "widgetCheckboxStmt", 
		"widgetDropdownStmt", "widgetYesNoDropdownStmt"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'stylesheet'", "'{'", "'}'", "'page'", "'section'", "'question'", 
		"'default'", "'widget'", "'radio'", "'('", "','", "')'", "'spinbox'", 
		"'text'", "'yesno-radios'", "'checkbox'", "'dropdown'", "'yesno-dropdown'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "TYPE", "IDENTIFIER", "LABEL", 
		"LINE_COMMENT", "COMMENT", "WS"
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
	public String getGrammarFileName() { return "QLS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QLSParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(QLSParser.EOF, 0); }
		public List<StylesheetStmtContext> stylesheetStmt() {
			return getRuleContexts(StylesheetStmtContext.class);
		}
		public StylesheetStmtContext stylesheetStmt(int i) {
			return getRuleContext(StylesheetStmtContext.class,i);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(34);
				stylesheetStmt();
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(40);
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

	public static class StylesheetStmtContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QLSParser.IDENTIFIER, 0); }
		public List<PageStmtContext> pageStmt() {
			return getRuleContexts(PageStmtContext.class);
		}
		public PageStmtContext pageStmt(int i) {
			return getRuleContext(PageStmtContext.class,i);
		}
		public StylesheetStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stylesheetStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitStylesheetStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StylesheetStmtContext stylesheetStmt() throws RecognitionException {
		StylesheetStmtContext _localctx = new StylesheetStmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stylesheetStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(T__0);
			setState(43);
			match(IDENTIFIER);
			setState(44);
			match(T__1);
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(45);
				pageStmt();
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51);
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

	public static class PageStmtContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QLSParser.IDENTIFIER, 0); }
		public BlockStmtContext blockStmt() {
			return getRuleContext(BlockStmtContext.class,0);
		}
		public PageStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pageStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitPageStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PageStmtContext pageStmt() throws RecognitionException {
		PageStmtContext _localctx = new PageStmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pageStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(T__3);
			setState(54);
			match(IDENTIFIER);
			setState(55);
			blockStmt();
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

	public static class BlockStmtContext extends ParserRuleContext {
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public BlockStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitBlockStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockStmtContext blockStmt() throws RecognitionException {
		BlockStmtContext _localctx = new BlockStmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_blockStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(T__1);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6))) != 0)) {
				{
				{
				setState(58);
				command();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
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

	public static class CommandContext extends ParserRuleContext {
		public SectionStmtContext sectionStmt() {
			return getRuleContext(SectionStmtContext.class,0);
		}
		public DefaultWidgetStmtContext defaultWidgetStmt() {
			return getRuleContext(DefaultWidgetStmtContext.class,0);
		}
		public QuestionStmtContext questionStmt() {
			return getRuleContext(QuestionStmtContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_command);
		try {
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				sectionStmt();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				defaultWidgetStmt();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				questionStmt();
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

	public static class SectionStmtContext extends ParserRuleContext {
		public TerminalNode LABEL() { return getToken(QLSParser.LABEL, 0); }
		public BlockStmtContext blockStmt() {
			return getRuleContext(BlockStmtContext.class,0);
		}
		public SectionStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sectionStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSectionStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionStmtContext sectionStmt() throws RecognitionException {
		SectionStmtContext _localctx = new SectionStmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_sectionStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(T__4);
			setState(72);
			match(LABEL);
			setState(73);
			blockStmt();
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

	public static class QuestionStmtContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QLSParser.IDENTIFIER, 0); }
		public WidgetStmtContext widgetStmt() {
			return getRuleContext(WidgetStmtContext.class,0);
		}
		public QuestionStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitQuestionStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionStmtContext questionStmt() throws RecognitionException {
		QuestionStmtContext _localctx = new QuestionStmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_questionStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__5);
			setState(76);
			match(IDENTIFIER);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(77);
				widgetStmt();
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

	public static class DefaultWidgetStmtContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(QLSParser.TYPE, 0); }
		public WidgetStmtContext widgetStmt() {
			return getRuleContext(WidgetStmtContext.class,0);
		}
		public DefaultWidgetStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultWidgetStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDefaultWidgetStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultWidgetStmtContext defaultWidgetStmt() throws RecognitionException {
		DefaultWidgetStmtContext _localctx = new DefaultWidgetStmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_defaultWidgetStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__6);
			setState(81);
			match(TYPE);
			setState(82);
			widgetStmt();
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

	public static class WidgetStmtContext extends ParserRuleContext {
		public WidgetTypeContext widgetType() {
			return getRuleContext(WidgetTypeContext.class,0);
		}
		public WidgetStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetStmtContext widgetStmt() throws RecognitionException {
		WidgetStmtContext _localctx = new WidgetStmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_widgetStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(T__7);
			setState(85);
			widgetType();
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

	public static class WidgetTypeContext extends ParserRuleContext {
		public WidgetRadioStmtContext widgetRadioStmt() {
			return getRuleContext(WidgetRadioStmtContext.class,0);
		}
		public WidgetSpinboxStmtContext widgetSpinboxStmt() {
			return getRuleContext(WidgetSpinboxStmtContext.class,0);
		}
		public WidgetTextStmtContext widgetTextStmt() {
			return getRuleContext(WidgetTextStmtContext.class,0);
		}
		public WidgetYesNoRadiosStmtContext widgetYesNoRadiosStmt() {
			return getRuleContext(WidgetYesNoRadiosStmtContext.class,0);
		}
		public WidgetCheckboxStmtContext widgetCheckboxStmt() {
			return getRuleContext(WidgetCheckboxStmtContext.class,0);
		}
		public WidgetDropdownStmtContext widgetDropdownStmt() {
			return getRuleContext(WidgetDropdownStmtContext.class,0);
		}
		public WidgetYesNoDropdownStmtContext widgetYesNoDropdownStmt() {
			return getRuleContext(WidgetYesNoDropdownStmtContext.class,0);
		}
		public WidgetTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetTypeContext widgetType() throws RecognitionException {
		WidgetTypeContext _localctx = new WidgetTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_widgetType);
		try {
			setState(94);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				widgetRadioStmt();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				widgetSpinboxStmt();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 3);
				{
				setState(89);
				widgetTextStmt();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 4);
				{
				setState(90);
				widgetYesNoRadiosStmt();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 5);
				{
				setState(91);
				widgetCheckboxStmt();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 6);
				{
				setState(92);
				widgetDropdownStmt();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 7);
				{
				setState(93);
				widgetYesNoDropdownStmt();
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

	public static class WidgetRadioStmtContext extends ParserRuleContext {
		public List<TerminalNode> LABEL() { return getTokens(QLSParser.LABEL); }
		public TerminalNode LABEL(int i) {
			return getToken(QLSParser.LABEL, i);
		}
		public WidgetRadioStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetRadioStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetRadioStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetRadioStmtContext widgetRadioStmt() throws RecognitionException {
		WidgetRadioStmtContext _localctx = new WidgetRadioStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_widgetRadioStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__8);
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(97);
				match(T__9);
				setState(98);
				match(LABEL);
				setState(99);
				match(T__10);
				setState(100);
				match(LABEL);
				setState(101);
				match(T__11);
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

	public static class WidgetSpinboxStmtContext extends ParserRuleContext {
		public WidgetSpinboxStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetSpinboxStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetSpinboxStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetSpinboxStmtContext widgetSpinboxStmt() throws RecognitionException {
		WidgetSpinboxStmtContext _localctx = new WidgetSpinboxStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_widgetSpinboxStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(T__12);
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

	public static class WidgetTextStmtContext extends ParserRuleContext {
		public WidgetTextStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetTextStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetTextStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetTextStmtContext widgetTextStmt() throws RecognitionException {
		WidgetTextStmtContext _localctx = new WidgetTextStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_widgetTextStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(T__13);
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

	public static class WidgetYesNoRadiosStmtContext extends ParserRuleContext {
		public WidgetYesNoRadiosStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetYesNoRadiosStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetYesNoRadiosStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetYesNoRadiosStmtContext widgetYesNoRadiosStmt() throws RecognitionException {
		WidgetYesNoRadiosStmtContext _localctx = new WidgetYesNoRadiosStmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_widgetYesNoRadiosStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T__14);
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

	public static class WidgetCheckboxStmtContext extends ParserRuleContext {
		public WidgetCheckboxStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetCheckboxStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetCheckboxStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetCheckboxStmtContext widgetCheckboxStmt() throws RecognitionException {
		WidgetCheckboxStmtContext _localctx = new WidgetCheckboxStmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_widgetCheckboxStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(T__15);
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

	public static class WidgetDropdownStmtContext extends ParserRuleContext {
		public List<TerminalNode> LABEL() { return getTokens(QLSParser.LABEL); }
		public TerminalNode LABEL(int i) {
			return getToken(QLSParser.LABEL, i);
		}
		public WidgetDropdownStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetDropdownStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetDropdownStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetDropdownStmtContext widgetDropdownStmt() throws RecognitionException {
		WidgetDropdownStmtContext _localctx = new WidgetDropdownStmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_widgetDropdownStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__16);
			setState(113);
			match(T__9);
			setState(114);
			match(LABEL);
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(115);
				match(T__10);
				setState(116);
				match(LABEL);
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122);
			match(T__11);
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

	public static class WidgetYesNoDropdownStmtContext extends ParserRuleContext {
		public WidgetYesNoDropdownStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetYesNoDropdownStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetYesNoDropdownStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetYesNoDropdownStmtContext widgetYesNoDropdownStmt() throws RecognitionException {
		WidgetYesNoDropdownStmtContext _localctx = new WidgetYesNoDropdownStmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_widgetYesNoDropdownStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(T__17);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32\u0081\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\7\2&\n\2\f\2\16\2)\13\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3\61\n\3\f\3\16"+
		"\3\64\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\7\5>\n\5\f\5\16\5A\13\5\3\5"+
		"\3\5\3\6\3\6\3\6\5\6H\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\5\bQ\n\b\3\t\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13a\n\13\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\5\fi\n\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\21\7\21x\n\21\f\21\16\21{\13\21\3\21\3\21\3\22\3"+
		"\22\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\2\2}\2\'\3"+
		"\2\2\2\4,\3\2\2\2\6\67\3\2\2\2\b;\3\2\2\2\nG\3\2\2\2\fI\3\2\2\2\16M\3"+
		"\2\2\2\20R\3\2\2\2\22V\3\2\2\2\24`\3\2\2\2\26b\3\2\2\2\30j\3\2\2\2\32"+
		"l\3\2\2\2\34n\3\2\2\2\36p\3\2\2\2 r\3\2\2\2\"~\3\2\2\2$&\5\4\3\2%$\3\2"+
		"\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(*\3\2\2\2)\'\3\2\2\2*+\7\2\2\3+\3"+
		"\3\2\2\2,-\7\3\2\2-.\7\26\2\2.\62\7\4\2\2/\61\5\6\4\2\60/\3\2\2\2\61\64"+
		"\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\65\3\2\2\2\64\62\3\2\2\2\65\66"+
		"\7\5\2\2\66\5\3\2\2\2\678\7\6\2\289\7\26\2\29:\5\b\5\2:\7\3\2\2\2;?\7"+
		"\4\2\2<>\5\n\6\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@B\3\2\2\2A?\3"+
		"\2\2\2BC\7\5\2\2C\t\3\2\2\2DH\5\f\7\2EH\5\20\t\2FH\5\16\b\2GD\3\2\2\2"+
		"GE\3\2\2\2GF\3\2\2\2H\13\3\2\2\2IJ\7\7\2\2JK\7\27\2\2KL\5\b\5\2L\r\3\2"+
		"\2\2MN\7\b\2\2NP\7\26\2\2OQ\5\22\n\2PO\3\2\2\2PQ\3\2\2\2Q\17\3\2\2\2R"+
		"S\7\t\2\2ST\7\25\2\2TU\5\22\n\2U\21\3\2\2\2VW\7\n\2\2WX\5\24\13\2X\23"+
		"\3\2\2\2Ya\5\26\f\2Za\5\30\r\2[a\5\32\16\2\\a\5\34\17\2]a\5\36\20\2^a"+
		"\5 \21\2_a\5\"\22\2`Y\3\2\2\2`Z\3\2\2\2`[\3\2\2\2`\\\3\2\2\2`]\3\2\2\2"+
		"`^\3\2\2\2`_\3\2\2\2a\25\3\2\2\2bh\7\13\2\2cd\7\f\2\2de\7\27\2\2ef\7\r"+
		"\2\2fg\7\27\2\2gi\7\16\2\2hc\3\2\2\2hi\3\2\2\2i\27\3\2\2\2jk\7\17\2\2"+
		"k\31\3\2\2\2lm\7\20\2\2m\33\3\2\2\2no\7\21\2\2o\35\3\2\2\2pq\7\22\2\2"+
		"q\37\3\2\2\2rs\7\23\2\2st\7\f\2\2ty\7\27\2\2uv\7\r\2\2vx\7\27\2\2wu\3"+
		"\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{y\3\2\2\2|}\7\16\2\2}!"+
		"\3\2\2\2~\177\7\24\2\2\177#\3\2\2\2\n\'\62?GP`hy";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}