grammar QL;

/*
 * Parser Rules
 */

form            
    :   'form' id=IDENTIFIER '{' block+ '}';

block           
    :   question | ifBlock;

question        
    :   label=STRING fieldDefinition;

fieldDefinition 
    :   fieldName=IDENTIFIER ':' fieldType=dataType assignment?;

assignment
    :   assignmentOperator=OP_ASSIG expression
    ;

dataType        
    :   TYPE_BOOLEAN | TYPE_STRING | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE | dataTypeMoney 
    ;

dataTypeMoney   
    :   TYPE_MONEY '(' currency=CURRENCY_CODE ')'           # MoneyTypeDeclarationWithCurrency
    |   TYPE_MONEY                                          # MoneyTypeDeclarationVoid
    ;

ifBlock         
    :   'if' '(' expression ')' '{' block* '}';


expression
    :   '(' expression ')'
    |   conditionalExpression
    ;

conditionalExpression
    :	conditionalOrExpression
	;

conditionalOrExpression
	:	conditionalAndExpression
	|	conditionalOrExpression OP_OR conditionalAndExpression
	;

conditionalAndExpression
	:	equalityExpression
	|	conditionalAndExpression OP_AND equalityExpression
	;

equalityExpression
	:	relationalExpression
	|	equalityExpression OP_EQ relationalExpression
	|	equalityExpression OP_NEQ relationalExpression
	;

relationalExpression
	:	additiveExpression
	|	relationalExpression OP_LT additiveExpression
	|	relationalExpression OP_GT additiveExpression
	|	relationalExpression OP_LE additiveExpression
	|	relationalExpression OP_GE additiveExpression
	;

additiveExpression
	:	multiplicativeExpression
	|	additiveExpression OP_PLUS multiplicativeExpression
	|	additiveExpression OP_MINUS multiplicativeExpression
	;

multiplicativeExpression
	:	unaryExpression
	|	multiplicativeExpression OP_MULT unaryExpression
	|	multiplicativeExpression OP_DIV unaryExpression
	;

unaryExpression
	:   '!' unaryExpression
	|   entity
	;


entity
    :   logicalEntity
    |   nonLogicalEntity
    ;


logicalEntity   
    :   (BOOL_TRUE | BOOL_FALSE)          # LocicalConst
    |   IDENTIFIER                        # LogicalVariable
    ;

nonLogicalEntity
    :   numericEntity
    |   dateEntity
    |   stringEntity
    |   moneyEntity
    ;

numericEntity   
    :   DECIMAL                           # DecimalNumericConst
    |   INTEGER                           # IntegerNumericConst
    |   variableReference                 # NumericVariable
    ;

dateEntity      
    :   '@' year=INTEGER '-' month=INTEGER '-' day=INTEGER    # DateValue
    |   variableReference                                     # DateVariable
    ;

stringEntity    
    :   text=STRING                       # StringValue
    |   variableReference                 # StringVariable
    ;

moneyEntity     
    :   CURRENCY_CODE '(' DECIMAL ')'      # MoneyValue
    |   variableReference                  # MoneyVariable
    ;

variableReference
    :   name=IDENTIFIER
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
TYPE_DECIMAL    : 'decimal';
TYPE_MONEY      : 'money';
TYPE_DATE       : 'date';

BOOL_TRUE    : 'true' | 'TRUE';
BOOL_FALSE   : 'false' | 'FALSE';

WS  :	(' ' | '\t' | '\n' | '\r')  -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

CURRENCY_CODE: 'A'..'Z''A'..'Z''A'..'Z'; // iso4217
IDENTIFIER:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
INTEGER: ('0'..'9')+;
STRING: '"' .*? '"';
DECIMAL: [0-9]+'.'[0-9]+;
