grammar QL;
questionnaire:   'form' FORM_ID '{}'; 

FORM_ID  :   [a-zA-Z]+ ;
NEWLINE:'\r'? '\n' -> skip;
WS  :   [ \t]+ -> skip ;