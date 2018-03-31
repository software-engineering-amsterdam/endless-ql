grammar QL;

root            : FORM identifier=IDENTIFIER block EOF;
block           : '{' statement* '}';
statement       : condition | question;
condition       : IF '(' expression ')' conditionTrueBlock=block ELSE conditionFalseBlock=block
                | IF '(' expression ')' conditionTrueBlock=block;
question        : label=STRING identifier=IDENTIFIER ':' (type | type '=' expression);

// Expressions, prioritized from top to bottom
// label them for easier evaluation
// inspired by: https://stackoverflow.com/a/23092428
expression      : '(' expression ')'                                                # parenthesesExpression
                | MINUS expression                                                  # negationExpression
                | NOT expression                                                    # notExpression
                | left=expression operator=(MUL | DIV) right=expression             # arithmeticExpression
                | left=expression operator=(PLUS | MINUS) right=expression          # arithmeticExpression
                | left=expression operator=(LE | LT | GE | GT) right=expression     # booleanExpression
                | left=expression operator=(EQ | NE) right=expression               # comparisonExpression
                | left=expression operator=AND right=expression                     # andOrExpression
                | left=expression operator=OR right=expression                      # andOrExpression
                | constant                                                          # constantExpression
                ;

type            : BOOLEANTYPE
                | STRINGTYPE
                | INTEGERTYPE
                | DATETYPE
                | DECIMALTYPE
                | MONEYTYPE;

constant        : (TRUE | FALSE)    # booleanConstant
                | INTEGER           # integerConstant
                | DECIMAL           # decimalConstant
                | MONEY             # moneyConstant
                | STRING            # stringConstant
                | IDENTIFIER        # identifierConstant;

// Operators
PLUS            : '+';
MINUS           : '-';
MUL             : '*';
DIV             : '/';
GT              : '>';
GE              : '>=';
LT              : '<';
LE              : '<=';
EQ              : '==';
NE              : '!=';
AND             : '&&';
OR              : '||';
NOT             : '!';
TRUE            : 'true';
FALSE           : 'false';

// Keywords
FORM            : 'form';
BOOLEANTYPE     : 'boolean';
STRINGTYPE      : 'string';
INTEGERTYPE     : 'integer';
DATETYPE        : 'date';
DECIMALTYPE     : 'decimal';
MONEYTYPE       : 'money';
IF              : 'if';
ELSE            : 'else';

// Literals
INTEGER         : [0-9]+;
MONEY           : ([0-9]+ '.' [0-9] [0-9]);
DECIMAL         : [0-9]+ '.' [0-9]+;
STRING          : '"' .*? '"';
IDENTIFIER      : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

WS              : [ \t\r\n]+ -> skip;
COMMENT         : ('/*' .*? '*/') -> skip;
LINE_COMMENT    : '//' ~[\r\n]* -> skip;