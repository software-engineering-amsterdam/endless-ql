grammar QL;

FORM         : 'form' ;
IF           : 'if' ;
ELSE         : 'else' ;
ASSIGN       : '=' ;

BOOLEAN      : 'boolean' ;
INTEGER      : 'integer' ;
STRING       : 'string' ;
DECIMAL      : 'decimal' ;
MONEY        : 'money' ;
DATE         : 'date' ;

FALSE        : 'false' ;
TRUE         : 'true' ;

CURLY_LEFT   : '{' ;
CURLY_RIGHT  : '}' ;

BRACK_LEFT   : '(' ;
BRACK_RIGHT  : ')' ;

DOUBLE_COLON : ':' ;

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

IntValue     : [1-9][0-9]* ;
DecValue     : [1-9][0-9]* PERIOD [0-9]+ ;
Identifier   : [a-zA-Z0-9_]+ ;
TEXT         : '"' .*? '"' { setText(getText().substring(1, getText().length() - 1)); };

WS           : [ \t\r\n]+ -> skip ;
COMMENT      : '//' .*? '\n' -> skip ;

bool        : FALSE | TRUE ;

form        : FORM Identifier CURLY_LEFT statement+ CURLY_RIGHT EOF ;

statement   : question
            | conditional ;

question    : label=TEXT Identifier DOUBLE_COLON answerType ( ASSIGN expression )?;
conditional : IF BRACK_LEFT condition=expression BRACK_RIGHT CURLY_LEFT thenBlock+=statement+ CURLY_RIGHT ( ELSE CURLY_LEFT elseBlock+=statement+ CURLY_RIGHT )? ;

answerType  : BOOLEAN | INTEGER | DECIMAL | MONEY | DATE | STRING ;

unaryOp          : SUB | NEG ;
multiplicativeOp : MUL | DIV ;
additiveOp       : ADD | SUB ;
relationalOp     : LT | GT | LTE | GTE ;
equalityOp       : EQ | NE ;

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
           | TEXT                                              # StringConst
           | bool                                              # BoolConst ;
