grammar ql;

whitespace : ' '
           | '\n'
           | '\r'
           | '\t'
           ;

form : whitespace? 'form' whitespace* '{' .* '}';


form 			: 'form' Identifier questionBlock;
questionBlock	: '{' question | conditions'}';
question        : STRING IDENTIFIER type;
conditions		: 'if' '(' expression ')' questionBlock;

//data types
type    : 'boolean'|'int'|'String'|'float'|'double';


//Literals
INTEGER    : [0-9]+;
STRING     : '"' .* '"';
IDENTIFIER : [a-zA-Z_] [a-zA-Z0-9]* ;

WhiteSpace : [ \t\r\n]+ -> skip ;



