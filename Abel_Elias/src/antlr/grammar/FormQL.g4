/** Grammar for questionnaire form */
grammar FormQL;

@parser::header
{
	package antlr.grammar;
	import antlr.grammar.*;
}

@lexer::header
{
	package antlr.grammar;
	import antlr.grammar.*;
}

/** Parser rules */
form : FORM identifier block EOF; // form

identifier:
    LETTER (LETTER | DIGIT | '_')*
;

block : CURLY_BRACE_L (ifStatement | question | statement)* CURLY_BRACE_R; // content

question : identifier COLON STR type;

statement : identifier COLON STR typeValue;

typeValue:
    booleanType boolean |
    moneyType  money |
    stringType string |
    integerType  integer |
    dateType date |
    decimalType decimal |
;

boolean:
    'true' |
    'false' |
    identifier |
    NOT boolean |
    BRACE_L boolean BRACE_R |
    boolean boolOperator boolean |
    money comparisonOperator money |
    integer comparisonOperator integer |
    string EQT string |
;

boolOperator:
    EQT | NEQT | AND | OR
;

comparisonOperator:
    EQT | GRT | LST | GRTE | LSTE
;

money:
    MON |
    identifier |
    BRACE_L money BRACE_R |
    money numberOperator money |
;

integer:
    INT |
    identifier |
    BRACE_L integer BRACE_R|
    integer numberOperator integer |
;

string:
    STR |
    BRACE_L string BRACE_R |
    string ADD string
;

numberOperator:
    NOT | ADD | SUB | MUL | DIV | REM
;

decimal:
    DEC |
    identifier |
    BRACE_L decimal BRACE_R |
    decimal numberOperator decimal |
;

date:
    'dateplaceholder'
;

ifStatement : IF BRACE_L booleanExpr BRACE_R content*; //statement

type:  booleanType | stringType | integerType | moneyType | dateType | decimalType ;

booleanType: 'boolean';
stringType: 'string';
integerType: 'integer';
moneyType: 'money' | currency;
dateType: 'date';
decimalType: 'decimal';

/** Lexer rules (tokens)*/

//COMMENT
//    : '/*' .* '*/' {$channel=HIDDEN;}
//    ;
WHITESPACE : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

FORM : ('form');
IF : ('if');
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

ESC_SEQ : '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\');

// literals
STR : '“' .*? '”';
INT : ('-')? DIGIT+;
BOOL : ('true' | 'false');
MON : DIGIT+ '.' DIGIT DIGIT;
DEC : DIGIT+  '.'  DIGIT+;
DIGIT : ('0'..'9');
LETTER: ('a'..'z'|'A'..'Z');
