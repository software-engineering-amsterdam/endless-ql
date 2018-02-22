grammar QL;

/*
 * Parser rules
 * TODO: () -> (3 + (2 - 2))
 */

form              : 'form' identifier '{' block '}' ;

ifStatement       : 'if' '(' expression ')' '{' block '}' ;

ifElseStatement   : 'if' '(' expression ')' '{' block '}' 'else' '{' block '}' ;

block             : statement+;

statement         : question
                  | ifStatement
                  | ifElseStatement
                  ;

question          : identifier ':' STRING questionType ;

questionType      : 'boolean' | 'string' | 'integer' | 'date' | 'decimal' | money;

expression        : '!' orExpression
                  | orExpression
                  ;

orExpression      : andExpression ('||' andExpression)* ;

andExpression     : relExpression ('&&' relExpression)* ;

relExpression     : addExpression (('<' | '<=' | '>' | '>=' | '==' | '!=') addExpression)* ;

addExpression     : mulExpression (addOperator mulExpression)*;

addOperator       : '+' | '-' ;

mulExpression     : unExpression (mulOperator unExpression)*;

mulOperator       : '*' | '/' ;

unExpression      : literal | identifier | '(' expression ')';

literal           : MONEY | DECIMAL | INT | STRING | BOOL ;

identifier        : IDENTIFIER ;

money             : 'money' | 'money(' addExpression ')' ;

/*
 * Lexer rules
 */

WHITESPACE   : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

COMMENT      : '/*' .*? '*/' -> channel(HIDDEN);

LINE_COMMENT : '//'.*? ~[\r\n]* -> channel(HIDDEN);

MONEY        : [1-9]+'.'([0-9] [0-9] [0-9]);

DECIMAL      : INT '.' [0-9]+;

INT          : ('1'..'9')+('0'..'9')*;//rejects leading zeros

STRING       : '"' .*? '"';

BOOL         : 'true' | 'false';

IDENTIFIER   : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
