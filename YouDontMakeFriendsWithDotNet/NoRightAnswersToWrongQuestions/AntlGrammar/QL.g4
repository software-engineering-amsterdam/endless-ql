grammar QL;

questionnaire: 'form' IDENTIFIER '{' statement* '}'; 

statement : (question | conditional); 

question: IDENTIFIER ':' QUESTIONTEXT questiontype | QUESTIONTEXT IDENTIFIER  ':' questiontype ;

questiontype: qtype=(BOOLTYPE | STRINGTYPE | INTTYPE | DATETYPE | DECIMALTYPE);

conditional: 'if' '(' condition ')' '{' statement* '}';

condition: IDENTIFIER                                                                 # booleancondition
         | (IDENTIFIER | booleanvalue) booleanoperator (IDENTIFIER | booleanvalue)          # booleancomparison
		 | (IDENTIFIER | comparisonvalue) relationaloperator (IDENTIFIER | comparisonvalue) # valuecomparison
		 | '(' condition')' booleanoperator (IDENTIFIER | booleanvalue)                     # booleancomparison2
		 | (IDENTIFIER | booleanvalue) booleanoperator '(' condition')'                     # booleancomparison3
		 | mathcondition relationaloperator condition                               # booleancomparison5
		 | condition relationaloperator mathcondition                               # booleancomparison6
		 | '(' condition')' booleanoperator '(' condition')'                                # booleancomparison4
         ;

mathcondition: '(' (IDENTIFIER | mathvalue) mathoperator (IDENTIFIER | mathvalue) ')';

booleanoperator: op=(ISEQUAL | ISNOTEQUAL);
booleanvalue: val=(TRUE | FALSE);
relationaloperator: (ISGREATERTHAN | ISGREATERTHANOREQUAL | ISLESSTHAN | ISLESSTHANOREQUAL | booleanoperator);
mathoperator: op=(ADD | MINUS | MULTIPLY | DIVIDE);
mathvalue: (INTEGER | DECIMAL);
comparisonvalue: (INTEGER | DECIMAL | DATE);

BOOLTYPE: 'boolean';
STRINGTYPE: 'string';
INTTYPE: 'integer';
DATETYPE: 'date';
DECIMALTYPE: 'decimal';

ISNOTEQUAL : '!=';
ISEQUAL : '==';
ISGREATERTHAN : '>';
ISGREATERTHANOREQUAL : '>=';
ISLESSTHAN : '<';
ISLESSTHANOREQUAL : '<=';
ADD: '+';
MINUS: '-';
MULTIPLY: '*';
DIVIDE: '/';

TRUE : ('true'| 'True'| 'TRUE');
FALSE : ('false' | 'False' | 'FALSE');

DATE: [0-9]?[0-9]'/'[0-9]?[0-9]'/'([0-9][0-9])?([0-9][0-9]);
DECIMAL: '-'?[0-9]+ '.' [0-9]+;
INTEGER: '-'?[0-9]+;


QUESTIONTEXT: '"' (~'"')* '"';
IDENTIFIER : [a-zA-Z] [a-zA-Z0-9_]* ;

NEWLINE:'\r'? '\n' -> skip;
WS  :   [ \t]+ -> skip ;
LINECOMMENT :  '//' ~[\r\n]* -> skip;
BLOCKCOMMENT :   '/*' .*? '*/' -> skip;
