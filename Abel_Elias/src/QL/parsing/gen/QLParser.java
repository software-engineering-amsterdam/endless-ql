// Generated from C:/dev/uva/endless-ql/Abel_Elias/src/QL/parsing\QL.g4 by ANTLR 4.7
package QL.parsing.gen;

import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
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
public class QLParser extends Parser {
    public static final int
            WS = 1, COMMENT = 2, LINE_COMMENT = 3, BOOLEANTYPE = 4, STRINGTYPE = 5, INTEGERTYPE = 6,
            MONEYTYPE = 7, DATETYPE = 8, DECIMALTYPE = 9, FORM = 10, IF = 11, ELSE = 12, COLON = 13,
            CURLY_BRACE_L = 14, CURLY_BRACE_R = 15, BRACE_L = 16, BRACE_R = 17, ADD = 18, SUB = 19,
            MUL = 20, DIV = 21, REM = 22, EQT = 23, GRT = 24, LST = 25, GRTE = 26, LSTE = 27, AND = 28,
            NEQT = 29, OR = 30, NOT = 31, BOOL = 32, IDENTIFIER = 33, STR = 34, INT = 35, MON = 36,
            DEC = 37, NEWLINE = 38;
    public static final int
            RULE_form = 0, RULE_block = 1, RULE_lineInBlock = 2, RULE_question = 3,
            RULE_expression = 4, RULE_boolOperator = 5, RULE_comparisonOperator = 6,
            RULE_equalsOperator = 7, RULE_numberOperator = 8, RULE_ifStatement = 9,
            RULE_type = 10;
    public static final String[] ruleNames = {
            "form", "block", "lineInBlock", "question", "expression", "boolOperator",
            "comparisonOperator", "equalsOperator", "numberOperator", "ifStatement",
            "type"
    };
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(\u008c\4\2\t\2\4" +
                    "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
                    "\13\4\f\t\f\3\2\3\2\3\2\3\2\3\2\3\3\3\3\7\3 \n\3\f\3\16\3#\13\3\3\3\7" +
                    "\3&\n\3\f\3\16\3)\13\3\3\3\3\3\7\3-\n\3\f\3\16\3\60\13\3\3\4\3\4\7\4\64" +
                    "\n\4\f\4\16\4\67\13\4\3\4\3\4\7\4;\n\4\f\4\16\4>\13\4\5\4@\n\4\3\5\3\5" +
                    "\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5L\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6" +
                    "\3\6\3\6\3\6\3\6\5\6Y\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6" +
                    "\3\6\3\6\3\6\3\6\3\6\7\6k\n\6\f\6\16\6n\13\6\3\7\3\7\3\b\3\b\3\t\3\t\3" +
                    "\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u0080\n\13\5\13\u0082" +
                    "\n\13\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u008a\n\f\3\f\2\3\n\r\2\4\6\b\n\f\16" +
                    "\20\22\24\26\2\7\3\2%\'\4\2\36\36  \3\2\32\35\4\2\31\31\37\37\3\2\24\30" +
                    "\2\u0097\2\30\3\2\2\2\4\35\3\2\2\2\6?\3\2\2\2\bK\3\2\2\2\nX\3\2\2\2\f" +
                    "o\3\2\2\2\16q\3\2\2\2\20s\3\2\2\2\22u\3\2\2\2\24w\3\2\2\2\26\u0089\3\2" +
                    "\2\2\30\31\7\f\2\2\31\32\7#\2\2\32\33\5\4\3\2\33\34\7\2\2\3\34\3\3\2\2" +
                    "\2\35!\7\20\2\2\36 \7(\2\2\37\36\3\2\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2" +
                    "\2\2\"\'\3\2\2\2#!\3\2\2\2$&\5\6\4\2%$\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'" +
                    "(\3\2\2\2(*\3\2\2\2)\'\3\2\2\2*.\7\21\2\2+-\7(\2\2,+\3\2\2\2-\60\3\2\2" +
                    "\2.,\3\2\2\2./\3\2\2\2/\5\3\2\2\2\60.\3\2\2\2\61\65\5\24\13\2\62\64\7" +
                    "(\2\2\63\62\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66@\3\2" +
                    "\2\2\67\65\3\2\2\28<\5\b\5\29;\7(\2\2:9\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=" +
                    "\3\2\2\2=@\3\2\2\2><\3\2\2\2?\61\3\2\2\2?8\3\2\2\2@\7\3\2\2\2AB\7#\2\2" +
                    "BC\7\17\2\2CD\7$\2\2DL\5\26\f\2EF\7#\2\2FG\7\17\2\2GH\7$\2\2HI\5\26\f" +
                    "\2IJ\5\n\6\2JL\3\2\2\2KA\3\2\2\2KE\3\2\2\2L\t\3\2\2\2MN\b\6\1\2NY\7#\2" +
                    "\2OP\7\22\2\2PQ\5\n\6\2QR\7\23\2\2RY\3\2\2\2ST\7!\2\2TY\5\n\6\6UY\7$\2" +
                    "\2VY\7\"\2\2WY\t\2\2\2XM\3\2\2\2XO\3\2\2\2XS\3\2\2\2XU\3\2\2\2XV\3\2\2" +
                    "\2XW\3\2\2\2Yl\3\2\2\2Z[\f\n\2\2[\\\5\f\7\2\\]\5\n\6\13]k\3\2\2\2^_\f" +
                    "\t\2\2_`\5\16\b\2`a\5\n\6\nak\3\2\2\2bc\f\b\2\2cd\5\20\t\2de\5\n\6\te" +
                    "k\3\2\2\2fg\f\7\2\2gh\5\22\n\2hi\5\n\6\bik\3\2\2\2jZ\3\2\2\2j^\3\2\2\2" +
                    "jb\3\2\2\2jf\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2m\13\3\2\2\2nl\3\2\2" +
                    "\2op\t\3\2\2p\r\3\2\2\2qr\t\4\2\2r\17\3\2\2\2st\t\5\2\2t\21\3\2\2\2uv" +
                    "\t\6\2\2v\23\3\2\2\2wx\7\r\2\2xy\7\22\2\2yz\5\n\6\2z{\7\23\2\2{\u0081" +
                    "\5\4\3\2|\177\7\16\2\2}\u0080\5\24\13\2~\u0080\5\4\3\2\177}\3\2\2\2\177" +
                    "~\3\2\2\2\u0080\u0082\3\2\2\2\u0081|\3\2\2\2\u0081\u0082\3\2\2\2\u0082" +
                    "\25\3\2\2\2\u0083\u008a\7\6\2\2\u0084\u008a\7\7\2\2\u0085\u008a\7\b\2" +
                    "\2\u0086\u008a\7\t\2\2\u0087\u008a\7\n\2\2\u0088\u008a\7\13\2\2\u0089" +
                    "\u0083\3\2\2\2\u0089\u0084\3\2\2\2\u0089\u0085\3\2\2\2\u0089\u0086\3\2" +
                    "\2\2\u0089\u0087\3\2\2\2\u0089\u0088\3\2\2\2\u008a\27\3\2\2\2\17!\'.\65" +
                    "<?KXjl\177\u0081\u0089";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = {
            null, null, null, null, "'boolean'", "'string'", "'integer'", "'money'",
            "'date'", "'decimal'", "'form'", "'if'", "'else'", "':'", "'{'", "'}'",
            "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'%'", "'=='", "'>'", "'<'",
            "'>='", "'<='", "'&&'", "'!='", "'||'", "'!'"
    };
    private static final String[] _SYMBOLIC_NAMES = {
            null, "WS", "COMMENT", "LINE_COMMENT", "BOOLEANTYPE", "STRINGTYPE", "INTEGERTYPE",
            "MONEYTYPE", "DATETYPE", "DECIMALTYPE", "FORM", "IF", "ELSE", "COLON",
            "CURLY_BRACE_L", "CURLY_BRACE_R", "BRACE_L", "BRACE_R", "ADD", "SUB",
            "MUL", "DIV", "REM", "EQT", "GRT", "LST", "GRTE", "LSTE", "AND", "NEQT",
            "OR", "NOT", "BOOL", "IDENTIFIER", "STR", "INT", "MON", "DEC", "NEWLINE"
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

    public QLParser(TokenStream input) {
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
        return "QL.g4";
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

    public final FormContext form() throws RecognitionException {
        FormContext _localctx = new FormContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_form);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(22);
                match(FORM);
                setState(23);
                match(IDENTIFIER);
                setState(24);
                block();
                setState(25);
                match(EOF);
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

    public final BlockContext block() throws RecognitionException {
        BlockContext _localctx = new BlockContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_block);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(27);
                match(CURLY_BRACE_L);
                setState(31);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == NEWLINE) {
                    {
                        {
                            setState(28);
                            match(NEWLINE);
                        }
                    }
                    setState(33);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(37);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == IF || _la == IDENTIFIER) {
                    {
                        {
                            setState(34);
                            lineInBlock();
                        }
                    }
                    setState(39);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(40);
                match(CURLY_BRACE_R);
                setState(44);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(41);
                                match(NEWLINE);
                            }
                        }
                    }
                    setState(46);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
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

    public final LineInBlockContext lineInBlock() throws RecognitionException {
        LineInBlockContext _localctx = new LineInBlockContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_lineInBlock);
        int _la;
        try {
            setState(61);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case IF:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(47);
                    ifStatement();
                    setState(51);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == NEWLINE) {
                        {
                            {
                                setState(48);
                                match(NEWLINE);
                            }
                        }
                        setState(53);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                }
                break;
                case IDENTIFIER:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(54);
                    question();
                    setState(58);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == NEWLINE) {
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

    public final QuestionContext question() throws RecognitionException {
        QuestionContext _localctx = new QuestionContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_question);
        try {
            setState(73);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 6, _ctx)) {
                case 1:
                    _localctx = new NormalQuestionContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(63);
                    match(IDENTIFIER);
                    setState(64);
                    match(COLON);
                    setState(65);
                    match(STR);
                    setState(66);
                    type();
                }
                break;
                case 2:
                    _localctx = new FixedQuestionContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(67);
                    match(IDENTIFIER);
                    setState(68);
                    match(COLON);
                    setState(69);
                    match(STR);
                    setState(70);
                    type();
                    setState(71);
                    expression(0);
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

    public final ExpressionContext expression() throws RecognitionException {
        return expression(0);
    }

    private ExpressionContext expression(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
        ExpressionContext _prevctx = _localctx;
        int _startState = 8;
        enterRecursionRule(_localctx, 8, RULE_expression, _p);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(86);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case IDENTIFIER: {
                        _localctx = new IdentifierContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;

                        setState(76);
                        match(IDENTIFIER);
                    }
                    break;
                    case BRACE_L: {
                        _localctx = new BraceExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(77);
                        match(BRACE_L);
                        setState(78);
                        expression(0);
                        setState(79);
                        match(BRACE_R);
                    }
                    break;
                    case NOT: {
                        _localctx = new NotExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(81);
                        match(NOT);
                        setState(82);
                        expression(4);
                    }
                    break;
                    case STR: {
                        _localctx = new StrValueContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(83);
                        match(STR);
                    }
                    break;
                    case BOOL: {
                        _localctx = new BoolValueContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(84);
                        match(BOOL);
                    }
                    break;
                    case INT:
                    case MON:
                    case DEC: {
                        _localctx = new NumValueContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(85);
                        _la = _input.LA(1);
                        if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << MON) | (1L << DEC))) != 0))) {
                            _errHandler.recoverInline(this);
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
                _ctx.stop = _input.LT(-1);
                setState(106);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 9, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            setState(104);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 8, _ctx)) {
                                case 1: {
                                    _localctx = new BoolExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    ((BoolExpressionContext) _localctx).left = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(88);
                                    if (!(precpred(_ctx, 8)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 8)");
                                    setState(89);
                                    boolOperator();
                                    setState(90);
                                    ((BoolExpressionContext) _localctx).right = expression(9);
                                }
                                break;
                                case 2: {
                                    _localctx = new CompExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    ((CompExpressionContext) _localctx).left = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(92);
                                    if (!(precpred(_ctx, 7)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 7)");
                                    setState(93);
                                    comparisonOperator();
                                    setState(94);
                                    ((CompExpressionContext) _localctx).right = expression(8);
                                }
                                break;
                                case 3: {
                                    _localctx = new EqExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    ((EqExpressionContext) _localctx).left = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(96);
                                    if (!(precpred(_ctx, 6)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 6)");
                                    setState(97);
                                    equalsOperator();
                                    setState(98);
                                    ((EqExpressionContext) _localctx).right = expression(7);
                                }
                                break;
                                case 4: {
                                    _localctx = new NumExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    ((NumExpressionContext) _localctx).left = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(100);
                                    if (!(precpred(_ctx, 5)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                                    setState(101);
                                    numberOperator();
                                    setState(102);
                                    ((NumExpressionContext) _localctx).right = expression(6);
                                }
                                break;
                            }
                        }
                    }
                    setState(108);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 9, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public final BoolOperatorContext boolOperator() throws RecognitionException {
        BoolOperatorContext _localctx = new BoolOperatorContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_boolOperator);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(109);
                _la = _input.LA(1);
                if (!(_la == AND || _la == OR)) {
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

    public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
        ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_comparisonOperator);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(111);
                _la = _input.LA(1);
                if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GRT) | (1L << LST) | (1L << GRTE) | (1L << LSTE))) != 0))) {
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

    public final EqualsOperatorContext equalsOperator() throws RecognitionException {
        EqualsOperatorContext _localctx = new EqualsOperatorContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_equalsOperator);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(113);
                _la = _input.LA(1);
                if (!(_la == EQT || _la == NEQT)) {
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

    public final NumberOperatorContext numberOperator() throws RecognitionException {
        NumberOperatorContext _localctx = new NumberOperatorContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_numberOperator);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(115);
                _la = _input.LA(1);
                if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << SUB) | (1L << MUL) | (1L << DIV) | (1L << REM))) != 0))) {
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

    public final IfStatementContext ifStatement() throws RecognitionException {
        IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_ifStatement);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(117);
                match(IF);
                setState(118);
                match(BRACE_L);
                setState(119);
                expression(0);
                setState(120);
                match(BRACE_R);
                setState(121);
                ((IfStatementContext) _localctx).ifBlock = block();
                setState(127);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == ELSE) {
                    {
                        setState(122);
                        match(ELSE);
                        setState(125);
                        _errHandler.sync(this);
                        switch (_input.LA(1)) {
                            case IF: {
                                setState(123);
                                ifStatement();
                            }
                            break;
                            case CURLY_BRACE_L: {
                                setState(124);
                                ((IfStatementContext) _localctx).elseBlock = block();
                            }
                            break;
                            default:
                                throw new NoViableAltException(this);
                        }
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

    public final TypeContext type() throws RecognitionException {
        TypeContext _localctx = new TypeContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_type);
        try {
            setState(135);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case BOOLEANTYPE:
                    _localctx = new BooltypeContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(129);
                    match(BOOLEANTYPE);
                }
                break;
                case STRINGTYPE:
                    _localctx = new StringtypeContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(130);
                    match(STRINGTYPE);
                }
                break;
                case INTEGERTYPE:
                    _localctx = new IntegertypeContext(_localctx);
                    enterOuterAlt(_localctx, 3);
                {
                    setState(131);
                    match(INTEGERTYPE);
                }
                break;
                case MONEYTYPE:
                    _localctx = new MoneytypeContext(_localctx);
                    enterOuterAlt(_localctx, 4);
                {
                    setState(132);
                    match(MONEYTYPE);
                }
                break;
                case DATETYPE:
                    _localctx = new DatetypeContext(_localctx);
                    enterOuterAlt(_localctx, 5);
                {
                    setState(133);
                    match(DATETYPE);
                }
                break;
                case DECIMALTYPE:
                    _localctx = new DecimaltypeContext(_localctx);
                    enterOuterAlt(_localctx, 6);
                {
                    setState(134);
                    match(DECIMALTYPE);
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

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch (ruleIndex) {
            case 4:
                return expression_sempred((ExpressionContext) _localctx, predIndex);
        }
        return true;
    }

    private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
        switch (predIndex) {
            case 0:
                return precpred(_ctx, 8);
            case 1:
                return precpred(_ctx, 7);
            case 2:
                return precpred(_ctx, 6);
            case 3:
                return precpred(_ctx, 5);
        }
        return true;
    }

    public static class FormContext extends ParserRuleContext {
        public FormContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode FORM() {
            return getToken(QLParser.FORM, 0);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(QLParser.IDENTIFIER, 0);
        }

        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        public TerminalNode EOF() {
            return getToken(QLParser.EOF, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_form;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterForm(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitForm(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitForm(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class BlockContext extends ParserRuleContext {
        public BlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode CURLY_BRACE_L() {
            return getToken(QLParser.CURLY_BRACE_L, 0);
        }

        public TerminalNode CURLY_BRACE_R() {
            return getToken(QLParser.CURLY_BRACE_R, 0);
        }

        public List<TerminalNode> NEWLINE() {
            return getTokens(QLParser.NEWLINE);
        }

        public TerminalNode NEWLINE(int i) {
            return getToken(QLParser.NEWLINE, i);
        }

        public List<LineInBlockContext> lineInBlock() {
            return getRuleContexts(LineInBlockContext.class);
        }

        public LineInBlockContext lineInBlock(int i) {
            return getRuleContext(LineInBlockContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_block;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterBlock(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitBlock(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitBlock(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class LineInBlockContext extends ParserRuleContext {
        public LineInBlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public IfStatementContext ifStatement() {
            return getRuleContext(IfStatementContext.class, 0);
        }

        public List<TerminalNode> NEWLINE() {
            return getTokens(QLParser.NEWLINE);
        }

        public TerminalNode NEWLINE(int i) {
            return getToken(QLParser.NEWLINE, i);
        }

        public QuestionContext question() {
            return getRuleContext(QuestionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_lineInBlock;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterLineInBlock(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitLineInBlock(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitLineInBlock(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class QuestionContext extends ParserRuleContext {
        public QuestionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public QuestionContext() {
        }

        @Override
        public int getRuleIndex() {
            return RULE_question;
        }

        public void copyFrom(QuestionContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class NormalQuestionContext extends QuestionContext {
        public NormalQuestionContext(QuestionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(QLParser.IDENTIFIER, 0);
        }

        public TerminalNode COLON() {
            return getToken(QLParser.COLON, 0);
        }

        public TerminalNode STR() {
            return getToken(QLParser.STR, 0);
        }

        public TypeContext type() {
            return getRuleContext(TypeContext.class, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterNormalQuestion(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitNormalQuestion(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitNormalQuestion(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class FixedQuestionContext extends QuestionContext {
        public FixedQuestionContext(QuestionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(QLParser.IDENTIFIER, 0);
        }

        public TerminalNode COLON() {
            return getToken(QLParser.COLON, 0);
        }

        public TerminalNode STR() {
            return getToken(QLParser.STR, 0);
        }

        public TypeContext type() {
            return getRuleContext(TypeContext.class, 0);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterFixedQuestion(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitFixedQuestion(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitFixedQuestion(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class ExpressionContext extends ParserRuleContext {
        public ExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ExpressionContext() {
        }

        @Override
        public int getRuleIndex() {
            return RULE_expression;
        }

        public void copyFrom(ExpressionContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class IdentifierContext extends ExpressionContext {
        public IdentifierContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(QLParser.IDENTIFIER, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterIdentifier(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitIdentifier(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitIdentifier(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class EqExpressionContext extends ExpressionContext {
        public ExpressionContext left;
        public ExpressionContext right;

        public EqExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public EqualsOperatorContext equalsOperator() {
            return getRuleContext(EqualsOperatorContext.class, 0);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterEqExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitEqExpression(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitEqExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class BoolExpressionContext extends ExpressionContext {
        public ExpressionContext left;
        public ExpressionContext right;

        public BoolExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public BoolOperatorContext boolOperator() {
            return getRuleContext(BoolOperatorContext.class, 0);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterBoolExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitBoolExpression(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitBoolExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class CompExpressionContext extends ExpressionContext {
        public ExpressionContext left;
        public ExpressionContext right;

        public CompExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public ComparisonOperatorContext comparisonOperator() {
            return getRuleContext(ComparisonOperatorContext.class, 0);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterCompExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitCompExpression(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitCompExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class NumExpressionContext extends ExpressionContext {
        public ExpressionContext left;
        public ExpressionContext right;

        public NumExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public NumberOperatorContext numberOperator() {
            return getRuleContext(NumberOperatorContext.class, 0);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterNumExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitNumExpression(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitNumExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class BraceExpressionContext extends ExpressionContext {
        public BraceExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode BRACE_L() {
            return getToken(QLParser.BRACE_L, 0);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public TerminalNode BRACE_R() {
            return getToken(QLParser.BRACE_R, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterBraceExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitBraceExpression(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitBraceExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class NotExpressionContext extends ExpressionContext {
        public NotExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode NOT() {
            return getToken(QLParser.NOT, 0);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterNotExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitNotExpression(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitNotExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class BoolValueContext extends ExpressionContext {
        public BoolValueContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode BOOL() {
            return getToken(QLParser.BOOL, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterBoolValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitBoolValue(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitBoolValue(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class StrValueContext extends ExpressionContext {
        public StrValueContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode STR() {
            return getToken(QLParser.STR, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterStrValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitStrValue(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitStrValue(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class NumValueContext extends ExpressionContext {
        public NumValueContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode MON() {
            return getToken(QLParser.MON, 0);
        }

        public TerminalNode INT() {
            return getToken(QLParser.INT, 0);
        }

        public TerminalNode DEC() {
            return getToken(QLParser.DEC, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterNumValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitNumValue(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitNumValue(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class BoolOperatorContext extends ParserRuleContext {
        public BoolOperatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode AND() {
            return getToken(QLParser.AND, 0);
        }

        public TerminalNode OR() {
            return getToken(QLParser.OR, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_boolOperator;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterBoolOperator(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitBoolOperator(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitBoolOperator(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class ComparisonOperatorContext extends ParserRuleContext {
        public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode GRT() {
            return getToken(QLParser.GRT, 0);
        }

        public TerminalNode LST() {
            return getToken(QLParser.LST, 0);
        }

        public TerminalNode GRTE() {
            return getToken(QLParser.GRTE, 0);
        }

        public TerminalNode LSTE() {
            return getToken(QLParser.LSTE, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_comparisonOperator;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterComparisonOperator(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitComparisonOperator(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitComparisonOperator(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class EqualsOperatorContext extends ParserRuleContext {
        public EqualsOperatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode EQT() {
            return getToken(QLParser.EQT, 0);
        }

        public TerminalNode NEQT() {
            return getToken(QLParser.NEQT, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_equalsOperator;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterEqualsOperator(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitEqualsOperator(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitEqualsOperator(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class NumberOperatorContext extends ParserRuleContext {
        public NumberOperatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode ADD() {
            return getToken(QLParser.ADD, 0);
        }

        public TerminalNode SUB() {
            return getToken(QLParser.SUB, 0);
        }

        public TerminalNode MUL() {
            return getToken(QLParser.MUL, 0);
        }

        public TerminalNode DIV() {
            return getToken(QLParser.DIV, 0);
        }

        public TerminalNode REM() {
            return getToken(QLParser.REM, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_numberOperator;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterNumberOperator(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitNumberOperator(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitNumberOperator(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class IfStatementContext extends ParserRuleContext {
        public BlockContext ifBlock;
        public BlockContext elseBlock;

        public IfStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode IF() {
            return getToken(QLParser.IF, 0);
        }

        public TerminalNode BRACE_L() {
            return getToken(QLParser.BRACE_L, 0);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public TerminalNode BRACE_R() {
            return getToken(QLParser.BRACE_R, 0);
        }

        public List<BlockContext> block() {
            return getRuleContexts(BlockContext.class);
        }

        public BlockContext block(int i) {
            return getRuleContext(BlockContext.class, i);
        }

        public TerminalNode ELSE() {
            return getToken(QLParser.ELSE, 0);
        }

        public IfStatementContext ifStatement() {
            return getRuleContext(IfStatementContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_ifStatement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterIfStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitIfStatement(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitIfStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class TypeContext extends ParserRuleContext {
        public TypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TypeContext() {
        }

        @Override
        public int getRuleIndex() {
            return RULE_type;
        }

        public void copyFrom(TypeContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class StringtypeContext extends TypeContext {
        public StringtypeContext(TypeContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode STRINGTYPE() {
            return getToken(QLParser.STRINGTYPE, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterStringtype(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitStringtype(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitStringtype(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class DatetypeContext extends TypeContext {
        public DatetypeContext(TypeContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode DATETYPE() {
            return getToken(QLParser.DATETYPE, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterDatetype(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitDatetype(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitDatetype(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class IntegertypeContext extends TypeContext {
        public IntegertypeContext(TypeContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode INTEGERTYPE() {
            return getToken(QLParser.INTEGERTYPE, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterIntegertype(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitIntegertype(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitIntegertype(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class MoneytypeContext extends TypeContext {
        public MoneytypeContext(TypeContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode MONEYTYPE() {
            return getToken(QLParser.MONEYTYPE, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterMoneytype(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitMoneytype(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitMoneytype(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class DecimaltypeContext extends TypeContext {
        public DecimaltypeContext(TypeContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode DECIMALTYPE() {
            return getToken(QLParser.DECIMALTYPE, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterDecimaltype(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitDecimaltype(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitDecimaltype(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class BooltypeContext extends TypeContext {
        public BooltypeContext(TypeContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode BOOLEANTYPE() {
            return getToken(QLParser.BOOLEANTYPE, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).enterBooltype(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof QLListener) ((QLListener) listener).exitBooltype(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof QLVisitor) return ((QLVisitor<? extends T>) visitor).visitBooltype(this);
            else return visitor.visitChildren(this);
        }
    }
}