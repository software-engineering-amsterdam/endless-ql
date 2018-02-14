grammar QL;
options {  language=Java; }

@parser::header
{
	package antlr.grammar;
	import antlr.grammar.*;
}

@lexer::header
{
	package antlr.grammar;
	import antlr.grammar.*;
}


/*^^^^^^^^^^^^^^^^^^^^*
	Parser Rules
*^^^^^^^^^^^^^^^^^^^^*/
literal: MONEY | INTEGER | BOOLEAN  | STRING | DATE | DECIMAL | IDENT; // atom

questionEnum: 'money' | 'integer' | 'boolean' | 'string' | 'date' | 'decimal';  // enum for type of answer

expr
  : IDENT
  | literal
  | '!' expr
  | '(' expr ')'
  | expr ('&&'|'||') expr
  | expr ('=='|'!=') expr
  | expr ('*'|'/') expr
  | expr ('+'|'-') expr
  | expr ('>'|'>='|'<'|'<=') expr
 ;

statement : question | ifStatement;

question
  : IDENT ':' STRING questionEnum  // question to be answered
  | IDENT ':' STRING questionEnum '(' expr ')' // question to be computed - expr eval
 ;
  
ifStatement
  : 'if' '(' expr ')' block  // if-statement
  | 'if' '(' expr ')' block 'else' elseBlock = block  //if-else-statement
 ;

block: '{' statement* '}'; // block of multiple statements
 
form: ('Form'|'form') IDENT '{' block* '}'; // questionnaire

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
QuadDigits   : ('0'..'9')('0'..'9')('0'..'9')('0'..'9');
MONEY        : DIGIT+ '.' TwoDigits;
DECIMAL      : DIGIT+ '.' DIGIT+;
DATE	     : TwoDigits'-'TwoDigits'-'QuadDigits;

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




