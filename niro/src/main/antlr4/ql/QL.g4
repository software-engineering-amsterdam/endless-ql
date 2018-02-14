grammar QL;

FORM        : 'form' ;
IF          : 'if' ;
ELSE        : 'else' ;
ASSIGN      : '=' ;

L_CURLY     : '{' ;
R_CURLY     : '}' ;

L_PEREN     : '(' ;
R_PEREN     : ')' ;

D_COLON     : ':' ;

LT          : '<' ;
LTE         : '<=' ;
EQ          : '==' ;
NE          : '!=' ;
GTE         : '>=' ;
GT          : '>' ;

SUB         : '-' ;
ADD         : '+' ;
DIV         : '/' ;
MUL         : '*' ;

OR          : '||' ;
AND         : '&&' ;
NOT         : '!' ;

BOOLEAN     : 'boolean' ;
INTEGER     : 'integer' ;
STRING      : 'string' ;

FALSE       : 'false' ;
TRUE        : 'true' ;

IntValue    : [1-9][0-9]* ;
Ident       : [a-zA-Z0-9_]+ ;
TEXT        : '"' .*? '"' ;

WS          : [ \t\r\n]+ -> skip ;
COMMENT     : '//' .*? '\n' -> skip ;

bool        : FALSE | TRUE ;

unaryOp     : SUB | ADD | NOT;
compOp      : LT | LTE | GTE | GT | NE | EQ ;
logicalOp   : OR | AND | NOT ;
arithmOp    : SUB | ADD | DIV | MUL ;

expression  : IntValue                                 # IntConst
            | bool                                     # BoolConst
            | unaryOp expression                       # UnaryExpr
            | lhs=expression arithmOp rhs=expression   # ArithmExpr
            | lhs=expression compOp rhs=expression     # CompExpr
            | lhs=expression logicalOp rhs=expression  # LogicalExpr
            | Ident                                    # Var ;

form        : FORM Ident L_CURLY statement+ R_CURLY EOF ;
statement   : question | conditional ;
question    : Ident D_COLON TEXT answerType ( ASSIGN L_PEREN expression R_PEREN )?;
answerType  : BOOLEAN | INTEGER | STRING ;

conditional : IF L_PEREN ( condition=expression ) R_PEREN L_CURLY ( thenBlock+=question ) R_CURLY ( ELSE L_CURLY ( elseBlock+=question ) R_CURLY )? ;
