grammar QL;

/*
 * Parser rules
 */

 form              : INDENTIFIER '{' statement+ '}' ;

 conditional_block : 'if' '(' expression ')' '{' statement+ '}' ;

 statement         : question | conditional_block ;

 question          : IDENTIFIER STRING type ;

 type              : STR | INT | BOOL ;

/*
 * Lexer rules
 */

WHITESPACE   : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

COMMENT      : '/*' .* '*/' -> channel(HIDDEN);

LINE_COMMENT : '//' -> channel(HIDDEN);

INT          : ('0'..'9')+;

STR          : '"' .* '"';

IDENTIFIER   : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

BOOL         : ('true'|'false');
