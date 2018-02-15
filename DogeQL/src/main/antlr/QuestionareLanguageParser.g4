parser grammar QuestionareLanguageParser;

options {
    tokenVocab = QuestionareLanguageLexer;
}

form
    : CON_FORM IDENTIFIER block
    ;

block
    : SEP_LBRACE statement* SEP_RBRACE
    ;

statement
    : ifStatement
    | questionStatement
    ;

questionStatement
    : LIT_STRING variableInitialization
    ;

ifStatement
    : FLOW_IF block
    ;

variableInitialization
    : variableDeclaration (OP_ASSIGN expression)?
    ;

variableDeclaration
    : IDENTIFIER SEP_COLON TYPE
    ;

expression
    : SEP_LPAREN expression SEP_RPAREN
    | expression BINARY_OPERATOR expression
    | UNARY_OPERATOR expression
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
