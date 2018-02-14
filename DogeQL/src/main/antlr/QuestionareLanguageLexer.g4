lexer grammar QuestionareLanguageLexer;

TYPE : STRING | INT | BOOLEAN | MONEY;

FORM : 'form';
INT : 'int';
BOOLEAN : 'boolean' ;
STRING : 'string' ;
MONEY : 'money' ;
IF: 'if';

// Separators
LPAREN: '(' ;
RPAREN: ')' ;
LBRACE: '{' ;
RBRACE: '}' ;
COLON : ':' ;

IDENTIFIER : [a-zA-Z]+;
STRING_LITERAL : '"' .*? '"' ;

WS: [ \t\r\n\u000C]+ -> channel(HIDDEN) ;