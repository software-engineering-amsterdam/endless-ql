grammar QLSGrammar;

/*
 * Parser rules
*/
stylesheet			: STYLESHEET ID LCURLY page* RCURLY;
page				: PAGE ID LCURLY section* RCURLY;
section				: SECTION ID LCURLY (section |question)* RCURLY;
question			: QUESTION ID;

/*
 * Lexer rules
*/

STYLESHEET			: 'stylesheet';
PAGE				: 'page';
SECTION				: 'section';
QUESTION			: 'question';
DEFAULT				: 'default';

LCURLY				: '{';
RCURLY				: '}';

ID					: [a-zA-Z][a-zA-Z0-9]+;

/*
 * Ignore
*/
LINE_COMMENT		: '//' ~[\r\n]* -> skip;
WS					: [ \t\n\r]+ -> skip;