grammar QL;

import  QL_Common ;

FORM         : 'form' ;
IF           : 'if' ;
ELSE         : 'else' ;
ASSIGN       : '=' ;

FALSE        : 'false' ;
TRUE         : 'true' ;

LT           : '<' ;
LTE          : '<=' ;
EQ           : '==' ;
NE           : '!=' ;
GTE          : '>=' ;
GT           : '>' ;

SUB          : '-' ;
ADD          : '+' ;
DIV          : '/' ;
MUL          : '*' ;

OR           : '||' ;
AND          : '&&' ;
NEG          : '!' ;
PERIOD       : '.' ;

DateValue    : [0-9][0-9][0-9][0-9] '-' [0-9][0-9] '-' [0-9][0-9] ;
IntValue     : [1-9][0-9]* ;
DecValue     : [1-9][0-9]* PERIOD [0-9]+ ;
TEXT         : '"' .*? '"' { setText(getText().substring(1, getText().length() - 1)); };

bool        : FALSE | TRUE ;

form        : FORM Identifier CURLY_LEFT statement+ CURLY_RIGHT EOF ;

statement   : question
            | conditional ;

// Precedence as specified by: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html
expression : BRACK_LEFT expression BRACK_RIGHT                 # GroupExpr
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
           | DateValue                                         # DateConst
           | TEXT                                              # StringConst
           | bool                                              # BoolConst ;

block      : CURLY_LEFT statement+ CURLY_RIGHT
           | statement ;

question    : label=TEXT Identifier DOUBLE_COLON answerType ( ASSIGN expression )?;
conditional : IF BRACK_LEFT condition=expression BRACK_RIGHT thenBlock+=block ( ELSE elseBlock+=block )? ;

answerType  : BOOLEAN | INTEGER | DECIMAL | MONEY | DATE | STRING ;

unaryOp          : SUB | NEG ;
multiplicativeOp : MUL | DIV ;
additiveOp       : ADD | SUB ;
relationalOp     : LT | GT | LTE | GTE ;
equalityOp       : EQ | NE ;
