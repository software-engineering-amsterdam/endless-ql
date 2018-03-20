grammar QLS;

root            : STYLESHEET IDENTIFIER ('{' page* '}' | page) EOF;
page            : PAGE IDENTIFIER ((section | defaultStyle) | '{' (section | defaultStyle)* '}');
section         : SECTION STRING ((section | question | defaultStyle) | '{' (section | question | defaultStyle)* '}');
question        : QUESTION (IDENTIFIER | IDENTIFIER widget);

// default styleAttribute without braces can only define one widget type
// default styleAttribute with braces defines none or multiple styleAttribute attributes,
// followed by one or no widget type
defaultStyle    : DEFAULT type (widget | '{' styleAttribute* widget? '}');

styleAttribute  : WIDTH ':' INTEGER                                                 # widgetWidth
                | FONT ':' STRING                                                   # widgetFont
                | FONTSIZE ':' INTEGER                                              # widgetFontSize
                | COLOR ':' HEXCOLOR                                                # widgetColor
                ;

widget          : WIDGET RADIO '(' trueLabel=STRING ',' falseLabel=STRING ')'       # radioWidget
                | WIDGET DROPDOWN '(' trueLabel=STRING ',' falseLabel=STRING ')'    # dropdownWidget
                | WIDGET CHECKBOX                                                   # checkBoxWidget
                | WIDGET SPINBOX                                                    # spinBoxWidget
                | WIDGET SLIDER                                                     # sliderWidget
                | WIDGET TEXTBOX                                                    # textBoxWidget
                ;

type            : BOOLEANTYPE
                | STRINGTYPE
                | INTEGERTYPE
                | DATETYPE
                | DECIMALTYPE
                | MONEYTYPE
                ;

// Keywords
STYLESHEET            : 'stylesheet';
PAGE                  : 'page';
SECTION               : 'section';
DEFAULT               : 'default';
WIDGET                : 'widget';
QUESTION              : 'question';
RADIO                 : 'radio';
CHECKBOX              : 'checkbox';
SPINBOX               : 'spinbox';
DROPDOWN              : 'dropdown';
SLIDER                : 'slider';
TEXTBOX               : 'text';
WIDTH                 : 'width';
FONT                  : 'font';
FONTSIZE              : 'fontsize';
COLOR                 : 'color';

BOOLEANTYPE           : 'boolean';
STRINGTYPE            : 'string';
INTEGERTYPE           : 'integer';
DATETYPE              : 'date';
DECIMALTYPE           : 'decimal';
MONEYTYPE             : 'money';

WS              : [ \t\r\n]+ -> skip;
COMMENT         : ('/*' .*? '*/') -> skip;
LINE_COMMENT    : '//' ~[\r\n]* -> skip;


// Literals
INTEGER         : [0-9]+;
MONEY           : [0-9]+ '.' [0-9] [0-9];
DECIMAL         : [0-9]+ '.' [0-9]+;
DATE            : ([0-9] | [0-3] [0-9]) '-' ([0-9] | [0-3] [0-9]) '-' ([0-9] [0-9] [0-9] [0-9]);
STRING          : '"' .*? '"';
IDENTIFIER      : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
HEXCOLOR        : '#' [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F];