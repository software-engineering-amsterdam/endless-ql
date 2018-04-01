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
		RULE_sliderWidget = 12, RULE_dropdownWidget = 13, RULE_dropDownList = 14, 
		RULE_widgetStyle = 15, RULE_style = 16, RULE_type = 17, RULE_defaultdef = 18, 
		RULE_blockdefault = 19, RULE_linedefault = 20, RULE_widgetProperty = 21, 
		RULE_widthproperty = 22, RULE_fontproperty = 23, RULE_fontsizeproperty = 24, 
		RULE_colorproperty = 25, RULE_value = 26;
	public static final String[] ruleNames = {
		"stylesheet", "page", "section", "element", "question", "defaultWidget", 
		"widget", "widgetType", "checkboxWidget", "textWidget", "radioWidget", 
		"spinboxWidget", "sliderWidget", "dropdownWidget", "dropDownList", "widgetStyle", 
		"style", "type", "defaultdef", "blockdefault", "linedefault", "widgetProperty", 
		"widthproperty", "fontproperty", "fontsizeproperty", "colorproperty", 
		"value"
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
			setState(54);
			match(STYLESHEET);
			setState(55);
			match(IDENTIFIER);
			setState(56);
			match(CURLY_BRACE_L);
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PAGE) {
				{
				{
				setState(57);
				page();
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(63);
				match(NEWLINE);
				}
				}
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(69);
			match(CURLY_BRACE_R);
			setState(70);
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
			setState(72);
			match(PAGE);
			setState(73);
			match(IDENTIFIER);
			setState(74);
			match(CURLY_BRACE_L);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SECTION) {
				{
				{
				setState(75);
				section();
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DEFAULT) {
				{
				{
				setState(81);
				defaultWidget();
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(87);
			match(CURLY_BRACE_R);
			setState(91);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(88);
					match(NEWLINE);
					}
					} 
				}
				setState(93);
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
			setState(94);
			match(SECTION);
			setState(95);
			match(STR);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CURLY_BRACE_L) {
				{
				setState(96);
				match(CURLY_BRACE_L);
				}
			}

			setState(102);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(99);
					element();
					}
					} 
				}
				setState(104);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(105);
				match(CURLY_BRACE_R);
				}
				break;
			}
			setState(111);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(108);
					match(NEWLINE);
					}
					} 
				}
				setState(113);
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
			setState(135);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SECTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				section();
				setState(118);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(115);
						match(NEWLINE);
						}
						} 
					}
					setState(120);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				defaultWidget();
				setState(125);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(122);
						match(NEWLINE);
						}
						} 
					}
					setState(127);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				}
				break;
			case QUESTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(128);
				question();
				setState(132);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(129);
						match(NEWLINE);
						}
						} 
					}
					setState(134);
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
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				match(QUESTION);
				setState(138);
				match(IDENTIFIER);
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WIDGET) {
					{
					setState(139);
					widget();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				match(QUESTION);
				setState(143);
				match(IDENTIFIER);
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(144);
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
		public WidgetStyleContext widgetStyle() {
			return getRuleContext(WidgetStyleContext.class,0);
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
			setState(149);
			match(DEFAULT);
			setState(150);
			type();
			setState(153);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WIDGET:
				{
				setState(151);
				widget();
				}
				break;
			case CURLY_BRACE_L:
				{
				setState(152);
				widgetStyle();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(158);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(155);
					match(NEWLINE);
					}
					} 
				}
				setState(160);
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
			setState(161);
			match(WIDGET);
			setState(162);
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
			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CHECKBOX:
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				checkboxWidget();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(165);
				textWidget();
				}
				break;
			case RADIO:
				enterOuterAlt(_localctx, 3);
				{
				setState(166);
				radioWidget();
				}
				break;
			case SPINBOX:
				enterOuterAlt(_localctx, 4);
				{
				setState(167);
				spinboxWidget();
				}
				break;
			case SLIDER:
				enterOuterAlt(_localctx, 5);
				{
				setState(168);
				sliderWidget();
				}
				break;
			case DROPDOWN:
				enterOuterAlt(_localctx, 6);
				{
				setState(169);
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
			setState(172);
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
			setState(174);
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
			setState(176);
			match(RADIO);
			setState(177);
			match(BRACE_L);
			setState(178);
			((RadioWidgetContext)_localctx).yes = match(STR);
			setState(179);
			match(COMMA);
			setState(180);
			((RadioWidgetContext)_localctx).no = match(STR);
			setState(181);
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
			setState(183);
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
			setState(185);
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
			setState(187);
			match(DROPDOWN);
			setState(188);
			dropDownList();
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

	public static class DropDownListContext extends ParserRuleContext {
		public TerminalNode BRACKET_L() { return getToken(QLSParser.BRACKET_L, 0); }
		public TerminalNode BRACKET_R() { return getToken(QLSParser.BRACKET_R, 0); }
		public List<TerminalNode> STR() { return getTokens(QLSParser.STR); }
		public TerminalNode STR(int i) {
			return getToken(QLSParser.STR, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(QLSParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(QLSParser.COMMA, i);
		}
		public DropDownListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropDownList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDropDownList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropDownListContext dropDownList() throws RecognitionException {
		DropDownListContext _localctx = new DropDownListContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_dropDownList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(BRACKET_L);
			{
			setState(195);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(191);
					match(STR);
					setState(192);
					match(COMMA);
					}
					} 
				}
				setState(197);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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

	public static class WidgetStyleContext extends ParserRuleContext {
		public TerminalNode CURLY_BRACE_L() { return getToken(QLSParser.CURLY_BRACE_L, 0); }
		public TerminalNode CURLY_BRACE_R() { return getToken(QLSParser.CURLY_BRACE_R, 0); }
		public List<WidgetPropertyContext> widgetProperty() {
			return getRuleContexts(WidgetPropertyContext.class);
		}
		public WidgetPropertyContext widgetProperty(int i) {
			return getRuleContext(WidgetPropertyContext.class,i);
		}
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public WidgetStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetStyle; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetStyle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetStyleContext widgetStyle() throws RecognitionException {
		WidgetStyleContext _localctx = new WidgetStyleContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_widgetStyle);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(CURLY_BRACE_L);
			setState(205); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(204);
					widgetProperty();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(207); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WIDGET) {
				{
				setState(209);
				widget();
				}
			}

			setState(212);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitStyle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StyleContext style() throws RecognitionException {
		StyleContext _localctx = new StyleContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_style);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(IDENTIFIER);
			setState(215);
			match(COLON);
			setState(216);
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
		enterRule(_localctx, 34, RULE_type);
		try {
			setState(224);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEANTYPE:
				_localctx = new BooltypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				match(BOOLEANTYPE);
				}
				break;
			case STRINGTYPE:
				_localctx = new StringtypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				match(STRINGTYPE);
				}
				break;
			case INTEGERTYPE:
				_localctx = new IntegertypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(220);
				match(INTEGERTYPE);
				}
				break;
			case MONEYTYPE:
				_localctx = new MoneytypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(221);
				match(MONEYTYPE);
				}
				break;
			case DATETYPE:
				_localctx = new DatetypeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(222);
				match(DATETYPE);
				}
				break;
			case DECIMALTYPE:
				_localctx = new DecimaltypeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(223);
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
		enterRule(_localctx, 36, RULE_defaultdef);
		try {
			setState(228);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(226);
				blockdefault();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(227);
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
		enterRule(_localctx, 38, RULE_blockdefault);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(DEFAULT);
			setState(231);
			type();
			setState(232);
			match(CURLY_BRACE_L);
			setState(234); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(233);
				widgetProperty();
				}
				}
				setState(236); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WIDTH) | (1L << FONT) | (1L << FONTSIZE) | (1L << COLOR) | (1L << WIDGET))) != 0) );
			setState(238);
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
		enterRule(_localctx, 40, RULE_linedefault);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(DEFAULT);
			setState(241);
			type();
			setState(242);
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
		enterRule(_localctx, 42, RULE_widgetProperty);
		try {
			setState(249);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WIDTH:
				enterOuterAlt(_localctx, 1);
				{
				setState(244);
				widthproperty();
				}
				break;
			case FONT:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				fontproperty();
				}
				break;
			case FONTSIZE:
				enterOuterAlt(_localctx, 3);
				{
				setState(246);
				fontsizeproperty();
				}
				break;
			case COLOR:
				enterOuterAlt(_localctx, 4);
				{
				setState(247);
				colorproperty();
				}
				break;
			case WIDGET:
				enterOuterAlt(_localctx, 5);
				{
				setState(248);
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
		enterRule(_localctx, 44, RULE_widthproperty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(WIDTH);
			setState(252);
			match(COLON);
			setState(253);
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
		enterRule(_localctx, 46, RULE_fontproperty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(FONT);
			setState(256);
			match(COLON);
			setState(257);
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
		enterRule(_localctx, 48, RULE_fontsizeproperty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			match(FONTSIZE);
			setState(260);
			match(COLON);
			setState(261);
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
		enterRule(_localctx, 50, RULE_colorproperty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			match(COLOR);
			setState(264);
			match(COLON);
			setState(265);
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
		enterRule(_localctx, 52, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3,\u0110\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\7\2=\n\2\f\2\16\2@\13\2"+
		"\3\2\7\2C\n\2\f\2\16\2F\13\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3O\n\3\f\3"+
		"\16\3R\13\3\3\3\7\3U\n\3\f\3\16\3X\13\3\3\3\3\3\7\3\\\n\3\f\3\16\3_\13"+
		"\3\3\4\3\4\3\4\5\4d\n\4\3\4\7\4g\n\4\f\4\16\4j\13\4\3\4\5\4m\n\4\3\4\7"+
		"\4p\n\4\f\4\16\4s\13\4\3\5\3\5\7\5w\n\5\f\5\16\5z\13\5\3\5\3\5\7\5~\n"+
		"\5\f\5\16\5\u0081\13\5\3\5\3\5\7\5\u0085\n\5\f\5\16\5\u0088\13\5\5\5\u008a"+
		"\n\5\3\6\3\6\3\6\5\6\u008f\n\6\3\6\3\6\3\6\5\6\u0094\n\6\5\6\u0096\n\6"+
		"\3\7\3\7\3\7\3\7\5\7\u009c\n\7\3\7\7\7\u009f\n\7\f\7\16\7\u00a2\13\7\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00ad\n\t\3\n\3\n\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\7\20\u00c4\n\20\f\20\16\20\u00c7\13\20\3\20\5\20\u00ca\n\20\3\20\3"+
		"\20\3\21\3\21\6\21\u00d0\n\21\r\21\16\21\u00d1\3\21\5\21\u00d5\n\21\3"+
		"\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00e3"+
		"\n\23\3\24\3\24\5\24\u00e7\n\24\3\25\3\25\3\25\3\25\6\25\u00ed\n\25\r"+
		"\25\16\25\u00ee\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27"+
		"\5\27\u00fc\n\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\2\2\35\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\66\2\3\3\2$%\2\u011b\28\3\2\2\2\4J\3"+
		"\2\2\2\6`\3\2\2\2\b\u0089\3\2\2\2\n\u0095\3\2\2\2\f\u0097\3\2\2\2\16\u00a3"+
		"\3\2\2\2\20\u00ac\3\2\2\2\22\u00ae\3\2\2\2\24\u00b0\3\2\2\2\26\u00b2\3"+
		"\2\2\2\30\u00b9\3\2\2\2\32\u00bb\3\2\2\2\34\u00bd\3\2\2\2\36\u00c0\3\2"+
		"\2\2 \u00cd\3\2\2\2\"\u00d8\3\2\2\2$\u00e2\3\2\2\2&\u00e6\3\2\2\2(\u00e8"+
		"\3\2\2\2*\u00f2\3\2\2\2,\u00fb\3\2\2\2.\u00fd\3\2\2\2\60\u0101\3\2\2\2"+
		"\62\u0105\3\2\2\2\64\u0109\3\2\2\2\66\u010d\3\2\2\289\7\24\2\29:\7#\2"+
		"\2:>\7\32\2\2;=\5\4\3\2<;\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?D\3\2"+
		"\2\2@>\3\2\2\2AC\7(\2\2BA\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2EG\3\2"+
		"\2\2FD\3\2\2\2GH\7\33\2\2HI\7\2\2\3I\3\3\2\2\2JK\7\25\2\2KL\7#\2\2LP\7"+
		"\32\2\2MO\5\6\4\2NM\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QV\3\2\2\2RP"+
		"\3\2\2\2SU\5\f\7\2TS\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WY\3\2\2\2X"+
		"V\3\2\2\2Y]\7\33\2\2Z\\\7(\2\2[Z\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2"+
		"\2^\5\3\2\2\2_]\3\2\2\2`a\7\26\2\2ac\7$\2\2bd\7\32\2\2cb\3\2\2\2cd\3\2"+
		"\2\2dh\3\2\2\2eg\5\b\5\2fe\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2il\3\2"+
		"\2\2jh\3\2\2\2km\7\33\2\2lk\3\2\2\2lm\3\2\2\2mq\3\2\2\2np\7(\2\2on\3\2"+
		"\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2r\7\3\2\2\2sq\3\2\2\2tx\5\6\4\2uw\7"+
		"(\2\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\u008a\3\2\2\2zx\3\2\2\2"+
		"{\177\5\f\7\2|~\7(\2\2}|\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080"+
		"\3\2\2\2\u0080\u008a\3\2\2\2\u0081\177\3\2\2\2\u0082\u0086\5\n\6\2\u0083"+
		"\u0085\7(\2\2\u0084\u0083\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2"+
		"\2\2\u0086\u0087\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0089"+
		"t\3\2\2\2\u0089{\3\2\2\2\u0089\u0082\3\2\2\2\u008a\t\3\2\2\2\u008b\u008c"+
		"\7\31\2\2\u008c\u008e\7#\2\2\u008d\u008f\5\16\b\2\u008e\u008d\3\2\2\2"+
		"\u008e\u008f\3\2\2\2\u008f\u0096\3\2\2\2\u0090\u0091\7\31\2\2\u0091\u0093"+
		"\7#\2\2\u0092\u0094\5\"\22\2\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0096\3\2\2\2\u0095\u008b\3\2\2\2\u0095\u0090\3\2\2\2\u0096\13\3\2\2"+
		"\2\u0097\u0098\7\27\2\2\u0098\u009b\5$\23\2\u0099\u009c\5\16\b\2\u009a"+
		"\u009c\5 \21\2\u009b\u0099\3\2\2\2\u009b\u009a\3\2\2\2\u009c\u00a0\3\2"+
		"\2\2\u009d\u009f\7(\2\2\u009e\u009d\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0"+
		"\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\r\3\2\2\2\u00a2\u00a0\3\2\2\2"+
		"\u00a3\u00a4\7\30\2\2\u00a4\u00a5\5\20\t\2\u00a5\17\3\2\2\2\u00a6\u00ad"+
		"\5\22\n\2\u00a7\u00ad\5\24\13\2\u00a8\u00ad\5\26\f\2\u00a9\u00ad\5\30"+
		"\r\2\u00aa\u00ad\5\32\16\2\u00ab\u00ad\5\34\17\2\u00ac\u00a6\3\2\2\2\u00ac"+
		"\u00a7\3\2\2\2\u00ac\u00a8\3\2\2\2\u00ac\u00a9\3\2\2\2\u00ac\u00aa\3\2"+
		"\2\2\u00ac\u00ab\3\2\2\2\u00ad\21\3\2\2\2\u00ae\u00af\7\f\2\2\u00af\23"+
		"\3\2\2\2\u00b0\u00b1\7\r\2\2\u00b1\25\3\2\2\2\u00b2\u00b3\7\16\2\2\u00b3"+
		"\u00b4\7\34\2\2\u00b4\u00b5\7$\2\2\u00b5\u00b6\7!\2\2\u00b6\u00b7\7$\2"+
		"\2\u00b7\u00b8\7\35\2\2\u00b8\27\3\2\2\2\u00b9\u00ba\7\13\2\2\u00ba\31"+
		"\3\2\2\2\u00bb\u00bc\7\n\2\2\u00bc\33\3\2\2\2\u00bd\u00be\7\17\2\2\u00be"+
		"\u00bf\5\36\20\2\u00bf\35\3\2\2\2\u00c0\u00c5\7\36\2\2\u00c1\u00c2\7$"+
		"\2\2\u00c2\u00c4\7!\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5"+
		"\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c5\3\2"+
		"\2\2\u00c8\u00ca\7$\2\2\u00c9\u00c8\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"\u00cb\3\2\2\2\u00cb\u00cc\7\37\2\2\u00cc\37\3\2\2\2\u00cd\u00cf\7\32"+
		"\2\2\u00ce\u00d0\5,\27\2\u00cf\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\3\2\2\2\u00d3\u00d5\5\16"+
		"\b\2\u00d4\u00d3\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6"+
		"\u00d7\7\33\2\2\u00d7!\3\2\2\2\u00d8\u00d9\7#\2\2\u00d9\u00da\7 \2\2\u00da"+
		"\u00db\5\66\34\2\u00db#\3\2\2\2\u00dc\u00e3\7\4\2\2\u00dd\u00e3\7\5\2"+
		"\2\u00de\u00e3\7\6\2\2\u00df\u00e3\7\7\2\2\u00e0\u00e3\7\b\2\2\u00e1\u00e3"+
		"\7\t\2\2\u00e2\u00dc\3\2\2\2\u00e2\u00dd\3\2\2\2\u00e2\u00de\3\2\2\2\u00e2"+
		"\u00df\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e1\3\2\2\2\u00e3%\3\2\2\2"+
		"\u00e4\u00e7\5(\25\2\u00e5\u00e7\5*\26\2\u00e6\u00e4\3\2\2\2\u00e6\u00e5"+
		"\3\2\2\2\u00e7\'\3\2\2\2\u00e8\u00e9\7\27\2\2\u00e9\u00ea\5$\23\2\u00ea"+
		"\u00ec\7\32\2\2\u00eb\u00ed\5,\27\2\u00ec\u00eb\3\2\2\2\u00ed\u00ee\3"+
		"\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0"+
		"\u00f1\7\33\2\2\u00f1)\3\2\2\2\u00f2\u00f3\7\27\2\2\u00f3\u00f4\5$\23"+
		"\2\u00f4\u00f5\5,\27\2\u00f5+\3\2\2\2\u00f6\u00fc\5.\30\2\u00f7\u00fc"+
		"\5\60\31\2\u00f8\u00fc\5\62\32\2\u00f9\u00fc\5\64\33\2\u00fa\u00fc\5\16"+
		"\b\2\u00fb\u00f6\3\2\2\2\u00fb\u00f7\3\2\2\2\u00fb\u00f8\3\2\2\2\u00fb"+
		"\u00f9\3\2\2\2\u00fb\u00fa\3\2\2\2\u00fc-\3\2\2\2\u00fd\u00fe\7\20\2\2"+
		"\u00fe\u00ff\7 \2\2\u00ff\u0100\7%\2\2\u0100/\3\2\2\2\u0101\u0102\7\21"+
		"\2\2\u0102\u0103\7 \2\2\u0103\u0104\7$\2\2\u0104\61\3\2\2\2\u0105\u0106"+
		"\7\22\2\2\u0106\u0107\7 \2\2\u0107\u0108\7%\2\2\u0108\63\3\2\2\2\u0109"+
		"\u010a\7\23\2\2\u010a\u010b\7 \2\2\u010b\u010c\7)\2\2\u010c\65\3\2\2\2"+
		"\u010d\u010e\t\2\2\2\u010e\67\3\2\2\2\35>DPV]chlqx\177\u0086\u0089\u008e"+
		"\u0093\u0095\u009b\u00a0\u00ac\u00c5\u00c9\u00d1\u00d4\u00e2\u00e6\u00ee"+
		"\u00fb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}