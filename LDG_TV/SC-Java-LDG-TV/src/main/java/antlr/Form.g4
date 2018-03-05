grammar Form;
/*
 * Parser Rules
 */
formBuilder  : 'form' CHARACTERS CURLY_BRACKET_OPEN formData CURLY_BRACKET_CLOSE;
formData : (questionStructure)+ (ifStructure)+?;

questionStructure:
    questionLabel
    questionVariable
    QUESTION_VARIABLE_SEPERATOR
    questionVariableType
    QUESTION_VARIABLE_VALUE_SEPERATOR?
    questionVariableValue?
;
ifStructure:
    IF
    statementBlockStructure
    CURLY_BRACKET_OPEN
    (questionStructure)+
    CURLY_BRACKET_CLOSE
;

statementBlockStructure: BRACKET_OPEN conditions BRACKET_CLOSE ;
plainValue: (CHARACTERS | NUMBERS);

questionLabel : QUESTION_LABEL;
questionVariable: CHARACTERS;
questionVariableType: 'boolean' | 'money' | 'string';
questionVariableValue: expression | plainValue;

expression: BRACKET_OPEN questionVariable operator questionVariable BRACKET_CLOSE;
booleanExpression: BRACKET_OPEN questionVariable comparisonOperator questionVariable BRACKET_CLOSE;

condition: (questionVariable | booleanExpression);
conditions: condition |condition (booleanOperator condition)+;

comparisonOperator: (GT | GTOEQ | STOEQ | ST | EQ | NEQ);
booleanOperator:  (OR | AND);
operator: (PLUS | MINUS | TIMES | DIV) ;
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


