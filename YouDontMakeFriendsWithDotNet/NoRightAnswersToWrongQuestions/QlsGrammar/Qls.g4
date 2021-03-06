grammar Qls;

styleSheet : STYLESHEETKEYWORD styleSheetName=IDENTIFIER 
             BEGINSCOPE page* defaultStyle* ENDSCOPE;

page : PAGEKEYWORD pageName=IDENTIFIER 
       BEGINSCOPE  section* defaultStyle* ENDSCOPE;

section: SECTIONKEYWORD sectionName=IDENTIFIER
         BEGINSCOPE question* defaultStyle* ENDSCOPE;

question: QUESTIONKEYWORD questionName=IDENTIFIER style?;

defaultStyle : DEFAULTKEYWORD type style;

type : BOOLEANTYPE
     | DECIMALTYPE
     | DATETYPE
     | INTEGERTYPE
     | STRINGTYPE
     ;

style : stylePart
      | BEGINSCOPE stylePart+ ENDSCOPE
      ;

stylePart : widget=control
          | WIDTHPROPERTY KEYVALUESEPARATOR width=INTEGER 
          | FONTPROPERTY KEYVALUESEPARATOR fontname=TEXT
          | FONTSIZEPROPERTY KEYVALUESEPARATOR fontsize=(DECIMAL | INTEGER)
          | COLORPROPERTY KEYVALUESEPARATOR color=HEXIDECIMAL
          ;

control : WIDGETKEYWORD controlType;

controlType : chosenType=(CHECKBOX
            | RADIOBUTTON trueFalseText?
            | COMBOBOX trueFalseText?
            | TEXTBOX
            | TRACKBAR sliderRange?
            | NUMERICUPDOWN)
            ;

trueFalseText: BEGINCOLLECTION 
                   trueText=TEXT SEPARATOR 
                   falseText=TEXT 
               ENDCOLLECTION;

sliderRange: BEGINCOLLECTION 
                rangeStart=INTEGER SEPARATOR 
                rangeEnd=INTEGER SEPARATOR 
                step=INTEGER 
             ENDCOLLECTION;

BEGINSCOPE: '{';
ENDSCOPE: '}';			
BEGINCOLLECTION: '(';
ENDCOLLECTION: ')';
SEPARATOR: ',';
KEYVALUESEPARATOR: ':';

WIDGETKEYWORD: 'widget';
DEFAULTKEYWORD: 'default';
PAGEKEYWORD: 'page';
STYLESHEETKEYWORD: 'stylesheet';
SECTIONKEYWORD: 'section';
QUESTIONKEYWORD: 'question';

BOOLEANTYPE: 'boolean';
INTEGERTYPE: 'integer';
DATETYPE: 'date';
DECIMALTYPE: 'decimal';
STRINGTYPE: 'string';

WIDTHPROPERTY: 'width';
FONTPROPERTY: 'font';
FONTSIZEPROPERTY: 'fontsize';
COLORPROPERTY: 'color';

CHECKBOX: 'checkbox';
RADIOBUTTON: 'radio';
COMBOBOX: 'dropdown';
TEXTBOX: 'textbox';
TRACKBAR: 'slider';
NUMERICUPDOWN: 'spinbox';

TEXT: '"' (~'"')* '"';
IDENTIFIER: [a-zA-Z] [a-zA-Z0-9_]* ;

fragment DIGIT: [0-9];
fragment HEXDIGIT: (DIGIT|[A-F]) (DIGIT|[A-F]);

HEXIDECIMAL: '#' HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT;

DECIMAL: '-'?DIGIT+ '.' DIGIT+;
INTEGER: '-'?DIGIT+;
NEWLINE: '\r'? '\n' -> skip;
WS: [ \t]+ -> skip ;