parser grammar QuestionareLanguageParser;

options {
    tokenVocab = QuestionareLanguageLexer;
}

form
    : FORM IDENTIFIER block
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
    : IDENTIFIER COLON TYPE
    ;

expression
    : LPAREN expression RPAREN
    | UNARY_OPERATOR expression
    | expression BINARY_OPERATOR expression
    | IDENTIFIER
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
