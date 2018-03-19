/** Grammar for qls */
grammar QLS;

/** Parser rules */
stylesheet : STYLESHEET IDENTIFIER page* NEWLINE* EOF; // form

page : CURLY_BRACE_L NEWLINE* PAGE IDENTIFIER block* CURLY_BRACE_R NEWLINE*; // content

block : CURLY_BRACE_L NEWLINE* lineInBlock* CURLY_BRACE_R NEWLINE*;

lineInBlock : section NEWLINE*
    | defaultWidget NEWLINE*
    | question NEWLINE*
;

question : QUESTION IDENTIFIER (widget)?;

section : SECTION IDENTIFIER block*;

defaultWidget : DEFAULT type (widget | widgetStyle);

widget : WIDGET widgetType;

widgetStyle : CURLY_BRACE_L NEWLINE* style+ widget? CURLY_BRACE_R NEWLINE*;

style : IDENTIFIER COLON value;

type: BOOLEANTYPE   #booltype
    | STRINGTYPE    #stringtype
    | INTEGERTYPE   #integertype
    | MONEYTYPE     #moneytype
    | DATETYPE      #datetype
    | DECIMALTYPE   #decimaltype
;

widgetType: SLIDER                                         #sliderwidget
          | SPINBOX                                        #spinboxwidget
          | CHECKBOX                                       #checkboxwidget
          | TEXT                                           #textwidget
          | RADIO BRACE_L yes=STR COMMA no=STR BRACE_R     #radiowidget
          | DROPDOWN BRACE_L yes=STR COMMA no=STR BRACE_R  #dropdownwidget
;

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