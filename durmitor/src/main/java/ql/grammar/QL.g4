grammar QL;

// Tokens
WS          : (' ' | '\t' | '\n' | '\r') ->channel (HIDDEN);

MLCOMMENT   : '/*' (.)*? '*/' -> channel(HIDDEN);

SLCOMMENT   : '//' (.)*? '\n' -> channel(HIDDEN);

BOOLEAN     : 'true'
            | 'false'
            ;

STRING      : '"' (.)*? '"';

DATE        : DIGIT DIGIT '-'DIGIT DIGIT'-'DIGIT DIGIT DIGIT DIGIT;

DIGIT       : ('0'..'9');

LOWERCASE   : ('a'..'z');
UPPERCASE   : ('A'..'Z');
LETTER      : (LOWERCASE | UPPERCASE);

UPPERCASE3  : UPPERCASE UPPERCASE UPPERCASE;

ID          : LETTER (LETTER | DIGIT | '_')*;

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

currency            : UPPERCASE3;

type                : 'boolean' #BooleanType
                    | 'string'  #StringType
                    | 'integer' #IntegerType
                    | 'decimal' #DecimalType
                    | currency  #MoneyType
                    | 'date'    #DateType
                    ;

label               : STRING;

digit               : DIGIT;

integer             : digit+;

decimal             : integer '.' digit+;

number              : integer #IntegerLiteral
                    | decimal #DecimalLiteral
                    ;
                    
money               : currency value=integer('.' digit (digit)?)?;
                    
literal             : BOOLEAN   #BooleanLiteral
                    | STRING    #StringLiteral
                    | money     #MoneyLiteral
                    | number    #NumberLiteral
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
