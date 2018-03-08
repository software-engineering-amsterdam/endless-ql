// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/SC-Java-LDG-TV/src/main/java/antlr/qls\Stylesheet.g4 by ANTLR 4.7
package antlr.qls;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StylesheetParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, NUMBERS=13, STRING=14, CURLY_BRACKET_OPEN=15, 
		CURLY_BRACKET_CLOSE=16, BRACKET_OPEN=17, BRACKET_CLOSE=18, WHITESPACE=19, 
		NEWLINE=20, CHARACTERS=21, SPECIAL_CHAR=22;
	public static final int
		RULE_stylesheetBuilder = 0, RULE_stylesheetData = 1, RULE_pageNodeStructure = 2, 
		RULE_sectionNodeStructure = 3, RULE_identifier = 4, RULE_label = 5, RULE_value = 6, 
		RULE_questionStructure = 7, RULE_uiElement = 8, RULE_uiIdentifier = 9, 
		RULE_uiType = 10, RULE_checkbox = 11, RULE_radio = 12, RULE_radioOptions = 13, 
		RULE_spinbox = 14, RULE_money = 15, RULE_option = 16;
	public static final String[] ruleNames = {
		"stylesheetBuilder", "stylesheetData", "pageNodeStructure", "sectionNodeStructure", 
		"identifier", "label", "value", "questionStructure", "uiElement", "uiIdentifier", 
		"uiType", "checkbox", "radio", "radioOptions", "spinbox", "money", "option"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'stylesheet'", "'page'", "'section'", "'question'", "'widget'", 
		"'default'", "'checkbox'", "'radio'", "','", "'spinbox'", "'money'", "':'", 
		null, null, "'{'", "'}'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "NUMBERS", "STRING", "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSE", 
		"BRACKET_OPEN", "BRACKET_CLOSE", "WHITESPACE", "NEWLINE", "CHARACTERS", 
		"SPECIAL_CHAR"
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
	public String getGrammarFileName() { return "Stylesheet.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public StylesheetParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StylesheetBuilderContext extends ParserRuleContext {
		public TerminalNode CHARACTERS() { return getToken(StylesheetParser.CHARACTERS, 0); }
		public TerminalNode CURLY_BRACKET_OPEN() { return getToken(StylesheetParser.CURLY_BRACKET_OPEN, 0); }
		public StylesheetDataContext stylesheetData() {
			return getRuleContext(StylesheetDataContext.class,0);
		}
		public TerminalNode CURLY_BRACKET_CLOSE() { return getToken(StylesheetParser.CURLY_BRACKET_CLOSE, 0); }
		public StylesheetBuilderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stylesheetBuilder; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterStylesheetBuilder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitStylesheetBuilder(this);
		}
	}

	public final StylesheetBuilderContext stylesheetBuilder() throws RecognitionException {
		StylesheetBuilderContext _localctx = new StylesheetBuilderContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stylesheetBuilder);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			match(CHARACTERS);
			setState(36);
			match(CURLY_BRACKET_OPEN);
			setState(37);
			stylesheetData();
			setState(38);
			match(CURLY_BRACKET_CLOSE);
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

	public static class StylesheetDataContext extends ParserRuleContext {
		public List<PageNodeStructureContext> pageNodeStructure() {
			return getRuleContexts(PageNodeStructureContext.class);
		}
		public PageNodeStructureContext pageNodeStructure(int i) {
			return getRuleContext(PageNodeStructureContext.class,i);
		}
		public StylesheetDataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stylesheetData; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterStylesheetData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitStylesheetData(this);
		}
	}

	public final StylesheetDataContext stylesheetData() throws RecognitionException {
		StylesheetDataContext _localctx = new StylesheetDataContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stylesheetData);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				pageNodeStructure();
				}
				}
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 );
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

	public static class PageNodeStructureContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public TerminalNode CURLY_BRACKET_OPEN() { return getToken(StylesheetParser.CURLY_BRACKET_OPEN, 0); }
		public TerminalNode CURLY_BRACKET_CLOSE() { return getToken(StylesheetParser.CURLY_BRACKET_CLOSE, 0); }
		public List<SectionNodeStructureContext> sectionNodeStructure() {
			return getRuleContexts(SectionNodeStructureContext.class);
		}
		public SectionNodeStructureContext sectionNodeStructure(int i) {
			return getRuleContext(SectionNodeStructureContext.class,i);
		}
		public PageNodeStructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pageNodeStructure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterPageNodeStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitPageNodeStructure(this);
		}
	}

	public final PageNodeStructureContext pageNodeStructure() throws RecognitionException {
		PageNodeStructureContext _localctx = new PageNodeStructureContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pageNodeStructure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(T__1);
			setState(46);
			label();
			setState(47);
			match(CURLY_BRACKET_OPEN);
			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48);
				sectionNodeStructure();
				}
				}
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			setState(53);
			match(CURLY_BRACKET_CLOSE);
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

	public static class SectionNodeStructureContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public TerminalNode CURLY_BRACKET_OPEN() { return getToken(StylesheetParser.CURLY_BRACKET_OPEN, 0); }
		public List<QuestionStructureContext> questionStructure() {
			return getRuleContexts(QuestionStructureContext.class);
		}
		public QuestionStructureContext questionStructure(int i) {
			return getRuleContext(QuestionStructureContext.class,i);
		}
		public TerminalNode CURLY_BRACKET_CLOSE() { return getToken(StylesheetParser.CURLY_BRACKET_CLOSE, 0); }
		public List<SectionNodeStructureContext> sectionNodeStructure() {
			return getRuleContexts(SectionNodeStructureContext.class);
		}
		public SectionNodeStructureContext sectionNodeStructure(int i) {
			return getRuleContext(SectionNodeStructureContext.class,i);
		}
		public SectionNodeStructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sectionNodeStructure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterSectionNodeStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitSectionNodeStructure(this);
		}
	}

	public final SectionNodeStructureContext sectionNodeStructure() throws RecognitionException {
		SectionNodeStructureContext _localctx = new SectionNodeStructureContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_sectionNodeStructure);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(T__2);
			setState(56);
			label();
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CURLY_BRACKET_OPEN) {
				{
				setState(57);
				match(CURLY_BRACKET_OPEN);
				}
			}

			setState(61); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(60);
				questionStructure();
				}
				}
				setState(63); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 );
			setState(66);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(65);
				match(CURLY_BRACKET_CLOSE);
				}
				break;
			}
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(69); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(68);
						sectionNodeStructure();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(71); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
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

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode CHARACTERS() { return getToken(StylesheetParser.CHARACTERS, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(CHARACTERS);
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
		public TerminalNode STRING() { return getToken(StylesheetParser.STRING, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitLabel(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(STRING);
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
		public TerminalNode CHARACTERS() { return getToken(StylesheetParser.CHARACTERS, 0); }
		public TerminalNode STRING() { return getToken(StylesheetParser.STRING, 0); }
		public TerminalNode NUMBERS() { return getToken(StylesheetParser.NUMBERS, 0); }
		public TerminalNode SPECIAL_CHAR() { return getToken(StylesheetParser.SPECIAL_CHAR, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CHARACTERS:
				{
				setState(79);
				match(CHARACTERS);
				}
				break;
			case STRING:
				{
				setState(80);
				match(STRING);
				}
				break;
			case NUMBERS:
				{
				setState(81);
				match(NUMBERS);
				}
				break;
			case SPECIAL_CHAR:
				{
				{
				setState(82);
				match(SPECIAL_CHAR);
				setState(83);
				match(NUMBERS);
				}
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

	public static class QuestionStructureContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public UiElementContext uiElement() {
			return getRuleContext(UiElementContext.class,0);
		}
		public QuestionStructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionStructure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterQuestionStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitQuestionStructure(this);
		}
	}

	public final QuestionStructureContext questionStructure() throws RecognitionException {
		QuestionStructureContext _localctx = new QuestionStructureContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_questionStructure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(T__3);
			setState(87);
			identifier();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4 || _la==T__5) {
				{
				setState(88);
				uiElement();
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

	public static class UiElementContext extends ParserRuleContext {
		public UiIdentifierContext uiIdentifier() {
			return getRuleContext(UiIdentifierContext.class,0);
		}
		public UiTypeContext uiType() {
			return getRuleContext(UiTypeContext.class,0);
		}
		public UiElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uiElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterUiElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitUiElement(this);
		}
	}

	public final UiElementContext uiElement() throws RecognitionException {
		UiElementContext _localctx = new UiElementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_uiElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			uiIdentifier();
			setState(92);
			uiType();
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

	public static class UiIdentifierContext extends ParserRuleContext {
		public UiIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uiIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterUiIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitUiIdentifier(this);
		}
	}

	public final UiIdentifierContext uiIdentifier() throws RecognitionException {
		UiIdentifierContext _localctx = new UiIdentifierContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_uiIdentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_la = _input.LA(1);
			if ( !(_la==T__4 || _la==T__5) ) {
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

	public static class UiTypeContext extends ParserRuleContext {
		public CheckboxContext checkbox() {
			return getRuleContext(CheckboxContext.class,0);
		}
		public RadioContext radio() {
			return getRuleContext(RadioContext.class,0);
		}
		public SpinboxContext spinbox() {
			return getRuleContext(SpinboxContext.class,0);
		}
		public MoneyContext money() {
			return getRuleContext(MoneyContext.class,0);
		}
		public UiTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uiType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterUiType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitUiType(this);
		}
	}

	public final UiTypeContext uiType() throws RecognitionException {
		UiTypeContext _localctx = new UiTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_uiType);
		try {
			setState(100);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(96);
				checkbox();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
				radio();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 3);
				{
				setState(98);
				spinbox();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 4);
				{
				setState(99);
				money();
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

	public static class CheckboxContext extends ParserRuleContext {
		public CheckboxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkbox; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterCheckbox(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitCheckbox(this);
		}
	}

	public final CheckboxContext checkbox() throws RecognitionException {
		CheckboxContext _localctx = new CheckboxContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_checkbox);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(T__6);
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

	public static class RadioContext extends ParserRuleContext {
		public List<RadioOptionsContext> radioOptions() {
			return getRuleContexts(RadioOptionsContext.class);
		}
		public RadioOptionsContext radioOptions(int i) {
			return getRuleContext(RadioOptionsContext.class,i);
		}
		public RadioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_radio; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterRadio(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitRadio(this);
		}
	}

	public final RadioContext radio() throws RecognitionException {
		RadioContext _localctx = new RadioContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_radio);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(T__7);
			setState(106); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(105);
				radioOptions();
				}
				}
				setState(108); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==BRACKET_OPEN );
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

	public static class RadioOptionsContext extends ParserRuleContext {
		public TerminalNode BRACKET_OPEN() { return getToken(StylesheetParser.BRACKET_OPEN, 0); }
		public List<LabelContext> label() {
			return getRuleContexts(LabelContext.class);
		}
		public LabelContext label(int i) {
			return getRuleContext(LabelContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(StylesheetParser.BRACKET_CLOSE, 0); }
		public RadioOptionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_radioOptions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterRadioOptions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitRadioOptions(this);
		}
	}

	public final RadioOptionsContext radioOptions() throws RecognitionException {
		RadioOptionsContext _localctx = new RadioOptionsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_radioOptions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(BRACKET_OPEN);
			setState(111);
			label();
			setState(112);
			match(T__8);
			setState(113);
			label();
			setState(114);
			match(BRACKET_CLOSE);
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

	public static class SpinboxContext extends ParserRuleContext {
		public SpinboxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spinbox; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterSpinbox(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitSpinbox(this);
		}
	}

	public final SpinboxContext spinbox() throws RecognitionException {
		SpinboxContext _localctx = new SpinboxContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_spinbox);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(T__9);
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
		public TerminalNode CURLY_BRACKET_OPEN() { return getToken(StylesheetParser.CURLY_BRACKET_OPEN, 0); }
		public TerminalNode CURLY_BRACKET_CLOSE() { return getToken(StylesheetParser.CURLY_BRACKET_CLOSE, 0); }
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public MoneyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_money; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterMoney(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitMoney(this);
		}
	}

	public final MoneyContext money() throws RecognitionException {
		MoneyContext _localctx = new MoneyContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_money);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(T__10);
			setState(119);
			match(CURLY_BRACKET_OPEN);
			setState(121); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(120);
				option();
				}
				}
				setState(123); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CHARACTERS );
			setState(125);
			match(CURLY_BRACKET_CLOSE);
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

	public static class OptionContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public UiElementContext uiElement() {
			return getRuleContext(UiElementContext.class,0);
		}
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StylesheetListener ) ((StylesheetListener)listener).exitOption(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			identifier();
			setState(128);
			match(T__11);
			setState(129);
			value();
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4 || _la==T__5) {
				{
				setState(130);
				uiElement();
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\30\u0088\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3,\n\3\r\3\16\3-\3\4\3\4\3\4\3\4\6\4\64"+
		"\n\4\r\4\16\4\65\3\4\3\4\3\5\3\5\3\5\5\5=\n\5\3\5\6\5@\n\5\r\5\16\5A\3"+
		"\5\5\5E\n\5\3\5\6\5H\n\5\r\5\16\5I\5\5L\n\5\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\b\5\bW\n\b\3\t\3\t\3\t\5\t\\\n\t\3\n\3\n\3\n\3\13\3\13\3\f\3"+
		"\f\3\f\3\f\5\fg\n\f\3\r\3\r\3\16\3\16\6\16m\n\16\r\16\16\16n\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\21\6\21|\n\21\r\21\16\21}\3"+
		"\21\3\21\3\22\3\22\3\22\3\22\5\22\u0086\n\22\3\22\2\2\23\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"\2\3\3\2\7\b\2\u0087\2$\3\2\2\2\4+\3\2\2\2"+
		"\6/\3\2\2\2\b9\3\2\2\2\nM\3\2\2\2\fO\3\2\2\2\16V\3\2\2\2\20X\3\2\2\2\22"+
		"]\3\2\2\2\24`\3\2\2\2\26f\3\2\2\2\30h\3\2\2\2\32j\3\2\2\2\34p\3\2\2\2"+
		"\36v\3\2\2\2 x\3\2\2\2\"\u0081\3\2\2\2$%\7\3\2\2%&\7\27\2\2&\'\7\21\2"+
		"\2\'(\5\4\3\2()\7\22\2\2)\3\3\2\2\2*,\5\6\4\2+*\3\2\2\2,-\3\2\2\2-+\3"+
		"\2\2\2-.\3\2\2\2.\5\3\2\2\2/\60\7\4\2\2\60\61\5\f\7\2\61\63\7\21\2\2\62"+
		"\64\5\b\5\2\63\62\3\2\2\2\64\65\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66"+
		"\67\3\2\2\2\678\7\22\2\28\7\3\2\2\29:\7\5\2\2:<\5\f\7\2;=\7\21\2\2<;\3"+
		"\2\2\2<=\3\2\2\2=?\3\2\2\2>@\5\20\t\2?>\3\2\2\2@A\3\2\2\2A?\3\2\2\2AB"+
		"\3\2\2\2BD\3\2\2\2CE\7\22\2\2DC\3\2\2\2DE\3\2\2\2EK\3\2\2\2FH\5\b\5\2"+
		"GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KG\3\2\2\2KL\3\2\2\2"+
		"L\t\3\2\2\2MN\7\27\2\2N\13\3\2\2\2OP\7\20\2\2P\r\3\2\2\2QW\7\27\2\2RW"+
		"\7\20\2\2SW\7\17\2\2TU\7\30\2\2UW\7\17\2\2VQ\3\2\2\2VR\3\2\2\2VS\3\2\2"+
		"\2VT\3\2\2\2W\17\3\2\2\2XY\7\6\2\2Y[\5\n\6\2Z\\\5\22\n\2[Z\3\2\2\2[\\"+
		"\3\2\2\2\\\21\3\2\2\2]^\5\24\13\2^_\5\26\f\2_\23\3\2\2\2`a\t\2\2\2a\25"+
		"\3\2\2\2bg\5\30\r\2cg\5\32\16\2dg\5\36\20\2eg\5 \21\2fb\3\2\2\2fc\3\2"+
		"\2\2fd\3\2\2\2fe\3\2\2\2g\27\3\2\2\2hi\7\t\2\2i\31\3\2\2\2jl\7\n\2\2k"+
		"m\5\34\17\2lk\3\2\2\2mn\3\2\2\2nl\3\2\2\2no\3\2\2\2o\33\3\2\2\2pq\7\23"+
		"\2\2qr\5\f\7\2rs\7\13\2\2st\5\f\7\2tu\7\24\2\2u\35\3\2\2\2vw\7\f\2\2w"+
		"\37\3\2\2\2xy\7\r\2\2y{\7\21\2\2z|\5\"\22\2{z\3\2\2\2|}\3\2\2\2}{\3\2"+
		"\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\7\22\2\2\u0080!\3\2\2\2\u0081\u0082"+
		"\5\n\6\2\u0082\u0083\7\16\2\2\u0083\u0085\5\16\b\2\u0084\u0086\5\22\n"+
		"\2\u0085\u0084\3\2\2\2\u0085\u0086\3\2\2\2\u0086#\3\2\2\2\17-\65<ADIK"+
		"V[fn}\u0085";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}