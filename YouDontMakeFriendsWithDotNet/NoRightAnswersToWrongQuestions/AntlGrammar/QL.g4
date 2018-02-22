grammar QL;

questionnaire: 'form' IDENTIFIER '{' statement* '}'; 

statement : (question | conditional); 

question: IDENTIFIER ':' QUESTIONTEXT questiontype | QUESTIONTEXT IDENTIFIER  ':' questiontype ;

questiontype: qtype=(BOOLTYPE | STRINGTYPE | INTTYPE | DATETYPE | DECIMALTYPE);

conditional: 'if' '(' condition ')' '{' statement* '}';

condition: IDENTIFIER                               # booleancondition
         | IDENTIFIER booleanoperator booleanvalue  # booleancomparison
		 | IDENTIFIER comparisonoperator '10'       # valuecomparison
         ;

booleanoperator: op=(ISEQUAL | ISNOTEQUAL);
booleanvalue: val=(TRUE | FALSE);
comparisonoperator: ISGREATERTHAN;

BOOLTYPE: 'boolean';
STRINGTYPE: 'string';
INTTYPE: 'integer';
DATETYPE: 'date';
DECIMALTYPE: 'decimal';

ISNOTEQUAL : '!=';
ISEQUAL : '==';
ISGREATERTHAN : '>';
TRUE : ('true'| 'True'| 'TRUE');
FALSE : ('false' | 'False' | 'FALSE');

QUESTIONTEXT: '"' (~'"')* '"';
IDENTIFIER : [a-zA-Z] [a-zA-Z0-9_]* ;
NEWLINE:'\r'? '\n' -> skip;
WS  :   [ \t]+ -> skip ;
LINECOMMENT :  '//' ~[\r\n]* -> skip;
BLOCKCOMMENT :   '/*' .*? '*/' -> skip;
