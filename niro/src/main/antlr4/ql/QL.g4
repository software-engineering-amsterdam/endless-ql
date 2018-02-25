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
COMMA       : ',' ;

BOOLEAN     : 'boolean' ;
INTEGER     : 'integer' ;
STRING      : 'string' ;
DECIMAL     : 'decimal' ;
MONEY       : 'money' ;
DATE        : 'date' ;

FALSE       : 'false' ;
TRUE        : 'true' ;

IntValue    : [1-9][0-9]* ;
DecValue    : [1-9][0-9]* COMMA [0-9]+ ;
Identifier  : [a-zA-Z0-9_]+ ;
TEXT        : '"' .*? '"' { setText(getText().substring(1, getText().length() - 1)); };

WS          : [ \t\r\n]+ -> skip ;
COMMENT     : '//' .*? '\n' -> skip ;

bool        : FALSE | TRUE ;

form        : FORM Identifier CURLY_L statement+ CURLY_R EOF ;

statement   : question
            | conditional ;

question    : label=TEXT Identifier D_COLON answerType ( ASSIGN BRACK_L expression BRACK_R )?;
conditional : IF BRACK_L condition=expression BRACK_R CURLY_L thenBlock+=statement+ CURLY_R ( ELSE CURLY_L elseBlock+=statement+ CURLY_R )? ;

answerType  : BOOLEAN | INTEGER | DECIMAL | MONEY | DATE | STRING ;

unaryOp          : SUB | NEG ;
multiplicativeOp : MUL | DIV ;
additiveOp       : ADD | SUB ;
relationalOp     : LT | GT | LTE | GTE ;
equalityOp       : EQ | NE ;

// Precence as specified by: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html
expression : BRACK_L expression BRACK_R                        # GroupExpr
           | op=unaryOp expression                             # UnaryExpr
           | lhs=expression op=multiplicativeOp rhs=expression # MultiplicativeExpr
           | lhs=expression op=additiveOp rhs=expression       # AdditiveExpr
           | lhs=expression op=relationalOp rhs=expression     # RelationalExp
           | lhs=expression op=equalityOp rhs=expression       # EqualityExpr
           | lhs=expression op=AND rhs=expression              # LogicalAndExpr
           | lhs=expression op=OR rhs=expression               # LogicalOrExpr
           | Identifier                                        # Var
           | IntValue                                          # IntConst
           | DecValue                                          # DecConst
           | bool                                              # BoolConst ;
