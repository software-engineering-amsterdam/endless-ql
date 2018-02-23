grammar QL;

form            : FORM IDENTIFIER block EOF;
block           : '{' statement+ '}';
statement       : condition | question;
condition       : IF '(' expression ')' block;
question        : IDENTIFIER ':' STRING questionType;

questionType    : type | type '=' expression;

expression      : '(' expression ')'                    # nestedExpr
                | operator expression                   # unaryExpr
                | expression operator expression        # binaryExpr
                | constant                              # constantExpr;

operator        : unaryOp
                | binaryOp;

unaryOp         : MINUS
                | NOT;

binaryOp        : arithmeticOp |logicalOp;

arithmeticOp    : MUL | DIV | PLUS | MINUS;

logicalOp       : LE | LT | GE | GT | EQ | NE | AND | OR;

constant        : INTEGER | DECIMAL | STRING | IDENTIFIER | MONEY | DATE;

type            : BOOLEANTYPE | STRINGTYPE | MONEYTYPE | INTEGERTYPE | DATETYPE | DECIMALTYPE;

// Operators
PLUS            : '+';
MINUS           : '-';
MUL             : '*';
DIV             : '/';
GT              : '>';
GE              : '>=';
LT              : '<';
LE              : '<=';
EQ              : '==';
NE              : '!=';
AND             : '&&';
OR              : '||';
NOT             : '!';

// Terms
FORM            : 'form';
IF              : 'if';
BOOLEANTYPE     : 'boolean';
STRINGTYPE      : 'string';
MONEYTYPE       : 'money';
INTEGERTYPE     : 'integer';
DATETYPE        : 'date';
DECIMALTYPE     : 'decimal';

// Literals
INTEGER         : [0-9]+;
DECIMAL         : [0-9]+ '.' [0-9]+;
MONEY           : ([0-9]+ '.' [0-9]+) | [0-9]+;
DATE            : DAY '-' MONTH '-' YEAR;
DAY             : '0'[0-9] | [1-2][0-9] | '3'[0-1];
MONTH           : '0'[1-9] | '1'[0-2];
YEAR            : [0-2][0-9][0-9][0-9];
STRING          : '"' .*? '"';
IDENTIFIER      : [a-zA-Z0-9]+;

WHITESPCAE      : [ \t\r\n]+ -> skip;
MULTI_COMMENT   : ('/*' .*? '*/') -> skip;
SINGLE_COMMENT  : '//' ~[\r\n]* -> skip;