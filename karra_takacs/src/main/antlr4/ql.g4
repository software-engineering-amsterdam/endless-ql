grammar ql;

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

questionText : WHITESPACE '"' .*? '"';

question : questionName questionText type ( '(' expression ')' )? NEWLINE;

form : FORM_HEADER '{' question+ '}';

FORM_HEADER : WHITESPACE 'form' WHITESPACE -> skip;

NEWLINE: ('\n' | '\r' | '\r\n') -> skip;

WHITESPACE : (' ' | '\n' | '\r' | '\t') -> skip;

IDENTIFIER: [a-zA-Z0-1_]+;

NUMBER: [0-9]+;

BOOLEAN: 'true' | 'false';

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

