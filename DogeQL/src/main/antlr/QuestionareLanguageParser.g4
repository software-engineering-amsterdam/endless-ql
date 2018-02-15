parser grammar QuestionareLanguageParser;

options { tokenVocab = QuestionareLanguageLexer; }

form : FORM IDENTIFIER formBody ;
formBody : LBRACE (question+ | statement*) RBRACE;

question : STRING_LITERAL IDENTIFIER COLON TYPE;

statement : IF LPAREN IDENTIFIER RPAREN question+;