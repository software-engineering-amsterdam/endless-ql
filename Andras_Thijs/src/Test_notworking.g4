grammar Test;

//TYPES
VARIABLE     : [a-zA-Z][a-zA-Z0-9_]+;
STRING       : '"'[a-zA-Z0-9?.!:;()/ \t]+'"'; // TODO: allow more characters
NUMERAL      : [0-9]+;
BOOLEAN      : 'true' | 'false';
TYPE         : 'boolean' | 'string' | 'integer' | 'date' | 'decimal' | 'money';
NOT          : '!';
BOOLOPERATOR : '&&' | '||';
COMPARISON   : '<' | '>' | '>=' | '<=' | '!=' | '==';
ARITHMETICS  : '+' | '-' | '/' | '*' ;

//STRUCTURE
ql : 'form' VARIABLE '{' question* condition* '}' ;
question : VARIABLE ':' STRING TYPE ('(' expression ')')? '\n';
expression : (STRING | NUMERAL | '(' boolexpression ')' | ('(' expression ARITHMETICS expression ')'));
boolexpression : (NOT? (VARIABLE | BOOLEAN) | expression (BOOLOPERATOR | COMPARISON) expression);
condition : 'if' '(' boolexpression ')' '{' (question)+ '}';
WS : [ \t\r\n]+ -> skip;
