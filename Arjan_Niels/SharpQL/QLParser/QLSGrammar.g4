grammar QLSGrammar;

/*
 * Parser rules
*/
stylesheet			: STYLESHEET ID LCURLY pages* RCURLY;
pages				: PAGE LCURLY RCURLY;






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