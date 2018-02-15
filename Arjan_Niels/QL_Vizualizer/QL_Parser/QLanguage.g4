grammar QLanguage;

/*
 * Parser Rules
*/
formDeclaration		: 'form' formName '{' (section)* '}';
section				: question | conditionalBlock;
question			: TEXT ID ':' QTYPE;
formName			: ID;

value				: ID | '(' statement ')'; 
binary				: (AND | OR);
conditionalBlock	: 'if' statement '{' (section)* '}';
statement			: value (binary statement)*;

 /*
  * Lexer Rules
  * Statements
 */

LPAREN				: '(';
RPAREN				: ')';
NOT					: 'not';
AND					: '&&';
OR					: '||';



 /*
  * Lexer Rules
  * Other
 */
QTYPE				: 'boolean' | 'money' | 'text' | 'integer';
ID					: [a-zA-Z0-9]+ ;
TEXT				: '"' .*? '"' ;
WS					: [ \t\n\r]+ -> skip;