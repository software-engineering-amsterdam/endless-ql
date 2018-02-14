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

identifier
 : IDENT;
   

questionType
   : 'money'
   | 'integer'
   | 'boolean'
   | 'string'
   | 'date'
   | 'decimal'; 

expr
  : identifier
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
  : identifier ':' STRING questionType  // question to be answered
  | identifier ':' STRING questionType '(' expr ')' // question to be computed - expr eval
 ;
  
ifElseStatement //if or if-else-statement
   : 'if' '(' expr ')' block ('else' block)?
  ;
 
block // block of multiple statements
   :'{' statement* '}'
  ; 
 
form // form
   : ('Form'|'form') identifier block;

// TODO check whether it is relevant to QL
//questionnaire: ('Questionnaire'|'questionnaire') identifier '{' form* '}';
