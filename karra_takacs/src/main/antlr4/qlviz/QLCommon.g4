lexer grammar QLCommon;
QUESTION_TYPE: 'boolean'
             | 'money'
             | 'string'
             | 'integer'
             | 'date'
             | 'decimal'
             ;
//To skip New Lines, White spaces and comments
NEWLINE: ('\n' | '\r' | '\r\n') -> skip;
WHITESPACE : (' ' | '\n' | '\r' | '\t') -> skip;
COMMENT			: ('/*' .*? '*/') ->skip;
LINE_COMMENT	: '//' ~[\r\n]* ->skip;
BRACKET_OPEN : '{';
BRACKET_CLOSE: '}';
PAREN_OPEN: '(';
PAREN_CLOSE: ')';

//keywords
IF	:'if';
FORM_HEADER :  'form';


//literals
IDENTIFIER	: [a-zA-Z0-1_]+;
NUMBER		: [0-9]+(.[0-9]+)?;
BOOLEAN		: 'true' | 'false';
STRING		: '"' .*? '"';

