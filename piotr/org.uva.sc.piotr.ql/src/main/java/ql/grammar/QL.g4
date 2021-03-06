grammar QL;

/*
 * Parser Rules
 */

form            
    :   'form' id=IDENTIFIER BEGIN statement* END;

statement
    : question
    | ifStatement
    ;

question        
    :   label=STRING variableName=IDENTIFIER ':' variableType=dataType (OP_ASSIG assignment=expression)?;

dataType
    : TYPE_BOOLEAN          #TypeDeclarationBoolean
    | TYPE_STRING           #TypeDeclarationString
    | TYPE_INTEGER          #TypeDeclarationInteger
    | TYPE_DECIMAL          #TypeDeclarationDecimal
    | TYPE_MONEY            #TypeDeclarationMoney
    | TYPE_DATE             #TypeDeclarationDate
    ;

ifStatement
    :   IF '(' condition=expression ')' BEGIN statement* END elseStatement?
    ;

elseStatement
    : ELSE BEGIN statement* END
    ;

expression
    : '(' subExpression=expression ')'                                              #ExpressionParenthesises
    | lhs=expression binaryOperator=OP_AND rhs=expression                           #ExpressionLogicalAnd
    | lhs=expression binaryOperator=OP_OR rhs=expression                            #ExpressionLogicalOr
    | lhs=expression binaryOperator=OP_GT rhs=expression                            #ExpressionComparisionGreaterThan
    | lhs=expression binaryOperator=OP_GE rhs=expression                            #ExpressionComparisionGreaterEqual
    | lhs=expression binaryOperator=OP_LT rhs=expression                            #ExpressionComparisionLessThan
    | lhs=expression binaryOperator=OP_LE rhs=expression                            #ExpressionComparisionLessEqual
    | lhs=expression binaryOperator=OP_EQ rhs=expression                            #ExpressionComparisionEqual
    | lhs=expression binaryOperator=OP_NEQ rhs=expression                           #ExpressionComparisionNotEqual
    | lhs=expression binaryOperator=OP_MULT rhs=expression                          #ExpressionArithmeticMultiplication
    | lhs=expression binaryOperator=OP_DIV rhs=expression                           #ExpressionArithmeticDivision
    | lhs=expression binaryOperator=OP_PLUS rhs=expression                          #ExpressionArithmeticAddition
    | lhs=expression binaryOperator=OP_MINUS rhs=expression                         #ExpressionArithmeticSubtraction
    | OP_NOT subExpression=expression                                               #ExpressionNegation
    | OP_MINUS subExpression=expression                                             #ExpressionArithmeticMinus
    | variableReference=IDENTIFIER                                                  #ExpressionVariableReference
    | value=(STRING | INTEGER | DECIMAL | MONEY | DATE | BOOL_TRUE | BOOL_FALSE)    #ExpressionSingleValue
;

/*
 * Lexer Rules
 */

OP_AND : '&&' ;
OP_OR  : '||' ;

OP_NOT : '!';  
OP_ASSIG : '=';

OP_MULT  : '*' ;
OP_DIV   : '/' ;
OP_PLUS  : '+' ;
OP_MINUS : '-' ;

OP_GT : '>' ;
OP_GE : '>=' ;
OP_LT : '<' ;
OP_LE : '<=' ;
OP_EQ : '==' ;
OP_NEQ : '!=';

IF      : 'if';
ELSE    : 'else';

BEGIN   : '{';
END     : '}';

TYPE_BOOLEAN    : 'boolean';
TYPE_STRING     : 'string';
TYPE_INTEGER    : 'integer';
TYPE_DECIMAL    : 'decimal';
TYPE_MONEY      : 'money';
TYPE_DATE       : 'date';

BOOL_TRUE    : 'true' | 'TRUE';
BOOL_FALSE   : 'false' | 'FALSE';

WS  :	(' ' | '\t' | '\n' | '\r')  -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

MONEY : '$'[0-9]+'.'[0-9][0-9];
IDENTIFIER:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
INTEGER: '-'?('0'..'9')+;
STRING: '"' .*? '"';
DECIMAL: [0-9]+'.'[0-9]+;

DAY : ('0'..'3')('0'..'9');
MONTH: ('0'..'1')('0'..'9');
YEAR: ('0'..'9')('0'..'9')('0'..'9')('0'..'9');
DATE: '['YEAR '-' MONTH '-' DAY']';

