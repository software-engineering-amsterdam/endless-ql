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
// TYPE has to go before ID
// --------------------------------------------------------------------------------------------

TYPE            :   'integer'
                |   'boolean'
                |   'money'
                ;

ID              :   [a-zA-Z][a-zA-Z0-9]*    ;
INTEGER         :   [0-9]+                  ;
BOOLEAN         :   TRUE | FALSE            ;
TRUE            :   '1'                     ;
FALSE           :   '0'                     ;

form            :   'form' ID block
                ;

block           :   '{' part* '}'
                ;

part            :   variable = ID ':' label = LABEL type = TYPE                         # LBL_Question
                |   variable = ID ':' label = LABEL type = TYPE '(' expression ')'      # LBL_ComputedQuestion
                |   'if' '(' expression ')' block                                       # LBL_ConditionalBlock
                ;

expression      :   operator = OPERATOR_NOT expression                                  # LBL_Not_Expression
                |   operator = OPERATOR_MIN expression                                  # LBL_Min_Expression
                |   expression operator = ( OPERATOR_MUL | OPERATOR_DIV ) expression    # LBL_Expression_MulDiv_Expression
                |   expression operator = ( OPERATOR_ADD | OPERATOR_MIN ) expression    # LBL_Expression_AddSub_Expression
                |   INTEGER                                                             # LBL_Integer_Expression
                |   ID                                                                  # LBL_Id_Expression
                |   '(' expression ')'                                                  # LBL_LParen_Expression_RParen
                ;
