grammar QL;

form: FORM ID block EOF;
block: BRACKETL NEWLINE* (statement NEWLINE*)* BRACKETR;
statement: (question | assignment | conditional);

question: STRING ID COL types;
assignment: STRING ID COL types ASSIGN PARL expression PARR;

expression: BOOL
            | INT
            | ID
            | PARL expression PARR
            | NOT expression
            | expression BOOL_OPERATOR expression
            | expression MATH_OPERATOR expression
            | expression AND expression
            | expression OR expression
            ;

conditional: if_conditional | (if_conditional else_conditional);
if_conditional: IF_TOKEN PARL expression PARR block;
else_conditional: ELSE_TOKEN block;

FORM:   'form';
IF_TOKEN: 'if';
ELIF_TOKEN: 'elif';
ELSE_TOKEN: 'else';
COL: ':';

MATH_OPERATOR: MUL | DIV | ADD | SUB;
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';

BOOL_OPERATOR: '<' | '>' | '<=' | '>=' | '==' | '!=';

types: 'integer' | 'boolean' | 'string' | 'date' | 'money';

BOOL: 'true' | 'false';
INT :   [0-9]+;
ID  :   [A-Za-z][A-Za-z0-9_]*;
STRING : '"' (~('"' | '\\' | '\r' | '\n'))* '"';

BRACKETL: '{';
BRACKETR: '}';
PARL: '(';
PARR: ')';
ASSIGN: '=';
NOT: '!';
AND: '&&';
OR: '||';

NEWLINE:'\r'? '\n' -> skip;
WHITESPACE  :   [ \t]+ -> skip;