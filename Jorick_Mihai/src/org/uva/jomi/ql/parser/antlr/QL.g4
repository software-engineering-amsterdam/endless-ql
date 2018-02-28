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
	   | ifElseStmt
	   ;

questionStmt: LABEL IDENTIFIER ':' TYPE (expression)? ;
ifStmt: 'if' '(' expression ')' blockStmt ;
ifElseStmt: 'if' '(' expression ')' ifBlock=blockStmt 'else' elseBlock=blockStmt;

expression: BOOLEAN # BooleanExpr
		  | LABEL  # StringExpr
		  | INTEGER # IntegerExpr
		  | IDENTIFIER # IdentifierExpr
		  | operator='!' expression # UnaryExpr
          | '(' expression ')' # GroupingExpr
          | expression operator=('*'|'/') expression # MultiplicationOrDivisionExpr
 	   	  | expression operator=('+'|'-') expression # AdditionOrSubtractionExpr
		  | expression operator=('>'|'>='|'<'|'<=') expression # ComparisonExpr
 	      | expression operator=('!='|'==') expression # EqualityExpr
 	      | expression operator='&&' expression # AndExpr
 	      | expression operator='||' expression # OrExpr
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