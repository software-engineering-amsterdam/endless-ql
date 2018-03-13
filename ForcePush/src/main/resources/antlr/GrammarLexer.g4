lexer grammar GrammarLexer;


//Whitespace and comments
WHITESPACE      : [ \t\r\n]+ -> skip;
COMMENT         :   '/*' .*? '*/' -> skip;
LINE_COMMENT    :   '//' ~[\r\n]* -> skip;

//Class types
FORM            : 'form';

//Types
BOOL            : 'boolean';
STR             : 'string';
INT             : 'integer';
DATE            : 'date';
DECIMAL         : 'decimal';
MULTIPLEANSWER  : 'multipleAnswer';
MONEY           : 'money' | 'currency';

//Operators
ASSIGN          : ':';
IF              : 'if';
ELSE            : 'else';
IFELSE          : 'ifelse';

//Separators
LPAREN          : '(';
RPAREN          : ')';
LBRACE          : '{';
RBRACE          : '}';
SEMI            : ';';
COMMA           : ',';
DOT             : '.';

//Arithmetic
PLUS            : '+';
MINUS           : '-';
MULTIPLY        : '*';
DIVIDE          : '/';
EQUAL           : '=';

//Comparisons
LESS            : '<';
GREATER         : '>';
EQUALGREATER    : '>=';
EQUALLESS       : '<=';
NOTEQUAL        : '!=';
ISEQUAL         : '==';

//Booleans
AND             : '&&';
OR              : '||';
NOT             : '!';

//Variables
NUM             : [0-9]+;
VAR             : [A-Za-z][A-Za-z0-9]*;
LABEL           : '"'[A-Za-z0-9 ,.?/:]+'"';
DEC             : [0-9]+'.'[0-9]+;


