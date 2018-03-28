grammar QLS;

// Parser
stylesheet : STYLESHEET ID page+ EOF;
page       : PAGE ID BRAL section+ (default)? BRAR;
section    : SECTION STRING ((question+ | question+ section question*) (default)? |
                        BRAL (question+ | question+ section question*) (default)? BRAR);
question   : QUESTION ID (attributes)?;

default    : DEFAULT type (attributes | (BRAL attributes BRAR));
attributes : (width | font | fontsize | color | widget)+;
type       : (BOOLEAN | MONEY | ID);

widget   : WIDGET (checkbox | radio | spinbox);
width    : 'width' COL INT;
font     : 'font' COL STRING;
fontsize : 'fontsize' COL INT;
color    : 'color' COL HEX;

checkbox : CHECKBOX;
radio    : RADIO choices;
spinbox  : SPINBOX;

choices : PARL (STRING COM)* STRING PARR;

// Lexer
STYLESHEET : 'stylesheet';
PAGE       : 'page';
SECTION    : 'section';
QUESTION   : 'question';
WIDGET     : 'widget';
DEFAULT    : 'default';

CHECKBOX : 'checkbox';
RADIO    : 'radio';
SPINBOX  : 'spinbox';

BOOLEAN : 'boolean';
MONEY   : 'money';

INT    : [0-9]+;
ID     : [A-Za-z][A-Za-z0-9_]*;
STRING : '"' (~('"' | '\\' | '\r' | '\n'))* '"';
HEX    : HASH INT;

BRAL : '{';
BRAR : '}';
PARL : '(';
PARR : ')';
COL  : ':';
COM  : ',';
HASH : '#';

SPACE   : [ \t]+ -> skip;
NEWLINE : '\r'? '\n' -> skip;