grammar QLanguage;

/*
 * Parser Rules
*/
formDeclaration		: FORM formName LCURLY (section)* RCURLY;
section				: computedVariable | question | conditionalBlock;
question			: TEXT ID COLON QTYPE;
computedVariable	: TEXT ID COLON QTYPE EQUAL artithmeticExpression;
formName			: ID;

conditionalBlock	: IF logicalExpression LCURLY (section)* RCURLY;


logicalExpression	: LEFT=logicalExpression OPR=(AND | OR) RIGHT=logicalExpression
					| comparisonExpression
					| LPAREN logicalExpression RPAREN
					| logicalEntity;
logicalEntity		: (ID | TRUE | FALSE);

comparisonExpression: LEFT=comparisonOperand comparisonOperator RIGHT=comparisonOperand
					| LPAREN comparisonExpression RPAREN;

comparisonOperand	: artithmeticExpression;


comparisonOperator	: GT| GE | LT | LE | EQ;


artithmeticExpression	: artithmeticExpression (MULT | DIV) artithmeticExpression
						| artithmeticExpression (PLUS | MIN) artithmeticExpression
						| MIN artithmeticExpression
						| LPAREN artithmeticExpression RPAREN
						| ID;

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

MIN					: '-';
PLUS				: '+';
MULT				: '*';
DIV					: '/';

GT					: '>';
GE					: '>=';
LT					: '<';
LE					: '<=';
EQ					: '==';


FORM				: 'form';
QTYPE				: 'boolean' | 'money' | 'text' | 'integer';
TRUE				: 'true';
FALSE				: 'false';
ID					: [a-zA-Z0-9]+ ;
TEXT				: '"' .*? '"' ;

LINE_COMMENT		: '//' ~[\r\n]* -> skip;
WS					: [ \t\n\r]+ -> skip;