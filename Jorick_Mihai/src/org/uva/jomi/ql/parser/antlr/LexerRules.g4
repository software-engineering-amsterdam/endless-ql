lexer grammar LexerRules;

BOOLEAN: TRUE | FALSE ;
TYPE: 'boolean' | 'string' | 'integer' | 'decimal' | 'date' | 'money';
 
TRUE: 'true' ;
FALSE: 'false' ;
STAR: '*' ;
SLASH: '/' ;
PLUS: '+' ;
MINUS: '-' ;
BANG: '!' ;
GREATER: '>' ;
GREATER_EQUAL: '>=' ;
LESS: '<' ;
LESS_EQUAL: '<=' ;
BANG_EQUAL: '!=' ;
EQUAL_EQUAL: '==' ;
AND: '&&' ;
OR: '||' ;
INTEGER: ('-')? DIGIT+ ;
LABEL: '"' .*? '"' ;

LINE_COMMENT : '//' .*? '\r'? '\n' -> skip ;
COMMENT : '/*' .*? '*/' -> skip ;
WS: [ \t\r\n] -> skip ;
IDENTIFIER: LETTER+ (LETTER|DIGIT)*  ;
 
// Fragments (Prefix the rule with 'fragment': the rule will be used ONLY by other rules.)
fragment
DIGIT:	[0-9];
fragment
LETTER: [a-zA-Z];

ErrorChar : . ;