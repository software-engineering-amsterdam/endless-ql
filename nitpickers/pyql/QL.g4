grammar QL;

/*
 * Parser rules
 */

 form              : 'form' identifier '{' block '}' ;

 conditional_block : 'if' '(' expression ')' '{' block '}' ;

 block             : statement+ ;

 expression        : '!' expression
                   | '(' expression ')'
                   | orExpression ;

 statement         : quest | conditional_block ;

 quest             : identifier ':' STR quest_type ;

 quest_type        : 'boolean' | 'text' | 'int' | 'date' | 'date' | 'money';

 unExpression  : literal | identifier;

 mulExpression : unExpression (('*' | '/') unExpression)*;

 addExpression : mulExpression (('+' | '-') mulExpression)*;

 relExpression : addExpression (('<' | '<=' | '>' | '>=' | '==' | '!=') addExpression)* ;

 andExpression : relExpression ('&&' relExpression)* ;

 orExpression : andExpression ('||' andExpression)* ;

 literal : MONEY | DECIMAL | INT | STR | BOOL ;

 identifier : IDENTIFIER;

/*
 * Lexer rules
 */

WHITESPACE   : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

COMMENT      : '/*' .*? '*/' -> channel(HIDDEN);

LINE_COMMENT : '//'.*? ~[\r\n]* -> channel(HIDDEN);

MONEY        : [1-9]+'.'([0-9] [0-9] [0-9]);

DECIMAL      : INT '.' [0-9]+;

INT          : ('1'..'9')+('0'..'9')*;//rejects leading zeros

STR          : '"' .*? '"';

BOOL         : 'true' | 'false';

IDENTIFIER   : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
