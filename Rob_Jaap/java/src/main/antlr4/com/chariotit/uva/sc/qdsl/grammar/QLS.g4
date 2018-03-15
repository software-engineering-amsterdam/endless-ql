grammar QLS;


/**
 * Parser rules
 */

stylesheet      : STYLESHEET identifier page+ ;
page            : PAGE identifier BRACKET_L section+ defaultdef* BRACKET_R ;
section_elem    : (section | question) ;
section         : SECTION STRING BRACKET_L section_elem+ defaultdef* BRACKET_R ;
question        : QUESTION identifier widgetproperty? ;
defaultdef      : blockdefault | linedefault ;
blockdefault    : DEFAULT type BRACKET_L property+ BRACKET_R ;
linedefault     : DEFAULT type property ;
property        : widthproperty
                | fontproperty
                | fontsizeproperty
                | colorproperty
                | widgetproperty
                ;
widgetproperty  : WIDGET widget_type ;
widget_type     : radiowidget
                | spinboxwidget
                | checkboxwidget
                | textwidget
                | sliderwidget
                | dropdownwidget
                ;
radiowidget     : RADIO (PARENTH_L STRING COMMA STRING PARENTH_R)? ;
spinboxwidget   : SPINBOX ;
checkboxwidget  : CHECKBOX ;
textwidget      : TEXT ;
sliderwidget    : SLIDER ;
dropdownwidget  : DROPDOWN (PARENTH_L STRING COMMA STRING PARENTH_R)? ;

widthproperty   : WIDTH COLON NUMBER ;
fontproperty    : FONT COLON STRING ;
fontsizeproperty: FONTSIZE COLON NUMBER ;
colorproperty   : COLOR COLON COLOR_CODE ;

type            : BOOLEAN_TYPE | STRING_TYPE | INTEGER_TYPE | MONEY_TYPE ;
identifier      : WORD ;

/**
 * Lexer rules
 */

STYLESHEET      : 'stylesheet' ;
PAGE            : 'page' ;
SECTION         : 'section' ;
WIDGET          : 'widget' ;
QUESTION        : 'question' ;
DEFAULT         : 'default' ;
BOOLEAN_TYPE    : 'boolean' ;
STRING_TYPE     : 'string' ;
INTEGER_TYPE    : 'integer' ;
MONEY_TYPE      : 'money' ;
RADIO           : 'radio' ;
SPINBOX         : 'spinbox' ;
CHECKBOX        : 'checkbox' ;
TEXT            : 'text' ;
SLIDER          : 'slider' ;
DROPDOWN        : 'dropdown' ;
WIDTH           : 'width' ;
FONT            : 'font' ;
FONTSIZE        : 'fontsize' ;
COLOR           : 'color' ;

BRACKET_L       : '{' ;
BRACKET_R       : '}' ;
PARENTH_L       : '(' ;
PARENTH_R       : ')' ;
COLON           : ':' ;
COMMA           : ',' ;


COLOR_CODE      : '#'[0-9a-fA-F]+ ;
NEWLINE         : ('\r'? '\n' | '\r')+ -> skip ;
WHITESPACE      : (' ' | '\t') -> skip ;

NUMBER          : [0-9]+ ;
WORD            : [a-zA-Z]+[a-zA-Z0-9]*;
STRING          : '"'(.*?)'"';