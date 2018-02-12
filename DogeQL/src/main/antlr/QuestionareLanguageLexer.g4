lexer grammar QuestionareLanguageLexer;

TYPE : STRING | INT | BOOLEAN | MONEY;

FORM : 'form';
INT : 'int';
BOOLEAN : 'boolean' ;
STRING : 'string' ;
MONEY : 'money' ;
IF: 'if';

OPEN_BRACE : '{' ;
CLOSE_BRACE : '}' ;
OPEN_PARENS : '(' ;
CLOSE_PARENS : ')' ;
COLON : ':' ;

IDENTIFIER : [a-zA-Z]+;
STRING_LITERAL : '"' .*? '"' ;

WS: [ \t\r\n\u000C]+ -> channel(HIDDEN) ;