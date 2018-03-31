// Generated from /Users/piotrkosytorz/Library/Mobile Documents/com~apple~CloudDocs/Study/SC/endless-ql/piotr/org.uva.sc.piotr.qls/src/main/java/qls/grammar/QLS.g4 by ANTLR 4.7
package qls.grammar;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLSParser extends Parser {
    public static final int
            T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
            T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, WIDGET_INTEGER_SPINBOX = 17,
            WIDGET_INTEGER_SLIDER = 18, WIDGET_TEXT = 19, WIDGET_BOOL_CHECKBOX = 20, WIDGET_BOOL_RADIO = 21,
            WIDGET_BOOL_DROPDOWN = 22, TYPE_BOOLEAN = 23, TYPE_STRING = 24, TYPE_INTEGER = 25,
            TYPE_DECIMAL = 26, TYPE_MONEY = 27, TYPE_DATE = 28, IDENTIFIER = 29, BEGIN = 30,
            END = 31, OPEN_PARENTHESIS = 32, CLOSE_PARENTHESIS = 33, STRING = 34, INTEGER = 35,
            HEX_VALUE = 36, HEX_DIGIT = 37, WS = 38, COMMENT = 39;
    public static final int
            RULE_stylesheet = 0, RULE_pageDefinition = 1, RULE_blockElement = 2, RULE_section = 3,
            RULE_questionDefinition = 4, RULE_defaultDefinition = 5, RULE_typeDefinitionProperty = 6,
            RULE_dataType = 7, RULE_widgetDefinition = 8, RULE_widget = 9, RULE_booleanParameters = 10,
            RULE_integerParameters = 11;
    public static final String[] ruleNames = {
            "stylesheet", "pageDefinition", "blockElement", "section", "questionDefinition",
            "defaultDefinition", "typeDefinitionProperty", "dataType", "widgetDefinition",
            "widget", "booleanParameters", "integerParameters"
    };
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3)\u0094\4\2\t\2\4" +
                    "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
                    "\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\7\2 \n\2\f\2\16\2#\13\2\3\2\3" +
                    "\2\3\3\3\3\3\3\3\3\6\3+\n\3\r\3\16\3,\3\3\3\3\3\4\3\4\3\4\5\4\64\n\4\3" +
                    "\5\3\5\3\5\3\5\6\5:\n\5\r\5\16\5;\3\5\3\5\3\5\5\5A\n\5\3\6\3\6\3\6\5\6" +
                    "F\n\6\3\7\3\7\3\7\3\7\3\7\7\7M\n\7\f\7\16\7P\13\7\3\7\5\7S\n\7\3\b\3\b" +
                    "\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bb\n\b\3\t\3\t\3\n\3\n" +
                    "\3\n\3\13\3\13\5\13k\n\13\3\13\3\13\5\13o\n\13\3\13\3\13\5\13s\n\13\3" +
                    "\13\3\13\5\13w\n\13\3\13\3\13\5\13{\n\13\3\13\5\13~\n\13\3\f\3\f\3\f\3" +
                    "\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r" +
                    "\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30\2\3\3\2\31\36\2\u009f\2\32\3\2\2" +
                    "\2\4&\3\2\2\2\6\63\3\2\2\2\b\65\3\2\2\2\nB\3\2\2\2\fG\3\2\2\2\16a\3\2" +
                    "\2\2\20c\3\2\2\2\22e\3\2\2\2\24}\3\2\2\2\26\177\3\2\2\2\30\u0085\3\2\2" +
                    "\2\32\33\7\3\2\2\33\34\7\37\2\2\34!\7 \2\2\35 \5\4\3\2\36 \5\f\7\2\37" +
                    "\35\3\2\2\2\37\36\3\2\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"$\3\2\2\2" +
                    "#!\3\2\2\2$%\7!\2\2%\3\3\2\2\2&\'\7\4\2\2\'(\7\37\2\2(*\7 \2\2)+\5\6\4" +
                    "\2*)\3\2\2\2+,\3\2\2\2,*\3\2\2\2,-\3\2\2\2-.\3\2\2\2./\7!\2\2/\5\3\2\2" +
                    "\2\60\64\5\n\6\2\61\64\5\b\5\2\62\64\5\f\7\2\63\60\3\2\2\2\63\61\3\2\2" +
                    "\2\63\62\3\2\2\2\64\7\3\2\2\2\65\66\7\5\2\2\66@\7$\2\2\679\7 \2\28:\5" +
                    "\6\4\298\3\2\2\2:;\3\2\2\2;9\3\2\2\2;<\3\2\2\2<=\3\2\2\2=>\7!\2\2>A\3" +
                    "\2\2\2?A\5\n\6\2@\67\3\2\2\2@?\3\2\2\2A\t\3\2\2\2BC\7\6\2\2CE\7\37\2\2" +
                    "DF\5\22\n\2ED\3\2\2\2EF\3\2\2\2F\13\3\2\2\2GH\7\7\2\2HR\5\20\t\2IS\5\22" +
                    "\n\2JN\7 \2\2KM\5\16\b\2LK\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OQ\3\2" +
                    "\2\2PN\3\2\2\2QS\7!\2\2RI\3\2\2\2RJ\3\2\2\2S\r\3\2\2\2TU\7\b\2\2UV\7\t" +
                    "\2\2Vb\7%\2\2WX\7\n\2\2XY\7\t\2\2Yb\7$\2\2Z[\7\13\2\2[\\\7\t\2\2\\b\7" +
                    "%\2\2]^\7\f\2\2^_\7\t\2\2_b\7&\2\2`b\5\22\n\2aT\3\2\2\2aW\3\2\2\2aZ\3" +
                    "\2\2\2a]\3\2\2\2a`\3\2\2\2b\17\3\2\2\2cd\t\2\2\2d\21\3\2\2\2ef\7\r\2\2" +
                    "fg\5\24\13\2g\23\3\2\2\2hj\7\26\2\2ik\5\26\f\2ji\3\2\2\2jk\3\2\2\2k~\3" +
                    "\2\2\2ln\7\30\2\2mo\5\26\f\2nm\3\2\2\2no\3\2\2\2o~\3\2\2\2pr\7\27\2\2" +
                    "qs\5\26\f\2rq\3\2\2\2rs\3\2\2\2s~\3\2\2\2tv\7\23\2\2uw\5\30\r\2vu\3\2" +
                    "\2\2vw\3\2\2\2w~\3\2\2\2xz\7\24\2\2y{\5\30\r\2zy\3\2\2\2z{\3\2\2\2{~\3" +
                    "\2\2\2|~\7\25\2\2}h\3\2\2\2}l\3\2\2\2}p\3\2\2\2}t\3\2\2\2}x\3\2\2\2}|" +
                    "\3\2\2\2~\25\3\2\2\2\177\u0080\7\"\2\2\u0080\u0081\7$\2\2\u0081\u0082" +
                    "\7\16\2\2\u0082\u0083\7$\2\2\u0083\u0084\7#\2\2\u0084\27\3\2\2\2\u0085" +
                    "\u0086\7\"\2\2\u0086\u0087\7\17\2\2\u0087\u0088\7\20\2\2\u0088\u0089\7" +
                    "%\2\2\u0089\u008a\7\16\2\2\u008a\u008b\7\21\2\2\u008b\u008c\7\20\2\2\u008c" +
                    "\u008d\7%\2\2\u008d\u008e\7\16\2\2\u008e\u008f\7\22\2\2\u008f\u0090\7" +
                    "\20\2\2\u0090\u0091\7%\2\2\u0091\u0092\7#\2\2\u0092\31\3\2\2\2\22\37!" +
                    ",\63;@ENRajnrvz}";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
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

    static {
        RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION);
    }

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

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }

    public QLSParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
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
    public String getGrammarFileName() {
        return "QLS.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public final StylesheetContext stylesheet() throws RecognitionException {
        StylesheetContext _localctx = new StylesheetContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_stylesheet);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(24);
                match(T__0);
                setState(25);
                ((StylesheetContext) _localctx).name = match(IDENTIFIER);
                setState(26);
                match(BEGIN);
                setState(31);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__1 || _la == T__4) {
                    {
                        setState(29);
                        _errHandler.sync(this);
                        switch (_input.LA(1)) {
                            case T__1: {
                                setState(27);
                                pageDefinition();
                            }
                            break;
                            case T__4: {
                                setState(28);
                                defaultDefinition();
                            }
                            break;
                            default:
                                throw new NoViableAltException(this);
                        }
                    }
                    setState(33);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(34);
                match(END);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final PageDefinitionContext pageDefinition() throws RecognitionException {
        PageDefinitionContext _localctx = new PageDefinitionContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_pageDefinition);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(36);
                match(T__1);
                setState(37);
                ((PageDefinitionContext) _localctx).name = match(IDENTIFIER);
                setState(38);
                match(BEGIN);
                setState(40);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        {
                            setState(39);
                            blockElement();
                        }
                    }
                    setState(42);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0));
                setState(44);
                match(END);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final BlockElementContext blockElement() throws RecognitionException {
        BlockElementContext _localctx = new BlockElementContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_blockElement);
        try {
            setState(49);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__3:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(46);
                    questionDefinition();
                }
                break;
                case T__2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(47);
                    section();
                }
                break;
                case T__4:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(48);
                    defaultDefinition();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final SectionContext section() throws RecognitionException {
        SectionContext _localctx = new SectionContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_section);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(51);
                match(T__2);
                setState(52);
                ((SectionContext) _localctx).name = match(STRING);
                setState(62);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case BEGIN: {
                        setState(53);
                        match(BEGIN);
                        setState(55);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        do {
                            {
                                {
                                    setState(54);
                                    blockElement();
                                }
                            }
                            setState(57);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                        }
                        while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0));
                        setState(59);
                        match(END);
                    }
                    break;
                    case T__3: {
                        setState(61);
                        questionDefinition();
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final QuestionDefinitionContext questionDefinition() throws RecognitionException {
        QuestionDefinitionContext _localctx = new QuestionDefinitionContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_questionDefinition);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(64);
                match(T__3);
                setState(65);
                ((QuestionDefinitionContext) _localctx).name = match(IDENTIFIER);
                setState(67);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__10) {
                    {
                        setState(66);
                        widgetDefinition();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final DefaultDefinitionContext defaultDefinition() throws RecognitionException {
        DefaultDefinitionContext _localctx = new DefaultDefinitionContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_defaultDefinition);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(69);
                match(T__4);
                setState(70);
                ((DefaultDefinitionContext) _localctx).type = dataType();
                setState(80);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case T__10: {
                        setState(71);
                        widgetDefinition();
                    }
                    break;
                    case BEGIN: {
                        setState(72);
                        match(BEGIN);
                        setState(76);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) {
                            {
                                {
                                    setState(73);
                                    typeDefinitionProperty();
                                }
                            }
                            setState(78);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                        }
                        setState(79);
                        match(END);
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeDefinitionPropertyContext typeDefinitionProperty() throws RecognitionException {
        TypeDefinitionPropertyContext _localctx = new TypeDefinitionPropertyContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_typeDefinitionProperty);
        try {
            setState(95);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__5:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(82);
                    match(T__5);
                    setState(83);
                    match(T__6);
                    setState(84);
                    ((TypeDefinitionPropertyContext) _localctx).width = match(INTEGER);
                }
                break;
                case T__7:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(85);
                    match(T__7);
                    setState(86);
                    match(T__6);
                    setState(87);
                    ((TypeDefinitionPropertyContext) _localctx).font = match(STRING);
                }
                break;
                case T__8:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(88);
                    match(T__8);
                    setState(89);
                    match(T__6);
                    setState(90);
                    ((TypeDefinitionPropertyContext) _localctx).fontSize = match(INTEGER);
                }
                break;
                case T__9:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(91);
                    match(T__9);
                    setState(92);
                    match(T__6);
                    setState(93);
                    ((TypeDefinitionPropertyContext) _localctx).color = match(HEX_VALUE);
                }
                break;
                case T__10:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(94);
                    widgetDefinition();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final DataTypeContext dataType() throws RecognitionException {
        DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_dataType);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(97);
                _la = _input.LA(1);
                if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TYPE_BOOLEAN) | (1L << TYPE_STRING) | (1L << TYPE_INTEGER) | (1L << TYPE_DECIMAL) | (1L << TYPE_MONEY) | (1L << TYPE_DATE))) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final WidgetDefinitionContext widgetDefinition() throws RecognitionException {
        WidgetDefinitionContext _localctx = new WidgetDefinitionContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_widgetDefinition);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(99);
                match(T__10);
                setState(100);
                widget();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final WidgetContext widget() throws RecognitionException {
        WidgetContext _localctx = new WidgetContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_widget);
        int _la;
        try {
            setState(123);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case WIDGET_BOOL_CHECKBOX:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(102);
                    match(WIDGET_BOOL_CHECKBOX);
                    setState(104);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == OPEN_PARENTHESIS) {
                        {
                            setState(103);
                            booleanParameters();
                        }
                    }

                }
                break;
                case WIDGET_BOOL_DROPDOWN:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(106);
                    match(WIDGET_BOOL_DROPDOWN);
                    setState(108);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == OPEN_PARENTHESIS) {
                        {
                            setState(107);
                            booleanParameters();
                        }
                    }

                }
                break;
                case WIDGET_BOOL_RADIO:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(110);
                    match(WIDGET_BOOL_RADIO);
                    setState(112);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == OPEN_PARENTHESIS) {
                        {
                            setState(111);
                            booleanParameters();
                        }
                    }

                }
                break;
                case WIDGET_INTEGER_SPINBOX:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(114);
                    match(WIDGET_INTEGER_SPINBOX);
                    setState(116);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == OPEN_PARENTHESIS) {
                        {
                            setState(115);
                            integerParameters();
                        }
                    }

                }
                break;
                case WIDGET_INTEGER_SLIDER:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(118);
                    match(WIDGET_INTEGER_SLIDER);
                    setState(120);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == OPEN_PARENTHESIS) {
                        {
                            setState(119);
                            integerParameters();
                        }
                    }

                }
                break;
                case WIDGET_TEXT:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(122);
                    match(WIDGET_TEXT);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final BooleanParametersContext booleanParameters() throws RecognitionException {
        BooleanParametersContext _localctx = new BooleanParametersContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_booleanParameters);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(125);
                match(OPEN_PARENTHESIS);
                setState(126);
                ((BooleanParametersContext) _localctx).trueValue = match(STRING);
                setState(127);
                match(T__11);
                setState(128);
                ((BooleanParametersContext) _localctx).falseValue = match(STRING);
                setState(129);
                match(CLOSE_PARENTHESIS);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final IntegerParametersContext integerParameters() throws RecognitionException {
        IntegerParametersContext _localctx = new IntegerParametersContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_integerParameters);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(131);
                match(OPEN_PARENTHESIS);
                setState(132);
                match(T__12);
                setState(133);
                match(T__13);
                setState(134);
                ((IntegerParametersContext) _localctx).min = match(INTEGER);
                setState(135);
                match(T__11);
                setState(136);
                match(T__14);
                setState(137);
                match(T__13);
                setState(138);
                ((IntegerParametersContext) _localctx).max = match(INTEGER);
                setState(139);
                match(T__11);
                setState(140);
                match(T__15);
                setState(141);
                match(T__13);
                setState(142);
                ((IntegerParametersContext) _localctx).step = match(INTEGER);
                setState(143);
                match(CLOSE_PARENTHESIS);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class StylesheetContext extends ParserRuleContext {
        public Token name;

        public StylesheetContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode BEGIN() {
            return getToken(QLSParser.BEGIN, 0);
        }

        public TerminalNode END() {
            return getToken(QLSParser.END, 0);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(QLSParser.IDENTIFIER, 0);
        }

        public List<PageDefinitionContext> pageDefinition() {
            return getRuleContexts(PageDefinitionContext.class);
        }

        public PageDefinitionContext pageDefinition(int i) {
            return getRuleContext(PageDefinitionContext.class, i);
        }

        public List<DefaultDefinitionContext> defaultDefinition() {
            return getRuleContexts(DefaultDefinitionContext.class);
        }

        public DefaultDefinitionContext defaultDefinition(int i) {
            return getRuleContext(DefaultDefinitionContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_stylesheet;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterStylesheet(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitStylesheet(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLSVisitor) return ((QLSVisitor<? extends T>) visitor).visitStylesheet(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class PageDefinitionContext extends ParserRuleContext {
        public Token name;

        public PageDefinitionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode BEGIN() {
            return getToken(QLSParser.BEGIN, 0);
        }

        public TerminalNode END() {
            return getToken(QLSParser.END, 0);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(QLSParser.IDENTIFIER, 0);
        }

        public List<BlockElementContext> blockElement() {
            return getRuleContexts(BlockElementContext.class);
        }

        public BlockElementContext blockElement(int i) {
            return getRuleContext(BlockElementContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_pageDefinition;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterPageDefinition(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitPageDefinition(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLSVisitor) return ((QLSVisitor<? extends T>) visitor).visitPageDefinition(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class BlockElementContext extends ParserRuleContext {
        public BlockElementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public QuestionDefinitionContext questionDefinition() {
            return getRuleContext(QuestionDefinitionContext.class, 0);
        }

        public SectionContext section() {
            return getRuleContext(SectionContext.class, 0);
        }

        public DefaultDefinitionContext defaultDefinition() {
            return getRuleContext(DefaultDefinitionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_blockElement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterBlockElement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitBlockElement(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLSVisitor) return ((QLSVisitor<? extends T>) visitor).visitBlockElement(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class SectionContext extends ParserRuleContext {
        public Token name;

        public SectionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(QLSParser.STRING, 0);
        }

        public TerminalNode BEGIN() {
            return getToken(QLSParser.BEGIN, 0);
        }

        public TerminalNode END() {
            return getToken(QLSParser.END, 0);
        }

        public QuestionDefinitionContext questionDefinition() {
            return getRuleContext(QuestionDefinitionContext.class, 0);
        }

        public List<BlockElementContext> blockElement() {
            return getRuleContexts(BlockElementContext.class);
        }

        public BlockElementContext blockElement(int i) {
            return getRuleContext(BlockElementContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_section;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterSection(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitSection(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLSVisitor) return ((QLSVisitor<? extends T>) visitor).visitSection(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class QuestionDefinitionContext extends ParserRuleContext {
        public Token name;

        public QuestionDefinitionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(QLSParser.IDENTIFIER, 0);
        }

        public WidgetDefinitionContext widgetDefinition() {
            return getRuleContext(WidgetDefinitionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_questionDefinition;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterQuestionDefinition(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitQuestionDefinition(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLSVisitor) return ((QLSVisitor<? extends T>) visitor).visitQuestionDefinition(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class DefaultDefinitionContext extends ParserRuleContext {
        public DataTypeContext type;

        public DefaultDefinitionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public DataTypeContext dataType() {
            return getRuleContext(DataTypeContext.class, 0);
        }

        public WidgetDefinitionContext widgetDefinition() {
            return getRuleContext(WidgetDefinitionContext.class, 0);
        }

        public TerminalNode BEGIN() {
            return getToken(QLSParser.BEGIN, 0);
        }

        public TerminalNode END() {
            return getToken(QLSParser.END, 0);
        }

        public List<TypeDefinitionPropertyContext> typeDefinitionProperty() {
            return getRuleContexts(TypeDefinitionPropertyContext.class);
        }

        public TypeDefinitionPropertyContext typeDefinitionProperty(int i) {
            return getRuleContext(TypeDefinitionPropertyContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_defaultDefinition;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterDefaultDefinition(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitDefaultDefinition(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLSVisitor) return ((QLSVisitor<? extends T>) visitor).visitDefaultDefinition(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class TypeDefinitionPropertyContext extends ParserRuleContext {
        public Token width;
        public Token font;
        public Token fontSize;
        public Token color;

        public TypeDefinitionPropertyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode INTEGER() {
            return getToken(QLSParser.INTEGER, 0);
        }

        public TerminalNode STRING() {
            return getToken(QLSParser.STRING, 0);
        }

        public TerminalNode HEX_VALUE() {
            return getToken(QLSParser.HEX_VALUE, 0);
        }

        public WidgetDefinitionContext widgetDefinition() {
            return getRuleContext(WidgetDefinitionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeDefinitionProperty;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterTypeDefinitionProperty(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitTypeDefinitionProperty(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLSVisitor)
                return ((QLSVisitor<? extends T>) visitor).visitTypeDefinitionProperty(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class DataTypeContext extends ParserRuleContext {
        public DataTypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode TYPE_BOOLEAN() {
            return getToken(QLSParser.TYPE_BOOLEAN, 0);
        }

        public TerminalNode TYPE_STRING() {
            return getToken(QLSParser.TYPE_STRING, 0);
        }

        public TerminalNode TYPE_INTEGER() {
            return getToken(QLSParser.TYPE_INTEGER, 0);
        }

        public TerminalNode TYPE_DECIMAL() {
            return getToken(QLSParser.TYPE_DECIMAL, 0);
        }

        public TerminalNode TYPE_MONEY() {
            return getToken(QLSParser.TYPE_MONEY, 0);
        }

        public TerminalNode TYPE_DATE() {
            return getToken(QLSParser.TYPE_DATE, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_dataType;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterDataType(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitDataType(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLSVisitor) return ((QLSVisitor<? extends T>) visitor).visitDataType(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class WidgetDefinitionContext extends ParserRuleContext {
        public WidgetDefinitionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public WidgetContext widget() {
            return getRuleContext(WidgetContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_widgetDefinition;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterWidgetDefinition(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitWidgetDefinition(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLSVisitor) return ((QLSVisitor<? extends T>) visitor).visitWidgetDefinition(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class WidgetContext extends ParserRuleContext {
        public WidgetContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode WIDGET_BOOL_CHECKBOX() {
            return getToken(QLSParser.WIDGET_BOOL_CHECKBOX, 0);
        }

        public BooleanParametersContext booleanParameters() {
            return getRuleContext(BooleanParametersContext.class, 0);
        }

        public TerminalNode WIDGET_BOOL_DROPDOWN() {
            return getToken(QLSParser.WIDGET_BOOL_DROPDOWN, 0);
        }

        public TerminalNode WIDGET_BOOL_RADIO() {
            return getToken(QLSParser.WIDGET_BOOL_RADIO, 0);
        }

        public TerminalNode WIDGET_INTEGER_SPINBOX() {
            return getToken(QLSParser.WIDGET_INTEGER_SPINBOX, 0);
        }

        public IntegerParametersContext integerParameters() {
            return getRuleContext(IntegerParametersContext.class, 0);
        }

        public TerminalNode WIDGET_INTEGER_SLIDER() {
            return getToken(QLSParser.WIDGET_INTEGER_SLIDER, 0);
        }

        public TerminalNode WIDGET_TEXT() {
            return getToken(QLSParser.WIDGET_TEXT, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_widget;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterWidget(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitWidget(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLSVisitor) return ((QLSVisitor<? extends T>) visitor).visitWidget(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class BooleanParametersContext extends ParserRuleContext {
        public Token trueValue;
        public Token falseValue;

        public BooleanParametersContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OPEN_PARENTHESIS() {
            return getToken(QLSParser.OPEN_PARENTHESIS, 0);
        }

        public TerminalNode CLOSE_PARENTHESIS() {
            return getToken(QLSParser.CLOSE_PARENTHESIS, 0);
        }

        public List<TerminalNode> STRING() {
            return getTokens(QLSParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(QLSParser.STRING, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_booleanParameters;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterBooleanParameters(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitBooleanParameters(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLSVisitor) return ((QLSVisitor<? extends T>) visitor).visitBooleanParameters(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class IntegerParametersContext extends ParserRuleContext {
        public Token min;
        public Token max;
        public Token step;

        public IntegerParametersContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OPEN_PARENTHESIS() {
            return getToken(QLSParser.OPEN_PARENTHESIS, 0);
        }

        public TerminalNode CLOSE_PARENTHESIS() {
            return getToken(QLSParser.CLOSE_PARENTHESIS, 0);
        }

        public List<TerminalNode> INTEGER() {
            return getTokens(QLSParser.INTEGER);
        }

        public TerminalNode INTEGER(int i) {
            return getToken(QLSParser.INTEGER, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_integerParameters;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterIntegerParameters(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitIntegerParameters(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLSVisitor) return ((QLSVisitor<? extends T>) visitor).visitIntegerParameters(this);
            else return visitor.visitChildren(this);
        }
    }
}