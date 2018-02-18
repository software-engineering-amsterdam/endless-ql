grammar QL;

//file to define grammar


form : FORM_HEADER '{' questionBlock+ '}' EOF;
question:   questionName questionText TYPE ;
questionText: STRING QUESTION_DELIMITER;
questionBlock:  question+
             | conditionalBlock+ ;
questionName: IDENTIFIER;
conditionalBlock:  IF  '(' (booleanExpression | IDENTIFIER)')' 
                  BRACKET_OPEN questionBlock+ BRACKET_OPEN;

//To skip New Lines, White spaces and comments
FORM_HEADER :  'form'  -> skip;
NEWLINE: ('\n' | '\r' | '\r\n') -> skip;
WHITESPACE : (' ' | '\n' | '\r' | '\t') -> skip;
COMMENT			: ('/*' .*? '*/') ->skip;
LINE_COMMENT	: '//' ~[\r\n]* ->skip;
BRACKET_OPEN : '{' -> skip;
BRACKET_CLOSE: '}' -> skip;


QUESTION_DELIMITER: ':' ;

TYPE : 'boolean'
     | 'money'
     | 'string'
     | 'integer'
     | 'date'
     | 'decimal'
     ;
     
//keywords
IF	:'if';

//literals
IDENTIFIER	: [a-zA-Z0-1_]+;
NUMBER		: [0-9]+;
BOOLEAN		: 'true' | 'false';
STRING		: '"' .*? '"';




//comparision operators

COMPARISON_OPERATOR: WHITESPACE COMPARISON WHITESPACE;
fragment COMPARISON : '<'
                    | '>'
                    | '<='
                    | '>='
                    | '=='
                    | '!='
                    ;

BINARY_BOOLEAN_OPERATOR: WHITESPACE ('&&' | '||') WHITESPACE;
UNARY_BOOLEAN_OPERATOR: WHITESPACE '!' WHITESPACE;
UNARY_NUMERIC_OPERATOR: WHITESPACE ('-' | '+') WHITESPACE;
BINARY_NUMERIC_OPERATOR: WHITESPACE NUMERIC_OP WHITESPACE;

fragment NUMERIC_OP : '+'
                    | '-'
                    | '*'
                    | '/'
                    ;

//expressions
numericExpression : NUMBER
                  | UNARY_NUMERIC_OPERATOR NUMBER
                  | numericExpression BINARY_NUMERIC_OPERATOR numericExpression
                  ;

booleanExpression : BOOLEAN
                  | booleanExpression BINARY_BOOLEAN_OPERATOR booleanExpression
                  | UNARY_BOOLEAN_OPERATOR booleanExpression
                  | numericExpression COMPARISON_OPERATOR numericExpression
                  ;

expression: numericExpression | booleanExpression;