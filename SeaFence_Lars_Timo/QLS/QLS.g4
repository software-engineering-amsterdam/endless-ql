grammar QLS;

/*
 * Parser rules
 */


/*
 * Lexer rules
 */

WS: [ \t\n\r]+ -> skip;
COMMENT: '/*' .*? '*/' -> skip;

NUMBER: [0-9]+;
STR: '"' .*? '"';
NAME: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;