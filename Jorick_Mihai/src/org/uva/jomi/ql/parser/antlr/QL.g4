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

questionStmt: IDENTIFIER ':' LABEL Type (expression)? ;
ifStmt: 'if' '(' expression ')' blockStmt ;

expression: Boolean # PrimaryExpr
		  | LABEL  # PrimaryExpr
		  | INT # PrimaryExpr
		  | IDENTIFIER # IdentifierExpr
		  | operator='!' expression # UnaryExpr
          | '(' expression ')' # GroupingExpr
          | expression operator=('*'|'/') expression # BinaryExpr
 	   	  | expression operator=('+'|'-') expression # BinaryExpr
		  | expression operator=('>'|'>='|'<'|'<=') expression # BinaryExpr
 	      | expression operator=('!='|'==') expression # BinaryExpr
 	      | expression operator='&&' expression # BinaryExpr
 	      | expression operator='||' expression # BinaryExpr
		  ;
 
Boolean: TRUE | FALSE ;
Type: BOOLEAN | STRING | INTEGER | DECIMAL | DATE | MONEY;
 
TRUE: 'true' ;
FALSE: 'false' ;


STRING : 'string' ;
BOOLEAN : 'boolean' ;
INTEGER : 'integer' ;
DECIMAL : 'decimal' ;
DATE : 'date' ;
MONEY : 'money' ;

INT: ('1'..'9')+ DIGIT* ;
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