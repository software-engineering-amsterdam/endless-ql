/** Grammar for qls */
grammar QLS;

/** Parser rules */
stylesheet : STYLESHEET IDENTIFIER NEWLINE* CURLY_BRACE_L page* NEWLINE* CURLY_BRACE_R EOF; // form

page : PAGE IDENTIFIER CURLY_BRACE_L section* defaultWidget* CURLY_BRACE_R NEWLINE*; // content

section : SECTION STR CURLY_BRACE_L? element* CURLY_BRACE_R? NEWLINE*;

//block : CURLY_BRACE_L NEWLINE* lineInBlock* CURLY_BRACE_R NEWLINE*;

element : section NEWLINE*
        | defaultWidget NEWLINE*
        | question NEWLINE*
        ;

question : QUESTION IDENTIFIER widget?
         | QUESTION IDENTIFIER style?
         ;

defaultWidget : DEFAULT type (widget | style) NEWLINE*;

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
radioWidget : RADIO argList;
spinboxWidget : SPINBOX;
sliderWidget : SLIDER;
dropdownWidget : DROPDOWN argList;

style : CURLY_BRACE_L widgetProperty+ CURLY_BRACE_R;
argList: BRACKET_L ((STR COMMA)* (STR)?) BRACKET_R;

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
fontproperty    : FONT COLON STR ;
fontsizeproperty: FONTSIZE COLON INT ;
colorproperty   : COLOR COLON CLR ;

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
BRACKET_L: '[';
BRACKET_R: ']';
COLON : ':';
COMMA: ',';

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
CLR : '#' ('0'..'9' | 'a'..'f')+;


WHITESPACE      : (' ' | '\t' | '\n' | '\r')+ -> skip;
MULTICOMMENT    : '/*' .*? '*/' -> skip;
SINGLECOMMENT   : '//' ~[\r\n]* '\r'? '\n' -> skip;