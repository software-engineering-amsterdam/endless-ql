grammar QL;

/*
 * Parser Rules
 */

form            
    :   'form' id=IDENTIFIER '{' statement* '}';

statement
    :   question | ifStatement;

question        
    :   label=STRING variableName=IDENTIFIER ':' variableType=dataType (OP_ASSIG expression)?;

dataType
    : TYPE_BOOLEAN  #TypeDeclarationBoolean
    | TYPE_STRING   #TypeDeclarationString
    | TYPE_INTEGER  #TypeDeclarationInteger
    | TYPE_DECIMAL  #TypeDeclarationDecimal
    ;

ifStatement         
    :   'if' '(' condition=expression ')' '{' statement* '}' elseStatement?;

elseStatement
    :   'else' '{' statement* '}'
    ;

expression
    : value                                                                                 #ExpressionSingleValue
    | variableReference                                                                     #ExpressionVariabeReference
    | '(' expression ')'                                                                    #ExpressionParenthesises
    | OP_NOT '(' expression ')'                                                             #ExpressionNegation
    | lhs=expression binaryOperator=(OP_MULT|OP_DIV) rhs=expression                         #ExpressionPlusMinOperation
    | lhs=expression binaryOperator=(OP_PLUS|OP_MINUS) rhs=expression                       #ExpressionMultDivOperation
    | lhs=expression binaryOperator=(OP_GT|OP_GE|OP_LT|OP_LE|OP_EQ|OP_NEQ) rhs=expression   #ExpressionCompareOperation
    | lhs=expression binaryOperator=(OP_AND|OP_OR) rhs=expression                           #ExpressionLogcalOperation
;

variableReference
    : IDENTIFIER
    ;

value
    : STRING                    #TypeValueString
    | INTEGER                   #TypeValueInteger
    | DECIMAL                   #TypeValueDecimal
    | (BOOL_TRUE | BOOL_FALSE)  #TypeValueBoolean
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

TYPE_BOOLEAN    : 'boolean';
TYPE_STRING     : 'string';
TYPE_INTEGER    : 'integer';
TYPE_DECIMAL    : 'decimal' | 'money';

BOOL_TRUE    : 'true' | 'TRUE';
BOOL_FALSE   : 'false' | 'FALSE';

WS  :	(' ' | '\t' | '\n' | '\r')  -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

IDENTIFIER:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
INTEGER: ('0'..'9')+;
STRING: '"' .*? '"';
DECIMAL: [0-9]+'.'[0-9]+;
