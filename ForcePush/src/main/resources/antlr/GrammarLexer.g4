lexer grammar GrammarLexer;


//Whitespace
WHITESPACE : [ \t\r\n]+ -> skip;

//Keywords
FORM        : 'form';

//Types
BOOL        : 'boolean';
STR         : 'string';
INT         : 'integer';
DATE        : 'date';
DECIMAL     : 'decimal';
MONEY       : 'money' | 'currency';

//Operators
ASSIGN          : ':';
LPAREN          : '(';
RPAREN          : ')';
LCURLYBRACKET   : '{';
RCURLYBRACKET   : '}';
IF              : 'if';
ELSE            : 'else';
IFELSE          : 'ifelse';

//Arithmetic
PLUS        : '+';
MINUS       : '-';
MULTIPLY    : '*';
DIVIDE      : '/';
EQUAL       : '=';

//Comparisons
LESS        : '<';
GREATER     : '>';
EQUALGREATER: '>=';
EQUALLESS   : '<=';
NOTEQUAL    : '!=';
ISEQUAL     : '==';

//Booleans
AND         : '&&';
OR          : '||';
NOT         : '!';

//Variables
NUM         : [0-9]+;
VAR         : [A-Za-z][A-Za-z0-9]*;
LABEL       : '"'[A-Za-z0-9 ,.?/:]+'"';


