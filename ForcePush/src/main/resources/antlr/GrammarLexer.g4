lexer grammar GrammarLexer;


//Whitespace
WHITESPACE : [ \t\r\n]+ -> skip;

//Keywords
FORM : 'form';

//Types
BOOL : 'boolean';
STR : 'string';
INT : 'integer';
DATE : 'date';
DECIMAL : 'decimal';
MONEY : 'money' | 'currency';

//Operators
ASSIGN : ':';
LPAREN : '(';
RPAREN : ')';
LCURLYBRAKET : '{';
RCURLYBRAKET : '}';
IF : 'if';
ELSE : 'else';
IFELSE : 'ifelse';

//Arithmetic
PLUS : '+';
MINUS : '-';
ASTERISK : '*';
DIVISION : '/';
EQUAL : '=';

//Comparisons
LESS : '<';
HIGHER : '>';
EQUALHIGHER : '>=';
EQUALLESS : '<=';
DIFF : '!=';
ISEQUAL : '==';

//Booleans
AND : '&&';
OR : '||';
NOT : '!';

//Variables
VAR : [_]*[A-Za-z][A-Za-z0-9]*;
LABEL : '"'[A-Za-z0-9 ,.?/:]+'"';


