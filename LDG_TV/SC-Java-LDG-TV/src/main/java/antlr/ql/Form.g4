grammar Form;
/*
 * Parser Rules
 */
formBuilder  : 'form' CHARACTERS CURLY_BRACKET_OPEN formData CURLY_BRACKET_CLOSE;
formData : (questionNodeStructure (questionNodeStructure)? (ifStructure)?)+ ;

questionNodeStructure:
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
    (questionNodeStructure)+
    CURLY_BRACKET_CLOSE
    elseStructure?
;
elseStructure:
    ELSE
    CURLY_BRACKET_OPEN
    (questionNodeStructure)+
    CURLY_BRACKET_CLOSE
;


statementBlockStructure: BRACKET_OPEN conditions BRACKET_CLOSE ;
value: (CHARACTERS | NUMBERS);

label : QUESTION_LABEL;
variable: CHARACTERS;
variableType: 'boolean' | 'money' | 'string';
variableValue: expression | value;


expression: BRACKET_OPEN (booleanExpression | arithmeticExpression) BRACKET_CLOSE;

booleanExpression: variable booleanExpressionOperator variable;
arithmeticExpression : variable arithmeticExpressionOperator variable;

arithmeticExpressionOperator: ('*' | '+' | '-' | '/');
booleanExpressionOperator: ('<' | '>' | '=<' '>=' | '!' | '!=' '==');

condition: (value | expression);
conditions: (condition booleanOperator? condition?)+;

booleanOperator:  (OR | AND);

/*
 * Lexer Rules
 */

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
NUMBERS : ('0' .. '9')+  ;

CURLY_BRACKET_OPEN : '{';
CURLY_BRACKET_CLOSE : '}';
BRACKET_OPEN : '(';
BRACKET_CLOSE : ')';
OR: '||';
AND: '&&';

QUESTION_LABEL : '"' + ((CHARACTERS | NUMBERS | ' ' | ':' | '?')+) + '"';
QUESTION_VARIABLE_SEPERATOR : ':';
QUESTION_VARIABLE_VALUE_SEPERATOR : '=';

IF : 'if';
ELSE : 'else';

WHITESPACE : [ \r\n\t] + -> skip;
NEWLINE : ('\r'? '\n' | '\r')+ -> skip;

CHARACTERS : (LOWERCASE | UPPERCASE)+;


