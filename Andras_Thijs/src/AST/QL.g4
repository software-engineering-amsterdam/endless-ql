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
NUMERAL  : INTEGER | DECIMAL;
INTEGER  : [0-9]+;
DECIMAL  : [0-9]+ '.' [0-9]+;
VARIABLE : [a-zA-Z][a-zA-Z0-9_]+;
STRING   : '"'[a-zA-Z0-9?.!:;()/ \t]+'"'; // TODO: allow more characters
// For now don't specify DATE, because we don't allow operators on them (yet?).
//DATE     : [0-9]{1,2}'-'[0-9]{1,2}'-'[0-9]{1,4} | [0-9]{1,4}'-'[0-9]{1,2}'-'[0-9]{1,2}; // Dates currently allow dd?-mm?-yy?y?y? yy?y?y?-mm?-dd?

// Form structure
form      : 'form' VARIABLE '{' question* condition* '}';
question  : VARIABLE ':' STRING TYPE expression?;
condition : 'if' expression '{' question* condition* '}';

// Expressions: Include some basic rules about operators
expression : '(' expression ')'
           | NOT expression
           | VARIABLE | BOOLEAN | STRING | NUMERAL
           | expression OPERATOR expression; // All other constrains will be handled by the type checker.
