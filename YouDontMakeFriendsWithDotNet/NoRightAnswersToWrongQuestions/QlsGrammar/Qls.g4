grammar Qls;

styleSheet : 'stylesheet' styleSheetName=IDENTIFIER BEGINSCOPE page* defaultStyle* ENDSCOPE;

page : 'page' pageName=IDENTIFIER BEGINSCOPE ENDSCOPE;

defaultStyle : 'default' 'boolean' widget;

widget : 'widget' 'checkbox'
       | 'widget' 'radio' trueFalseText?
       | 'widget' 'dropdown' trueFalseText?
       ;

trueFalseText: BEGINCOLLECTION trueText=TEXT ',' falseText=TEXT ENDCOLLECTION;


BEGINSCOPE: '{';
ENDSCOPE: '}';			
BEGINCOLLECTION: '(';
ENDCOLLECTION: ')';


TEXT: '"' (~'"')* '"';
IDENTIFIER: [a-zA-Z] [a-zA-Z0-9_]* ;
NEWLINE: '\r'? '\n' -> skip;
WS: [ \t]+ -> skip ;