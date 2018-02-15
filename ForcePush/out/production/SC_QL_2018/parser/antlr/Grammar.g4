grammar Grammar;
options {backtrack=true; memorize=true;}

//Whitespace
NEWLINE : '\r\n' | 'r' | '\n';
WHITESPACE : [\t]+;

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

//Comparisons
LESS : '<';
HIGHER : '>';
EQUALHIGHER : '>=';
EQUALLESS : '<=';
DIFF : '!=';
EQUAL : '==';

//Booleans
AND : '&&';
OR : '||';
NOT : '!';

//Variables
VAR : [_]*[a-z][A-Za-z0-9_]*;
LABEL : '"'[A-Za-z0-9\t]+'"';

//RULES
a : INT INT;