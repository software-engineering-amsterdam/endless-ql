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
    : LIT_STRING variableInitialization
    ;

ifStatement
    : IF LPAREN expression RPAREN block
    ;

variableInitialization
    : variableDeclaration (ASSIGN expression)?
    ;

variableDeclaration
    : NAME COLON TYPE
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
