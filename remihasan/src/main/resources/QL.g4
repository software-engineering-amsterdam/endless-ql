// Define a grammar called QL
grammar QL;
r  : 'form' IDENTIFIER block EOF;
WS : [ \t\r\n]+ -> skip ;
COMMENT : ('/*' .*? '*/') -> skip;
block : '{' (condition | question)* '}';
condition : IF '(' expression ')' block;
question : IDENTIFIER ':' questionString ((questionvaluetype '=' expression) | questionvaluetype);

questionString : STRING;

// Expressions, possibly nested
expression :
    value
    | '(' expression ')'
    | expression operation expression;

// Operators such as '+', '>=' and '&&'
operation :
    SUMOPERATOR
    | SUBTRACTOPERTOR
    | MULTIPLYOPERATOR
    | DIVIDEOPERATOR
    | GT
    | GEQ
    | LT
    | LEQ
    | EQ
    | AND
    | OR
    | NOT;
SUMOPERATOR : '+';
SUBTRACTOPERTOR : '-';
MULTIPLYOPERATOR : '*';
DIVIDEOPERATOR : '/';
GT : '>';
GEQ: '>=';
LT : '<';
LEQ : '<=';
EQ : '==';
AND : '&&';
OR : '||';
NOT : '!';





// Question answer value types
questionvaluetype :
    BOOLEANTYPE
    | STRINGTYPE
    | INTEGERTYPE
    | DATETYPE
    | DECIMALTYPE
    | MONEYTYPE;
BOOLEANTYPE : 'boolean';
STRINGTYPE : 'string';
INTEGERTYPE : 'integer';
DATETYPE : 'date';
DECIMALTYPE : 'decimal';
MONEYTYPE : 'money';

// All value types, numbers, date, etc.
value :
    INTEGER
    | DECIMAL
    | DATE
    | MONEY
    | STRING
    | IDENTIFIER;
IF : 'if';
INTEGER : [0-9]+;
DECIMAL : [0-9]+ '.' [0-9]+;
DATE :  ([0-9] | [0-3] [0-9]) '-' ([0-9] | [0-3] [0-9]) '-' ([0-9] [0-9] [0-9] [0-9]);
MONEY : ([0-9]+ '.' [0-9]+) | [0-9]+;
STRING : '"' .*? '"';
IDENTIFIER :  ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;