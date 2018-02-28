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
TYPE     : 'boolean' | 'string' | 'integer' | 'date' | 'decimal' | 'money'; //TODO figuring out naming
BOOLEAN  : 'true' | 'false';
STRING   : '"'[a-zA-Z0-9?.!:;()/ \t]+'"'; // TODO: allow more characters
INTEGER  : [0-9]+;
DECIMAL  : [0-9]+ '.' [0-9]+;
numeral  : INTEGER | DECIMAL;
VARIABLE : [a-zA-Z][a-zA-Z0-9_]+;
// For now don't specify DATE, because we don't allow operators on them (yet?).
//DATE     : [0-9]{1,2}'-'[0-9]{1,2}'-'[0-9]{1,4} | [0-9]{1,4}'-'[0-9]{1,2}'-'[0-9]{1,2}; // Dates currently allow dd?-mm?-yy?y?y? yy?y?y?-mm?-dd?

// Form structure
form      : 'form' VARIABLE '{' question* condition* '}';
question  : VARIABLE ':' STRING TYPE expression?;
condition : 'if' expression '{' question* condition* '}';

// Expressions: Includes order about operators
expression : '(' expression ')'
           | NOT expression // Not is special because it's unary.
           | VARIABLE | BOOLEAN | STRING | numeral
           | expression factor expression
           | expression muldiv expression
           | expression addsub expression
           | expression operator expression;

// Operators
operator      : booloperator | equaloperator | comparision;
booloperator  : AND | OR;
equaloperator : EQUAL | NOTEQUAL;
comparision   : LESS | GREATER | LESSEQ | GREATEREQ;
addsub        : ADDITION | SUBTRACTION;
muldiv        : MULTIPLICATION | DIVISION;
factor        : EXPONENT;
