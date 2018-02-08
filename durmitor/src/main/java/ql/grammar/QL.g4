grammar QL;

// Tokens
WS          : (' ' | '\t' | '\n' | '\r') ->channel (HIDDEN);

SLCOMMENT   : '//' (.)*? '\n' -> channel(HIDDEN);

MLCOMMENT   : '/*' (.)*? '*/' -> channel(HIDDEN);

BOOLEAN     : 'true'
            | 'false'
            ;

STRING      : '"' (.)*? '"';

DATE        : TWO_DIGITS'-'TWO_DIGITS'-'FOUR_DIGITS;

MONEY       : DIGIT+ [.] TWO_DIGITS;

DECIMAL     : DIGIT* [.] DIGIT+;

INTEGER     : DIGIT+;

FOUR_DIGITS : TWO_DIGITS TWO_DIGITS;

TWO_DIGITS  : DIGIT DIGIT;

DIGIT       : ('0'..'9');

ID          : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// Questionnaire language
form        : 'form' identifier block;

block       : '{' question* '}';

question    : conditional
            | computed
            | answerable
            ; 

conditional : ifThenElse
            | ifThen
            ;

computed    : answerable '(' expr ')';

answerable  : identifier ':' label typeLabel;

label       : STRING;

typeLabel   : 'boolean'
            | 'string'
            | 'integer'
            | 'decimal'
            | 'money'
            | 'date'
            ;

type        : BOOLEAN
            | STRING
            | INTEGER
            | DECIMAL
            | MONEY
            | DATE
            ;

identifier  : ID;

literal     : type;

ifThenElse  : ifThen 'else' block;

ifThen      : 'if' '(' expr ')' block;

// Expressions
andExpr     : orExpr '&&' orExpr;

orExpr      : relExpr '||' relExpr;

relExpr     : mulExpr ('<'|'<='|'>'|'>='|'=='|'!=') mulExpr;
    
mulExpr     : addExpr ('*'|'/') addExpr; 
  
addExpr     : unExpr ('+'|'-') unExpr; 
  
unExpr      : primary
            | '+' unExpr
            | '-' unExpr
            | '!' unExpr
            ;    
    
primary     : literal
            | identifier
            ;

expr        : andExpr
            | orExpr
            | relExpr
            | mulExpr
            | addExpr
            | unExpr
            ;
