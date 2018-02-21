<<<<<<< HEAD
grammar QL;

questionnaire: 'form' IDENTIFIER '{' question* '}'; 
 
question: IDENTIFIER ':' QUESTIONTEXT questiontype | QUESTIONTEXT IDENTIFIER  ':' questiontype ;

questiontype: qtype=(BOOLTYPE | STRINGTYPE | INTTYPE | DATETYPE | DECIMALTYPE);


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
=======
grammar QL;
questionnaire: 'form' IDENT '{' question* '}'; 
question: IDENT ':' STRING questiontype;


questiontype: 'boolean'  # bool;

STRING: '"' (~'"')* '"';
IDENT  :  [a-zA-Z] [a-zA-Z0-9_]* ;
NEWLINE:'\r'? '\n' -> skip;
WS  :   [ \t]+ -> skip ;
LINECOMMENT :  '//' ~[\r\n]* -> skip;
>>>>>>> b4a9b6ed7a567bef7322e087eb0d3de8f04a3913
BLOCKCOMMENT : '/*' .*? '*/'  -> skip;