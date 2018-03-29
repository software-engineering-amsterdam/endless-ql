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
numericExpression : ADDITIVE_OPERATION? PAREN_OPEN numericExpression PAREN_CLOSE
                  | numericExpression MULTIPLICATIVE_OPERATION numericExpression
                  | numericExpression ADDITIVE_OPERATION numericExpression
                  | ADDITIVE_OPERATION? NUMBER
                  | ADDITIVE_OPERATION? IDENTIFIER
                  ;

booleanExpression : NOT? PAREN_OPEN booleanExpression PAREN_CLOSE
                  | booleanExpression AND booleanExpression
                  | booleanExpression OR booleanExpression
                  | numericExpression COMPARISON_OPERATOR numericExpression
                  | NOT? BOOLEAN
                  | NOT? IDENTIFIER
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
AND: '&&';
OR: '||';
NOT:  '!' ;

MULTIPLICATIVE_OPERATION : '*'
                         | '/'
                         ;
ADDITIVE_OPERATION : '+'
                   | '-'
                   ;
