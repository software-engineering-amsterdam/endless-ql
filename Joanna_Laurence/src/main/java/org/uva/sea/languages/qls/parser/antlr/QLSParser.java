// Generated from src/main/java/org/uva/sea/languages/qls/parser/antlr/QLS.g by ANTLR 4.7.1

    package org.uva.sea.languages.qls.parser.antlr;

    import org.uva.sea.languages.qls.parser.elements.Page;
    import org.uva.sea.languages.qls.parser.elements.Parameter;
    import org.uva.sea.languages.qls.parser.elements.Stylesheet;

    import org.uva.sea.languages.qls.parser.elements.style.Color;
    import org.uva.sea.languages.qls.parser.elements.style.Font;
    import org.uva.sea.languages.qls.parser.elements.style.FontSize;
    import org.uva.sea.languages.qls.parser.elements.style.Widget;
    import org.uva.sea.languages.qls.parser.elements.style.Width;
   	import org.uva.sea.languages.qls.parser.elements.style.StyleSpecification;

    import org.uva.sea.languages.qls.parser.elements.specification.DefaultStyle;
    import org.uva.sea.languages.qls.parser.elements.specification.Question;
    import org.uva.sea.languages.qls.parser.elements.specification.Section;
   	import org.uva.sea.languages.qls.parser.elements.specification.Specification;


	import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
    import org.antlr.v4.runtime.tree.*;
import java.util.List;
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
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, IDENT=23, NUM=24, COLOR_CODE=25, 
		STR=26, WS=27, COMMENT=28, SINGLE_COMMENT=29;
	public static final int
		RULE_stylesheet = 0, RULE_pages = 1, RULE_page = 2, RULE_specifications = 3, 
		RULE_specification = 4, RULE_section = 5, RULE_question = 6, RULE_widget = 7, 
		RULE_parameters = 8, RULE_parameter = 9, RULE_defaultStyle = 10, RULE_styleSpecifications = 11, 
		RULE_styleSpecification = 12;
	public static final String[] ruleNames = {
		"stylesheet", "pages", "page", "specifications", "specification", "section", 
		"question", "widget", "parameters", "parameter", "defaultStyle", "styleSpecifications", 
		"styleSpecification"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'stylesheet'", "'{'", "'}'", "'page'", "'section'", "'question'", 
		"'widget'", "'checkbox'", "'choicebox'", "'radio'", "'slider'", "'spinbox'", 
		"'textfield'", "'('", "')'", "','", "'default'", "'width'", "':'", "'font'", 
		"'fontsize'", "'color'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "IDENT", 
		"NUM", "COLOR_CODE", "STR", "WS", "COMMENT", "SINGLE_COMMENT"
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
	public String getGrammarFileName() { return "QLS.g"; }

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
		public Stylesheet result;
		public Token f;
		public Token IDENT;
		public PagesContext pages;
		public TerminalNode IDENT() { return getToken(QLSParser.IDENT, 0); }
		public PagesContext pages() {
			return getRuleContext(PagesContext.class,0);
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
	}

	public final StylesheetContext stylesheet() throws RecognitionException {
		StylesheetContext _localctx = new StylesheetContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stylesheet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			((StylesheetContext)_localctx).f = match(T__0);
			setState(27);
			((StylesheetContext)_localctx).IDENT = match(IDENT);
			setState(28);
			match(T__1);
			setState(29);
			((StylesheetContext)_localctx).pages = pages();
			setState(30);
			match(T__2);

			            ((StylesheetContext)_localctx).result =  new Stylesheet(((StylesheetContext)_localctx).f, (((StylesheetContext)_localctx).IDENT!=null?((StylesheetContext)_localctx).IDENT.getText():null), ((StylesheetContext)_localctx).pages.result);
			        
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

	public static class PagesContext extends ParserRuleContext {
		public List<Page> result;
		public PageContext page;
		public List<PageContext> page() {
			return getRuleContexts(PageContext.class);
		}
		public PageContext page(int i) {
			return getRuleContext(PageContext.class,i);
		}
		public PagesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pages; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterPages(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitPages(this);
		}
	}

	public final PagesContext pages() throws RecognitionException {
		PagesContext _localctx = new PagesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_pages);
		 List<Page> pages = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(33);
				((PagesContext)_localctx).page = page();

				        pages.add(((PagesContext)_localctx).page.result);
				    
				}
				}
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);
			 ((PagesContext)_localctx).result =  pages; 
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
		public Page result;
		public Token p;
		public Token IDENT;
		public SpecificationsContext specifications;
		public TerminalNode IDENT() { return getToken(QLSParser.IDENT, 0); }
		public SpecificationsContext specifications() {
			return getRuleContext(SpecificationsContext.class,0);
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
	}

	public final PageContext page() throws RecognitionException {
		PageContext _localctx = new PageContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_page);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			((PageContext)_localctx).p = match(T__3);
			setState(42);
			((PageContext)_localctx).IDENT = match(IDENT);
			setState(43);
			match(T__1);
			setState(44);
			((PageContext)_localctx).specifications = specifications();
			setState(45);
			match(T__2);

			            ((PageContext)_localctx).result =  new Page(((PageContext)_localctx).p, (((PageContext)_localctx).IDENT!=null?((PageContext)_localctx).IDENT.getText():null), ((PageContext)_localctx).specifications.result);
			        
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

	public static class SpecificationsContext extends ParserRuleContext {
		public List<Specification> result;
		public SpecificationContext specification;
		public List<SpecificationContext> specification() {
			return getRuleContexts(SpecificationContext.class);
		}
		public SpecificationContext specification(int i) {
			return getRuleContext(SpecificationContext.class,i);
		}
		public SpecificationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specifications; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterSpecifications(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitSpecifications(this);
		}
	}

	public final SpecificationsContext specifications() throws RecognitionException {
		SpecificationsContext _localctx = new SpecificationsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_specifications);
		 List<Specification> specifications = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__16))) != 0)) {
				{
				{
				setState(48);
				((SpecificationsContext)_localctx).specification = specification();

				        specifications.add(((SpecificationsContext)_localctx).specification.result);
				    
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);
			 ((SpecificationsContext)_localctx).result =  specifications; 
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

	public static class SpecificationContext extends ParserRuleContext {
		public Specification result;
		public SectionContext section;
		public DefaultStyleContext defaultStyle;
		public QuestionContext question;
		public SectionContext section() {
			return getRuleContext(SectionContext.class,0);
		}
		public DefaultStyleContext defaultStyle() {
			return getRuleContext(DefaultStyleContext.class,0);
		}
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public SpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitSpecification(this);
		}
	}

	public final SpecificationContext specification() throws RecognitionException {
		SpecificationContext _localctx = new SpecificationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_specification);
		try {
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				((SpecificationContext)_localctx).section = section();
				 ((SpecificationContext)_localctx).result =  ((SpecificationContext)_localctx).section.result; 
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				((SpecificationContext)_localctx).defaultStyle = defaultStyle();
				 ((SpecificationContext)_localctx).result =  ((SpecificationContext)_localctx).defaultStyle.result; 
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				((SpecificationContext)_localctx).question = question();
				 ((SpecificationContext)_localctx).result =  ((SpecificationContext)_localctx).question.result; 
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
		public Section result;
		public Token s;
		public Token STR;
		public SpecificationsContext specifications;
		public SpecificationContext specification;
		public TerminalNode STR() { return getToken(QLSParser.STR, 0); }
		public SpecificationsContext specifications() {
			return getRuleContext(SpecificationsContext.class,0);
		}
		public SpecificationContext specification() {
			return getRuleContext(SpecificationContext.class,0);
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
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_section);
		try {
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				((SectionContext)_localctx).s = match(T__4);
				setState(68);
				((SectionContext)_localctx).STR = match(STR);
				setState(69);
				match(T__1);
				setState(70);
				((SectionContext)_localctx).specifications = specifications();
				setState(71);
				match(T__2);

				            ((SectionContext)_localctx).result =  new Section(((SectionContext)_localctx).s, ParserHelper.removeQuotes((((SectionContext)_localctx).STR!=null?((SectionContext)_localctx).STR.getText():null)), ((SectionContext)_localctx).specifications.result);
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				((SectionContext)_localctx).s = match(T__4);
				setState(75);
				((SectionContext)_localctx).STR = match(STR);
				setState(76);
				((SectionContext)_localctx).specification = specification();

				            List<Specification> specifications = new ArrayList<>();
				            specifications.add(((SectionContext)_localctx).specification.result);
				            ((SectionContext)_localctx).result =  new Section(((SectionContext)_localctx).s, ParserHelper.removeQuotes((((SectionContext)_localctx).STR!=null?((SectionContext)_localctx).STR.getText():null)), specifications);
				        
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

	public static class QuestionContext extends ParserRuleContext {
		public Question result;
		public Token q;
		public Token name;
		public WidgetContext widget;
		public TerminalNode IDENT() { return getToken(QLSParser.IDENT, 0); }
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitQuestion(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			((QuestionContext)_localctx).q = match(T__5);
			setState(82);
			((QuestionContext)_localctx).name = match(IDENT);
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(83);
				((QuestionContext)_localctx).widget = widget();
				}
			}


			           Widget widget = null;
			           if((((QuestionContext)_localctx).widget!=null?_input.getText(((QuestionContext)_localctx).widget.start,((QuestionContext)_localctx).widget.stop):null) != null) {
			                widget = ((QuestionContext)_localctx).widget.result;
			           }

			           ((QuestionContext)_localctx).result =  new Question(((QuestionContext)_localctx).q, (((QuestionContext)_localctx).name!=null?((QuestionContext)_localctx).name.getText():null), widget);
			       
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
		public Widget result;
		public Token w;
		public Token name;
		public ParametersContext parameters;
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
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
	}

	public final WidgetContext widget() throws RecognitionException {
		WidgetContext _localctx = new WidgetContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_widget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			((WidgetContext)_localctx).w = match(T__6);
			setState(89);
			((WidgetContext)_localctx).name = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) ) {
				((WidgetContext)_localctx).name = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(90);
				match(T__13);
				setState(91);
				((WidgetContext)_localctx).parameters = parameters();
				setState(92);
				match(T__14);
				}
			}


			        ((WidgetContext)_localctx).result =  new Widget(((WidgetContext)_localctx).w, (((WidgetContext)_localctx).name!=null?((WidgetContext)_localctx).name.getText():null), (((WidgetContext)_localctx).parameters!=null?_input.getText(((WidgetContext)_localctx).parameters.start,((WidgetContext)_localctx).parameters.stop):null) != null ? ((WidgetContext)_localctx).parameters.result : new ArrayList<>());
			    
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

	public static class ParametersContext extends ParserRuleContext {
		public List<Parameter> result;
		public ParameterContext parameter;
		public ParametersContext parameters;
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitParameters(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parameters);
		 List<Parameter> parameters = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STR) {
				{
				setState(98);
				((ParametersContext)_localctx).parameter = parameter();
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(99);
					match(T__15);
					setState(100);
					((ParametersContext)_localctx).parameters = parameters();
					}
				}


				        parameters.add(((ParametersContext)_localctx).parameter.result);

				        if((((ParametersContext)_localctx).parameters!=null?_input.getText(((ParametersContext)_localctx).parameters.start,((ParametersContext)_localctx).parameters.stop):null) != null) {
				            parameters.addAll(((ParametersContext)_localctx).parameters.result);
				        }
				    
				}
			}

			}
			_ctx.stop = _input.LT(-1);
			 ((ParametersContext)_localctx).result =  parameters; 
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

	public static class ParameterContext extends ParserRuleContext {
		public Parameter result;
		public Token p;
		public TerminalNode STR() { return getToken(QLSParser.STR, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitParameter(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			((ParameterContext)_localctx).p = match(STR);

			        ((ParameterContext)_localctx).result =  new Parameter(((ParameterContext)_localctx).p, (((ParameterContext)_localctx).p!=null?((ParameterContext)_localctx).p.getText():null));
			    
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

	public static class DefaultStyleContext extends ParserRuleContext {
		public DefaultStyle result;
		public Token d;
		public Token type;
		public StyleSpecificationsContext styleSpecifications;
		public StyleSpecificationsContext styleSpecifications() {
			return getRuleContext(StyleSpecificationsContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(QLSParser.IDENT, 0); }
		public DefaultStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultStyle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterDefaultStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitDefaultStyle(this);
		}
	}

	public final DefaultStyleContext defaultStyle() throws RecognitionException {
		DefaultStyleContext _localctx = new DefaultStyleContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_defaultStyle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			((DefaultStyleContext)_localctx).d = match(T__16);
			setState(111);
			((DefaultStyleContext)_localctx).type = match(IDENT);
			setState(112);
			((DefaultStyleContext)_localctx).styleSpecifications = styleSpecifications();

			        ((DefaultStyleContext)_localctx).result =  new DefaultStyle(((DefaultStyleContext)_localctx).d, (((DefaultStyleContext)_localctx).type!=null?((DefaultStyleContext)_localctx).type.getText():null), ((DefaultStyleContext)_localctx).styleSpecifications.result);
			    
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

	public static class StyleSpecificationsContext extends ParserRuleContext {
		public List<StyleSpecification> result;
		public StyleSpecificationContext styleSpecification;
		public List<StyleSpecificationContext> styleSpecification() {
			return getRuleContexts(StyleSpecificationContext.class);
		}
		public StyleSpecificationContext styleSpecification(int i) {
			return getRuleContext(StyleSpecificationContext.class,i);
		}
		public StyleSpecificationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_styleSpecifications; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterStyleSpecifications(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitStyleSpecifications(this);
		}
	}

	public final StyleSpecificationsContext styleSpecifications() throws RecognitionException {
		StyleSpecificationsContext _localctx = new StyleSpecificationsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_styleSpecifications);
		 List<StyleSpecification> specifications = new ArrayList<>(); 
		int _la;
		try {
			setState(128);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				match(T__1);
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21))) != 0)) {
					{
					{
					setState(116);
					((StyleSpecificationsContext)_localctx).styleSpecification = styleSpecification();

					                specifications.add(((StyleSpecificationsContext)_localctx).styleSpecification.result);
					           
					}
					}
					setState(123);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(124);
				match(T__2);
				}
				break;
			case T__6:
			case T__17:
			case T__19:
			case T__20:
			case T__21:
				enterOuterAlt(_localctx, 2);
				{
				setState(125);
				((StyleSpecificationsContext)_localctx).styleSpecification = styleSpecification();
				 specifications.add(((StyleSpecificationsContext)_localctx).styleSpecification.result); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			 ((StyleSpecificationsContext)_localctx).result =  specifications; 
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

	public static class StyleSpecificationContext extends ParserRuleContext {
		public StyleSpecification result;
		public Token s;
		public Token NUM;
		public Token STR;
		public Token COLOR_CODE;
		public WidgetContext widget;
		public TerminalNode NUM() { return getToken(QLSParser.NUM, 0); }
		public TerminalNode STR() { return getToken(QLSParser.STR, 0); }
		public TerminalNode COLOR_CODE() { return getToken(QLSParser.COLOR_CODE, 0); }
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public StyleSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_styleSpecification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterStyleSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitStyleSpecification(this);
		}
	}

	public final StyleSpecificationContext styleSpecification() throws RecognitionException {
		StyleSpecificationContext _localctx = new StyleSpecificationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_styleSpecification);
		try {
			setState(149);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__17:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				((StyleSpecificationContext)_localctx).s = match(T__17);
				setState(131);
				match(T__18);
				setState(132);
				((StyleSpecificationContext)_localctx).NUM = match(NUM);
				 ((StyleSpecificationContext)_localctx).result =  new Width(((StyleSpecificationContext)_localctx).s, (((StyleSpecificationContext)_localctx).NUM!=null?((StyleSpecificationContext)_localctx).NUM.getText():null)); 
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				((StyleSpecificationContext)_localctx).s = match(T__19);
				setState(135);
				match(T__18);
				setState(136);
				((StyleSpecificationContext)_localctx).STR = match(STR);
				 ((StyleSpecificationContext)_localctx).result =  new Font(((StyleSpecificationContext)_localctx).s, ParserHelper.removeQuotes((((StyleSpecificationContext)_localctx).STR!=null?((StyleSpecificationContext)_localctx).STR.getText():null))); 
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 3);
				{
				setState(138);
				((StyleSpecificationContext)_localctx).s = match(T__20);
				setState(139);
				match(T__18);
				setState(140);
				((StyleSpecificationContext)_localctx).NUM = match(NUM);
				 ((StyleSpecificationContext)_localctx).result =  new FontSize(((StyleSpecificationContext)_localctx).s, (((StyleSpecificationContext)_localctx).NUM!=null?((StyleSpecificationContext)_localctx).NUM.getText():null)); 
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 4);
				{
				setState(142);
				((StyleSpecificationContext)_localctx).s = match(T__21);
				setState(143);
				match(T__18);
				setState(144);
				((StyleSpecificationContext)_localctx).COLOR_CODE = match(COLOR_CODE);
				 ((StyleSpecificationContext)_localctx).result =  new Color(((StyleSpecificationContext)_localctx).s, (((StyleSpecificationContext)_localctx).COLOR_CODE!=null?((StyleSpecificationContext)_localctx).COLOR_CODE.getText():null)); 
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(146);
				((StyleSpecificationContext)_localctx).widget = widget();
				 ((StyleSpecificationContext)_localctx).result =  ((StyleSpecificationContext)_localctx).widget.result; 
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\37\u009a\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\3\7\3\'\n\3\f\3\16\3*\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\7"+
		"\5\66\n\5\f\5\16\59\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6D\n\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7R\n\7\3\b\3\b\3\b"+
		"\5\bW\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\5\ta\n\t\3\t\3\t\3\n\3\n\3\n"+
		"\5\nh\n\n\3\n\3\n\5\nl\n\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\7\rz\n\r\f\r\16\r}\13\r\3\r\3\r\3\r\3\r\5\r\u0083\n\r\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\5\16\u0098\n\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\2\3\3\2\n\17\2\u009b\2\34\3\2\2\2\4(\3\2\2\2\6+\3\2\2\2\b\67"+
		"\3\2\2\2\nC\3\2\2\2\fQ\3\2\2\2\16S\3\2\2\2\20Z\3\2\2\2\22k\3\2\2\2\24"+
		"m\3\2\2\2\26p\3\2\2\2\30\u0082\3\2\2\2\32\u0097\3\2\2\2\34\35\7\3\2\2"+
		"\35\36\7\31\2\2\36\37\7\4\2\2\37 \5\4\3\2 !\7\5\2\2!\"\b\2\1\2\"\3\3\2"+
		"\2\2#$\5\6\4\2$%\b\3\1\2%\'\3\2\2\2&#\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3"+
		"\2\2\2)\5\3\2\2\2*(\3\2\2\2+,\7\6\2\2,-\7\31\2\2-.\7\4\2\2./\5\b\5\2/"+
		"\60\7\5\2\2\60\61\b\4\1\2\61\7\3\2\2\2\62\63\5\n\6\2\63\64\b\5\1\2\64"+
		"\66\3\2\2\2\65\62\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\t\3\2"+
		"\2\29\67\3\2\2\2:;\5\f\7\2;<\b\6\1\2<D\3\2\2\2=>\5\26\f\2>?\b\6\1\2?D"+
		"\3\2\2\2@A\5\16\b\2AB\b\6\1\2BD\3\2\2\2C:\3\2\2\2C=\3\2\2\2C@\3\2\2\2"+
		"D\13\3\2\2\2EF\7\7\2\2FG\7\34\2\2GH\7\4\2\2HI\5\b\5\2IJ\7\5\2\2JK\b\7"+
		"\1\2KR\3\2\2\2LM\7\7\2\2MN\7\34\2\2NO\5\n\6\2OP\b\7\1\2PR\3\2\2\2QE\3"+
		"\2\2\2QL\3\2\2\2R\r\3\2\2\2ST\7\b\2\2TV\7\31\2\2UW\5\20\t\2VU\3\2\2\2"+
		"VW\3\2\2\2WX\3\2\2\2XY\b\b\1\2Y\17\3\2\2\2Z[\7\t\2\2[`\t\2\2\2\\]\7\20"+
		"\2\2]^\5\22\n\2^_\7\21\2\2_a\3\2\2\2`\\\3\2\2\2`a\3\2\2\2ab\3\2\2\2bc"+
		"\b\t\1\2c\21\3\2\2\2dg\5\24\13\2ef\7\22\2\2fh\5\22\n\2ge\3\2\2\2gh\3\2"+
		"\2\2hi\3\2\2\2ij\b\n\1\2jl\3\2\2\2kd\3\2\2\2kl\3\2\2\2l\23\3\2\2\2mn\7"+
		"\34\2\2no\b\13\1\2o\25\3\2\2\2pq\7\23\2\2qr\7\31\2\2rs\5\30\r\2st\b\f"+
		"\1\2t\27\3\2\2\2u{\7\4\2\2vw\5\32\16\2wx\b\r\1\2xz\3\2\2\2yv\3\2\2\2z"+
		"}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|~\3\2\2\2}{\3\2\2\2~\u0083\7\5\2\2\177\u0080"+
		"\5\32\16\2\u0080\u0081\b\r\1\2\u0081\u0083\3\2\2\2\u0082u\3\2\2\2\u0082"+
		"\177\3\2\2\2\u0083\31\3\2\2\2\u0084\u0085\7\24\2\2\u0085\u0086\7\25\2"+
		"\2\u0086\u0087\7\32\2\2\u0087\u0098\b\16\1\2\u0088\u0089\7\26\2\2\u0089"+
		"\u008a\7\25\2\2\u008a\u008b\7\34\2\2\u008b\u0098\b\16\1\2\u008c\u008d"+
		"\7\27\2\2\u008d\u008e\7\25\2\2\u008e\u008f\7\32\2\2\u008f\u0098\b\16\1"+
		"\2\u0090\u0091\7\30\2\2\u0091\u0092\7\25\2\2\u0092\u0093\7\33\2\2\u0093"+
		"\u0098\b\16\1\2\u0094\u0095\5\20\t\2\u0095\u0096\b\16\1\2\u0096\u0098"+
		"\3\2\2\2\u0097\u0084\3\2\2\2\u0097\u0088\3\2\2\2\u0097\u008c\3\2\2\2\u0097"+
		"\u0090\3\2\2\2\u0097\u0094\3\2\2\2\u0098\33\3\2\2\2\r(\67CQV`gk{\u0082"+
		"\u0097";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}