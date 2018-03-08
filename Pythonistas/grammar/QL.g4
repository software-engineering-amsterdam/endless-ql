grammar QL;

// Parser
form: FORM ID block EOF;
block: BRACKETL NEWLINE* (stmt NEWLINE*)* BRACKETR;
stmt: (question | if_conditional);


question: STRING ID COL type_declaration declaration*;
declaration: ASSIGN (PARL)+ expression (PARR)+;

expression: type_declaration;

if_conditional: IF_TOKEN (PARL)+ expression (PARR)+ block;
type_declaration: (BOOLEAN | MONEY | INT | ID); // | OTHER {System.out.println("first token "+$start.getText());}

// Lexer
FORM:   'form';
IF_TOKEN: 'if';
ELSE_TOKEN: 'else';
BOOLEAN: 'boolean';
MONEY: 'money';

INT :   [0-9]+;
ID  :   [A-Za-z][A-Za-z0-9_]*;
STRING : '"' (~('"' | '\\' | '\r' | '\n'))* '"';

COL: ':';
BRACKETL: '{';
BRACKETR: '}';
PARL: '(';
PARR: ')';
ASSIGN: '=';
NOT: '!';
AND: '&&';
OR: '||';

MATH_OPERATOR: MUL | DIV | ADD | SUB;
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';

BOOL_OPERATOR: GT | LT | LTE | GTE | EQ | NEQ;
GT: '>';
LT: '<';
LTE: '<=';
GTE: '>=';
EQ: '==';
NEQ: '!=';

SPACE: [ \t]+ -> skip;
NEWLINE: '\r'? '\n' -> skip;


OTHER: .;
