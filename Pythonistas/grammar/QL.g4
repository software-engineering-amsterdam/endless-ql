grammar QL;

// Parser
form: FORM ID block EOF;
block: BRACKETL NEWLINE* (statement NEWLINE*)* BRACKETR;
statement: (question | assignment | conditional);

question: ID COL STRING typeDeclaration;
assignment: STRING ID COL typeDeclaration ASSIGN PARL expression PARR;

expression: BOOLEAN | INT | ID | PARL expression PARR | NOT expression
            | expression BOOL_OPERATOR expression
            | expression MATH_OPERATOR expression
            | expression AND expression
            | expression OR expression;

conditional: if_conditional | (if_conditional else_conditional);
if_conditional: IF_TOKEN PARL expression PARR block;
else_conditional: ELSE_TOKEN block;

typeDeclaration: BOOLEAN | MONEY;

// Lexer
FORM:   'form';
IF_TOKEN: 'if';
ELSE_TOKEN: 'else';

INT :   [0-9]+;
ID  :   [A-Za-z][A-Za-z0-9_]*;
STRING : '"' (~('"' | '\\' | '\r' | '\n'))* '"';

BOOLEAN: 'boolean';
MONEY: 'money';

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

WHITESPACE: [ \t]+ -> skip;
NEWLINE: '\r'? '\n' -> skip;
