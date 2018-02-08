grammar QL;
options {  language=Java; }

@parser::header
{
	package Antlr.Grammar;
	import Antlr.Grammar.*;
}

@lexer::header
{
	package Antlr.Grammar;
	import Antlr.Grammar.*;
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
  /*| NOT expr 
  | LP expr RP
  | expr (AND|OR) expr
  | expr (EQ|NEQ) expr
  | expr (MUL|DIV) expr
  | expr (ADD|SUB) expr
  | expr (LT|LTEQ|GT|GTEQ) expr
 ;*/

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



