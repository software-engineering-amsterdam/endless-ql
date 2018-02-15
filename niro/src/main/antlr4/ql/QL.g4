grammar QL;

FORM        : 'form' ;
IF          : 'if' ;
ELSE        : 'else' ;
ASSIGN      : '=' ;

CURLY_L     : '{' ;
CURLY_R     : '}' ;

BRACK_L     : '(' ;
BRACK_R     : ')' ;

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
NEG         : '!' ;

BOOLEAN     : 'boolean' ;
INTEGER     : 'integer' ;
STRING      : 'string' ;

FALSE       : 'false' ;
TRUE        : 'true' ;

IntValue    : [1-9][0-9]* ;
Ident       : [a-zA-Z0-9_]+ ;
TEXT        : '"' .*? '"' { setText(getText().substring(1, getText().length() - 1)); };

WS          : [ \t\r\n]+ -> skip ;
COMMENT     : '//' .*? '\n' -> skip ;

bool        : FALSE | TRUE ;

unaryOp     : SUB | NEG;
compOp      : LT | LTE | GTE | GT | NE | EQ ;
logicalOp   : OR | AND | NEG ;
arithmOp    : SUB | ADD | DIV | MUL ;

expression  : IntValue                                 # IntConst
            | Ident                                    # Var
            | bool                                     # BoolConst
            | lhs=expression arithmOp rhs=expression   # ArithmExpr
            | lhs=expression compOp rhs=expression     # CompExpr
            | lhs=expression logicalOp rhs=expression  # LogicalExpr
            | unaryOp expression                       # UnaryExpr
            | BRACK_L expression BRACK_R               # GroupExpr ;

form        : FORM Ident CURLY_L statement+ CURLY_R EOF ;
statement   : question | conditional ;
question    : Ident D_COLON TEXT answerType ( ASSIGN BRACK_L expression BRACK_R )?;
answerType  : BOOLEAN | INTEGER | STRING ;

conditional : IF BRACK_L condition=expression BRACK_R CURLY_L thenBlock+=statement+ CURLY_R ( ELSE CURLY_L elseBlock+=statement+ CURLY_R )? ;
