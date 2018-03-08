grammar QLS;

@header {
package antlr.generated;
}

stylesheet
    :   'stylesheet' id=ID OPEN_BRACKET page+ CLOSE_BRACKET
    ;


page: 'page' ID OPEN_BRACKET * CLOSE_BRACKET;

section: 'section' STRING OPEN_BRACKET * CLOSE_BRACKET;

segment: question
       | section
       ;

question: 'question' ID widget*;

widget: 'widget' widgetType style*;

widgetType: 'slider'
          | 'spinbox'
          | 'text'
          | 'radio'
          | 'checkbox'
          ;

style: OPEN_BRACKET * CLOSE_BRACKET;



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
