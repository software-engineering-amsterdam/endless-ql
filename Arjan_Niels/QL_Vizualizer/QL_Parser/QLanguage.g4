grammar QLanguage;

/*
 * Parser Rules
*/
formDeclaration		: FORM formName LCURLY (section)* RCURLY;
section				: question | conditionalBlock;
question			: TEXT ID COLON QTYPE;
formName			: ID;

value				: LPAREN statement RPAREN | ID; 
binary				: (AND | OR);
conditionalBlock	: IF statement LCURLY (section)* RCURLY;
statement			: value binary statement | value;

 /*
  * Lexer Rules
 */
LCURLY				: '{';
RCURLY				: '}';
LPAREN				: '(';
RPAREN				: ')';
COLON				: ':';
NOT					: 'not';
AND					: '&&';
OR					: '||';
IF					: 'if';
FORM				: 'form';
QTYPE				: 'boolean' | 'money' | 'text' | 'integer';
ID					: [a-zA-Z0-9]+ ;
TEXT				: '"' .*? '"' ;

LINE_COMMENT		: '//' ~[\r\n]* -> skip;
WS					: [ \t\n\r]+ -> skip;