grammar QL;

@parser::header {
	package org.uva.jomi.ql.parser.antlr;
}

@lexer::header {
	package org.uva.jomi.ql.parser.antlr;
}

parse: formStatement* EOF ;

formStatement: 'form' IDENTIFIER blockStatement ;

blockStatement: '{' command* '}' ;

command: questionStatement
	   | ifStatement
	   | ifElseStatement
	   ;

questionStatement: LABEL IDENTIFIER ':' TYPE ('=' expression)? ;
ifStatement: 'if' '(' expression ')' blockStatement ;
ifElseStatement: 'if' '(' expression ')' ifBlock=blockStatement 'else' elseBlock=blockStatement;

expression: BOOLEAN # BooleanExpression
		  | LABEL  # StringExpression
		  | INTEGER # IntegerExpression
		  | IDENTIFIER # IdentifierExpression
		  | operator='!' expression # UnaryExpression
          | '(' expression ')' # GroupingExpression
          | left=expression operator=('*'|'/') right=expression # MultiplicationOrDivisionExpression
 	   	  | left=expression operator=('+'|'-') right=expression # AdditionOrSubtractionExpression
		  | left=expression operator=('>'|'>='|'<'|'<=') right=expression # ComparisonExpression
 	      | left=expression operator=('!='|'==') right=expression # EqualityExpression
 	      | left=expression operator='&&' right=expression # AndExpression
 	      | left=expression operator='||' right=expression # OrExpression
		  ;
 
BOOLEAN: TRUE | FALSE ;
TYPE: 'boolean' | 'string' | 'integer' | 'decimal' | 'date' | 'money';
 
TRUE: 'true' ;
FALSE: 'false' ;
STAR: '*' ;
SLASH: '/' ;
PLUS: '+' ;
MINUS: '-' ;
BANG: '!' ;
GREATER: '>' ;
GREATER_EQUAL: '>=' ;
LESS: '<' ;
LESS_EQUAL: '<=' ;
BANG_EQUAL: '!=' ;
EQUAL_EQUAL: '==' ;
AND: '&&' ;
OR: '||' ;
INTEGER: ('-')? DIGIT+ ;
LABEL: '"' .*? '"' ;

LINE_COMMENT : '//' .*? '\r'? '\n' -> skip ;
COMMENT : '/*' .*? '*/' -> skip ;
WS: [ \t\r\n] -> skip ;
IDENTIFIER: LETTER+ (LETTER|DIGIT)*  ;
 
// Fragments (Prefix the rule with 'fragment': the rule will be used ONLY by other rules.)
fragment
DIGIT:	[0-9];
fragment
LETTER: [a-zA-Z];

ErrorChar : . ;