lexer grammar QLLexer;
options {  language=Java; }


@lexer::header
{
	package antlr.grammar;
	import antlr.grammar.*;
}

/*^^^^^^^^^^^^^^^^^^^^^^^^*
	Lexer Rules - Tokens
*^^^^^^^^^^^^^^^^^^^^^^^^*/   
WHITESPACE   : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);
SingleComment: '//' ~[\r\n]+ -> channel(HIDDEN);
BlockComment : '/*' .*? '*/' -> channel(HIDDEN);
IDENT        : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
DIGIT        : ('0'..'9');
INTEGER      : DIGIT+;
STRING       : '"' .*? '"';
BOOLEAN      : ('true'|'false');
TwoDigits    : ('0'..'9')('0'..'9');
FourDigits   : ('0'..'9')('0'..'9')('0'..'9')('0'..'9');
MONEY        : DIGIT+ '.' TwoDigits;
DECIMAL      : DIGIT+ '.' DIGIT+;
DATE	     : TwoDigits'-'TwoDigits'-'FourDigits;

ADD : '+';
SUB : '-';
MUL : '*';
DIV : '/';
LT  : '<';
LTEQ: '<=';
GT  : '>';
GTEQ: '>=';
EQ  : '==';
NEQ : '!=';
NOT : '!';
AND : '&&';
OR  : '||';
LP  : '(';
RP  : ')';

