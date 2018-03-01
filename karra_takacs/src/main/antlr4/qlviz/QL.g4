grammar QL;

//file to define grammar


form : FORM_HEADER IDENTIFIER BRACKET_OPEN questionBlock+ BRACKET_CLOSE EOF;
question: questionText questionName QUESTION_DELIMITER TYPE computedValue?;
questionText: STRING ;
questionBlock:  question+
             | conditionalBlock+ ;
questionName: IDENTIFIER;
conditionalBlock:  IF  PAREN_OPEN (booleanExpression | IDENTIFIER)PAREN_CLOSE
                  BRACKET_OPEN questionBlock+ BRACKET_CLOSE;
computedValue: '=' numericExpression;

//expressions
numericExpression : NUMBER
                  | UNARY_NUMERIC_OPERATOR NUMBER
                  | numericExpression BINARY_NUMERIC_OPERATOR numericExpression
                  | IDENTIFIER
                  | PAREN_OPEN numericExpression PAREN_CLOSE
                  ;

booleanExpression : BOOLEAN
                  | booleanExpression BINARY_BOOLEAN_OPERATOR booleanExpression
                  | UNARY_BOOLEAN_OPERATOR booleanExpression
                  | numericExpression COMPARISON_OPERATOR numericExpression
                  | IDENTIFIER
                  | PAREN_OPEN booleanExpression PAREN_CLOSE
                  ;

expression: numericExpression | booleanExpression;

//To skip New Lines, White spaces and comments
FORM_HEADER :  'form';
NEWLINE: ('\n' | '\r' | '\r\n') -> skip;
WHITESPACE : (' ' | '\n' | '\r' | '\t') -> skip;
COMMENT			: ('/*' .*? '*/') ->skip;
LINE_COMMENT	: '//' ~[\r\n]* ->skip;
BRACKET_OPEN : '{';
BRACKET_CLOSE: '}';
PAREN_OPEN: '(';
PAREN_CLOSE: ')';

QUESTION_DELIMITER: ':';

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

BINARY_BOOLEAN_OPERATOR:  ('&&' | '||') ;
BINARY_NUMERIC_OPERATOR:  NUMERIC_OP ;
UNARY_BOOLEAN_OPERATOR:  '!' ;
UNARY_NUMERIC_OPERATOR:  ('-' | '+') ;

fragment NUMERIC_OP : '+'
                    | '-'
                    | '*'
                    | '/'
                    ;

