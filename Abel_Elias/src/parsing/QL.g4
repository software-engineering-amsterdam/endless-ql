/** Grammar for questionnaire form */
grammar QL;

/** Parser rules */
form : FORM IDENTIFIER block EOF; // form

block : CURLY_BRACE_L NEWLINE* lineInBlock* CURLY_BRACE_R NEWLINE*; // content

lineInBlock : ifStatement NEWLINE*
    | question NEWLINE*
;

question : IDENTIFIER COLON STR type #normalQuestion
    | IDENTIFIER COLON STR type expression #fixedQuestion
;

expression: IDENTIFIER #identifier
    | booleanExpression #boolExpression
    | numberExpression #numExpression
    | STR #string
;

booleanExpression: IDENTIFIER #boolIdentifier
    | left=booleanExpression boolOperator right=booleanExpression #boolOperation
    | BRACE_L booleanExpression BRACE_R #boolBraces
    | left=numberExpression comparisonOperator right=numberExpression #compOperation
    | NOT booleanExpression #notOperation
    | BOOL #boolValue
;

numberExpression: IDENTIFIER #numIdentifier
    | BRACE_L numberExpression BRACE_R  #numBraces
    | left=numberExpression numberOperator right=numberExpression #numOperation
    | MON #moneyValue
    | INT #intValue
    | DEC #decValue
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

ifStatement : IF BRACE_L booleanExpression BRACE_R block;

type: BOOLEANTYPE   #booltype
    | STRINGTYPE    #stringtype
    | INTEGERTYPE   #integertype
    | MONEYTYPE     #moneytype
    | DATETYPE      #datetype
    | DECIMALTYPE   #decimaltype
;

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
DEC : ('-')? DIGIT+  '.'  DIGIT+;
NEWLINE : '\r'? '\n';
