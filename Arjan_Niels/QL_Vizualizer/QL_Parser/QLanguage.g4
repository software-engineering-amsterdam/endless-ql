grammar QLanguage;

/*
 * Parser Rules
*/
formDeclaration		: 'form' formName '{' (section)* '}';
section				: (question | conditionalBlock);

formName			: ID ;
question			: TEXT NEWLINE;
conditionalBlock	: 'if x { }';

 /*
  * Lexer Rules
 */

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
 
ID					: [a-zA-Z0-9]+ ;
WORDS               : (ID)+ ;
TEXT				: '"' .*? '"' ;
WS					: [ \t\n\r]+ -> skip ;
NEWLINE             : ('\r'? '\n' | '\r')+ ;