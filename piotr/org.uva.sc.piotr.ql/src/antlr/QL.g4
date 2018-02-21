grammar QL;

/*
 * Parser Rules
 */


form        : 'form' id=Ident '{' block '}';

block       : (question | condition)*
            ;

question    : txt=Str id=Ident ':' (type|equaiton);

type        : 'boolean' | 'string' | 'integer' | 'decimal' | 'money' | 'date';

condition   : 'if' cond=expression '{' block '}';

expression  : '(' expression ')'
            | literal=Ident
            | NOT expression
            | expression bop=(MULT|DIV|MOD) expression
            | expression bop=(PLUS|MINUS) expression
            | expression bop=(GT|GE|LT|LE|EQ) expression
            | expression bop=(AND|OR)
//            | assigType=type ASSIG expression
;

equaiton    : ti=type '=' expression;

/*
 * Lexer Rules
 */

AND : '&&' ;
OR  : '||' ;

NOT : '!';
ASSIG : '=';

MULT  : '*' ;
DIV   : '/' ;
MOD   : '%' ;
PLUS  : '+' ;
MINUS : '-' ;

GT : '>' ;
GE : '>=' ;
LT : '<' ;
LE : '<=' ;
EQ : '==' ;

WS  :	(' ' | '\t' | '\n' | '\r')  -> channel(HIDDEN)
    ;

COMMENT
     : '/*' .*? '*/'  -> channel(HIDDEN)
    ;


Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int: ('0'..'9')+;

Str: '"' .*? '"';