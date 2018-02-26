grammar QL;

/*
 * Parser Rules
 */

form            : 'form' id=Identifier '{' block+ '}';

block           : question | ifBlock;

question        : label=String fieldDefinition;

fieldDefinition : fieldName=Identifier ':' fieldType=dataType assignment?;

assignment      : '=' arithmeticExpression;

dataType        : TYPE_BOOLEAN | TYPE_STRING | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE | dataTypeMoney ;

dataTypeMoney   : TYPE_MONEY '(' currency=CurrencyCode ')'           # MoneyTypeDeclarationWithCurrency
                | TYPE_MONEY                                # MoneyTypeDeclarationVoid
                ;

ifBlock         : 'if' '(' condition ')' '{' block* '}';

condition       : logicalExpression ;

logicalExpression
                : logicalExpression AND logicalExpression   # LogicalExpressionAnd
                | logicalExpression OR logicalExpression    # LogicalExpressionOr
                | comparisonExpression                      # LogicalExpressionComparison
                | NOT logicalExpression                     # LogicalExpressionNegation
                | '(' logicalExpression ')'                 # LogicalExpressionInParen
                | logicalEntity                             # LogicalExpressionEntity
                ;

comparisonExpression
                : comparisonOperand comparisionOperator comparisonOperand   # ComparisonExpressionWithOperator
                | '(' comparisonExpression ')'                              # ComparisonExpressionParens
                ;

comparisonOperand
                : arithmeticExpression
                ;

comparisionOperator
                : GT
                | GE
                | LT
                | LE
                | EQ
                | NEQ
                ;

arithmeticExpression
                : arithmeticExpression MULT arithmeticExpression    # ArithmeticExpressionMult
                | arithmeticExpression DIV arithmeticExpression     # ArithmeticExpressionDiv
                | arithmeticExpression PLUS arithmeticExpression    # ArithmeticExpressionPlus
                | arithmeticExpression MINUS arithmeticExpression   # ArithmeticExpressionMinus
                | MINUS arithmeticExpression                        # ArithmeticExpressionNegation
                | '(' arithmeticExpression ')'                      # ArithmeticExpressionParens
                | numericEntity                                     # ArithmeticExpressionNumericEntity
                | moneyEntity                                       # ArithmeticExpressionMoneyEntity
                | stringEntity                                      # ArithmeticExpressionMoneyEntity
                | dateEntity                                        # ArithmeticExpressionDateEntity
                | logicalEntity                                     # ArithmeticExpressionLogicalEntity
                ;

logicalEntity   : (TRUE | FALSE)                    # LocicalConst
                | Identifier                        # LogicalVariable
                ;

numericEntity   : Decimal                           # DecimalNumericConst
                | Integer                           # IntegerNumericConst
                | variableReference                 # NumericVariable
                ;

dateEntity      : '@' year=Integer '-' month=Integer '-' day=Integer    # DateValue
                | variableReference                                     # DateVariable
                ;

stringEntity    : text=String                       # StringValue
                | variableReference                 # StringVariable
                ;

moneyEntity     : CurrencyCode '(' Decimal ')'      # MoneyValue
                | variableReference                 # MoneyVariable
                ;

variableReference
                : name=Identifier
                ;

CurrencyCode    : 'A'..'Z''A'..'Z''A'..'Z' // iso4217
                ;


/*
 * Lexer Rules
 */

AND : '&&' ;
OR  : '||' ;

NOT : '!';  
ASSIG : '=';

MULT  : '*' ;
DIV   : '/' ;
PLUS  : '+' ;
MINUS : '-' ;

GT : '>' ;
GE : '>=' ;
LT : '<' ;
LE : '<=' ;
EQ : '==' ;
NEQ : '!=';

TYPE_BOOLEAN    : 'boolean';
TYPE_STRING     : 'string';
TYPE_INTEGER    : 'integer';
TYPE_DECIMAL    : 'decimal';
TYPE_MONEY      : 'money';
TYPE_DATE       : 'date';

TRUE    : 'true' | 'TRUE';
FALSE   : 'false' | 'FALSE';

WS  :	(' ' | '\t' | '\n' | '\r')  -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

Identifier:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Integer: ('0'..'9')+;
String: '"' .*? '"';
Decimal: [0-9]+'.'[0-9]+;


