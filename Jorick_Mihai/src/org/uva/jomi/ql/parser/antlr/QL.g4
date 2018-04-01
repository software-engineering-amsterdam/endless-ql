grammar QL;

import LexerRules;

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
		  
