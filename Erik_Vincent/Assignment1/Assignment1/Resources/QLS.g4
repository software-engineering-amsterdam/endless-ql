parser grammar QLS;

@header {
using Assignment1.Model;
}

options { tokenVocab=QLSLexer; }

// Add instructions to generate appropriate classes

stylesheet
    : STYLESHEET ID OPEN_CB page+ CLOSE_CB
    ;
page
    : PAGE ID OPEN_CB section+ CLOSE_CB
    ;
section
    : SECTION LABEL content 
    | SECTION LABEL OPEN_CB content* CLOSE_CB
    ;
content
    : section 
    | question 
    | default_style
    ;
question
    : QUESTION ID 
    | QUESTION ID widget
    ;
widget
    : WIDGET CHECKBOX
    | WIDGET RADIO OPEN_BR LABEL COMMA LABEL CLOSE_BR
    | WIDGET SLIDER
    | WIDGET SPINBOX
    | WIDGET TEXTBOX
    | WIDGET DROPDOWN
    ;
default_style
    : DEFAULT type OPEN_CB style* widget CLOSE_CB
    | DEFAULT type widget
    ;
style
    : WIDTH SEP NUMBER
    | FONT SEP LABEL
    | FONTSIZE SEP NUMBER
    | COLOR SEP HEXCOLORCODE
    ;
type
    : BOOLEAN_TYPE
    | DATE_TYPE
	| DECIMAL_TYPE
	| INTEGER_TYPE
	| MONEY_TYPE
	| STRING_TYPE
    ;
