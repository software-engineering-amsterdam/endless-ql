grammar QL;

/*
 * Parser Rules
 */

questionnaire:
    form*
;

form:
    FORM identifier block EOF
;

identifier:
    LETTER (LETTER | DIGIT | '_')*
;

block:
    '{' (expression)* '}'
;

//TODO: Add if/else expressions
expression:
   question '\n'
;

question:
    identifier ':' string type
;

string:
    '"' (~('"'))* '"'
;

type:
    BOOLEANTYPE |
    STRINGTYPE |
    INTEGERTYPE |
    DATETYPE |
    DECIMALTYPE |
    MONEYTYPE
;

/*
 * Lexer Rules
 */

FORM:
    'form'
;

WHITESPACE : ' ' -> skip

MONEYTYPE:
    'money' | 'currency'
;

DECIMALTYPE:
    'decimal'
;

DATETYPE:
    'date'
;

INTEGERTYPE:
    'integer'
;

STRINGTYPE:
    'string'
;

BOOLEANTYPE:
    'boolean'
;



LETTER:
    'A'..'Z' | 'a'..'z'
;

DIGIT:
    '0' .. '9'
;