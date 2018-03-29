grammar QL;


// Lexical parts
// Grammar is whitespace insensative
WS: [ \n\t\r]+      -> skip;
LC: '//'~[\r\n]+    -> skip;

// Top level element indicator
FORM:       'form';

// Data types
DATE:       'date';
MONEY:      'money';
STRING:     'string';
DECIMAL:    'decimal';
BOOLEAN:    'boolean';
INTEGER:    'integer';

IF:         'if';
// Reserved boolean values
BOOLVAL:    ('true' | 'false');

// Variable names, also form name
NAME:       [a-zA-Z][a-zA-Z0-9]*;

// ISO8601 date notation
DATVAL:     [0-9]+'-'[0-9]+'-'[0-9]+;

INTVAL:     [0-9]+;
DECVAL:     [0-9]+'.'[0-9]+;
MONVAL:     [A-Z][A-Z][A-Z][+-]?[0-9]*[.]?[0-9]*;
STRVAL:     '"'~['\\\r\n]*?'"';

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
dataType
    : DATE
    | MONEY
    | STRING
    | DECIMAL
    | BOOLEAN
    | INTEGER
    ;

value
    : STRVAL
    | INTVAL
    | MONVAL
    | DECVAL
    | BOOLVAL
    | DATVAL
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
    | ifBlock
    ;

expression
    : NAME                                              # variable
    | value                                             # rawValue
    | LP expression RP                                  # parentheses
    | NOT expression                                    # negation
    | expression op=(DIV | MULT) expression             # arithExpression
    | expression op=(MIN | PLUS) expression             # arithExpression
    | expression op=(LT | LTE | GT | GTE) expression    # compExpression
    | expression op=(EQ | NEQ) expression               # compExpression
    | expression op=AND expression                      # boolExpression
    | expression op=OR expression                       # boolExpression
    ;

question
    : STRVAL NAME COLON dataType (ASSIGN expression)?
    ;

ifBlock
    : IF LP expression RP NAME formBlock
    ;
