grammar QL;

/*
 * Parser Rules
 */

form            
    :   'form' id=IDENTIFIER '{' block+ '}';

block           
    :   question | ifBlock;

question        
    :   label=STRING fieldName=IDENTIFIER ':' fieldType=dataType (OP_ASSIG expression)?;

dataType        
    :   TYPE_BOOLEAN | TYPE_STRING | TYPE_INTEGER | TYPE_DECIMAL
    ;

ifBlock         
    :   'if' '(' condition=expression ')' '{' block* '}' elseBlock?;

elseBlock
    :   'else' '{' block* '}'
    ;

expression
    : value
    | variableReference
    | '(' expression ')'
    | OP_NOT '(' expression ')'
    | lhs=expression binaryOperator=(OP_MULT|OP_DIV) rhs=expression
    | lhs=expression binaryOperator=(OP_PLUS|OP_MINUS) rhs=expression
    | lhs=expression binaryOperator=(OP_GT|OP_GE|OP_LT|OP_LE|OP_EQ|OP_NEQ) rhs=expression
    | lhs=expression binaryOperator=(OP_AND|OP_OR) rhs=expression
;

variableReference
    : IDENTIFIER
    ;

value
    : STRING
    | INTEGER
    | DECIMAL
    | (BOOL_TRUE | BOOL_FALSE)
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
