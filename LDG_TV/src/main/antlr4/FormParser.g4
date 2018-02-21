// Define a grammar called Hello
grammar FormParser;
/*
 * Parser Rules
 */
form_builder  : 'form' CHARACTERS CURLY_BRACKET_OPEN form_data CURLY_BRACKET_CLOSE;
form_data : (question_structure)+ if_structure?;

question_structure:
    question_identifier
    question_variable
    QUESTION_VARIABLE_SEPERATOR
    question_answer_type
    QUESTION_ANSWER_SEPERATOR?
    question_answer?
;
if_structure:
    IF
    stat_block
    CURLY_BRACKET_OPEN
    (question_structure)+
    CURLY_BRACKET_CLOSE
;

stat_block:
   BRACKET_OPEN
   question_variable
   BRACKET_CLOSE
;

question_identifier : QUESTION_IDENTIFIER;
question_variable: CHARACTERS;
question_answer_type: CHARACTERS;
question_answer: BRACKET_OPEN question_variable (PLUS | MINUS | TIMES | DIV) question_variable BRACKET_CLOSE;

/*
 * Lexer Rules
 */

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;

CURLY_BRACKET_OPEN : '{';
CURLY_BRACKET_CLOSE : '}';
BRACKET_OPEN : '(';
BRACKET_CLOSE : ')';

PLUS: '+';
MINUS: '-';
TIMES: '*';
DIV: '/';

QUESTION_IDENTIFIER : '"' + ((CHARACTERS | NUMBERS | ' ' | ':' | '?')+) + '"';
QUESTION_VARIABLE_SEPERATOR : ':';
QUESTION_ANSWER_SEPERATOR : '=';

IF : 'if';

WHITESPACE : [ \r\n\t] + -> skip;
NEWLINE : ('\r'? '\n' | '\r')+ -> skip;

CHARACTERS : (LOWERCASE | UPPERCASE)+;

fragment NUMBERS
   : ('0' .. '9') + ('.' ('0' .. '9') +)?
   ;

