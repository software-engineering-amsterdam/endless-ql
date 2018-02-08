grammar QL;

// Tokens
WS          : (' ' | '\t' | '\n' | '\r') ->channel (HIDDEN);

SLComment   : '//' (.)*? '\n' -> channel(HIDDEN);
  
MLCOMMENT   : '/*' (.)*? '*/' -> channel(HIDDEN);

BOOLEAN     : 'true'
            | 'false'
            | 'boolean'
            ;
		
DIGIT       : ('0'..'9');

STRING      : '"' (.)*? '"'
            | 'string'
            ;

INTEGER     : DIGIT+
            | 'integer'
            ;

DECIMAL     : DIGIT* [.] DIGIT+
            | 'float'
            ;

TWO_DIGITS  :	DIGIT DIGIT;

FOUR_DIGITS : TWO_DIGITS TWO_DIGITS;

CURRENCY    : DIGIT+ [.] TWO_DIGITS
            | 'money'
            ;

DATE        : TWO_DIGITS'-'TWO_DIGITS'-'FOUR_DIGITS
            | 'date'
            ;

ID          : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;


// Questionnaire language
form        : 'form' ID block;

block       : '{' question* '}';

question    : answerable
            | conditional
            | computed
            ; 

answerable  : identifier ':' label type;

computed    : identifier ':' label type '(' expr ')';

conditional : ifThen
            | ifThenElse
            ;

label       : STRING;

identifier  : ID;

type        : BOOLEAN
            | STRING
            | INTEGER
            | DECIMAL
            | CURRENCY
            | DATE
            ;

ifThen      : 'if' '(' expr ')' block;

ifThenElse  : 'if' '(' expr ')' block 'else' block;

// Expressions
expr        : unExpr
            | mulExpr
            | addExpr
            | relExpr
            | andExpr
            | orExpr
            ;
	
primary     : type
            | identifier
            ;

unExpr      : '+' unExpr
            | '-' unExpr
            | '!' unExpr
            |  primary
            ;    
    
mulExpr     : unExpr ('*'|'/') unExpr; 
  
addExpr     : mulExpr ('+'|'-') mulExpr; 
  
relExpr     : addExpr ('<'|'<='|'>'|'>='|'=='|'!=') addExpr;
    
andExpr     : relExpr '&&' relExpr;

orExpr      : andExpr '||' andExpr;

