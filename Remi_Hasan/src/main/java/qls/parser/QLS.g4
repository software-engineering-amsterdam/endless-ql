// TODO make external file for parts that are the same as QL.g4?
// TODO and include that file?

// TODO SECTION { and without when single

grammar QLS;

root            : STYLESHEET IDENTIFIER ('{' page* '}' | page) EOF;
page            : PAGE IDENTIFIER ((section | default_) | '{' (section | default_)* '}');
section         : SECTION STRING ((section | question | default_) | '{' (section | question | default_)* '}');
question        : QUESTION (IDENTIFIER | IDENTIFIER widget);
default_        : DEFAULT type (widget | '{' widget* '}');

// Widgets
widget          : WIDGET RADIO '(' STRING (',' STRING)* ')' # radioWidget
                | WIDGET CHECKBOX # checkBoxWidget
                | WIDGET SPINBOX # spinBoxWidget
                | WIDTH ':' INTEGER # widgetWidth
                | FONT ':' STRING # widgetFont
                | FONTSIZE ':' INTEGER # widgetFontSize
                | COLOR ':' HEXCOLOR # widgetColor
                ;

//radioWidget     : ;
//checkBoxWidget  : CHECKBOX;
//spinBoxWidget   : SPINBOX;
//widgetWidth     : WIDTH ':' INTEGER;
//widgetFont      : FONT ':' STRING; // TODO validate font family?
//widgetFontSize  : FONTSIZE ':' INTEGER;
//widgetColor     : COLOR ':' HEXCOLOR;


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