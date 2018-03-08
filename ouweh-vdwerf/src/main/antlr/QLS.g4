grammar QLS;

@header {
package antlr.generated;
}

stylesheet
    :   'stylesheet' id=ID OPEN_BRACKET page+ CLOSE_BRACKET
    ;


page: 'page' ID OPEN_BRACKET * CLOSE_BRACKET;

section:




ID:  [a-zA-Z_]+[a-zA-Z0-9_]*;

WHITESPACE
    :   (' ' | '\t' | '\r'| '\n') -> channel(HIDDEN)
    ;

MULTI_LINE_COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

SINGLE_LINE_COMMENT
    : '//' ~[\r\n]* -> channel(HIDDEN)
    ;

OPEN_BRACKET :  '{' ;
CLOSE_BRACKET : '}' ;
OPEN_PARENTH :  '(' ;
CLOSE_PARENTH : ')' ;
