/** Grammar for qls */
grammar QLS;

/** Parser rules */
stylesheet : STYLESHEET IDENTIFIER CURLY_BRACE_L page* NEWLINE* CURLY_BRACE_R EOF; // form

page : PAGE IDENTIFIER CURLY_BRACE_L section* CURLY_BRACE_R NEWLINE*; // content

section : SECTION IDENTIFIER CURLY_BRACE_L element* CURLY_BRACE_R NEWLINE*;

//block : CURLY_BRACE_L NEWLINE* lineInBlock* CURLY_BRACE_R NEWLINE*;

element : section NEWLINE*
    | defaultWidget NEWLINE*
    | question NEWLINE*
;

question : QUESTION IDENTIFIER (widget)?;

defaultWidget : DEFAULT type (widget | widgetStyle);

widget : WIDGET widgetType;

widgetType  : checkboxWidget
            | textWidget
            | radioWidget
            | spinboxWidget
            | sliderWidget
            | dropdownWidget
;
checkboxWidget : CHECKBOX;
textWidget : TEXT;
radioWidget : RADIO BRACE_L yes=STR COMMA no=STR BRACE_R;
spinboxWidget : SPINBOX;
sliderWidget : SLIDER;
dropdownWidget : DROPDOWN BRACE_L yes=STR COMMA no=STR BRACE_R;

widgetStyle : CURLY_BRACE_L NEWLINE* style+ widget? CURLY_BRACE_R NEWLINE*;

style : IDENTIFIER COLON value;

type : BOOLEANTYPE   #booltype
     | STRINGTYPE    #stringtype
     | INTEGERTYPE   #integertype
     | MONEYTYPE     #moneytype
     | DATETYPE      #datetype
     | DECIMALTYPE   #decimaltype
;

defaultdef      : blockdefault | linedefault ;

blockdefault    : DEFAULT type CURLY_BRACE_L widgetProperty+ CURLY_BRACE_R ;

linedefault     : DEFAULT type widgetProperty ;

widgetProperty  : widthproperty
                | fontproperty
                | fontsizeproperty
                | colorproperty
                | widget
                ;

widthproperty   : WIDTH COLON INT ;
fontproperty    : FONT COLON INT ;
fontsizeproperty: FONTSIZE COLON INT ;
colorproperty   : COLOR COLON INT ;

value           : STR
                | INT
;

/** Lexer rules (tokens)*/
WS : (' ' | '\t')+ -> channel(HIDDEN);

//value types
BOOLEANTYPE: 'boolean';
STRINGTYPE: 'string';
INTEGERTYPE: 'integer';
MONEYTYPE: 'money' | 'currency';
DATETYPE: 'date';
DECIMALTYPE: 'decimal';



//widget types
SLIDER: 'slider';
SPINBOX: 'spinbox';
CHECKBOX: 'checkbox';
TEXT: 'text';
RADIO: 'radio';
DROPDOWN: 'dropdown';

WIDTH           : 'width' ;
FONT            : 'font' ;
FONTSIZE        : 'fontsize' ;
COLOR           : 'color' ;

STYLESHEET : 'stylesheet';
PAGE : 'page';
SECTION : 'section';
DEFAULT : 'default';
WIDGET : 'widget';
QUESTION: 'question';

CURLY_BRACE_L : '{';
CURLY_BRACE_R: '}';
BRACE_L : '(';
BRACE_R : ')';
COMMA: ',';
COLON : ':';

// literals
fragment DIGIT : ('0'..'9');
fragment LETTER : ('a'..'z'|'A'..'Z');

BOOL : ('true' | 'false');
IDENTIFIER: LETTER (LETTER | DIGIT | '_')*;
STR : '"' .*? '"';
INT : ('-')? DIGIT+;
MON : DIGIT+ '.' DIGIT DIGIT;
DEC : ('-')? DIGIT+  '.'  DIGIT+;
NEWLINE : '\r'? '\n';

WHITESPACE      : (' ' | '\t' | '\n' | '\r')+ -> skip;
MULTICOMMENT    : '/*' .*? '*/' -> skip;
SINGLECOMMENT   : '//' ~[\r\n]* '\r'? '\n' -> skip;