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

CURLY_LEFT   : '{' ;
CURLY_RIGHT  : '}' ;

BRACK_LEFT   : '(' ;
BRACK_RIGHT  : ')' ;

DOUBLE_COLON : ':' ;

Identifier   : [a-zA-Z0-9_]+ ;

Text       : '"' .*? '"' { setText(getText().substring(1, getText().length() - 1)); } ;

WHITESPACE   : [ \t\r\n]+ -> skip ;
COMMENT      : '//' .*? '\n' -> skip ;


stylesheet : STYLESHEET name=Identifier CURLY_LEFT page+ CURLY_RIGHT EOF ;
page       : PAGE name=Identifier CURLY_LEFT section+ CURLY_RIGHT ;
section    : SECTION name=Text CURLY_LEFT question+ CURLY_RIGHT ;
question   : QUESTION name=Identifier ;