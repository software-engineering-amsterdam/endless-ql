// Generated from src/main/java/org/uva/sea/qls/parser/antlr/QLS.g by ANTLR 4.7.1

package org.uva.sea.qls.parser.antlr;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.uva.sea.qls.parser.elements.Page;
import org.uva.sea.qls.parser.elements.Parameter;
import org.uva.sea.qls.parser.elements.Stylesheet;
import org.uva.sea.qls.parser.elements.specification.DefaultStyle;
import org.uva.sea.qls.parser.elements.specification.Question;
import org.uva.sea.qls.parser.elements.specification.Section;
import org.uva.sea.qls.parser.elements.specification.Specification;
import org.uva.sea.qls.parser.elements.style.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLSParser extends Parser {
    public static final int
            T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
            T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17,
            IDENT = 18, NUM = 19, COLOR_CODE = 20, STR = 21, WS = 22, COMMENT = 23, SINGLE_COMMENT = 24;
    public static final int
            RULE_stylesheet = 0, RULE_pages = 1, RULE_page = 2, RULE_specifications = 3,
            RULE_specification = 4, RULE_section = 5, RULE_question = 6, RULE_widget = 7,
            RULE_parameters = 8, RULE_parameter = 9, RULE_defaultStyle = 10, RULE_styleSpecifications = 11,
            RULE_styleSpecification = 12, RULE_string = 13;
    public static final String[] ruleNames = {
            "stylesheet", "pages", "page", "specifications", "specification", "section",
            "questionData", "widget", "parameters", "parameter", "defaultStyle", "styleSpecifications",
            "styleSpecification", "string"
    };
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32\u00a1\4\2\t\2" +
                    "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
                    "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\2" +
                    "\3\3\3\3\3\3\7\3)\n\3\f\3\16\3,\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3" +
                    "\5\3\5\7\58\n\5\f\5\16\5;\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6" +
                    "F\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7T\n\7\3\b\3\b" +
                    "\3\b\5\bY\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\5\tc\n\t\3\t\3\t\3\n\3\n" +
                    "\3\n\5\nj\n\n\3\n\3\n\5\nn\n\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r" +
                    "\3\r\3\r\3\r\7\r|\n\r\f\r\16\r\177\13\r\3\r\3\r\3\r\3\r\5\r\u0085\n\r" +
                    "\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16" +
                    "\3\16\3\16\3\16\3\16\3\16\5\16\u009a\n\16\3\17\3\17\3\17\3\17\3\17\3\17" +
                    "\2\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\2\2\u00a1\2\36\3\2\2\2\4" +
                    "*\3\2\2\2\6-\3\2\2\2\b9\3\2\2\2\nE\3\2\2\2\fS\3\2\2\2\16U\3\2\2\2\20\\" +
                    "\3\2\2\2\22m\3\2\2\2\24o\3\2\2\2\26r\3\2\2\2\30\u0084\3\2\2\2\32\u0099" +
                    "\3\2\2\2\34\u009b\3\2\2\2\36\37\7\3\2\2\37 \7\24\2\2 !\7\4\2\2!\"\5\4" +
                    "\3\2\"#\7\5\2\2#$\b\2\1\2$\3\3\2\2\2%&\5\6\4\2&\'\b\3\1\2\')\3\2\2\2(" +
                    "%\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\5\3\2\2\2,*\3\2\2\2-.\7\6\2\2" +
                    "./\7\24\2\2/\60\7\4\2\2\60\61\5\b\5\2\61\62\7\5\2\2\62\63\b\4\1\2\63\7" +
                    "\3\2\2\2\64\65\5\n\6\2\65\66\b\5\1\2\668\3\2\2\2\67\64\3\2\2\28;\3\2\2" +
                    "\29\67\3\2\2\29:\3\2\2\2:\t\3\2\2\2;9\3\2\2\2<=\5\f\7\2=>\b\6\1\2>F\3" +
                    "\2\2\2?@\5\26\f\2@A\b\6\1\2AF\3\2\2\2BC\5\16\b\2CD\b\6\1\2DF\3\2\2\2E" +
                    "<\3\2\2\2E?\3\2\2\2EB\3\2\2\2F\13\3\2\2\2GH\7\7\2\2HI\7\27\2\2IJ\7\4\2" +
                    "\2JK\5\b\5\2KL\7\5\2\2LM\b\7\1\2MT\3\2\2\2NO\7\7\2\2OP\7\27\2\2PQ\5\n" +
                    "\6\2QR\b\7\1\2RT\3\2\2\2SG\3\2\2\2SN\3\2\2\2T\r\3\2\2\2UV\7\b\2\2VX\7" +
                    "\24\2\2WY\5\20\t\2XW\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z[\b\b\1\2[\17\3\2\2\2" +
                    "\\]\7\t\2\2]b\7\24\2\2^_\7\n\2\2_`\5\22\n\2`a\7\13\2\2ac\3\2\2\2b^\3\2" +
                    "\2\2bc\3\2\2\2cd\3\2\2\2de\b\t\1\2e\21\3\2\2\2fi\5\24\13\2gh\7\f\2\2h" +
                    "j\5\22\n\2ig\3\2\2\2ij\3\2\2\2jk\3\2\2\2kl\b\n\1\2ln\3\2\2\2mf\3\2\2\2" +
                    "mn\3\2\2\2n\23\3\2\2\2op\7\27\2\2pq\b\13\1\2q\25\3\2\2\2rs\7\r\2\2st\7" +
                    "\24\2\2tu\5\30\r\2uv\b\f\1\2v\27\3\2\2\2w}\7\4\2\2xy\5\32\16\2yz\b\r\1" +
                    "\2z|\3\2\2\2{x\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\u0080\3\2\2\2" +
                    "\177}\3\2\2\2\u0080\u0085\7\5\2\2\u0081\u0082\5\32\16\2\u0082\u0083\b" +
                    "\r\1\2\u0083\u0085\3\2\2\2\u0084w\3\2\2\2\u0084\u0081\3\2\2\2\u0085\31" +
                    "\3\2\2\2\u0086\u0087\7\16\2\2\u0087\u0088\7\17\2\2\u0088\u0089\7\25\2" +
                    "\2\u0089\u009a\b\16\1\2\u008a\u008b\7\20\2\2\u008b\u008c\7\17\2\2\u008c" +
                    "\u008d\7\27\2\2\u008d\u009a\b\16\1\2\u008e\u008f\7\21\2\2\u008f\u0090" +
                    "\7\17\2\2\u0090\u0091\7\25\2\2\u0091\u009a\b\16\1\2\u0092\u0093\7\22\2" +
                    "\2\u0093\u0094\7\17\2\2\u0094\u0095\7\26\2\2\u0095\u009a\b\16\1\2\u0096" +
                    "\u0097\5\20\t\2\u0097\u0098\b\16\1\2\u0098\u009a\3\2\2\2\u0099\u0086\3" +
                    "\2\2\2\u0099\u008a\3\2\2\2\u0099\u008e\3\2\2\2\u0099\u0092\3\2\2\2\u0099" +
                    "\u0096\3\2\2\2\u009a\33\3\2\2\2\u009b\u009c\7\23\2\2\u009c\u009d\7\24" +
                    "\2\2\u009d\u009e\7\23\2\2\u009e\u009f\b\17\1\2\u009f\35\3\2\2\2\r*9ES" +
                    "Xbim}\u0084\u0099";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = {
            null, "'stylesheet'", "'{'", "'}'", "'page'", "'section'", "'questionData'",
            "'widget'", "'('", "')'", "','", "'default'", "'width'", "':'", "'font'",
            "'fontsize'", "'color'", "'\"'"
    };
    private static final String[] _SYMBOLIC_NAMES = {
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, "IDENT", "NUM", "COLOR_CODE", "STR",
            "WS", "COMMENT", "SINGLE_COMMENT"
    };
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    static {
        RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION);
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
        return "QLS.g";
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
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(28);
                ((StylesheetContext) _localctx).f = match(T__0);
                setState(29);
                ((StylesheetContext) _localctx).IDENT = match(IDENT);
                setState(30);
                match(T__1);
                setState(31);
                ((StylesheetContext) _localctx).pages = pages();
                setState(32);
                match(T__2);

                ((StylesheetContext) _localctx).result = new Stylesheet(((StylesheetContext) _localctx).f, (((StylesheetContext) _localctx).IDENT != null ? ((StylesheetContext) _localctx).IDENT.getText() : null), ((StylesheetContext) _localctx).pages.result);

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

    public final PagesContext pages() throws RecognitionException {
        PagesContext _localctx = new PagesContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_pages);
        List<Page> pages = new ArrayList<>();
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(40);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__3) {
                    {
                        {
                            setState(35);
                            ((PagesContext) _localctx).page = page();

                            pages.add(((PagesContext) _localctx).page.result);

                        }
                    }
                    setState(42);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
            _ctx.stop = _input.LT(-1);
            ((PagesContext) _localctx).result = pages;
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final PageContext page() throws RecognitionException {
        PageContext _localctx = new PageContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_page);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(43);
                ((PageContext) _localctx).p = match(T__3);
                setState(44);
                ((PageContext) _localctx).IDENT = match(IDENT);
                setState(45);
                match(T__1);
                setState(46);
                ((PageContext) _localctx).specifications = specifications();
                setState(47);
                match(T__2);

                ((PageContext) _localctx).result = new Page(((PageContext) _localctx).p, (((PageContext) _localctx).IDENT != null ? ((PageContext) _localctx).IDENT.getText() : null), ((PageContext) _localctx).specifications.result);

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

    public final SpecificationsContext specifications() throws RecognitionException {
        SpecificationsContext _localctx = new SpecificationsContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_specifications);
        List<Specification> specifications = new ArrayList<>();
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(55);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__10))) != 0)) {
                    {
                        {
                            setState(50);
                            ((SpecificationsContext) _localctx).specification = specification();

                            specifications.add(((SpecificationsContext) _localctx).specification.result);

                        }
                    }
                    setState(57);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
            _ctx.stop = _input.LT(-1);
            ((SpecificationsContext) _localctx).result = specifications;
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final SpecificationContext specification() throws RecognitionException {
        SpecificationContext _localctx = new SpecificationContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_specification);
        try {
            setState(67);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__4:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(58);
                    ((SpecificationContext) _localctx).section = section();
                    ((SpecificationContext) _localctx).result = ((SpecificationContext) _localctx).section.result;
                }
                break;
                case T__10:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(61);
                    ((SpecificationContext) _localctx).defaultStyle = defaultStyle();
                    ((SpecificationContext) _localctx).result = ((SpecificationContext) _localctx).defaultStyle.result;
                }
                break;
                case T__5:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(64);
                    ((SpecificationContext) _localctx).question = question();
                    ((SpecificationContext) _localctx).result = ((SpecificationContext) _localctx).question.result;
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
        enterRule(_localctx, 10, RULE_section);
        try {
            setState(81);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 3, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(69);
                    ((SectionContext) _localctx).s = match(T__4);
                    setState(70);
                    ((SectionContext) _localctx).STR = match(STR);
                    setState(71);
                    match(T__1);
                    setState(72);
                    ((SectionContext) _localctx).specifications = specifications();
                    setState(73);
                    match(T__2);

                    ((SectionContext) _localctx).result = new Section(((SectionContext) _localctx).s, (((SectionContext) _localctx).STR != null ? ((SectionContext) _localctx).STR.getText() : null), ((SectionContext) _localctx).specifications.result);

                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(76);
                    ((SectionContext) _localctx).s = match(T__4);
                    setState(77);
                    ((SectionContext) _localctx).STR = match(STR);
                    setState(78);
                    ((SectionContext) _localctx).specification = specification();

                    List<Specification> specifications = new ArrayList<>();
                    specifications.add(((SectionContext) _localctx).specification.result);
                    ((SectionContext) _localctx).result = new Section(((SectionContext) _localctx).s, (((SectionContext) _localctx).STR != null ? ((SectionContext) _localctx).STR.getText() : null), specifications);

                }
                break;
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

    public final QuestionContext question() throws RecognitionException {
        QuestionContext _localctx = new QuestionContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_question);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(83);
                ((QuestionContext) _localctx).q = match(T__5);
                setState(84);
                ((QuestionContext) _localctx).name = match(IDENT);
                setState(86);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__6) {
                    {
                        setState(85);
                        ((QuestionContext) _localctx).widget = widget();
                    }
                }


                Widget widget = null;
                if ((((QuestionContext) _localctx).widget != null ? _input.getText(((QuestionContext) _localctx).widget.start, ((QuestionContext) _localctx).widget.stop) : null) != null) {
                    widget = ((QuestionContext) _localctx).widget.result;
                }

                ((QuestionContext) _localctx).result = new Question(((QuestionContext) _localctx).q, (((QuestionContext) _localctx).name != null ? ((QuestionContext) _localctx).name.getText() : null), widget);

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
        enterRule(_localctx, 14, RULE_widget);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(90);
                ((WidgetContext) _localctx).w = match(T__6);
                setState(91);
                ((WidgetContext) _localctx).name = match(IDENT);
                setState(96);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__7) {
                    {
                        setState(92);
                        match(T__7);
                        setState(93);
                        ((WidgetContext) _localctx).parameters = parameters();
                        setState(94);
                        match(T__8);
                    }
                }


                ((WidgetContext) _localctx).result = new Widget(((WidgetContext) _localctx).w, (((WidgetContext) _localctx).name != null ? ((WidgetContext) _localctx).name.getText() : null), (((WidgetContext) _localctx).parameters != null ? _input.getText(((WidgetContext) _localctx).parameters.start, ((WidgetContext) _localctx).parameters.stop) : null) != null ? ((WidgetContext) _localctx).parameters.result : new ArrayList<>());

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

    public final ParametersContext parameters() throws RecognitionException {
        ParametersContext _localctx = new ParametersContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_parameters);
        List<Parameter> parameters = new ArrayList<>();
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(107);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == STR) {
                    {
                        setState(100);
                        ((ParametersContext) _localctx).parameter = parameter();
                        setState(103);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la == T__9) {
                            {
                                setState(101);
                                match(T__9);
                                setState(102);
                                ((ParametersContext) _localctx).parameters = parameters();
                            }
                        }


                        parameters.add(((ParametersContext) _localctx).parameter.result);

                        if ((((ParametersContext) _localctx).parameters != null ? _input.getText(((ParametersContext) _localctx).parameters.start, ((ParametersContext) _localctx).parameters.stop) : null) != null) {
                            parameters.addAll(((ParametersContext) _localctx).parameters.result);
                        }

                    }
                }

            }
            _ctx.stop = _input.LT(-1);
            ((ParametersContext) _localctx).result = parameters;
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ParameterContext parameter() throws RecognitionException {
        ParameterContext _localctx = new ParameterContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_parameter);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(109);
                ((ParameterContext) _localctx).p = match(STR);

                ((ParameterContext) _localctx).result = new Parameter(((ParameterContext) _localctx).p, (((ParameterContext) _localctx).p != null ? ((ParameterContext) _localctx).p.getText() : null));

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

    public final DefaultStyleContext defaultStyle() throws RecognitionException {
        DefaultStyleContext _localctx = new DefaultStyleContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_defaultStyle);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(112);
                ((DefaultStyleContext) _localctx).d = match(T__10);
                setState(113);
                ((DefaultStyleContext) _localctx).type = match(IDENT);
                setState(114);
                ((DefaultStyleContext) _localctx).styleSpecifications = styleSpecifications();

                ((DefaultStyleContext) _localctx).result = new DefaultStyle(((DefaultStyleContext) _localctx).d, (((DefaultStyleContext) _localctx).type != null ? ((DefaultStyleContext) _localctx).type.getText() : null), ((DefaultStyleContext) _localctx).styleSpecifications.result);

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

    public final StyleSpecificationsContext styleSpecifications() throws RecognitionException {
        StyleSpecificationsContext _localctx = new StyleSpecificationsContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_styleSpecifications);
        List<StyleSpecification> specifications = new ArrayList<>();
        int _la;
        try {
            setState(130);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(117);
                    match(T__1);
                    setState(123);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__11) | (1L << T__13) | (1L << T__14) | (1L << T__15))) != 0)) {
                        {
                            {
                                setState(118);
                                ((StyleSpecificationsContext) _localctx).styleSpecification = styleSpecification();

                                specifications.add(((StyleSpecificationsContext) _localctx).styleSpecification.result);

                            }
                        }
                        setState(125);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(126);
                    match(T__2);
                }
                break;
                case T__6:
                case T__11:
                case T__13:
                case T__14:
                case T__15:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(127);
                    ((StyleSpecificationsContext) _localctx).styleSpecification = styleSpecification();
                    specifications.add(((StyleSpecificationsContext) _localctx).styleSpecification.result);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
            _ctx.stop = _input.LT(-1);
            ((StyleSpecificationsContext) _localctx).result = specifications;
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final StyleSpecificationContext styleSpecification() throws RecognitionException {
        StyleSpecificationContext _localctx = new StyleSpecificationContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_styleSpecification);
        try {
            setState(151);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__11:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(132);
                    ((StyleSpecificationContext) _localctx).s = match(T__11);
                    setState(133);
                    match(T__12);
                    setState(134);
                    ((StyleSpecificationContext) _localctx).NUM = match(NUM);
                    ((StyleSpecificationContext) _localctx).result = new Width(((StyleSpecificationContext) _localctx).s, (((StyleSpecificationContext) _localctx).NUM != null ? ((StyleSpecificationContext) _localctx).NUM.getText() : null));
                }
                break;
                case T__13:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(136);
                    ((StyleSpecificationContext) _localctx).s = match(T__13);
                    setState(137);
                    match(T__12);
                    setState(138);
                    ((StyleSpecificationContext) _localctx).STR = match(STR);
                    ((StyleSpecificationContext) _localctx).result = new Font(((StyleSpecificationContext) _localctx).s, (((StyleSpecificationContext) _localctx).STR != null ? ((StyleSpecificationContext) _localctx).STR.getText() : null));
                }
                break;
                case T__14:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(140);
                    ((StyleSpecificationContext) _localctx).s = match(T__14);
                    setState(141);
                    match(T__12);
                    setState(142);
                    ((StyleSpecificationContext) _localctx).NUM = match(NUM);
                    ((StyleSpecificationContext) _localctx).result = new FontSize(((StyleSpecificationContext) _localctx).s, (((StyleSpecificationContext) _localctx).NUM != null ? ((StyleSpecificationContext) _localctx).NUM.getText() : null));
                }
                break;
                case T__15:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(144);
                    ((StyleSpecificationContext) _localctx).s = match(T__15);
                    setState(145);
                    match(T__12);
                    setState(146);
                    ((StyleSpecificationContext) _localctx).COLOR_CODE = match(COLOR_CODE);
                    ((StyleSpecificationContext) _localctx).result = new Color(((StyleSpecificationContext) _localctx).s, (((StyleSpecificationContext) _localctx).COLOR_CODE != null ? ((StyleSpecificationContext) _localctx).COLOR_CODE.getText() : null));
                }
                break;
                case T__6:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(148);
                    ((StyleSpecificationContext) _localctx).widget = widget();
                    ((StyleSpecificationContext) _localctx).result = ((StyleSpecificationContext) _localctx).widget.result;
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

    public final StringContext string() throws RecognitionException {
        StringContext _localctx = new StringContext(_ctx, getState());
        enterRule(_localctx, 26, RULE_string);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(153);
                match(T__16);
                setState(154);
                ((StringContext) _localctx).IDENT = match(IDENT);
                setState(155);
                match(T__16);
                ((StringContext) _localctx).result = (((StringContext) _localctx).IDENT != null ? ((StringContext) _localctx).IDENT.getText() : null);
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
        public Stylesheet result;
        public Token f;
        public Token IDENT;
        public PagesContext pages;

        public StylesheetContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode IDENT() {
            return getToken(QLSParser.IDENT, 0);
        }

        public PagesContext pages() {
            return getRuleContext(PagesContext.class, 0);
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
    }

    public static class PagesContext extends ParserRuleContext {
        public List<Page> result;
        public PageContext page;

        public PagesContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<PageContext> page() {
            return getRuleContexts(PageContext.class);
        }

        public PageContext page(int i) {
            return getRuleContext(PageContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_pages;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterPages(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitPages(this);
        }
    }

    public static class PageContext extends ParserRuleContext {
        public Page result;
        public Token p;
        public Token IDENT;
        public SpecificationsContext specifications;

        public PageContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode IDENT() {
            return getToken(QLSParser.IDENT, 0);
        }

        public SpecificationsContext specifications() {
            return getRuleContext(SpecificationsContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_page;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterPage(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitPage(this);
        }
    }

    public static class SpecificationsContext extends ParserRuleContext {
        public List<Specification> result;
        public SpecificationContext specification;

        public SpecificationsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<SpecificationContext> specification() {
            return getRuleContexts(SpecificationContext.class);
        }

        public SpecificationContext specification(int i) {
            return getRuleContext(SpecificationContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_specifications;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterSpecifications(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitSpecifications(this);
        }
    }

    public static class SpecificationContext extends ParserRuleContext {
        public Specification result;
        public SectionContext section;
        public DefaultStyleContext defaultStyle;
        public QuestionContext question;

        public SpecificationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public SectionContext section() {
            return getRuleContext(SectionContext.class, 0);
        }

        public DefaultStyleContext defaultStyle() {
            return getRuleContext(DefaultStyleContext.class, 0);
        }

        public QuestionContext question() {
            return getRuleContext(QuestionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_specification;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterSpecification(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitSpecification(this);
        }
    }

    public static class SectionContext extends ParserRuleContext {
        public Section result;
        public Token s;
        public Token STR;
        public SpecificationsContext specifications;
        public SpecificationContext specification;

        public SectionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STR() {
            return getToken(QLSParser.STR, 0);
        }

        public SpecificationsContext specifications() {
            return getRuleContext(SpecificationsContext.class, 0);
        }

        public SpecificationContext specification() {
            return getRuleContext(SpecificationContext.class, 0);
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
    }

    public static class QuestionContext extends ParserRuleContext {
        public Question result;
        public Token q;
        public Token name;
        public WidgetContext widget;

        public QuestionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode IDENT() {
            return getToken(QLSParser.IDENT, 0);
        }

        public WidgetContext widget() {
            return getRuleContext(WidgetContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_question;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterQuestion(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitQuestion(this);
        }
    }

    public static class WidgetContext extends ParserRuleContext {
        public Widget result;
        public Token w;
        public Token name;
        public ParametersContext parameters;

        public WidgetContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode IDENT() {
            return getToken(QLSParser.IDENT, 0);
        }

        public ParametersContext parameters() {
            return getRuleContext(ParametersContext.class, 0);
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
    }

    public static class ParametersContext extends ParserRuleContext {
        public List<Parameter> result;
        public ParameterContext parameter;
        public ParametersContext parameters;

        public ParametersContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ParameterContext parameter() {
            return getRuleContext(ParameterContext.class, 0);
        }

        public ParametersContext parameters() {
            return getRuleContext(ParametersContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_parameters;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterParameters(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitParameters(this);
        }
    }

    public static class ParameterContext extends ParserRuleContext {
        public Parameter result;
        public Token p;

        public ParameterContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STR() {
            return getToken(QLSParser.STR, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_parameter;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterParameter(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitParameter(this);
        }
    }

    public static class DefaultStyleContext extends ParserRuleContext {
        public DefaultStyle result;
        public Token d;
        public Token type;
        public StyleSpecificationsContext styleSpecifications;

        public DefaultStyleContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public StyleSpecificationsContext styleSpecifications() {
            return getRuleContext(StyleSpecificationsContext.class, 0);
        }

        public TerminalNode IDENT() {
            return getToken(QLSParser.IDENT, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_defaultStyle;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterDefaultStyle(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitDefaultStyle(this);
        }
    }

    public static class StyleSpecificationsContext extends ParserRuleContext {
        public List<StyleSpecification> result;
        public StyleSpecificationContext styleSpecification;

        public StyleSpecificationsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<StyleSpecificationContext> styleSpecification() {
            return getRuleContexts(StyleSpecificationContext.class);
        }

        public StyleSpecificationContext styleSpecification(int i) {
            return getRuleContext(StyleSpecificationContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_styleSpecifications;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterStyleSpecifications(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitStyleSpecifications(this);
        }
    }

    public static class StyleSpecificationContext extends ParserRuleContext {
        public StyleSpecification result;
        public Token s;
        public Token NUM;
        public Token STR;
        public Token COLOR_CODE;
        public WidgetContext widget;

        public StyleSpecificationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode NUM() {
            return getToken(QLSParser.NUM, 0);
        }

        public TerminalNode STR() {
            return getToken(QLSParser.STR, 0);
        }

        public TerminalNode COLOR_CODE() {
            return getToken(QLSParser.COLOR_CODE, 0);
        }

        public WidgetContext widget() {
            return getRuleContext(WidgetContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_styleSpecification;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterStyleSpecification(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitStyleSpecification(this);
        }
    }

    public static class StringContext extends ParserRuleContext {
        public String result;
        public Token IDENT;

        public StringContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode IDENT() {
            return getToken(QLSParser.IDENT, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_string;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).enterString(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLSListener) ((QLSListener) listener).exitString(this);
        }
    }
}