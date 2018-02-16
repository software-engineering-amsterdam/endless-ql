/** Grammar for questionnaire form */
grammar FormQL;

/** Parser rules */
form : FORM IDENTIFIER CURLY_BRACE_L block CURLY_BRACE_R EOF; // form


block : CURLY_BRACE_L (ifStatement | question | statement)* CURLY_BRACE_R; // content

question : IDENTIFIER COLON STR type;

statement : IDENTIFIER COLON STR type value;

value:
    BOOL |
    MON |
    INT |
    DEC |
    IDENTIFIER |
    BRACE_L value BRACE_R |
    value operator value
;

operator:
    boolOperator |
    comparisonOperator |
    numberOperator
;

boolOperator:
    EQT | NEQT | AND | OR
;

comparisonOperator:
    EQT | GRT | LST | GRTE | LSTE
;

numberOperator:
    NOT | ADD | SUB | MUL | DIV | REM
;

ifStatement : IF BRACE_L boolean BRACE_R block*;

type:  BOOLEANTYPE | STRINGTYPE | INTEGERTYPE | MONEYTYPE | DATETYPE | DECIMALTYPE ;

/** Lexer rules (tokens)*/
BOOLEANTYPE: 'boolean';
STRINGTYPE: 'string';
INTEGERTYPE: 'integer';
MONEYTYPE: 'money' | 'currency';
DATETYPE: 'date';
DECIMALTYPE: 'decimal';

FORM : 'form';
IF : 'if';
COLON : ':';

// seperators
CURLY_BRACE_L : '{';
CURLY_BRACE_R: '}';
BRACE_L : '(';
BRACE_R : ')';

// operators
ADD : '+';
SUB : '-';
MUL : '*';
DIV : '/';
REM : '%';

EQT : '==';
GRT : '>';
LST : '<';
GRTE : '>=';
LSTE : '<=';

AND : '&&';
NEQT : '!=';
OR : '||';
NOT : '!';

// ESC_SEQ : '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\');

// literals
fragment DIGIT : ('0'..'9');
fragment LETTER : ('a'..'z'|'A'..'Z');

IDENTIFIER: LETTER (LETTER | DIGIT | '_')*;
STR : '“' .*? '”';
INT : ('-')? DIGIT+;
BOOL : ('true' | 'false');
MON : DIGIT+ '.' DIGIT DIGIT;
DEC : DIGIT+  '.'  DIGIT+;
