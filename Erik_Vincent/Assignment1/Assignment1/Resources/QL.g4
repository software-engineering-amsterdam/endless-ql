grammar QL;

f               : 'form' ID '{' content* '}' EOF;
content         : (question | statement) | '{' content* '}';
question        : ID ':' LABEL ((qtype expression) | qtype) ';';
// More statements? Otherwise keep only ifstatement
statement       : ifstatement | ifelsestatement;
ifstatement     : IF '(' expression ')' content;
ifelsestatement : IF '(' expression ')' content ELSE content;
expression      : ID | VALUE | '(' expression ')' | expression operator expression;

operator    : boolOp | compOp | arithOp;
boolOp      : AND | OR | NOT;
compOp      : EL | EG | LT | GT | NEQ | EQ;
arithOp     : ADD | SUB | MUL | DIV;
qtype       : 'boolean' | 'string' | 'integer' | 'date' | 'decimal' | 'money';

IF          : 'if';
ELSE        : 'else';
BOOL        : 'true' | 'false';
AND         : '&&';
OR          : '||';
NOT         : '!';
EL          : '<=';
EG          : '>=';
LT          : '<';
GT          : '>';
NEQ         : '!=';
EQ          : '==';
ADD         : '+';
SUB         : '-';
MUL         : '*';
DIV         : '/';
VALUE       : INTEGER | DECIMAL | DATE | MONEY;
INTEGER     : [0-9]+;
DECIMAL     : [0-9]+ '.' [0-9]+;
DATE        : DAY '-' MONTH '-' YEAR;
DAY         : '0'[1-9] | [1-3][0-9];
// Make it possible to do text or just numbers for month? 
MONTH       : '0'[1-9] | '1'[0-2];
YEAR        : [1-2][0-9][0-9][0-9];
MONEY       : INTEGER | DECIMAL;
ID          : [a-zA-Z0-9]+;
LABEL       : '"' ~'"'*? '"';
COMMENT     : '//' ~'\n'*? '\n' -> skip;
WHITESPACE  : [ \n\t\r]+ -> skip;