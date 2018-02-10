grammar QL;

@parser::header {
	package org.uva.jomi.ql.parser.antlr;
}

@lexer::header {
	package org.uva.jomi.ql.parser.antlr;
}

parse: formStmt* EOF ;

formStmt: 'form' IDENTIFIER blockStmt ;

blockStmt: '{' command* '}' ;

command: questionStmt
	   | ifStmt
	   ;

questionStmt: IDENTIFIER ':' LABEL TYPE (expression)? ;
ifStmt: 'if' '(' expression ')' blockStmt ;

expression: BOOLEAN # BooleanExpr
		  | LABEL  # StringExpr
		  | INTEGER # IntegerExpr
		  | IDENTIFIER # IdentifierExpr
		  | operator='!' expression # UnaryExpr
          | '(' expression ')' # GroupingExpr
          | expression operator=('*'|'/') expression # MultiplicationExpr
 	   	  | expression operator=('+'|'-') expression # AdditionExpr
		  | expression operator=('>'|'>='|'<'|'<=') expression # ComparisonExpr
 	      | expression operator=('!='|'==') expression # EqualityExpr
 	      | expression operator='&&' expression # AndExpr
 	      | expression operator='||' expression # OrExpr
		  ;
 
BOOLEAN: TRUE | FALSE ;
TYPE: 'boolean' | 'string' | 'integer' | 'decimal' | 'date' | 'money';
 
TRUE: 'true' ;
FALSE: 'false' ;

INTEGER: ('1'..'9')+ DIGIT* ;
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