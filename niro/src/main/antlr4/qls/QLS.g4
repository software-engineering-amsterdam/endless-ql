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


stylesheet    : STYLESHEET name=Identifier CURLY_LEFT page+ CURLY_RIGHT EOF ;
page          : PAGE name=Identifier CURLY_LEFT section+ CURLY_RIGHT ;
section       : SECTION name=Text questionBlock ;
questionBlock : CURLY_LEFT questions+=question+ CURLY_RIGHT
              | questions+=question ;
widgetType    : CHECKBOX
              | SPINGBOX
              | COMBO BRACKET_LEFT trueValue=Text COMMA falseValue=Text BRACKET_RIGHT
              | RADIO BRACKET_LEFT trueValue=Text COMMA falseValue=Text BRACKET_RIGHT ;
question      : QUESTION name=Identifier (WIDGET widgetType)? ;