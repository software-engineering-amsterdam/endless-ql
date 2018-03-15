lexer grammar QlLexer;

WS: [ \t\n]+ -> skip ;

BOOL_T: 'boolean';
MONEY_T: 'money';
INTEGER_T: 'integer';
STRING_T: 'string';

FORM: 'form';

ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';

OCB: '{';
CCB: '}';

OB: '(';
CB: ')';

ASS: '=';
DD: ':';

GT: '>';
LT: '<';
LTE: '<=';
GTE: '>=';
EQ: '==';
NEQ: '!=';

CON: '&&';
DIS: '||';

NOT: '!';

IF: 'if';

fragment DIGIT: '0'..'9';
INTEGER: DIGIT+;
/* NUMBER: ('0'..'9')+; */

FLOAT: ('0' .. '9') + ('.' ('0' .. '9') +)?;
IDENTIFIER: [A-Za-z_][A-Za-z_0-9]*;

/* Escape double quoted in string in visitor */
STRINGLIT: '"' ( '\\' [btnfr"'\\] | ~[\r\n\\"] )* '"';


