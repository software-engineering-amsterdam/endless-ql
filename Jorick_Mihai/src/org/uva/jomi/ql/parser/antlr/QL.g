grammar QL;



form : 'form' IDENTIFIER block ;

block: '{' command* '}' ;

command: question | condition ;

condition: 'if' '(' IDENTIFIER ')' block ;

question : IDENTIFIER ':' LABEL type '';

LABEL : '"' .*? '"' ;
type : STRING | BOOLEAN | INTEGER | DECIMAL | DATE | MONEY;


LINE_COMMENT : '//' .*? '\r'? '\n' -> skip ;
COMMENT : '/*' .*? '*/' -> skip ; 
WS : (' ' | '\t' | '\r' | '\n')+ -> skip ;

IDENTIFIER : [a-zA-Z][a-zA-Z0-9]+ ;


STRING : 'string' ;
BOOLEAN : 'boolean' ;
INTEGER : 'integer' ;
DECIMAL : 'decimal' ;
DATE : 'date' ;
MONEY : 'money' ;