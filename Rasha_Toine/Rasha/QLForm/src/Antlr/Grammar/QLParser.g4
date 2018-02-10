grammar QLParser;
options {  language=Java; }

@parser::header
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