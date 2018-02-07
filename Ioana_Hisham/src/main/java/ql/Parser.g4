grammar Parser;

form            : 'form' Identifier '{''}' ;

question        : StringLiteral Identifier ':' type ;

type            : 'boolean'
                | 'integer'
                | 'string'
                | 'money'
                ;

// Tokens
WS              : (' ' | '\t' | '\n' | '\r')-> channel(HIDDEN) ;
Comment         : ('/*' .* '*/') -> channel(HIDDEN) ;
BooleanLiteral  : ('true' | 'false') ;
Identifier      : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')* ;
StringLiteral   : '"' (~'"')* '"' ;
IntegerLiteral  : ('0'..'9')+ ;