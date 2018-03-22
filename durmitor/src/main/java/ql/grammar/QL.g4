grammar QL;

// Questionnaire language
form                : 'form' identifier block;
statement           : block
                    | question
                    | ifThenElse
                    | ifThen
                    ;
question            : computedQuestion
                    | answerableQuestion
                    ;
conditionalStatement: ifThen
                    | ifThenElse
                    ;
block               : '{' statement* '}';
ifThen              : 'if' '(' condition=expression ')' thenStatement=statement;
ifThenElse          : ifThen 'else' elseStatement=statement;
computedQuestion    : label identifier ':' type '=' '(' expression ')';
answerableQuestion  : label identifier ':' type;
label               : STRING;
type                : 'boolean' #BooleanType
                    | 'string'  #StringType
                    | 'integer' #IntegerType
                    | 'decimal' #DecimalType
                    | currency  #MoneyType
                    | 'date'    #DateType
                    ;
currency            : UPPERCASE3;
integer             : (DAY|MONTH|YEAR|INTEGER);
decimal             : DECIMAL;
number              : integer   #IntegerLiteral
                    | decimal   #DecimalLiteral
                    ;
money               : currency value=MONEY;
date                : DATE_YMD   #DateYMD
                    | DATE_YDM   #DateYDM
                    | DATE_DMY   #DateDMY
                    | DATE_MDY   #DateMDY
                    ;
literal             : BOOLEAN   #BooleanLiteral
                    | STRING    #StringLiteral
                    | date      #DateLiteral
                    | number    #NumberLiteral
                    | money     #MoneyLiteral
                    ;
identifier          : IDENTIFIER;

// Expressions
primary             : literal
                    | identifier
                    ;
expression          : primary                                                                             #PrimaryExpression
                    | '(' expression ')'                                                                  #BracesExpression
                    | operator = (PLUS|DASH|EXCLAMATION) operand = expression                             #PrefixExpression
                    | firstOperand = expression operator = (ASTERISK|SLASH) secondOperand = expression    #MultiplyExpression
                    | firstOperand = expression operator = (PLUS|DASH) secondOperand = expression         #AddExpression
                    | firstOperand = expression operator = (GE|GT|LT|LE|EQ|NE) secondOperand = expression #RelationalExpression
                    | firstOperand = expression operator = AND secondOperand = expression                 #AndExpression
                    | firstOperand = expression operator = OR secondOperand = expression                  #OrExpression
                    ;

// Tokens
WS                  : (' ' | '\t' | '\n' | '\r') ->channel (HIDDEN);
MLCOMMENT           : '/*' (.)*? '*/' -> channel(HIDDEN);
SLCOMMENT           : '//' (.)*? '\n' -> channel(HIDDEN);

BOOLEAN             : 'true'
                    | 'false'
                    ;

STRING              : '"' (.)*? '"';

MONTH               : '0'? ('1'..'9')
                    | '1' ('0'|'1'|'2')
                    ;
MONTH_NAME          : J A N (U A R Y)?
                    | F E B (R U A R Y)?
                    | M A R (C H)?
                    | A P R (I L)?
                    | M A Y
                    | J U N E?
                    | J U L Y?
                    | A U G (U S T)?
                    | S E P (T E M B E R)?
                    | O C T (O B E R)?
                    | N O V (E M B E R)?
                    | D E C (E M B E R)?
                    ;
DAY                 : '0'? ('1'..'9')
                    | ('1'|'2') ('0'..'9')
                    | '3' ('0'|'1')
                    ;
DAY_SUFFIX          : DAY ('st'|'nd'|'rd'|'th')
                    ;
//DAY_SUFFIX          : '0'? (('1' 'st'?) | ('2' 'nd'?) | ('3' 'rd'?) | (('4'..'9') 'th'?))
//                    | '1' ('0'..'9') 'th'?
//                    | ('2'|'3') '0' 'th'?
//                    | ('2'|'3') '1' 'st'?
//                    | '2' (('2' 'nd'?) | ('3' 'rd'?) | (('4'..'9') 'th'?))
//                    ;
YEAR                : DIGIT DIGIT DIGIT DIGIT;
DATE_YDM            : YEAR DASH (DAY|DAY_SUFFIX) DASH MONTH_NAME
                    | YEAR SLASH (DAY|DAY_SUFFIX) SLASH MONTH_NAME
                    | YEAR DOT (DAY|DAY_SUFFIX) DOT MONTH_NAME
                    | YEAR SPACE (DAY|DAY_SUFFIX) SPACE MONTH_NAME
                    ;
DATE_YMD            : YEAR DASH (MONTH|MONTH_NAME) DASH (DAY|DAY_SUFFIX)
                    | YEAR SLASH (MONTH|MONTH_NAME) SLASH (DAY|DAY_SUFFIX)
                    | YEAR DOT (MONTH|MONTH_NAME) DOT (DAY|DAY_SUFFIX)
                    | YEAR SPACE (MONTH|MONTH_NAME) SPACE (DAY|DAY_SUFFIX)
                    ;
DATE_DMY            : (DAY|DAY_SUFFIX) DASH (MONTH|MONTH_NAME) DASH YEAR
                    | (DAY|DAY_SUFFIX) SLASH (MONTH|MONTH_NAME) SLASH YEAR
                    | (DAY|DAY_SUFFIX) DOT (MONTH|MONTH_NAME) DOT YEAR
                    | (DAY|DAY_SUFFIX) SPACE (MONTH|MONTH_NAME) SPACE YEAR
                    ;
DATE_MDY            : MONTH_NAME DASH (DAY|DAY_SUFFIX) DASH YEAR
                    | MONTH_NAME SLASH (DAY|DAY_SUFFIX) SLASH YEAR
                    | MONTH_NAME DOT (DAY|DAY_SUFFIX) DOT YEAR
                    | MONTH_NAME SPACE (DAY|DAY_SUFFIX) SPACE YEAR
                    ;
            
MONEY               : ('0'..'9')+ '.' ('0'..'9') ('0'..'9');
DECIMAL             : ('0'..'9')+ '.' ('0'..'9')+;
DIGIT               : ('0'..'9');
INTEGER             : DIGIT+;

AND                 : '&&';
OR                  : '||';
LT                  : '<';
LE                  : '<=';
GT                  : '>';
GE                  : '>=';
EQ                  : '==';
NE                  : '!=';
PLUS                : '+';
DASH                : '-';
ASTERISK            : '*';
SLASH               : '/';
EXCLAMATION         : '!';
DOT                 : '.';
SPACE               : ' ';

A                   : 'A' | 'a';
B                   : 'B' | 'b';
C                   : 'C' | 'c';
D                   : 'D' | 'd';
E                   : 'E' | 'e';
F                   : 'F' | 'f';
G                   : 'G' | 'g';
H                   : 'H' | 'h';
I                   : 'I' | 'i';
J                   : 'J' | 'j';
K                   : 'K' | 'k';
L                   : 'L' | 'l';
M                   : 'M' | 'm';
N                   : 'N' | 'n';
O                   : 'O' | 'o';
P                   : 'P' | 'p';
Q                   : 'Q' | 'q';
R                   : 'R' | 'r';
S                   : 'S' | 's';
T                   : 'T' | 't';
U                   : 'U' | 'u';
V                   : 'V' | 'v';
W                   : 'W' | 'w';
X                   : 'X' | 'x';
Y                   : 'Y' | 'y';
Z                   : 'Z' | 'z';
LOWERCASE           : ('a'..'z');
UPPERCASE           : ('A'..'Z');
LETTER              : (LOWERCASE | UPPERCASE);
UPPERCASE3          : UPPERCASE UPPERCASE UPPERCASE;

IDENTIFIER          : LETTER (LETTER | DIGIT | '_')*;
