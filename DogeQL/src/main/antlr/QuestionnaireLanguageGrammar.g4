grammar QuestionnaireLanguageGrammar;

form
    : FORM NAME block
    ;

block
    : LBRACE statement* RBRACE
    ;

statement
    : ifStatement
    | questionStatement
    ;

questionStatement
    : LIT_STRING NAME COLON TYPE (ASSIGN expression)?
    ;

ifStatement
    : IF LPAREN expression RPAREN block
    ;

expression
    : NOT expression
    | expression (MUL | DIV) expression
    | expression (ADD | SUB) expression
    | expression (LT | GT | LE | GE) expression
    | expression (EQUAL | NOTEQUAL) expression
    | expression AND expression
    | expression OR expression
    | LPAREN expression RPAREN
    | literal
    | NAME
    ;

literal
    : LIT_BOOLEAN
    | LIT_INTEGER
    | LIT_DECIMAL
    | LIT_STRING
    | LIT_COLOR
    | LIT_DATE
    ;

/* Tokens */

/* Constructs */

FORM
    : 'form'
    ;

STYLESHEET
    : 'stylesheet'
    ;

PAGE
    : 'page'
    ;

SECTION
    : 'section'
    ;

WIDGET
    : 'widget'
    ;

DEFAULT
    : 'default'
    ;

QUESTION
    : 'question'
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

/* Name */

NAME
    : NAME_LETTER (NAME_LETTER_OR_DIGIT*)
    ;

fragment
NAME_LETTER
    : [a-zA-Z$_]
    ;

fragment
NAME_LETTER_OR_DIGIT
    : [a-zA-Z0-9$_]
    ;

/* Operators */

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
