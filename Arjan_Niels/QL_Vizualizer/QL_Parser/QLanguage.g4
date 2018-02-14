grammar QLanguage;

/*
 * Parser Rules
*/
formDeclaration		: 'form' formName '{' (section)* '}';
section				: (question | conditionalBlock);
question			: TEXT ID ':' QTYPE;
conditionalBlock	: 'if x { }';

formName			: ID;

 /*
  * Lexer Rules
 */

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
 
QTYPE				: ('boolean' | 'money' | 'text');
ID					: [a-zA-Z0-9]+ ;
TEXT				: '"' .*? '"' ;
WS					: [ \t\n\r]+ -> skip;