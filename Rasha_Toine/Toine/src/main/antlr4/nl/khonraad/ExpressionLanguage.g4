grammar ExpressionLanguage;

WS              :   [ \t\r\n\u000C]+        -> channel(HIDDEN);
COMMENT         :   '/*' .*? '*/'           -> channel(HIDDEN);
LINE_COMMENT    :   '//' ~[\r\n]*           -> channel(HIDDEN);

LABEL           :   '"' .+? '"'             ;

OPERATOR_NOT    :   '!'                     ;
OPERATOR_MUL    :   '*'                     ;
OPERATOR_DIV    :   '/'                     ;
OPERATOR_ADD    :   '+'                     ;
OPERATOR_MIN    :   '-'                     ;

// --------------------------------------------------------------------------------------------
// IOTYPE has to go before ID
// --------------------------------------------------------------------------------------------

IOTYPE           :   'integer'
                 |   'boolean'
                 |   'money'
                 ;

IDENTIFIER       :   [a-zA-Z][a-zA-Z0-9]*    ;
INTEGER_CONSTANT :   [0-9]+                  ;
BOOLEAN_CONSTANT :   TRUE | FALSE            ;
TRUE             :   '1'                     ;
FALSE            :   '0'                     ;

form             :   'form' IDENTIFIER block
                 ;

block            :   '{' part* '}'
                 ;

part             :   variable = IDENTIFIER ':' label = LABEL type = IOTYPE                       # LBL_Question
                 |   variable = IDENTIFIER ':' label = LABEL type = IOTYPE '(' expression ')'    # LBL_ComputedQuestion
                 |   'if' '(' expression ')' block                                               # LBL_ConditionalBlock
                 ;

expression       :   operator = OPERATOR_NOT expression                                          # LBL_Not_Expression
                 |   operator = OPERATOR_MIN expression                                          # LBL_Min_Expression
                 |   expression operator = ( OPERATOR_MUL | OPERATOR_DIV ) expression            # LBL_Expression_MulDiv_Expression
                 |   expression operator = ( OPERATOR_ADD | OPERATOR_MIN ) expression            # LBL_Expression_AddSub_Expression
                 |   INTEGER_CONSTANT                                                            # LBL_Integer_Expression
                 |   BOOLEAN_CONSTANT                                                            # LBL_Boolean_Expression
                 |   IDENTIFIER                                                                  # LBL_Id_Expression
                 |   '(' expression ')'                                                          # LBL_LParen_Expression_RParen
                 ;
