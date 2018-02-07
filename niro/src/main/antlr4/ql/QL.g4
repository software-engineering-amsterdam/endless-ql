grammar QL;

INT   : [1-9][0-9]* ;

ID    : [a-z][a-zA-Z]* ;

TEXT  : '"' .*? '"' ;

WS      : [ \t\r\n]+ -> skip ;
COMMENT : '//' .*? '\n' -> skip ;

program : form ;

form : name '{' question+ '}' ;

name : ID ;

question: name ':' TEXT type;

type: bool;

bool: 'boolean';
