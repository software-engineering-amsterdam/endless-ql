parser grammar StyleParser;

import SharedParser;

options {
    tokenVocab = QuestionareLanguageLexer;
}

stylesheet
    : STYLESHEET NAME pages
    ;

pages
    : LBRACE page* RBRACE
    ;

page
    : PAGE NAME styles
    ;

styles
    : LBRACE style* RBRACE
    ;

style
    : section
    | default
    ;

section
    : SECTION LIT_STRING elements
    ;

elements
    : LBRACE element* RBRACE
    ;

element
    : question
    | default
    | section
    ;

question
    : QUESTION NAME (widget)?
    ;

default
    : DEFAULT TYPE attributes
    ;

attributes
    : LBRACE attribute* RBRACE
    ;

attribute
    : pair
    | widget
    ;

pair
    : NAME COLON literal
    ;

widget
    : WIDGET NAME
    ;