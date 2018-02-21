grammar Parser;

form            : 'form' Identifier '{''}' ;

question        : StringLiteral Identifier ':' type ;

type            : 'boolean'
                | 'integer'
                | 'string'
                | 'money'
                ;


expression      : BooleanLiteral                                        #booleanLiteral
                | Identifier                                            #identifier
                | StringLiteral                                         #stringLiteral
                | IntegerLiteral                                        #integerLiteral
                | '+' expression                                        #unaryPlusExpr
                | '-' expression                                        #unaryMinusExpr
                | '!' expression                                        #notExpr
                | expression op=('*' | '/' ) expression                 #multiplicationExpr
                | expression op=('+' | '-') expression                  #additiveExpr
                | expression op=('<' | '<=' | '>' | '>=') expression    #relationalExpr
                | expression op=('==' | '!=') expression                #equalityExpr
                | expression '&&' expression                            #andExpr
                | expression '||' expression                            #orExpr
                | '(' expression ')'                                    #groupedExpression
                ;


ifStatement     : 'if' '(' expression ')' '{' '}';

// Tokens
WS              : (' ' | '\t' | '\n' | '\r')-> channel(HIDDEN) ;
Comment         : ('/*' .* '*/') -> channel(HIDDEN) ;
BooleanLiteral  : ('true' | 'false') ;
Identifier      : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')* ;
StringLiteral   : '"' (~'"')* '"' ;
IntegerLiteral  : ('0'..'9')+ ;
Operator        : ('/' | '*' | '-' | '+' | '<' | '<=' | '>' | '>=' | '==' | '!=' | '&&' | '||');