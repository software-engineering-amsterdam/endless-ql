grammar Turtleage;

INT   : [1-9][0-9]* ;

ID    : [a-z][a-zA-Z]* ;

WS      : [ \t\r\n]+ -> skip ;
COMMENT : '//' .*? '\n' -> skip ;

program : sentence+ ;

sentence : ((method_call '.') | method_def | repeat) ;

method_call : ID ;
method_def  : ID '=' (method_call | repeat) (',' (method_call | repeat))* '.' ;
repeat      : 'repeat' INT 'times' (method_call | repeat) (',' (method_call | repeat))* '.' ;
