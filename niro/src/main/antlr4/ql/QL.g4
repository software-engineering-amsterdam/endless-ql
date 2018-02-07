grammar QL;

ID    : [a-zA-Z0-9]+ ;
TEXT  : '"' .*? '"' ;
WS      : [ \t\r\n]+ -> skip ;
COMMENT : '//' .*? '\n' -> skip ;

form : 'form' name '{' question+ '}' ;

name : ID ;

question: name ':' TEXT type;

type: bool;

bool: 'boolean';
