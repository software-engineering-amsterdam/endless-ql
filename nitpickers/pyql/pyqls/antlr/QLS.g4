grammar QLS;

/*
 * Parser rules
 */

stylesheet      : 'stylesheet' identifier '{' stylesheetBlock '}' ;

stylesheetBlock : page+ ;

page            : 'page' identifier '{' pageBlock '}' ;

pageBlock       : section+ ;

section         : 'section' STRING '{' sectionBlock '}'  #singleStatementSection
                | 'section' STRING 'question' identifier  #multiStatementSection
                ;

sectionBlock    : statement+ ;

statement       : 'question' identifier #questionStatement
                | questionStyle         #questionStyleStatement
                | section               #sectionStatement
                | default               #defaultStatement
                ;

questionStyle   : 'question' identifier widget ;

widget          : 'widget' widgetType;

widgetType      : 'radio(' STRING (',' STRING)* ')'    #radioWidget
                | 'dropdown(' STRING (',' STRING)* ')' #dropdownWidget
                | 'spinbox'                            #spinboxWidget
                | 'checkbox'                           #checkboxWidget
                | 'slider'                             #sliderWidget
                | 'text'                               #textWidget
                ;

default         : 'default' questionType widget ;

questionType    : 'boolean' #booleanType
                | 'string'  #stringType
                | 'integer' #integerType
                | 'date'    #dateType
                | 'decimal' #decimalType
                | 'money'   #moneyType
                ;

identifier      : IDENTIFIER ;

/*
 * Lexer rules
 */

WHITESPACE   : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

COMMENT      : '/*' .*? '*/' -> channel(HIDDEN);

LINE_COMMENT : '//'.*? ~[\r\n]* -> channel(HIDDEN);

STRING       : '"' .*? '"';

INT          : [0-9]+;

IDENTIFIER   : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

HEX_COLOR    : '#'[0-9A-F][0-9A-F][0-9A-F][0-9A-F][0-9A-F][0-9A-F];