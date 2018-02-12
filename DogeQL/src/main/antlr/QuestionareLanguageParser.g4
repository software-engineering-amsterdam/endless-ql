parser grammar QuestionareLanguageParser;

options { tokenVocab=QuestionareLanguageLexer; }


form : FORM IDENTIFIER formBody ;
formBody : questionBody | statement;

question : STRING_LITERAL IDENTIFIER COLON TYPE ;
questionBody : OPEN_BRACE question*? CLOSE_BRACE ;

statement : IF OPEN_PARENS IDENTIFIER CLOSE_PARENS questionBody;