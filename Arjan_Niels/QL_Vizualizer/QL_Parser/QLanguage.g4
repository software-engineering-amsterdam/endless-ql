grammar QLanguage;

/*
 * Parser Rules
*/
formDeclaration		: FORM formName LCURLY (section)* RCURLY;
section				: computedVariable | question | conditionalBlock;
question			: TEXT ID COLON QTYPE;
computedVariable	: TEXT ID COLON QTYPE EQUAL (artithmeticExpression | comparisonExpression);
formName			: ID;

conditionalBlock	: IF logicalExpression LCURLY (section)* RCURLY;


logicalExpression	: LEFT=logicalExpression OPR=(AND | OR) RIGHT=logicalExpression
					| comparisonExpression
					| LPAREN logicalExpression RPAREN
					| logicalEntity;
logicalEntity		: (ID | TRUE | FALSE);

comparisonExpression: LEFT=comparisonOperand OPR=comparisonOperator RIGHT=comparisonOperand
					| LPAREN comparisonExpression RPAREN;

comparisonOperand	: artithmeticExpression;

comparisonOperator	: GT| GE | LT | LE | EQ;

artithmeticExpression	: LEFT=artithmeticExpression (MULT | DIV) RIGHT=artithmeticExpression
						| LEFT=artithmeticExpression (PLUS | MINUS) RIGHT=artithmeticExpression
						| MINUS artithmeticExpression
						| LPAREN artithmeticExpression RPAREN
						| (ID | DOUBLE | INT);

DOUBLE				: INT PT INT
					| PT INT
					;

 /*
  * Lexer Rules
 */
IF					: 'if';
LCURLY				: '{';
RCURLY				: '}';
LPAREN				: '(';
RPAREN				: ')';
COLON				: ':';
NOT					: 'not';
EQUAL				: '=';
AND					: '&&';
OR					: '||';

MINUS				: '-';
PLUS				: '+';
MULT				: '*';
DIV					: '/';

GT					: '>';
GE					: '>=';
LT					: '<';
LE					: '<=';
EQ					: '==';

FORM				: 'form';
QTYPE				: 'boolean' | 'money' | 'text' | 'integer' | 'double';
TRUE				: 'true';
FALSE				: 'false';
ID					: [a-zA-Z][a-zA-Z0-9]+;
TEXT				: '"' .*? '"' ;

INT					: [0-9]+;		
PT					: '.';

LINE_COMMENT		: '//' ~[\r\n]* -> skip;
WS					: [ \t\n\r]+ -> skip;