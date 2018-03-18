// Generated from /home/ajm/Desktop/newEndless/endless-ql/Abel_Elias/src/QLS/parsing/QLS.g4 by ANTLR 4.7
package QLS.parsing.gen;
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
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, BOOLEANTYPE=2, STRINGTYPE=3, INTEGERTYPE=4, MONEYTYPE=5, DATETYPE=6, 
		DECIMALTYPE=7, SLIDER=8, SPINBOX=9, CHECKBOX=10, TEXT=11, RADIO=12, DROPDOWN=13, 
		STYLESHEET=14, PAGE=15, SECTION=16, DEFAULT=17, WIDGET=18, CURLY_BRACE_L=19, 
		CURLY_BRACE_R=20, BRACE_L=21, BRACE_R=22, COMMA=23, COLON=24, BOOL=25, 
		IDENTIFIER=26, STR=27, INT=28, MON=29, DEC=30, NEWLINE=31, WHITESPACE=32, 
		MULTICOMMENT=33, SINGLECOMMENT=34;
	public static final int
		RULE_stylesheet = 0, RULE_page = 1, RULE_block = 2, RULE_lineInBlock = 3, 
		RULE_section = 4, RULE_defaultWidget = 5, RULE_widget = 6, RULE_widgetStyle = 7, 
		RULE_style = 8, RULE_type = 9, RULE_widgetType = 10, RULE_value = 11;
	public static final String[] ruleNames = {
		"stylesheet", "page", "block", "lineInBlock", "section", "defaultWidget", 
		"widget", "widgetStyle", "style", "type", "widgetType", "value"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'boolean'", "'string'", "'integer'", null, "'date'", "'decimal'", 
		"'slider'", "'spinbox'", "'checkbox'", "'text'", "'radio'", "'dropdown'", 
		"'stylesheet'", "'page'", "'section'", "'default'", "'widget'", "'{'", 
		"'}'", "'('", "')'", "','", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE", "MONEYTYPE", "DATETYPE", 
		"DECIMALTYPE", "SLIDER", "SPINBOX", "CHECKBOX", "TEXT", "RADIO", "DROPDOWN", 
		"STYLESHEET", "PAGE", "SECTION", "DEFAULT", "WIDGET", "CURLY_BRACE_L", 
		"CURLY_BRACE_R", "BRACE_L", "BRACE_R", "COMMA", "COLON", "BOOL", "IDENTIFIER", 
		"STR", "INT", "MON", "DEC", "NEWLINE", "WHITESPACE", "MULTICOMMENT", "SINGLECOMMENT"
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
	public static class StylesheetContext extends ParserRuleContext {
		public TerminalNode STYLESHEET() { return getToken(QLSParser.STYLESHEET, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QLSParser.IDENTIFIER, 0); }
		public PageContext page() {
			return getRuleContext(PageContext.class,0);
		}
		public TerminalNode EOF() { return getToken(QLSParser.EOF, 0); }
		public StylesheetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stylesheet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterStylesheet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitStylesheet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitStylesheet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StylesheetContext stylesheet() throws RecognitionException {
		StylesheetContext _localctx = new StylesheetContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stylesheet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(STYLESHEET);
			setState(25);
			match(IDENTIFIER);
			setState(26);
			page();
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

	public static class PageContext extends ParserRuleContext {
		public TerminalNode CURLY_BRACE_L() { return getToken(QLSParser.CURLY_BRACE_L, 0); }
		public TerminalNode PAGE() { return getToken(QLSParser.PAGE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QLSParser.IDENTIFIER, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode CURLY_BRACE_R() { return getToken(QLSParser.CURLY_BRACE_R, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(QLSParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(QLSParser.NEWLINE, i);
		}
		public PageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_page; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterPage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitPage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitPage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PageContext page() throws RecognitionException {
		PageContext _localctx = new PageContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_page);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(CURLY_BRACE_L);
			setState(30);
			match(PAGE);
			setState(31);
			match(IDENTIFIER);
			setState(32);
			block();
			setState(33);
			match(CURLY_BRACE_R);
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(34);
				match(NEWLINE);
				}
				}
				setState(39);
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

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode CURLY_BRACE_L() { return getToken(QLSParser.CURLY_BRACE_L, 0); }
		public TerminalNode CURLY_BRACE_R() { return getToken(QLSParser.CURLY_BRACE_R, 0); }
		public List<LineInBlockContext> lineInBlock() {
			return getRuleContexts(LineInBlockContext.class);
		}
		public LineInBlockContext lineInBlock(int i) {
			return getRuleContext(LineInBlockContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(QLSParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(QLSParser.NEWLINE, i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(CURLY_BRACE_L);
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SECTION || _la==DEFAULT) {
				{
				{
				setState(41);
				lineInBlock();
				}
				}
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(47);
			match(CURLY_BRACE_R);
			setState(51);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(48);
					match(NEWLINE);
					}
					} 
				}
				setState(53);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class LineInBlockContext extends ParserRuleContext {
		public SectionContext section() {
			return getRuleContext(SectionContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(QLSParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(QLSParser.NEWLINE, i);
		}
		public DefaultWidgetContext defaultWidget() {
			return getRuleContext(DefaultWidgetContext.class,0);
		}
		public LineInBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lineInBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterLineInBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitLineInBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitLineInBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineInBlockContext lineInBlock() throws RecognitionException {
		LineInBlockContext _localctx = new LineInBlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_lineInBlock);
		int _la;
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SECTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				section();
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(55);
					match(NEWLINE);
					}
					}
					setState(60);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				defaultWidget();
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(62);
					match(NEWLINE);
					}
					}
					setState(67);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class SectionContext extends ParserRuleContext {
		public TerminalNode SECTION() { return getToken(QLSParser.SECTION, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QLSParser.IDENTIFIER, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_section);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(SECTION);
			setState(71);
			match(IDENTIFIER);
			setState(72);
			block();
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

	public static class DefaultWidgetContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(QLSParser.DEFAULT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public WidgetStyleContext widgetStyle() {
			return getRuleContext(WidgetStyleContext.class,0);
		}
		public DefaultWidgetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultWidget; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterDefaultWidget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitDefaultWidget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDefaultWidget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultWidgetContext defaultWidget() throws RecognitionException {
		DefaultWidgetContext _localctx = new DefaultWidgetContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_defaultWidget);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(DEFAULT);
			setState(75);
			type();
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WIDGET:
				{
				setState(76);
				widget();
				}
				break;
			case CURLY_BRACE_L:
				{
				setState(77);
				widgetStyle();
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

	public static class WidgetContext extends ParserRuleContext {
		public TerminalNode WIDGET() { return getToken(QLSParser.WIDGET, 0); }
		public WidgetTypeContext widgetType() {
			return getRuleContext(WidgetTypeContext.class,0);
		}
		public WidgetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widget; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterWidget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitWidget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetContext widget() throws RecognitionException {
		WidgetContext _localctx = new WidgetContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_widget);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(WIDGET);
			setState(81);
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

	public static class WidgetStyleContext extends ParserRuleContext {
		public TerminalNode CURLY_BRACE_L() { return getToken(QLSParser.CURLY_BRACE_L, 0); }
		public TerminalNode CURLY_BRACE_R() { return getToken(QLSParser.CURLY_BRACE_R, 0); }
		public List<StyleContext> style() {
			return getRuleContexts(StyleContext.class);
		}
		public StyleContext style(int i) {
			return getRuleContext(StyleContext.class,i);
		}
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public WidgetStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetStyle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterWidgetStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitWidgetStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetStyle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetStyleContext widgetStyle() throws RecognitionException {
		WidgetStyleContext _localctx = new WidgetStyleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_widgetStyle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(CURLY_BRACE_L);
			setState(85); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(84);
				style();
				}
				}
				setState(87); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WIDGET) {
				{
				setState(89);
				widget();
				}
			}

			setState(92);
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

	public static class StyleContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QLSParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(QLSParser.COLON, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public StyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_style; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitStyle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StyleContext style() throws RecognitionException {
		StyleContext _localctx = new StyleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_style);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(IDENTIFIER);
			setState(95);
			match(COLON);
			setState(96);
			value();
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
		public TerminalNode STRINGTYPE() { return getToken(QLSParser.STRINGTYPE, 0); }
		public StringtypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterStringtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitStringtype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitStringtype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DatetypeContext extends TypeContext {
		public TerminalNode DATETYPE() { return getToken(QLSParser.DATETYPE, 0); }
		public DatetypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterDatetype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitDatetype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDatetype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegertypeContext extends TypeContext {
		public TerminalNode INTEGERTYPE() { return getToken(QLSParser.INTEGERTYPE, 0); }
		public IntegertypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterIntegertype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitIntegertype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitIntegertype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MoneytypeContext extends TypeContext {
		public TerminalNode MONEYTYPE() { return getToken(QLSParser.MONEYTYPE, 0); }
		public MoneytypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterMoneytype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitMoneytype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitMoneytype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DecimaltypeContext extends TypeContext {
		public TerminalNode DECIMALTYPE() { return getToken(QLSParser.DECIMALTYPE, 0); }
		public DecimaltypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterDecimaltype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitDecimaltype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDecimaltype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooltypeContext extends TypeContext {
		public TerminalNode BOOLEANTYPE() { return getToken(QLSParser.BOOLEANTYPE, 0); }
		public BooltypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterBooltype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitBooltype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitBooltype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_type);
		try {
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEANTYPE:
				_localctx = new BooltypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				match(BOOLEANTYPE);
				}
				break;
			case STRINGTYPE:
				_localctx = new StringtypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				match(STRINGTYPE);
				}
				break;
			case INTEGERTYPE:
				_localctx = new IntegertypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(100);
				match(INTEGERTYPE);
				}
				break;
			case MONEYTYPE:
				_localctx = new MoneytypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(101);
				match(MONEYTYPE);
				}
				break;
			case DATETYPE:
				_localctx = new DatetypeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(102);
				match(DATETYPE);
				}
				break;
			case DECIMALTYPE:
				_localctx = new DecimaltypeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(103);
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

	public static class WidgetTypeContext extends ParserRuleContext {
		public WidgetTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetType; }
	 
		public WidgetTypeContext() { }
		public void copyFrom(WidgetTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SliderwidgetContext extends WidgetTypeContext {
		public TerminalNode SLIDER() { return getToken(QLSParser.SLIDER, 0); }
		public SliderwidgetContext(WidgetTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterSliderwidget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitSliderwidget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSliderwidget(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RadiowidgetContext extends WidgetTypeContext {
		public Token yes;
		public Token no;
		public TerminalNode RADIO() { return getToken(QLSParser.RADIO, 0); }
		public TerminalNode BRACE_L() { return getToken(QLSParser.BRACE_L, 0); }
		public TerminalNode COMMA() { return getToken(QLSParser.COMMA, 0); }
		public TerminalNode BRACE_R() { return getToken(QLSParser.BRACE_R, 0); }
		public List<TerminalNode> STR() { return getTokens(QLSParser.STR); }
		public TerminalNode STR(int i) {
			return getToken(QLSParser.STR, i);
		}
		public RadiowidgetContext(WidgetTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterRadiowidget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitRadiowidget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitRadiowidget(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SpinboxwidgetContext extends WidgetTypeContext {
		public TerminalNode SPINBOX() { return getToken(QLSParser.SPINBOX, 0); }
		public SpinboxwidgetContext(WidgetTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterSpinboxwidget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitSpinboxwidget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSpinboxwidget(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CheckboxwidgetContext extends WidgetTypeContext {
		public TerminalNode CHECKBOX() { return getToken(QLSParser.CHECKBOX, 0); }
		public CheckboxwidgetContext(WidgetTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterCheckboxwidget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitCheckboxwidget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitCheckboxwidget(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropdownwidgetContext extends WidgetTypeContext {
		public Token yes;
		public Token no;
		public TerminalNode DROPDOWN() { return getToken(QLSParser.DROPDOWN, 0); }
		public TerminalNode BRACE_L() { return getToken(QLSParser.BRACE_L, 0); }
		public TerminalNode COMMA() { return getToken(QLSParser.COMMA, 0); }
		public TerminalNode BRACE_R() { return getToken(QLSParser.BRACE_R, 0); }
		public List<TerminalNode> STR() { return getTokens(QLSParser.STR); }
		public TerminalNode STR(int i) {
			return getToken(QLSParser.STR, i);
		}
		public DropdownwidgetContext(WidgetTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterDropdownwidget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitDropdownwidget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDropdownwidget(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextwidgetContext extends WidgetTypeContext {
		public TerminalNode TEXT() { return getToken(QLSParser.TEXT, 0); }
		public TextwidgetContext(WidgetTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterTextwidget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitTextwidget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitTextwidget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetTypeContext widgetType() throws RecognitionException {
		WidgetTypeContext _localctx = new WidgetTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_widgetType);
		try {
			setState(122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SLIDER:
				_localctx = new SliderwidgetContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				match(SLIDER);
				}
				break;
			case SPINBOX:
				_localctx = new SpinboxwidgetContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				match(SPINBOX);
				}
				break;
			case CHECKBOX:
				_localctx = new CheckboxwidgetContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				match(CHECKBOX);
				}
				break;
			case TEXT:
				_localctx = new TextwidgetContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(109);
				match(TEXT);
				}
				break;
			case RADIO:
				_localctx = new RadiowidgetContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(110);
				match(RADIO);
				setState(111);
				match(BRACE_L);
				setState(112);
				((RadiowidgetContext)_localctx).yes = match(STR);
				setState(113);
				match(COMMA);
				setState(114);
				((RadiowidgetContext)_localctx).no = match(STR);
				setState(115);
				match(BRACE_R);
				}
				break;
			case DROPDOWN:
				_localctx = new DropdownwidgetContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(116);
				match(DROPDOWN);
				setState(117);
				match(BRACE_L);
				setState(118);
				((DropdownwidgetContext)_localctx).yes = match(STR);
				setState(119);
				match(COMMA);
				setState(120);
				((DropdownwidgetContext)_localctx).no = match(STR);
				setState(121);
				match(BRACE_R);
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

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode STR() { return getToken(QLSParser.STR, 0); }
		public TerminalNode INT() { return getToken(QLSParser.INT, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			_la = _input.LA(1);
			if ( !(_la==STR || _la==INT) ) {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3$\u0081\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3&\n"+
		"\3\f\3\16\3)\13\3\3\4\3\4\7\4-\n\4\f\4\16\4\60\13\4\3\4\3\4\7\4\64\n\4"+
		"\f\4\16\4\67\13\4\3\5\3\5\7\5;\n\5\f\5\16\5>\13\5\3\5\3\5\7\5B\n\5\f\5"+
		"\16\5E\13\5\5\5G\n\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7Q\n\7\3\b\3\b"+
		"\3\b\3\t\3\t\6\tX\n\t\r\t\16\tY\3\t\5\t]\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\5\13k\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f}\n\f\3\r\3\r\3\r\2\2\16\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\2\3\3\2\35\36\2\u0087\2\32\3\2\2\2\4\37\3\2\2\2"+
		"\6*\3\2\2\2\bF\3\2\2\2\nH\3\2\2\2\fL\3\2\2\2\16R\3\2\2\2\20U\3\2\2\2\22"+
		"`\3\2\2\2\24j\3\2\2\2\26|\3\2\2\2\30~\3\2\2\2\32\33\7\20\2\2\33\34\7\34"+
		"\2\2\34\35\5\4\3\2\35\36\7\2\2\3\36\3\3\2\2\2\37 \7\25\2\2 !\7\21\2\2"+
		"!\"\7\34\2\2\"#\5\6\4\2#\'\7\26\2\2$&\7!\2\2%$\3\2\2\2&)\3\2\2\2\'%\3"+
		"\2\2\2\'(\3\2\2\2(\5\3\2\2\2)\'\3\2\2\2*.\7\25\2\2+-\5\b\5\2,+\3\2\2\2"+
		"-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\61\3\2\2\2\60.\3\2\2\2\61\65\7\26\2"+
		"\2\62\64\7!\2\2\63\62\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2"+
		"\2\66\7\3\2\2\2\67\65\3\2\2\28<\5\n\6\29;\7!\2\2:9\3\2\2\2;>\3\2\2\2<"+
		":\3\2\2\2<=\3\2\2\2=G\3\2\2\2><\3\2\2\2?C\5\f\7\2@B\7!\2\2A@\3\2\2\2B"+
		"E\3\2\2\2CA\3\2\2\2CD\3\2\2\2DG\3\2\2\2EC\3\2\2\2F8\3\2\2\2F?\3\2\2\2"+
		"G\t\3\2\2\2HI\7\22\2\2IJ\7\34\2\2JK\5\6\4\2K\13\3\2\2\2LM\7\23\2\2MP\5"+
		"\24\13\2NQ\5\16\b\2OQ\5\20\t\2PN\3\2\2\2PO\3\2\2\2Q\r\3\2\2\2RS\7\24\2"+
		"\2ST\5\26\f\2T\17\3\2\2\2UW\7\25\2\2VX\5\22\n\2WV\3\2\2\2XY\3\2\2\2YW"+
		"\3\2\2\2YZ\3\2\2\2Z\\\3\2\2\2[]\5\16\b\2\\[\3\2\2\2\\]\3\2\2\2]^\3\2\2"+
		"\2^_\7\26\2\2_\21\3\2\2\2`a\7\34\2\2ab\7\32\2\2bc\5\30\r\2c\23\3\2\2\2"+
		"dk\7\4\2\2ek\7\5\2\2fk\7\6\2\2gk\7\7\2\2hk\7\b\2\2ik\7\t\2\2jd\3\2\2\2"+
		"je\3\2\2\2jf\3\2\2\2jg\3\2\2\2jh\3\2\2\2ji\3\2\2\2k\25\3\2\2\2l}\7\n\2"+
		"\2m}\7\13\2\2n}\7\f\2\2o}\7\r\2\2pq\7\16\2\2qr\7\27\2\2rs\7\35\2\2st\7"+
		"\31\2\2tu\7\35\2\2u}\7\30\2\2vw\7\17\2\2wx\7\27\2\2xy\7\35\2\2yz\7\31"+
		"\2\2z{\7\35\2\2{}\7\30\2\2|l\3\2\2\2|m\3\2\2\2|n\3\2\2\2|o\3\2\2\2|p\3"+
		"\2\2\2|v\3\2\2\2}\27\3\2\2\2~\177\t\2\2\2\177\31\3\2\2\2\r\'.\65<CFPY"+
		"\\j|";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}