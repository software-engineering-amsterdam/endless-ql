grammar QL;            
form: FORM ID BRACKETL NEWLINE groupquestions BRACKETR;

groupquestions: (question NEWLINE)+;

question: STRING_LITERAL NEWLINE ID DOT ID;

FORM:   'form';
MUL :   '*' ; 
DIV :   '/' ;
ADD :   '+' ;
SUB :   '-' ;
DOT :   ':';
BRACKETL: '{';
BRACKETR: '}';
ID  :   [a-zA-Z]+ ;      // match identifiers
STRING_LITERAL : '"' (~('"' | '\\' | '\r' | '\n'))* '"';
INT :   [0-9]+ ;         // match integers
NEWLINE:'\r'? '\n' ;     // return newlines to parser (is end-statement signal)
WS  :   [ \t]+ -> skip ; // toss out whitespace