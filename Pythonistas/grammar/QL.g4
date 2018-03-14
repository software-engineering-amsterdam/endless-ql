grammar QL;

// Parser
form: FORM ID block EOF;
block: BRACKETL NEWLINE* (stmt NEWLINE*)* BRACKETR;
stmt: (question | if_);


question: STRING ID COL type declaration*;
declaration: EQUAL PARL value PARR;

expression: ID | PARL expression PARR | boolean_;

if_: IF_ PARL expression PARR block;
type: (BOOLEAN | MONEY | ID); // | OTHER {System.out.println("first token "+$start.getText());}
value: (INT | BOOL| compute);

compute: arithmetic_ | boolean_;
arithmetic_: INT ARITHMETIC_OP INT | PARL arithmetic_ PARR;
boolean_: INT BOOLEAN_OP INT | PARL boolean_ PARR;

// Lexer
FORM       : 'form';
IF_   : 'if';
ELSE_ : 'else';
BOOLEAN    : 'boolean';
MONEY      : 'money';

BOOL   : ('true'|'false');
INT    : [0-9]+;
ID     : [A-Za-z][A-Za-z0-9_]*;
STRING : '"' (~('"' | '\\' | '\r' | '\n'))* '"';

COL      : ':';
BRACKETL : '{';
BRACKETR : '}';
PARL     : '(';
PARR     : ')';
EQUAL    : '=';
NOT      : '!';
AND      : '&&';
OR       : '||';

ARITHMETIC_OP: MUL | DIV | ADD | SUB;
MUL : '*';
DIV : '/';
ADD : '+';
SUB : '-';

BOOLEAN_OP: GT | LT | LTE | GTE | EQ | NEQ;
GT  : '>';
LT  : '<';
LTE : '<=';
GTE : '>=';
EQ  : '==';
NEQ : '!=';

SPACE: [ \t]+ -> skip;
NEWLINE: '\r'? '\n' -> skip;


OTHER: .;
