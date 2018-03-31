// Generated from /Users/piotrkosytorz/Library/Mobile Documents/com~apple~CloudDocs/Study/SC/endless-ql/piotr/org.uva.sc.piotr.qls/src/main/java/qls/grammar/QLS.g4 by ANTLR 4.7
package qls.grammar;
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, WIDGET_INTEGER_SPINBOX=17, 
		WIDGET_INTEGER_SLIDER=18, WIDGET_TEXT=19, WIDGET_BOOL_CHECKBOX=20, WIDGET_BOOL_RADIO=21, 
		WIDGET_BOOL_DROPDOWN=22, TYPE_BOOLEAN=23, TYPE_STRING=24, TYPE_INTEGER=25, 
		TYPE_DECIMAL=26, TYPE_MONEY=27, TYPE_DATE=28, IDENTIFIER=29, BEGIN=30, 
		END=31, OPEN_PARENTHESIS=32, CLOSE_PARENTHESIS=33, STRING=34, INTEGER=35, 
		HEX_VALUE=36, HEX_DIGIT=37, WS=38, COMMENT=39;
	public static final int
		RULE_stylesheet = 0, RULE_stylesheetElement = 1, RULE_pageDefition = 2, 
		RULE_pageElement = 3, RULE_section = 4, RULE_sectionElement = 5, RULE_questionDefinition = 6, 
		RULE_defaultTypeDefinition = 7, RULE_dataTypeDefinionBlock = 8, RULE_typeDefinitionProperty = 9, 
		RULE_dataType = 10, RULE_widgetDefinition = 11, RULE_widget = 12, RULE_booleanParameters = 13, 
		RULE_integerParameters = 14;
	public static final String[] ruleNames = {
		"stylesheet", "stylesheetElement", "pageDefition", "pageElement", "section", 
		"sectionElement", "questionDefinition", "defaultTypeDefinition", "dataTypeDefinionBlock", 
		"typeDefinitionProperty", "dataType", "widgetDefinition", "widget", "booleanParameters", 
		"integerParameters"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'stylesheet'", "'page'", "'section'", "'question'", "'default'", 
		"'width'", "':'", "'font'", "'fontsize'", "'color'", "'widget'", "','", 
		"'min'", "'='", "'max'", "'step'", "'spinbox'", "'slider'", "'text'", 
		"'checkbox'", "'radio'", "'dropdown'", "'boolean'", "'string'", "'integer'", 
		"'decimal'", "'money'", "'date'", null, "'{'", "'}'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "WIDGET_INTEGER_SPINBOX", "WIDGET_INTEGER_SLIDER", 
		"WIDGET_TEXT", "WIDGET_BOOL_CHECKBOX", "WIDGET_BOOL_RADIO", "WIDGET_BOOL_DROPDOWN", 
		"TYPE_BOOLEAN", "TYPE_STRING", "TYPE_INTEGER", "TYPE_DECIMAL", "TYPE_MONEY", 
		"TYPE_DATE", "IDENTIFIER", "BEGIN", "END", "OPEN_PARENTHESIS", "CLOSE_PARENTHESIS", 
		"STRING", "INTEGER", "HEX_VALUE", "HEX_DIGIT", "WS", "COMMENT"
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
		public Token name;
		public TerminalNode BEGIN() { return getToken(QLSParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(QLSParser.END, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QLSParser.IDENTIFIER, 0); }
		public List<StylesheetElementContext> stylesheetElement() {
			return getRuleContexts(StylesheetElementContext.class);
		}
		public StylesheetElementContext stylesheetElement(int i) {
			return getRuleContext(StylesheetElementContext.class,i);
		}
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(T__0);
			setState(31);
			((StylesheetContext)_localctx).name = match(IDENTIFIER);
			setState(32);
			match(BEGIN);
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1 || _la==T__4) {
				{
				{
				setState(33);
				stylesheetElement();
				}
				}
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(39);
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

	public static class StylesheetElementContext extends ParserRuleContext {
		public PageDefitionContext pageDefition() {
			return getRuleContext(PageDefitionContext.class,0);
		}
		public DefaultTypeDefinitionContext defaultTypeDefinition() {
			return getRuleContext(DefaultTypeDefinitionContext.class,0);
		}
		public StylesheetElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stylesheetElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterStylesheetElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitStylesheetElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitStylesheetElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StylesheetElementContext stylesheetElement() throws RecognitionException {
		StylesheetElementContext _localctx = new StylesheetElementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stylesheetElement);
		try {
			setState(43);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(41);
				pageDefition();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(42);
				defaultTypeDefinition();
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

	public static class PageDefitionContext extends ParserRuleContext {
		public Token title;
		public TerminalNode BEGIN() { return getToken(QLSParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(QLSParser.END, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QLSParser.IDENTIFIER, 0); }
		public List<PageElementContext> pageElement() {
			return getRuleContexts(PageElementContext.class);
		}
		public PageElementContext pageElement(int i) {
			return getRuleContext(PageElementContext.class,i);
		}
		public PageDefitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pageDefition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterPageDefition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitPageDefition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitPageDefition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PageDefitionContext pageDefition() throws RecognitionException {
		PageDefitionContext _localctx = new PageDefitionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pageDefition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(T__1);
			setState(46);
			((PageDefitionContext)_localctx).title = match(IDENTIFIER);
			setState(47);
			match(BEGIN);
			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48);
				pageElement();
				}
				}
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0) );
			setState(53);
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

	public static class PageElementContext extends ParserRuleContext {
		public QuestionDefinitionContext questionDefinition() {
			return getRuleContext(QuestionDefinitionContext.class,0);
		}
		public SectionContext section() {
			return getRuleContext(SectionContext.class,0);
		}
		public DefaultTypeDefinitionContext defaultTypeDefinition() {
			return getRuleContext(DefaultTypeDefinitionContext.class,0);
		}
		public PageElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pageElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterPageElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitPageElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitPageElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PageElementContext pageElement() throws RecognitionException {
		PageElementContext _localctx = new PageElementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_pageElement);
		try {
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				questionDefinition();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				section();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(57);
				defaultTypeDefinition();
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
		public Token title;
		public TerminalNode STRING() { return getToken(QLSParser.STRING, 0); }
		public TerminalNode BEGIN() { return getToken(QLSParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(QLSParser.END, 0); }
		public QuestionDefinitionContext questionDefinition() {
			return getRuleContext(QuestionDefinitionContext.class,0);
		}
		public List<SectionElementContext> sectionElement() {
			return getRuleContexts(SectionElementContext.class);
		}
		public SectionElementContext sectionElement(int i) {
			return getRuleContext(SectionElementContext.class,i);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(T__2);
			setState(61);
			((SectionContext)_localctx).title = match(STRING);
			setState(71);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BEGIN:
				{
				setState(62);
				match(BEGIN);
				setState(64); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(63);
					sectionElement();
					}
					}
					setState(66); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0) );
				setState(68);
				match(END);
				}
				break;
			case T__3:
				{
				setState(70);
				questionDefinition();
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

	public static class SectionElementContext extends ParserRuleContext {
		public QuestionDefinitionContext questionDefinition() {
			return getRuleContext(QuestionDefinitionContext.class,0);
		}
		public SectionContext section() {
			return getRuleContext(SectionContext.class,0);
		}
		public DefaultTypeDefinitionContext defaultTypeDefinition() {
			return getRuleContext(DefaultTypeDefinitionContext.class,0);
		}
		public SectionElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sectionElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterSectionElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitSectionElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSectionElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionElementContext sectionElement() throws RecognitionException {
		SectionElementContext _localctx = new SectionElementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_sectionElement);
		try {
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(73);
				questionDefinition();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				section();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				defaultTypeDefinition();
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

	public static class QuestionDefinitionContext extends ParserRuleContext {
		public Token name;
		public TerminalNode IDENTIFIER() { return getToken(QLSParser.IDENTIFIER, 0); }
		public WidgetDefinitionContext widgetDefinition() {
			return getRuleContext(WidgetDefinitionContext.class,0);
		}
		public QuestionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterQuestionDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitQuestionDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitQuestionDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionDefinitionContext questionDefinition() throws RecognitionException {
		QuestionDefinitionContext _localctx = new QuestionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_questionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(T__3);
			setState(79);
			((QuestionDefinitionContext)_localctx).name = match(IDENTIFIER);
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(80);
				widgetDefinition();
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

	public static class DefaultTypeDefinitionContext extends ParserRuleContext {
		public DataTypeContext type;
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public WidgetDefinitionContext widgetDefinition() {
			return getRuleContext(WidgetDefinitionContext.class,0);
		}
		public DataTypeDefinionBlockContext dataTypeDefinionBlock() {
			return getRuleContext(DataTypeDefinionBlockContext.class,0);
		}
		public DefaultTypeDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultTypeDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterDefaultTypeDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitDefaultTypeDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDefaultTypeDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultTypeDefinitionContext defaultTypeDefinition() throws RecognitionException {
		DefaultTypeDefinitionContext _localctx = new DefaultTypeDefinitionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_defaultTypeDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__4);
			setState(84);
			((DefaultTypeDefinitionContext)_localctx).type = dataType();
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				{
				setState(85);
				widgetDefinition();
				}
				break;
			case BEGIN:
				{
				setState(86);
				dataTypeDefinionBlock();
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

	public static class DataTypeDefinionBlockContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(QLSParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(QLSParser.END, 0); }
		public List<TypeDefinitionPropertyContext> typeDefinitionProperty() {
			return getRuleContexts(TypeDefinitionPropertyContext.class);
		}
		public TypeDefinitionPropertyContext typeDefinitionProperty(int i) {
			return getRuleContext(TypeDefinitionPropertyContext.class,i);
		}
		public DataTypeDefinionBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataTypeDefinionBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterDataTypeDefinionBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitDataTypeDefinionBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDataTypeDefinionBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataTypeDefinionBlockContext dataTypeDefinionBlock() throws RecognitionException {
		DataTypeDefinionBlockContext _localctx = new DataTypeDefinionBlockContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_dataTypeDefinionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(BEGIN);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) {
				{
				{
				setState(90);
				typeDefinitionProperty();
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(96);
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

	public static class TypeDefinitionPropertyContext extends ParserRuleContext {
		public Token width;
		public Token font;
		public Token fontSize;
		public Token color;
		public TerminalNode INTEGER() { return getToken(QLSParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(QLSParser.STRING, 0); }
		public TerminalNode HEX_VALUE() { return getToken(QLSParser.HEX_VALUE, 0); }
		public WidgetDefinitionContext widgetDefinition() {
			return getRuleContext(WidgetDefinitionContext.class,0);
		}
		public TypeDefinitionPropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDefinitionProperty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterTypeDefinitionProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitTypeDefinitionProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitTypeDefinitionProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDefinitionPropertyContext typeDefinitionProperty() throws RecognitionException {
		TypeDefinitionPropertyContext _localctx = new TypeDefinitionPropertyContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_typeDefinitionProperty);
		try {
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				match(T__5);
				setState(99);
				match(T__6);
				setState(100);
				((TypeDefinitionPropertyContext)_localctx).width = match(INTEGER);
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(101);
				match(T__7);
				setState(102);
				match(T__6);
				setState(103);
				((TypeDefinitionPropertyContext)_localctx).font = match(STRING);
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3);
				{
				setState(104);
				match(T__8);
				setState(105);
				match(T__6);
				setState(106);
				((TypeDefinitionPropertyContext)_localctx).fontSize = match(INTEGER);
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 4);
				{
				setState(107);
				match(T__9);
				setState(108);
				match(T__6);
				setState(109);
				((TypeDefinitionPropertyContext)_localctx).color = match(HEX_VALUE);
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 5);
				{
				setState(110);
				widgetDefinition();
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
		public TerminalNode TYPE_INTEGER() { return getToken(QLSParser.TYPE_INTEGER, 0); }
		public TypeDeclarationIntegerContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterTypeDeclarationInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitTypeDeclarationInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitTypeDeclarationInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeDeclarationDecimalContext extends DataTypeContext {
		public TerminalNode TYPE_DECIMAL() { return getToken(QLSParser.TYPE_DECIMAL, 0); }
		public TypeDeclarationDecimalContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterTypeDeclarationDecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitTypeDeclarationDecimal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitTypeDeclarationDecimal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeDeclarationDateContext extends DataTypeContext {
		public TerminalNode TYPE_DATE() { return getToken(QLSParser.TYPE_DATE, 0); }
		public TypeDeclarationDateContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterTypeDeclarationDate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitTypeDeclarationDate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitTypeDeclarationDate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeDeclarationMoneyContext extends DataTypeContext {
		public TerminalNode TYPE_MONEY() { return getToken(QLSParser.TYPE_MONEY, 0); }
		public TypeDeclarationMoneyContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterTypeDeclarationMoney(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitTypeDeclarationMoney(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitTypeDeclarationMoney(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeDeclarationStringContext extends DataTypeContext {
		public TerminalNode TYPE_STRING() { return getToken(QLSParser.TYPE_STRING, 0); }
		public TypeDeclarationStringContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterTypeDeclarationString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitTypeDeclarationString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitTypeDeclarationString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeDeclarationBooleanContext extends DataTypeContext {
		public TerminalNode TYPE_BOOLEAN() { return getToken(QLSParser.TYPE_BOOLEAN, 0); }
		public TypeDeclarationBooleanContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterTypeDeclarationBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitTypeDeclarationBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitTypeDeclarationBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_dataType);
		try {
			setState(119);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE_BOOLEAN:
				_localctx = new TypeDeclarationBooleanContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				match(TYPE_BOOLEAN);
				}
				break;
			case TYPE_STRING:
				_localctx = new TypeDeclarationStringContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				match(TYPE_STRING);
				}
				break;
			case TYPE_INTEGER:
				_localctx = new TypeDeclarationIntegerContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(115);
				match(TYPE_INTEGER);
				}
				break;
			case TYPE_DECIMAL:
				_localctx = new TypeDeclarationDecimalContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(116);
				match(TYPE_DECIMAL);
				}
				break;
			case TYPE_MONEY:
				_localctx = new TypeDeclarationMoneyContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(117);
				match(TYPE_MONEY);
				}
				break;
			case TYPE_DATE:
				_localctx = new TypeDeclarationDateContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(118);
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

	public static class WidgetDefinitionContext extends ParserRuleContext {
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public WidgetDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterWidgetDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitWidgetDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetDefinitionContext widgetDefinition() throws RecognitionException {
		WidgetDefinitionContext _localctx = new WidgetDefinitionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_widgetDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(T__10);
			setState(122);
			widget();
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
		public WidgetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widget; }
	 
		public WidgetContext() { }
		public void copyFrom(WidgetContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class WidgetRadioDefinitionContext extends WidgetContext {
		public TerminalNode WIDGET_BOOL_RADIO() { return getToken(QLSParser.WIDGET_BOOL_RADIO, 0); }
		public BooleanParametersContext booleanParameters() {
			return getRuleContext(BooleanParametersContext.class,0);
		}
		public WidgetRadioDefinitionContext(WidgetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterWidgetRadioDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitWidgetRadioDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetRadioDefinition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WidgetDropdownDefinitionContext extends WidgetContext {
		public TerminalNode WIDGET_BOOL_DROPDOWN() { return getToken(QLSParser.WIDGET_BOOL_DROPDOWN, 0); }
		public BooleanParametersContext booleanParameters() {
			return getRuleContext(BooleanParametersContext.class,0);
		}
		public WidgetDropdownDefinitionContext(WidgetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterWidgetDropdownDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitWidgetDropdownDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetDropdownDefinition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WidgetCheckboxDefinitionContext extends WidgetContext {
		public TerminalNode WIDGET_BOOL_CHECKBOX() { return getToken(QLSParser.WIDGET_BOOL_CHECKBOX, 0); }
		public BooleanParametersContext booleanParameters() {
			return getRuleContext(BooleanParametersContext.class,0);
		}
		public WidgetCheckboxDefinitionContext(WidgetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterWidgetCheckboxDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitWidgetCheckboxDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetCheckboxDefinition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WidgetSpinboxDefinitionContext extends WidgetContext {
		public TerminalNode WIDGET_INTEGER_SPINBOX() { return getToken(QLSParser.WIDGET_INTEGER_SPINBOX, 0); }
		public IntegerParametersContext integerParameters() {
			return getRuleContext(IntegerParametersContext.class,0);
		}
		public WidgetSpinboxDefinitionContext(WidgetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterWidgetSpinboxDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitWidgetSpinboxDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetSpinboxDefinition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WidgetTextDefinitionContext extends WidgetContext {
		public TerminalNode WIDGET_TEXT() { return getToken(QLSParser.WIDGET_TEXT, 0); }
		public WidgetTextDefinitionContext(WidgetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterWidgetTextDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitWidgetTextDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetTextDefinition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WidgetSliderDefinitionContext extends WidgetContext {
		public TerminalNode WIDGET_INTEGER_SLIDER() { return getToken(QLSParser.WIDGET_INTEGER_SLIDER, 0); }
		public IntegerParametersContext integerParameters() {
			return getRuleContext(IntegerParametersContext.class,0);
		}
		public WidgetSliderDefinitionContext(WidgetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterWidgetSliderDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitWidgetSliderDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetSliderDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetContext widget() throws RecognitionException {
		WidgetContext _localctx = new WidgetContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_widget);
		int _la;
		try {
			setState(145);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WIDGET_BOOL_CHECKBOX:
				_localctx = new WidgetCheckboxDefinitionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				match(WIDGET_BOOL_CHECKBOX);
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESIS) {
					{
					setState(125);
					booleanParameters();
					}
				}

				}
				break;
			case WIDGET_BOOL_DROPDOWN:
				_localctx = new WidgetDropdownDefinitionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				match(WIDGET_BOOL_DROPDOWN);
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESIS) {
					{
					setState(129);
					booleanParameters();
					}
				}

				}
				break;
			case WIDGET_BOOL_RADIO:
				_localctx = new WidgetRadioDefinitionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(132);
				match(WIDGET_BOOL_RADIO);
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESIS) {
					{
					setState(133);
					booleanParameters();
					}
				}

				}
				break;
			case WIDGET_INTEGER_SPINBOX:
				_localctx = new WidgetSpinboxDefinitionContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(136);
				match(WIDGET_INTEGER_SPINBOX);
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESIS) {
					{
					setState(137);
					integerParameters();
					}
				}

				}
				break;
			case WIDGET_INTEGER_SLIDER:
				_localctx = new WidgetSliderDefinitionContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(140);
				match(WIDGET_INTEGER_SLIDER);
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESIS) {
					{
					setState(141);
					integerParameters();
					}
				}

				}
				break;
			case WIDGET_TEXT:
				_localctx = new WidgetTextDefinitionContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(144);
				match(WIDGET_TEXT);
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

	public static class BooleanParametersContext extends ParserRuleContext {
		public Token trueValue;
		public Token falseValue;
		public TerminalNode OPEN_PARENTHESIS() { return getToken(QLSParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(QLSParser.CLOSE_PARENTHESIS, 0); }
		public List<TerminalNode> STRING() { return getTokens(QLSParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(QLSParser.STRING, i);
		}
		public BooleanParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterBooleanParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitBooleanParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitBooleanParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanParametersContext booleanParameters() throws RecognitionException {
		BooleanParametersContext _localctx = new BooleanParametersContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_booleanParameters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(OPEN_PARENTHESIS);
			setState(148);
			((BooleanParametersContext)_localctx).trueValue = match(STRING);
			setState(149);
			match(T__11);
			setState(150);
			((BooleanParametersContext)_localctx).falseValue = match(STRING);
			setState(151);
			match(CLOSE_PARENTHESIS);
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

	public static class IntegerParametersContext extends ParserRuleContext {
		public TerminalNode OPEN_PARENTHESIS() { return getToken(QLSParser.OPEN_PARENTHESIS, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(QLSParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(QLSParser.INTEGER, i);
		}
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(QLSParser.CLOSE_PARENTHESIS, 0); }
		public IntegerParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterIntegerParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitIntegerParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitIntegerParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerParametersContext integerParameters() throws RecognitionException {
		IntegerParametersContext _localctx = new IntegerParametersContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_integerParameters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(OPEN_PARENTHESIS);
			setState(154);
			match(T__12);
			setState(155);
			match(T__13);
			setState(156);
			match(INTEGER);
			setState(157);
			match(T__11);
			setState(158);
			match(T__14);
			setState(159);
			match(T__13);
			setState(160);
			match(INTEGER);
			setState(161);
			match(T__11);
			setState(162);
			match(T__15);
			setState(163);
			match(T__13);
			setState(164);
			match(INTEGER);
			setState(165);
			match(CLOSE_PARENTHESIS);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3)\u00aa\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\7\2"+
		"%\n\2\f\2\16\2(\13\2\3\2\3\2\3\3\3\3\5\3.\n\3\3\4\3\4\3\4\3\4\6\4\64\n"+
		"\4\r\4\16\4\65\3\4\3\4\3\5\3\5\3\5\5\5=\n\5\3\6\3\6\3\6\3\6\6\6C\n\6\r"+
		"\6\16\6D\3\6\3\6\3\6\5\6J\n\6\3\7\3\7\3\7\5\7O\n\7\3\b\3\b\3\b\5\bT\n"+
		"\b\3\t\3\t\3\t\3\t\5\tZ\n\t\3\n\3\n\7\n^\n\n\f\n\16\na\13\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13r"+
		"\n\13\3\f\3\f\3\f\3\f\3\f\3\f\5\fz\n\f\3\r\3\r\3\r\3\16\3\16\5\16\u0081"+
		"\n\16\3\16\3\16\5\16\u0085\n\16\3\16\3\16\5\16\u0089\n\16\3\16\3\16\5"+
		"\16\u008d\n\16\3\16\3\16\5\16\u0091\n\16\3\16\5\16\u0094\n\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\2\2\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		"\2\2\2\u00b9\2 \3\2\2\2\4-\3\2\2\2\6/\3\2\2\2\b<\3\2\2\2\n>\3\2\2\2\f"+
		"N\3\2\2\2\16P\3\2\2\2\20U\3\2\2\2\22[\3\2\2\2\24q\3\2\2\2\26y\3\2\2\2"+
		"\30{\3\2\2\2\32\u0093\3\2\2\2\34\u0095\3\2\2\2\36\u009b\3\2\2\2 !\7\3"+
		"\2\2!\"\7\37\2\2\"&\7 \2\2#%\5\4\3\2$#\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'"+
		"\3\2\2\2\')\3\2\2\2(&\3\2\2\2)*\7!\2\2*\3\3\2\2\2+.\5\6\4\2,.\5\20\t\2"+
		"-+\3\2\2\2-,\3\2\2\2.\5\3\2\2\2/\60\7\4\2\2\60\61\7\37\2\2\61\63\7 \2"+
		"\2\62\64\5\b\5\2\63\62\3\2\2\2\64\65\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2"+
		"\2\66\67\3\2\2\2\678\7!\2\28\7\3\2\2\29=\5\16\b\2:=\5\n\6\2;=\5\20\t\2"+
		"<9\3\2\2\2<:\3\2\2\2<;\3\2\2\2=\t\3\2\2\2>?\7\5\2\2?I\7$\2\2@B\7 \2\2"+
		"AC\5\f\7\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2EF\3\2\2\2FG\7!\2\2"+
		"GJ\3\2\2\2HJ\5\16\b\2I@\3\2\2\2IH\3\2\2\2J\13\3\2\2\2KO\5\16\b\2LO\5\n"+
		"\6\2MO\5\20\t\2NK\3\2\2\2NL\3\2\2\2NM\3\2\2\2O\r\3\2\2\2PQ\7\6\2\2QS\7"+
		"\37\2\2RT\5\30\r\2SR\3\2\2\2ST\3\2\2\2T\17\3\2\2\2UV\7\7\2\2VY\5\26\f"+
		"\2WZ\5\30\r\2XZ\5\22\n\2YW\3\2\2\2YX\3\2\2\2Z\21\3\2\2\2[_\7 \2\2\\^\5"+
		"\24\13\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`b\3\2\2\2a_\3\2\2\2"+
		"bc\7!\2\2c\23\3\2\2\2de\7\b\2\2ef\7\t\2\2fr\7%\2\2gh\7\n\2\2hi\7\t\2\2"+
		"ir\7$\2\2jk\7\13\2\2kl\7\t\2\2lr\7%\2\2mn\7\f\2\2no\7\t\2\2or\7&\2\2p"+
		"r\5\30\r\2qd\3\2\2\2qg\3\2\2\2qj\3\2\2\2qm\3\2\2\2qp\3\2\2\2r\25\3\2\2"+
		"\2sz\7\31\2\2tz\7\32\2\2uz\7\33\2\2vz\7\34\2\2wz\7\35\2\2xz\7\36\2\2y"+
		"s\3\2\2\2yt\3\2\2\2yu\3\2\2\2yv\3\2\2\2yw\3\2\2\2yx\3\2\2\2z\27\3\2\2"+
		"\2{|\7\r\2\2|}\5\32\16\2}\31\3\2\2\2~\u0080\7\26\2\2\177\u0081\5\34\17"+
		"\2\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0094\3\2\2\2\u0082\u0084"+
		"\7\30\2\2\u0083\u0085\5\34\17\2\u0084\u0083\3\2\2\2\u0084\u0085\3\2\2"+
		"\2\u0085\u0094\3\2\2\2\u0086\u0088\7\27\2\2\u0087\u0089\5\34\17\2\u0088"+
		"\u0087\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0094\3\2\2\2\u008a\u008c\7\23"+
		"\2\2\u008b\u008d\5\36\20\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\u0094\3\2\2\2\u008e\u0090\7\24\2\2\u008f\u0091\5\36\20\2\u0090\u008f"+
		"\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0094\7\25\2\2"+
		"\u0093~\3\2\2\2\u0093\u0082\3\2\2\2\u0093\u0086\3\2\2\2\u0093\u008a\3"+
		"\2\2\2\u0093\u008e\3\2\2\2\u0093\u0092\3\2\2\2\u0094\33\3\2\2\2\u0095"+
		"\u0096\7\"\2\2\u0096\u0097\7$\2\2\u0097\u0098\7\16\2\2\u0098\u0099\7$"+
		"\2\2\u0099\u009a\7#\2\2\u009a\35\3\2\2\2\u009b\u009c\7\"\2\2\u009c\u009d"+
		"\7\17\2\2\u009d\u009e\7\20\2\2\u009e\u009f\7%\2\2\u009f\u00a0\7\16\2\2"+
		"\u00a0\u00a1\7\21\2\2\u00a1\u00a2\7\20\2\2\u00a2\u00a3\7%\2\2\u00a3\u00a4"+
		"\7\16\2\2\u00a4\u00a5\7\22\2\2\u00a5\u00a6\7\20\2\2\u00a6\u00a7\7%\2\2"+
		"\u00a7\u00a8\7#\2\2\u00a8\37\3\2\2\2\24&-\65<DINSY_qy\u0080\u0084\u0088"+
		"\u008c\u0090\u0093";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}