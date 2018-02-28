/** Grammar for questionnaire form */
grammar QL;

/** Parser rules */
form : FORM IDENTIFIER block EOF; // form

block : CURLY_BRACE_L NEWLINE ((ifStatement | question | statement) NEWLINE)* CURLY_BRACE_R; // content

question : IDENTIFIER COLON STR type;

statement : IDENTIFIER COLON STR type expression;

expression:
    BOOL |
    MON |
    INT |
    DEC |
    STR |
    IDENTIFIER |
    BRACE_L expression BRACE_R |
    expression operator expression |
    NOT expression
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
   ADD | SUB | MUL | DIV | REM
;

ifStatement : IF BRACE_L expression BRACE_R block*;

type:  BOOLEANTYPE | STRINGTYPE | INTEGERTYPE | MONEYTYPE | DATETYPE | DECIMALTYPE ;

/** Lexer rules (tokens)*/
WS : (' ' | '\t')+ -> channel(HIDDEN);

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
STR : '"' .*? '"';
INT : ('-')? DIGIT+;
BOOL : ('true' | 'false');
MON : DIGIT+ '.' DIGIT DIGIT;
DEC : DIGIT+  '.'  DIGIT+;
NEWLINE : '\r'? '\n';
