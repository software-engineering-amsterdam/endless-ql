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

// Expressions: Include some basic rules about operators //TODO fix the expression not being recognised in test_grammar
expression : '(' expression ')'
           | NOT expression // Not is special because it's unary.
           | VARIABLE | BOOLEAN | STRING | numeral
           | expression operator expression;

// Operators
operator      : booloperator | equaloperator | comparision | addsub; //arithmetic;
booloperator  : AND | OR;
equaloperator : EQUAL | NOTEQUAL;
comparision   : LESS | GREATER | LESSEQ | GREATEREQ;
//arithmetic    : factor | muldiv | addsub;
addsub        : muldiv | ADDITION | SUBTRACTION;
muldiv        : factor | MULTIPLICATION | DIVISION;
factor        : EXPONENT;
