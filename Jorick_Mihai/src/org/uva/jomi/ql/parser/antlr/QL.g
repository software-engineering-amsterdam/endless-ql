grammar QL;

form : 'form' IDENTIFIER block ;

block: '{' command* '}' ;

command: question | condition ;

condition: 'if' '(' IDENTIFIER ')' block ;

question : IDENTIFIER ':' STRING type ;

type : BOOLEAN | MONEY ;

BOOLEAN : 'boolean' ;
MONEY : 'money' ;


LINE_COMMENT : '//' .*? '\r'? '\n' -> skip ;
COMMENT : '/*' .*? '*/' -> skip ; 
WS : (' ' | '\t' | '\r' | '\n')+ -> skip ;

IDENTIFIER : [a-zA-Z][a-zA-Z0-9]+ ;
STRING : '"' .*? '"' ;