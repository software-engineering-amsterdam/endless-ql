// Define a grammar called QL
grammar QL;

root            : FORM IDENTIFIER block EOF;
block           : '{' (condition | question)* '}';
condition       : IF '(' expression ')' block;
question        : identifier ':' questionString questionType;

identifier      : IDENTIFIER;
questionString  : STRING;
questionType    : (type | type '=' expression);

// Expressions, prioritized
expression      : '(' expression ')'
                | MINUS expression
                | NOT expression
                | expression (MUL | DIV) expression
                | expression (PLUS | MINUS) expression
                | expression (LE | LT | GE | GT) expression
                | expression (EQ | NE) expression
                | expression AND expression
                | expression OR expression
                | constant;

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