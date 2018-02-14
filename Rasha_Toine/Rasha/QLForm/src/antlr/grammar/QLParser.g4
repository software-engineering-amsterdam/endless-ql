grammar QLParser;
options {  language=Java; }

@parser::header
{
	package antlr.grammar;
	import antlr.grammar.*;
}


/*^^^^^^^^^^^^^^^^^^^^*
	Parser Rules
*^^^^^^^^^^^^^^^^^^^^*/
literal // atom
   : MONEY
   | INTEGER
   | BOOLEAN
   | STRING
   | DATE
   | DECIMAL
   | IDENT
  ;
   

questionType
   : 'money'
   | 'integer'
   | 'boolean'
   | 'string'
   | 'date'
   | 'decimal'; 

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

statement 
  : question | ifElseStatement;

question
  : IDENT ':' STRING questionType  // question to be answered
  | IDENT ':' STRING questionType '(' expr ')' // question to be computed - expr eval
 ;
  
ifElseStatement
   : 'if' '(' expr ')' block ('else' block)?;  //if or if-else-statement
 
block 
   :'{' statement* '}'; // block of multiple statements
 
form
   : ('Form'|'form') IDENT block; // form

// TODO check whether it is relevant to QL
//questionnaire: ('Questionnaire'|'questionnaire') IDENT '{' form* '}';
