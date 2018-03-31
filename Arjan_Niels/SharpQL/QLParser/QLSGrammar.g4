grammar QLSGrammar;

/*
 * Parser rules
*/
stylesheet			: STYLESHEET ID LCURLY (page|defaults)* RCURLY;
page				: PAGE TEXT LCURLY (section|defaults)* RCURLY;
section				: SECTION TEXT LCURLY (section |question|defaults)* RCURLY;
question			: QUESTION ID widgetspecification
					| QUESTION ID;
defaults			: DEFAULT QTYPE LCURLY stylevalue* widgetspecification? RCURLY;

stylevalue			: ID COLON (TEXT | DOUBLE | HEX | INT);
widgetspecification	: WIDGET WIDGETTYPE widgettypearguments;
widgettypearguments	: LPAREN TEXT (COMMA TEXT)+ RPAREN;

DOUBLE				: INT PT INT
					| PT INT;
HEX					: HASHTAG INT;

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
COLON				: ':';
HASHTAG				: '#';

QTYPE				: 'boolean' | 'money' | 'text' | 'integer' | 'double' | 'hex';
WIDGETTYPE			: 'checkbox' | 'spinner' | 'radio' | 'textfield';

ID					: [a-zA-Z][a-zA-Z0-9\-]+;
TEXT				: '"' .*? '"' ;
INT					: [0-9]+;		
PT					: '.';


/*
 * Ignore
*/
LINE_COMMENT		: '//' ~[\r\n]* -> skip;
WS					: [ \t\n\r]+ -> skip;