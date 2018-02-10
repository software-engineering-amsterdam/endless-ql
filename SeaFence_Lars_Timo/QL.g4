// https://tomassetti.me/antlr-mega-tutorial/

grammar QL;

/*
 * Parser rules
 */

form: FORM form_id '{' question '}' EOF;
question: STR var;
var: NAME ':' TYPE;
form_id: NAME;

/*
 * Lexer rules
 */
fragment F: ('F'|'f');
fragment O: ('O'|'o');
fragment R: ('R'|'r');
fragment M: ('M'|'m');
FORM: F O R M;

TYPE: (INT | BOOL);
INT: 'int';
BOOL: 'boolean';
WS:	[ \t\n\r]+ -> skip;
COMMENT: '/*' .* '*/' -> skip;

NUMBER: [0-9]+;
STR: '"' .* '"';
NAME: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;