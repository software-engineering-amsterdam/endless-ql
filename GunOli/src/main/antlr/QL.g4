grammar QL;

head            : FORM IDENTIFIER block EOF;
block           : '{' statement+ '}';
statement       : condition | question;
condition       : IF '(' expression ')' block;
question        : IDENTIFIER ':' STRING questionType;

questionType    : type | type '=' expression;

expression      : '(' expression ')'                                # nestedExpr
                | operator expression                               # unaryExpr
                | left=expression operator right=expression         # binaryExpr
                | constant                                          # constantExpr;

operator        : unaryOp | binaryOp;

unaryOp         : MINUS | NOT;

binaryOp        : MUL | DIV | PLUS | MINUS | LE | LT | GE | GT | EQ | NE | AND | OR;

constant        : INTEGER                                           # integerConstant
                | BOOLEAN                                           # booleanConstant
                | DECIMAL                                           # decimalConstant
                | STRING                                            # stringConstant
                | IDENTIFIER                                        # identifierConstant
                | MONEY                                             # moneyConstant
                | DATE                                              # dateConstant;

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
BOOLEAN         : ('true'|'false');
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