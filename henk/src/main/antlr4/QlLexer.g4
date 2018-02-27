lexer grammar QlLexer;

WS: [ \t\n]+ -> skip ;

BOOL: 'boolean';
MONEY: 'money';

FORM: 'form';

ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';

OCB: '{';
CCB: '}';

OB: '(';
CB: ')';

EQ: '=';
DD: ':';

EM: '!';

IF: 'if';


NUMBER: ('0' .. '9') + ('.' ('0' .. '9') +)?;
IDENTIFIER: [A-Za-z_][A-Za-z_0-9]*;

/* Escape double quoted in string in visitor */
STRINGLIT: '"' ( '\\' [btnfr"'\\] | ~[\r\n\\"] )* '"';


