grammar QL;


questionnaire:
    form*
;

form:
    'form' identifier block
;

block:
    '{' (expression)* '}'
;

//TODO: Add if/else expressions
expression:
   question
;

question:
    identifier ':' string type
;

string:
    '"' (~('"'))* '"'
;

//Types taken from doc: boolean, string, integer, date and decimal and money/currency
//Question: Seperate(now) or in type
type:
    BOOLEANTYPE |
    STRINGTYPE |
    INTEGERTYPE |
    DATETYPE |
    DECIMALTYPE |
    MONEYTYPE
;

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

identifier:
    LETTER indentifierTail
;

indentifierTail:
    (LETTER | DIGIT | '_')*
;


LETTER:
    'A'..'Z' | 'a'..'z'
;

DIGIT:
    '0' .. '9'
;