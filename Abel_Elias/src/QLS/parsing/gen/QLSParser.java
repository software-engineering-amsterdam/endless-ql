// Generated from C:/dev/uva/endless-ql/Abel_Elias/src/QLS/parsing\QLS.g4 by ANTLR 4.7
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
		WIDTH=14, FONT=15, FONTSIZE=16, COLOR=17, STYLESHEET=18, PAGE=19, SECTION=20, 
		DEFAULT=21, WIDGET=22, QUESTION=23, CURLY_BRACE_L=24, CURLY_BRACE_R=25, 
		BRACE_L=26, BRACE_R=27, BRACKET_L=28, BRACKET_R=29, COLON=30, COMMA=31, 
		BOOL=32, IDENTIFIER=33, STR=34, INT=35, MON=36, DEC=37, NEWLINE=38, CLR=39, 
		WHITESPACE=40, MULTICOMMENT=41, SINGLECOMMENT=42;
	public static final int
		RULE_stylesheet = 0, RULE_page = 1, RULE_section = 2, RULE_element = 3, 
		RULE_question = 4, RULE_defaultWidget = 5, RULE_widget = 6, RULE_widgetType = 7, 
		RULE_checkboxWidget = 8, RULE_textWidget = 9, RULE_radioWidget = 10, RULE_spinboxWidget = 11, 
		RULE_sliderWidget = 12, RULE_dropdownWidget = 13, RULE_style = 14, RULE_type = 15, 
		RULE_defaultdef = 16, RULE_blockdefault = 17, RULE_linedefault = 18, RULE_widgetProperty = 19, 
		RULE_widthproperty = 20, RULE_fontproperty = 21, RULE_fontsizeproperty = 22, 
		RULE_colorproperty = 23, RULE_value = 24;
	public static final String[] ruleNames = {
		"stylesheet", "page", "section", "element", "question", "defaultWidget", 
		"widget", "widgetType", "checkboxWidget", "textWidget", "radioWidget", 
		"spinboxWidget", "sliderWidget", "dropdownWidget", "style", "type", "defaultdef", 
		"blockdefault", "linedefault", "widgetProperty", "widthproperty", "fontproperty", 
		"fontsizeproperty", "colorproperty", "value"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'boolean'", "'string'", "'integer'", null, "'date'", "'decimal'", 
		"'slider'", "'spinbox'", "'checkbox'", "'text'", "'radio'", "'dropdown'", 
		"'width'", "'font'", "'fontsize'", "'color'", "'stylesheet'", "'page'", 
		"'section'", "'default'", "'widget'", "'question'", "'{'", "'}'", "'('", 
		"')'", "'['", "']'", "':'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE", "MONEYTYPE", "DATETYPE", 
		"DECIMALTYPE", "SLIDER", "SPINBOX", "CHECKBOX", "TEXT", "RADIO", "DROPDOWN", 
		"WIDTH", "FONT", "FONTSIZE", "COLOR", "STYLESHEET", "PAGE", "SECTION", 
		"DEFAULT", "WIDGET", "QUESTION", "CURLY_BRACE_L", "CURLY_BRACE_R", "BRACE_L", 
		"BRACE_R", "BRACKET_L", "BRACKET_R", "COLON", "COMMA", "BOOL", "IDENTIFIER", 
		"STR", "INT", "MON", "DEC", "NEWLINE", "CLR", "WHITESPACE", "MULTICOMMENT", 
		"SINGLECOMMENT"
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
		public TerminalNode CURLY_BRACE_L() { return getToken(QLSParser.CURLY_BRACE_L, 0); }
		public TerminalNode CURLY_BRACE_R() { return getToken(QLSParser.CURLY_BRACE_R, 0); }
		public TerminalNode EOF() { return getToken(QLSParser.EOF, 0); }
		public List<PageContext> page() {
			return getRuleContexts(PageContext.class);
		}
		public PageContext page(int i) {
			return getRuleContext(PageContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(QLSParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(QLSParser.NEWLINE, i);
		}
		public StylesheetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stylesheet; }
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
			setState(50);
			match(STYLESHEET);
			setState(51);
			match(IDENTIFIER);
			setState(52);
			match(CURLY_BRACE_L);
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PAGE) {
				{
				{
				setState(53);
				page();
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(59);
				match(NEWLINE);
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(65);
			match(CURLY_BRACE_R);
			setState(66);
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
		public TerminalNode PAGE() { return getToken(QLSParser.PAGE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QLSParser.IDENTIFIER, 0); }
		public TerminalNode CURLY_BRACE_L() { return getToken(QLSParser.CURLY_BRACE_L, 0); }
		public TerminalNode CURLY_BRACE_R() { return getToken(QLSParser.CURLY_BRACE_R, 0); }
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public List<DefaultWidgetContext> defaultWidget() {
			return getRuleContexts(DefaultWidgetContext.class);
		}
		public DefaultWidgetContext defaultWidget(int i) {
			return getRuleContext(DefaultWidgetContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(QLSParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(QLSParser.NEWLINE, i);
		}
		public PageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_page; }
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(PAGE);
			setState(69);
			match(IDENTIFIER);
			setState(70);
			match(CURLY_BRACE_L);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SECTION) {
				{
				{
				setState(71);
				section();
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DEFAULT) {
				{
				{
				setState(77);
				defaultWidget();
				}
				}
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(83);
			match(CURLY_BRACE_R);
			setState(87);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(84);
					match(NEWLINE);
					}
					} 
				}
				setState(89);
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

	public static class SectionContext extends ParserRuleContext {
		public TerminalNode SECTION() { return getToken(QLSParser.SECTION, 0); }
		public TerminalNode STR() { return getToken(QLSParser.STR, 0); }
		public TerminalNode CURLY_BRACE_L() { return getToken(QLSParser.CURLY_BRACE_L, 0); }
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public TerminalNode CURLY_BRACE_R() { return getToken(QLSParser.CURLY_BRACE_R, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(QLSParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(QLSParser.NEWLINE, i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_section);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(SECTION);
			setState(91);
			match(STR);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CURLY_BRACE_L) {
				{
				setState(92);
				match(CURLY_BRACE_L);
				}
			}

			setState(98);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(95);
					element();
					}
					} 
				}
				setState(100);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(102);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(101);
				match(CURLY_BRACE_R);
				}
				break;
			}
			setState(107);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(104);
					match(NEWLINE);
					}
					} 
				}
				setState(109);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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

	public static class ElementContext extends ParserRuleContext {
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
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_element);
		try {
			int _alt;
			setState(131);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SECTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				section();
				setState(114);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(111);
						match(NEWLINE);
						}
						} 
					}
					setState(116);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				defaultWidget();
				setState(121);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(118);
						match(NEWLINE);
						}
						} 
					}
					setState(123);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				}
				break;
			case QUESTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				question();
				setState(128);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(125);
						match(NEWLINE);
						}
						} 
					}
					setState(130);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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

	public static class QuestionContext extends ParserRuleContext {
		public TerminalNode QUESTION() { return getToken(QLSParser.QUESTION, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QLSParser.IDENTIFIER, 0); }
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public StyleContext style() {
			return getRuleContext(StyleContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_question);
		int _la;
		try {
			setState(143);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				match(QUESTION);
				setState(134);
				match(IDENTIFIER);
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WIDGET) {
					{
					setState(135);
					widget();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				match(QUESTION);
				setState(139);
				match(IDENTIFIER);
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CURLY_BRACE_L) {
					{
					setState(140);
					style();
					}
				}

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

	public static class DefaultWidgetContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(QLSParser.DEFAULT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public StyleContext style() {
			return getRuleContext(StyleContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(QLSParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(QLSParser.NEWLINE, i);
		}
		public DefaultWidgetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultWidget; }
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(DEFAULT);
			setState(146);
			type();
			setState(149);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WIDGET:
				{
				setState(147);
				widget();
				}
				break;
			case CURLY_BRACE_L:
				{
				setState(148);
				style();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(154);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(151);
					match(NEWLINE);
					}
					} 
				}
				setState(156);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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
			setState(157);
			match(WIDGET);
			setState(158);
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
		public CheckboxWidgetContext checkboxWidget() {
			return getRuleContext(CheckboxWidgetContext.class,0);
		}
		public TextWidgetContext textWidget() {
			return getRuleContext(TextWidgetContext.class,0);
		}
		public RadioWidgetContext radioWidget() {
			return getRuleContext(RadioWidgetContext.class,0);
		}
		public SpinboxWidgetContext spinboxWidget() {
			return getRuleContext(SpinboxWidgetContext.class,0);
		}
		public SliderWidgetContext sliderWidget() {
			return getRuleContext(SliderWidgetContext.class,0);
		}
		public DropdownWidgetContext dropdownWidget() {
			return getRuleContext(DropdownWidgetContext.class,0);
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
		enterRule(_localctx, 14, RULE_widgetType);
		try {
			setState(166);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CHECKBOX:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				checkboxWidget();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				textWidget();
				}
				break;
			case RADIO:
				enterOuterAlt(_localctx, 3);
				{
				setState(162);
				radioWidget();
				}
				break;
			case SPINBOX:
				enterOuterAlt(_localctx, 4);
				{
				setState(163);
				spinboxWidget();
				}
				break;
			case SLIDER:
				enterOuterAlt(_localctx, 5);
				{
				setState(164);
				sliderWidget();
				}
				break;
			case DROPDOWN:
				enterOuterAlt(_localctx, 6);
				{
				setState(165);
				dropdownWidget();
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

	public static class CheckboxWidgetContext extends ParserRuleContext {
		public TerminalNode CHECKBOX() { return getToken(QLSParser.CHECKBOX, 0); }
		public CheckboxWidgetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkboxWidget; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitCheckboxWidget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CheckboxWidgetContext checkboxWidget() throws RecognitionException {
		CheckboxWidgetContext _localctx = new CheckboxWidgetContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_checkboxWidget);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(CHECKBOX);
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

	public static class TextWidgetContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(QLSParser.TEXT, 0); }
		public TextWidgetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textWidget; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitTextWidget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextWidgetContext textWidget() throws RecognitionException {
		TextWidgetContext _localctx = new TextWidgetContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_textWidget);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(TEXT);
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

	public static class RadioWidgetContext extends ParserRuleContext {
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
		public RadioWidgetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_radioWidget; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitRadioWidget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RadioWidgetContext radioWidget() throws RecognitionException {
		RadioWidgetContext _localctx = new RadioWidgetContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_radioWidget);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(RADIO);
			setState(173);
			match(BRACE_L);
			setState(174);
			((RadioWidgetContext)_localctx).yes = match(STR);
			setState(175);
			match(COMMA);
			setState(176);
			((RadioWidgetContext)_localctx).no = match(STR);
			setState(177);
			match(BRACE_R);
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

	public static class SpinboxWidgetContext extends ParserRuleContext {
		public TerminalNode SPINBOX() { return getToken(QLSParser.SPINBOX, 0); }
		public SpinboxWidgetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spinboxWidget; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSpinboxWidget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpinboxWidgetContext spinboxWidget() throws RecognitionException {
		SpinboxWidgetContext _localctx = new SpinboxWidgetContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_spinboxWidget);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			match(SPINBOX);
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

	public static class SliderWidgetContext extends ParserRuleContext {
		public TerminalNode SLIDER() { return getToken(QLSParser.SLIDER, 0); }
		public SliderWidgetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sliderWidget; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSliderWidget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SliderWidgetContext sliderWidget() throws RecognitionException {
		SliderWidgetContext _localctx = new SliderWidgetContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_sliderWidget);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(SLIDER);
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

	public static class DropdownWidgetContext extends ParserRuleContext {
		public TerminalNode DROPDOWN() { return getToken(QLSParser.DROPDOWN, 0); }
		public DropDownListContext dropDownList() {
			return getRuleContext(DropDownListContext.class,0);
		}
		public DropdownWidgetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropdownWidget; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDropdownWidget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropdownWidgetContext dropdownWidget() throws RecognitionException {
		DropdownWidgetContext _localctx = new DropdownWidgetContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_dropdownWidget);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(DROPDOWN);
			setState(184);
			match(BRACE_L);
			setState(185);
			((DropdownWidgetContext)_localctx).yes = match(STR);
			setState(186);
			match(COMMA);
			setState(187);
			((DropdownWidgetContext)_localctx).no = match(STR);
			setState(188);
			match(BRACE_R);
			}
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STR) {
				{
				setState(198);
				match(STR);
				}
			}

			}
			setState(201);
			match(BRACKET_R);
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
		public TerminalNode CURLY_BRACE_L() { return getToken(QLSParser.CURLY_BRACE_L, 0); }
		public TerminalNode CURLY_BRACE_R() { return getToken(QLSParser.CURLY_BRACE_R, 0); }
		public List<WidgetPropertyContext> widgetProperty() {
			return getRuleContexts(WidgetPropertyContext.class);
		}
		public WidgetPropertyContext widgetProperty(int i) {
			return getRuleContext(WidgetPropertyContext.class,i);
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
		enterRule(_localctx, 28, RULE_style);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(CURLY_BRACE_L);
			setState(192); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(191);
				widgetProperty();
				}
				}
				setState(194); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WIDTH) | (1L << FONT) | (1L << FONTSIZE) | (1L << COLOR) | (1L << WIDGET))) != 0) );
			setState(196);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitStringtype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DatetypeContext extends TypeContext {
		public TerminalNode DATETYPE() { return getToken(QLSParser.DATETYPE, 0); }
		public DatetypeContext(TypeContext ctx) { copyFrom(ctx); }
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitIntegertype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MoneytypeContext extends TypeContext {
		public TerminalNode MONEYTYPE() { return getToken(QLSParser.MONEYTYPE, 0); }
		public MoneytypeContext(TypeContext ctx) { copyFrom(ctx); }
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDecimaltype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooltypeContext extends TypeContext {
		public TerminalNode BOOLEANTYPE() { return getToken(QLSParser.BOOLEANTYPE, 0); }
		public BooltypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitBooltype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_type);
		try {
			setState(204);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEANTYPE:
				_localctx = new BooltypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(198);
				match(BOOLEANTYPE);
				}
				break;
			case STRINGTYPE:
				_localctx = new StringtypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(199);
				match(STRINGTYPE);
				}
				break;
			case INTEGERTYPE:
				_localctx = new IntegertypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(200);
				match(INTEGERTYPE);
				}
				break;
			case MONEYTYPE:
				_localctx = new MoneytypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(201);
				match(MONEYTYPE);
				}
				break;
			case DATETYPE:
				_localctx = new DatetypeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(202);
				match(DATETYPE);
				}
				break;
			case DECIMALTYPE:
				_localctx = new DecimaltypeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(203);
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

	public static class DefaultdefContext extends ParserRuleContext {
		public BlockdefaultContext blockdefault() {
			return getRuleContext(BlockdefaultContext.class,0);
		}
		public LinedefaultContext linedefault() {
			return getRuleContext(LinedefaultContext.class,0);
		}
		public DefaultdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultdef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDefaultdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultdefContext defaultdef() throws RecognitionException {
		DefaultdefContext _localctx = new DefaultdefContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_defaultdef);
		try {
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(206);
				blockdefault();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(207);
				linedefault();
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

	public static class BlockdefaultContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(QLSParser.DEFAULT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode CURLY_BRACE_L() { return getToken(QLSParser.CURLY_BRACE_L, 0); }
		public TerminalNode CURLY_BRACE_R() { return getToken(QLSParser.CURLY_BRACE_R, 0); }
		public List<WidgetPropertyContext> widgetProperty() {
			return getRuleContexts(WidgetPropertyContext.class);
		}
		public WidgetPropertyContext widgetProperty(int i) {
			return getRuleContext(WidgetPropertyContext.class,i);
		}
		public BlockdefaultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockdefault; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitBlockdefault(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockdefaultContext blockdefault() throws RecognitionException {
		BlockdefaultContext _localctx = new BlockdefaultContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_blockdefault);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(DEFAULT);
			setState(211);
			type();
			setState(212);
			match(CURLY_BRACE_L);
			setState(214); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(213);
				widgetProperty();
				}
				}
				setState(216); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WIDTH) | (1L << FONT) | (1L << FONTSIZE) | (1L << COLOR) | (1L << WIDGET))) != 0) );
			setState(218);
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

	public static class LinedefaultContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(QLSParser.DEFAULT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public WidgetPropertyContext widgetProperty() {
			return getRuleContext(WidgetPropertyContext.class,0);
		}
		public LinedefaultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linedefault; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitLinedefault(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinedefaultContext linedefault() throws RecognitionException {
		LinedefaultContext _localctx = new LinedefaultContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_linedefault);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(DEFAULT);
			setState(221);
			type();
			setState(222);
			widgetProperty();
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

	public static class WidgetPropertyContext extends ParserRuleContext {
		public WidthpropertyContext widthproperty() {
			return getRuleContext(WidthpropertyContext.class,0);
		}
		public FontpropertyContext fontproperty() {
			return getRuleContext(FontpropertyContext.class,0);
		}
		public FontsizepropertyContext fontsizeproperty() {
			return getRuleContext(FontsizepropertyContext.class,0);
		}
		public ColorpropertyContext colorproperty() {
			return getRuleContext(ColorpropertyContext.class,0);
		}
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public WidgetPropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetProperty; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetPropertyContext widgetProperty() throws RecognitionException {
		WidgetPropertyContext _localctx = new WidgetPropertyContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_widgetProperty);
		try {
			setState(229);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WIDTH:
				enterOuterAlt(_localctx, 1);
				{
				setState(224);
				widthproperty();
				}
				break;
			case FONT:
				enterOuterAlt(_localctx, 2);
				{
				setState(225);
				fontproperty();
				}
				break;
			case FONTSIZE:
				enterOuterAlt(_localctx, 3);
				{
				setState(226);
				fontsizeproperty();
				}
				break;
			case COLOR:
				enterOuterAlt(_localctx, 4);
				{
				setState(227);
				colorproperty();
				}
				break;
			case WIDGET:
				enterOuterAlt(_localctx, 5);
				{
				setState(228);
				widget();
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

	public static class WidthpropertyContext extends ParserRuleContext {
		public TerminalNode WIDTH() { return getToken(QLSParser.WIDTH, 0); }
		public TerminalNode COLON() { return getToken(QLSParser.COLON, 0); }
		public TerminalNode INT() { return getToken(QLSParser.INT, 0); }
		public WidthpropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widthproperty; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidthproperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidthpropertyContext widthproperty() throws RecognitionException {
		WidthpropertyContext _localctx = new WidthpropertyContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_widthproperty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(WIDTH);
			setState(232);
			match(COLON);
			setState(233);
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

	public static class FontpropertyContext extends ParserRuleContext {
		public TerminalNode FONT() { return getToken(QLSParser.FONT, 0); }
		public TerminalNode COLON() { return getToken(QLSParser.COLON, 0); }
		public TerminalNode STR() { return getToken(QLSParser.STR, 0); }
		public FontpropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fontproperty; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitFontproperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FontpropertyContext fontproperty() throws RecognitionException {
		FontpropertyContext _localctx = new FontpropertyContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_fontproperty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(FONT);
			setState(236);
			match(COLON);
			setState(237);
			match(STR);
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

	public static class FontsizepropertyContext extends ParserRuleContext {
		public TerminalNode FONTSIZE() { return getToken(QLSParser.FONTSIZE, 0); }
		public TerminalNode COLON() { return getToken(QLSParser.COLON, 0); }
		public TerminalNode INT() { return getToken(QLSParser.INT, 0); }
		public FontsizepropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fontsizeproperty; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitFontsizeproperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FontsizepropertyContext fontsizeproperty() throws RecognitionException {
		FontsizepropertyContext _localctx = new FontsizepropertyContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_fontsizeproperty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(FONTSIZE);
			setState(240);
			match(COLON);
			setState(241);
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

	public static class ColorpropertyContext extends ParserRuleContext {
		public TerminalNode COLOR() { return getToken(QLSParser.COLOR, 0); }
		public TerminalNode COLON() { return getToken(QLSParser.COLON, 0); }
		public TerminalNode CLR() { return getToken(QLSParser.CLR, 0); }
		public ColorpropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colorproperty; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitColorproperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColorpropertyContext colorproperty() throws RecognitionException {
		ColorpropertyContext _localctx = new ColorpropertyContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_colorproperty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			match(COLOR);
			setState(244);
			match(COLON);
			setState(245);
			match(CLR);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*\u00fc\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\2\3\2\7\29\n\2\f\2\16\2<\13\2\3\2\7\2?\n\2\f\2\16"+
		"\2B\13\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3K\n\3\f\3\16\3N\13\3\3\3\7\3Q"+
		"\n\3\f\3\16\3T\13\3\3\3\3\3\7\3X\n\3\f\3\16\3[\13\3\3\4\3\4\3\4\5\4`\n"+
		"\4\3\4\7\4c\n\4\f\4\16\4f\13\4\3\4\5\4i\n\4\3\4\7\4l\n\4\f\4\16\4o\13"+
		"\4\3\5\3\5\7\5s\n\5\f\5\16\5v\13\5\3\5\3\5\7\5z\n\5\f\5\16\5}\13\5\3\5"+
		"\3\5\7\5\u0081\n\5\f\5\16\5\u0084\13\5\5\5\u0086\n\5\3\6\3\6\3\6\5\6\u008b"+
		"\n\6\3\6\3\6\3\6\5\6\u0090\n\6\5\6\u0092\n\6\3\7\3\7\3\7\3\7\5\7\u0098"+
		"\n\7\3\7\7\7\u009b\n\7\f\7\16\7\u009e\13\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\5\t\u00a9\n\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\6\20\u00c3"+
		"\n\20\r\20\16\20\u00c4\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00cf"+
		"\n\21\3\22\3\22\5\22\u00d3\n\22\3\23\3\23\3\23\3\23\6\23\u00d9\n\23\r"+
		"\23\16\23\u00da\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25"+
		"\5\25\u00e8\n\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\2\2\33\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\2\3\3\2\"#\2\u0106\2\64\3\2\2\2\4F\3\2"+
		"\2\2\6\\\3\2\2\2\b\u0085\3\2\2\2\n\u0091\3\2\2\2\f\u0093\3\2\2\2\16\u009f"+
		"\3\2\2\2\20\u00a8\3\2\2\2\22\u00aa\3\2\2\2\24\u00ac\3\2\2\2\26\u00ae\3"+
		"\2\2\2\30\u00b5\3\2\2\2\32\u00b7\3\2\2\2\34\u00b9\3\2\2\2\36\u00c0\3\2"+
		"\2\2 \u00ce\3\2\2\2\"\u00d2\3\2\2\2$\u00d4\3\2\2\2&\u00de\3\2\2\2(\u00e7"+
		"\3\2\2\2*\u00e9\3\2\2\2,\u00ed\3\2\2\2.\u00f1\3\2\2\2\60\u00f5\3\2\2\2"+
		"\62\u00f9\3\2\2\2\64\65\7\24\2\2\65\66\7!\2\2\66:\7\32\2\2\679\5\4\3\2"+
		"8\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;@\3\2\2\2<:\3\2\2\2=?\7&\2"+
		"\2>=\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3\2\2\2B@\3\2\2\2CD\7\33"+
		"\2\2DE\7\2\2\3E\3\3\2\2\2FG\7\25\2\2GH\7!\2\2HL\7\32\2\2IK\5\6\4\2JI\3"+
		"\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MR\3\2\2\2NL\3\2\2\2OQ\5\f\7\2PO\3"+
		"\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2SU\3\2\2\2TR\3\2\2\2UY\7\33\2\2VX"+
		"\7&\2\2WV\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\5\3\2\2\2[Y\3\2\2\2\\"+
		"]\7\26\2\2]_\7\"\2\2^`\7\32\2\2_^\3\2\2\2_`\3\2\2\2`d\3\2\2\2ac\5\b\5"+
		"\2ba\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2eh\3\2\2\2fd\3\2\2\2gi\7\33"+
		"\2\2hg\3\2\2\2hi\3\2\2\2im\3\2\2\2jl\7&\2\2kj\3\2\2\2lo\3\2\2\2mk\3\2"+
		"\2\2mn\3\2\2\2n\7\3\2\2\2om\3\2\2\2pt\5\6\4\2qs\7&\2\2rq\3\2\2\2sv\3\2"+
		"\2\2tr\3\2\2\2tu\3\2\2\2u\u0086\3\2\2\2vt\3\2\2\2w{\5\f\7\2xz\7&\2\2y"+
		"x\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|\u0086\3\2\2\2}{\3\2\2\2~\u0082"+
		"\5\n\6\2\177\u0081\7&\2\2\u0080\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082"+
		"\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2"+
		"\2\2\u0085p\3\2\2\2\u0085w\3\2\2\2\u0085~\3\2\2\2\u0086\t\3\2\2\2\u0087"+
		"\u0088\7\31\2\2\u0088\u008a\7!\2\2\u0089\u008b\5\16\b\2\u008a\u0089\3"+
		"\2\2\2\u008a\u008b\3\2\2\2\u008b\u0092\3\2\2\2\u008c\u008d\7\31\2\2\u008d"+
		"\u008f\7!\2\2\u008e\u0090\5\36\20\2\u008f\u008e\3\2\2\2\u008f\u0090\3"+
		"\2\2\2\u0090\u0092\3\2\2\2\u0091\u0087\3\2\2\2\u0091\u008c\3\2\2\2\u0092"+
		"\13\3\2\2\2\u0093\u0094\7\27\2\2\u0094\u0097\5 \21\2\u0095\u0098\5\16"+
		"\b\2\u0096\u0098\5\36\20\2\u0097\u0095\3\2\2\2\u0097\u0096\3\2\2\2\u0098"+
		"\u009c\3\2\2\2\u0099\u009b\7&\2\2\u009a\u0099\3\2\2\2\u009b\u009e\3\2"+
		"\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\r\3\2\2\2\u009e\u009c"+
		"\3\2\2\2\u009f\u00a0\7\30\2\2\u00a0\u00a1\5\20\t\2\u00a1\17\3\2\2\2\u00a2"+
		"\u00a9\5\22\n\2\u00a3\u00a9\5\24\13\2\u00a4\u00a9\5\26\f\2\u00a5\u00a9"+
		"\5\30\r\2\u00a6\u00a9\5\32\16\2\u00a7\u00a9\5\34\17\2\u00a8\u00a2\3\2"+
		"\2\2\u00a8\u00a3\3\2\2\2\u00a8\u00a4\3\2\2\2\u00a8\u00a5\3\2\2\2\u00a8"+
		"\u00a6\3\2\2\2\u00a8\u00a7\3\2\2\2\u00a9\21\3\2\2\2\u00aa\u00ab\7\f\2"+
		"\2\u00ab\23\3\2\2\2\u00ac\u00ad\7\r\2\2\u00ad\25\3\2\2\2\u00ae\u00af\7"+
		"\16\2\2\u00af\u00b0\7\34\2\2\u00b0\u00b1\7\"\2\2\u00b1\u00b2\7\36\2\2"+
		"\u00b2\u00b3\7\"\2\2\u00b3\u00b4\7\35\2\2\u00b4\27\3\2\2\2\u00b5\u00b6"+
		"\7\13\2\2\u00b6\31\3\2\2\2\u00b7\u00b8\7\n\2\2\u00b8\33\3\2\2\2\u00b9"+
		"\u00ba\7\17\2\2\u00ba\u00bb\7\34\2\2\u00bb\u00bc\7\"\2\2\u00bc\u00bd\7"+
		"\36\2\2\u00bd\u00be\7\"\2\2\u00be\u00bf\7\35\2\2\u00bf\35\3\2\2\2\u00c0"+
		"\u00c2\7\32\2\2\u00c1\u00c3\5(\25\2\u00c2\u00c1\3\2\2\2\u00c3\u00c4\3"+
		"\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00c7\7\33\2\2\u00c7\37\3\2\2\2\u00c8\u00cf\7\4\2\2\u00c9\u00cf\7\5\2"+
		"\2\u00ca\u00cf\7\6\2\2\u00cb\u00cf\7\7\2\2\u00cc\u00cf\7\b\2\2\u00cd\u00cf"+
		"\7\t\2\2\u00ce\u00c8\3\2\2\2\u00ce\u00c9\3\2\2\2\u00ce\u00ca\3\2\2\2\u00ce"+
		"\u00cb\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cd\3\2\2\2\u00cf!\3\2\2\2"+
		"\u00d0\u00d3\5$\23\2\u00d1\u00d3\5&\24\2\u00d2\u00d0\3\2\2\2\u00d2\u00d1"+
		"\3\2\2\2\u00d3#\3\2\2\2\u00d4\u00d5\7\27\2\2\u00d5\u00d6\5 \21\2\u00d6"+
		"\u00d8\7\32\2\2\u00d7\u00d9\5(\25\2\u00d8\u00d7\3\2\2\2\u00d9\u00da\3"+
		"\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc"+
		"\u00dd\7\33\2\2\u00dd%\3\2\2\2\u00de\u00df\7\27\2\2\u00df\u00e0\5 \21"+
		"\2\u00e0\u00e1\5(\25\2\u00e1\'\3\2\2\2\u00e2\u00e8\5*\26\2\u00e3\u00e8"+
		"\5,\27\2\u00e4\u00e8\5.\30\2\u00e5\u00e8\5\60\31\2\u00e6\u00e8\5\16\b"+
		"\2\u00e7\u00e2\3\2\2\2\u00e7\u00e3\3\2\2\2\u00e7\u00e4\3\2\2\2\u00e7\u00e5"+
		"\3\2\2\2\u00e7\u00e6\3\2\2\2\u00e8)\3\2\2\2\u00e9\u00ea\7\20\2\2\u00ea"+
		"\u00eb\7\37\2\2\u00eb\u00ec\7#\2\2\u00ec+\3\2\2\2\u00ed\u00ee\7\21\2\2"+
		"\u00ee\u00ef\7\37\2\2\u00ef\u00f0\7\"\2\2\u00f0-\3\2\2\2\u00f1\u00f2\7"+
		"\22\2\2\u00f2\u00f3\7\37\2\2\u00f3\u00f4\7#\2\2\u00f4/\3\2\2\2\u00f5\u00f6"+
		"\7\23\2\2\u00f6\u00f7\7\37\2\2\u00f7\u00f8\7\'\2\2\u00f8\61\3\2\2\2\u00f9"+
		"\u00fa\t\2\2\2\u00fa\63\3\2\2\2\32:@LRY_dhmt{\u0082\u0085\u008a\u008f"+
		"\u0091\u0097\u009c\u00a8\u00c4\u00ce\u00d2\u00da\u00e7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}