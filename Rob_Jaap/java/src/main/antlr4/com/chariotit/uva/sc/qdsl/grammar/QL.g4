grammar QL;


/**
 * Parser rules
 */
forms           : form+ EOF ;
form            : FORM label BRACKET_L elem+ BRACKET_R ;

label           : WORD ;
line_elm        : label COLON question type_expr ;
elem            : line_elm | block_elm ;
block_elm       : if_block ;
if_block        : IF PARENTH_L expr PARENTH_R BRACKET_L elem+ BRACKET_R ;
question        : STRING ;
expr            : unop expr | (constant binop expr) | (label binop expr) | constant | label_expr ;
label_expr      : label ;
constant        : NUMBER ;
binop           : (MINUS | PLUS | MULTIPLY | DIVIDE | EQ | NEQ | GTE | GT | LTE | LT) ;
unop            : (MINUS | PLUS | NOT) ;
type_expr       : type
                | type PARENTH_L expr PARENTH_R ;
type            : (BOOLEAN_TYPE | MONEY_TYPE | INTEGER_TYPE | STRING_TYPE) ;


/**
 * Lexer rules
 */

FORM            : 'form' ;
IF              : 'if' ;
BOOLEAN_TYPE    : 'boolean' ;
STRING_TYPE     : 'string' ;
INTEGER_TYPE    : 'integer' ;
MONEY_TYPE      : 'money' ;
BRACKET_L       : '{' ;
BRACKET_R       : '}' ;
PARENTH_L       : '(' ;
PARENTH_R       : ')' ;
MINUS           : '-' ;
PLUS            : '+' ;
MULTIPLY        : '*' ;
DIVIDE          : '/' ;
COLON           : ':' ;
EQ              : '==' ;
NEQ             : '!=' ;
GTE             : '>=' ;
GT              : '>' ;
LTE             : '<=' ;
LT              : '<' ;
NOT             : '!' ;


NEWLINE         : ('\r'? '\n' | '\r')+ -> skip ;
WHITESPACE      : (' ' | '\t') -> skip ;

NUMBER          : [0-9]+ ;
WORD            : [a-zA-Z]+[a-zA-Z0-9]*;
STRING          : '"'(.*?)'"';