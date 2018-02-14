grammar QL;

// Tokens
WS          : (' ' | '\t' | '\n' | '\r') ->channel (HIDDEN);

MLCOMMENT   : '/*' (.)*? '*/' -> channel(HIDDEN);

SLCOMMENT   : '//' (.)*? '\n' -> channel(HIDDEN);

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
form                : 'form' identifier block;

statement           : block
                    | question
                    | ifThenElse
                    | ifThen
                    ;
            
block               : '{' statement* '}';

question            : computedQuestion
                    | answerableQuestion
                    ;

computedQuestion    : lbl=label identifier ':' type '=' '(' expr ')';

answerableQuestion  : lbl=label identifier ':' type;

type                : 'boolean'
                    | 'string'
                    | 'integer'
                    | 'decimal'
                    | 'money'
                    | 'date'
                    ;

label               : STRING;

literal             : BOOLEAN
                    | STRING
                    | INTEGER
                    | DECIMAL
                    | MONEY
                    | DATE
                    ;

identifier          : ID;

ifThen              : 'if' '(' condition=expr ')' thenStmt=statement;

ifThenElse          : 'if' '(' condition=expr ')' thenStmt=statement 'else' elseStmt=statement;

// Expressions
expr        : literal
            | identifier
            | ('+' | '-') expr
            | expr ('+'|'-') expr
            | expr ('*'|'/') expr
            | expr ('<'|'>'|'<='|'>='|'!='|'==') expr
            | expr '||' expr
            | expr '&&' expr
            | '(' expr ')'
            | '!' expr
            ;
