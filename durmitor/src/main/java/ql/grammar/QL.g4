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

AND         : '&&';
OR          : '||';
LT          : '<';
LE          : '<=';
GT          : '>';
GE          : '>=';
EQ          : '==';
NE          : '!=';
PLUS        : '+';
MINUS       : '-';
MULTIPLY    : '*';
DIVIDE      : '/';
NOT         : '!';

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

computedQuestion    : label identifier ':' type '=' '(' expr ')';

answerableQuestion  : label identifier ':' type;

type                : 'boolean' #BooleanType
                    | 'string'  #StringType
                    | 'integer' #IntegerType
                    | 'decimal' #DecimalType
                    | 'money'   #MoneyType
                    | 'date'    #DateType
                    ;

label               : STRING;

literal             : BOOLEAN   #BooleanLiteral
                    | STRING    #StringLiteral
                    | INTEGER   #IntegerLiteral
                    | DECIMAL   #DecimalLiteral
                    | MONEY     #MoneyLiteral
                    | DATE      #DateLiteral
                    ;

identifier          : ID;

ifThen              : 'if' '(' condition=expr ')' thenStmt=statement;

ifThenElse          : 'if' '(' condition=expr ')' thenStmt=statement 'else' elseStmt=statement;

// Expressions
primary : literal
        | identifier
        ;
        
expr    : ex = primary                                      #PriExpr
        | '(' ex = expr ')'                                 #BraExpr
        |  op = (PLUS|MINUS|NOT) ex = expr                  #PreExpr
        | lhs = expr op = (MULTIPLY|DIVIDE) rhs = expr      #MulExpr
        | lhs = expr op = (PLUS|MINUS) rhs = expr           #AddExpr
        | lhs = expr op = (GE|GT|LT|LE|EQ|NE) rhs = expr    #RelExpr
        | lhs = expr op = AND rhs = expr                    #AndExpr
        | lhs = expr op = OR rhs = expr                     #OrExpr
        ;
