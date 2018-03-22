grammar QLS;

head        : STYLESHEET IDENTIFIER block EOF;
block       : '{' page+ '}';
page        : PAGE IDENTIFIER '{' (section | defaultSec)+ '}';
section     : SECTION STRING (question | '{' (question | section | defaultSec)+ '}');
question    : QUESTION IDENTIFIER (widget)*;
defaultSec  : DEFAULT type (widget | '{' widget* '}');

widget  : WIDGET RADIO '(' STRING (',' STRING)* ')' #radioWidget
        | WIDGET CHECKBOX                           #checkWidget
        | WIDGET SPINBOX                            #spinWidget
        | WIDTH INTEGER                             #widthWidget
        | FONT STRING                               #fontWidget
        | FONTSIZE INTEGER                          #fontSizeWidget
        | COLOR HEXVALUE                            #colorWidget;

type    : BOOLEANTYPE | STRINGTYPE | MONEYTYPE | INTEGERTYPE | DATETYPE | DECIMALTYPE;

//Terms
STYLESHEET      : 'stylesheet';
PAGE            : 'page';
SECTION         : 'section';
DEFAULT         : 'default';
QUESTION        : 'question';
WIDGET          : 'widget';
RADIO           : 'radio';
CHECKBOX        : 'checkbox';
SPINBOX         : 'spinbox';
WIDTH           : 'width:';
FONT            : 'font:';
FONTSIZE        : 'fontsize:';
COLOR           : 'color:';
BOOLEANTYPE     : 'boolean';
STRINGTYPE      : 'string';
MONEYTYPE       : 'money';
INTEGERTYPE     : 'integer';
DATETYPE        : 'date';
DECIMALTYPE     : 'decimal';

//Literals
INTEGER         : [0-9]+;
DECIMAL         : [0-9]+ '.' [0-9]+;
MONEY           : ([0-9]+ '.' [0-9]+) | [0-9]+;
DATE            : DAY '-' MONTH '-' YEAR;
DAY             : '0'[0-9] | [1-2][0-9] | '3'[0-1];
MONTH           : '0'[1-9] | '1'[0-2];
YEAR            : [0-2][0-9][0-9][0-9];
STRING          : '"' .*? '"';
IDENTIFIER      : [a-zA-Z0-9]+;
HEXVALUE        : '#'([0-9]|'a'..'f')([0-9]|'a'..'f')([0-9]|'a'..'f')([0-9]|'a'..'f')([0-9]|'a'..'f')([0-9]|'a'..'f');

WHITESPACE      : [ \t\r\n]+ -> skip;
MULTI_COMMENT   : ('/*' .*? '*/') -> skip;
SINGLE_COMMENT  : '//' ~[\r\n]* -> skip;