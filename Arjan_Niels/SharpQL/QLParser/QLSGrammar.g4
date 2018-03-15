grammar QLSGrammar;

/*
 * Parser rules
*/
stylesheet			: STYLESHEET ID LCURLY page* RCURLY;
page				: PAGE TEXT LCURLY section* RCURLY;
section				: SECTION TEXT LCURLY (section |question)* RCURLY;
question			: QUESTION ID widgetspecification
					| QUESTION ID;

widgetspecification	: WIDGET WIDGETTYPE widgettypearguments;
widgettypearguments	: LPAREN TEXT (COMMA TEXT)+ RPAREN;

/*
 * Lexer rules
*/

STYLESHEET			: 'stylesheet';
PAGE				: 'page';
SECTION				: 'section';
QUESTION			: 'question';
DEFAULT				: 'default';
WIDGET				: 'widget';

LCURLY				: '{';
RCURLY				: '}';
LPAREN				: '(';
RPAREN				: ')';
COMMA				: ',';

WIDGETTYPE			: CHECKBOX | SPINNER | RADIO;
CHECKBOX			: 'checkbox';
SPINNER				: 'spinner';
RADIO				: 'radio';



ID					: [a-zA-Z][a-zA-Z0-9]+;
TEXT				: '"' .*? '"' ;



/*
 * Ignore
*/
LINE_COMMENT		: '//' ~[\r\n]* -> skip;
WS					: [ \t\n\r]+ -> skip;