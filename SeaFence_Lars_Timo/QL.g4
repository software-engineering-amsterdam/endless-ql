/* https://tomassetti.me/antlr-mega-tutorial/ */

grammar QL;

/*
 * Parser rules
 */

form: 'form' form_id '{' question* '}' EOF;
question: STR var ':' type;
form_id: NAME;
var: NAME;
type: 'int' | 'boolean';

/*
 * Lexer rules
 */

WS:	[ \t\n\r]+ -> skip;
COMMENT: '/*' .*? '*/' -> skip;

NUMBER: [0-9]+;
STR: '"' .*? '"';
NAME: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;