grammar QL;

@parser::header
{
package ql.antlr;
}

@lexer::header
{
package ql.antlr;
}

form            : 'form' Identifier '{' statement* '}' ;

statement       : description id ':' type ('=' expression)?     #question
                | 'if' '(' expression ')' '{' statement* '}'    #ifThen
                ;

//BEGIN STATEMENTS

// ('=' expresion) part is optional and is treated only in case its enabling condition/s are satisfied
//question        : StringLiteral Identifier ':' type ('=' expression)? ; //TODO keep in check if something else than '=' will be used

//ifStatement     : 'if' '(' expression ')' '{' statement* '}';

//END STATEMENTS

description     : StringLiteral;

id              : Identifier;

type            : 'boolean' #booleanType
                | 'integer' #integerType
                | 'string'  #stringType
                | 'money'   #moneyType
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

// Tokens
WS              : (' ' | '\t' | '\n' | '\r')-> channel(HIDDEN) ;
Comment         : ('/*' .*? '*/') -> channel(HIDDEN) ;
BooleanLiteral  : ('true' | 'false') ;
Identifier      : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')* ;
StringLiteral   : '"' (~'"')* '"' ;
IntegerLiteral  : ('0'..'9')+ ;