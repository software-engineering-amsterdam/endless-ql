lexer grammar QuestionareLanguageLexer;

/* Constructs */

FORM
    : 'form'
    ;

/* Control flow */

IF
    : 'if'
    ;

/* Types */

TYPE
    : TYPE_BOOLEAN
    | TYPE_INT
    | TYPE_STRING
    | TYPE_DECIMAL
    | TYPE_MONEY
    | TYPE_DATE
    ;

TYPE_BOOLEAN
    : 'boolean'
    ;

TYPE_INT
    : 'int'
    ;

TYPE_STRING
    : 'string'
    ;

TYPE_DECIMAL
    : 'decimal'
    ;

TYPE_MONEY
    : 'money'
    ;

TYPE_DATE
    : 'date'
    ;

/* Separators */

LPAREN
    : '('
    ;

RPAREN
    : ')'
    ;

LBRACE
    : '{'
    ;

RBRACE
    : '}'
    ;

COLON
    : ':'
    ;

QUOTE
    : '"'
    ;

/* Literals */

LIT_BOOLEAN
    : True
    | False
    ;

LIT_INTEGER
    : ('-'+)?Digits
    | ('-'+)?Hexadecimal
    ;

LIT_DECIMAL
    : Digits '.' Digits
    ;

LIT_STRING
    : QUOTE .*? QUOTE
    ;

LIT_COLOR
    : '#' HexDigit HexDigit HexDigit HexDigit HexDigit HexDigit
    ;

LIT_DATE
    : Digit Digit '-' Digit Digit '-' Digit Digit Digit Digit
    | Digit Digit Digit Digit '-' Digit Digit '-' Digit Digit
    ;

fragment
True
    : 'true'
    ;

fragment
False
    : 'false'
    ;

fragment
Digit
    : [0-9]
    ;

fragment
Digits
    : Digit+
    ;

fragment
Hexadecimal
    : '0' [xX] HexDigits
    ;

fragment
HexDigits
    : HexDigit (HexDigit)?
    ;

fragment
HexDigit
	: [0-9a-fA-F]
	;

/* Operators */

UNARY_OPERATOR
    : NOT
    ;

BINARY_OPERATOR
    : GT
    | LT
    | EQUAL
    | LE
    | GE
    | NOTEQUAL
    | AND
    | OR
    | ADD
    | SUB
    | MUL
    | DIV
    ;

ASSIGN
    : '='
    ;

GT
    : '>'
    ;

LT
    : '<'
    ;

NOT
    : '!'
    ;

EQUAL
    : '=='
    ;

LE
    : '<='
    ;

GE
    : '>='
    ;

NOTEQUAL
    : '!='
    ;

AND
    : '&&'
    ;

OR
    : '||'
    ;

ADD
    : '+'
    ;

SUB
    : '-'
    ;

MUL
    : '*'
    ;

DIV
    : '/'
    ;

/* Identifiers */

IDENTIFIER
    : IDENTIFIER_LETTER (IDENTIFIER_LETTER_OR_DIGIT*)
    ;

fragment
IDENTIFIER_LETTER
    : [a-zA-Z$_]
    ;

fragment
IDENTIFIER_LETTER_OR_DIGIT
    : [a-zA-Z0-9$_]
    ;

/* Whitespace and comments */

WHITESPACE
    : [ \t\r\n\u000C]+ -> channel(HIDDEN)
    ;

COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

LINE_COMMENT
    : '//' ~[\r\n]* -> channel(HIDDEN)
    ;
