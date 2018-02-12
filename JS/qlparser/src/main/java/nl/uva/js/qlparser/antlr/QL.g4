grammar QL;


// Lexical parts
// Grammar is whitespace insensative
WS: [ \n\t\r]+ -> channel(HIDDEN);

// Top level element indicator
FORM:       'form';

// Data types
DATE:       'date';
MONEY:      'money';
STRING:     'string';
DECIMAL:    'decimal';
BOOLEAN:    'boolean';
INTEGER:    'integer';

// Reserved boolean values
TRUE:       'true';
FALSE:      'false';

INTVAL:     [0-9]+;
DECVAL:     [0-9]+'.'[0-9]+;
STRVAL:     '"'~['\\\r\n]+'"';

IF:         'if';

// Variable names, also form name
NAME:       [a-zA-Z]+;

COLON:      ':';
ASSIGN:     '=';

LP:         '(';
RP:         ')';
LB:         '{';
RB:         '}';

OR:         '||';
AND:        '&&';
NOT:        '!';

LT:         '<';
GT:         '>';
LTE:        '<=';
GTE:        '>=';

EQ:         '==';
NEQ:        '!=';

PLUS:       '+';
MIN:        '-';
MULT:       '*';
DIV:        '/';

// Token groupings
datatype
    : DATE
    | MONEY
    | STRING
    | DECIMAL
    | BOOLEAN
    | INTEGER
    ;

boolval
    : TRUE
    | FALSE
    ;

values
    : STRVAL
    | INTVAL
    | DECVAL
    | boolval
    ;

boolOp
    : OR
    | AND
    ;

compOp
    : LT
    | LTE
    | GT
    | GTE
    | EQ
    | NEQ
    ;

arithOp
    : MIN
    | PLUS
    | DIV
    | MULT
    ;

oper
    : boolOp
    | compOp
    | arithOp
    ;

// Higher level parsing
// Entry point for this grammar
form
    : FORM NAME formBlock
    ;

formBlock
    : LB formExpression* RB
    ;

formExpression
    : question
    | IF LP expression RP formBlock
    ;

expression
    : NAME
    | values
    | LP expression RP
    | NOT expression
    | expression oper expression
    ;

question
    : STRVAL NAME COLON datatype (ASSIGN expression)?
    ;
