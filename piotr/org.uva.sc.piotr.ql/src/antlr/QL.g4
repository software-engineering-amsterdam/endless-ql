grammar QL;

/*
 * Parser Rules
 */

unit        : form*?;

form        : 'form' id=Ident '{' block* '}';

block       : question | condition;

question    : label=Str fieldDefinition;

fieldDefinition : variable=Ident ':' fieldType=type assignment?;

type        : TYPE_BOOLEAN | TYPE_STRING | TYPE_INTEGER | TYPE_DECIMAL | TYPE_MOMEY | TYPE_DATE;

condition   : 'if' cond=expression '{' block* '}';

expression  : '(' expression ')'
            | literal=Ident
            | NOT expression
            | expression bop=(MULT|DIV) expression
            | expression bop=(PLUS|MINUS) expression
            | expression bop=(GT|GE|LT|LE|EQ|NEQ) expression
            | expression bop=(AND|OR)
;

assignment  : '=' expression;

/*
 * Lexer Rules
 */

AND : '&&' ;
OR  : '||' ;

NOT : '!';
ASSIG : '=';

MULT  : '*' ;
DIV   : '/' ;
PLUS  : '+' ;
MINUS : '-' ;

GT : '>' ;
GE : '>=' ;
LT : '<' ;
LE : '<=' ;
EQ : '==' ;
NEQ : '!=';

TYPE_BOOLEAN    : 'boolean';
TYPE_STRING     : 'string';
TYPE_INTEGER    : 'integer';
TYPE_DECIMAL    : 'decimal';
TYPE_MOMEY      : 'money';
TYPE_DATE       : 'date';

WS  :	(' ' | '\t' | '\n' | '\r')  -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int: ('0'..'9')+;

Str: '"' .*? '"';