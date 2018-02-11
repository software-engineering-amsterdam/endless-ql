grammar ql;
//file to define grammar

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

type : 'boolean'
     | 'money'
     | 'string'
     | 'integer'
     | 'date'
     | 'decimal'
     ;

questionName: IDENTIFIER WHITESPACE ':';
questionText : WHITESPACE STRING;
question : questionName questionText type ( '(' expression ')' ) NEWLINE;

form : FORM_HEADER '{' question+ '}';

//To skip New Lines, White spaces and comments
FORM_HEADER : WHITESPACE 'form'  -> skip;
NEWLINE: ('\n' | '\r' | '\r\n') -> skip;
WHITESPACE : (' ' | '\n' | '\r' | '\t') -> skip;
COMMENT			: ('/*' .*? '*/') ->skip;
LINE_COMMENT	: '//' ~[\r\n]* ->skip;

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

