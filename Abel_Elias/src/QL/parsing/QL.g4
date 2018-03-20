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
    | BRACE_L expression BRACE_R #braceExpression
    | left=expression boolOperator right=expression #boolExpression
    | left=expression comparisonOperator right=expression #compExpression
    | left=expression equalsOperator right=expression #eqExpression
    | left=expression numberOperator right=expression #numExpression
    | NOT expression #notExpression
    | STR #strValue
    | BOOL #boolValue
    | (MON | INT | DEC) #numValue
;

boolOperator:
    AND | OR
;

comparisonOperator:
    GRT | LST | GRTE | LSTE
;

equalsOperator:
    EQT | NEQT
;

numberOperator:
   ADD | SUB | MUL | DIV | REM
;

ifStatement:
    IF BRACE_L expression BRACE_R ifBlock=block (ELSE (ifStatement | elseBlock=block))?
;

type: BOOLEANTYPE   #booltype
    | STRINGTYPE    #stringtype
    | INTEGERTYPE   #integertype
    | MONEYTYPE     #moneytype
    | DATETYPE      #datetype
    | DECIMALTYPE   #decimaltype
;

/** Lexer rules (tokens)*/
WS : (' ' | '\t' )+ -> channel(HIDDEN);
COMMENT:   '/*' .*? '*/' -> channel(HIDDEN);
LINE_COMMENT:  '//' ~[\r\n]* -> channel(HIDDEN);

BOOLEANTYPE: 'boolean';
STRINGTYPE: 'string';
INTEGERTYPE: 'integer';
MONEYTYPE: 'money';
DATETYPE: 'date';
DECIMALTYPE: 'decimal';

FORM : 'form';
IF : 'if';
ELSE: 'else';
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

BOOL : ('true' | 'false');
IDENTIFIER: LETTER (LETTER | DIGIT | '_')*;
STR : '"' (~["\\\r\n])* '"';
INT : ('-')? DIGIT+;
MON : DIGIT+ '.' DIGIT DIGIT;
DEC : ('-')? DIGIT+  '.'  DIGIT+;
NEWLINE : '\r'? '\n';
