grammar QL;

INT   : [1-9][0-9]* ;

ID    : [a-z][a-zA-Z]* ;

TEXT  : '"'[a-z][a-zA-Z]*'"' ;

WS      : [ \t\r\n]+ -> skip ;
COMMENT : '//' .*? '\n' -> skip ;

program : form ;

form : name '{' field+ '}' ;

name : ID ;

field: name ':' TEXT type;

type: bool;

bool: 'boolean';
