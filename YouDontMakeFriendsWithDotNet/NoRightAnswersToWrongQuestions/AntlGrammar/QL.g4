grammar QL;

questionnaire: 'form' IDENTIFIER '{' statement* '}'; 

statement : (question | conditional); 

question: IDENTIFIER ':' QUESTIONTEXT questiontype | QUESTIONTEXT IDENTIFIER  ':' questiontype ;

questiontype: qtype=(BOOLTYPE | STRINGTYPE | INTTYPE | DATETYPE | DECIMALTYPE);

conditional: 'if' '(' IDENTIFIER ')' '{' statement* '}';

BOOLTYPE: 'boolean';
STRINGTYPE: 'string';
INTTYPE: 'integer';
DATETYPE: 'date';
DECIMALTYPE: 'decimal';

QUESTIONTEXT: '"' (~'"')* '"';
IDENTIFIER : [a-zA-Z] [a-zA-Z0-9_]* ;
NEWLINE:'\r'? '\n' -> skip;
WS  :   [ \t]+ -> skip ;
LINECOMMENT :  '//' ~[\r\n]* -> skip;
BLOCKCOMMENT :   '/*' .*? '*/' -> skip;



