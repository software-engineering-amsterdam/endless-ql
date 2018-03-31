grammar QLS;

root                : STYLESHEET identifier=IDENTIFIER (page | '{' page* '}') EOF;
page                : PAGE identifier=IDENTIFIER (statement | '{' statement* '}');
section             : SECTION title=STRING (statement | '{' statement* '}');
questionReference   : QUESTION identifier=IDENTIFIER widget?;
statement           : (section | questionReference | defaultStyle);

// default style without braces can only define one widget type
// default style with braces defines none or multiple style attributes,
// followed by one or no widget type
defaultStyle        : DEFAULT type (widget | '{' styleAttribute* widget? '}');

styleAttribute      : WIDTH ':' value=INTEGER       # widgetWidth
                    | FONT ':' value=STRING         # widgetFont
                    | FONTSIZE ':' value=INTEGER    # widgetFontSize
                    | COLOR ':' value=HEXCOLOR      # widgetColor
                    ;

widget              : WIDGET RADIO '(' trueLabel=STRING ',' falseLabel=STRING ')'               # radioWidget
                    | WIDGET DROPDOWN '(' trueLabel=STRING ',' falseLabel=STRING ')'            # dropdownWidget
                    | WIDGET CHECKBOX                                                           # checkBoxWidget
                    | WIDGET SPINBOX                                                            # spinBoxWidget
                    | WIDGET TEXTBOX                                                            # textBoxWidget
                    | WIDGET SLIDER '(' min=INTEGER ',' max=INTEGER ')'                         # sliderWidget
                    ;

type                : BOOLEANTYPE
                    | STRINGTYPE
                    | INTEGERTYPE
                    | DATETYPE
                    | DECIMALTYPE
                    | MONEYTYPE
                    ;

// Keywords
STYLESHEET          : 'stylesheet';
PAGE                : 'page';
SECTION             : 'section';
DEFAULT             : 'default';
WIDGET              : 'widget';
QUESTION            : 'question';
RADIO               : 'radio';
CHECKBOX            : 'checkbox';
SPINBOX             : 'spinbox';
DROPDOWN            : 'dropdown';
SLIDER              : 'slider';
TEXTBOX             : 'textbox';
WIDTH               : 'width';
FONT                : 'font';
FONTSIZE            : 'fontsize';
COLOR               : 'color';

BOOLEANTYPE         : 'boolean';
STRINGTYPE          : 'string';
INTEGERTYPE         : 'integer';
DATETYPE            : 'date';
DECIMALTYPE         : 'decimal';
MONEYTYPE           : 'money';

// Literals
INTEGER             : [0-9]+;
DATE                : ([0-9] | [0-3] [0-9]) '-' ([0-9] | [0-3] [0-9]) '-' ([0-9] [0-9] [0-9] [0-9]);
STRING              : '"' .*? '"';
IDENTIFIER          : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
HEXCOLOR            : '#' [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F];

WS                  : [ \t\r\n]+ -> skip;
COMMENT             : ('/*' .*? '*/') -> skip;
LINE_COMMENT        : '//' ~[\r\n]* -> skip;