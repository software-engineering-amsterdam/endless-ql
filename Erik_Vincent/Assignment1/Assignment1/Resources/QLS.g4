grammar QLS;

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
    : BOOLEAN
    | MONEY
    ;

// Move (merge) necessary keywords to lexer file

STYLESHEET
    : 'stylesheet'
    ;
PAGE
    : 'page'
    ;
SECTION
    : 'section'
    ;
QUESTION
    : 'question'
    ;
WIDGET
    : 'widget'
    ;
RADIO
    : 'radio'
    ;
CHECKBOX
    : 'checkbox'
    ;
SLIDER
    : 'slider'
    ;
SPINBOX
    : 'spinbox'
    ;
TEXTBOX
    : 'textbox'
    ;
DROPDOWN
    : 'dropdown'
    ;
DEFAULT
    : 'default'
    ;
WIDTH
    : 'width'
    ;
FONT
    : 'font'
    ;
FONTSIZE
    : 'fontsize'
    ;
COLOR
    : 'color'
    ;
HEXCOLORCODE
    : '#' ([0..9] | [a-f]) ([0..9] | [a-f]) ([0..9] | [a-f]) ([0..9] | [a-f]) ([0..9] | [a-f]) ([0..9] | [a-f])
    ;
OPEN_BR
    : '('
    ;
CLOSE_BR
    : ')'
    ;
OPEN_CB
    : '{'
    ;
CLOSE_CB
    : '}'
    ;
SEP
    : ':'
    ;
COMMA
    : ','
    ;
BOOLEAN
    : 'boolean'
    ;
MONEY
    : 'money'
    ;
fragment NUMBER
    : [0-9]+
    ;
ID
    : [a-zA-Z0-9]+
    ;
LABEL
    : '"' ~'"'*? '"'
    ;
COMMENT
    : '//' ~'\n'*? '\n' -> skip
    ;
WHITESPACE
    : [ \n\t\r]+ -> skip
    ;