grammar QL;

INTEGER     : [1-9][0-9]* ;
ID          : [a-zA-Z0-9_]+ ;
TEXT        : '"' .*? '"' ;

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

FALSE       : 'false' ;
TRUE        : 'true' ;
OR          : '||' ;
AND         : '&&' ;
NOT         : '!' ;

bool        : FALSE | TRUE ;

unaryOp     : SUB | ADD | NOT;
compOp      : LT | LTE | GTE | GT | NE | EQ ;
logicalOp   : OR | AND | NOT ;
arithmOp    : SUB | ADD | DIV | MUL ;

WS          : [ \t\r\n]+ -> skip ;
COMMENT     : '//' .*? '\n' -> skip ;

expression  : INTEGER                                  # IntConst
            | bool                                     # BoolConst
            | unaryOp expression                       # UnaryExpr
            | lhs=expression arithmOp rhs=expression   # ArithmExpr
            | lhs=expression compOp rhs=expression     # CompExpr
            | lhs=expression logicalOp rhs=expression  # LogicalExpr
            | name                                     # Var ;

form        : 'form' name '{' statement+ '}' ;
name        : ID ;
statement   : question | conditional ;
question    : name ':' TEXT answerType ( '=' '(' expression ')' )?;
answerType : 'boolean' | 'integer' | 'string' ;

conditional : 'if' '(' condition=expression ')' '{' thenBlock=statement+ '}' ( 'else' '{' elseBlock=statement+ '}' )? ;
