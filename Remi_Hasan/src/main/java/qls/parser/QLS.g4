grammar QLS;

root            : STYLESHEET IDENTIFIER ('{' page* '}' | page) EOF;
page            : PAGE IDENTIFIER ((section | defaultStyle) | '{' (section | defaultStyle)* '}');
section         : SECTION STRING ((section | question | defaultStyle) | '{' (section | question | defaultStyle)* '}');
question        : QUESTION (IDENTIFIER | IDENTIFIER widget);
defaultStyle    : DEFAULT type (widget | '{' widget* '}');

// Widgets
widget          : WIDGET RADIO '(' STRING (',' STRING)* ')' # radioWidget
                | WIDGET CHECKBOX                           # checkBoxWidget
                | WIDGET SPINBOX                            # spinBoxWidget
                | WIDTH ':' INTEGER                         # widgetWidth
                | FONT ':' STRING                           # widgetFont
                | FONTSIZE ':' INTEGER                      # widgetFontSize
                | COLOR ':' HEXCOLOR                        # widgetColor
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
HEXCOLOR        : '#' ([0-9] | 'a'..'f') ([0-9] | 'a'..'f') ([0-9] | 'a'..'f') ([0-9] | 'a'..'f') ([0-9] | 'a'..'f') ([0-9] | 'a'..'f');