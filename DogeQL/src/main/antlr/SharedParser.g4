parser grammar SharedParser;

options {
    tokenVocab = QuestionareLanguageLexer;
}

literal
    : LIT_BOOLEAN
    | LIT_INTEGER
    | LIT_DECIMAL
    | LIT_STRING
    | LIT_COLOR
    | LIT_DATE
    ;