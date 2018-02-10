// Define a grammar called Hello
grammar FormParser;
/*
 * Parser Rules
 */
form_builder  : 'form' FORM_CLASSIFIER CURLY_BRACKET_OPEN form_data CURLY_BRACKT_CLOSE;
form_data : question_structure? if_structure?;

question_structure :
    question_identifier
    question_variable
    question_variable_seperator
    question_answer_type
    question_answer_seperator?
    question_answer?
;
if_structure:
    'if'
    BRACKET_OPEN
    question_variable
    BRACKET_CLOSE
    CURLY_BRACKET_OPEN
    question_structure
    CURLY_BRACKT_CLOSE
;
question_identifier : QUESTION_IDENTIFIER;
question_variable: QUESTION_VARIABLE;
question_variable_seperator : QUESTION_VARIABLE_SEPERATOR;
question_answer_type: QUESTION_ANSWER_TYPE;
question_answer_seperator:  QUESTION_ANSWER_SEPERATOR;
question_answer: QUESTION_ANSWER;
/*
 * Lexer Rules
 */
fragment CHARACTERS : ('a' .. 'z' | 'A' .. 'Z');
fragment NUMBERS : ('0' .. '9');

CURLY_BRACKET_OPEN : '{';
CURLY_BRACKT_CLOSE : '}';
BRACKET_OPEN : '(';
BRACKET_CLOSE : ')';

FORM_CLASSIFIER : CHARACTERS+;

QUESTION_IDENTIFIER : '"' + ((CHARACTERS | NUMBERS | ' ')+) + '?"';
QUESTION_VARIABLE : CHARACTERS+;
QUESTION_VARIABLE_SEPERATOR : ':';
QUESTION_ANSWER_TYPE : CHARACTERS+;
QUESTION_ANSWER_SEPERATOR : '=';
QUESTION_ANSWER : '(' + (CHARACTERS | ' - ')+ ')';

WHITESPACE : ' ' -> skip;
NEWLINE : '\n' -> skip;