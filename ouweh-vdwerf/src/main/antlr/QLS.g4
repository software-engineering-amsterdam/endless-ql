grammar QLS;

@header {
package antlr.generated;
}

stylesheet
    :   'stylesheet' id=ID OPEN_BRACKET page+ CLOSE_BRACKET
    ;

page: 'page' ID OPEN_BRACKET segment+ default* CLOSE_BRACKET;

section: 'section' ID OPEN_BRACKET segment+ default* CLOSE_BRACKET;

segment: question
       | section
       ;

default: 'default' type widget
       | 'default' type style
       ;

question: 'question' ID widget?;

widget: 'widget' widgetType style?;

widgetType: 'slider'
          | 'spinbox'
          | 'text'
          | 'radio'
          | 'checkbox'
          ;

type
    : 'boolean'                                                             #booleanType
    | 'integer'                                                             #integerType
    | 'money'                                                               #moneyType
    | 'string'                                                              #stringType
    ;

style: OPEN_BRACKET styleProperty+ CLOSE_BRACKET;

styleProperty: name=STRING ':' value;

value: STRING
     | NUMBER
     ;


ID:   [a-zA-Z_]+[a-zA-Z0-9_]*;

STRING: '"' .*? '"';

NUMBER
    :   '-'? ('0'..'9')+ ('.' ('0'..'9')+)?
    ;

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
