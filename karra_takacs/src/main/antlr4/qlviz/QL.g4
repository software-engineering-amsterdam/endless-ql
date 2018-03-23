grammar QL;
import QLCommon;
//file to define grammar

form : FORM_HEADER IDENTIFIER BRACKET_OPEN questionBlock+ BRACKET_CLOSE EOF;
question: questionText questionName QUESTION_DELIMITER QUESTION_TYPE computedValue?;
questionText: STRING ;
questionBlock:  question+
             | conditionalBlock+ ;
questionName: IDENTIFIER;
conditionalBlock:  IF  PAREN_OPEN (booleanExpression | IDENTIFIER)PAREN_CLOSE
                  BRACKET_OPEN questionBlock+ BRACKET_CLOSE;
computedValue: '=' numericExpression | booleanExpression;

//expressions
numericExpression : PAREN_OPEN numericExpression PAREN_CLOSE
                  | numericExpression MULTIPLICATIVE_OPERATION numericExpression
                  | numericExpression ADDITIVE_OPERATION numericExpression
                  | UNARY_NUMERIC_OPERATOR numericExpression
                  | NUMBER
                  | IDENTIFIER
                  ;

booleanExpression : BOOLEAN
                  | booleanExpression BINARY_BOOLEAN_OPERATOR booleanExpression
                  | UNARY_BOOLEAN_OPERATOR booleanExpression
                  | numericExpression COMPARISON_OPERATOR numericExpression
                  | IDENTIFIER
                  | PAREN_OPEN booleanExpression PAREN_CLOSE
                  ;

expression: numericExpression | booleanExpression;


QUESTION_DELIMITER: ':';





//comparision operators

COMPARISON_OPERATOR: WHITESPACE COMPARISON WHITESPACE;
fragment COMPARISON : '<'
                    | '>'
                    | '<='
                    | '>='
                    | '=='
                    | '!='
                    ;

BINARY_BOOLEAN_OPERATOR:  ('&&' | '||') ;
UNARY_BOOLEAN_OPERATOR:  '!' ;

MULTIPLICATIVE_OPERATION : '*'
                         | '/'
                         ;
ADDITIVE_OPERATION : '+'
                   | '-'
                   ;

UNARY_NUMERIC_OPERATOR:  ('-' | '+') ;
