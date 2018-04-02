grammar QL;

// Skip whitespace
WS : [ \t\r\n]+ -> skip;

// Operators
NOT            : '!';
EXPONENT       : '^';
ADDITION       : '+';
SUBTRACTION    : '-';
DIVISION       : '/';
MULTIPLICATION : '*';
GREATER        : '>';
LESS           : '<';
GREATEREQ      : '>=';
LESSEQ         : '<=';
EQUAL          : '==';
NOTEQUAL       : '!=';
AND            : '&&';
OR             : '||';

// Types
TYPE     : 'boolean' | 'string' | 'integer' | 'date' | 'decimal' | 'money';
BOOLEAN  : 'true' | 'false';
STRING   : '"'[a-zA-Z0-9?.!:;()/ \t]+'"';
INTEGER  : [0-9]+;
DECIMAL  : [0-9]+ '.' [0-9]+;
VARIABLE : [a-zA-Z][a-zA-Z0-9_]+;

// Form structure
form      : 'form' VARIABLE '{' question* condition* '}';
question  : VARIABLE ':' STRING TYPE expression?;
condition : 'if' expression '{' question* condition* '}';

// Expressions: Includes order about operators
expression : '(' expression ')'
           | NOT expression // Not is special because it's unary.
           | term
           | expression factor expression
           | expression muldiv expression
           | expression addsub expression
           | expression operator expression;

// Operators
operator      : booloperator | equaloperator | comparison;
booloperator  : AND | OR;
equaloperator : EQUAL | NOTEQUAL;
comparison    : LESS | GREATER | LESSEQ | GREATEREQ;
addsub        : ADDITION | SUBTRACTION;
muldiv        : MULTIPLICATION | DIVISION;
factor        : EXPONENT;

term          : BOOLEAN | VARIABLE | STRING | INTEGER | DECIMAL;
