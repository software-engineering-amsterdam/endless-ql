grammar QL;

/*
 * Parser rules
 */

 form              : 'form' IDENTIFIER '{' statement+ '}' ;

 conditional_block : 'if' '(' expression ')' '{' statement+ '}' ;

 expression        : 'true'|'false' ;

 //rel_expression    :

 //unary_expresstion : '!';

 statement         : quest | conditional_block ;

 quest             : IDENTIFIER ':' STR typ ;

 typ               : 'boolean' | 'text' | 'int' | DATE | DECIMAL | MONEY ;


/*
 * Lexer rules
 */

WHITESPACE   : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

COMMENT      : '/*' .*? '*/' -> channel(HIDDEN);

LINE_COMMENT : '//'.*? -> channel(HIDDEN);

INT          : ('0'..'9')+;

STR          : '"' .*? '"';

BOOL         : 'true' | 'false';

MONEY        : ([0-9]+.[0-9]{3});

DECIMAL      : ([0-9]+.[0-9]+);

DATE         : 'date';

IDENTIFIER   : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
