grammar QL;
questionnaire: 'form' IDENT '{' question* '}'; 
question: IDENT ':' STRING;

STRING: '"' (~'"')* '"';
IDENT  :  [a-zA-Z] [a-zA-Z0-9_]* ;
NEWLINE:'\r'? '\n' -> skip;
WS  :   [ \t]+ -> skip ;
LINECOMMENT :  '//' ~[\r\n]* -> skip;
BLOCKCOMMENT : '/*' .*? '*/'  -> skip;