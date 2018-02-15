grammar QLanguage;

/*
 * Parser Rules
*/
formDeclaration		: 'form' formName '{' (section)* '}';
section				: question | conditionalBlock;
question			: TEXT ID ':' QTYPE;
formName			: ID;

conditionalBlock	: 'if' statement '{' (section)* '}';
statement			: 'x < y';

 /*
  * Lexer Rules
 */

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
 
LPAREN				: '(';
RPAREN				: ')';

AND					: '&&';
OR					: '||';


OPR					: AND | OR;
QTYPE				: 'boolean' | 'money' | 'text' | 'integer';
ID					: [a-zA-Z0-9]+ ;
TEXT				: '"' .*? '"' ;
WS					: [ \t\n\r]+ -> skip;