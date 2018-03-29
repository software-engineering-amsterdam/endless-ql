grammar QLS;

STYLESHEET : 'stylesheet' ;
PAGE       : 'page' ;
SECTION    : 'section' ;
QUESTION   : 'question' ;

BOOLEAN      : 'boolean' ;
INTEGER      : 'integer' ;
STRING       : 'string' ;
DECIMAL      : 'decimal' ;
MONEY        : 'money' ;
DATE         : 'date' ;

WIDGET       : 'widget' ;
DEFAULT      : 'default' ;
CHECKBOX     : 'checkbox' ;
SPINBOX      : 'spinbox' ;
SLIDER       : 'slider' ;
RADIO        : 'radio' ;
COMBO        : 'combo' ;

FONT         : 'font' ;
FONTSIZE     : 'fontsize' ;
COLOR        : 'color' ;
WIDTH        : 'width' ;
MIN          : '-' ;

CURLY_LEFT   : '{' ;
CURLY_RIGHT  : '}' ;

BRACKET_LEFT  : '(' ;
BRACKET_RIGHT : ')' ;

DOUBLE_COLON  : ':' ;
COMMA         : ',' ;
PERIOD        : '.' ;

DecimalValue : MIN? [0-9]+ PERIOD [0-9]+ ;
IntegerValue : MIN? [1-9][0-9]* ;
HEXDIGIT     : [0-9]|[A-F] ;
HexValue     : '#' HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT;
Identifier   : [a-zA-Z0-9_]+ ;

Text         : '"' .*? '"' { setText(getText().substring(1, getText().length() - 1)); } ;

WHITESPACE   : [ \t\r\n]+ -> skip ;
COMMENT      : '//' .*? '\n' -> skip ;

stylesheet    : STYLESHEET name=Identifier CURLY_LEFT page+ defaultStyle* CURLY_RIGHT EOF ;

page          : PAGE name=Identifier CURLY_LEFT section+ defaultStyle* CURLY_RIGHT ;

section       : SECTION name=Text questionBlock ;

questionBlock : CURLY_LEFT questions+=question+ defaultStyle* CURLY_RIGHT
              | questions+=question ;

question      : QUESTION name=Identifier styling? ;

defaultStyle  : DEFAULT questionType styling ;

questionType  : BOOLEAN | STRING | DATE | INTEGER | DECIMAL | MONEY ;

styling       : style
              | CURLY_LEFT style+ CURLY_RIGHT ;

style         : WIDGET widgetType                           # WidgetStyling
              | WIDTH DOUBLE_COLON widthValue=IntegerValue  # WidthStyling
              | COLOR DOUBLE_COLON colorValue=HexValue      # ColorStyling
              | FONT DOUBLE_COLON fontType=Text             # FontTypeStyling
              | FONTSIZE DOUBLE_COLON fontSize=IntegerValue # FontSizeStyling;

widgetType    : CHECKBOX                                                                # CheckBox
              | SPINBOX                                                                 # SpinBox
              | SLIDER BRACKET_LEFT minimum=(DecimalValue | IntegerValue)
                              COMMA maximum=(DecimalValue | IntegerValue) BRACKET_RIGHT # Slider
              | COMBO BRACKET_LEFT trueValue=Text COMMA falseValue=Text BRACKET_RIGHT   # ComboBox
              | RADIO BRACKET_LEFT trueValue=Text COMMA falseValue=Text BRACKET_RIGHT   # RadioButtons;
