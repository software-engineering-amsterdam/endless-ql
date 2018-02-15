grammar QL;

/*
 * Parser rules
 * TODO: () -> (3 + (2 - 2))
 */

 form              : 'form' identifier '{' block '}' ;

 conditional_block : 'if' '(' expression ')' '{' block '}' ;

 block             : statement+;

 statement         : question | conditional_block ;

 question          : identifier ':' STR question_type ;

 question_type     : 'boolean' | 'string' | 'integer' | 'date' | 'decimal' | money;

 expression        : '!' expression
                   | orExpression
                   | '(' expression ')'
                   ;

 orExpression : andExpression ('||' andExpression)* ;

 andExpression : relExpression ('&&' relExpression)* ;

 relExpression : addExpression (('<' | '<=' | '>' | '>=' | '==' | '!=') addExpression)* ;

 addExpression : mulExpression (('+' | '-') mulExpression)*;

 mulExpression : unExpression (('*' | '/') unExpression)*;

 unExpression  : literal | identifier;

 literal : MONEY | DECIMAL | INT | STR | BOOL ;

 identifier : IDENTIFIER ;

 money: 'money' | 'money(' identifier ('-'|'+'|'*'|'/') identifier ')' ;

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
