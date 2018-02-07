grammar Parser;

form            : 'form' Identifier '{''}';


// Tokens
WS              : (' ' | '\t' | '\n' | '\r')-> channel(HIDDEN);
Comment         : ('/*' .* '*/') -> channel(HIDDEN);
BooleanLiteral  : ('true' | 'false');
Identifier      : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;
StringLiteral   : '"' (~'"')* '"';
IntegerLiteral  : ('0'..'9')+;