// Define a grammar called QL
grammar QL;

root            : 'form' IDENTIFIER block EOF;
block           : '{' (condition | question)* '}';
condition       : IF '(' expression ')' block;
question        : identifier ':' questionString questionType;

identifier      : IDENTIFIER;
questionString  : STRING;
questionType    : (type | type '=' expression);

// Expressions, possibly nested
expression      : value
                | '(' expression ')'
                | expression operation expression;

// Operators such as '+', '>=' and '&&'
operation       : ADD
                | SUB
                | MUL
                | DIV
                | GT
                | GEQ
                | LT
                | LEQ
                | EQ
                | AND
                | OR
                | NOT;

// Question answer value types
type            : BOOLEANTYPE
                | STRINGTYPE
                | INTEGERTYPE
                | DATETYPE
                | DECIMALTYPE
                | MONEYTYPE;

// All value types, numbers, date, etc.
value           : INTEGER
                | DECIMAL
                | DATE
                | MONEY
                | STRING
                | IDENTIFIER;

ADD             : '+';
SUB             : '-';
MUL             : '*';
DIV             : '/';
GT              : '>';
GEQ             : '>=';
LT              : '<';
LEQ             : '<=';
EQ              : '==';
AND             : '&&';
OR              : '||';
NOT             : '!';

BOOLEANTYPE     : 'boolean';
STRINGTYPE      : 'string';
INTEGERTYPE     : 'integer';
DATETYPE        : 'date';
DECIMALTYPE     : 'decimal';
MONEYTYPE       : 'money';

IF              : 'if';
INTEGER         : [0-9]+;
DECIMAL         : [0-9]+ '.' [0-9]+;
DATE            : ([0-9] | [0-3] [0-9]) '-' ([0-9] | [0-3] [0-9]) '-' ([0-9] [0-9] [0-9] [0-9]);
MONEY           : ([0-9]+ '.' [0-9]+) | [0-9]+;
STRING          : '"' .*? '"';
IDENTIFIER      : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

COMMENT         : ('/*' .*? '*/') -> skip;
WS              : [ \t\r\n]+ -> skip;