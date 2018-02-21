parser grammar QuestionareLanguageParser;

options {
    tokenVocab = QuestionareLanguageLexer;
}

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
    : LPAREN expression RPAREN
    | UNARY_OPERATOR expression
    | expression BINARY_OPERATOR expression
    | NAME
    | literal
    ;

literal
    : LIT_BOOLEAN
    | LIT_INTEGER
    | LIT_DECIMAL
    | LIT_STRING
    | LIT_COLOR
    | LIT_DATE
    ;
