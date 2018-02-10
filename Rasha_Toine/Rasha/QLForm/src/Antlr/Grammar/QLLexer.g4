lexer grammar QLLexer;
options {  language=Java; }


@lexer::header
{
	package Antlr.Grammar;
	import Antlr.Grammar.*;
}

/*^^^^^^^^^^^^^^^^^^^^^^^^*
	Lexer Rules - Tokens
*^^^^^^^^^^^^^^^^^^^^^^^^*/   
WHITESPACE   : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);
SingleComment: '//' ~[\r\n]+ -> channel(HIDDEN);
BlockComment : '/*' .*? '*/' -> channel(HIDDEN);
IDENT        : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
INTEGER      : ('0'..'9')+;
STRING       : '"' .*? '"';
BOOLEAN      : ('true'|'false');
DIGIT        : ('0'..'9');
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

/*
BooleanType: 'boolean';
IntType    : 'integer';
MoneyType  : 'money';
DecimalType: 'decimal';
DateType   : 'date';
StrType    : 'string';
IfType     : 'if';
ElseType   : 'else';
FormType   : ('Form'|'form');
COLON      : ':';
LCB        : '{';
RCB        : '}';*/


