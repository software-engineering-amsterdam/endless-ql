grammar QL_Common ;

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

WS           : [ \t\r\n]+ -> skip ;
COMMENT      : '//' .*? '\n' -> skip ;
