grammar QL;

/*
 * Parser rules
 */

form              : 'form' identifier '{' block '}' ;

ifStatement       : 'if' '(' expression ')' '{' block '}' ;

ifElseStatement   : 'if' '(' expression ')' '{' block '}' 'else' '{' block '}' ;

block             : statement+;

statement         : question | ifStatement | ifElseStatement ;

question          : identifier ':' STRING questionType                    #basicQuestion
                  | identifier ':' STRING questionType '(' expression ')' #computedQuestion
                  ;

questionType      : 'boolean' #booleanType
                  | 'string'  #stringType
                  | 'integer' #integerType
                  | 'date'    #dateType
                  | 'decimal' #decimalType
                  | 'money'   #moneyType
                  ;

expression        : orExpression ;

orExpression      : andExpression ('||' andExpression)* ;

andExpression     : relExpression ('&&' relExpression)* ;

relExpression     : addExpression (('<' | '<=' | '>' | '>=' | '==' | '!=') addExpression)* ;

addExpression     : mulExpression (addOperator mulExpression)*;

addOperator       : ('+' | '-') ;

mulExpression     : unExpression (mulOperator unExpression)*;

mulOperator       : ('*' | '/') ;

unExpression      : ('!' | '-') unExpression #negNotUnExpression
                  | primary                  #primaryUnExpression
                  ;

primary           : literal | identifier | '(' expression ')';

literal           : MONEY   #moneyLiteral
                  | DECIMAL #decimalLiteral
                  | INT     #intLiteral
                  | STRING  #stringLiteral
                  | BOOL    #boolLiteral
                  ;

identifier        : IDENTIFIER ;

/*
 * Lexer rules
 */

WHITESPACE   : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

COMMENT      : '/*' .*? '*/' -> channel(HIDDEN);

LINE_COMMENT : '//'.*? ~[\r\n]* -> channel(HIDDEN);

MONEY        : [0-9] ([1-9] [0-9]*)? '.' [0-9] [0-9];

DECIMAL      : [0-9] ([1-9] [0-9]*)? '.' [0-9]+;

INT          : [1-9]+[0-9]*;//rejects leading zeros

STRING       : '"' .*? '"';

BOOL         : 'true' | 'false';

IDENTIFIER   : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
