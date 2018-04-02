grammar QLS;

stylesheet    : STYLESHEET name=IDENTIFIER CURLY_LEFT page+ defaultStyle* CURLY_RIGHT EOF ;

page          : PAGE name=IDENTIFIER CURLY_LEFT sections+=section+ defaultStyle* CURLY_RIGHT ;

section       : SECTION name=TEXT CURLY_LEFT statements+=statement+ defaultStyle* CURLY_RIGHT # MultiStatementSection
              | SECTION name=TEXT statement                                                   # SingleStatementSection ;

statement     : section  # SectionStatement
              | question # QuestionStatement ;

question      : QUESTION name=IDENTIFIER styling? ;

defaultStyle  : DEFAULT questionType styling ;

questionType  : BOOLEAN | STRING | DATE | INTEGER | DECIMAL | MONEY ;

styling       : style
              | CURLY_LEFT style+ CURLY_RIGHT ;

style         : WIDGET widgetType                            # WidgetStyling
              | WIDTH DOUBLE_COLON widthValue=INTEGER_VALUE  # WidthStyling
              | COLOR DOUBLE_COLON colorValue=HEX_VALUE      # ColorStyling
              | FONT DOUBLE_COLON fontType=TEXT              # FontTypeStyling
              | FONTSIZE DOUBLE_COLON fontSize=INTEGER_VALUE # FontSizeStyling;

widgetType    : CHECKBOX                                                                    # CheckBox
              | SPINBOX BRACKET_LEFT minimum=(DECIMAL_VALUE | INTEGER_VALUE)
                               COMMA maximum=(DECIMAL_VALUE | INTEGER_VALUE)
                               COMMA stepSize=(DECIMAL_VALUE | INTEGER_VALUE) BRACKET_RIGHT # SpinBox
              | SLIDER BRACKET_LEFT minimum=(DECIMAL_VALUE | INTEGER_VALUE)
                              COMMA maximum=(DECIMAL_VALUE | INTEGER_VALUE) BRACKET_RIGHT   # Slider
              | COMBO BRACKET_LEFT trueValue=TEXT COMMA falseValue=TEXT BRACKET_RIGHT       # ComboBox
              | RADIO BRACKET_LEFT trueValue=TEXT COMMA falseValue=TEXT BRACKET_RIGHT       # RadioButtons;

STYLESHEET    : 'stylesheet' ;
PAGE          : 'page' ;
SECTION       : 'section' ;
QUESTION      : 'question' ;

BOOLEAN       : 'boolean' ;
INTEGER       : 'integer' ;
STRING        : 'string' ;
DECIMAL       : 'decimal' ;
MONEY         : 'money' ;
DATE          : 'date' ;

WIDGET        : 'widget' ;
DEFAULT       : 'default' ;
CHECKBOX      : 'checkbox' ;
SPINBOX       : 'spinbox' ;
SLIDER        : 'slider' ;
RADIO         : 'radio' ;
COMBO         : 'combo' ;

FONT          : 'font' ;
FONTSIZE      : 'fontsize' ;
COLOR         : 'color' ;
WIDTH         : 'width' ;
MIN           : '-' ;

CURLY_LEFT    : '{' ;
CURLY_RIGHT   : '}' ;

BRACKET_LEFT  : '(' ;
BRACKET_RIGHT : ')' ;

DOUBLE_COLON  : ':' ;
COMMA         : ',' ;
PERIOD        : '.' ;

DECIMAL_VALUE : MIN? [0-9]+ PERIOD [0-9]+ ;
INTEGER_VALUE : MIN? [1-9][0-9]* ;
HEXDIGIT      : [0-9]|[A-F] ;
HEX_VALUE     : '#' HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT;
IDENTIFIER    : [a-zA-Z0-9_]+ ;

TEXT          : '"' .*? '"' { setText(getText().substring(1, getText().length() - 1)); } ;

WHITESPACE    : [ \t\r\n]+ -> skip ;
COMMENT       : '//' .*? '\n' -> skip ;
