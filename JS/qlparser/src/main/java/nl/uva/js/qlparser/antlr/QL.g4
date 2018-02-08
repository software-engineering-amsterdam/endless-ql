grammar QL;

FORM:       'form';
MONEY:      'money';
BOOLEAN:    'boolean';

ANY:        .+?;
LABEL:      '"' ~['\\\r\n] '"';
FORM_NAME:  [a-zA-Z]+;

LBRACE:     '{';
RBRACE:     '}';

form
    : FORM FORM_NAME LBRACE ANY RBRACE
    ;
