grammar QL;

// Skip whitespace
WS : [ \t\r\n]+ -> skip;

// Operators
NOT           : '!'; // Not is special because it's unary.
OPERATOR      : BOOLOPERATOR | EQUALOPERATOR | COMPARISON | ARITHMETICS;
BOOLOPERATOR  : '&&' | '||';
EQUALOPERATOR : '==' | '!=';
COMPARISON    : '<' | '>' | '>=' | '<=';
ARITHMETICS   : '+' | '-' | '/' | '*' ;

// Types
TYPE     : 'boolean' | 'string' | 'integer' | 'date' | 'decimal' | 'money';
BOOLEAN  : 'true' | 'false';
VARIABLE : [a-zA-Z][a-zA-Z0-9_]+;
STRING   : '"'[a-zA-Z0-9?.!:;()/ \t]+'"'; // TODO: allow more characters
NUMERAL  : [0-9]+;

// Form structure
form      : 'form' VARIABLE '{' question* condition* '}';
question  : VARIABLE ':' STRING TYPE expression?;
condition : 'if' boolexpression '{' question+ '}';

// Expressions: Include some basic rules about operators
expression : '(' expression ')'
           | NOT expression
           | STRING EQUALOPERATOR STRING
           | NUMERAL (EQUALOPERATOR | COMPARISON | ARITHMETICS) NUMERAL
           | BOOLEAN (BOOLOPERATOR | EQUALOPERATOR) BOOLEAN
           | VARIABLE | BOOLEAN | STRING | NUMERAL
           | expression OPERATOR expression;

// Boolexpressions: these ensure that a boolean (or variable) is returned, needed for conditions.
boolexpression : '(' boolexpression ')'
               | NOT boolexpression
               | VARIABLE | BOOLEAN
               | expression (BOOLOPERATOR | COMPARISON) expression;
