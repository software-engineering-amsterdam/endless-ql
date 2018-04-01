grammar QL;

@parser::header
{
package ql.antlr;
}

@lexer::header
{
package ql.antlr;
}

form            : 'form' id '{' statement* '}' ;

statement       : description id ':' type                                           #question
                | description id ':' type '=' expression                            #calculableQuestion
                | 'if' '(' expression ')' '{' statement* '}'                            #ifThen
                | 'if' '(' expression ')' '{' statement* '}' 'else' '{' statement* '}'    #ifThenElse
                ;

description     : StringLiteral;

id              : ID;

type            : 'boolean' #booleanType
                | 'integer' #integerType
                | 'string'  #stringType
                | 'money'   #moneyType
                ;

expression      : BooleanLiteral                            #booleanLiteral
                | ID                                        #identifier
                | StringLiteral                             #stringLiteral
                | IntegerLiteral                            #integerLiteral
                | '(' expression ')'                        #groupExpression
                | '+' expression                            #unaryPlusExpr
                | '-' expression                            #unaryMinusExpr
                | '!' expression                            #notExpr
                | left=expression op='/'  right=expression  #division
                | left=expression op='*'  right=expression  #multiplication
                | left=expression op='-'  right=expression  #subtraction
                | left=expression op='+'  right=expression  #addition
                | left=expression op='<'  right=expression  #lessThan
                | left=expression op='>'  right=expression  #greaterThan
                | left=expression op='<=' right=expression  #lessThanOrEqual
                | left=expression op='>=' right=expression  #greaterThanOrEqual
                | left=expression op='==' right=expression  #equal
                | left=expression op='!=' right=expression  #notEqual
                | left=expression op='&&' right=expression  #logicalAnd
                | left=expression op='||' right=expression  #logicalOr
                ;

// Tokens
WS              : (' ' | '\t' | '\n' | '\r')-> channel(HIDDEN) ;
Comment         : ('/*' .*? '*/') -> channel(HIDDEN) ;
BooleanLiteral  : ('true' | 'false') ;
ID      : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')* ;
StringLiteral   : '"' (~'"')* '"' ;
IntegerLiteral  : ('0'..'9')+ ;