lexer grammar QlLexer;

NEWLINE: '\r'? '\n' -> skip;
WHITESPACE: [\t]+ -> skip;

FORM:   'form';
IF_TOKEN: 'if';
ELSE_TOKEN: 'else';
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

BOOL_OPERATOR: '<' | '>' | '<=' | '>=' | '==' | '!=';
BOOL: 'true' | 'false';
