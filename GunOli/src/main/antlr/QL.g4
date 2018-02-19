grammar QL;

root        : FORM IDENTIFIER block EOF;
block       : '{' statement+ '}';
statement   : condition | question;
condition   : IF '(' expression ')' block;
question    : IDENTIFIER ':' STRING type;

expression  : '(' expression ')'                                       # parenExpr
          | MINUS expression                                          # negExpr
          | NOT expression                                            # notExpr
          | left=expression op=(MUL | DIV) right=expression           # opExpr
          | left=expression op=(PLUS | MINUS) right=expression        # opExpr
          | left=expression op=(LE | LT | GE | GT) right=expression   # boolExpr
          | left=expression op=(EQ | NE) right=expression             # compExpr
          | left=expression op=(AND | OR) right=expression            # andOrExpr
          | constant                                                  # constExpr
          ;

constant  : INTEGER                                                  # intConstant
          | DECIMAL                                                   # floatConstant
          | STRING                                                    # strConstant
          | IDENTIFIER                                                # idConstant
          | MONEY                                                     # moneyConstant
          | DATE                                                      # dateConstant
          ;

type      : BOOLEANTYPE
          | STRINGTYPE
          | MONEYTYPE
          | INTEGERTYPE
          | DATETYPE
          | DECIMALTYPE
          ;

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

// Terms
FORM            : 'form';
IF              : 'if';
BOOLEANTYPE     : 'boolean';
STRINGTYPE      : 'string';
MONEYTYPE       : 'money';
INTEGERTYPE     : 'integer';
DATETYPE        : 'date';
DECIMALTYPE     : 'decimal';

// Literals
INTEGER         : [0-9]+;
DECIMAL         : [0-9]+ '.' [0-9]+;
MONEY           : ([0-9]+ '.' [0-9]+) | [0-9]+;
DATE            : DAY '-' MONTH '-' YEAR;
DAY             : '0'[0-9] | [1-2][0-9] | '3'[0-1];
MONTH           : '0'[1-9] | '1'[0-2];
YEAR            : [0-2][0-9][0-9][0-9];
STRING          : '"' .*? '"';
IDENTIFIER      : [a-zA-Z0-9]+;

WHITESPCAE      : [ \t\r\n]+ -> skip;
MULTI_COMMENT   : ('/*' .*? '*/') -> skip;
SINGLE_COMMENT  : '//' ~[\r\n]* -> skip;