grammar Qls;

styleSheet : 'stylesheet' styleSheetName=IDENTIFIER '{' page* '}';

page : 'page' pageName=IDENTIFIER '{' '}';

IDENTIFIER: [a-zA-Z] [a-zA-Z0-9_]* ;
NEWLINE: '\r'? '\n' -> skip;
WS: [ \t]+ -> skip ;