grammar QLS;


/**
 * Parser rules
 */

stylesheet      : STYLESHEET identifier page+ ;
page            : PAGE identifier BRACKET_L section+ defaultdef? BRACKET_R ;
section_elem    : (section | question | defaultdef) ;
section         : SECTION STRING BRACKET_L section_elem+ BRACKET_R ;
question        : QUESTION identifier widget? ;
defaultdef      : DEFAULT identifier BRACKET_L property+ BRACKET_R
                | DEFAULT type WIDGET widget ;
property        : WORD COLON (NUMBER | STRING | COLOR_CODE)
                | widget ;
widget          : WIDGET widget_type ;
widget_type     : RADIO | SPINBOX | CHECKBOX | TEXT | SLIDER | DROPDOWN ;

type            : BOOLEAN_TYPE | STRING_TYPE | INTEGER_TYPE | MONEY_TYPE ;
identifier      : WORD ;

/**
 * Lexer rules
 */

STYLESHEET      : 'stylesheet' ;
PAGE            : 'page' ;
SECTION         : 'section' ;
WIDGET          : 'widget' ;
QUESTION        : 'question' ;
DEFAULT         : 'default' ;
BOOLEAN_TYPE    : 'boolean' ;
STRING_TYPE     : 'string' ;
INTEGER_TYPE    : 'integer' ;
MONEY_TYPE      : 'money' ;
RADIO           : 'radio' ;
SPINBOX         : 'spinbox' ;
CHECKBOX        : 'checkbox' ;
TEXT            : 'text' ;
SLIDER          : 'slider' ;
DROPDOWN        : 'dropdown' ;

BRACKET_L       : '{' ;
BRACKET_R       : '}' ;
PARENTH_L       : '(' ;
PARENTH_R       : ')' ;
COLON           : ':' ;


COLOR_CODE      : '#'[0-9a-fA-F]+ ;
NEWLINE         : ('\r'? '\n' | '\r')+ -> skip ;
WHITESPACE      : (' ' | '\t') -> skip ;

NUMBER          : [0-9]+ ;
WORD            : [a-zA-Z]+[a-zA-Z0-9]*;
STRING          : '"'(.*?)'"';