grammar QL;

f               : 'form' ID content EOF;
content         : '{' (question | statement)* '}';
question        : ID ':' LABEL ((QTYPE expression) | QTYPE) ';';
// More statements? Otherwise keep only ifstatement
statement       : ifstatement | ifelsestatement;
ifstatement     : IF '(' expression ')' content;
ifelsestatement : IF '(' expression ')' content ELSE content;
expression      : VALUE | '(' expression ')' | expression operator expression | ID;

operator    : boolOp | compOp | arithOp;
boolOp      : AND | OR | NOT;
compOp      : EL | EG | LT | GT | NEQ | EQ;
arithOp     : ADD | SUB | MUL | DIV;

fragment BOOL    : 'true' | 'false';
fragment INTEGER : [0-9]+;

QTYPE       : 'boolean' | 'string' | 'integer' | 'date' | 'decimal' | 'money';
IF          : 'if';
ELSE        : 'else';
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
VALUE       : INTEGER | DECIMAL | DATE | BOOL;
DECIMAL     : [0-9]+ '.' [0-9]+;
DATE        : DAY '-' MONTH '-' YEAR;
DAY         : '0'[1-9] | [1-3][0-9];
// Make it possible to do text or just numbers for month? 
MONTH       : '0'[1-9] | '1'[0-2];
YEAR        : [1-2][0-9][0-9][0-9];
ID          : [a-zA-Z0-9]+;
LABEL       : '"' ~'"'*? '"';
COMMENT     : '//' ~'\n'*? '\n' -> skip;
WHITESPACE  : [ \n\t\r]+ -> skip;