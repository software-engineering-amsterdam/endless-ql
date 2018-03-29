grammar Qls;

styleSheet : STYLESHEETKEYWORD styleSheetName=IDENTIFIER 
             BEGINSCOPE page* defaultStyle* ENDSCOPE;

page : PAGEKEYWORD pageName=IDENTIFIER BEGINSCOPE ENDSCOPE;

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

stylePart : control
          | WIDTHPROPERTY KEYVALUESEPARATOR size=INTEGER 
          | FONTPROPERTY KEYVALUESEPARATOR fontName=TEXT
          | FONTSIZEPROPERTY KEYVALUESEPARATOR fontSize=DECIMAL
          | COLORPROPERTY KEYVALUESEPARATOR color=HEXIDECIMAL
          ;

control : WIDGETKEYWORD controlType;

controlType : CHECKBOX
            | RADIOBUTTON trueFalseText?
            | COMBOBOX trueFalseText?
            | TEXTBOX
            | TRACKBAR sliderRange?
            | NUMERICUPDOWN
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

fragment:
HEXDIGIT: ([0-9]|[A-F]) ([0-9]|[A-F]);

HEXIDECIMAL: '#' HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT;

DECIMAL: '-'?[0-9]+ '.' [0-9]+;
INTEGER: '-'?[0-9]+;
NEWLINE: '\r'? '\n' -> skip;
WS: [ \t]+ -> skip ;