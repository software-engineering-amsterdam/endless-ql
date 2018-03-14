grammar QL;


/**
 * Parser rules
 */
forms               : form+ EOF ;
form                : FORM label BRACKET_L elem+ BRACKET_R ;

label               : WORD ;
line_elm            : label COLON question type_expr ;
elem                : line_elm | block_elm ;
block_elm           : if_block ;
if_block            : IF PARENTH_L expr PARENTH_R BRACKET_L elem+ BRACKET_R
                    | IF PARENTH_L expr PARENTH_R BRACKET_L elem+ BRACKET_R ELSE BRACKET_L
                    else_elems
                    BRACKET_R ;
else_elems          : elem+ ;
question            : STRING ;
expr                : unop_expr | label_binop_expr | const_binop_expr | constant | label_expr ;
unop_expr           : unop expr ;
label_binop_expr    : label_expr binop expr ;
const_binop_expr    : constant binop expr ;
label_expr          : WORD ;
constant            : money_constant | string_constant | boolean_constant | integer_constant ;
money_constant      : NUMBER '.' NUMBER ;
string_constant     : STRING ;
boolean_constant    : TRUE | FALSE ;
integer_constant    : NUMBER ;
binop               : (MINUS | PLUS | MULTIPLY | DIVIDE | EQ | NEQ | GTE | GT | LTE | LT) ;
unop                : (MINUS | PLUS | NOT) ;
type_expr           : typeNode
                    | typeNode PARENTH_L expr PARENTH_R ;
typeNode                : (BOOLEAN_TYPE | MONEY_TYPE | INTEGER_TYPE | STRING_TYPE) ;


/**
 * Lexer rules
 */

FORM            : 'form' ;
IF              : 'if' ;
ELSE            : 'else' ;
BOOLEAN_TYPE    : 'boolean' ;
STRING_TYPE     : 'string' ;
INTEGER_TYPE    : 'integer' ;
MONEY_TYPE      : 'money' ;
TRUE            : 'true' ;
FALSE           : 'false' ;
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