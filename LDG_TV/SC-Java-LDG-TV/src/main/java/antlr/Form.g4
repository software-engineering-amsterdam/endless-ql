grammar Form;
/*
 * Parser Rules
 */
formBuilder  : 'form' CHARACTERS CURLY_BRACKET_OPEN formData CURLY_BRACKET_CLOSE;
formData : (questionStructure)+ (ifStructure)+?;

questionStructure:
    label
    variable
    QUESTION_VARIABLE_SEPERATOR
    variableType
    QUESTION_VARIABLE_VALUE_SEPERATOR?
    variableValue?
;
ifStructure:
    IF
    statementBlockStructure
    CURLY_BRACKET_OPEN
    (questionStructure)+
    CURLY_BRACKET_CLOSE
;

statementBlockStructure: BRACKET_OPEN conditions BRACKET_CLOSE ;
value: (CHARACTERS | NUMBERS);

label : QUESTION_LABEL;
variable: CHARACTERS;
variableType: 'boolean' | 'money' | 'string';
variableValue: expression | value;


expression: BRACKET_OPEN (booleanExpression | aritmaticExpression) BRACKET_CLOSE;

booleanExpression: unaryBooleanExpression | gteoqExpression | gtExpression | stExpression | stoeqExpression | eqExpression | neqExpression;

unaryBooleanExpression: '!' value;
gtExpression: variable GT variable ;
gteoqExpression: variable GTOEQ variable ;
stExpression: variable ST variable ;
stoeqExpression: variable STOEQ variable ;
eqExpression: variable EQ variable ;
neqExpression: variable NEQ variable ;

aritmaticExpression : (mulExpression| divExpression | addExpression | minExpression);

mulExpression: variable TIMES variable ;
addExpression: variable PLUS variable;
minExpression: variable MINUS variable;
divExpression: variable DIV variable;

condition: (value | expression);
conditions: (condition booleanOperator? condition?)+;

booleanOperator:  (OR | AND);

/*
 * Lexer Rules
 */

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
fragment NUMBERS : ('0' .. '9')+  ;

CURLY_BRACKET_OPEN : '{';
CURLY_BRACKET_CLOSE : '}';
BRACKET_OPEN : '(';
BRACKET_CLOSE : ')';
OR: '||';
AND: '&&';
PLUS: '+';
MINUS: '-';
TIMES: '*';
DIV: '/';
GT: '>';
GTOEQ: '>=';
STOEQ: '=<';
ST: '<';
EQ: '==';
NEQ: '!=';

QUESTION_LABEL : '"' + ((CHARACTERS | NUMBERS | ' ' | ':' | '?')+) + '"';
QUESTION_VARIABLE_SEPERATOR : ':';
QUESTION_VARIABLE_VALUE_SEPERATOR : '=';

IF : 'if';

WHITESPACE : [ \r\n\t] + -> skip;
NEWLINE : ('\r'? '\n' | '\r')+ -> skip;

CHARACTERS : (LOWERCASE | UPPERCASE)+;


