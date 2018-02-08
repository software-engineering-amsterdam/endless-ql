// Define a grammar called QL
grammar QL;

root            : FORM IDENTIFIER block EOF;
block           : '{' (condition | question)* '}';
condition       : IF '(' expression ')' block;
question        : identifier ':' questionString questionType;

identifier      : IDENTIFIER;
questionString  : STRING;
questionType    : (type | type '=' expression);

// Expressions, prioritized from top to bottom
// label them for easier evaluation
// inspired by: https://stackoverflow.com/a/23092428
expression      : '(' expr=expression ')'                                   # parenExpr
                | MINUS expr=expression                                     # negExpr
                | NOT expr=expression                                       # notExpr
                | left=expression op=(MUL | DIV) right=expression           # opExpr
                | left=expression op=(PLUS | MINUS) right=expression        # opExpr
                | left=expression op=(LE | LT | GE | GT) right=expression   # boolExpr
                | left=expression op=(EQ | NE) right=expression             # boolExpr
                | left=expression op=AND right=expression                   # boolExpr
                | left=expression op=OR right=expression                    # boolExpr
                | constant                                                  # constExpr
                ;

type            : BOOLEANTYPE
                | STRINGTYPE
                | INTEGERTYPE
                | DATETYPE
                | DECIMALTYPE
                | MONEYTYPE;

constant        : INTEGER
                | DECIMAL
                | DATE
                | MONEY
                | STRING
                | IDENTIFIER;

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

// Keywords
FORM            : 'form';
BOOLEANTYPE     : 'boolean';
STRINGTYPE      : 'string';
INTEGERTYPE     : 'integer';
DATETYPE        : 'date';
DECIMALTYPE     : 'decimal';
MONEYTYPE       : 'money';
IF              : 'if';

// Literals
INTEGER         : [0-9]+;
DECIMAL         : [0-9]+ '.' [0-9]+;
DATE            : ([0-9] | [0-3] [0-9]) '-' ([0-9] | [0-3] [0-9]) '-' ([0-9] [0-9] [0-9] [0-9]);
MONEY           : ([0-9]+ '.' [0-9]+) | [0-9]+;
STRING          : '"' .*? '"';
IDENTIFIER      : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

COMMENT         : ('/*' .*? '*/') -> skip;
WS              : [ \t\r\n]+ -> skip;