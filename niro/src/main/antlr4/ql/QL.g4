grammar QL;

ID          : [a-zA-Z0-9]+ ;
TEXT        : '"' .*? '"' ;
WS          : [ \t\r\n]+ -> skip ;
COMMENT     : '//' .*? '\n' -> skip ;

form        : 'form' name '{' ( question | conditional )+ '}' ;
name        : ID ;
question    : name ':' TEXT type;
conditional : 'if' '(' condition ')' '{' ( question | conditional )+ '}' ;
condition   : 'true' | 'false' ;
type        : 'boolean' | 'integer' | 'text' ;
