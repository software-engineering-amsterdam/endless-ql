// define a grammar called Hello
grammar HelloWorld;
r   : 'hello' ID;
ID  : [a-z]+ ;
WS  : [ \t\r\n]+ -> skip ;