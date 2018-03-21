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
SPINGBOX     : 'spinbox' ;
RADIO        : 'radio' ;
COMBO        : 'combo' ;

CURLY_LEFT   : '{' ;
CURLY_RIGHT  : '}' ;

BRACKET_LEFT  : '(' ;
BRACKET_RIGHT : ')' ;

DOUBLE_COLON  : ':' ;
COMMA         : ',' ;

Identifier   : [a-zA-Z0-9_]+ ;

Text         : '"' .*? '"' { setText(getText().substring(1, getText().length() - 1)); } ;

WHITESPACE   : [ \t\r\n]+ -> skip ;
COMMENT      : '//' .*? '\n' -> skip ;


stylesheet    : STYLESHEET name=Identifier CURLY_LEFT page+ defaultStyle* CURLY_RIGHT EOF ;

page          : PAGE name=Identifier CURLY_LEFT section+ defaultStyle* CURLY_RIGHT ;

section       : SECTION name=Text questionBlock ;

questionBlock : CURLY_LEFT questions+=question+ defaultStyle* CURLY_RIGHT
              | questions+=question ;

widgetType    : CHECKBOX
              | SPINGBOX
              | COMBO BRACKET_LEFT trueValue=Text COMMA falseValue=Text BRACKET_RIGHT
              | RADIO BRACKET_LEFT trueValue=Text COMMA falseValue=Text BRACKET_RIGHT ;

style         : WIDGET widgetType ;

questionType  : BOOLEAN | STRING | DATE | INTEGER | DECIMAL | MONEY ;

defaultStyle  : DEFAULT questionType style ;

question      : QUESTION name=Identifier style? ;
