lexer grammar QuestionareLanguageLexer;

/* Constructs */

CON_FORM
    : 'form'
    ;

/* Control flow */

FLOW_IF
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

SEP_LPAREN
    : '('
    ;

SEP_RPAREN
    : ')'
    ;

SEP_LBRACE
    : '{'
    ;

SEP_RBRACE
    : '}'
    ;

SEP_COLON
    : ':'
    ;

SEP_QUOTE
    : '"'
    ;

/* Literals */

LIT_BOOLEAN
    : True
    | False
    ;

LIT_INTEGER
    : Digits
    | Hexadecimal
    ;

LIT_DECIMAL
    : Digits '.' Digits
    ;

LIT_STRING
    : SEP_QUOTE .*? SEP_QUOTE
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
    : OP_NOT
    ;

BINARY_OPERATOR
    : OP_GT
    | OP_LT
    | OP_EQUAL
    | OP_LE
    | OP_GE
    | OP_NOTEQUAL
    | OP_AND
    | OP_OR
    | OP_ADD
    | OP_SUB
    | OP_MUL
    | OP_DIV
    ;

OP_ASSIGN
    : '='
    ;

OP_GT
    : '>'
    ;

OP_LT
    : '<'
    ;

OP_NOT
    : '!'
    ;

OP_EQUAL
    : '=='
    ;

OP_LE
    : '<='
    ;

OP_GE
    : '>='
    ;

OP_NOTEQUAL
    : '!='
    ;

OP_AND
    : '&&'
    ;

OP_OR
    : '||'
    ;

OP_ADD
    : '+'
    ;

OP_SUB
    : '-'
    ;

OP_MUL
    : '*'
    ;

OP_DIV
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

WS
    : [ \t\r\n\u000C]+ -> channel(HIDDEN)
    ;

COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

LINE_COMMENT
    : '//' ~[\r\n]* -> channel(HIDDEN)
    ;
